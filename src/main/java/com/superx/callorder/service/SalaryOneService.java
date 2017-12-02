package com.superx.callorder.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superx.callorder.entity.SalaryOne;
import com.superx.callorder.entity.SalaryTwo;
import com.superx.callorder.repository.SalaryOneMapper;
import com.superx.callorder.repository.SalaryTwoMapper;
import com.superx.callorder.util.DesUtils;

@Service
public class SalaryOneService {

	@Autowired
	private SalaryOneMapper salaryOneMapper;

	@Autowired
	private SalaryTwoMapper salaryTwoMapper;
	
	public SalaryOne selectByPrimaryKey(Integer id) {
		return salaryOneMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SalaryOne record) {
		return salaryOneMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SalaryOne record) {
		return salaryOneMapper.updateByPrimaryKey(record);
	}
	
	public int insertSelective(SalaryOne record){
		return salaryOneMapper.insertSelective(record);
	}
	
	public int deletByCount(String count){
		return salaryOneMapper.deletByCount(count);
	}
	
	public List<SalaryOne> selectOneList(SalaryOne record){
		return salaryOneMapper.selectOneList(record);
	}
	String[] excelHeader = { "部门名称","职员姓名	","薪级工资","岗位工资","工改保留","津贴","浮动工资","职补","地区补助",
			"交补","未纳入","提租补贴","回婴独","菜蓝子","生活补贴","通信补贴","固定岗位绩效","浮动岗位绩效","物业费",
			"项目绩效","补发","应发工资合计","其他","个税","公积金","养老保险","失业保险","补扣公积金","扣工会会费",
			"加班费用","预扣养老金","实发工资合计","咨询费","评审费","审稿费","编辑费","值班费","其他费用","讲课费","出车补助",
			"应发费用合计","费用个税","实发费用合计","发放次数","合计"};  
	
