package com.yuepaijie.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuepaijie.constants.exceptions.AuthStoreException;
import com.yuepaijie.dao.impls.UserAuthDaoImpl;
import com.yuepaijie.kit.redis.RedisKit;
import com.yuepaijie.model.entity.generated.UserAuth;
import com.yuepaijie.model.vo.UserLoginAccountDetail;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import static com.yuepaijie.constants.RedisKeys.TICKET_DICT_KEY;
import static com.yuepaijie.constants.RedisKeys.USER_DETAIL_PREFIX;

@Slf4j
@Component
public class AuthenticationStore {

  /**
   * 存储格式：
   * 所有信息的 Key 均以 yuepaijie_auth 开头，与其他数据隔离，包含以下两种：
   * 1. 授权信息：yuepaijie_auth:user_detail:{ticket}
   * 使用 string 进行存储，使用带标记的 json 进行存储
   * 2. username 与 ticket 对应关系：yuepaijie_auth:user_ticket_dict
   * 使用 hashList 进行存储，username -> ticket
   *
   * PS：清除缓存的命令
   * eval "return redis.call('del', unpack(redis.call('keys', 'yuepaijie_auth*')))" 0
   */

  /**
   * 授权信息以 标记:JSON 格式的字符串进行存储 字符串开头使用 1 和 0 来标记是否管理员，方便反序列化
   */
  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private AuthConfig authConfig;

  @Autowired
  private RedisKit redisKit;

  @Autowired
  private UserAuthDaoImpl userAuthDao;

