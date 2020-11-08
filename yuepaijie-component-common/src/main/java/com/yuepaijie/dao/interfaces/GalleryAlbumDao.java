package com.yuepaijie.dao.interfaces;

import com.yuepaijie.model.dto.AlbumParam;
import com.yuepaijie.model.entity.generated.GalleryAlbum;
import com.yuepaijie.model.entity.generated.GalleryAlbumExample;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryAlbumDao extends DaoHelper {

  GalleryAlbum selectByUserIdAndTitle(Long uerId, String title);

  Integer insertSelective(GalleryAlbum galleryAlbum, AlbumParam albumParam, Long userId);

  GalleryAlbum selectByAlbumIdAndUserId(Long albumId, Long userId);

  Integer updateBySelective(GalleryAlbum galleryAlbum);

  GalleryAlbum selectByExample(GalleryAlbumExample example);

  GalleryAlbum selectByPrimaryKey(Long Id);

  Integer deleteById(Long Id);
}
