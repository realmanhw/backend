package com.yuepaijie.dao.impls;

import com.yuepaijie.dao.mapper.generated.GalleryAlbumMapper;
import com.yuepaijie.dao.interfaces.GalleryAlbumDao;
import com.yuepaijie.kit.TimeUtils;
import com.yuepaijie.model.dto.AlbumParam;
import com.yuepaijie.model.entity.generated.GalleryAlbum;
import com.yuepaijie.model.entity.generated.GalleryAlbumExample;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class GalleryAlbumDaoImpl implements GalleryAlbumDao {

  @Resource GalleryAlbumMapper galleryAlbumMapper;

  @Override
  public Integer insertSelective(GalleryAlbum galleryAlbum,AlbumParam albumParam,Long uesrId){
    galleryAlbum.setUserId(uesrId);
    galleryAlbum.setTitle(albumParam.getTitle().trim());
    galleryAlbum.setDescription(albumParam.getDescription());
    galleryAlbum.setIsPrivate(albumParam.getIsPrivate());
    galleryAlbum.setIsEncryption(albumParam.getIsEncryption());
    galleryAlbum.setPassword(albumParam.getPassword());
    galleryAlbum.setCreatetime(TimeUtils.getNow());
    galleryAlbum.setUpdatetime(TimeUtils.getNow());
    return galleryAlbumMapper.insertSelective(galleryAlbum);
  }

  @Override
  public GalleryAlbum selectByUserIdAndTitle(Long uerId,String title){
    GalleryAlbumExample example = new GalleryAlbumExample();
    example.createCriteria().andUserIdEqualTo(uerId).andTitleEqualTo(title.trim());
    return firstOrNull(galleryAlbumMapper.selectByExample(example));
  }

  @Override
  public GalleryAlbum selectByAlbumIdAndUserId(Long albumId, Long userId){
    GalleryAlbumExample example = new GalleryAlbumExample();
    example.createCriteria().andIdEqualTo(albumId).andUserIdEqualTo(userId);
    return firstOrNull(galleryAlbumMapper.selectByExample(example));
  }

  @Override
  public Integer updateBySelective(GalleryAlbum galleryAlbum){
    galleryAlbum.setUpdatetime(TimeUtils.getNow());
    return galleryAlbumMapper.updateByPrimaryKeySelective(galleryAlbum);
  }

  @Override
  public GalleryAlbum selectByExample(GalleryAlbumExample example){
    return firstOrNull(galleryAlbumMapper.selectByExample(example));
  }

  @Override
  public GalleryAlbum selectByPrimaryKey(Long Id){
    return galleryAlbumMapper.selectByPrimaryKey(Id);
  }

  @Override
  public Integer deleteById(Long Id){
    return galleryAlbumMapper.deleteByPrimaryKey(Id);
  }


}
