package com.yuepaijie.constants.enums;

import lombok.Getter;

@Getter
public enum Status {
  //
  SUCCESS(0, "成功"),

  FAILED(1,"失败"),

  NEED_LOGIN(2,"未登录"),

  PARAMETER_ERROR(3, "参数错误");

  private final Integer status;
  private final String desc;

  Status(Integer status, String desc) {
    this.status = status;
    this.desc = desc;
  }
}
