var code ; //在全局定义验证码   
$(document).ready(function(){
	createCode();
	$("body").keydown(function() {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	        $(".btn-login").click();
	    }
	});  
});
//产生验证码  
function createCode(){  
 code = "";   
 var codeLength = 4;//验证码的长度  
 var checkCode = document.getElementById("code");   
 var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
 'S','T','U','V','W','X','Y','Z');//随机数  
 for(var i = 0; i < codeLength; i++) {//循环操作  
    var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）  
    code += random[index];//根据索引取得随机数加到code上  
}  
checkCode.value = code;//把code值赋给验证码  
}  
function validate(){  
            
} 
$(".btn-success").on("click","",function(){
	var managerName = $("#managerName").val();
	var password = $("#password").val();
	if(managerName == null || managerName == undefined || managerName == ''){
		/*layer.msg('用户名不能为空!',{icon: 2,time:1000});*/
		layer.alert("用户名不能为空!",{icon: 2})
		return;
	}
	if(password == null || password == undefined || password == ''){
		/*alert("密码不能为空!")*/
		layer.alert("密码不能为空!",{icon: 2})
		return;
	}
	//校验验证码  
	var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写        
	if(inputCode.length <= 0) { //若输入的验证码长度为0  
		layer.alert("请输入验证码！",{icon: 2})
/*	    alert("请输入验证码！");*/ //则弹出请输入验证码  
	    return;
	}         
	else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时 
		layer.alert("验证码输入错误！@_@",{icon: 2})
/*	    alert("验证码输入错误！@_@");*/ //则弹出验证码输入错误  
	    createCode();//刷新验证码  
	    document.getElementById("input").value = "";//清空文本框  
	    return;
	}         
	else { //输入正确时  
	   /* alert("^-^"); //弹出^-^  
*/	} 
	//window.location.href = "/tz/admin/manager/index";
	//点击登录，触发点击事件提交账户和密码  发出请求
	$.ajax({  
        type:"GET",  
        url: "/tz/admin/manager/login",  
        data:{managerName:managerName,password:password},
        success: function(data){
        	console.info(data);
            if(data.flag){  
            	//登录成功获取结果flag=true 跳转至主页面
            	window.location.href = "/tz/admin/manager/index";
            }else{
            	createCode();//刷新验证码  
        	    document.getElementById("input").value = "";//清空文本框  
            	//登录失败，显示错误信息
            	layer.msg(data.msg,{icon: 2,time:1500});
            	/*document.getElementById("managerName").value = "";//清空文本框  
*/            	document.getElementById("password").value = "";//清空文本框  
//            	alert(data.msg);
            	
            }  
        },  
        error:function(xhr,status,statusText){
        	if(xhr.status == 500)
        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
        }
    });  
})