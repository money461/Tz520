package com.tz.id;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 各种id生成策略
 * <p>Title: IDUtils.java</p>
 * <p>Package com.irzhd.weiye.util </p>
 * <p>Description:  </p>
 * @author   menglin  
 * @date     2016年7月14日 下午4:57:33 
 * @version  V1.0
 */
public class IDUtils {
	
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
	/**
	 * 获取加密的附加值
	 * @return
	 */
	public static String getCode() {
		Random random = new Random();
		//附加值长度为6~16
		int length=16-random.nextInt(10);
		return	RandomStringUtils.randomAlphanumeric(length);
	}

	/**
	 * 图片名生成
	 */
	public static String genImageName() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = millis + String.format("%03d", end3);
		
		return str;
	}
	
	/**
	 * id生成32位
	 */
	public static String genId() {
		UUID uuid  = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	/**
	 * id生成 15位数字
	 */
	public static String genId15() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		//如果不足两位前面补0
		return millis + String.format("%02d", end2);
	}
	/**
	 * 生成6位手机验证码
	 * @return
	 */
	public static String phoneCode() {
		String str="0123456789";
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<6;i++)
		{
		char ch=str.charAt(new Random().nextInt(str.length()));
		sb.append(ch);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	/**
	 * 手机验证码
	 * @param length 1(1) - 10(2) - 100(3) - 1000(4)...
	 * @return
	 */
	public static int phoneCodeByLength(int length) {
		return (int)((Math.random()*10)*length);
	}
	public static void main(String[] args) {
		System.out.println(genId());
	}
}
