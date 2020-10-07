package com.yuepaijie.dao;

import com.yuepaijie.entity.UserInfo;
import com.yuepaijie.entity.UserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userInfo
     *
     * @mbg.generated Wed Oct 07 21:04:50 CST 2020
     */
    long countByExample(UserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userInfo
     *
     * @mbg.generated Wed Oct 07 21:04:50 CST 2020
     */
    int deleteByExample(UserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userInfo
     *
     * @mbg.generated Wed Oct 07 21:04:50 CST 2020
     */
    int insert(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userInfo
     *
     * @mbg.generated Wed Oct 07 21:04:50 CST 2020
     */
    int insertSelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userInfo
     *
     * @mbg.generated Wed Oct 07 21:04:50 CST 2020
     */
    List<UserInfo> selectByExample(UserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userInfo
     *
     * @mbg.generated Wed Oct 07 21:04:50 CST 2020
     */
    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userInfo
     *
     * @mbg.generated Wed Oct 07 21:04:50 CST 2020
     */
    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);
}