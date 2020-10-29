package com.yuepaijie.dao.interfaces;

import com.yuepaijie.model.entity.generated.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends DaoHelper {

  Integer insertSelectiveAutoGenerateNickname(User user);

  User getUserByNickName(String nickname);
}
