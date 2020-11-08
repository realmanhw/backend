package com.yuepaijie.dao.interfaces;

import com.yuepaijie.model.entity.generated.GalleryImage;
import com.yuepaijie.model.entity.generated.GalleryImageExample;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryImageDao extends DaoHelper {

  Integer insertSelective(GalleryImage galleryImage);

  List<GalleryImage> selectByExample(GalleryImageExample example);

  Integer deleteByExample(GalleryImageExample example);
}
