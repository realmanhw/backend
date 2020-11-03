package com.yuepaijie.dao.generated;

import com.yuepaijie.model.entity.generated.GalleryUserAlbumRel;
import com.yuepaijie.model.entity.generated.GalleryUserAlbumRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GalleryUserAlbumRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    long countByExample(GalleryUserAlbumRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int deleteByExample(GalleryUserAlbumRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int insert(GalleryUserAlbumRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int insertSelective(GalleryUserAlbumRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    List<GalleryUserAlbumRel> selectByExample(GalleryUserAlbumRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    GalleryUserAlbumRel selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int updateByExampleSelective(@Param("record") GalleryUserAlbumRel record, @Param("example") GalleryUserAlbumRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int updateByExample(@Param("record") GalleryUserAlbumRel record, @Param("example") GalleryUserAlbumRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int updateByPrimaryKeySelective(GalleryUserAlbumRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_user_album_rel
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int updateByPrimaryKey(GalleryUserAlbumRel record);
}