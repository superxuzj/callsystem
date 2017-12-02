package com.superx.callorder.repository;

import java.util.List;

import com.superx.callorder.entity.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    List<Department> selectDepartmentList(Department record);
    
    Integer selectDepartmentCount(Department record);
}