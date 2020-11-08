package com.yuepaijie.dao.interfaces;

import com.yuepaijie.model.entity.generated.GalleryAlbumImageRel;
import com.yuepaijie.model.entity.generated.GalleryAlbumImageRelExample;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryAlbumImageRelDao extends DaoHelper {

  Integer insertSelective(GalleryAlbumImageRel galleryAlbumImageRel);

  GalleryAlbumImageRel selectByAlbumIdAndImageId(Long albumId, Long ImageId);

  List<Long> getImageListByAlbumId(Long albumId);

  Integer deleteByExample(GalleryAlbumImageRelExample example);
}
