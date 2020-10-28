package com.yuepaijie.constants.enums;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public enum IdentityType {
  //
  ACCOUNT_PWD("account_pwd", "账号密码登录"),

  EMAIL("email", "邮箱登录"),

  WE_CHAT("wechat", "微信登录"),

  WE_BLOG("weblog", "微博登录")
  ;

  private final String status;
  private final String desc;

  IdentityType(String status, String desc) {
    this.status = status;
    this.desc = desc;
  }

  public static Set<String> getAllStatus(){
    Set<String> set = new HashSet<>();
    for(IdentityType type : values() ){
      set.add(type.getStatus());
    }
    return set;
  }
}
