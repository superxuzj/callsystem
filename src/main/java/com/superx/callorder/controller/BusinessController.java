package com.superx.callorder.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.superx.callorder.entity.Business;
import com.superx.callorder.entity.OrderNum;
import com.superx.callorder.entity.User;
import com.superx.callorder.service.BusinessService;
import com.superx.callorder.service.OrderNumService;
import com.superx.callorder.service.UserService;
import com.superx.callorder.util.Constant;
import com.superx.callorder.util.SendMessageUtil;

@Controller
@RequestMapping("/business")
public class BusinessController extends BaseCommonController{
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private OrderNumService orderNumService;
	@Autowired
	private UserService userService;
	@RequestMapping("")
	public String index(HttpServletRequest request, 
    		HttpServletResponse response,String type,
    		Business business,Model model){
		String usertype = getUserType(request);
		model.addAttribute("usertype", usertype);
		List<Business> list = new ArrayList<Business>();
		if(type!=null && !type.equals("")){
			business.setType(type);
		}else{
			business.setType(getBusinessType(request));
		}
		
		list = businessService.selectBusinessList(business);
		if(list!=null && list.size()>0){
			Business busi = list.get(0);
			busi.setAmStart(this.dateToString(busi.getAmStartTime()));
			busi.setAmEnd(this.dateToString(busi.getAmEndTime()));
			busi.setPmStart(this.dateToString(busi.getPmStartTime()));
			busi.setPmEnd(this.dateToString(busi.getPmEndTime()));
			
			OrderNum record = new OrderNum();
			record.setBusinessId(busi.getId());
			record.setType(busi.getType());
			record.setCreatorTime(new Date());
		    // 左侧的排队列表  当天所有的排队人员
			List<OrderNum> listOrderNum = orderNumService.selectOrderListByRecord(record);
			
			
			record.setStatus("12");
			List<OrderNum> orderList = orderNumService.selectOrderNumTop2(record);//前两位
			if(orderList!=null && orderList.size()>0){
				OrderNum current = orderList.get(0);
				User currentuser = userService.selectByPrimaryKey(current.getCreatorId());
				current.setPhone(currentuser.getPhone());
				current.setDepartment(currentuser.getDepartment());
				if(current.getStatus().equals("2")){
					model.addAttribute("current", current);//当前
				}
				if(orderList.size()>1){
					OrderNum next = orderList.get(1);
					User nextuser = userService.selectByPrimaryKey(current.getCreatorId());
					next.setPhone(nextuser.getPhone());
					next.setDepartment(nextuser.getDepartment());
					model.addAttribute("next", next);//下一位
				}
			}
//			List<OrderNum> listOrderNumTop2 = 
			model.addAttribute("listOrderNum", listOrderNum);
			model.addAttribute("business", busi);
		}
		return "finance";
	}
	
	
	 //业务状态
  	@RequestMapping("/timeleft")
  	@ResponseBody
  	public String timeleft(HttpServletRequest request, 
  			HttpServletResponse response,Integer id) {
  		Business business = businessService.selectByPrimaryKey(id);
  		OrderNum record = new OrderNum();
		record.setBusinessId(business.getId());
		record.setType(business.getType());
		record.setCreatorTime(new Date());
	    // 左侧的排队列表  当天所有的排队人员
		List<OrderNum> listOrderNum = orderNumService.selectOrderListByRecord(record);
		
		String out = "";
		
		if(listOrderNum!=null && listOrderNum.size()>0){
			out= "";
			for (OrderNum orderNum : listOrderNum) {
				out = out+"<li><a href='#'>"+orderNum.getOrderNum()+"__"+orderNum.getCreatorName()
						+"__未处理"+"</a></li>";
			}
		}
		Map<String,String> map = new HashMap<String,String> ();
		map.put("message",out);
  		return responseWrite(request, response, map);
  	}
	
  	/**
  	 * 下一位准备
  	 * @param request
  	 * @param response
  	 * @param businessId
  	 * @param currentId
  	 * @param model
  	 * @return
  	 */
	@RequestMapping("/nextOne")
	public String nextOne(HttpServletRequest request, HttpServletResponse response,
			Integer businessId,Integer currentId,Model model){
		System.out.println(currentId+" currentId");
		Business business = businessService.selectByPrimaryKey(businessId);
		
		if(currentId!=0){
			OrderNum currentOrder = orderNumService.selectByPrimaryKey(currentId);
			currentOrder.setStatus("3");
			currentOrder.setOperatorId(this.getUserId(request));
			currentOrder.setOperatorTime(new Date());
			orderNumService.updateByPrimaryKeySelective(currentOrder);
		}
		OrderNum record = new OrderNum();
		record.setBusinessId(businessId);
		record.setCreatorTime(new Date());
		record.setStatus("1");
		List<OrderNum> orderList = orderNumService.selectOrderNumTop2(record);
		if(orderList!=null && orderList.size()>0){
			OrderNum current = orderList.get(0);
			current.setStatus("2");//把第一个设置为处理中
			User user = userService.selectByPrimaryKey(current.getCreatorId());
			String phone = "tel:"+user.getPhone();
			//String phone = "tel:18631253aasas795";
			SendMessageUtil.sendMessage(phone, this.getDictValueByCode("NEXT"));//下一位短信
			orderNumService.updateByPrimaryKeySelective(current);
		}
		return "redirect:/business?type="+business.getType();
	}
	/**
	 * 1中心员工
		2横向
		3纵向
		4横纵
		5管理员
	 */
	@RequestMapping("/start")
	public String start(HttpServletRequest request, 
    		HttpServletResponse response,
    		Business business,Model model){
		Business record = businessService.selectByPrimaryKey(business.getId());
		record.setAmStartTime(this.stringToDate(business.getAmStart()));
		record.setAmEndTime(this.stringToDate(business.getAmEnd()));
		record.setPmStartTime(this.stringToDate(business.getPmStart()));
		record.setPmEndTime(this.stringToDate(business.getPmEnd()));
		record.setMaxNum(business.getMaxNum());
		record.setOperatorId(this.getUserId(request));
		record.setOperatorTime(new Date());
		//1、开始 2、暂停 3、结束
		record.setStatus("1");
		businessService.updateByPrimaryKey(record);
		return "redirect:/business?type="+record.getType();
	}
	
