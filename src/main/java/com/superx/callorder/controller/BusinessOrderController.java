package com.superx.callorder.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.superx.callorder.controller.base.BaseCommonController;
import com.superx.callorder.entity.User;
import com.superx.callorder.service.UserService;
import com.superx.callorder.util.JxlUtil;

@Controller
public class BusinessOrderController extends BaseCommonController{

	@Autowired
	private UserService userService;
	@RequestMapping("/business/order")
	public String orderNum(HttpServletRequest request, 
  			HttpServletResponse response,String type,Model model) {
        System.out.println(type );
        System.out.println("插入" );
        return "staffh";
	 }
	 
	
	//--------------------------则七杂八的处理-----------------------------------------------------------
	@RequestMapping("/util")
	public String util(HttpServletRequest request, 
  			HttpServletResponse response,Model model) {
        return "util";
	 }
	/**
	 * excel添加到数据库
	 * @param request
	 * @param response
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping("/util/addxls")
	@ResponseBody
	public String addxls(HttpServletRequest request, 
  			HttpServletResponse response,String type,Model model) {
        System.out.println(type );
        System.out.println("插入" );
        List<User> list = JxlUtil.getUserList();
        for (User user : list) {
        	userService.insertSelective(user);
		}
        Map map = new HashMap();
  		map.put("message", "OK");
  		return responseWrite(request, response, map);
	 }
	 
}
