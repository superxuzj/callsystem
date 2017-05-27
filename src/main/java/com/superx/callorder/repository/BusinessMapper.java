package com.superx.callorder.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.superx.callorder.entity.Business;
public interface BusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Business record);
    
    int updateByTypeSelective(Business record);

    int updateByPrimaryKey(Business record);
    
    public List<Business> selectBusinessList(Business record);
}