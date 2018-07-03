package com.hbzl.dev.ibom.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hbzl.dev.ibom.system.model.User;
import com.hbzl.dev.ibom.system.model.UserKey;

public interface UserMapper {
    int deleteByPrimaryKey(UserKey key);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(UserKey key);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User verifyUser(@Param("list")List<String> list,@Param("userName")String userName,@Param("password")String password);
}