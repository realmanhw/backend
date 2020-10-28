package com.yuepaijie.service;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import com.yuepaijie.constants.enums.IdentityType;
import com.yuepaijie.dao.interfaces.UserAuthDao;
import com.yuepaijie.dao.interfaces.UserDao;
import com.yuepaijie.model.entity.generated.User;
import com.yuepaijie.model.entity.generated.UserAuth;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserInfoService {

  @Autowired UserAuthDao userAuthDao;

  @Autowired UserDao userDao;

  @Transactional
  public Boolean signUp(User user, UserAuth userAuth) {
    if (!IdentityType.getAllStatus().contains(userAuth.getIdentityType())) {
      return false;
    }
    List<UserAuth> userAuths =
        userAuthDao.getByIdentifierAndIdentityType(userAuth.getIdentifier(), userAuth.getIdentityType());
    if (userAuths != null && userAuths.size() > 0) {
      return false;
    }
    Integer res = userDao.insertSelectiveAutoGenerateNickname(user);
    if (res == 0) {
      return false;
    }
    Long key = user.getId();
    if (key == null) {
      log.error("sign up user wrong: " + JSON.toJSONString(user));
      return false;
    }
    userAuth.setUserId(key);
    res = userAuthDao.insertSelective(userAuth);
    return res != 0;
  }

  public UserAuth checkPassword(String identifier, String credential, String identityType) {
    if (StringUtils.isNullOrEmpty(identifier)) {
      return null;
    }
    if (StringUtils.isNullOrEmpty(credential)) {
      return null;
    }
    if (StringUtils.isNullOrEmpty(identityType)) {
      return null;
    }
    return userAuthDao.checkPassword(identifier,credential,identityType);
  }
}
