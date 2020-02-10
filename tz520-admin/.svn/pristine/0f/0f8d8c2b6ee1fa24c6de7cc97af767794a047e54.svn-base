package com.tz.excel;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.tz.pojo.TzOrderItem;
import com.tz.pojo.vo.TzOrderVo;
import com.tz.pojo.vo.UserLoveConsumptionDetailsVo;
import com.tz.pojo.vo.UserLoveShowVo;
import com.tz.pojo.vo.UserLoveVo;



/**
 * 蒙林
 * 2017年08月5日11:38:25
 * @author win10
 *
 */
public class ExcelUtil {

	private static int MAX = 60000 ;
	/**
	 * 读取excel
	 * @param file
	 * @return
	 */
	public static LinkedList<List<Object>> readExcel(String ce1,String ce2,int num,MultipartFile file){
		Iterator<Row> ros =null ;
//		List<Row> list = new ArrayList<Row>() ;
		LinkedList<List<Object>> linkedList = new LinkedList<>();
		try {
			InputStream is = file.getInputStream() ;
//			String filename = file.getOriginalFilename() ;
			Workbook wb = null ;
				//07以上的excel
				wb = new XSSFWorkbook(is) ;
//			else if(filename.endsWith(".xls") || filename.endsWith(".XLS")){
//				//07以下版本的excel
//				wb = new HSSFWorkbook(is) ;
//			}
			//循环工作表sheet
			
//			for(int i =0; i<wb.getNumberOfSheets();i++){
//				Sheet sheet = wb.getSheetAt(0) ;
				//循环行
//				for(int j=0 ; j<sheet.getLastRowNum();j++){
//					list.add(sheet.getRow(j)) ;
//				}
//			}
			Sheet sheet = wb.getSheetAt(0) ;
			Row row = sheet.getRow(1) ;
			boolean flag = true ;
			for(int i=0 ;i <num;i++){
				if(row.getCell(i)==null){
					flag = false ;
					break ;
				}
			}
			String cell1 = String.valueOf(row.getCell(0)).trim() ;
			String cell2 = String.valueOf(row.getCell(1)).trim() ;
			if(ce1.equals(cell1) && ce2.equals(cell2) && flag){
				
				ros = sheet.iterator() ;
			}else{
				ros = null ;
			}
			boolean flag2 = false;
			int count  = 0;
			while (ros.hasNext()) {
				Row row2 = (Row) ros.next();
				if(flag2 && count == 2){
					List<Object> list = new ArrayList<>();
					for(int i=0 ;i <num;i++){
						list.add(row2.getCell(i));
					}
					linkedList.add(list);
				}	
				else{
					count++;
					flag2 = true;	
				}
	        }  
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(linkedList);
		return linkedList ;
	}
	
	// 判断文件是否为excel文件
	public static boolean isExcel(String name){
		boolean flag = false ;
		if(name.contains(".")){
			name = name.substring(name.lastIndexOf(".")) ;
			return ".xls".equalsIgnoreCase(name) || ".xlsx".equalsIgnoreCase(name);
		}
		return flag ;
	}
	
	/**
	 * 导出错误数据
	 * @param list
	 * @param filed
	 * @param titile
	 * @param fileName
	 * @return
	 */
	public static String exportExcel(List<?> list,String[] filed,String[] title,String fileName,String attend,int redStr,HttpServletRequest req){
		if(list!=null && list.size()>0){
			try {
				//创建工作薄
				 XSSFWorkbook wb = new XSSFWorkbook() ;
				Sheet sheet = wb.createSheet();
				

//				//创建注意行
				Row row0 = sheet.createRow(0) ;
				Cell cell0 = row0.createCell(0) ;
				cell0.setCellValue(attend);
				cell0.setCellStyle(setAttentionStyle(wb));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, title.length)) ;
				row0.setHeight((short)(2*row0.getHeight() ));
				
				//创建标题行
				Row row = sheet.createRow(1) ;
				for(int i = 0 ; i< title.length;i++){
					Cell cell = row.createCell(i)  ;
					cell.setCellValue(title[i]);
					if(i<redStr)
					cell.setCellStyle(setFontColor(wb));
					
				}
				
//				for(int k= 1 ;k<title.length;k++){
//					System.out.println(k+"=================");
//					sheet.autoSizeColumn(k,true);
//				}
				for(int i=0;i<list.size();i++){
					Object obj = list.get(i) ;
					Row rows = sheet.createRow(i+2) ;
					for(int j=0;j<filed.length;j++){
						Cell cells = rows.createCell(j) ;
						cells.setCellType(Cell.CELL_TYPE_STRING);
						Object value = invokeMethod(obj,filed[j]) ;
						cells.setCellValue(value!=null?value.toString():""); 
						
					}
				}
				
				
//				sheet.autoSizeColumn(0, true);
				
				String path = req.getSession().getServletContext().getRealPath("/errorExcel") ;
				File file1 = new  File(path) ;
				if(!file1.exists()){
					file1.mkdirs() ; //创建文件夹
				}
				
				//导出路径
				FileOutputStream out = new FileOutputStream(path+"/"+fileName+".xlsx") ;
				wb.write(out);
				out.flush();
				out.close();
				return list==null?null:String.valueOf(list.size()) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null ;
	}
	
	/**
	 * 反射方法，通过get方法获取对象属性
	 * 
	 * @param owner
	 * @param fieldname
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(Object owner, String fieldname)throws Exception {

		String methodName = "get" + fieldname.substring(0, 1).toUpperCase()+ fieldname.substring(1);
		Class<?> ownerClass = owner.getClass();

//		Class[] argsClass = new Class[args.length];
//
//		for (int i = 0, j = args.length; i < j; i++) {
//			argsClass[i] = args[i].getClass();
//		}

		Method method = ownerClass.getMethod(methodName);
		return method.invoke(owner);
	}
	
	/**
	 * 下载文件
	 * @param downFile
	 * @param newFile
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void download(String downfile,String downloadPath,HttpServletResponse response) throws IOException{
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((downfile + ".xlsx").getBytes(), "iso-8859-1"));
        File downloadFile = new File(downloadPath) ;
        FileInputStream in = new FileInputStream(downloadFile) ;
        OutputStream out = response.getOutputStream(); 
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = in.read(buff, 0, buff.length))) {
		    out.write(buff, 0, bytesRead);
		}
		out.flush();
		out.close();
		in.close(); 
	}
	
	
	public static XSSFCellStyle setFontColor(Workbook wb){
		XSSFFont font = (XSSFFont) wb.createFont();
		font.setColor(HSSFColor.RED.index);
		XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
		style.setFont(font);
		return style ;
	}
	
	public static XSSFCellStyle setAttentionStyle(Workbook wb){
		XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
		style.setWrapText(true);
		return style ;
	}

	
	/**
	 * 导出excel
	 * @param orderList
	 * @param order
	 * @param title
	 * @param orderItemList
	 * @param fileName
	 * @param response
	 */
	public static String exportExcel(List<?> list, String[] order, String[] title, String orderItemList,
			String fileName, HttpServletResponse response) {
		
		if(list!=null && list.size()>0){
			try {
				//创建工作薄
				 XSSFWorkbook wb = new XSSFWorkbook() ;
				Sheet sheet = wb.createSheet();
				
				//创建标题行
				Row row = sheet.createRow(0) ;
				for(int i = 0 ; i< title.length;i++){
					Cell cell = row.createCell(i)  ;
					cell.setCellValue(title[i]);
				}
				String val=null; //订单状态/属性
				int r=0; //行记录数
				int size = list.size();  //i 数据条数
				for(int i=0;i<size;i++){  
					Object obj = list.get(i);
					Row rows = sheet.createRow(r+1) ; //创建行记录对象
					@SuppressWarnings("unchecked")
					List<TzOrderItem> orderItems = (List<TzOrderItem>)invokeMethod(obj,orderItemList) ; //获取订单商品集合对象orderItemList
					for(int j=0;j<order.length;j++){ //j 代表列
						
						
						if(j>=20 && j<=27){ //第20-27列 展示订单商品一对多 
							int size2 = orderItems.size();
								//多个商品数据
								for(int n=0;n<size2;n++){
									if(n==0){
										//第一条商品数据
										for(int k=j;k<8+j;k++){
											Cell cells = rows.createCell(k);
											Object value = invokeMethod(orderItems.get(n),order[k]) ;
											cells.setCellValue(value!=null?value.toString():""); 
										}
									}else{
										//创建下一行记录对象
										Row nextrows = sheet.createRow(r+1+n);
										for(int k=j;k<8+j;k++){
											Cell cells = nextrows.createCell(k);
											Object value = invokeMethod(orderItems.get(n),order[k]) ;
											cells.setCellValue(value!=null?value.toString():""); 
										}
									}
								}
								r+=size2-1;//添加行记录总数
							    j+=7; //增加列数
						}else{
							Cell cells = rows.createCell(j) ;
							Object value = invokeMethod(obj,order[j]) ;
							val=null; //把状态属性值记录清空
							if(j==9){ //支付方式
								if(value==null){
									val="";
								}else if(value.toString().equals("1")){
									val="支付宝支付";
								}else if(value.toString().equals("2")){
									val="微信支付";
								}else if(value.toString().equals("3")){
									val="网银支付";
								}else if(value.toString().equals("4")){
									val="爱心值支付";
								}
								cells.setCellValue(val);
							}else if(j==10){ //订单状态
								if(value==null){
									val="";
								}else if(value.toString().equals("0")){
									val="订单已取消";
								}else if(value.toString().equals("1")){
									val="订单未付款";
								}else if(value.toString().equals("2")){
									val="订单已付款待发货";
								}else if(value.toString().equals("3")){
									val="订单已发货";
								}else if(value.toString().equals("4")){
									val="订单交易成功";
								}else if(value.toString().equals("5")){
									val="订单交易关闭";
								}else if(value.toString().equals("6")){
									val="订单已退款";
								}
								cells.setCellValue(val);
							}else if(j==11){ //订单属性
								if(value==null){
									val="";
								}else if(value.toString().equals("0")){
									val="会员升级订单";
								}else if(value.toString().equals("1")){
									val="购物订单";
								}
								cells.setCellValue(val);
							}else{
								cells.setCellValue(value!=null?value.toString():""); 
							}
						}
					}
					r++;
				}
				response.reset();
				response.setContentType("application/vnd.ms-excel");  
		        response.setContentType("application/vnd.ms-excel;charset=utf-8");
		        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
		        
		        //获取桌面路径
		      /*  File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
		        String desktopPath = desktopDir.getAbsolutePath();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		        String date = dateFormat.format(new Date());
		        File file = new File(desktopPath+"/"+fileName+"-"+date+".xlsx");
		        FileOutputStream out = new FileOutputStream(file);*/
		        OutputStream out = response.getOutputStream();
		        //使用装饰模式，把out装饰进去bos中。使用缓冲写出速度变快
		        BufferedOutputStream bos=new BufferedOutputStream(out);
				wb.write(bos);
				bos.flush();
				bos.close();
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		return list==null?null:String.valueOf(list.size()) ;
		
	}
	
	
	/**
	 * 导出用户爱心值excel
	 * @param list
	 * @param order
	 * @param title
	 * @param fileName
	 * @param response
	 * @return
	 */
	public static String exportExcel2(List<?> list, String[] title, String [] filed, String fileName, HttpServletRequest request,HttpServletResponse response) {
		if(list!=null && list.size()>0){
			try {
				//创建工作薄
				 XSSFWorkbook wb = new XSSFWorkbook() ;
				Sheet sheet = wb.createSheet();
				
				String val=null; //提现类型标识，提现状态
				//创建标题行
				Row row = sheet.createRow(0) ;
				for(int i = 0 ; i< title.length;i++){
					Cell cell = row.createCell(i)  ;
					cell.setCellValue(title[i]);
				}
				for(int i=0;i<list.size();i++){
					
					Object obj = list.get(i) ;
					Row rows = sheet.createRow(i+1) ;
					for(int j=0;j<filed.length;j++){
						Cell cells = rows.createCell(j) ;
						Object value = invokeMethod(obj,filed[j]) ;
						val=null;
						if(j==4){
						  if(value==null){
							  val="";
						  }else if(value.toString().equals("0")){
							  val="支付宝提取";
						  }else if(value.toString().equals("1")){
							  val="微信提取";
						  }
						  cells.setCellValue(val);
							
						}else if(j==5){
							  if(value==null){
								  val="";
							  }else if(value.toString().equals("0")){
								  val="正在处理中";
							  }else if(value.toString().equals("1")){
								  val="提现完成";
							  }
							  cells.setCellValue(val);
						}else if(j>=9 && j<=11){
							 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						        String date = dateFormat.format(value);
						        cells.setCellValue(date); 
						}else{
							
							cells.setCellValue(value!=null?value.toString():""); 
						}
					}
				}
				response.reset();
				response.setContentType("application/vnd.ms-excel");  
		        response.setContentType("application/vnd.ms-excel;charset=utf-8");
		        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
		        //获取桌面路径
		      /*  File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
		        String desktopPath = desktopDir.getAbsolutePath();
		        
		        System.out.println("桌面路径-----"+desktopPath);
		        
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		        String date = dateFormat.format(new Date());*/
		        
		        //获取客户端请求地址
		        /*String remoteAddr = request.getRemoteAddr();
		        System.out.println("客户端地址--"+remoteAddr);*/
//		        File file = new File("http://"+remoteAddr+"\\"+desktopPath+"\\"+fileName+"-"+date+".xlsx");
//		        FileOutputStream out = new FileOutputStream(file);
		        //使用装饰模式，把out装饰进去bos中。使用缓冲写出速度变快
//		        BufferedOutputStream bos=new BufferedOutputStream(out);
		       // 获得response的输出流:
				OutputStream out = response.getOutputStream();
				BufferedOutputStream bos=new BufferedOutputStream(out);
				wb.write(bos);
				bos.flush();
				bos.close();
				System.out.println("-------------下载结束！！");
				return list==null?null:String.valueOf(list.size()) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null ;
	}

	/**
	 * 导出爱心值消费明细excel
	 * @param lists
	 * @param title
	 * @param loveConsumptionDetail
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 */
	public static String exportExcel3(List<?> list, String[] title,
			String[] filed, String fileName, HttpServletRequest request, HttpServletResponse response) {
		if(list!=null && list.size()>0){
			try {
				//创建工作薄
				 XSSFWorkbook wb = new XSSFWorkbook() ;
				Sheet sheet = wb.createSheet();
				
				String val=null; //提现类型标识，提现状态
				//创建标题行
				Row row = sheet.createRow(0) ;
				for(int i = 0 ; i< title.length;i++){
					Cell cell = row.createCell(i)  ;
					cell.setCellValue(title[i]);
				}
				for(int i=0;i<list.size();i++){
					
					Object obj = list.get(i) ;
					Row rows = sheet.createRow(i+1) ;
					for(int j=0;j<filed.length;j++){
						Cell cells = rows.createCell(j) ;
						Object value = invokeMethod(obj,filed[j]) ;
						val=null;
						if(j==5){
						  if(value==null){
							  val="";
						  }else if(value.toString().equals("0")){
							  val="支付宝提取";
						  }else if(value.toString().equals("1")){
							  val="微信提取";
						  }
						  cells.setCellValue(val);
							
						}else if(j==6){
							  if(value==null){
								  val="";
							  }else if(value.toString().equals("0")){
								  val="提现消费";
							  }else if(value.toString().equals("1")){
								  val="购物消费";
							  }else if(value.toString().equals("3")){
								  val="秒到";
							  }else if(value.toString().equals("4")){
								  val="推荐";
							  }
							  cells.setCellValue(val);
						}else if(j==7){
							 if(value==null){
								  val="";
							  }else if(value.toString().equals("0")){
								  val="支出";
							  }else if(value.toString().equals("1")){
								  val="收入";
							  }
							  cells.setCellValue(val);
						}else if(j==10){
							 if(value==null){
								  val="";
							  }else if(value.toString().equals("0")){
								  val="无手续费";
							  }else if(value.toString().equals("1")){
								  val="有手续费";
							  }
							  cells.setCellValue(val);
						}else if(j>=12 && j<=13){
							 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						        String date = dateFormat.format(value);
						        cells.setCellValue(date); 
						}else{
							
							cells.setCellValue(value!=null?value.toString():""); 
						}
					}
				}
				response.reset();
				response.setContentType("application/vnd.ms-excel");  
		        response.setContentType("application/vnd.ms-excel;charset=utf-8");
		        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
		       
				OutputStream out = response.getOutputStream();
				BufferedOutputStream bos=new BufferedOutputStream(out);
				wb.write(bos);
				bos.flush();
				bos.close();
				System.out.println("-------------下载结束！！");
				return list==null?null:String.valueOf(list.size()) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 导出用户爱心值数据
	 * @param list
	 * @param title
	 * @param userLove
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 */
	public static String exportExcel4(List<?> list, String[] title, String[] filed, String fileName,
			HttpServletRequest request, HttpServletResponse response) {
		
		if(list!=null && list.size()>0){
			try {
				//创建工作薄
				 XSSFWorkbook wb = new XSSFWorkbook() ;
				Sheet sheet = wb.createSheet();
				
				String val=null; //提现类型标识，提现状态
				//创建标题行
				Row row = sheet.createRow(0) ;
				for(int i = 0 ; i< title.length;i++){
					Cell cell = row.createCell(i)  ;
					cell.setCellValue(title[i]);
				}
				for(int i=0;i<list.size();i++){
					
					Object obj = list.get(i) ;
					Row rows = sheet.createRow(i+1) ;
					for(int j=0;j<filed.length;j++){
						Cell cells = rows.createCell(j) ;
						Object value = invokeMethod(obj,filed[j]) ;
						val=null;
						 if(j==6){
							  if(value==null){
								  val="";
							  }else if(value.toString().equals("0")){
								  val="冻结";
							  }else if(value.toString().equals("1")){
								  val="启动";
							  }
							  cells.setCellValue(val);
						}else if(j>=7 && j<=9){
							 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						        String date = dateFormat.format(value);
						        cells.setCellValue(date); 
						}else{
							
							cells.setCellValue(value!=null?value.toString():""); 
						}
					}
				}
				response.reset();
				response.setContentType("application/vnd.ms-excel");  
		        response.setContentType("application/vnd.ms-excel;charset=utf-8");
		        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
		       
				OutputStream out = response.getOutputStream();
				BufferedOutputStream bos=new BufferedOutputStream(out);
				wb.write(bos);
				bos.flush();
				bos.close();
				System.out.println("-------------下载结束！！");
				return list==null?null:String.valueOf(list.size()) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}  
