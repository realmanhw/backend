package com.yuepaijie.secirity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Slf4j
public class LogoutFilter extends BaseAuthenticationFilter {

  public LogoutFilter() {
    super(new AntPathRequestMatcher("/auth/logout", "POST"));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
    return null;
  }
}
