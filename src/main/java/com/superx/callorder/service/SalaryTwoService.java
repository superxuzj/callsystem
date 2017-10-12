package com.superx.callorder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superx.callorder.entity.SalaryTwo;
import com.superx.callorder.repository.SalaryTwoMapper;

@Service
public class SalaryTwoService {

	@Autowired
	private SalaryTwoMapper salaryTwoMapper;

	public SalaryTwo selectByPrimaryKey(Integer id) {
		return salaryTwoMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SalaryTwo record) {
		return salaryTwoMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SalaryTwo record) {
		return salaryTwoMapper.updateByPrimaryKey(record);
	}

	public int insertSelective(SalaryTwo record){
		return salaryTwoMapper.insertSelective(record);
	}
	
	public int deletByCount(String count){
		return salaryTwoMapper.deletByCount(count);
	}
	
	public List<SalaryTwo> selectTwoList(SalaryTwo record){
		return salaryTwoMapper.selectTwoList(record);
	}
}
