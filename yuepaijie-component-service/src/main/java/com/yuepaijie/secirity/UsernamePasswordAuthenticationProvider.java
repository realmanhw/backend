package com.yuepaijie.secirity;

import com.yuepaijie.model.entity.generated.UserAccount;
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
    UserAccount userAccount = userInfoService.checkPassword(account,password).orElse(null);
    if(userAccount == null){
      throw new AuthenticationServiceException("登录失败");
    }
    return authenticate(userAccount);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication == UsernamePasswordAuthenticationToken.class;
  }

  private Authentication authenticate(UserAccount userAccount){
    UserLoginAccountDetail userLoginAccountDetail = new UserLoginAccountDetail();
    userLoginAccountDetail.setId(userAccount.getId());
    userLoginAccountDetail.setAccount(userAccount.getAccount());
    return createAuth(userLoginAccountDetail);
  }

  private Authentication createAuth(UserLoginAccountDetail detail) {
    CommonAuthenticatedToken token = new CommonAuthenticatedToken(detail.getId());
    token.setDetails(detail);
    return token;
  }
}
