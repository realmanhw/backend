package com.yuepaijie.model.vo;

import lombok.Data;

@Data
public class RestEntity {
  private int code;
  private Object obj;
  public RestEntity(){

  }
  public RestEntity(int code, String msg){
    this.code = code;
    this.obj = msg;
  }
}
