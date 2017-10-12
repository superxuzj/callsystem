package com.superx.callorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superx.callorder.entity.SalaryRecord;
import com.superx.callorder.repository.SalaryRecordMapper;

@Service
public class SalaryRecordService {

	@Autowired
	private SalaryRecordMapper salaryRecordMapper;

	public SalaryRecord selectByPrimaryKey(Integer id) {
		return salaryRecordMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SalaryRecord record) {
		return salaryRecordMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SalaryRecord record) {
		return salaryRecordMapper.updateByPrimaryKey(record);
	}

	public int insertSelective(SalaryRecord record){
		return salaryRecordMapper.insertSelective(record);
	}
}
