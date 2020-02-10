
$(function(){
	/*异步加载权限信息 生成权限树形*/
	authFunction();
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	
	/*提交表单添加角色*/
	$("#saveRole").click(function(){
		var name=$("#RoleName").val();
		var code=$("#code").val();
		var description = $("#description").val();
		if(isEmpty(name,"角色名称不能为空")){return false};
		if(isEmpty(code,"角色关键词不能为空")){return false};
		if(isEmpty(description,"为角色描述不能为空")){return false};
	//获取Ztree被选中的选中id
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		
		//遍历被选中的结点对象，获取id值
		var array = new Array();
		for(var i=0;i<nodes.length;i++){
//			alert(nodes[i].id);
			array.push(nodes[i].id);
		}
		//给id设置分隔符
		var functionIds = array.join(",");
		//将组装好的id数组赋值给Ztree表单的value
//		$("input[name='functionIds']").val(functionIds);
        
//		var formData = new FormData($("#form-admin-role")[0]); 
		if(isEmpty(functionIds,"为角色赋值权限不能为空")){return false};
		//异步请求保存角色信息
//		console.info(formData);
		$.ajax({
			type:"POST",
			data : {name:name,code:code,description:description,functionIds:functionIds},
			url:"/tz/admin/role/addRole",
			dataType: "json",  
			success:function(data){
				if(data.flag){
					layer.msg(data.msg,{icon:1,time:1000});
					var index = parent.layer.getFrameIndex(window.name);  
					parent.layer.close(index);  
				}
			}, 
			statusCode : {
				400 : function() {
					layer.msg('提交的参数不合法', {time:1500});
				},
				500 : function() {
					layer.msg('网络异常，请稍候再试!', {time:1500});
				}
			},
	        error:function(xhr,status,statusText){
	        	if(xhr.status == 500)
	        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
	        }
		});
		
	});
}); 

function authFunction(){
	 /*异步加载权限树形菜单*/
	// 授权树初始化
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	$.ajax({
		//发送ajax请求加载权限数据
		type : 'post',
		dataType : 'json',
		url:'/tz/admin/function/findAllFunction',
		success : function(data) {//data就是服务端返回的权限数据
			//var zNodes = eval("(" + data + ")");
			console.info(data)
			data = data.data;
			//使用权限数据初始化ztree
			$.fn.zTree.init($("#treeDemo"), setting, data);
		},
		error : function() {
			alert('树加载异常!');
		}
		
		});
}
  
        