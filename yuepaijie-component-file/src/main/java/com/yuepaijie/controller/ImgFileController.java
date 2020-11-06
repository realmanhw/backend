package com.yuepaijie.controller;

import com.yuepaijie.model.vo.RestEntity;
import com.yuepaijie.model.vo.UserLoginAccountDetail;
import com.yuepaijie.security.AuthUtils;
import com.yuepaijie.service.ImgFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件-Controller")
@RestController
@RequestMapping("/imgFile")
public class ImgFileController {

    @Autowired
    ImgFileService imgFileService;

    //@Autowired AliyunOssFileClient aliyunOssFileClient;

    /**
     * 接收上传的文件，并且将上传的文件存储在指定路径下
     * @param file
     * @param request
     * @return
     */
    @ApiOperation(value = "接收上传的文件，并且将上传的文件存储在指定路径下")
    @PostMapping(value = "/upload")
    public RestEntity upload(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request) {
        //try{
        //    aliyunOssFileClient.uploadPublic(file,"pub","1.png");
        //    byte[] b = aliyunOssFileClient.downloadPublic(
        //        "pub/4090accb-1be9-4413-8774-64f7d3d5b190.png");
        //    b = null;
        //}catch (Exception e){
        //    System.out.println(e);
        //}
        UserLoginAccountDetail userLoginAccountDetail = AuthUtils.getUser();
        Long id = userLoginAccountDetail.getUserAuthId();
        System.out.println(id);
        return imgFileService.saveImgFile(file, request);
    }

    //@PostMapping(value = "/uploadImg")
    //public RestEntity uploadImg(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request){
    //    AuthUtils
    //}
}
