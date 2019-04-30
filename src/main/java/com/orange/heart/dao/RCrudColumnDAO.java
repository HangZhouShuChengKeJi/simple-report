package com.orange.heart.dao;

import com.orange.heart.entity.RCrudColumn;
import com.orange.heart.entity.RCrudColumnExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RCrudColumnDAO {
    int countByExample(RCrudColumnExample example);

    int deleteByExample(RCrudColumnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RCrudColumn record);

    int insertSelective(RCrudColumn record);

    List<RCrudColumn> selectByExample(RCrudColumnExample example);

    RCrudColumn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RCrudColumn record, @Param("example") RCrudColumnExample example);

    int updateByExample(@Param("record") RCrudColumn record, @Param("example") RCrudColumnExample example);

    int updateByPrimaryKeySelective(RCrudColumn record);

    int updateByPrimaryKey(RCrudColumn record);
}