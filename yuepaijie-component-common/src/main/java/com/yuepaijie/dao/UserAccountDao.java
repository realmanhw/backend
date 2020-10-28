package com.yuepaijie.dao;

import com.yuepaijie.dao.generated.UserAccountMapper;
import com.yuepaijie.model.entity.generated.UserAccount;
import com.yuepaijie.model.entity.generated.UserAccountExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserAccountDao implements DaoHelper{

  @Resource UserAccountMapper userAccountMapper;

  public UserAccount getUserAccountById(Long id){
    UserAccountExample userAccountExample = new UserAccountExample();
    userAccountExample.createCriteria().andIdEqualTo(id);
    List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
    return firstOrNull(userAccounts);
  }
}
