package com.yuepaijie.service;

import com.google.common.collect.Lists;
import com.yuepaijie.constants.constvals.OssKeys;
import com.yuepaijie.dao.impls.GalleryAlbumDaoImpl;
import com.yuepaijie.dao.impls.GalleryAlbumImageRelDaoImpl;
import com.yuepaijie.dao.impls.GalleryImageDaoImpl;
import com.yuepaijie.kit.TimeUtils;
import com.yuepaijie.kit.file.FileUploadResp;
import com.yuepaijie.kit.file.FileUtil;
import com.yuepaijie.kit.file.aliyunoss.AliyunOssFileClient;
import com.yuepaijie.kit.thirdparty.encrypt.EncryptAndDecryptUtils;
import com.yuepaijie.model.dto.AlbumParam;
import com.yuepaijie.model.entity.generated.GalleryAlbum;
import com.yuepaijie.model.entity.generated.GalleryAlbumExample;
import com.yuepaijie.model.entity.generated.GalleryAlbumImageRel;
import com.yuepaijie.model.entity.generated.GalleryAlbumImageRelExample;
import com.yuepaijie.model.entity.generated.GalleryImage;
import com.yuepaijie.model.entity.generated.GalleryImageExample;
import com.yuepaijie.security.AuthUtils;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImgFileService {

  @Autowired GalleryAlbumDaoImpl galleryAlbumDao;

  @Autowired AliyunOssFileClient aliyunOssFileClient;

  @Autowired GalleryImageDaoImpl galleryImageDao;

  @Autowired GalleryAlbumImageRelDaoImpl galleryAlbumImageRelDao;

  @Transactional
  public Boolean createAlbum(AlbumParam albumParam) {
    Long userId = AuthUtils.getUserId();
    GalleryAlbum galleryAlbum = galleryAlbumDao.selectByUserIdAndTitle(userId, albumParam.getTitle().trim());
    if (galleryAlbum != null) {
      return true;
    }
    galleryAlbum = new GalleryAlbum();
    Integer res = galleryAlbumDao.insertSelective(galleryAlbum, albumParam, AuthUtils.getUserId());
    if(res.equals(0) ){
      return false;
    }
    Long albumId = galleryAlbum.getId();
    String uri = OssKeys.PHOT0
        + "/"
        + EncryptAndDecryptUtils.md5Encrypt(String.valueOf(AuthUtils.getUserId()))
        + "/"
        + EncryptAndDecryptUtils.md5Encrypt(String.valueOf(albumId))
        + "/";
    galleryAlbum = new GalleryAlbum();
    galleryAlbum.setId(albumId);
    galleryAlbum.setUri(uri);
    res = galleryAlbumDao.updateBySelective(galleryAlbum);
    return res != 0;
  }

  @Transactional
  public Boolean uploadImg(MultipartFile file, InputStream inputStream,BufferedImage image,GalleryAlbum album) {
    try {
      String title = file.getOriginalFilename();
      String targetLocation = album.getUri();
      if (image == null) {
        return false;
      }
      String md5 = FileUtil.calcMD5(inputStream);
      Long size = file.getSize();
      Integer width = image.getWidth();
      Integer height = image.getHeight();
      GalleryImage galleryImage = new GalleryImage();
      FileUploadResp resp = aliyunOssFileClient.uploadPublic(file,targetLocation,title);
      String uri = resp.getUri();
      String url = resp.getUrl();
      createGalleryImage(galleryImage, title, uri, url, md5, size, width, height);
      Integer res = galleryImageDao.insertSelective(galleryImage);
      if(res.equals(0)){
        return false;
      }
      Long ImageId = galleryImage.getId();
      GalleryAlbumImageRel galleryAlbumImageRel = galleryAlbumImageRelDao.selectByAlbumIdAndImageId(album.getId(),ImageId);
      if(galleryAlbumImageRel!=null){
        return false;
      }
      galleryAlbumImageRel = new GalleryAlbumImageRel();
      galleryAlbumImageRel.setAlbumId(album.getId());
      galleryAlbumImageRel.setImageId(ImageId);
      res = galleryAlbumImageRelDao.insertSelective(galleryAlbumImageRel);
      if( res.equals(0)){
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  private void createGalleryImage(GalleryImage galleryImage,
      String title, String uri, String url, String md5, Long size,
      Integer width, Integer height) {
    galleryImage.setTitle(title);
    galleryImage.setUri(uri);
    galleryImage.setUrl(url);
    galleryImage.setMd5(md5);
    galleryImage.setSize(size);
    galleryImage.setWidth(width);
    galleryImage.setHeight(height);
    galleryImage.setCreatetime(TimeUtils.getNow());
    galleryImage.setUpdatetime(TimeUtils.getNow());
  }

  @Transactional
  public Boolean deleteAlbum(Long albumId){
    List<Long> imageIdList = galleryAlbumImageRelDao.getImageListByAlbumId(albumId);
    if(CollectionUtils.isEmpty(imageIdList)){
      //TODO 这里要改一下，即使相册里面没有任何照片，也要把相册删掉哈~~
      //删除oss中的相册目录
      GalleryAlbum galleryAlbum = galleryAlbumDao.selectByPrimaryKey(albumId);
      if(galleryAlbum==null){
        return true;
      }
      String albumUri = galleryAlbum.getUri();
      aliyunOssFileClient.deletePublic(albumUri);
      //删除galleryAlbum表中的记录
      galleryAlbumDao.deleteById(albumId);
      return true;
    }
    GalleryImageExample galleryImageExample = new GalleryImageExample();
    galleryImageExample.createCriteria().andIdIn(imageIdList);
    //查询相册中的照片
    List<GalleryImage> images = galleryImageDao.selectByExample(galleryImageExample);
    if(images == null){
      return false;
    }
    List<String> uriList = images.stream().map(GalleryImage::getUri).collect(Collectors.toList());
    List<List<String>> uriBatchList = Lists.partition(uriList, 100);
    //分批次删除oss中照片
    uriBatchList.forEach(batch -> aliyunOssFileClient.deleteBatchPublic(batch));
    //删除galleryImage表中的照片记录
    galleryImageDao.deleteByExample(galleryImageExample);
    //删除galleryAlbumImageRel关联表中的记录
    GalleryAlbumImageRelExample example = new GalleryAlbumImageRelExample();
    example.createCriteria().andAlbumIdEqualTo(albumId);
    galleryAlbumImageRelDao.deleteByExample(example);
    //删除oss中的相册目录
    GalleryAlbum galleryAlbum = galleryAlbumDao.selectByPrimaryKey(albumId);
    String albumUri = galleryAlbum.getUri();
    aliyunOssFileClient.deletePublic(albumUri);
    //删除galleryAlbum表中的记录
    galleryAlbumDao.deleteById(albumId);
    return true;
  }
}
