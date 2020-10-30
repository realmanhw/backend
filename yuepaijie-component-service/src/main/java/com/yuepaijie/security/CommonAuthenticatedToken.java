package com.yuepaijie.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class CommonAuthenticatedToken extends AbstractAuthenticationToken {

  //登录账户Id
  private final Long userAuthId;

  public CommonAuthenticatedToken(final Long loginAccountId) {
    super(null);
    this.userAuthId = loginAccountId;

    super.setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return userAuthId;
  }
}
