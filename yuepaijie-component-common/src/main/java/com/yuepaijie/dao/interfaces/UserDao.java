package com.yuepaijie.dao.interfaces;

import com.yuepaijie.model.entity.generated.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends DaoHelper {

  public Integer insertSelectiveAutoGenerateNickname(User user);

  public User getUserByNickName(String nickname);
}
