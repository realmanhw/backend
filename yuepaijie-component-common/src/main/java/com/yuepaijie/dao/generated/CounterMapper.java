package com.yuepaijie.dao.generated;

import com.yuepaijie.model.entity.generated.Counter;
import com.yuepaijie.model.entity.generated.CounterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CounterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    long countByExample(CounterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int deleteByExample(CounterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int insert(Counter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int insertSelective(Counter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    List<Counter> selectByExample(CounterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    Counter selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int updateByExampleSelective(@Param("record") Counter record, @Param("example") CounterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int updateByExample(@Param("record") Counter record, @Param("example") CounterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int updateByPrimaryKeySelective(Counter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbg.generated Tue Nov 03 11:24:45 CST 2020
     */
    int updateByPrimaryKey(Counter record);
}