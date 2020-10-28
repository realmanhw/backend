package com.yuepaijie.dao.generated;

import com.yuepaijie.model.entity.generated.UserAccount;
import com.yuepaijie.model.entity.generated.UserAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbg.generated Sat Oct 24 21:31:05 CST 2020
     */
    long countByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbg.generated Sat Oct 24 21:31:05 CST 2020
     */
    int deleteByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbg.generated Sat Oct 24 21:31:05 CST 2020
     */
    int insert(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbg.generated Sat Oct 24 21:31:05 CST 2020
     */
    int insertSelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbg.generated Sat Oct 24 21:31:05 CST 2020
     */
    List<UserAccount> selectByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbg.generated Sat Oct 24 21:31:05 CST 2020
     */
    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbg.generated Sat Oct 24 21:31:05 CST 2020
     */
    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);
}