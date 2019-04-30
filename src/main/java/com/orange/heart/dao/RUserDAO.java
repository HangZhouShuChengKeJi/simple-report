package com.orange.heart.dao;

import com.orange.heart.entity.RUser;
import com.orange.heart.entity.RUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RUserDAO {
    int countByExample(RUserExample example);

    int deleteByExample(RUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RUser record);

    int insertSelective(RUser record);

    List<RUser> selectByExample(RUserExample example);

    RUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RUser record, @Param("example") RUserExample example);

    int updateByExample(@Param("record") RUser record, @Param("example") RUserExample example);

    int updateByPrimaryKeySelective(RUser record);

    int updateByPrimaryKey(RUser record);
}