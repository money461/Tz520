
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
		var zindex = $("#zindex").val();
		//获取被选中的角色上级目录
		 //获取Ztree被选中的选中id
		var roletreeObj = $.fn.zTree.getZTreeObj("roleDemo");
		var rolenodes = roletreeObj.getCheckedNodes(true);
		var pId = new Array();
		for(var i=0;i<rolenodes.length;i++){
			pId.push(rolenodes[i].id);
		}
		pId = pId[0];
		if(isEmpty(name,"角色名称不能为空")){return false};
		if(isEmpty(code,"角色关键词不能为空")){return false};
		if(isEmpty(description,"为角色描述不能为空")){return false};
		if(isEmpty(pId,"角色父目录节点不能为空")){return false}
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
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/role/addRole',
		        data : {name:name,code:code,description:description,pId:pId,zindex:zindex,functionIds:functionIds},
		        dataType: "json",
		        beforeSend: function () {
		            //防止重复提交
		        	 $(".layui-layer-shade").css({'display':'none'});
		        	 $(".layui-layer-dialog").css({'display':'none'});
		        	 loading.loadingShow();
		        },
		        success: function(data){
		        	loading.loadingHide();
		        	 if(data.flag){
		        		 layer.alert(data.msg,{icon:1,time:2000});
		        		var index = parent.layer.getFrameIndex(window.name);
		 				parent.flush();//点击确定调用父页面方法  
		 				parent.layer.close(index);		
		        	}else{
		        		layer.msg('操作失败，请稍后重试！',{icon:5,time:1000});
		        	}	 
		        },  
		        error:function(xhr,status,statusText){
		        	loading.loadingHide();
		        	if(xhr.status == 500)
		        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
		        }  
		    });
		 });
	});
}); 

function authFunction(){
	 /*异步加载权限树形菜单*/
	// 授权树初始化
	var setting = {
			check: {
				enable: true,
//				chkStyle: "radio",  //单选框
//	            radioType: "all" , //对所有节点设置单选
	            chkboxType: {"Y" : "s", "N" : ""}
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
//			console.info(data)
			if(data.flag){
				data = data.data;
				//使用权限数据初始化ztree
				$.fn.zTree.init($("#treeDemo"), setting, data);
			}
			/*异步加载角色信息*/
			getParentRoleIdData();
		},
		error : function() {
			alert('树加载异常!');
		}
		
		});
}
  
/*角色上级目录*/
function getParentRoleIdData(){
	// 授权树初始化
	var setting = {
			check: {
				enable: true,
				chkStyle: "radio",  //单选框
	            radioType: "all" , //对所有节点设置单选
				 chkboxType:  { "Y": "", "N": "" }
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	$.ajax({
		//发送ajax请求加载权限数据
		type : 'get',
		dataType : 'json',
		url:'/tz/admin/role/findAllRole',
		success : function(data) {//data就是服务端返回的权限数据
			//var zNodes = eval("(" + data + ")");
//			console.info(data)
			if(data.flag){
				data = data.data;
				//使用权限数据初始化ztree
				$.fn.zTree.init($("#roleDemo"), setting, data);
			}
		},
		error : function() {
			alert('树加载异常!');
		}
		
		});
}
