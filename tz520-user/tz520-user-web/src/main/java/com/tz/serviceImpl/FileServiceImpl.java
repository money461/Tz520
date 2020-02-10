package com.tz.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.swing.plaf.synth.SynthStyle;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.res.MsgResult;
import com.tz.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
    //fast 文件上传
    @Autowired
    private FastDFSClientWrapper dfsClientWrapper;
    
	@Autowired
	private RedisCache cache;
    
	@Override
	public AppMsgResult deleteFile(String userId,String userToken,String fileUrl) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		System.out.println(fileUrl);
		//判断空
		if(StringUtil.isNotEmpty(fileUrl)){
			if(fileUrl.equals(Constant.DFHEAD)){
				msgResult = AppMsgResult.result(200,"success",null );
			}else{
				if(fileUrl.equals(Constant.DFHEAD2)){
					msgResult = AppMsgResult.result(200,"success",null );
				}else{
					if(fileUrl.equals(Constant.DFHEAD3)){
						msgResult = AppMsgResult.result(200,"success",null );
					}else{
						if(StringUtil.isNotEmpty(userId)){
							if(StringUtil.isNotEmpty(userToken)){
								//获取用户的登录 token
								String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
								String result_cache = cache.getCache(cache_key, String.class);
								//是否存在
								if(null != result_cache){
									//验证
									if(result_cache.equals(userToken)){
										//修改
										try {
											if(fileUrl.indexOf(Constant.FILESERVER_URL) != -1){
												fileUrl = fileUrl.substring(fileUrl.indexOf(Constant.FILESERVER_URL)+Constant.FILESERVER_URL.length(),fileUrl.length());
											}
											String url = fileUrl.split("\\?")[0];
											dfsClientWrapper.deleteFile(url);
											String[] url_150 = url.split("\\.");
											dfsClientWrapper.deleteFile(url_150[0]+"_150x150."+url_150[1]);
											LOG.info("deleteFile--------");
											msgResult = AppMsgResult.result(200,"success",null );
										} catch (Exception e) {
											// TODO: handle exception
											msgResult =  AppMsgResult.result(528, "操作失败，请稍后重试！",null);
										}
										
									}else{
										msgResult = AppMsgResult.result(538,"用户未登录！",null );
									}
								}else{
									msgResult = AppMsgResult.result(538,"用户未登录！",null );
								}
							}else{
								msgResult = AppMsgResult.result(541, "用户token不能为空！", null);
							}
						}else{
							msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
						}
					}
				}
			}
		}else{
			msgResult = AppMsgResult.result(550,"删除的文件路径不能为空！", null);
		}
		return msgResult;
	} 
	@Override
	public AppMsgResult uploadHead(String id,String userToken,MultipartFile file){
		AppMsgResult msgResult = null;
		//判断空
		if(StringUtil.isNotEmpty(id)){
			if(StringUtil.isNotEmpty(userToken)){
				//获取用户的登录 token
				String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+id;
				String result_cache = cache.getCache(cache_key, String.class);
				//是否存在
				if(null != result_cache){
					//验证
					if(result_cache.equals(userToken)){
						//上传
						try {
							String path = dfsClientWrapper.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), file.getOriginalFilename());
							LOG.info("uploadImageAndCrtThumbImage--------"+path+"?"+file.getOriginalFilename());
							if(StringUtil.isNotEmpty(path)){
								msgResult =  AppMsgResult.result(200, "success", ""+path+"?"+file.getOriginalFilename());
							}
						} catch (Exception e) {
							// TODO: handle exception
							msgResult =  AppMsgResult.result(528, "服务器繁忙，请稍后重试！",null);
						}
					}else{
						msgResult = AppMsgResult.result(538,"用户未登录！",null );
					}
				}else{
					msgResult = AppMsgResult.result(538,"用户未登录！",null);
				}
			}else{
				msgResult = AppMsgResult.result(541, "用户token不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		}
		return msgResult;
	}
}
