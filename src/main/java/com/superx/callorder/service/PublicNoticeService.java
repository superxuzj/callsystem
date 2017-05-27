package com.superx.callorder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superx.callorder.entity.PublicNotice;
import com.superx.callorder.repository.PublicNoticeMapper;

@Service
public class PublicNoticeService {

	@Autowired
	private PublicNoticeMapper publicNoticeMapper;
	
	public int insertSelective(PublicNotice record){
		return publicNoticeMapper.insertSelective(record);
	}
	
	public List<PublicNotice>  selectPublicNoticeList(PublicNotice record){
		return publicNoticeMapper.selectPublicNoticeList(record);
	}
	
}
