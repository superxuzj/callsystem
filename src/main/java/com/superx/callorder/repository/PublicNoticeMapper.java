package com.superx.callorder.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.superx.callorder.entity.PublicNotice;
public interface PublicNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PublicNotice record);

    int insertSelective(PublicNotice record);

    PublicNotice selectByPrimaryKey(Integer id);
    
    //根据业务id查找公告 按时间
    List<PublicNotice> selectPublicNoticeListByBusinessId(Integer businessId);

    int updateByPrimaryKeySelective(PublicNotice record);

    int updateByPrimaryKey(PublicNotice record);
    
    List<PublicNotice>  selectPublicNoticeList(PublicNotice record);
}