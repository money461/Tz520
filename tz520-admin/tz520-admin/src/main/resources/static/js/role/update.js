
//获取编辑角色id
var id =$("#roleId").val();

$(function(){
//	异步加载权限树信息
	AuthFunctiondata();
	
	//提交表单添加角色
	$(".submit_data").click(function(){
		var name = $("#RoleName").val();
		var code = $("#code").val();
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
		//获得ztree对象
		var treeObj = $.fn.zTree.getZTreeObj("functionTree");
		//获得当前ztree对象选中的节点数组
		var nodes = treeObj.getCheckedNodes(true);//在提交表单之前将选中的checkbox收集
		//循环数组，获得节点的ID，拼接成字符串使用逗号分隔
		var array = new Array();
		for(var i=0;i<nodes.length;i++){
			array.push(nodes[i].id);
		}
		var functionIds = array.join(",");
		if(isEmpty(name,"角色名称不能为空")){return false};
		if(isEmpty(code,"角色关键词不能为空")){return false};
		if(isEmpty(description,"角色描述不能为空")){return false};
		
		
		//在页面设置权限隐藏字段functionIds(多个权限id)
//		 $("input[name=functionIds]").val(functionIds);
		if(id==pId){
			layer.msg('不能选择自身对象作为父目录',{icon:5,time:1000});
			return;
		}
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
				type : "POST",
				url : "/tz/admin/role/updateRole",
				data : {
					id : id,
					name : name,
					code : code,
					pId :pId,
					zindex:zindex,
					description : description,
					functionIds : functionIds
				},
				dataType:'json',
				success : function(data) {
					if (data.flag) {
						layer.msg(data.msg, {
							icon : 1,
							time : 1500
						});
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				},
				beforeSend : function() {
					// 防止重复提交
					$(".layui-layer-shade").css({
						'display' : 'none'
					});
					$(".layui-layer-dialog").css({
						'display' : 'none'
					});
					loading.loadingShow();
				},
				success : function(data) {
					loading.loadingHide();
					if (data.flag) {
						var index = parent.layer.getFrameIndex(window.name);
						parent.flush();// 点击确定调用父页面方法
						parent.layer.close(index);
					} else {
						layer.msg('操作失败，请稍后重试！', {
							icon : 5,
							time : 1000
						});
					}
				},
				error : function(xhr, status, statusText) {
					loading.loadingHide();
					if (xhr.status == 500)
						layer.msg("服务器连接失败，请联系管理员！", {
							icon : 2,
							time : 1500
						});
				}
			});
		});

	});
});



//update编辑页面异步加载角色数据回显
function getDate(){
	$.ajax({
        type: "POST",
        url: '/tz/admin/role/findRoleById',
        data: {id:id},
        dataType: "json",
        success: function(data){
            if(data.flag){
            	data = data.data;
            	$("#id").val(data.id);
            	$("#RoleName").val(data.name);
	    		$("#code").val(data.code);
	    		$("#description").val(data.description);
	    		$("#zindex").val(data.zindex);
	    		
             }	
	    	else{
	    		layer.msg(data.msg,{icon:5,time:1000});
		    }
        },  
        error:function(xhr,status,statusText){
        	if(xhr.status == 500)
        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
        }  
    });
    
}

//异步加载权限树信息
function AuthFunctiondata(){
//	 异步加载权限树形菜单
	// 授权树初始化
	var setting = {
			check: {
				enable: true,
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
				$.fn.zTree.init($("#functionTree"), setting, data);
			}
			/*异步加载父功能节点*/
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
			/*回显数据*/
			getDate();
		},
		error : function() {
			alert('树加载异常!');
		}
		
		});
}

        
