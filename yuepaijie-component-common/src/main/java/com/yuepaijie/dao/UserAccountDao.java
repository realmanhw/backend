package com.yuepaijie.dao;

import com.yuepaijie.dao.generated.UserAccountMapper;
import com.yuepaijie.pojo.entity.generated.UserAccount;
import com.yuepaijie.pojo.entity.generated.UserAccountExample;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
