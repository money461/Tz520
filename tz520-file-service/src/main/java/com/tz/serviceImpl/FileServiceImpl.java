package com.tz.serviceImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.github.tobato.fastdfs.domain.MateData;
import com.tz.interfaces.FileService;

@Service
public class FileServiceImpl implements FileService  {


	/**
	 * 
	 */

	private final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    
    @Autowired
    private FastDFSClientWrapper dfsClientWrapper;
    
	@Override
	
    public void uploadFile(String filename, InputStream data) { 
    /*	try {
			dfsClientWrapper.uploadFile(data, 9999, "sss.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	 
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        try {  
            //获取客户端传递的InputStream  
            bis = new BufferedInputStream(data);  
            //创建文件输出流  
            bos = new BufferedOutputStream(new FileOutputStream(  
                    "E://fileUpload/" + filename));  
            byte[] buffer = new byte[8192];  
            int r = bis.read(buffer, 0, buffer.length);  
            while (r > 0) {  
                bos.write(buffer, 0, r);  
                r = bis.read(buffer, 0, buffer.length);  
            } 
            
            System.out.println("-------文件-"+dfsClientWrapper.uploadFile(data, 99924, filename));
            System.out.println("-------文件上传成功！-------------");  
        } catch (IOException e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e) {  
                    throw new RuntimeException(e);  
                }  
            }  
            if (bis != null) {  
                try {  
                    bis.close();  
                } catch (IOException e) {  
                    throw new RuntimeException(e);  
                }  
            }  
        } 
    }

}