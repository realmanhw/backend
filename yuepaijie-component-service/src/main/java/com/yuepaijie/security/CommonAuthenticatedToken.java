package com.yuepaijie.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class CommonAuthenticatedToken extends AbstractAuthenticationToken {

  private final Long loginAccountId;

  public CommonAuthenticatedToken(final Long loginAccountId) {
    super(null);
    this.loginAccountId = loginAccountId;

    super.setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return loginAccountId;
  }
}
