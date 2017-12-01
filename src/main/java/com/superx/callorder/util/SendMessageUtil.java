package com.superx.callorder.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.client.Service;
import org.apache.axis.types.URI.MalformedURIException;

import com.roya.mas.platform.business.SiMockStub;
import com.roya.mas.platform.schema.sms.MessageFormat;
import com.roya.mas.platform.schema.sms.SendMethodType;
import com.roya.mas.platform.schema.sms.SendSmsRequest;
import com.roya.mas.platform.schema.sms.SendSmsResponse;

public class SendMessageUtil {

	////"tel:18611453795;tel:18611453795;tel:18611453795tel:18611453795;tel:18611453795"
	//根据tel 发送短信
	/**
	 * 根据tels 发送短信
	 * @param tels 短信号码tel:18611453795;tel:18611453795;tel:18611453795tel
	 * @param message 信息内容
	 * @return
	 * @throws MalformedURIException 
	 * @throws MalformedURLException 
	 * @throws RemoteException 
	 */
	public static String sendMessage(String tels,String message) {
		String requestIdentifier = "";
		URL url;
		
		try {
			url = new URL("http://10.9.1.180/services/cmcc_mas_wbs");
			Service service = new Service();
			SiMockStub stub = new SiMockStub(url, service);

			SendSmsRequest s = new SendSmsRequest();
			s.setApplicationID("P000000000000072");
			s.setDeliveryResultRequest(true);
			s.setExtendCode("123456");
			s.setMessage(message);
			s.setMessageFormat(MessageFormat.fromValue("GB2312"));
			s.setSendMethod(SendMethodType.fromValue("Normal"));
			//"tel:18611453795;tel:18611453795;tel:18611453795tel:18611453795;tel:18611453795"
			String[] a = tels.split(";");
			int leng= a.length;
			org.apache.axis.types.URI [] ary=new org.apache.axis.types.URI[leng];
			for(int i=0;i<leng;i++){
				org.apache.axis.types.URI temp=new org.apache.axis.types.URI(a[i]);
				ary[i]=temp;
			}
			s.setDestinationAddresses(ary);
			SendSmsResponse rep = stub.sendSms(s);
			requestIdentifier = rep.getRequestIdentifier();
		} catch (MalformedURIException c) {
			System.out.println("MalformedURIException");
			System.out.println(c.getMessage());
			c.printStackTrace();
		} catch (MalformedURLException c) {
			System.out.println("MalformedURLException");
			System.out.println(c.getMessage());
			c.printStackTrace();
		} catch (RemoteException c) {
			System.out.println("RemoteException");
			System.out.println(c.getMessage());
			c.printStackTrace();
		}
			
		System.out.println("requestIdentifier="+requestIdentifier);
		return requestIdentifier;
	}
	
	public static void main(String[] args) {
		String retu = SendMessageUtil.sendMessage("tel:", "22");
		System.out.println(retu);
	}
	
	/**
参考 sendSms
	 * #应用ID或插件的ID
ApplicationID=P000000000000072
#短消息要被发送到的地址。群发短消息的最大数量为254
Address=tel:15210419293
#指由该应用填写的内部扩展号码。MAS服务器需自动补充为此业务分配的长服务号码
ExtendCode=123456
#在短消息中发送的文本
Message=财务预约系统测试,发短信时间：
#消息编码类型
#ASCII	ASCII字符。
#UCS2	USC2格式的UniCode字符。
#GB18030	GB18030格式的中文字符。
#GB2312	GB2312格式的中文字符。
#Binary	二进制短信，用十六进制字符串。
MessageFormat=GB2312
#发送消息选项
#Normal	普通短信
#Instant	普通短信立即显示
#Long	长短信
#Structured	长度小于160字节，但UDHI需置为1
SendMethod=Normal
#指示是否需要网络侧返回递交状态报告。若无，则不返回。True表示需要网络侧返回递交状态报告，false表示不需要网络侧返回递交状态报告
DeliveryResultRequest=true
	 */
}
