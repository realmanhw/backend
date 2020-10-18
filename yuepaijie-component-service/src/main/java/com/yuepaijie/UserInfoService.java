package com.yuepaijie;

import com.yuepaijie.dao.generated.UserAccountMapper;
import com.yuepaijie.entity.generated.UserAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoService {

  @Resource
  UserAccountMapper userAccountMapper;

  public Boolean addOrModify(UserAccount userAccount){
    if(userAccount.getName()!=null){
      userAccountMapper.insert(userAccount);
      return true;
    }
    return false;
  }
}
