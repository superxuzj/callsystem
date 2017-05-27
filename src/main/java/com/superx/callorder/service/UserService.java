package com.superx.callorder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superx.callorder.entity.User;
import com.superx.callorder.repository.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	
	public int insertSelective(User user) {
        return userMapper.insertSelective(user);
    }
	
    public List<User> selectUserList(User record){
    	return userMapper.selectUserList(record);
    }
    
    public Integer selectUserListCount(User record){
    	return userMapper.selectUserListCount(record);
    }
    
    public User selectByPrimaryKey(Integer id){
    	return userMapper.selectByPrimaryKey(id);
    }
    
    public  int updateByPrimaryKeySelective(User record){
    	return userMapper.updateByPrimaryKeySelective(record);
    }
}
