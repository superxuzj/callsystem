package com.superx.callorder.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.superx.callorder.entity.Business;
import com.superx.callorder.repository.BusinessMapper;

@Service
@Component("businessService")
public class BusinessService {

	@Autowired
	private BusinessMapper businessMapper;
	
	//定时任务 判断结束时间业务状态
	public void runTask() {
		List<Business> businessList = businessMapper.selectBusinessList(new Business());
		if(businessList!=null && businessList.size()>0){
			for (Business business : businessList) {
				Date now = new Date();
				if(business.getPmEndTime()!=null){
					if(now.getTime()>business.getPmEndTime().getTime()&& !business.getStatus().equals("3") ){
						business.setStatus("3");
						businessMapper.updateByPrimaryKey(business);
						return ;
					}
				}else{
					if(business.getAmEndTime()!=null){
						if(now.getTime()>business.getAmEndTime().getTime()&& !business.getStatus().equals("3") ){
							business.setStatus("3");
							businessMapper.updateByPrimaryKey(business);
							return ;
						}
					}
				}
				
			}
		}
		System.out.println("11111111");
	}
	
	public List<Business> selectBusinessList(Business record){
		return businessMapper.selectBusinessList(record);
	}
	
	public int updateByTypeSelective(Business record){
		return businessMapper.updateByTypeSelective(record);
	}
	
	public Business selectByPrimaryKey(Integer id){
		return businessMapper.selectByPrimaryKey(id);
	}
	
	public Business selectBusinessByType(String type){
		Business record =new Business();
		record.setType(type);
		List<Business> list = businessMapper.selectBusinessList(record);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return record;
		}
			
	}
	
	public int updateByPrimaryKeySelective(Business record){
		return businessMapper.updateByPrimaryKeySelective(record);
	}
	
	public int updateByPrimaryKey(Business record){
		return businessMapper.updateByPrimaryKey(record);
	}
	public String test(){
		System.out.println("111111111111111");
		return "";
	}
	
}
