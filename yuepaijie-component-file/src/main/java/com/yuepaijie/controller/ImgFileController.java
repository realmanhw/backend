package com.yuepaijie.controller;

import com.yuepaijie.constants.constvals.ConstCollections;
import com.yuepaijie.constants.constvals.ConstVals;
import com.yuepaijie.constants.enums.Status;
import com.yuepaijie.dao.impls.GalleryAlbumDaoImpl;
import com.yuepaijie.kit.StringKit;
import com.yuepaijie.kit.file.FileUtil;
import com.yuepaijie.model.dto.AlbumParam;
import com.yuepaijie.model.entity.generated.GalleryAlbum;
import com.yuepaijie.model.vo.RestEntity;
import com.yuepaijie.security.AuthUtils;
import com.yuepaijie.service.ImgFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件-Controller")
@RestController
@RequestMapping("/imgFile")
public class ImgFileController {

  @Autowired ImgFileService imgFileService;

  @Autowired GalleryAlbumDaoImpl galleryAlbumDao;

  @ApiOperation(value = "创建相册")
  @PostMapping("/createAlbum")
  public RestEntity createAlbum(@RequestBody AlbumParam albumParam) {
    if (!AuthUtils.isLogin()) {
      return RestEntity.error(Status.NEED_LOGIN);
    }
    if (albumParam == null) {
      return RestEntity.error(Status.PARAMETER_ERROR);
    }
    if (StringKit.containsSpecialChar(albumParam.getTitle().trim())) {
      return RestEntity.error(Status.PARAMETER_ERROR.getStatus(), "相册不能包含特殊字符");
    }
    if (albumParam.getIsEncryption() == null) {
      return RestEntity.error(Status.PARAMETER_ERROR);
    }
    if (albumParam.getIsEncryption() && albumParam.getPassword() == null) {
      return RestEntity.error(Status.PARAMETER_ERROR);
    }
    Boolean flag = imgFileService.createAlbum(albumParam);
    if (flag) {
      RestEntity.error();
    }
    return RestEntity.ok();
  }

  @ApiOperation(value = "删除相册--todo")
  @PostMapping("/deleteAlbum/{albumId}")
  public RestEntity deleteAlbum(@PathVariable(name = "albumId") Long albumId) {
    if (!AuthUtils.isLogin()) {
      return RestEntity.error(Status.NEED_LOGIN);
    }
    if (albumId == null) {
      return RestEntity.error(Status.PARAMETER_ERROR);
    }
    if(galleryAlbumDao.selectByAlbumIdAndUserId(albumId,AuthUtils.getUserId())==null){
      return RestEntity.error(Status.FAILED.getStatus(),"该用户没有此相册");
    }
    if(imgFileService.deleteAlbum(albumId)){
      return RestEntity.ok();
    }
    return RestEntity.error();
  }

  @ApiOperation(value = "上传照片到相册")
  @PostMapping(value = "/uploadPhoto/{albumId}")
  public RestEntity uploadImg(@RequestParam(value = "file", required = false) MultipartFile file,
      @PathVariable(name = "albumId") Long albumId) {
    if (!AuthUtils.isLogin()) {
      return RestEntity.error(Status.NEED_LOGIN);
    }
    if(albumId == null){
      return RestEntity.error(Status.PARAMETER_ERROR);
    }
    if(file == null){
      return RestEntity.error(Status.PARAMETER_ERROR);
    }
    if(file.getOriginalFilename() == null){
      return RestEntity.error(Status.FAILED.getStatus(),"文件名不存在");
    }
    String title = file.getOriginalFilename();
    if(title.contains("/")){
      return RestEntity.error(Status.FAILED.getStatus(),"文件名不能包含'/'符号");
    }
    if (!ConstCollections.imgExtentions.contains(FileUtil.getFileExtension(title))) {
      StringBuffer res = new StringBuffer("只支持格式为:");
      for (String s : ConstCollections.imgExtentions) {
        res.append(" " + s);
      }
      return RestEntity.error(Status.FAILED.getStatus(), res.toString());
    }
    if (file.getSize() > ConstVals.SIZE_5_M) {
      return RestEntity.error(Status.FAILED.getStatus(), "图片大小不能超过5M");
    }
    GalleryAlbum album = galleryAlbumDao.selectByAlbumIdAndUserId(albumId,AuthUtils.getUserId());
    if(album == null){
      return RestEntity.error(Status.FAILED.getStatus(),"该用户不拥有此相册");
    }
    InputStream inputStream = null;
    BufferedImage image = null;
    try{
      inputStream = file.getInputStream();
      image = ImageIO.read(inputStream);
      if (image == null) {
        return RestEntity.error(Status.FAILED.getStatus(),"非图片格式");
      }
    }catch (Exception e){
      return RestEntity.error(Status.FAILED);
    }
    if(!imgFileService.uploadImg(file,inputStream,image,album)){
      return RestEntity.error();
    }
    return RestEntity.ok();
  }
}
