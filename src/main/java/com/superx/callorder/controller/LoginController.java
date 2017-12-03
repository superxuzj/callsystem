package com.superx.callorder.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.superx.callorder.controller.base.BaseCommonController;
import com.superx.callorder.entity.LoginInfo;
import com.superx.callorder.entity.User;
import com.superx.callorder.service.LoginInfoService;
import com.superx.callorder.service.UserService;
import com.superx.callorder.util.CommonUtils;
import com.superx.callorder.util.Constant;
import com.superx.callorder.util.DesUtils;

@Controller
public class LoginController extends BaseCommonController{

	@Autowired
	private UserService userService;
	@Autowired
	private LoginInfoService  loginInfoService;
	
	/**
	 * 1中心员工
		2横向
		3纵向
		4横纵
		5管理员
		6财务管理员
	 * @param request
	 * @param response
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/dologin")
	public String dologin(HttpServletRequest request, 
  			HttpServletResponse response,User user,Model model){
		User usermodel = userService.selectByPrimaryKey(this.getUserId(request));
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setLogindate(new Date());
		loginInfo.setLoginip(this.getRemortIP(request));
		loginInfo.setName(usermodel.getName());
		loginInfo.setUsername(usermodel.getPhone());
		loginInfoService.insertSelective(loginInfo);
		if(usermodel.getPassword().equals(DesUtils.getEncryptValue("111111"))){
			return "redirect:/changepwd?id="+usermodel.getId()+"&change=1";
		}
		if(usermodel.getType()!=null && usermodel.getType().equals("5")){
			return "redirect:/manage";
		}
		else if(usermodel.getType()!=null && usermodel.getType().equals("6")){//财务管理员
			return "redirect:/salary/manage";
		}else if(usermodel.getType()!=null && usermodel.getType().equals("4")){
			return "redirect:/business";
		}else if(usermodel.getType()!=null && usermodel.getType().equals("3")){
			model.addAttribute("businesstype", "2");
			return "redirect:/business";
		}else if(usermodel.getType()!=null && usermodel.getType().equals("2")){
			model.addAttribute("businesstype", "1");
			return "redirect:/business";
		}else if(usermodel.getType()!=null && usermodel.getType().equals("1")){
			return "redirect:/center";
		}
		return "";
	}
	
	public String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	/*@RequestMapping("")
    public String index(HttpServletRequest request, 
  			HttpServletResponse response) {
        return "redirect:/login";
    }*/
	
	@RequestMapping("")
    public String index(HttpServletRequest request, 
  			HttpServletResponse response) {
        return "index";
    }
	
	
	@RequestMapping("/login")
    public String login(HttpServletRequest request, 
  			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute(Constant.USERID)!=null){
			return "redirect:/dologin";
		}else{
			 return "login";
		}
       
    }
	
	 //验证用户名和密码是否正确
  	@RequestMapping("/user/validate")
  	@ResponseBody
  	public String validateNameAndPassword(HttpServletRequest request, 
  			HttpServletResponse response, String phone,String password) {
  		System.out.println(phone+" "+password);
//  		HttpSession sesion = request.getSession();
  		User user = new User();
  		user.setPhone(phone);
  		user.setPassword(DesUtils.getEncryptValue(password));
  		//user.setPassword(password);
  		List<User> loginList = userService.selectUserList(user);
  		Map<String,String> map = new HashMap<String,String> ();
  		if(loginList!=null && loginList.size()>0){
  			HttpSession session = request.getSession();
  			User usermodel = loginList.get(0);
  			session.setAttribute(Constant.USERID, usermodel.getId());
  			session.setAttribute(Constant.USERNAME, usermodel.getName());
  			session.setAttribute(Constant.USERTYPE, usermodel.getType());
  			session.setAttribute(Constant.ISLOGIN, "OK");
  			map.put("message", "OK");
  		}else{
  			map.put("message", "用户名或密码错误！");
  		}
  		return responseWrite(request, response, map);
  	}
  	
  	@RequestMapping("/changepwd")
	public String changepassword(HttpServletRequest request, 
  			HttpServletResponse response,Integer id,Model model,String change){
  		if(change==null || change.equals("")){
  			
  		}else{
  			model.addAttribute("change", "--为了确保您的账户安全，请您修改初始密码。");
  		}
  		User user = userService.selectByPrimaryKey(id);
  		user.setPassword(DesUtils.getDecryptValue(user.getPassword()));
  		model.addAttribute("user", user);
		return "changepwd";
	}
  	
  	@RequestMapping("/savechange")
	public String savechange(HttpServletRequest request, 
  			HttpServletResponse response,User user,Model model){
  		User olduser = userService.selectByPrimaryKey(user.getId());
  		if(user.getPassword().equals("")){
  			user.setPassword(olduser.getPassword());
  		}else{
  			//修改密码 加密
  			user.setPassword(DesUtils.getEncryptValue(user.getPassword()));
  			
  		}
  		user.setOperatorTime(new Date());
  		userService.updateByPrimaryKeySelective(user);
  		model.addAttribute("user", user);
		return "redirect:/loginout";
	}
  	
  	
  	@RequestMapping("/loginout")
	public String loginout(HttpServletRequest request, 
  			HttpServletResponse response,User user,Model model){
		HttpSession session = request.getSession();
		CommonUtils.addNoCacheHeader(response);
		
		session.setAttribute(Constant.USERID,null);
		session.setAttribute(Constant.USERTYPE,null);
		session.setAttribute(Constant.ISLOGIN,null);
		return "redirect:/login";
	}
  	
  	@RequestMapping("/test")
    public String test(HttpServletRequest request, 
  			HttpServletResponse response) {
			 return "center";
    }
}
