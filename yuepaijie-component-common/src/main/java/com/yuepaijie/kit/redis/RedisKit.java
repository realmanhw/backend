package com.yuepaijie.kit.redis;

import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * Created by tanwei on 下午7:48 2018/6/25
 */
@Component("redisKit")
@Slf4j
public class RedisKit {

  @Autowired
  private Pool<Jedis> jedisPool;

  private static final String OK_CODE = "OK";
  private static final String OK_MULTI_CODE = "+OK";
  /**
   * 分布式锁的key前缀
   */
  private final String KEY_LOCK = "yuepaijie:lock:";
  /**
   * 分布式锁失效时间 单位秒
   */
  private final int LOCK_EXPIRE_SECONDS = 10;

  /**
   * 判断命令是否成功执行
   */
  private static boolean isStatusOk(String status) {
    return (status != null) && (OK_CODE.equals(status) || OK_MULTI_CODE.equals(status));
  }

  public Jedis getJedis() {
    return jedisPool.getResource();
  }

  public long publish(final String channel, String message) {
    try (Jedis jedis = getJedis()) {
      return jedis.publish(channel, message);
    }
  }

  public void expire(String key, long seconds) {
    try (Jedis jedis = getJedis()) {
      jedis.expire(key, (int) seconds);
    }
  }

  public void set(String key, String value) {
    try (Jedis jedis = getJedis()) {
      jedis.set(key, value);
    }
  }

  public void set(String key, long seconds, String value) {
    try (Jedis jedis = getJedis()) {
      jedis.setex(key, (int) seconds, value);
    }
  }

  public String get(String key) {
    try (Jedis jedis = getJedis()) {
      return jedis.get(key);
    }
  }

  public Long del(String key) {
    try (Jedis jedis = getJedis()) {
      return jedis.del(key);
    }
  }

  public boolean exists(String key) {
    try (Jedis jedis = getJedis()) {
      return jedis.exists(key);
    }
  }

  public void setex(String key, int seconds, String value) {
    try (Jedis jedis = getJedis()) {
      jedis.setex(key, seconds, value);
    }
  }

  public boolean setnx(String key, String value) {
    try (Jedis jedis = getJedis()) {
      return jedis.setnx(key, value) == 1;
    }
  }

  public boolean setnxex(String key, String value, int sencods) {
    try (Jedis jedis = getJedis()) {
      String result = jedis.set(key, value, "NX", "EX", sencods);
      return isStatusOk(result);
    }
  }

  public boolean setnxpx(String key, String value, long time) {
    try (Jedis jedis = getJedis()) {
      String result = jedis.set(key, value, "NX", "PX", time);
      return isStatusOk(result);
    }
  }

  public boolean setxxpx(String key, String value, long time) {
    try (Jedis jedis = getJedis()) {
      String result = jedis.set(key, value, "XX", "PX", time);
      return isStatusOk(result);
    }
  }

  /**
   * 集合
   */
  public Set<String> smembers(String key) {
    try (Jedis jedis = getJedis()) {
      return jedis.smembers(key);
    }
  }

  public void sadd(String key, String... members) {
    try (Jedis jedis = getJedis()) {
      jedis.sadd(key, members);
    }
  }

  public Boolean sismember(String key, String member) {
    try (Jedis jedis = getJedis()) {
      return jedis.sismember(key, member);
    }
  }

  public Set<String> keys(String prefixKey) {
    try (Jedis jedis = getJedis()) {
      return jedis.keys(prefixKey);
    }
  }

  public String hget(final String key, final String fieldName) {
    try (Jedis jedis = getJedis()) {
      return jedis.hget(key, fieldName);
    }
  }

  public void hset(final String key, final String fieldName, final String value) {
    try (Jedis jedis = getJedis()) {
      jedis.hset(key, fieldName, value);
    }
  }

  public void hsetex(final String key, final Map<String, String> map, int time) {
    try (Jedis jedis = getJedis()) {
      for (Map.Entry<String, String> entry : map.entrySet()) {
        jedis.hset(key, entry.getKey(), entry.getValue());
      }
      jedis.expire(key, time);
    }
  }

  public Long hdel(final String key, final String... fieldsNames) {
    try (Jedis jedis = getJedis()) {
      return jedis.hdel(key, fieldsNames);
    }
  }

  public long incr(String key) {
    try (Jedis jedis = getJedis()) {
      return jedis.incr(key);
    }
  }

  public long incrBy(String key, long increment) {
    try (Jedis jedis = getJedis()) {
      return jedis.incrBy(key, increment);
    }
  }

  /**
   * 获取redis分布式锁
   */
  public boolean getLock(final String id, final int expireTime) {
    try (Jedis jedis = getJedis()) {
      String key = KEY_LOCK + id;
      String result = jedis
          .set(key, String.valueOf(System.currentTimeMillis()), "NX", "EX", expireTime);
      if ("OK".equalsIgnoreCase(result)) {
        return true;
      }

      return false;
    }
  }

  /**
   * 获取redis分布式锁
   *
   * @param id 要锁定的id
   */
  public boolean getLock(String id) {
    return getLock(id, LOCK_EXPIRE_SECONDS);
  }

  /**
   * 释放锁
   */
  public void releaseLock(String id) {
    try (Jedis jedis = getJedis()) {
      if (StringUtils.isBlank(id)) {
        return;
      }
      String key = KEY_LOCK + id;
      jedis.del(key);
    }
  }

  public Long srem(String key, String... members) {
    try (Jedis jedis = getJedis()) {
      return jedis.srem(key, members);
    }
  }
}
