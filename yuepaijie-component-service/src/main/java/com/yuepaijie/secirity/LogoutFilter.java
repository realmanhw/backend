package com.yuepaijie.secirity;

import com.yuepaijie.constants.RedisKeys;
import com.yuepaijie.constants.enums.Status;
import com.yuepaijie.kit.redis.RedisKit;
import com.yuepaijie.model.vo.RestEntity;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
public class LogoutFilter extends BaseAuthenticationFilter {

  private final AuthenticationStore authenticationStore;

  private final RedisKit redisKit;
  public LogoutFilter(AuthenticationStore authenticationStore, RedisKit redisKit) {
    super(new AntPathRequestMatcher("/auth/logout", "POST"));
    this.authenticationStore = authenticationStore;
    this.redisKit = redisKit;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    return new AbstractAuthenticationToken(null) {
      @Override
      public Object getCredentials() {
        return null;
      }

      @Override
      public Object getPrincipal() {
        return null;
      }
    };
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
    String token = AuthUtils.getToken(request);
    authenticationStore.removeByTicket(token);
    redisKit.del(RedisKeys.AUTHENTICATOR_TOKEN_KEY + token);
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    setResponse(response, RestEntity.ok());
  }
}
