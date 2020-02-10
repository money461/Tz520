package com.tz.service;

import org.springframework.web.multipart.MultipartFile;

import com.tz.res.MsgResult;

public interface FileService {
	
	//删除文件
	MsgResult  deleteFile(String fileUrl) throws Exception;	
	//上传文件带缩略图
	MsgResult  uploadImageAndCrtThumbImage(MultipartFile file) throws Exception;
	//上传文件
	MsgResult  uploadFile(MultipartFile file) throws Exception;	 
}