	@RequestMapping("/pause")
	public String pause(HttpServletRequest request, 
    		HttpServletResponse response,
    		Integer id,Model model){
		Business record = businessService.selectByPrimaryKey(id);
		record.setOperatorId(this.getUserId(request));
		record.setOperatorTime(new Date());
		//1、开始 2、暂停 3、结束
		record.setStatus("2");
		businessService.updateByPrimaryKeySelective(record);
		return "redirect:/business?type="+record.getType();
	}
	
	/**
	 * 作废按钮
	 * 
	 * 要是保留页面不变就用ajax
	 * @param request
	 * @param response
	 * @param id
	 * @param currentid
	 * @param model
	 * @return
	 */
	@RequestMapping("/cancel")
	public String cancel(HttpServletRequest request, 
    		HttpServletResponse response,
    		Integer currentid,Model model){
		System.out.println("cancel");
		
		OrderNum orderNum = orderNumService.selectByPrimaryKey(currentid);
		User user = userService.selectByPrimaryKey(orderNum.getCreatorId());
		orderNum.setStatus("5");//手动
		orderNumService.updateByPrimaryKeySelective(orderNum);
		
		String tels = "";
		tels = "tel:"+user.getPhone();
		SendMessageUtil.sendMessage(tels, this.getDictValueByCode("CANCLE"));//结束短信
	
		return "redirect:/business?type="+orderNum.getType();
	}
	
	@RequestMapping("/end")
	public String end(HttpServletRequest request, 
    		HttpServletResponse response,Integer id,
    		Integer currentid,Model model){
		System.out.println("111");
		Business record = businessService.selectByPrimaryKey(id);
		record.setOperatorId(this.getUserId(request));
		record.setOperatorTime(new Date());
		//1、开始 2、暂停 3、结束
		record.setStatus("3");
		businessService.updateByPrimaryKeySelective(record);
		
		OrderNum orderNum = new OrderNum();
		orderNum.setBusinessId(id);
		orderNum.setType(record.getType());
		orderNum.setCreatorTime(new Date());
		if(currentid!=0){
			orderNum.setId(currentid);
			orderNum.setStatus("3");//当前报销状态结束
			orderNumService.updateByPrimaryKeySelective(orderNum);
		}
		//结束  作废
		orderNum.setStatus("1");
//		orderNumService.updateByPrimaryKeySelective(record)
		//还没有处理的
		List<OrderNum> orderList = orderNumService.selectOrderListByRecord(orderNum);
		String tels = "tel:18611453795;";
		if(orderList!=null && orderList.size()>0){
			for (OrderNum orderN : orderList) {
				orderN.setStatus("4");
				orderNumService.updateByPrimaryKeySelective(orderN);
				User user = userService.selectByPrimaryKey(orderN.getCreatorId());
				tels = tels+ "tel:"+user.getPhone()+";";
			}
			tels = tels.substring(0, tels.length()-1);
		}
		SendMessageUtil.sendMessage(tels, this.getDictValueByCode("END"));//结束短信
		//发送短信
		return "redirect:/business?type="+record.getType();
	}
	
	 //业务状态
  	@RequestMapping("/businessStatus")
  	@ResponseBody
  	public String businessStatus(HttpServletRequest request, 
  			HttpServletResponse response,Integer id) {
  		Business business = businessService.selectByPrimaryKey(id);
		Map<String,String> map = new HashMap<String,String> ();
		map.put("message", business.getStatus());
  		return responseWrite(request, response, map);
  	}
	/**
	 * 一个用户类型对应一个业务类型
	 * @param request
	 * @return
	 */
	private String getBusinessType(HttpServletRequest request){
		/**
		 * usertype
		 * 
		 * <option value="1">中心员工</option>
			<option value="2">横向</option>
			<option value="3">纵向</option>
			<option value="4">横纵</option>
			<option value="5">管理员</option>
		 */
		String usertype = this.getUserType(request);
		String businessType = "";
		if(usertype.equals(Constant.HENG)){
			businessType = Constant.BUSINESSHENG;
		}else if(usertype.equals(Constant.ZONG)){
			businessType = Constant.BUSINESSZONG;
		}else if(usertype.equals(Constant.HENGZONG)){
			businessType = Constant.BUSINESSHENG;
		}
		return businessType;
				
	}
}
