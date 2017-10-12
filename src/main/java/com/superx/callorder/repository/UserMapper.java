package com.superx.callorder.repository;

import java.util.List;

import com.superx.callorder.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectUserList(User record);
    
    Integer selectUserListCount(User record);
    
}