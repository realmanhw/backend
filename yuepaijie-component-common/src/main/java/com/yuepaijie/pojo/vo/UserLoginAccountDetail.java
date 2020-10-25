package com.yuepaijie.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuepaijie.pojo.entity.generated.UserAccount;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class UserLoginAccountDetail {

  private Long id;
  private String account;
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

  public UserLoginAccountDetail(UserAccount account, UserLoginAccountDetail detail) {
    BeanUtils.copyProperties(detail, this);

    this.setId(account.getId());
    this.setCreateTime(account.getCreatetime());
  }
}
