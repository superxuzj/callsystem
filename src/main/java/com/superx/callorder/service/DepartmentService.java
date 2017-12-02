package com.superx.callorder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superx.callorder.entity.Department;
import com.superx.callorder.entity.User;
import com.superx.callorder.repository.DepartmentMapper;

@Service
public class DepartmentService {

	@Autowired
	DepartmentMapper departmentMapper;
	
	public int insertSelective(Department department) {
        return departmentMapper.insertSelective(department);
    }
	
    public List<Department> selectDepartmentList(Department record){
    	return departmentMapper.selectDepartmentList(record);
    }
    
    public Integer selectDepartmentCount(Department record){
    	return departmentMapper.selectDepartmentCount(record);
    }
    
    public Department selectByPrimaryKey(Integer id){
    	return departmentMapper.selectByPrimaryKey(id);
    }
    
    public  int updateByPrimaryKeySelective(Department record){
    	return departmentMapper.updateByPrimaryKeySelective(record);
    }
}
