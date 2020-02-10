package com.tz.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/test")
public class test {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/containsEmoji")
	public ModelAndView listAllPage(String pic) throws Exception {
		if(!containsEmoji(pic)){
			LOG.info("invoke----------输入了表情等特殊符号！");
		}else{
			LOG.info("invoke----------没有输入了表情等特殊符号！");
		}
		return null;
	}
	/** 
     * 检测是否有emoji字符 
     * 
     * @param source 
     * @return 一旦含有就抛出 
     */  
    public static boolean containsEmoji(String source) {  
        if (StringUtils.isBlank(source)) {  
            return false;  
        }  
        int len = source.length();  
        for (int i = 0; i < len; i++) {  
            char codePoint = source.charAt(i);  
            if (!isNotEmojiCharacter(codePoint)) {  
                //判断到了这里表明，确认有表情字符  
                return true;  
            }  
        }  
        return false;  
    }
    /** 
     * 判断是否为非Emoji字符 
     * 
     * @param codePoint 比较的单个字符 
     * @return 
     */  
    private static boolean isNotEmojiCharacter(char codePoint) {  
        return (codePoint == 0x0) ||  
                (codePoint == 0x9) ||  
                (codePoint == 0xA) ||  
                (codePoint == 0xD) ||  
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||  
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||  
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));  
    }  

}
