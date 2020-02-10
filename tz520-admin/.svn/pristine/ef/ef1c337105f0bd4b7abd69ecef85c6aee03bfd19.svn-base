package com.tz.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.util.StringUtil;
import com.tz.res.MsgResult;

import com.tz.service.FileService;

@Service
public class FileServiceImpl implements FileService  {
	
	/**
	 * 
	 */
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private FastDFSClientWrapper dfsClientWrapper;

	@Override
	public MsgResult deleteFile(String fileUrl) throws Exception {
		// TODO Auto-generated method stub
		MsgResult result = null;
		System.out.println(fileUrl);
		try {
			String url = fileUrl.split("\\?")[0];
			dfsClientWrapper.deleteFile(url);
			String[] url_150 = url.split("\\.");
			dfsClientWrapper.deleteFile(url_150[0]+"_150x150."+url_150[1]);
			LOG.info("deleteFile--------");
		} catch (Exception e) {
			// TODO: handle exception
			result =  MsgResult.result(false, "删除失败！",null);
		}
		return result;
	}
	@Override
	public MsgResult uploadImageAndCrtThumbImage(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		MsgResult result = null;
		try {
			String path = dfsClientWrapper.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), file.getOriginalFilename());
			LOG.info("uploadImageAndCrtThumbImage--------"+path+"?"+file.getOriginalFilename());
			if(StringUtil.isNotEmpty(path)){
				result =  MsgResult.result(true, "上传成功", path+"?"+file.getOriginalFilename());
			}
		} catch (Exception e) {
			// TODO: handle exception
			result =  MsgResult.result(false, "上传失败！",file.getOriginalFilename());
		}
		return result;
	}
	@Override
	public MsgResult uploadFile(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		MsgResult result = null;
		try {
			String path = dfsClientWrapper.uploadFile(file.getInputStream(), file.getSize(), file.getOriginalFilename());
			LOG.info("uploadFile--------"+path+"?"+file.getOriginalFilename());
			if(StringUtil.isNotEmpty(path)){
				result =  MsgResult.result(true, "上传成功", path);
			}
		} catch (Exception e) {
			// TODO: handle exception
			result =  MsgResult.result(false, "上传失败！",file.getOriginalFilename());
		}
		return result;
	}
   public static void main(String[] args) {
	   String path = "group1/M00/00/01/rBBH51nZ88-AVo_lAAFV-7fgPdU228.jpg";
	   System.out.println(path.split("\\."));
   }

	
}
