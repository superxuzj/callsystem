package com.superx.callorder.service;

import java.math.BigDecimal;
import java.util.List;

import jxl.format.Font;

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
			"项目绩效","补发","应发工资合计","其他","个税","公积金","养老保险","失业保险","预扣养老金","实发工资合计",
			"编辑费","值班费","讲课费","出车补助","应发费用合计","费用个税","实发费用合计","发放次数","合计"};  
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
        row.createCell(2).setCellValue(salaryOne.getXinji());
        row.createCell(3).setCellValue(salaryOne.getGangwei());
        row.createCell(4).setCellValue(salaryOne.getGonggai());
        row.createCell(5).setCellValue(salaryOne.getJintie());
        row.createCell(6).setCellValue(salaryOne.getFudong());
        row.createCell(7).setCellValue(salaryOne.getZhibu());
        row.createCell(8).setCellValue(salaryOne.getDiqu());
        row.createCell(9).setCellValue(salaryOne.getJiaobu());
        row.createCell(10).setCellValue(salaryOne.getWeinaru());
        row.createCell(11).setCellValue(salaryOne.getTizu());
        row.createCell(12).setCellValue(salaryOne.getHuiyingdu());
        row.createCell(13).setCellValue(salaryOne.getCailanzi());
        row.createCell(14).setCellValue(salaryOne.getShenghuo());
        row.createCell(15).setCellValue(salaryOne.getTongxin());
        row.createCell(16).setCellValue(salaryOne.getGuginggangwei());
        row.createCell(17).setCellValue(salaryOne.getFudonggangwei());
        row.createCell(18).setCellValue(salaryOne.getWuyefei());
        row.createCell(19).setCellValue(salaryOne.getXiangmujixiao());
        row.createCell(20).setCellValue(salaryOne.getBufa());
        row.createCell(21).setCellValue(salaryOne.getYingfaheji());
        row.createCell(22).setCellValue(salaryOne.getQita());
        row.createCell(23).setCellValue(salaryOne.getGeshui());
        row.createCell(24).setCellValue(salaryOne.getGongjijin());
        row.createCell(25).setCellValue(salaryOne.getYanglao());
        row.createCell(26).setCellValue(salaryOne.getShiye());
        row.createCell(27).setCellValue(salaryOne.getYukou());
        row.createCell(28).setCellValue(salaryOne.getShifaheji());
        
        SalaryTwo record2 = new SalaryTwo();
        record2.setName(record.getName());
        record2.setDepartment(record.getDepartment());
        record2.setNianyue(record.getNianyue());
        List<SalaryTwo> list2 = salaryTwoMapper.selectTwoList(record2);
        SalaryTwo salaryTwo = new SalaryTwo();
        if(list2!=null && list2.size()>0){
        	salaryTwo = list2.get(0);
        }
        row.createCell(29).setCellValue(salaryTwo.getBianji());
        row.createCell(30).setCellValue(salaryTwo.getZhiban());
        row.createCell(31).setCellValue(salaryTwo.getJiangke());
        row.createCell(32).setCellValue(salaryTwo.getChuche());
        row.createCell(33).setCellValue(salaryTwo.getYingfaheji());
        row.createCell(34).setCellValue(salaryTwo.getGeshui());
        row.createCell(35).setCellValue(salaryTwo.getShifaheji());
        if(salaryOne.getCount()!=null){
        	 row.createCell(36).setCellValue(salaryOne.getCount());
        }else{
        	row.createCell(36).setCellValue(salaryTwo.getCount());
        }
       
        BigDecimal b1 = new BigDecimal(salaryOne.getShifaheji());  
        BigDecimal b2 = new BigDecimal(salaryTwo.getShifaheji());
        row.createCell(37).setCellValue(b1.add(b2).toString());
        
        return wb;    
    }    

}
