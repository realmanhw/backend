package com.yuepaijie.model.entity.generated;

import java.util.Date;

public class Counter {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column counter.id
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column counter.number
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    private Long number;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column counter.type
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column counter.createtime
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column counter.updatetime
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public Counter(Long id, Long number, String type, Date createtime, Date updatetime) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public Counter() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column counter.id
     *
     * @return the value of counter.id
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column counter.id
     *
     * @param id the value for counter.id
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column counter.number
     *
     * @return the value of counter.number
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public Long getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column counter.number
     *
     * @param number the value for counter.number
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public void setNumber(Long number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column counter.type
     *
     * @return the value of counter.type
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column counter.type
     *
     * @param type the value for counter.type
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column counter.createtime
     *
     * @return the value of counter.createtime
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column counter.createtime
     *
     * @param createtime the value for counter.createtime
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column counter.updatetime
     *
     * @return the value of counter.updatetime
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column counter.updatetime
     *
     * @param updatetime the value for counter.updatetime
     *
     * @mbg.generated Thu Oct 29 01:50:04 CST 2020
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}