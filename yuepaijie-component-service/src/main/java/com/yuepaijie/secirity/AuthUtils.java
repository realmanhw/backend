package com.yuepaijie.secirity;

import com.yuepaijie.pojo.vo.UserLoginAccountDetail;
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

}
