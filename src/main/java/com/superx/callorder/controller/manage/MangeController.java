package com.superx.callorder.controller.manage;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.superx.callorder.common.Pager;
import com.superx.callorder.entity.Department;
import com.superx.callorder.entity.User;
import com.superx.callorder.service.DepartmentService;
import com.superx.callorder.service.UserService;

@Controller
@RequestMapping("/manage")
public class MangeController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("")
    public String index(HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
    		User user,Model model) {
		user.setStart((pageNo-1)*10);
		user.setLimit(10);
		List<User> userList = userService.selectUserList(user);
		Integer count = userService.selectUserListCount(user);
		Pager<User> pager = new Pager<User>(pageNo,10,count,userList);
		model.addAttribute("page", pager);
        return "/manage/usermanage";
    }
	
	/**
	 * 跳转修改用户密码界面
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edituser")
    public String goEdituser(HttpServletRequest request, 
    		HttpServletResponse response,
    		Integer id,Model model) {
		User user =  userService.selectByPrimaryKey(id);
		model.addAttribute("user", user);
		
	  List<Department> depatList = departmentService.selectDepartmentList(new Department());
	  model.addAttribute("depatList", depatList);
        return "/manage/useredit";
    }
	
	/**
	 * 执行更新操作
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateuser")
    public String updateuser(HttpServletRequest request, 
    		HttpServletResponse response,
    		User user,Model model) {
		String password = user.getPassword();
		String phone = user.getPhone();
		String department = user.getDepartment();
		user =  userService.selectByPrimaryKey(user.getId());
		user.setPassword(password);
		user.setPhone(phone);
		user.setDepartment(department);
		userService.updateByPrimaryKeySelective(user);
        return "redirect:/manage";
    }
	
	/**
	 * 执行更新操作
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/deluser")
    public String deluser(HttpServletRequest request, 
    		HttpServletResponse response,
    		Integer id,Model model) {
		User user =  userService.selectByPrimaryKey(id);
		user.setType("99");//删除
		userService.updateByPrimaryKeySelective(user);
        return "redirect:/manage";
    }
	
	/**
	 * 跳转新增员工
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/goadduser")
    public String goadduser(HttpServletRequest request, 
    		HttpServletResponse response,
    		Integer id,Model model) {
		List<Department> depatList = departmentService.selectDepartmentList(new Department());
		  model.addAttribute("depatList", depatList);
        return "/manage/useradd";
    }
	
	/**
	 * 新增用户
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveuser")
    public String saveuser(HttpServletRequest request, 
    		HttpServletResponse response,
    		User user,Model model) {
		user.setOperatorTime(new Date());
		user.setOperatorName("admin");
		int i = userService.insertSelective(user);
		if(i>0){
			return "redirect:/manage";
		}else{
			return "redirect:/manage";
		}
        
    }
}
