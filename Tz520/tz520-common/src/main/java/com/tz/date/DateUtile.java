package com.tz.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;


/**
 * 时间操作类 simple introduction
 *
 * <p>
 * detailed comment
 * 
 * @author qinjuncai 2016年12月27日
 * @see
 * @since 1.0
 */
public class DateUtile {

	
	
	/**
	 * 根据时间向前或向后推amount天， 向前推amount为负数，向后退amount为正数
	 * 
	 * @param date
	 * @param amount
	 *            需要推的天数
	 * @return
	 */
	public static Date pushDays(Date date, int amount) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, amount);
			return calendar.getTime();
			/*
			 * Date result = calendar.getTime();
			 * 
			 * calendar.clear(); calendar.setTime(result);
			 */

			/*
			 * DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 * return dateFormat.format(calendar.getTime());
			 */

		}
		return null;
	}
	/**
	 * 根据开始时间推算本周结束时间
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date pushDaysByStartDate(Date date,int endDay) {
		if (date == null) {
			return null;
		}
		Date enDate  = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//date中的“日”
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		//当前日期是一周中的星期几(从周一开始)
		int k = new Integer(calendar.get(Calendar.DAY_OF_WEEK))+1;
		//结束时间的“日”
		int countEndDay = day;
		switch (k) {
		case 1:
			countEndDay = (day+6>endDay)?day:(day+6);
			break;
		case 2:
			countEndDay = (day+5>endDay)?day:(day+5);
			break;
		case 3:
			countEndDay = (day+4>endDay)?day:(day+4);
			break;
		case 4:
			countEndDay = (day+3>endDay)?day:(day+3);
			break;
		case 5:
			countEndDay = (day+2>endDay)?day:(day+2);
			break;
		case 6:
			countEndDay = (day+1>endDay)?day:(day+1);
			break;
		case 7:
			countEndDay = day;
			break;

		default:
			countEndDay = day;
			break;
		}
		int year = calendar.get(Calendar.YEAR); // 年
		int month = calendar.get(Calendar.MONTH) + 1; // 月
		calendar.clear();
		calendar.set(year, month, countEndDay);
		return calendar.getTime();
		
	}

	/**
	 * 
	 * @Description 计算两个时间之差，以天为单位
	 * @param min 较小的
	 * @param max 较大的
	 * @return
	 */
	public static String dayCalculation(Date min,Date max){
	    if(min == null || max ==null){
            return "";
        }
	    try {
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        min=sdf.parse(sdf.format(min));  
	        max=sdf.parse(sdf.format(max));  
	        
            Calendar cal = Calendar.getInstance();    
            cal.setTime(min);    
            long minday = cal.getTimeInMillis();    
            
            cal.setTime(max);    
            long maxday = cal.getTimeInMillis(); 
            
            long betweenTime = 0 ;
            if(maxday-minday<=0){
                return "";
            }else{
                betweenTime = maxday-minday;
            }
            long between_days=betweenTime/(1000*3600*24);  
                    
            return Long.toString(between_days);  
        } catch (Exception e) {
        }
	    return "";
	}
	
	  /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate){
    	if(smdate == null || bdate ==null){
    		return 0;
    	}
    	try {
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        smdate=sdf.parse(sdf.format(smdate));  
	        bdate=sdf.parse(sdf.format(bdate));  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();      
	        long betweenTime = 0 ;
	        if(time2-time1<0){
	        	betweenTime = time1-time2;
	        }else{
	        	betweenTime = time2-time1;
	        }
	        long between_days=betweenTime/(1000*3600*24);  
    	            
    	    return Integer.parseInt(String.valueOf(between_days));     
		} catch (Exception e) {
			
		}
          return 0;   
    }    
    
    /**
     * 比较2个string类型的时间大小，返回大的
     * @param date1
     * @param date2
     * @return
     */
    public static String compareStringDataReturnBig(String date1 , String date2){
    	if(StringUtils.isEmpty(date1)&&StringUtils.isEmpty(date2)){
    		return "";
    	}
    	if(StringUtils.isEmpty(date1)){
    		return date2;
    	}
    	if(StringUtils.isEmpty(date2)){
    		return date1;
    	}
    	
    	//比较年
    	int year1 = getYearByStringDate(date1);
    	int year2 = getYearByStringDate(date2);
    	if(year1>year2){
    		return date1;
    	}
    	if(year2>year1){
    		return date2;
    	}
    	
    	//年相等比较月
    	int month1 = getMonthByStringDate(date1);
    	int month2 = getMonthByStringDate(date2);
    	if(month1>month2){
    		return date1;
    	}
    	if(month2>month1){
    		return date2;
    	}
    	
    	//年月都相等比较日
    	int day1 = getDayByStringDate(date1);
    	int day2 = getDayByStringDate(date2);
    	if(day1>day2){
    		return date1;
    	}else if(day2>day1){
    		return date2;
    	}else if(day1==day2){
    		return date1;
    	}  	
    	
    	return "";
    }
    
    /**
     * 根据String类型日期返回日期中的月
     * @param date
     * @return
     */
    public static int getMonthByStringDate(String date){
    	try {
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		Date dateInpurt = dateFormat.parse(date);
    		Calendar calendar = new GregorianCalendar();
    		calendar.setTime(dateInpurt);
    		int month = calendar.get(Calendar.MONTH) + 1; // 月
    		return month;
		} catch (Exception e) {
			return 0;
		}
    }
    /**
     * 根据String类型日期返回日期中的日
     * @param date
     * @return
     */
    public static int getDayByStringDate(String date){
    	try {
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		Date dateInpurt = dateFormat.parse(date);
    		Calendar calendar = new GregorianCalendar();
    		calendar.setTime(dateInpurt);
    		int day = calendar.get(Calendar.DAY_OF_MONTH); // 日
    		return day;
    	} catch (Exception e) {
    		return 0;
    	}
    }
    /**
     * 根据String类型日期返回日期中的年
     * @param date
     * @return
     */
    public static int getYearByStringDate(String date){
    	try {
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		Date dateInpurt = dateFormat.parse(date);
    		Calendar calendar = new GregorianCalendar();
    		calendar.setTime(dateInpurt);
    		int year = calendar.get(Calendar.YEAR); // 年
    		return year;
    	} catch (Exception e) {
    		return 0;
    	}
    }
    
    /** 
    *字符串的日期格式的计算 
    */
    public static int daysBetween(String smdate,String bdate){  
    	try {
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
            Calendar cal = Calendar.getInstance();    
            cal.setTime(sdf.parse(smdate));    
            long time1 = cal.getTimeInMillis();                 
            cal.setTime(sdf.parse(bdate));    
            long time2 = cal.getTimeInMillis();         
            long between_days=(time2-time1)/(1000*3600*24);  
                
           return Integer.parseInt(String.valueOf(between_days)); 
		} catch (Exception e) {
		}
         return 0;   
    }  
    /**
     * 获取时间 零点零分零秒 
     * @param amount 1 推迟或者以后的天数
     * @return
     */
    public static Date zero (int amount){
	  Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.add(Calendar.DATE, amount);
      Date zero = calendar.getTime();
      return zero;
    }
    /** 
     * 获取现在时间 
     *  
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss 
     */  
    public static String getStringDate( Date currentTime) {   
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String dateString = formatter.format(currentTime);  
        return dateString;  
    } 
    /** 
     * 获取现在时间 
     *  
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss 
     */  
    public static Date StringToDate(String time) {   
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			return sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    } 
    /**
     * 根据当前时间往后推迟一年
     * @param currentTime
     * @return
     */
    public static Date getPutOffYear( Date currentTime) {   
    	Calendar calendar = new GregorianCalendar(); 
    	calendar.setTime(currentTime); 
    	calendar.add(calendar.YEAR, 1);//把日期往后增加一年.整数往后推,负数往前移动
    	calendar.add(calendar.DAY_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
    	calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
    	calendar.add(calendar.WEEK_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
    	currentTime=calendar.getTime();   //这个时间就是日期往后推一天的结果 
    	return currentTime;
    } 
	public static void main(String[] args) throws Exception {
        /*List<DateResult> list = DateUtile.getWeekDayByDate("2017-3-31");
		for (DateResult date : list) {
			System.out.println(date.getNum() + "开始时间" + date.getStartWeekDay() + "结束时间" + date.getEndWeekDay());
		}*/
	    String st1 = "2016-03-06";
	    String st2="2017-3-5";
		Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(st1);
		Date d2=new SimpleDateFormat("yyyy-MM-dd").parse(st2);
		/*System.out.println(pushDays(new Date(),0));*/ 
          System.out.println(zero(1));
          System.out.println(StringToDate("2014-11-27 15:45:57"));
	}
}

