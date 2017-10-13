package com.superx.callorder.controller.manage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.superx.callorder.controller.base.BaseCommonController;
import com.superx.callorder.entity.SalaryOne;
import com.superx.callorder.entity.SalaryRecord;
import com.superx.callorder.entity.SalaryTwo;
import com.superx.callorder.entity.User;
import com.superx.callorder.service.SalaryOneService;
import com.superx.callorder.service.SalaryRecordService;
import com.superx.callorder.service.SalaryTwoService;
import com.superx.callorder.service.UserService;
import com.superx.callorder.util.CommonUtils;
import com.superx.callorder.util.EncrypDESUtil;
import com.superx.callorder.util.SendMessageUtil;

/**
 * 工资查询Controller
 * 
 * @author xuzj
 * 
 */
@Controller
@RequestMapping("/salary")
public class SalaryControllor extends BaseCommonController {

	@Autowired
	private SalaryOneService salaryOneService;
	
	@Autowired
	private SalaryTwoService salaryTwoService;
	
	@Autowired
	private SalaryRecordService salaryRecordService;
	
	@Autowired
	private UserService userService; 

	/**
	 * 跳转验证码页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/vercode")
	public String goVerCode(HttpServletRequest request,Model model) {
		Integer userId = getUserId(request);
		User user = userService.selectByPrimaryKey(userId);
		if(user.getPhone()!=null && !user.getPhone().equals("")){
			model.addAttribute("phone", user.getPhone());
		}
		return "vercode";
	}
	
	/**
	 * 生成手机短信验证码给前端验证
	 * @param request
	 * @param model
	 * @param phone
	 * @return
	 */
	@RequestMapping("/code")
	@ResponseBody
	public String code(HttpServletRequest request,Model model,String phone) {
		System.out.println(phone);
		
		phone = "tel:"+phone;
		//String phone = "tel:15210419293;tel:18611453795";
		//String phone = "tel:18631253aasas795";
		String code = CommonUtils.getFourRandom();
		SendMessageUtil.sendMessage(phone, "您此次工资条查询手机验证码为："+code+"，请妥善保管，不要告诉其他人。");
		return code;
	}

	@RequestMapping("/view")
	public String goview(HttpServletRequest request,Model model,String datatime) {
		
		//System.out.println("dddd'"+datatime);
		
		if(datatime==null || datatime.equals("")){
			datatime = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
		}
		Integer userId = getUserId(request);
		User user = userService.selectByPrimaryKey(userId);
		SalaryOne recordOne = new SalaryOne();
		recordOne.setName(user.getName());
		recordOne.setDepartment(user.getDepartment());
		recordOne.setNianyue(datatime);
		List<SalaryOne> list1 = salaryOneService.selectOneList(recordOne);
		if(list1!=null && list1.size()>0){
			SalaryOne salaryOne = list1.get(0);
			model.addAttribute("salaryOne", salaryOne);
		}
		
		SalaryTwo recordTwo = new SalaryTwo();
		recordTwo.setName(user.getName());
		recordTwo.setDepartment(user.getDepartment());
		recordTwo.setNianyue(datatime);
		List<SalaryTwo> list2 = salaryTwoService.selectTwoList(recordTwo);
		if(list2!=null && list2.size()>0){
			SalaryTwo salaryTwo = list2.get(0);
			model.addAttribute("salaryTwo", salaryTwo);
		}
		model.addAttribute("datatime", datatime);
		return "view";
	}
	
