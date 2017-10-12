package com.superx.callorder.controller.manage;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecimalFormat    df   = new DecimalFormat("######0.00");   

		double d1 = 3.23456;
		double d2 = 0.0;
		double d3 = 2.0;
		String a = df.format(d1);
		String b = df.format(d2);
		String c = df.format(d3);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		BigDecimal b1 = new BigDecimal(a);  
        BigDecimal b2 = new BigDecimal(b);  
        
        System.out.println(b1.add(b2).doubleValue());
		
	}

}
