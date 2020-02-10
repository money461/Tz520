package com.tz.service;

import org.springframework.web.multipart.MultipartFile;

import com.tz.res.AppMsgResult;
import com.tz.res.MsgResult;

public interface FileService {
	
	//用户上传头像
	AppMsgResult uploadHead(String userId,String userToken,MultipartFile file);
	//删除垃圾图片
	AppMsgResult deleteFile(String userId,String userToken,String fileUrl);
	
 
}
