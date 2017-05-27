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
import com.superx.callorder.entity.Business;
import com.superx.callorder.entity.OrderNum;
import com.superx.callorder.entity.PublicNotice;
import com.superx.callorder.service.BusinessService;
import com.superx.callorder.service.OrderNumService;
import com.superx.callorder.service.PublicNoticeService;

/**
 * 中心员工Controller
 * @author xuzj
 *
 */
@Controller
@RequestMapping("/center")
public class CenterOrderControllor extends BaseCommonController{
	@Autowired
	private OrderNumService numService;
	@Autowired
	private BusinessService businessService;
	@Autowired
	private PublicNoticeService publicNoticeService;
	@RequestMapping("")
	public String index(HttpServletRequest request, 
  			HttpServletResponse response,Model model,Business record){
		List<Business> businessList = businessService.selectBusinessList(record);
		Business hBusiness = businessList.get(0);
		Business zBusiness = businessList.get(1);
		String hstatus = hBusiness.getStatus();
		String zstatus = zBusiness.getStatus();
		
		if(hBusiness.getStatus().equals("3") || !compareTime(hBusiness)){
			hstatus = "3";
		}
		if(zBusiness.getStatus().equals("3") || !compareTime(zBusiness)){
			zstatus = "3";
		}
		model.addAttribute("hstatus", hstatus);
		model.addAttribute("zstatus", zstatus);
		return "center";
	}
	
	private Boolean compareTime(Business business){
		Date now = new Date();
		if(business.getAmStartTime()!=null && business.getAmEndTime()!=null){
			if(now.getTime()>business.getAmStartTime().getTime() && now.getTime()< business.getAmEndTime().getTime()){
				return true;
			}
		}
		if(business.getPmStartTime()!=null && business.getPmEndTime()!=null){
			if(now.getTime()>business.getPmStartTime().getTime() && now.getTime()< business.getPmEndTime().getTime()){
				return true;
			}
		}
		
		return false;
	}

	
	@RequestMapping("/horder")
	public String horder(HttpServletRequest request, 
  			HttpServletResponse response,Model model){
		//new innerclass().test();
		model.addAttribute("FIRST", this.getDictValueByCode("FIRST"));
		model.addAttribute("ORDER_SE", this.getDictValueByCode("ORDER_SE"));
		Business business = businessService.selectBusinessByType("1");//1 横向 2 纵向
		PublicNotice publicNotice = new PublicNotice();
		publicNotice.setCreatorTime(new Date());
		publicNotice.setBusinessId(business.getId());
		publicNotice.setStatus("1");
		publicNotice.setType("1");
		List<PublicNotice> listpublicnotice = publicNoticeService.selectPublicNoticeList(publicNotice);
		if(listpublicnotice!=null && listpublicnotice.size()>0){
			publicNotice = listpublicnotice.get(0);
			if(business.getStatus().equals("2")|| business.getStatus().equals("3"))//业务暂停 或者结束才能看到公告
				model.addAttribute("publicNotice", publicNotice);
		}
		
		OrderNum orderNum = new OrderNum();
		orderNum.setBusinessId(business.getId());
		orderNum.setType("1");//1 横向 2 纵向
		orderNum.setStatus("1");//1 申请  2 处理中 3 处理完 4 作废
		
		//业务已开启 //1、开始 2、暂停 3、结束  并且时间在范围内
		if(business.getStatus().equals("3") || !compareTime(business)){//是不是只有在业务开始结束时间才能排号
			orderNum.setOrderNum(0);
			model.addAttribute("count", 0);
			model.addAttribute("orderNum", orderNum);
			business.setStatus("3");
			model.addAttribute("business", business);
			model.addAttribute("HUNBEGIN", this.getDictValueByCode("HUNBEGIN"));
			return "order";
		}else{
			
			orderNum.setCreatorId(this.getUserId(request));
			orderNum.setCreatorTime(new Date());
			orderNum.setCreatorName(this.getUserName(request));
			if(!numService.isOrderByRecord(orderNum)){//是否当天已经排号，如果没有，就排号
				orderNum.setStatus("1");
				numService.insertSelective(orderNum);
			}
			orderNum.setStatus("12");
			orderNum = numService.selectOrderByRecord(orderNum);//查询当天排号未处理的信息
			int count = numService.selectNohandleOrderedNum(orderNum);
			model.addAttribute("count", count);
			model.addAttribute("orderNum", orderNum);
			model.addAttribute("business", business);
			model.addAttribute("HBEGIN", this.getDictValueByCode("HBEGIN"));
			return "order";
		}
	}
	/////////多线程测试  begin-------------------
	class innerclass  extends Thread{
		//重写run方法，run方法的方法体就是现场执行体  
		int i=0;
	    public void run()  {  
	    	OrderNum orderNum = new OrderNum();
	    	orderNum.setBusinessId(1);
			orderNum.setType("1");//1 横向 2 纵向
			orderNum.setStatus("1");//1 申请  2 处理中 3 处理完 4 作废
	    	orderNum.setCreatorId(1);
			orderNum.setCreatorName("admin");
			orderNum.setCreatorTime(new Date());
			//是否当天已经排号，如果没有，就排号
			orderNum.setStatus("1");
			numService.insertSelective(orderNum);
	    } 
	    public void test(){
	    	for(int i = 0;i< 100;i++)  {  
	            System.out.println(Thread.currentThread().getName()+"  : "+i);  
	                new innerclass().start();  
	        }
	    }
	}
		/////////多线程测试  end-------------------
	
