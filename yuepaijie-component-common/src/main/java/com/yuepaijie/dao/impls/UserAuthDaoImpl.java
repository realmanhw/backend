package com.yuepaijie.dao.impls;

import com.yuepaijie.dao.mapper.generated.UserAuthMapper;
import com.yuepaijie.dao.interfaces.UserAuthDao;
import com.yuepaijie.kit.TimeUtils;
import com.yuepaijie.kit.encryption.CredentialUtils;
import com.yuepaijie.model.entity.generated.UserAuth;
import com.yuepaijie.model.entity.generated.UserAuthExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthDaoImpl implements UserAuthDao {

  @Resource UserAuthMapper userAuthMapper;

  @Override
  public Integer insertSelective(UserAuth userAuth) {
    userAuth.setIdentifier(userAuth.getIdentifier().trim());
    String credential = userAuth.getCredential().trim();
    userAuth.setCredential(CredentialUtils.credentialEncode(credential));
    userAuth.setCreatetime(TimeUtils.getNow());
    userAuth.setUpdatetime(TimeUtils.getNow());
    return userAuthMapper.insertSelective(userAuth);
  }

  @Override
  public List<UserAuth> getByIdentifierAndIdentityType(String identifier, String identityType) {
    UserAuthExample example = new UserAuthExample();
    example.createCriteria()
        .andIdentifierEqualTo(identifier.trim())
        .andIdentityTypeEqualTo(identityType.trim());
    List<UserAuth> userAuths = userAuthMapper.selectByExample(example);
    return userAuths;
  }

  @Override
  public UserAuth checkPassword(String identifier, String credential, String identityType){
    UserAuthExample example = new UserAuthExample();
    example.createCriteria()
        .andIdentifierEqualTo(identifier.trim())
        .andCredentialEqualTo(CredentialUtils.credentialEncode(credential.trim()))
        .andIdentityTypeEqualTo(identityType.trim());
    return firstOrNull(userAuthMapper.selectByExample(example));
  }

  @Override
  public UserAuth getById(Long id){
    return userAuthMapper.selectByPrimaryKey(id);
  }
}
