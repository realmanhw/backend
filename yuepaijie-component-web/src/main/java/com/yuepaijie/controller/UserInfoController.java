package com.yuepaijie.controller;

import com.mysql.cj.util.StringUtils;
import com.yuepaijie.kit.StringKit;
import com.yuepaijie.model.dto.UserParam;
import com.yuepaijie.model.entity.generated.User;
import com.yuepaijie.constants.enums.Status;
import com.yuepaijie.model.entity.generated.UserAuth;
import com.yuepaijie.service.UserInfoService;
import com.yuepaijie.model.vo.RestEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户信息-Controller")
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

  @Autowired
  UserInfoService userInfoService;

  @ApiOperation(value = "用户注册", notes = "必须传-账户名称、密码、登录方式")
  @PostMapping("/signUp")
  public RestEntity signUp(
      @ApiParam(name = "userParam", value = "必须传账户名称、密码、登录方式", required = true)
      @RequestBody UserParam userParam) {
    User user = userParam.getUser();
    UserAuth userAuth = userParam.getUserAuth();
    if(user == null || userAuth == null){
      return RestEntity.error(Status.PARAMETER_ERROR);
    }
    if (StringUtils.isNullOrEmpty(userAuth.getIdentifier())) {
      return RestEntity.error(Status.PARAMETER_ERROR.getStatus(), "需要账户名称");
    }
    if (StringUtils.isNullOrEmpty(userAuth.getCredential())) {
      return RestEntity.error(Status.PARAMETER_ERROR.getStatus(), "需要密码");
    }
    if (StringUtils.isNullOrEmpty(userAuth.getIdentityType())) {
      return RestEntity.error(Status.PARAMETER_ERROR.getStatus(), "需要登录方式");
    }
    if (!StringUtils.isNullOrEmpty(user.getNickname())) {
      if(StringKit.containsSpecialChar(user.getNickname().trim())){
        return RestEntity.error(Status.PARAMETER_ERROR.getStatus(), "昵称不能包含特殊字符");
      }
    }
    Boolean flag = userInfoService.signUp(user,userAuth);
    if (!flag) {
      return RestEntity.error(Status.FAILED.getStatus(), "注册失败");
    }
    return RestEntity.ok(Status.SUCCESS.getStatus());
  }
}
