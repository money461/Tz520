package com.tz.res;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
/**
 * 返回接口封装 类
 * @author menglin
 *	2017年9月21日14:00:08
 */
public class AppMsgResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2857028881989611096L;
	
	private Object flag;
	private Object msg;
	private Object data;
	
	private AppMsgResult(){
		
	}
	private static AppMsgResult mr = null;
	private static AppMsgResult getInstant(){
		if(null == mr){
			mr = new AppMsgResult();
		}
		return mr;
	}
	
	

	public static AppMsgResult result(Object flag, Object msg, Object data){
		AppMsgResult mResult = AppMsgResult.getInstant();
		mResult.setFlag(flag);
		mResult.setMsg(msg);
		mResult.setData(data);
		return mResult;
	}
	
	public static AppMsgResult nodata(Object flag, Object msg){
		AppMsgResult mResult = AppMsgResult.getInstant();
		mResult.setFlag(flag);
		mResult.setMsg(msg);
		mResult.setData(null);
		return mResult;
	}
	
	public Object getFlag() {
		return flag;
	}


	public void setFlag(Object flag) {
		this.flag = flag;
	}


	public Object getMsg() {
		return msg;
	}


	public void setMsg(Object msg) {
		this.msg = msg;
	}


	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static void getLogger(Class t,String msg){
		Logger logger = Logger.getLogger(t);
		logger.info(msg);
	}
	
	@Override
	public String toString() {
		return "MsgResult [flag=" + flag + ", msg=" + msg + ", data=" + data
				+ "]";
	}
	
	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(sdf.format(new Date(1472096788000l)));

	}

}
