package com.tz.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.tz.pojo.TzItem;
import com.tz.pojo.vo.TzItemVo;
import com.tz.res.MsgResult;



public interface ItemService  {
	
	//添加文件图片信息
	public String add(MultipartFile file)throws IOException;

	//分页商品信息
	public MsgResult getItemList(TzItemVo tzItemVo,Integer curPage, Integer rows);

	//添加商品信息
	public MsgResult addItem(TzItem tzItem);

	//根据商品id，回显商品信息
	public MsgResult findItemById(String id);

	//更新商品信息
	public MsgResult updateItem(TzItem tzItem);

	//上架下架 删除一个或者多个商品
	public MsgResult operation(String type, String ids);

	
		 
}
