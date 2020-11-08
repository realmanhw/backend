package com.yuepaijie.dao.impls;

import com.yuepaijie.dao.mapper.GalleryAlbumImageRelMapperManual;
import com.yuepaijie.dao.mapper.generated.GalleryAlbumImageRelMapper;
import com.yuepaijie.dao.interfaces.GalleryAlbumImageRelDao;
import com.yuepaijie.kit.TimeUtils;
import com.yuepaijie.model.entity.generated.GalleryAlbumImageRel;
import com.yuepaijie.model.entity.generated.GalleryAlbumImageRelExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class GalleryAlbumImageRelDaoImpl implements GalleryAlbumImageRelDao {

  @Resource GalleryAlbumImageRelMapper galleryAlbumImageRelMapper;

  @Resource GalleryAlbumImageRelMapperManual galleryAlbumImageRelMapperManual;

  @Override
  public Integer insertSelective(GalleryAlbumImageRel galleryAlbumImageRel) {
    galleryAlbumImageRel.setId(null);
    galleryAlbumImageRel.setCteatetime(TimeUtils.getNow());
    galleryAlbumImageRel.setUpdatetime(TimeUtils.getNow());
    return galleryAlbumImageRelMapper.insertSelective(galleryAlbumImageRel);
  }

  @Override
  public GalleryAlbumImageRel selectByAlbumIdAndImageId(Long albumId, Long ImageId){
    GalleryAlbumImageRelExample example = new GalleryAlbumImageRelExample();
    example.createCriteria().andAlbumIdEqualTo(albumId).andImageIdEqualTo(ImageId);
    return firstOrNull(galleryAlbumImageRelMapper.selectByExample(example));
  }

  @Override
  public List<Long> getImageListByAlbumId(Long albumId){
    return galleryAlbumImageRelMapperManual.getImageListByAlbumId(albumId);
  }

  @Override
  public Integer deleteByExample(GalleryAlbumImageRelExample example){
    return galleryAlbumImageRelMapper.deleteByExample(example);
  }
}
