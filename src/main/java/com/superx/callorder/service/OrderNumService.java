package com.superx.callorder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superx.callorder.entity.OrderNum;
import com.superx.callorder.repository.OrderNumMapper;

@Service
public class OrderNumService {
	@Autowired
	private OrderNumMapper orderNumMapper;
	
	public int updateByPrimaryKeySelective(OrderNum record){
		return orderNumMapper.updateByPrimaryKeySelective(record);
	}
	
	public OrderNum selectByPrimaryKey(Integer id){
		return orderNumMapper.selectByPrimaryKey(id);
	}
	
	public synchronized int insertSelective(OrderNum record){
		int orderNum = orderNumMapper.selectOrderedNum(record);//今天排到第几号  今天所有的排号数
		record.setOrderNum(orderNum+1);
		return orderNumMapper.insertSelective(record);
	}
	
	
	
	/**
	 * 根据userid businessId type status 查询该员工当天是否已经排号
	 * 而且该号码是申请状态
	 * @param record
	 * @return
	 */
	public Boolean isOrderByRecord(OrderNum record){
		record.setStatus("12");
		List<OrderNum> list = orderNumMapper.selectOrderListByRecord(record);
		if(list!=null && list.size()>0){
			return true;//已经排号了
		}else{
			return false;
		}
	}
	/**
	 * 查询员工当天排号未处理的排号信息
	 * @param record
	 * @return
	 */
	public OrderNum selectOrderByRecord(OrderNum record){
		List<OrderNum> list = orderNumMapper.selectOrderListByRecord(record);
		return list.get(0);
	}
	
	/**
	 * //查询当天未处理的排号数据
	 * 还有几个排号没有处理
	 * @param record
	 * @return
	 */
	public int selectNohandleOrderedNum(OrderNum record){
		record.setStatus("12");
		return orderNumMapper.selectNohandleOrderedNum(record);
	}
	
	public List<OrderNum> selectOrderListByRecord(OrderNum record){
		return orderNumMapper.selectOrderListByRecord(record);
	}
	
	//财务页面，当前报销和下一位  排号没有叫号的前两位
	public List<OrderNum> selectOrderNumTop2(OrderNum record){
		//record.setStatus("1");//1 申请  2 处理中 3 处理完 4 作废
		return orderNumMapper.selectOrderNumTop2(record);
	}
	
}
