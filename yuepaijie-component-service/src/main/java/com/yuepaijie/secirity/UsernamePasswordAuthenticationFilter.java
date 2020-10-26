package com.yuepaijie.secirity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuepaijie.dao.generated.UserAccountMapper;
import com.yuepaijie.constants.enums.ResCode;
import com.yuepaijie.pojo.vo.LoginParam;
import com.yuepaijie.pojo.vo.RestEntity;
import com.yuepaijie.pojo.vo.UserLoginAccountDetail;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

public class UsernamePasswordAuthenticationFilter extends BaseAuthenticationFilter {

  private final UserAccountMapper userAccountMapper;
  private final AuthenticationStore authenticationStore;

  public UsernamePasswordAuthenticationFilter(UserAccountMapper userAccountMapper, AuthenticationStore authenticationStore) {
    super(new AntPathRequestMatcher("/auth/login", "POST"));
    this.userAccountMapper = userAccountMapper;
    this.authenticationStore = authenticationStore;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException {
    LoginParam loginParam = parseLoginParam(request);
    String account = loginParam.getAccount();
    String password = loginParam.getPassword();

    if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
      throw new BadCredentialsException("Empty account or password");
    }

    account = account.toLowerCase();
    password = password.toLowerCase();

    Authentication authentication = this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(account, password));

    return authentication;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req,
      HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
    SecurityContextHolder.getContext().setAuthentication(auth);
    String token = authenticationStore.addAuthentication(auth);
    UserLoginAccountDetail detail = (UserLoginAccountDetail) auth.getDetails();
    detail.setToken(token);
    setResponse(res, new RestEntity(ResCode.SUCCESS.getStatus(),token));
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed)
      throws IOException {
    RestEntity res = null;
    if (failed instanceof AuthenticationServiceException) {
      res = new RestEntity(ResCode.BAD_REQUEST.getStatus(),"登录验证失败");
    } else {
      res = new RestEntity(ResCode.BAD_REQUEST.getStatus(),"登录异常");
    }
    setResponse(response, res);
  }

  private LoginParam parseLoginParam(HttpServletRequest request) throws IOException {
    try (InputStream is = request.getInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(is, LoginParam.class);
    }
  }
}
