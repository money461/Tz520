package com.tz.interfaces;
import java.io.IOException;
import java.io.InputStream;

public interface FileService   {

	 /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */ 
    public void uploadFile(String filename, InputStream data);

    

}