	@RequestMapping("/zorder")
	public String zorder(HttpServletRequest request, 
  			HttpServletResponse response,Model model){
		model.addAttribute("FIRST", this.getDictValueByCode("FIRST"));
		model.addAttribute("ORDER_SE", this.getDictValueByCode("ORDER_SE"));
		Business business = businessService.selectBusinessByType("2");//1 横向 2 纵向
		
		PublicNotice publicNotice = new PublicNotice();
		publicNotice.setCreatorTime(new Date());
		publicNotice.setBusinessId(business.getId());
		publicNotice.setStatus("1");
		publicNotice.setType("1");
		List<PublicNotice> listpublicnotice = publicNoticeService.selectPublicNoticeList(publicNotice);
		if(listpublicnotice!=null && listpublicnotice.size()>0){
			publicNotice = listpublicnotice.get(0);
			if(business.getStatus().equals("2"))//业务暂停才能看到公告
				model.addAttribute("publicNotice", publicNotice);
		}
		
		OrderNum orderNum = new OrderNum();
		orderNum.setBusinessId(business.getId());
		orderNum.setType("2");//1 横向 2 纵向
		orderNum.setStatus("1");//1 申请  2 处理中 3 处理完 4 作废
		//业务已开启 //1、开始 2、暂停 3、结束  并且时间在范围内
		if(business.getStatus().equals("3") || !compareTime(business)){//是不是只有在业务开始结束时间才能排号
			orderNum.setOrderNum(0);
			model.addAttribute("count", 0);
			model.addAttribute("orderNum", orderNum);
			business.setStatus("3");
			model.addAttribute("business", business);
			model.addAttribute("ZUNBEING", this.getDictValueByCode("ZUNBEING"));
			return "order";
		}else{
			orderNum.setCreatorId(this.getUserId(request));
			orderNum.setCreatorName(this.getUserName(request));
			orderNum.setCreatorTime(new Date());
			orderNum.setCreatorName(this.getUserName(request));
			if(!numService.isOrderByRecord(orderNum)){//是否当天已经排号，如果没有，就排号
				orderNum.setStatus("1");
				numService.insertSelective(orderNum);
			}
			orderNum.setStatus("12");
			orderNum = numService.selectOrderByRecord(orderNum);//查询当天排号未处理的信息
			System.out.println(orderNum.getCreatorTime());
			int count = numService.selectNohandleOrderedNum(orderNum);
			model.addAttribute("count", count);
			model.addAttribute("orderNum", orderNum);
			model.addAttribute("business", business);
			model.addAttribute("ZBEGIN", this.getDictValueByCode("ZBEGIN"));
			System.out.println(orderNum.toString());
			return "order";
		}
	}
}