  public AuthenticationStore() {
    mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * 根据 ticket 获取授权信息
   */
  public UserLoginAccountDetail loadDetail(String ticket, boolean resetTtl) {
    try (Jedis jedis = redisKit.getJedis()) {
      String key = USER_DETAIL_PREFIX + ticket;
      boolean expired;
      if (resetTtl) {
        // 重置过期时间
        Long result = jedis.pexpire(key, ttl());
        if (result == null || result < 1) {
          // 重置失败则 ticket 不存在或者已经过期被删（或者 redis 版本过低）
          log.warn("Token[{}] has expired", ticket);
          return null;
        }
        expired = false; // 重置成功
      } else {
        // 检测过期时间
        Long ttl = jedis.pttl(key);
        if (ttl == null || ttl == -2) {
          return null;
        }
        if (ttl == -1) {
          throw new IllegalStateException("" + ttl);
        }
        expired = ttl < 1000; // 剩余时间小于 1 秒则默认为过期
      }

      // 如果过期则进行删除
      String result = jedis.get(key);
      if (result != null) {
        UserLoginAccountDetail detail = deserialize(result);
        if (!expired) {
          return detail;
        }
        if (detail != null) {
          log.warn("Token has expired, remove the relationship between loginAccountId[{}] and ticket[{}]",
              detail.getUserAuthId(), ticket);
          removeByLoginAccountId(detail.getUserAuthId().toString());
        }
      }
    }

    return null;
  }

  /**
   * 更新授权信息
   */
  public void updateAuthentication(Authentication auth) {
    if (auth == null) {
      throw new AuthStoreException("auth is null.");
    }
    String loginAccountId = String.valueOf(auth.getPrincipal());
    try {
      updateDetail(loginAccountId, (UserLoginAccountDetail) auth.getDetails());
    } catch (Exception e) {
      throw new AuthStoreException(e);
    }
  }

  public void refreshAuthentication(Long loginAccountId) {
    String ticket = redisKit.hget(TICKET_DICT_KEY, loginAccountId.toString());
    if (ticket == null) {
      log.warn("Can find ticket by loginAccountId {}", loginAccountId);
      return;
    }

    UserLoginAccountDetail detail = loadDetail(ticket, false);
    UserAuth userAuth = userAuthDao.getById(loginAccountId);

    UserLoginAccountDetail updated = new UserLoginAccountDetail(userAuth, detail);

    refreshDetail(ticket, updated);
  }

  /**
   * 保存 ticket 对应的授权信息
   */
  private void saveDetail(String ticket, UserLoginAccountDetail detail) {
    String key = USER_DETAIL_PREFIX + ticket;
    String value = serialize(detail);
    boolean result = redisKit.setnxpx(key, value, ttl());
    if (!result) {
      log.error("saveDetail error. token:{}", ticket);
      throw new IllegalStateException();
    }
    log.info("Save User[accountId={}] login details, token={}", detail.getUserAuthId(), ticket);
  }

  private void refreshDetail(String ticket, UserLoginAccountDetail detail) {
    String key = USER_DETAIL_PREFIX + ticket;
    String value = serialize(detail);
    boolean result = redisKit.setxxpx(key, value, ttl());
    if (!result) {
      log.error("refreshDetail error. token:{}", ticket);
      throw new IllegalStateException();
    }
  }

  /**
   * 更新 loginAccountId 对应的授权信息
   */
  private boolean updateDetail(String loginAccountId, UserLoginAccountDetail detail) {
    String ticket = redisKit.hget(TICKET_DICT_KEY, loginAccountId);
    if (ticket == null) {
      return false;
    }

    String key = USER_DETAIL_PREFIX + ticket;
    String value = serialize(detail);
    boolean result = redisKit.setxxpx(key, value, ttl());

    if (!result) {
      log.error("updateDetail error. token:{}", ticket);
      throw new IllegalStateException();
    }
    return true;
  }

  /**
   * 更新 loginAccountId 和 ticket 的对应关系
   */
  private String addTicket(String loginAccountId, String ticket) {
    String oldTicket = redisKit.hget(TICKET_DICT_KEY, loginAccountId);
    if (oldTicket != null) {
      removeByTicket(oldTicket);
      log.info("Old token[{}] of the account[{}] is replaced", oldTicket, loginAccountId);
    }
    redisKit.hset(TICKET_DICT_KEY, loginAccountId, ticket);
    log.info("Add new login token[{}] to account[{}]", ticket, loginAccountId);
    return oldTicket;
  }

  /**
   * 删除授权信息
   */
  void removeByTicket(String ticket) {
    String key = USER_DETAIL_PREFIX + ticket;
    redisKit.del(key);
  }

  private void removeByLoginAccountId(String loginAccountId) {
    String ticket = redisKit.hget(TICKET_DICT_KEY, loginAccountId);
    if (ticket != null) {
      String key = USER_DETAIL_PREFIX + ticket;
      redisKit.del(key);
      redisKit.hdel(TICKET_DICT_KEY, loginAccountId);
    }
  }

  /**
   * 添加授权信息
   *
   * @param auth 用户授权信息
   * @return 系统生成的 ticket
   */
  public String addAuthentication(Authentication auth) {
    String loginAccountId = String.valueOf(auth.getPrincipal());
    String ticket = UUID.randomUUID().toString().replace("-", "");

    try {
      addTicket(loginAccountId, ticket);
      UserLoginAccountDetail details = (UserLoginAccountDetail) auth.getDetails();
      saveDetail(ticket, details);
    } catch (Exception e) {
      throw new AuthStoreException(String.format("Token[%s] save exception, accountId=%s", ticket, loginAccountId), e);
    }
    return ticket;
  }

  /**
   * 根据 ticket 获取授权信息
   *
   * @param ticket   系统生成的 ticket
   * @param resetTtl 是否更新过期时间
   */
  Authentication getAuthentication(String ticket, boolean resetTtl) {
    if (ticket == null) {
      return null;
    }

    UserLoginAccountDetail detail;
    try {
      detail = loadDetail(ticket, resetTtl);
    } catch (Exception e) {
      throw new AuthStoreException(String.format("Token[%s] loading exception", ticket), e);
    }
    if (detail == null) {
      return null;
    }
    return AuthUtils.createAuth(detail);
  }

  private String serialize(UserLoginAccountDetail detail) {
    String prefix = detail.isAdmin() ? "1" : "0";
    String json;
    try {
      json = mapper.writeValueAsString(detail);
    } catch (JsonProcessingException e) {
      log.error("User[accountId={}, userId={}] information serialization failed", detail.getUserAuthId(), detail.getUserId());
      throw new RuntimeException(e);
    }
    return prefix + ':' + json;
  }

  private UserLoginAccountDetail deserialize(String value) {
    if (value.length() < 2 || value.charAt(1) != ':' ||
        (value.charAt(0) != '1' && value.charAt(0) != '0')) {
      throw new IllegalArgumentException(value);
    }

    String json = value.substring(2);
    try {
      return mapper.readValue(json, UserLoginAccountDetail.class);
    } catch (IOException e) {
      log.error("Failed to deserialize user information, value={}", value);
      throw new RuntimeException(e);
    }
  }

  private long ttl() {
    return TimeUnit.MINUTES.toMillis(authConfig.getTimeoutMinute());
  }
}
