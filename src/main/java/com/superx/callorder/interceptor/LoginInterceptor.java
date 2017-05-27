package com.superx.callorder.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object obj, Exception err)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object obj, ModelAndView mav) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object obj) throws Exception {
		// TODO Auto-generated method stub
		//获取session里的登录状态值
	    String str = (String) request.getSession().getAttribute("isLogin");
	    //如果登录状态不为空则返回true，返回true则会执行相应controller的方法
	    str= "1";
	    if(str!=null){
	      return true;
	    }
	    //如果登录状态为空则重定向到登录页面，并返回false，不执行原来controller的方法
	    response.sendRedirect("/backend/loginPage");
	    return false;
	}

}
