package com.superx.callorder.repository;

import java.util.List;

import com.superx.callorder.entity.SalaryOne;

public interface SalaryOneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalaryOne record);

    int insertSelective(SalaryOne record);

    SalaryOne selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalaryOne record);

    int updateByPrimaryKey(SalaryOne record);
    
    int deletByCount(String count);
    
    List<SalaryOne> selectOneList(SalaryOne record);
}