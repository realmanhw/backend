package com.yuepaijie.controller;


import com.mysql.cj.util.StringUtils;
import com.yuepaijie.pojo.entity.generated.UserAccount;
import com.yuepaijie.pojo.enums.ResCode;
import com.yuepaijie.service.UserInfoService;
import com.yuepaijie.pojo.vo.RestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

  @Autowired
  UserInfoService userInfoService;

  @PostMapping("/signUp")
  public RestEntity signUp(@RequestBody UserAccount userAccount){
    if(StringUtils.isNullOrEmpty(userAccount.getNickname())){
      return new RestEntity(ResCode.BAD_REQUEST.getStatus(),"需要昵称");
    }
    if(StringUtils.isNullOrEmpty(userAccount.getAccount())){
      return new RestEntity(ResCode.BAD_REQUEST.getStatus(),"需要账户名称");
    }
    if(StringUtils.isNullOrEmpty(userAccount.getPassword())){
      return new RestEntity(ResCode.BAD_REQUEST.getStatus(),"需要密码");
    }
    Boolean flag = userInfoService.addUserAccount(userAccount);
    if(!flag){
      return new RestEntity(ResCode.BAD_REQUEST.getStatus(),"注册失败");
    }
    return new RestEntity(ResCode.SUCCESS.getStatus(), "注册成功");
  }
}
