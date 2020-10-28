package com.yuepaijie.model.dto;

import com.yuepaijie.model.entity.generated.UserAuth;
import com.yuepaijie.model.entity.generated.User;
import lombok.Data;

@Data
public class UserParam {
  User user;
  UserAuth userAuth;
}
