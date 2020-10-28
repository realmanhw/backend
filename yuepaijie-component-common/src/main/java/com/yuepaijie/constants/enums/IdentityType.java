package com.yuepaijie.constants.enums;

public enum IdentityType {
  //
  BAD_REQUEST(0, "账号密码登录"),

  EMAIL(1, "邮箱登录"),

  WE_CHAT(3, "微信登录"),

  WE_BLOG(4, "微博登录")
  ;

  private final Integer status;
  private final String desc;

  IdentityType(Integer status, String desc) {
    this.status = status;
    this.desc = desc;
  }
}
