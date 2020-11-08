package com.yuepaijie.dao.impls;

import com.yuepaijie.dao.mapper.generated.GalleryImageMapper;
import com.yuepaijie.dao.interfaces.GalleryImageDao;
import com.yuepaijie.kit.TimeUtils;
import com.yuepaijie.model.entity.generated.GalleryAlbum;
import com.yuepaijie.model.entity.generated.GalleryImage;
import com.yuepaijie.model.entity.generated.GalleryImageExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class GalleryImageDaoImpl implements GalleryImageDao {

  @Resource GalleryImageMapper galleryImageMapper;

  @Override
  public Integer insertSelective(GalleryImage galleryImage){
    galleryImage.setId(null);
    galleryImage.setCreatetime(TimeUtils.getNow());
    galleryImage.setUpdatetime(TimeUtils.getNow());
    return galleryImageMapper.insertSelective(galleryImage);
  }

  @Override
  public List<GalleryImage> selectByExample(GalleryImageExample example){
    return galleryImageMapper.selectByExample(example);
  }

  @Override
  public Integer deleteByExample(GalleryImageExample example){
    return galleryImageMapper.deleteByExample(example);
  }
}
