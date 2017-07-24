package com.superx.callorder.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.superx.callorder.controller.base.BaseCommonController;
import com.superx.callorder.entity.OrderNum;
import com.superx.callorder.entity.PublicNotice;
import com.superx.callorder.entity.User;
import com.superx.callorder.service.OrderNumService;
import com.superx.callorder.service.PublicNoticeService;
import com.superx.callorder.service.UserService;
import com.superx.callorder.util.SendMessageUtil;

@Controller
@RequestMapping("/notice")
public class PublicNoticeController extends BaseCommonController{

	@Autowired
	private OrderNumService orderNumService;
	@Autowired
	private UserService userService;
	@Autowired
	private PublicNoticeService publicNoticeService;
	@RequestMapping("/add")
	public String add(HttpServletRequest request, 
    		HttpServletResponse response,
    		PublicNotice publicNotice,Model model){
		publicNotice.setCreatorId(this.getUserId(request));
		publicNotice.setCreatorTime(new Date());
		publicNotice.setStatus("1");
		publicNotice.setType("1");
		publicNoticeService.insertSelective(publicNotice);
		
		OrderNum record = new OrderNum();
		record.setBusinessId(publicNotice.getBusinessId());
		record.setStatus("1");
		record.setCreatorTime(new Date());
		List<OrderNum> orderList = orderNumService.selectOrderListByRecord(record);
		String tels = "tel:18611453795;";
		if(orderList!=null && orderList.size()>0){
			for (OrderNum orderNum : orderList) {
				User user = userService.selectByPrimaryKey(orderNum.getCreatorId());
				tels = tels+ "tel:"+user.getPhone()+";";
			}
			tels = tels.substring(0, tels.length()-1);
		}
		
		SendMessageUtil.sendMessage(tels, publicNotice.getContent());
		
		return "redirect:/business";
	}
}
