package com.yuepaijie.dao.interfaces;

import com.yuepaijie.model.entity.generated.UserAuth;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthDao extends DaoHelper {

  public Integer insertSelective(UserAuth userAuth);

  public List<UserAuth> getByIdentifierAndIdentityType(String Identifier, String identityType);

  public UserAuth checkPassword(String Identifier, String credential, String identityType);

  public UserAuth getById(Long id);
}
