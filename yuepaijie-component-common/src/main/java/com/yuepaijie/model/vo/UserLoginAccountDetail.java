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

  private Long id;
  private String identifier;
  private String identityType;
  private String name;
  private String token;
  private boolean isAdmin;
  private String language;
  private Long userId;
  private String userType;
  private Date createTime;
  private Date updateTime;
  @JsonIgnore
  private String ipWhiteList;
  private Integer loginType;

  ///**
  // * 本次登录所使用的登录方式
  // */
  //private LoginType currentLoginType;

  public UserLoginAccountDetail(UserAuth userAuth, UserLoginAccountDetail detail) {
    BeanUtils.copyProperties(detail, this);
    this.setId(userAuth.getId());
    this.setCreateTime(userAuth.getCreatetime());
  }
}
