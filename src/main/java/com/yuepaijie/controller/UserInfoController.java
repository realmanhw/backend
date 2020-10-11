package com.yuepaijie.controller;


import com.yuepaijie.common.entity.generated.UserAccount;
import com.yuepaijie.common.vo.RestEntity;
import com.yuepaijie.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

  @Autowired UserInfoService userInfoService;

  @PostMapping("/addoredit")
  public RestEntity addOrEditUser(@RequestBody UserAccount userAccount){
    userInfoService.addOrModify(userAccount);
    RestEntity restEntity = new RestEntity();
    restEntity.setCode(200);
    return restEntity;
  }
}
