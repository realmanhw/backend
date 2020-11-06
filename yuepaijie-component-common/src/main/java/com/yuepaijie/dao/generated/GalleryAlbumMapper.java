package com.yuepaijie.dao.generated;

import com.yuepaijie.model.entity.generated.GalleryAlbum;
import com.yuepaijie.model.entity.generated.GalleryAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GalleryAlbumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    long countByExample(GalleryAlbumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    int deleteByExample(GalleryAlbumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    int insert(GalleryAlbum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    int insertSelective(GalleryAlbum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    List<GalleryAlbum> selectByExample(GalleryAlbumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    GalleryAlbum selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    int updateByExampleSelective(@Param("record") GalleryAlbum record, @Param("example") GalleryAlbumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    int updateByExample(@Param("record") GalleryAlbum record, @Param("example") GalleryAlbumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    int updateByPrimaryKeySelective(GalleryAlbum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_album
     *
     * @mbg.generated Fri Nov 06 15:57:10 CST 2020
     */
    int updateByPrimaryKey(GalleryAlbum record);
}