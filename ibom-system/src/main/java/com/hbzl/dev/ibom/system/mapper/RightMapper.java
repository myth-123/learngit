package com.hbzl.dev.ibom.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hbzl.dev.ibom.system.model.Right;

public interface RightMapper {
    int deleteByPrimaryKey(String id);

    int insert(Right record);

    int insertSelective(Right record);

    Right selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Right record);

    int updateByPrimaryKey(Right record);
    
    List<String> getRoleRightByRoleId(@Param("roleIdList")List<String> roleIdList);
}