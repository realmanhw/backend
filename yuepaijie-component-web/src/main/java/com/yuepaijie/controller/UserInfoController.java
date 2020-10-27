package com.yuepaijie.controller;

import com.mysql.cj.util.StringUtils;
import com.yuepaijie.pojo.entity.generated.UserAccount;
import com.yuepaijie.constants.enums.ResCode;
import com.yuepaijie.service.UserInfoService;
import com.yuepaijie.pojo.vo.RestEntity;
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

  @ApiOperation(value = "用户注册", notes = "必须传昵称、账户名称、密码")
  @PostMapping("/signUp")
  public RestEntity signUp(
      @ApiParam(name = "userAccount", value = "注册实体：必须传昵称、账户名称、密码", required = true)
      @RequestBody UserAccount userAccount) {
    if (StringUtils.isNullOrEmpty(userAccount.getNickname())) {
      return new RestEntity(ResCode.BAD_REQUEST.getStatus(), "需要昵称");
    }
    if (StringUtils.isNullOrEmpty(userAccount.getAccount())) {
      return new RestEntity(ResCode.BAD_REQUEST.getStatus(), "需要账户名称");
    }
    if (StringUtils.isNullOrEmpty(userAccount.getPassword())) {
      return new RestEntity(ResCode.BAD_REQUEST.getStatus(), "需要密码");
    }
    Boolean flag = userInfoService.addUserAccount(userAccount);
    if (!flag) {
      return new RestEntity(ResCode.BAD_REQUEST.getStatus(), "注册失败");
    }
    return new RestEntity(ResCode.SUCCESS.getStatus(), "注册成功");
  }
}
