package com.yuepaijie.secirity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

  public static Object getUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth == null ? null :  auth.getDetails();
  }
}
