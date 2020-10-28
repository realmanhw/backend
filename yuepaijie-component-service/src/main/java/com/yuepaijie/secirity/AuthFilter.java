package com.yuepaijie.secirity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuepaijie.constants.enums.ResCode;
import com.yuepaijie.model.vo.RestEntity;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class AuthFilter extends OncePerRequestFilter {

  private final AntPathMatcher matcher = new AntPathMatcher();
  private final AuthenticationStore authenticationStore;
  private String[] whiteList;

  public AuthFilter(String[] whiteList, AuthenticationStore authenticationStore) {
    this.whiteList = whiteList;
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
    } else if (inWhiteList(request.getRequestURI())) {
      authentication = createGuest();
      SecurityContextHolder.getContext().setAuthentication(authentication);

      filterChain.doFilter(request, response);

      SecurityContextHolder.getContext().setAuthentication(null);
    } else {
      response.getWriter().write(new ObjectMapper().writeValueAsString(new RestEntity(ResCode.BAD_REQUEST.getStatus(),"Had not log in")));
    }
  }

  private boolean inWhiteList(String path) {
    if (ArrayUtils.isNotEmpty(whiteList)) {
      for (String pattern : whiteList) {
        if (matcher.match(pattern, path)) {
          return true;
        }
      }
    }
    return false;
  }

  private Authentication createGuest() {
    return new UsernamePasswordAuthenticationToken("GUEST", "", AuthorityUtils.NO_AUTHORITIES);
  }
}
