package com.superx.callorder.util;

import java.net.URL;

import org.apache.axis.client.Service;

import com.roya.mas.common.PathUtil;
import com.roya.mas.platform.business.SiMockStub;
import com.roya.mas.platform.schema.sms.MessageFormat;
import com.roya.mas.platform.schema.sms.SendMethodType;
import com.roya.mas.platform.schema.sms.SendSmsRequest;
import com.roya.mas.platform.schema.sms.SendSmsResponse;
import com.roya.mas.test.TestBase;
import com.roya.mas.test.sms.SendSms;

public class Test extends TestBase{

	public static void main(String[] args) throws Exception {
		Test test = new Test();
		/*String globalFile="";
		String configFIle="";
		if(args == null || args.length == 0){
			globalFile = PathUtil.getRootPath()+"../test/global.properties";
			configFIle = PathUtil.getRootPath()+"../test/sms/SendSms.properties";
		}
		else{
			globalFile = args[0];
			configFIle = args[1];
		}
		test.init(globalFile, configFIle);*/
		test.sendSms();
	}

	private String sendSms() {

		String requestIdentifier = "";

		// TODO �Զ���ɷ������
		URL url;
		try {
			url = new URL("http://10.9.1.180/services/cmcc_mas_wbs");
			Service service = new Service();
			SiMockStub stub = new SiMockStub(url, service);

			SendSmsRequest s = new SendSmsRequest();
			// Ӧ��ID
			s.setApplicationID("P000000000000072");
			// �Ƿ���Ҫ״̬����
			s.setDeliveryResultRequest(true);
			// )չ��
			s.setExtendCode("123456");
			// ���͵���Ϣ
			String message = "测试短信！";
			s.setMessage(message);
			//s.setMessage(new String(message.getBytes("ISO-8859-1"),"utf-8"));
			//System.out.println(new String(message.getBytes("ISO-8859-1"),"utf-8"));
			// ���͵���Ϣ����
			s.setMessageFormat(MessageFormat.fromValue("GB2312"));
			// �Ƿ񳤶���
			s.setSendMethod(SendMethodType.fromValue("Normal"));
			// ���ŷ��͵�ַ
			String[] a = "tel:18611453795;tel:h23123".split(";");
			int leng= a.length;
			org.apache.axis.types.URI [] ary=new org.apache.axis.types.URI[leng];
			for(int i=0;i<leng;i++){
				org.apache.axis.types.URI temp=new org.apache.axis.types.URI(a[i]);
				ary[i]=temp;
			}
			s.setDestinationAddresses(ary);

			// ���Ͷ���Ϣ
			SendSmsResponse rep = stub.sendSms(s);
			requestIdentifier = rep.getRequestIdentifier();
		} catch (Exception e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		}
		System.out.println("requestIdentifier="+requestIdentifier);
		return requestIdentifier;
	}
}
