package com.superx.callorder.repository;

import java.util.List;

import com.superx.callorder.entity.SalaryTwo;

public interface SalaryTwoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalaryTwo record);

    int insertSelective(SalaryTwo record);

    SalaryTwo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalaryTwo record);

    int updateByPrimaryKey(SalaryTwo record);
    
    int deletByCount(String count);
    
    List<SalaryTwo> selectTwoList(SalaryTwo record);
}