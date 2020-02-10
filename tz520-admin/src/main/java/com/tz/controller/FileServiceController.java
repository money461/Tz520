package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tz.res.MsgResult;

import com.tz.service.FileService;

/**
 * 文件服务
 * @author menglin
 * 2017年10月8日10:24:23
 */
@RestController
@RequestMapping("/admin/file")
public class FileServiceController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileService fileService;
	/**
	 * 删除文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/deleteFile")
	public MsgResult deleteFile(String fileUrl) throws Exception {
		LOG.info("invoke----------/tzItem/deleteFile");
		return fileService.deleteFile(fileUrl);
	}
	/**
	 * 上传文件带缩略图
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/uploadImageAndCrtThumbImage")
	public MsgResult uploadImageAndCrtThumbImage(@RequestParam("file")MultipartFile file) throws Exception {
		LOG.info("invoke----------/file/uploadImageAndCrtThumbImage");
		return fileService.uploadImageAndCrtThumbImage(file);
	}
	/**
	 * 上传文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/uploadFile")
	public MsgResult uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
		LOG.info("invoke----------/file/uploadFile");
		return fileService.uploadFile(file);
	}
	
	

}
