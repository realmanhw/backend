package com.yuepaijie.security;

import com.yuepaijie.constants.enums.IdentityType;
import com.yuepaijie.model.entity.generated.UserAuth;
import com.yuepaijie.model.vo.UserLoginAccountDetail;
import com.yuepaijie.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author hw
 */
@Component
@Slf4j
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  UserInfoService userInfoService;

  @Override
  public Authentication authenticate(Authentication auth) throws AuthenticationException {
    String account = (String) auth.getPrincipal();
    String password = (String) auth.getCredentials();
    UserAuth userAuth = userInfoService.checkPassword(account,password, IdentityType.ACCOUNT_PWD.getStatus());
    if(userAuth == null){
      throw new AuthenticationServiceException("登录失败");
    }
    return authenticate(userAuth);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication == UsernamePasswordAuthenticationToken.class;
  }

  private Authentication authenticate(UserAuth userAuth){
    UserLoginAccountDetail userLoginAccountDetail = new UserLoginAccountDetail();
    userLoginAccountDetail.setId(userAuth.getId());
    userLoginAccountDetail.setIdentifier(userAuth.getIdentifier());
    userLoginAccountDetail.setIdentityType(userAuth.getIdentityType());
    return createAuth(userLoginAccountDetail);
  }

  private Authentication createAuth(UserLoginAccountDetail detail) {
    CommonAuthenticatedToken token = new CommonAuthenticatedToken(detail.getId());
    token.setDetails(detail);
    return token;
  }
}
