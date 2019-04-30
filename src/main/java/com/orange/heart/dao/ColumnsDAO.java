package com.orange.heart.dao;

import com.orange.heart.entity.Columns;
import com.orange.heart.entity.ColumnsExample;
import com.orange.heart.entity.ColumnsWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColumnsDAO {
    int countByExample(ColumnsExample example);

    int deleteByExample(ColumnsExample example);

    int insert(ColumnsWithBLOBs record);

    int insertSelective(ColumnsWithBLOBs record);

    List<ColumnsWithBLOBs> selectByExampleWithBLOBs(ColumnsExample example);

    List<Columns> selectByExample(ColumnsExample example);

    int updateByExampleSelective(@Param("record") ColumnsWithBLOBs record, @Param("example") ColumnsExample example);

    int updateByExampleWithBLOBs(@Param("record") ColumnsWithBLOBs record, @Param("example") ColumnsExample example);

    int updateByExample(@Param("record") Columns record, @Param("example") ColumnsExample example);
}