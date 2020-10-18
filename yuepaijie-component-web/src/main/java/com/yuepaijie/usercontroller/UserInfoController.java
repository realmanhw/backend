package com.yuepaijie.usercontroller;


import com.yuepaijie.UserInfoService;
import com.yuepaijie.entity.generated.UserAccount;
import com.yuepaijie.obj.vo.RestEntity;
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

  @PostMapping("/addoredit")
  public RestEntity addOrEditUser(@RequestBody UserAccount userAccount){
    userInfoService.addOrModify(userAccount);
    RestEntity restEntity = new RestEntity();
    restEntity.setCode(200);
    return restEntity;
  }
}
