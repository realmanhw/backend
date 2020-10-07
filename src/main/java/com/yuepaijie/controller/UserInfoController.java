package com.yuepaijie.controller;

import com.yuepaijie.common.vo.RestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/userInfo")
public class UserInfoController {
  public RestEntity addOrEditUser(){

    RestEntity restEntity = new RestEntity();
    return null;
  }
}
