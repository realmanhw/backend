package com.yuepaijie.model.entity.generated;

import java.util.Date;

public class GalleryImage {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.id
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.title
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.url
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.md5
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private String md5;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.weight
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private Double weight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.height
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private Double height;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.width
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private Double width;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.description
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.thumbnail_url
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private String thumbnailUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.thumbnail_md5
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private String thumbnailMd5;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.thumbnail_weight
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private Double thumbnailWeight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.thumbnail_height
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private Double thumbnailHeight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.thumbnail_width
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private Double thumbnailWidth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.createtime
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gallery_image.updatetime
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_image
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public GalleryImage(Long id, String title, String url, String md5, Double weight, Double height, Double width, String description, String thumbnailUrl, String thumbnailMd5, Double thumbnailWeight, Double thumbnailHeight, Double thumbnailWidth, Date createtime, Date updatetime) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.md5 = md5;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailMd5 = thumbnailMd5;
        this.thumbnailWeight = thumbnailWeight;
        this.thumbnailHeight = thumbnailHeight;
        this.thumbnailWidth = thumbnailWidth;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gallery_image
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public GalleryImage() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.id
     *
     * @return the value of gallery_image.id
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.id
     *
     * @param id the value for gallery_image.id
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.title
     *
     * @return the value of gallery_image.title
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.title
     *
     * @param title the value for gallery_image.title
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.url
     *
     * @return the value of gallery_image.url
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.url
     *
     * @param url the value for gallery_image.url
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.md5
     *
     * @return the value of gallery_image.md5
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public String getMd5() {
        return md5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.md5
     *
     * @param md5 the value for gallery_image.md5
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.weight
     *
     * @return the value of gallery_image.weight
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.weight
     *
     * @param weight the value for gallery_image.weight
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.height
     *
     * @return the value of gallery_image.height
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public Double getHeight() {
        return height;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.height
     *
     * @param height the value for gallery_image.height
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.width
     *
     * @return the value of gallery_image.width
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public Double getWidth() {
        return width;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.width
     *
     * @param width the value for gallery_image.width
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.description
     *
     * @return the value of gallery_image.description
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.description
     *
     * @param description the value for gallery_image.description
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.thumbnail_url
     *
     * @return the value of gallery_image.thumbnail_url
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.thumbnail_url
     *
     * @param thumbnailUrl the value for gallery_image.thumbnail_url
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl == null ? null : thumbnailUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.thumbnail_md5
     *
     * @return the value of gallery_image.thumbnail_md5
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public String getThumbnailMd5() {
        return thumbnailMd5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.thumbnail_md5
     *
     * @param thumbnailMd5 the value for gallery_image.thumbnail_md5
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setThumbnailMd5(String thumbnailMd5) {
        this.thumbnailMd5 = thumbnailMd5 == null ? null : thumbnailMd5.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.thumbnail_weight
     *
     * @return the value of gallery_image.thumbnail_weight
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public Double getThumbnailWeight() {
        return thumbnailWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.thumbnail_weight
     *
     * @param thumbnailWeight the value for gallery_image.thumbnail_weight
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setThumbnailWeight(Double thumbnailWeight) {
        this.thumbnailWeight = thumbnailWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.thumbnail_height
     *
     * @return the value of gallery_image.thumbnail_height
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public Double getThumbnailHeight() {
        return thumbnailHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.thumbnail_height
     *
     * @param thumbnailHeight the value for gallery_image.thumbnail_height
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setThumbnailHeight(Double thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.thumbnail_width
     *
     * @return the value of gallery_image.thumbnail_width
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public Double getThumbnailWidth() {
        return thumbnailWidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.thumbnail_width
     *
     * @param thumbnailWidth the value for gallery_image.thumbnail_width
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setThumbnailWidth(Double thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.createtime
     *
     * @return the value of gallery_image.createtime
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.createtime
     *
     * @param createtime the value for gallery_image.createtime
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gallery_image.updatetime
     *
     * @return the value of gallery_image.updatetime
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gallery_image.updatetime
     *
     * @param updatetime the value for gallery_image.updatetime
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}