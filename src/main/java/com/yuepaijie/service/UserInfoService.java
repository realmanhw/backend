package com.yuepaijie.service;

import com.yuepaijie.common.dao.generated.UserAccountMapper;
import com.yuepaijie.common.entity.generated.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

  @Autowired UserAccountMapper userAccountMapper;

  public Boolean addOrModify(UserAccount userAccount){
    if(userAccount.getName()!=null){
      userAccountMapper.insert(userAccount);
      return true;
    }
    return false;
  }
}
