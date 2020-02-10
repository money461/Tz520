package com.tz.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.util.StringUtil;
import com.tz.res.AppMsgResult;

import net.sf.json.JSONObject;

public class JuheDemo {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
 
    //配置您申请的KEY
    public static final String APPKEY ="b27bd98b2c2458373e396c6a2287207c";
 
    //1.常用快递查询API
    public static AppMsgResult getRequest1(String com,String no){
    	AppMsgResult msgResult = null;
        String result =null;
        String url ="http://v.juhe.cn/exp/index";//请求接口地址
        if(StringUtil.isNotEmpty(com)){
        	if(StringUtil.isNotEmpty(com)){
        	     Map params = new HashMap();//请求参数
                 params.put("com",com);//需要查询的快递公司编号
                 params.put("no",no);//需要查询的订单号
                 params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
                 params.put("dtype","");//返回数据的格式,xml或json，默认json
      
             try {
                 result =net(url, params, "GET");
                 JSONObject object = JSONObject.fromObject(result);
                 if(object.getInt("error_code")==0){
                 	msgResult = AppMsgResult.result(200, "", object.get("result"));
                     System.out.println(object.get("result"));
                     System.out.println(object);
                 }else{
                 	msgResult = AppMsgResult.result(566, "", object.get("reason"));
                     System.out.println(object.get("error_code")+":"+object.get("reason"));
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
            }else{
            	msgResult = AppMsgResult.result(561, "快递物流号不能为空！", null);
            }
        }else{
        	msgResult = AppMsgResult.result(562, "快递简码不能为空！", null);
        }
        return msgResult;
    }
 
    //2.快递公司编号对照表
    public static void getRequest2(){
        String result =null;
        String url ="http://v.juhe.cn/exp/com";//请求接口地址
        Map params = new HashMap();//请求参数
 
        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
 
 
    public static void main(String[] args) {
    	System.out.println(JuheDemo.getRequest1("sto","3344903435562"));
    }
 
    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}