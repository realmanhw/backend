package com.yuepaijie.model.vo;

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
  /**
   * 基本数据类型，默认为false非管理员，这里目前不用也不可以改
   */
  private boolean isAdmin;

  private Date createTime;

  public UserLoginAccountDetail(UserAuth userAuth, UserLoginAccountDetail detail) {
    BeanUtils.copyProperties(detail, this);
    this.setUserAuthId(userAuth.getId());
    this.setCreateTime(userAuth.getCreatetime());
  }
}
