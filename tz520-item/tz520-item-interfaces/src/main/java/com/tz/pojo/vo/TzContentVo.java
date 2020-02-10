package com.tz.pojo.vo;

import com.tz.pojo.TzContent;
import com.tz.res.Constant;

public class TzContentVo extends TzContent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8397365740362701394L;

	
	public String[] getFirstPrics(){
		//获取从数据库查询出来的图片名称字符串
		String string = this.getPicFirstUrl();
		if(string !=null && !"".equals(string)){
			String[] firstPrics = string.split(",");
			for(int i=0;i<firstPrics.length;i++){
				firstPrics[i] = Constant.FILESERVER_URL+firstPrics[i];
			}
			return firstPrics;
		}
		return new String[]{};
	}
	
	
	public String[] getSecondPrics(){
		//获取从数据库查询出来的图片名称字符串
				String string = this.getPicSecondUrl();
				if(string !=null && !"".equals(string)){
					String[] secondPrics = string.split(",");
					for(int i=0;i<secondPrics.length;i++){
						secondPrics[i] = Constant.FILESERVER_URL+secondPrics[i];
					}
					return secondPrics;
				}
				return new String[]{};
	}


			public TzContentVo() {
				super();
			}
	
	
	

}