	/*{ "部门名称0","职员姓名1","薪级工资2","岗位工资3","工改保留4","津贴5","浮动工资6","职补7","地区补助8",
		"交补9","未纳入10","提租补贴11","回婴独12","菜蓝子13","生活补贴14","通信补贴15","固定岗位绩效16","浮动岗位绩效17","物业费18",
		"项目绩效19","补发20","应发工资合计21","其他22","个税23","公积金24","养老保险25","失业保险26","补扣公积金27","扣工会会费28",
		"加班费用29","预扣养老金30","实发工资合计31","咨询费32","评审费33","审稿费34","编辑费35","值班费36","其他费用37","讲课38费","出车补助39",
		"应发费用合计40","费用个税41","实发费用合计42","发放次数43","合计"};*/
	public HSSFWorkbook export(SalaryOne record) {    
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("工资条");    
        HSSFRow row = sheet.createRow((int) 0);
        
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        
        HSSFFont font = wb.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 粗体 
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中  
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
    
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);  
            sheet.setColumnWidth(i, 3500); 
        }    
    
        row = sheet.createRow(1);
        List<SalaryOne> list1 = salaryOneMapper.selectOneList(record);
        SalaryOne salaryOne = new SalaryOne();
        if(list1!=null && list1.size()>0){
        	salaryOne = list1.get(0);
        }
        row.createCell(0).setCellValue(salaryOne.getDepartment());    
        row.createCell(1).setCellValue(salaryOne.getName());    
        row.createCell(2).setCellValue(getDecrypt(salaryOne.getXinji()));
        row.createCell(3).setCellValue(getDecrypt(salaryOne.getGangwei()));
        row.createCell(4).setCellValue(getDecrypt(salaryOne.getGonggai()));
        row.createCell(5).setCellValue(getDecrypt(salaryOne.getJintie()));
        row.createCell(6).setCellValue(getDecrypt(salaryOne.getFudong()));
        row.createCell(7).setCellValue(getDecrypt(salaryOne.getZhibu()));
        row.createCell(8).setCellValue(getDecrypt(salaryOne.getDiqu()));
        row.createCell(9).setCellValue(getDecrypt(salaryOne.getJiaobu()));
        row.createCell(10).setCellValue(getDecrypt(salaryOne.getWeinaru()));
        row.createCell(11).setCellValue(getDecrypt(salaryOne.getTizu()));
        row.createCell(12).setCellValue(getDecrypt(salaryOne.getHuiyingdu()));
        row.createCell(13).setCellValue(getDecrypt(salaryOne.getCailanzi()));
        row.createCell(14).setCellValue(getDecrypt(salaryOne.getShenghuo()));
        row.createCell(15).setCellValue(getDecrypt(salaryOne.getTongxin()));
        row.createCell(16).setCellValue(getDecrypt(salaryOne.getGuginggangwei()));
        row.createCell(17).setCellValue(getDecrypt(salaryOne.getFudonggangwei()));
        row.createCell(18).setCellValue(getDecrypt(salaryOne.getWuyefei()));
        row.createCell(19).setCellValue(getDecrypt(salaryOne.getXiangmujixiao()));
        row.createCell(20).setCellValue(getDecrypt(salaryOne.getBufa()));
        row.createCell(21).setCellValue(getDecrypt(salaryOne.getYingfaheji()));
        row.createCell(22).setCellValue(getDecrypt(salaryOne.getQita()));
        row.createCell(23).setCellValue(getDecrypt(salaryOne.getGeshui()));
        row.createCell(24).setCellValue(getDecrypt(salaryOne.getGongjijin()));
        row.createCell(25).setCellValue(getDecrypt(salaryOne.getYanglao()));
        row.createCell(26).setCellValue(getDecrypt(salaryOne.getShiye()));
        
        row.createCell(27).setCellValue(getDecrypt(salaryOne.getBukougong()));
        row.createCell(28).setCellValue(getDecrypt(salaryOne.getKougonghui()));
        row.createCell(29).setCellValue(getDecrypt(salaryOne.getJiaban()));
        
        row.createCell(30).setCellValue(getDecrypt(salaryOne.getYukou()));
        row.createCell(31).setCellValue(getDecrypt(salaryOne.getShifaheji()));
        
        SalaryTwo record2 = new SalaryTwo();
        record2.setName(record.getName());
        record2.setDepartment(record.getDepartment());
        record2.setNianyue(record.getNianyue());
        List<SalaryTwo> list2 = salaryTwoMapper.selectTwoList(record2);
        SalaryTwo salaryTwo = new SalaryTwo();
        if(list2!=null && list2.size()>0){
        	salaryTwo = list2.get(0);
        }
        
        row.createCell(32).setCellValue(getDecrypt(salaryTwo.getZixun()));
        row.createCell(33).setCellValue(getDecrypt(salaryTwo.getPingshen()));
        row.createCell(34).setCellValue(getDecrypt(salaryTwo.getShengao()));
        
        row.createCell(35).setCellValue(getDecrypt(salaryTwo.getBianji()));
        row.createCell(36).setCellValue(getDecrypt(salaryTwo.getZhiban()));
        
        row.createCell(37).setCellValue(getDecrypt(salaryTwo.getQita()));
        
        row.createCell(38).setCellValue(getDecrypt(salaryTwo.getJiangke()));
        row.createCell(39).setCellValue(getDecrypt(salaryTwo.getChuche()));
        row.createCell(40).setCellValue(getDecrypt(salaryTwo.getYingfaheji()));
        
        row.createCell(41).setCellValue(getDecrypt(salaryTwo.getGeshui()));
        row.createCell(42).setCellValue(getDecrypt(salaryTwo.getShifaheji()));
        
        row.createCell(43).setCellValue(getDecrypt(salaryOne.getBukougong()));
        row.createCell(37).setCellValue(getDecrypt(salaryOne.getKougonghui()));
        
        if(salaryOne.getCount()!=null){
        	 row.createCell(43).setCellValue(salaryOne.getCount());
        }else{
        	row.createCell(43).setCellValue(salaryTwo.getCount());
        }
       
        String sb1 = getDecrypt(salaryOne.getShifaheji());
		String sb2 = getDecrypt(salaryTwo.getShifaheji());
		if(getDecrypt(salaryOne.getShifaheji())==null ||getDecrypt(salaryOne.getShifaheji()).equals("")){
			sb1 = "0";
		}
		if(getDecrypt(salaryTwo.getShifaheji())==null ||getDecrypt(salaryTwo.getShifaheji()).equals("")){
			sb2 = "0";
		}
		BigDecimal b1 = new BigDecimal(sb1);  
        BigDecimal b2 = new BigDecimal(sb2);
        
        row.createCell(44).setCellValue(b1.add(b2).toString());
        
        return wb;    
    }    
	
	/**
	 * 获取解密后的字符串
	 * @param sr
	 * @return
	 */
	private String getDecrypt(String sr){
		if(sr==null || sr.equals("")){
			return sr;
		}
		String str = sr;
		try {
			DesUtils des = new DesUtils();
			str = des.decrypt(sr);
		} catch (Exception c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		return str;
	}

}
