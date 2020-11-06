package com.yuepaijie.security;

import com.yuepaijie.model.vo.UserLoginAccountDetail;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

  public static String getToken(HttpServletRequest request) {
    return request.getHeader("Authorization");
  }

  static Authentication createAuth(UserLoginAccountDetail detail) {
    CommonAuthenticatedToken token = new CommonAuthenticatedToken(detail.getUserAuthId());
    token.setDetails(detail);
    return token;
  }

  public static Boolean isLogin(){
    UserLoginAccountDetail userLoginAccountDetail = getUser();
    if(userLoginAccountDetail == null){
      return false;
    }
    return true;
  }

  public static UserLoginAccountDetail getUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth == null ? null : (UserLoginAccountDetail) auth.getDetails();
  }

  /**
   * 获取用户Id
   * @return
   */
  public static Long getUserId() {
    UserLoginAccountDetail user = getUser();
    return user == null ? null : user.getUserId();
  }

  /**
   * 获取登录账户Id
   * @return
   */
  public static Long getUserAuthId() {
    UserLoginAccountDetail user = getUser();
    return user == null ? null : user.getUserAuthId();
  }
}
