package com.superx.callorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superx.callorder.entity.LoginInfo;
import com.superx.callorder.repository.LoginInfoMapper;
@Service
public class LoginInfoService {
	@Autowired
	private LoginInfoMapper loginInfoMapper;

    public int insertSelective(LoginInfo record){
    	return loginInfoMapper.insertSelective(record);
    }

}