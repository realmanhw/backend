package com.yuepaijie.dao.interfaces;

import com.yuepaijie.model.entity.generated.UserAuth;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthDao extends DaoHelper {

  Integer insertSelective(UserAuth userAuth);

  List<UserAuth> getByIdentifierAndIdentityType(String Identifier, String identityType);

  UserAuth checkPassword(String Identifier, String credential, String identityType);

  UserAuth getById(Long id);
}
