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

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping
	public String defaultIndex(){
		return "redirect:/department/list";
	}
	
	@RequestMapping("list")
    public String index(HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
    		Department department,Model model) {
		department.setStart((pageNo-1)*10);
		department.setLimit(10);
		List<Department> departmentList = departmentService.selectDepartmentList(department);
		Integer count = departmentService.selectDepartmentCount(department);
		Pager<Department> pager = new Pager<Department>(pageNo,10,count,departmentList);
		model.addAttribute("page", pager);
        return "/department/list";
    }
	
	/**
	 * 跳转修改用户密码界面
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editdepartment")
    public String goEditdepartment(HttpServletRequest request, 
    		HttpServletResponse response,
    		Integer id,Model model) {
		Department department =  departmentService.selectByPrimaryKey(id);
		model.addAttribute("department", department);
        return "/department/edit";
    }
	
	/**
	 * 执行更新操作
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updatedepartment")
    public String updatedepartment(HttpServletRequest request, 
    		HttpServletResponse response,
    		Department department,Model model) {
		departmentService.updateByPrimaryKeySelective(department);
        return "redirect:/department/list";
    }
	
	/**
	 * 执行删除操作
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/deldepartment")
    public String deluser(HttpServletRequest request, 
    		HttpServletResponse response,
    		Integer id,Model model) {
		Department department=  departmentService.selectByPrimaryKey(id);
		department.setType("99");//删除
		departmentService.updateByPrimaryKeySelective(department);
        return "redirect:/department/list";
    }
	
	/**
	 * 跳转新增员工
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/goadddepartment")
    public String goadduser(HttpServletRequest request, 
    		HttpServletResponse response,
    		Integer id,Model model) {
        return "/department/add";
    }
	
	/**
	 * 新增用户
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/save")
    public String saveuser(HttpServletRequest request, 
    		HttpServletResponse response,
    		Department department,Model model) {
		department.setType("1");
		departmentService.insertSelective(department);
		return "redirect:/department/list";
    }
}
