package com.yuepaijie.pojo.enums;

import lombok.Getter;

@Getter
public enum  ResCode {
  //
  BAD_REQUEST(400, "请求失败"),
  //
  SUCCESS(200, "请求成功")
  ;

  private final Integer status;
  private final String desc;

  ResCode(Integer status, String desc) {
    this.status = status;
    this.desc = desc;
  }
}
