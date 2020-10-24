package com.yuepaijie.secirity;

import com.yuepaijie.dao.generated.UserAccountMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class UsernamePasswordAuthenticationFilter extends BaseAuthenticationFilter {

  private final UserAccountMapper userAccountMapper;

  public UsernamePasswordAuthenticationFilter(UserAccountMapper userAccountMapper) {
    super(new AntPathRequestMatcher("/auth/login", "POST"));
    this.userAccountMapper = userAccountMapper;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
    return null;
  }
}
