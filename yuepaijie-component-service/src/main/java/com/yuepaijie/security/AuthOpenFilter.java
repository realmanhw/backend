package com.yuepaijie.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 开放式登录控制过滤器，请求都放行，但仅为登录者分配了身份，适合开放式社区网站
 */
@Slf4j
public class AuthOpenFilter extends OncePerRequestFilter {

  private final AntPathMatcher matcher = new AntPathMatcher();
  private final AuthenticationStore authenticationStore;

  public AuthOpenFilter(AuthenticationStore authenticationStore) {
    this.authenticationStore = authenticationStore;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token = AuthUtils.getToken(request);
    Authentication authentication = authenticationStore.getAuthentication(token, true);
    if (authentication != null) {
      SecurityContextHolder.getContext().setAuthentication(authentication);

      filterChain.doFilter(request, response);

      SecurityContextHolder.getContext().setAuthentication(null);
    } else {
      authentication = createGuest();
      SecurityContextHolder.getContext().setAuthentication(authentication);

      filterChain.doFilter(request, response);

      SecurityContextHolder.getContext().setAuthentication(null);
    }
  }

  private Authentication createGuest() {
    return new UsernamePasswordAuthenticationToken("GUEST", "", AuthorityUtils.NO_AUTHORITIES);
  }
}
