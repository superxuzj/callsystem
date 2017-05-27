package com.superx.callorder.controller.base;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.superx.callorder.service.DictionaryService;
import com.superx.callorder.util.Constant;

public class BaseCommonController {

	@Autowired
	private DictionaryService dictionaryService;
	
	public String getDictValueByCode(String code){
		return dictionaryService.getDictvalueByCode(code);
	}
	/**
	 * json跨域输出内容
	 */
	/**
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	protected String responseWrite(HttpServletRequest request,
			HttpServletResponse response, Object map) {
		try {	
			if(map instanceof String){
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(map);
				response.flushBuffer();
			}else{
				String json = JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd");
				Object param = request.getParameter("jsonpcallback");
				String content = json;
				if (param != null) {
					content = param + "(" + json + ")";
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(content);
				response.flushBuffer();
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected String getUserType(HttpServletRequest request){
		HttpSession session = request.getSession();
		String type = (String) session.getAttribute(Constant.USERTYPE);
		return type;
	}
	protected Integer getUserId(HttpServletRequest request){
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute(Constant.USERID);
		return userid;
	}
	protected String getUserName(HttpServletRequest request){
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(Constant.USERNAME);
		return userName;
	}
	
	protected Date stringToDate(String sourceString){
		if(sourceString==null || sourceString.equals("")){
			return null;
		}
		Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置显示格式
        String nowTime=df.format(dt);//当天日期
	   	System.out.println(nowTime);
	   	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
	   	java.util.Date date = null;
		try {
			date = sdf.parse(nowTime+" "+sourceString+":00");
		} catch (ParseException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		} 
	    return date;
	}
	
	protected String dateToString(Date date){
		if(date == null){
			return "";
		}
        DateFormat df = new SimpleDateFormat("HH:mm");//设置显示格式
        String nowTime=df.format(date);
        return nowTime;
	}
	
	/**
	 * 获取当前登录用户
	 * 
	 */
/*	protected UserModel getUserModel(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.getAttribute("");
		if(!isEmpty(casFastUserModel)){
    		CasFastUserModel model = new CasFastUserModel();
			String r;
			for(String row:casFastUserModel.split("&")){
				//u=?
				if(row.length()<=2)
					continue;
				//u=
				r = row.substring(2);
				if(isEmpty(r))
					continue;
				if(row.startsWith("i"))
					model.setId(Long.parseLong(row.substring(2)));
				else if(row.startsWith("n"))
					model.setName(r);
				else if(row.startsWith("e"))
					model.setEmail(r);
				else if(row.startsWith("m"))
					model.setMobile(r);
				else if(row.startsWith("g"))
					model.setGendar(r);
				else if(row.startsWith("u"))
					model.setWorkunit(r);
				else if(row.startsWith("l"))
					model.setLastLoginTime(r);
			}
    		return model;
    	}
		return CASContext.getCASUserModel(request.getSession());
	}
*/}
