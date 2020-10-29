package com.yuepaijie.model.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.yuepaijie.constants.View;
import com.yuepaijie.constants.enums.Status;
import lombok.Data;

@Data
public class RestEntity {

  @JsonView(View.BaseView.class)
  private int code;

  @JsonView(View.BaseView.class)
  private String msg;

  @JsonView(View.BaseView.class)
  private Object data;

  public static RestEntity ok() {
    RestEntity entity = new RestEntity();
    entity.code = Status.SUCCESS.getStatus();
    entity.msg = Status.SUCCESS.getDesc();
    return entity;
  }

  public static RestEntity ok(Object data) {
    RestEntity entity = new RestEntity();
    entity.code = Status.SUCCESS.getStatus();
    entity.msg = Status.SUCCESS.getDesc();
    entity.data = data;
    return entity;
  }

  public static RestEntity error() {
    RestEntity entity = new RestEntity();
    entity.code = Status.FAILED.getStatus();
    entity.msg = Status.FAILED.getDesc();
    return entity;
  }

  public static RestEntity error(Status status) {
    RestEntity entity = new RestEntity();
    entity.code = status.getStatus();
    entity.msg = status.getDesc();
    return entity;
  }

  public static RestEntity error(Integer code, String msg) {
    RestEntity entity = new RestEntity();
    entity.code = code;
    entity.msg = msg;
    return entity;
  }

  public static RestEntity error(Integer code, String msg, Object data) {
    RestEntity entity = new RestEntity();
    entity.code = code;
    entity.msg = msg;
    entity.data = data;
    return entity;
  }
}
