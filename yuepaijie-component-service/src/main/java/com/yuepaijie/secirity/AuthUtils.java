package com.yuepaijie.secirity;

import com.yuepaijie.pojo.vo.UserLoginAccountDetail;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

  static Authentication createAuth(UserLoginAccountDetail detail) {
    CommonAuthenticatedToken token = new CommonAuthenticatedToken(detail.getId());
    token.setDetails(detail);
    return token;
  }
  public static Object getUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth == null ? null :  auth.getDetails();
  }
  public static String getToken(HttpServletRequest request) {
    return request.getHeader("Authorization");
  }
}
