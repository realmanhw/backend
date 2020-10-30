package com.yuepaijie.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuepaijie.model.entity.generated.UserAuth;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class UserLoginAccountDetail {

  private Long userId;
  //登录账户Id
  private Long userAuthId;
  //账户名称
  private String identifier;
  //登录方式
  private String identityType;
  private boolean isAdmin;
  private String name;
  private String token;
  private String language;
  private String userType;
  private Date createTime;
  private Date updateTime;

  ///**
  // * 本次登录所使用的登录方式
  // */
  //private LoginType currentLoginType;

  public UserLoginAccountDetail(UserAuth userAuth, UserLoginAccountDetail detail) {
    BeanUtils.copyProperties(detail, this);
    this.setUserAuthId(userAuth.getId());
    this.setCreateTime(userAuth.getCreatetime());
  }
}
