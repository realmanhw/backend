package com.yuepaijie.security;

import com.yuepaijie.model.vo.UserLoginAccountDetail;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

  static Authentication createAuth(UserLoginAccountDetail detail) {
    CommonAuthenticatedToken token = new CommonAuthenticatedToken(detail.getUserAuthId());
    token.setDetails(detail);
    return token;
  }

  public static UserLoginAccountDetail getUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth == null ? null : (UserLoginAccountDetail) auth.getDetails();
  }

  public static String getToken(HttpServletRequest request) {
    return request.getHeader("Authorization");
  }

  public static boolean isAdmin() {
    UserLoginAccountDetail user = getUser();
    return user != null && user.isAdmin();
  }

  public static Long getUserId() {
    UserLoginAccountDetail user = getUser();
    return user == null ? null : user.getUserId();
  }

  public static Long getUserAuthId() {
    UserLoginAccountDetail user = getUser();
    return user == null ? null : user.getUserAuthId();
  }

  public static String getUserType() {
    UserLoginAccountDetail user = getUser();
    return user == null ? null : user.getUserType();
  }
}
