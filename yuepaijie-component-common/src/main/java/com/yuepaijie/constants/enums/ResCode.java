package com.yuepaijie.constants.enums;

import lombok.Getter;

@Getter
public enum ResCode {
  //
  BAD_REQUEST(0, "请求失败"),

  SUCCESS(1, "请求成功"),

  PARAMETER_ERROR(2, "参数错误");;

  private final Integer status;
  private final String desc;

  ResCode(Integer status, String desc) {
    this.status = status;
    this.desc = desc;
  }
}
