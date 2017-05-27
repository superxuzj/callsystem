package com.superx.callorder.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.superx.callorder.entity.OrderNum;
public interface OrderNumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderNum record);

    int insertSelective(OrderNum record);

    OrderNum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderNum record);

    int updateByPrimaryKey(OrderNum record);
    //今天已经排号该业务的人数,包括已经处理的
    int selectOrderedNum(OrderNum record);
    
    // 查询今天排号早于该员工，未处理的数量
    int selectNohandleOrderedNum(OrderNum record);
    
    List<OrderNum> selectOrderListByRecord(OrderNum record);
    
	List<OrderNum> selectOrderNumTop2(OrderNum record);
    
}