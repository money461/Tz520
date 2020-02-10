package com.tz.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.pagehelper.util.StringUtil;
/**
 * 短信接口
 * @author menglin
 * 2017年9月21日13:59:11
 */
public class ALiDaYuUtil {
	/**
	 * 获取IAcsClient对象
	 * 
	 * @return
	 * @throws ClientException
	 */
	private static IAcsClient initClient() throws ClientException {
	    System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	    System.setProperty("sun.net.client.defaultReadTimeout", "10000");
	    // 初始化ascClient需要的几个参数
	    final String product = "Dysmsapi";// 短信API产品名称
	    final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名
	    // 秘钥key和secret
	    //final String appkey = "LTAIHTphWYTRJw3W";
	    //final String appSecret = "kVfOmAsxLCpUsvaR3qHXnkKU4b0Uzd";
	    //香港蓝博
	 /*   final String appkey = "LTAIiQHbH8CBBeWB";
	    final String appSecret = "fGdequh6OMXvJjBjbNnLvzZaX6nzgK";*/
	    //执爱
	    final String appkey = "LTAIu6lLbQ7Iemvl";
	    final String appSecret = "cpSYkNuuZNWZT289pAK6TZAIO8DzFz";
	    
	    
	    // 初始化ascClient,暂时不支持多region
	    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", appkey, appSecret);
	    DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
	    IAcsClient acsClient = new DefaultAcsClient(profile);
	    return acsClient;
	}
	/**
	 * 获取SMS_80725002短信模板对应的请求
	 * 
	 * @return
	 */
	private static SendSmsRequest getSMS_Message(String signName,String templateCode,String PhoneNumber,String code) {
	     //组装请求对象
	     SendSmsRequest request = new SendSmsRequest();
	     //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
	     request.setPhoneNumbers(PhoneNumber);
	     //必填:短信签名-可在短信控制台中找到
	     //request.setSignName("阿里云短信测试专用");
	     request.setSignName(signName);
	     
	     //必填:短信模板-可在短信控制台中找到
	     //request.setTemplateCode("SMS_78915320");
	     request.setTemplateCode(templateCode);
	     //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	     request.setTemplateParam("{\"code\":'"+code+"'}");
	     //request.setTemplateParam("{\"code\":\"123\"}");
	     //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
	     request.setOutId("yourOutId");
	     
		return request;
	 }
	/**
	 * @param templateCode 短信模板code
	 * @throws ClientException
	 * 
	 */
	public static String sendMessage(String signName,String templateCode,String PhoneNumber,String code) throws ClientException   {
		
	    // 初始化client对象
	    IAcsClient client = initClient();
	    // 短信模板请求对象
	    SendSmsRequest request = null;
	    // 根据短信模板code来获取不同的短信模板请求对象
	    if(StringUtil.isEmpty(templateCode))
	    	templateCode = "SMS_80725002";
	    request = getSMS_Message(signName,templateCode,PhoneNumber,code);
	    // 发送短信
	    SendSmsResponse response = client.getAcsResponse(request);
	    // 打印短信的消息
	    System.out.println("_____________发送短信收到的响应信息_______________");
	    System.out.println("请求的ID:" + response.getRequestId());
	    System.out.println("code:" + code);
	    System.out.println("请求的状态码:" + response.getCode());
	    System.out.println("请求的状态码描述:" + response.getMessage());
	    System.out.println("请求的回执ID:" + response.getBizId());
	    return response.getCode();
	
	}
	public static void main(String[] args)  {
		
		try {
		/*	sendMessage("执爱","SMS_105250418","18113018416","0888");*/
			sendMessage("执爱","SMS_105250417","18113018416","0888");
			
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
