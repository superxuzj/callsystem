package com.superx.callorder.repository;

import com.superx.callorder.entity.SalaryRecord;

public interface SalaryRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalaryRecord record);

    int insertSelective(SalaryRecord record);

    SalaryRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalaryRecord record);

    int updateByPrimaryKey(SalaryRecord record);
}