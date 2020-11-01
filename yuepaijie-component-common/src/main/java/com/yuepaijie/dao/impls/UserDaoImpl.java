package com.yuepaijie.dao.impls;

import com.mysql.cj.util.StringUtils;
import com.yuepaijie.dao.generated.UserMapper;
import com.yuepaijie.dao.interfaces.UserDao;
import com.yuepaijie.kit.TimeUtils;
import com.yuepaijie.model.entity.generated.User;
import com.yuepaijie.model.entity.generated.UserExample;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

  @Resource UserMapper userMapper;

  @Override
  public Integer insertSelectiveAutoGenerateNickname(User user) {
    if (user.getNickname() == null || StringUtils.isNullOrEmpty(user.getNickname().trim())) {
      String uuid = UUID.randomUUID().toString().replace("-", "");
      user.setNickname("yuepai" + uuid);
    }
    int count = 10;
    while (getUserByNickName(user.getNickname().trim()) != null) {
      String uuid = UUID.randomUUID().toString().replace("-", "");
      user.setNickname("yuepai" + uuid);
      count--;
      if (count < 0) {
        return 0;
      }
    }
    user.setNickname(user.getNickname().trim());
    user.setId(null);
    user.setCreatetime(TimeUtils.getNow());
    user.setUpdatetime(TimeUtils.getNow());
    return userMapper.insertSelective(user);
  }

  @Override
  public User getUserByNickName(String nickname) {
    UserExample example = new UserExample();
    example.createCriteria().andNicknameEqualTo(nickname.trim());
    return firstOrNull(userMapper.selectByExample(example));
  }

  ;
}
