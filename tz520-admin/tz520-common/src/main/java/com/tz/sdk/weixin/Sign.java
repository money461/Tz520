package com.tz.sdk.weixin;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;  

public class Sign {
/*    public static void main(String[] args) {
    	 String jsapi_ticket = "jsapi_ticket";

         // 注意 URL 一定要动态获取，不能 hardcode
         String url = "http://example.com";
         Map<String, String> ret = sign(jsapi_ticket, url);
         for (Map.Entry entry : ret.entrySet()) {
             System.out.println(entry.getKey() + ", " + entry.getValue());
         }
    };*/
	
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    //获取公众号的access_token
    public static String getAccess_token(){
    	String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		url = url.replace("APPID",WeChatPayConfig.gzh_appid);
		url = url.replace("APPSECRET",WeChatPayConfig.gzh_AppSecret);
		JSONObject object = JSONObject.fromObject(WXPayUtil.httpRequest(url, "GET", null));
	    String access_token = object.getString("access_token");
		System.out.println(access_token.toString());
		System.out.println(access_token.toString());
		return  access_token.toString();
    }
    //通过access_token获取ticket
    public static String getJsapiTicket(String access_token){
        String jsapiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        jsapiUrl = jsapiUrl.replace("ACCESS_TOKEN", access_token);
        JSONObject object = JSONObject.fromObject(WXPayUtil.httpRequest(jsapiUrl, "GET", null));
        String jsapiTicket = object.getString("ticket");
        System.out.println(jsapiTicket.toString());	
        return jsapiTicket.toString();
    }
    //通过code获取用户对应公众号的 openId(微信公众号支付必须要用户的openId)
    public static String getOpenId(String code){
    	String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		url = url.replace("APPID",WeChatPayConfig.gzh_appid);
		url = url.replace("SECRET",WeChatPayConfig.gzh_AppSecret);
		url = url.replace("CODE",code);
        JSONObject object = JSONObject.fromObject(WXPayUtil.httpRequest(url, "GET", null));
        String openid = object.getString("openid");
        System.out.println(openid.toString());	
        return openid.toString();
    }
    public static Map<String, String> createShareSign(String jsapiTicket,String access_token,String url) throws Exception{
    	 Map<String, String> ret = new HashMap<String, String>();
	     //首先获得一个accessToken
	     /* String token = WeixinAccessTokenUtil.getAccessToken(accountId);*///自己写的获取缓存access_token,大家可按自己的方式获取accessToken
	     //根据token获取jsapi
	     /*String jsapiTicket = getJsapiTicket(access_token);*/
	     System.out.println("返回的jsapiticket::"+jsapiTicket);
	     String nonce_str = create_nonce_str();
	     String timestamp = create_timestamp();
	     String string1="";
	     String signature="";
         //System.out.println("SHA1签名 :"+str);
	     string1 = "jsapi_ticket=" + jsapiTicket +
                    "&noncestr=" + nonce_str +
                    "&timestamp=" + timestamp +
                    "&url=" + url;
          System.out.println(string1);
          try
          {
              MessageDigest crypt = MessageDigest.getInstance("SHA-1");
              crypt.reset();
              crypt.update(string1.getBytes("UTF-8"));
              signature = byteToHex(crypt.digest());
          }
          catch (NoSuchAlgorithmException e)
          {
              e.printStackTrace();
          }
          catch (UnsupportedEncodingException e)
          {
              e.printStackTrace();
          }
          ret.put("url", url);
          ret.put("jsapi_ticket", jsapiTicket);
          ret.put("nonceStr", nonce_str);
          ret.put("timestamp", timestamp);
          ret.put("signature", signature);
          System.out.println("签名1:"+signature);
         // System.out.println("签名2:"+  SHA1Util.encode(string1) );
     return ret;
     
    }
    
    public static void main(String[] args) {
    	  String access_token = getAccess_token();
    	  getJsapiTicket(access_token);
    	/*  try {
			createShareSign(access_token,"www.baidu.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	  
	}
}
