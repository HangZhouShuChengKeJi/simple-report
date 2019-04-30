package com.orange.heart.dao;

import com.orange.heart.entity.RReportChart;
import com.orange.heart.entity.RReportChartExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RReportChartDAO {
    int countByExample(RReportChartExample example);

    int deleteByExample(RReportChartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RReportChart record);

    int insertSelective(RReportChart record);

    List<RReportChart> selectByExample(RReportChartExample example);

    RReportChart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RReportChart record, @Param("example") RReportChartExample example);

    int updateByExample(@Param("record") RReportChart record, @Param("example") RReportChartExample example);

    int updateByPrimaryKeySelective(RReportChart record);

    int updateByPrimaryKey(RReportChart record);
}