	@RequestMapping("/export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response,Model model,String datatime) {
		if(datatime==null || datatime.equals("")){
			datatime = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
		}
		SalaryOne recordOne = new SalaryOne();
		Integer userId = getUserId(request);
		User user = userService.selectByPrimaryKey(userId);
		recordOne.setName(user.getName());
		recordOne.setDepartment(user.getDepartment());
		recordOne.setNianyue(datatime);
        HSSFWorkbook wb = salaryOneService.export(recordOne);   
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename="+datatime+".xls");    
        OutputStream ouputStream;
		try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);    
	        ouputStream.flush();    
	        ouputStream.close(); 
		} catch (IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}    
       
	}
	
	@RequestMapping("/manage")
	public String manage() {
		return "/manage/salaryManage";
	}

	@RequestMapping("/uploadfile")
	public String uploadfile(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") CommonsMultipartFile file,Model model) {
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		int ncount = 0;
		try {
			request.setCharacterEncoding("UTF-8");
			InputStream is = file.getInputStream();
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 获取每一个工作薄
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			if (hssfSheet == null) {
				model.addAttribute("message", "没有数据，请检查原始文档！");
				return "/manage/info"; 
			}
			
			HSSFRow hssfTop = hssfSheet.getRow(0);
			String typeString = getNoValue(hssfTop.getCell(2));
			if(typeString.trim().equals("薪级工资")){
				//去重操作，删除当前月份的数据
				HSSFRow hssfRowCount = hssfSheet.getRow(1);
				salaryOneService.deletByCount(getCount(hssfRowCount.getCell(29)));
				
				// 工资条1 多的
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						SalaryOne salaryOne = new SalaryOne();
						HSSFCell department = hssfRow.getCell(0);
						salaryOne.setDepartment(getValue(department));
						HSSFCell name = hssfRow.getCell(1);
						if (getValue(name).trim().equals("")) {
							continue;
						}
						salaryOne.setName(getValue(name));
						HSSFCell xinji = hssfRow.getCell(2);
						salaryOne.setXinji(getValue(xinji));
						HSSFCell gangwei = hssfRow.getCell(3);
						salaryOne.setGangwei(getValue(gangwei));
						HSSFCell gonggai = hssfRow.getCell(4);
						salaryOne.setGonggai(getValue(gonggai));
						HSSFCell jintie = hssfRow.getCell(5);
						salaryOne.setJintie(getValue(jintie));
						HSSFCell fudong = hssfRow.getCell(6);
						salaryOne.setFudong(getValue(fudong));
						HSSFCell zhibu = hssfRow.getCell(7);
						salaryOne.setZhibu(getValue(zhibu));
						HSSFCell diqu = hssfRow.getCell(8);
						salaryOne.setDiqu(getValue(diqu));
						HSSFCell jiaobu = hssfRow.getCell(9);
						salaryOne.setJiaobu(getValue(jiaobu));
						HSSFCell weinaru = hssfRow.getCell(10);
						salaryOne.setWeinaru(getValue(weinaru));
						HSSFCell tizu = hssfRow.getCell(11);
						salaryOne.setTizu(getValue(tizu));
						HSSFCell huiyingdu = hssfRow.getCell(12);
						salaryOne.setHuiyingdu(getValue(huiyingdu));
						HSSFCell cailanzi = hssfRow.getCell(13);
						salaryOne.setCailanzi(getValue(cailanzi));
						HSSFCell shenghuo = hssfRow.getCell(14);
						salaryOne.setShenghuo(getValue(shenghuo));
						HSSFCell tongxin = hssfRow.getCell(15);
						salaryOne.setTongxin(getValue(tongxin));
						HSSFCell guginggangwei = hssfRow.getCell(16);
						salaryOne.setGuginggangwei(getValue(guginggangwei));
						HSSFCell fudonggangwei = hssfRow.getCell(17);
						salaryOne.setFudonggangwei(getValue(fudonggangwei));
						HSSFCell wuyefei = hssfRow.getCell(18);
						salaryOne.setWuyefei(getValue(wuyefei));
						HSSFCell xiangmujixiao = hssfRow.getCell(19);
						salaryOne.setXiangmujixiao(getValue(xiangmujixiao));
						HSSFCell bufa = hssfRow.getCell(20);
						salaryOne.setBufa(getValue(bufa));
						HSSFCell yingfaheji = hssfRow.getCell(21);
						salaryOne.setYingfaheji(getValue(yingfaheji));
						HSSFCell qita = hssfRow.getCell(22);
						salaryOne.setQita(getValue(qita));
						HSSFCell geshui = hssfRow.getCell(23);
						salaryOne.setGeshui(getValue(geshui));
						HSSFCell gongjijin = hssfRow.getCell(24);
						salaryOne.setGongjijin(getValue(gongjijin));
						HSSFCell yanglao = hssfRow.getCell(25);
						salaryOne.setYanglao(getValue(yanglao));
						HSSFCell shiye = hssfRow.getCell(26);
						salaryOne.setShiye(getValue(shiye));
						HSSFCell yukou = hssfRow.getCell(27);
						salaryOne.setYukou(getValue(yukou));
						HSSFCell shifaheji = hssfRow.getCell(28);
						salaryOne.setShifaheji(getValue(shifaheji));
						HSSFCell count = hssfRow.getCell(29);
						salaryOne.setCount(getCount(count));
						String d=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
						salaryOne.setNianyue(d);
						salaryOne.setCtratetime(new Date());
						salaryOne.setCtrator("admin");
						ncount++;
						salaryOneService.insertSelective(salaryOne);
						
					}
				}
			}else{
				//去重操作，删除当前月份的数据
				HSSFRow hssfRowCount = hssfSheet.getRow(1);
				salaryTwoService.deletByCount(getCount(hssfRowCount.getCell(9)));
				// 工资条2 少的
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						SalaryTwo salaryTwo = new SalaryTwo();
						HSSFCell department = hssfRow.getCell(0);
						salaryTwo.setDepartment(getValue(department));
						HSSFCell name = hssfRow.getCell(1);
						if (getValue(name).trim().equals("")) {
							continue;
						}
						salaryTwo.setName(getValue(name));
						HSSFCell bianji = hssfRow.getCell(2);
						salaryTwo.setBianji(getValue(bianji));
						HSSFCell zhiban = hssfRow.getCell(3);
						salaryTwo.setZhiban(getValue(zhiban));
						HSSFCell jiangke = hssfRow.getCell(4);
						salaryTwo.setJiangke(getValue(jiangke));
						HSSFCell chuche = hssfRow.getCell(5);
						salaryTwo.setChuche(getValue(chuche));
						HSSFCell yingfa = hssfRow.getCell(6);
						salaryTwo.setYingfaheji(getValue(yingfa));
						HSSFCell geshui = hssfRow.getCell(7);
						salaryTwo.setGeshui(getValue(geshui));
						HSSFCell shifaheji = hssfRow.getCell(8);
						salaryTwo.setShifaheji(getValue(shifaheji));
						HSSFCell count = hssfRow.getCell(9);
						salaryTwo.setCount(getCount(count));
						//年月
						String d=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
						salaryTwo.setNianyue(d);
						salaryTwo.setCtratetime(new Date());
						salaryTwo.setCtrator("admin");
						ncount++;
						salaryTwoService.insertSelective(salaryTwo);
					}
				}
			}
			//记录总数
			SalaryRecord salaryRecord = new SalaryRecord();
			salaryRecord.setCount(ncount);
			salaryRecord.setCreatdate(new Date());
			salaryRecord.setType("1");
			salaryRecord.setCreator("admin");
			salaryRecordService.insertSelective(salaryRecord);
			
		} catch (IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
			model.addAttribute("message", "文档内容错误，请检查重新上传");
			return "/manage/info";
		}
		model.addAttribute("message", "上传成功");
		return "/manage/info";
	}

	/**
	 * 没有加密
	 * @param hssfCell
	 * @return
	 */
	private String getNoValue(HSSFCell hssfCell){
		String returnString = "";
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			returnString =  String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			DecimalFormat df = new DecimalFormat("######0.00");
			returnString = df.format(hssfCell.getNumericCellValue());
		} else {
			returnString = String.valueOf(hssfCell.getStringCellValue());
		}
		return returnString;
	}
	
	// 转换数据格式
	private String getValue(HSSFCell hssfCell) {
		String returnString = "";
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			returnString =  String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			DecimalFormat df = new DecimalFormat("######0.00");
			returnString = df.format(hssfCell.getNumericCellValue());
		} else {
			returnString = String.valueOf(hssfCell.getStringCellValue());
		}
		
		
		try {
			EncrypDESUtil de1 = new EncrypDESUtil();
	        byte[] encontent = de1.Encrytor(returnString);  
	        returnString = new String(encontent);
		} catch (NoSuchAlgorithmException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		} catch (NoSuchPaddingException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		} catch (InvalidKeyException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		} catch (IllegalBlockSizeException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		} catch (BadPaddingException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}  
         System.out.println(returnString);
		return returnString;
	}
	
	

	// 转换数据格式
	private String getCount(HSSFCell hssfCell) {
		DecimalFormat df = new DecimalFormat("######0");
		return df.format(hssfCell.getNumericCellValue());
	}
}