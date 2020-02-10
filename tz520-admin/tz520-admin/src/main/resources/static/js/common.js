var projectPath="lanbo";
//公共js
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
//空验证
function isEmpty(value,msg){
	if(value==null||value==''){
		layer.msg(msg,{icon:7,time:1500});	
		return true;
	}
	return false;
}
//空添加
function isNull(value){
	if(value==null||value==''){
		return "";
	}
	return value;	
}
//判断是否大于等于0的数
function isCheckNum(value,msg) {
    var ss = /^[0-9]\d*(\.\d+)?$/;
    if (!ss.test(value)) {
    	layer.msg(msg,{icon:7,time:1500});	
        return true;
    }
    else {
        return false;
    }
}
//判断是否大于等于0的正整数
function isIntNum(value,msg) {
	var reg = /^([1-9]\d{0,}|0)$/;
    if (!reg.test(value)) {
    	layer.msg(msg,{icon:7,time:1500});	
        return true;
    }
    else {
        return false;
    }
}
// 手机号码验证    
function isPhone(value) {    
  var length = value.length;    
  if(length != 11 && !/^(((13[0-9]{1})|(15[0-35-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value)){
	layer.msg("手机号码格式不正确！",{icon:7,time:1500});	
	return true;
  } 
	return false;
};
// 匹配密码，以字母开头，长度在6-16之间，只能包含字符、数字和下划线。      
function isPasswod(value) {  
		  if(!/^[\d_a-zA-Z]{6,12}$/.test(value)){
		layer.msg("以字母开头，长度在6-12之间，只能包含字符、数字和下划线。",{icon:7,time:1500});	
		return true;
	  }
	  return false;
	};
// 判断整数value是否大于0
function isNumber(value) {    
	  value=parseInt(value);  
	  if((/^(\+|-)?\d+$/.test(value)) && value > 0 && value < 6 ){
		  return false;
	  }else{
		  layer.msg("订单数必须为正整数，并且单数最多不超过 5！",{icon:7,time:1500});	
		  return true;
	  } 
		
	};
// 身份证号码验证
function isIdCard(value) {    
	  var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;    
	  if(!idCard.test(value)){
		layer.msg("身份证号码不正确！",{icon:7,time:1500});	
		return true;
	  } 
		return false;
	};

//去除数组重复
	function unique(arr) {
	    var result = [], hash = {};
	    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
	        if (!hash[elem]) {
	            result.push(elem);
	            hash[elem] = true;
	        }
	    }
	    return result;
	}