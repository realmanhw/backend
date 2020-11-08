package com.yuepaijie.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GalleryAlbumImageRelMapperManual {

  List<Long> getImageListByAlbumId(@Param("albumId") Long albumId);
}
