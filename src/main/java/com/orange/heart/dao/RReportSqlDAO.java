package com.orange.heart.dao;

import com.orange.heart.entity.RReportSql;
import com.orange.heart.entity.RReportSqlExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RReportSqlDAO {
    int countByExample(RReportSqlExample example);

    int deleteByExample(RReportSqlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RReportSql record);

    int insertSelective(RReportSql record);

    List<RReportSql> selectByExample(RReportSqlExample example);

    RReportSql selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RReportSql record, @Param("example") RReportSqlExample example);

    int updateByExample(@Param("record") RReportSql record, @Param("example") RReportSqlExample example);

    int updateByPrimaryKeySelective(RReportSql record);

    int updateByPrimaryKey(RReportSql record);
}