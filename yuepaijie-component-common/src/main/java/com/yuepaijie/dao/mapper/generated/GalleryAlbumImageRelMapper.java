package com.yuepaijie.dao.mapper.generated;

import com.yuepaijie.model.entity.generated.GalleryAlbumImageRel;
import com.yuepaijie.model.entity.generated.GalleryAlbumImageRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GalleryAlbumImageRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    long countByExample(GalleryAlbumImageRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    int deleteByExample(GalleryAlbumImageRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    int insert(GalleryAlbumImageRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    int insertSelective(GalleryAlbumImageRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    List<GalleryAlbumImageRel> selectByExample(GalleryAlbumImageRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    GalleryAlbumImageRel selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    int updateByExampleSelective(@Param("record") GalleryAlbumImageRel record, @Param("example") GalleryAlbumImageRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    int updateByExample(@Param("record") GalleryAlbumImageRel record, @Param("example") GalleryAlbumImageRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    int updateByPrimaryKeySelective(GalleryAlbumImageRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album_image_rel
     *
     * @mbg.generated Mon Nov 09 22:17:12 CST 2020
     */
    int updateByPrimaryKey(GalleryAlbumImageRel record);
}