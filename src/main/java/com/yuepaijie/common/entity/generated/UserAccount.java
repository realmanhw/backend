package com.yuepaijie.common.entity.generated;

import java.util.Date;

public class UserAccount {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.id
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.account
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private String account;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.password
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.name
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.gender
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private Byte gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.phone
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.province
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private String province;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.city
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.birthday
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private Date birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.createtime
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userAccount.updatetime
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public UserAccount(Long id, String account, String password, String name, Byte gender, String phone, String province, String city, Date birthday, Date createtime, Date updatetime) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.birthday = birthday;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public UserAccount() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.id
     *
     * @return the value of userAccount.id
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.id
     *
     * @param id the value for userAccount.id
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.account
     *
     * @return the value of userAccount.account
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.account
     *
     * @param account the value for userAccount.account
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.password
     *
     * @return the value of userAccount.password
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.password
     *
     * @param password the value for userAccount.password
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.name
     *
     * @return the value of userAccount.name
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.name
     *
     * @param name the value for userAccount.name
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.gender
     *
     * @return the value of userAccount.gender
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.gender
     *
     * @param gender the value for userAccount.gender
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.phone
     *
     * @return the value of userAccount.phone
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.phone
     *
     * @param phone the value for userAccount.phone
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.province
     *
     * @return the value of userAccount.province
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.province
     *
     * @param province the value for userAccount.province
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.city
     *
     * @return the value of userAccount.city
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.city
     *
     * @param city the value for userAccount.city
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.birthday
     *
     * @return the value of userAccount.birthday
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.birthday
     *
     * @param birthday the value for userAccount.birthday
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.createtime
     *
     * @return the value of userAccount.createtime
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.createtime
     *
     * @param createtime the value for userAccount.createtime
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userAccount.updatetime
     *
     * @return the value of userAccount.updatetime
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userAccount.updatetime
     *
     * @param updatetime the value for userAccount.updatetime
     *
     * @mbg.generated Sun Oct 11 23:24:25 CST 2020
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}