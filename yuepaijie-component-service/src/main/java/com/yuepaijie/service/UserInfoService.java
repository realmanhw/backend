package com.yuepaijie.service;

import com.mysql.cj.util.StringUtils;
import java.util.Optional;
import com.yuepaijie.dao.generated.UserAccountMapper;
import com.yuepaijie.model.entity.generated.UserAccount;
import com.yuepaijie.model.entity.generated.UserAccountExample;
import com.yuepaijie.kit.TimeUtils;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
public class UserInfoService {

  @Resource
  UserAccountMapper userAccountMapper;

  public Boolean addOrModify(UserAccount userAccount) {
    if (StringUtils.isNullOrEmpty(userAccount.getAccount())) {
      return false;
    }
    UserAccountExample userAccountExample = new UserAccountExample();
    userAccountExample.createCriteria().andAccountEqualTo(userAccount.getAccount());
    List<UserAccount> exists = userAccountMapper.selectByExample(userAccountExample);
    if (CollectionUtils.isEmpty(exists)) {
      if (StringUtils.isNullOrEmpty(userAccount.getNickname())) {
        return false;
      }
      if (StringUtils.isNullOrEmpty(userAccount.getPassword())) {
        return false;
      }
      userAccount.setCreatetime(TimeUtils.getNow());
      userAccount.setUpdatetime(TimeUtils.getNow());
      int res = userAccountMapper.insert(userAccount);
      return res != 0;
    }
    if (exists.size() > 1) {
      log.error("同样账户的用户数量大于1个,account:" + userAccount.getAccount());
      return false;
    }
    UserAccount exist = exists.get(0);
    userAccount.setId(exist.getId());
    userAccount.setUpdatetime(TimeUtils.getNow());
    userAccountExample = new UserAccountExample();
    userAccountExample.createCriteria().andIdEqualTo(exist.getId());
    userAccountMapper.updateByExampleSelective(userAccount, userAccountExample);
    return false;
  }

  public Boolean addUserAccount(UserAccount userAccount) {
    if (StringUtils.isNullOrEmpty(userAccount.getAccount())) {
      return false;
    }
    UserAccountExample userAccountExample = new UserAccountExample();
    userAccountExample.createCriteria().andAccountEqualTo(userAccount.getAccount());
    List<UserAccount> exists = userAccountMapper.selectByExample(userAccountExample);
    if (CollectionUtils.isEmpty(exists)) {
      if (StringUtils.isNullOrEmpty(userAccount.getNickname())) {
        return false;
      }
      if (StringUtils.isNullOrEmpty(userAccount.getPassword())) {
        return false;
      }
      userAccount.setCreatetime(TimeUtils.getNow());
      userAccount.setUpdatetime(TimeUtils.getNow());
      int res = userAccountMapper.insert(userAccount);
      return res != 0;
    }
    if (exists.size() > 1) {
      log.error("同样账户的用户数量大于1个,account:" + userAccount.getAccount());
      return false;
    }
    return false;
  }

  public Boolean updateUserAccount(UserAccount userAccount) {
    if (StringUtils.isNullOrEmpty(userAccount.getAccount())) {
      return false;
    }
    UserAccountExample userAccountExample = new UserAccountExample();
    userAccountExample.createCriteria().andAccountEqualTo(userAccount.getAccount());
    List<UserAccount> exists = userAccountMapper.selectByExample(userAccountExample);
    if (CollectionUtils.isEmpty(exists)) {
      return false;
    }
    if (exists.size() > 1) {
      log.error("同样账户的用户数量大于1个,account:" + userAccount.getAccount());
      return false;
    }
    UserAccount exist = exists.get(0);
    userAccount.setId(exist.getId());
    userAccount.setUpdatetime(TimeUtils.getNow());
    userAccountExample = new UserAccountExample();
    userAccountExample.createCriteria().andIdEqualTo(exist.getId());
    int res = userAccountMapper.updateByExampleSelective(userAccount, userAccountExample);
    return res != 0;
  }

  public Optional<UserAccount> checkPassword(String account, String password) {
    if (StringUtils.isNullOrEmpty(account)) {
      return Optional.empty();
    }
    if (StringUtils.isNullOrEmpty(password)) {
      return Optional.empty();
    }
    UserAccountExample userAccountExample = new UserAccountExample();
    userAccountExample.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(password);
    List<UserAccount> exists = userAccountMapper.selectByExample(userAccountExample);
    if (CollectionUtils.isEmpty(exists)) {
      return Optional.empty();
    }
    if (exists.size() > 1) {
      log.error("同样账户的用户数量大于1个,account:" + account);
      return Optional.empty();
    }
    return Optional.ofNullable(exists.get(0));
  }
}
