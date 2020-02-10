;(function ($, undefined) {
	$.fn.uploadfile = function (setting) {
		var defaultSetting = {
			url : 'file.php',
			width : 600,
			height : 50,
			canDrag : false,
			canMultiple : false,
			fileType : "image",
			Maximum:5,
			success : function (fileName) {   //单个文件上传成功的回调函数
			},
			error : function (fileName) {     //单个文件上传失败的回调函数
			},
			complete : function () {  //上传完成的回调函数
			}
		};
		//判断浏览器是否支持FileReader
		if(!window.FileReader){
			alert('您的浏览器不支持FileReader，请更换浏览器。');
			return;
		}
		//重載数据 如果没设置则用默认的，设置了则替换
		setting = $.extend(true, {}, defaultSetting, setting);
		setting.width < 450 && (setting.width = 450);

		$(this).each(function (i, item) {
			var demoHtml = '';
			//是否可以拖拽图片上传，构造dom结构
			if(setting.canDrag){
				setting.height < 200 && (setting.height = 200);
				demoHtml +=  			'<div class="file_sel">';
				demoHtml +=					'<div class="file_input">';
				demoHtml +=						'<div class="sel_file_img">';
				demoHtml +=							'<span><img src="'+projectPath+'/static/rzhd/imgs/add_img.png"/></span>';
				demoHtml +=						'</div>';
				demoHtml +=						'<div class="sel_file_btn">';
				demoHtml +=							'<input type="file"/>';
				demoHtml +=							'<button>点击选择文件</button>';
				demoHtml +=						'</div>';
				demoHtml +=					'</div>';
				demoHtml +=					'<div class="file_drag">';
				demoHtml +=						'<span>或者将文件拖到此处</span>';
				demoHtml +=					'</div>';
				demoHtml +=				'</div>';
				demoHtml +=				'<div class="file_info_handle">';
				demoHtml +=					'<div class="file_info">';
				demoHtml +=						'当前选择了<span class="file_count">0</span>个文件，共<span class="file_size">0</span>KB。';
				demoHtml +=						'<input type="file"/>';
				demoHtml +=						'<button class="continue_sel">继续选择</button>';
				demoHtml +=						'<button class="uploadfile">开始上传</button>';
				demoHtml +=					'</div>';
				demoHtml +=				'</div>';
				demoHtml +=				'<div class="file_show">';
				demoHtml +=				'</div>';
			}else{
				setting.height < 50 && (setting.height = 50);
				$(item).addClass('noDrag');
				demoHtml +=		'<div class="file_info_handle">';
				demoHtml +=			'<div class="file_info">';
				demoHtml +=				'当前选择了<span class="file_count">0</span>个文件，共<span class="file_size">0</span>KB。';
				demoHtml +=				'<input type="file"/>';
				demoHtml +=				'<button class="continue_sel">继续选择</button>';
				demoHtml +=				'<button class="uploadfile">开始上传</button>';
				demoHtml +=			'</div>';
				demoHtml +=		'</div>';
				demoHtml +=		'<div class="file_show">';
				demoHtml +=			'<div class="sel_file_btn">';
				demoHtml +=				'<input type="file"/>';
				demoHtml +=				'<div class="sel_btn"></div>';
				demoHtml +=			'</div>';
				demoHtml +=		'</div>';
			}
			$(item).css({
				width : setting.width,
				height : setting.height,
				display : 'block'
			});
			$(item).html(demoHtml);

			//获取DOM节点
			var fileArr = [],
			fileSize = 0,
			_this = $(item),
			fileDrag = $('.file_sel .file_drag', _this),
			selFileIpt = $('input[type=file]', _this),
			selFileBtn = selFileIpt.next();
			fileCount = $('.file_info_handle .file_info .file_count', _this),
			fileSz = $('.file_info_handle .file_info .file_size', _this),
			beginUpload = $('.file_info_handle .file_info .uploadfile', _this),
			fileShow = $('.file_show', _this),
			noDragSelFile = $('.file_show .sel_file_btn', _this);
			
			//显示拖拽上传部分
			setting.canDrag || fileShow.show();

			//是否可以多选
			setting.canMultiple && selFileIpt.attr('multiple', 'multiple');

			//绑定事件
			selFileIpt.on('change', selFile);

			//让按钮去触发input的click事件
			selFileBtn.on('click', function () {  
				$(this).prev().click();
			})

			fileDrag.on({
				dragover : dragOver, 
				drop : selFile
			})
			
			//点击上上传
			beginUpload.on('click', upLoadFile);
			// 选择文件
			function selFile (e) {
				//上传数限制
				var Maximum = setting.Maximum;
				var length = fileArr.length;
				if(length == Maximum){
					alert("最多上传:"+Maximum+"个文件！");
					return;
				}else{
					e = e || window.event;
					//阻止浏览器的默认行为
					if(e.preventDefault){  
						e.preventDefault();	
					}else{
						e.returnValue = false;
					}
					var files = this.files || event.dataTransfer.files,
					src = projectPath+'/static/rzhd/imgs/',
					imgSrc;
					Array.prototype.forEach.call(files, function (item, i) {
						//防止重复选择相同的文件
						var notExist = fileArr.some(function (existFile) {
							return existFile.name === item.name;
						})
						if(notExist && fileArr.length != 0){
							return !notExist;
						}
						var fr = new FileReader();
						fr.readAsDataURL(item);
						fr.onload = function () {
							//文件类型判断
							var fileType =  setting.fileType;
							if(fileType==null||fileType===undefined||fileType==""||fileType==null)
								fileType = "image";
							
							//判断展示的文件类型
							if(item.type.indexOf("image") > -1){
								if("image"==fileType ||"all"==fileType ){
									imgSrc = fr.result;
								}	
								else{
									alert("不能上传图片类型的文件!")
									return;
								}
									
							}else{
								if("file"==fileType ||"all"==fileType ){
									imgSrc = src + 'files.png';
								}else{
									alert("只能上传图片类型的文件！")
									return;
								}
									
							}
							fileArr.push(item);
							//展示选择的文件
							var imgDom = $('<span class="img_box"><span class="up_load_success" title="上传成功"></span><span class="img_handle"><span class="file_name" title="'+ item.name +'">'+ item.name +'</span><span class="icon-bin"></span></span><img src="'+ imgSrc +'"/></span>');
							if(setting.canDrag){
								fileShow.css('display') === 'none' && fileShow.show();
								fileShow.append(imgDom);
							}else{
								fileShow.css('display') === 'none' && fileShow.show();
								noDragSelFile.before(imgDom);
							}
						}
					}) 

					//选择的文件的信息
					fileCount.html(fileArr.length);
					fileSz.html(getFileInfo());
					//防止在删除了上次选择的文件后，再次选择相同的文件无效的问题。
					this.value ='';  
				}
			}

			//拖拽
			function dragOver (e) {
				var event = e || window.event;
				event.preventDefault();
			}

			//上传文件
			function upLoadFile () {
			if(!fileArr.length){
				alert('请选择文件');
				return;
			}
			var formData = new FormData();
			fileArr.forEach(function (item, i) {
				var upLoadSuccess = $('.img_box').eq(i).children('.up_load_success');
				//防止重复上传
				if(upLoadSuccess.css('display') === 'block') return false;
				formData.append('files[]', item);
			})
			console.info(fileArr)
			$.ajax({
			    url: setting.url,
			    type: 'POST',
			    cache: false,
			    data: formData,
			    processData: false,
			    contentType: false
			}).done(function(res) {
				//上传成功图标
			/*	upLoadSuccess.show();*/
				//单个文件上传成功执行回调
				setting.success(res.data);
				//全部文件上传完成执行回调函数
				(i === (fileArr.length - 1)) && setting.complete();
			}).fail(function(res) {
				//单个文件上传失败执行回调
				setting.error(item.name);
				(i === (fileArr.length - 1)) && setting.complete();
			});
		}
			//计算文件信息
			function getFileInfo () {
				//每次重新计算大小，防止单位不同造成错误
				fileSize = 0;
				fileArr.forEach(function (item, i) {
					fileSize += item.size;
				})
				fileSize = (fileSize / 1024).toFixed(2);
				return fileSize;
			}

			fileShow.on('click', '.icon-bin' , function () {
				//删除节点
				var index = $(this).parents('.img_box').index();
				$(this).parents('.img_box').remove();

				//删除上传文件
				fileArr.splice(index, 1);

				//修改文件信息
				fileCount.html(fileArr.length);
				fileSz.html(getFileInfo());

				//隐藏文件显示区域
				!setting.canDrag || fileArr.length || fileShow.hide();
			})
		})
	}
})(jQuery);

//单独针对单文件上传
/*var hfu_ajax=function(obj){
	if(obj==null||obj.url===undefined||obj.url==""||obj.url==null){
		throw new Error("请求链接不能为空！")
	}
	var fileName=document.getElementById("file_upload");
	if(fileName.files.length<=0){
		return;
	}
	var xhr =new XMLHttpRequest();
	var form= document.getElementById('file_up');
	var formData=new FormData(form);
	//头像
	var name=$("#file_upload").val();
	xhr.open('POST',obj.url);
	xhr.upload.addEventListener('progress',function(e){if(obj.progress==null||typeof(obj.progress) != "function"){return;};var progress=(e.loaded/e.total*100).toFixed(2);obj.progress(progress);},false)
	xhr.upload.addEventListener('load', function(e){if(obj.load==null||typeof(obj.load) != "function"){return;};obj.load(e);}, false);
	xhr.upload.addEventListener('error', function(e){if(obj.error==null|typeof(obj.error) != "function"){return;};obj.error(e);}, false);
	xhr.onreadystatechange = function(e){
		if( e.target.readyState == 4 && e.target.status == 200){
			if(obj.success==null||typeof(obj.success) != "function"){return;};
			obj.success(eval("("+e.target.response+")"));
			}};
	xhr.send(formData);		
}*/
//----------------------------- 下面针对自己单文件处理
var filesArr = [];
function  handleFiles(files,fileType) {
  if(files.length)  
  {  
	 var file = files[0];  
	//防止重复选择相同的文件
	var notExist = filesArr.some(function (existFile) {
		return existFile.name === file.name;
	})
	if(notExist && fileArr.length != 0){
		return !notExist;
	} 
	var fr = new FileReader();
	fr.readAsDataURL(file);
	fr.onload = function () {
		//文件类型判断
		if(fileType==null||fileType===undefined||fileType==""||fileType==null)
			fileType = "image";
		//判断展示的文件类型
		if(file.type.indexOf("image") > -1){
			if("image"==fileType ||"all"==fileType ){
				//本地地址 imgSrc = fr.result;
			}	
			else{
				alert("不能上传图片类型的文件!")
				return;
			}
				
		}else{
			if("file"==fileType ||"all"==fileType ){
				
			}else{
				alert("请上传图片类型的文件！")
				return;
			}
				
		}
		filesArr.push(file);
	}
  }  
}  
//多文件上传
var hfu_ajaxs=function(obj){
	if(!filesArr.length){
		alert('请选择文件');
		return;
	}
	if(obj==null||obj.url===undefined||obj.url==""||obj.url==null){
		throw new Error("请求链接不能为空！")
	}
	var xhr =new XMLHttpRequest();
	var formData=new FormData();
	filesArr.forEach(function (item, i) {
		formData.append('files[]', item);
	})
	xhr.open('POST',obj.url);
	xhr.upload.addEventListener('progress',function(e){if(obj.progress==null||typeof(obj.progress) != "function"){return;};var progress=(e.loaded/e.total*100).toFixed(2);obj.progress(progress);},false)
	xhr.upload.addEventListener('load', function(e){if(obj.load==null||typeof(obj.load) != "function"){return;};obj.load(e);}, false);
	xhr.upload.addEventListener('error', function(e){if(obj.error==null|typeof(obj.error) != "function"){return;};obj.error(e);}, false);
	xhr.onreadystatechange = function(e){
		if( e.target.readyState == 4 && e.target.status == 200){
			if(obj.success==null||typeof(obj.success) != "function"){return;};
			obj.success(eval("("+e.target.response+")"));
			}};
	xhr.send(formData);
			
}
//单文件
var hfu_ajax=function(obj){
	if(obj==null||obj.url===undefined||obj.url==""||obj.url==null){
		throw new Error("请求链接不能为空！")
	}
	var xhr =new XMLHttpRequest();
	var form= document.getElementById(obj.fromId);
	var formData=new FormData(form);
	xhr.open('POST',obj.url);
	xhr.upload.addEventListener('progress',function(e){if(obj.progress==null||typeof(obj.progress) != "function"){return;};var progress=(e.loaded/e.total*100).toFixed(2);obj.progress(progress);},false)
	xhr.upload.addEventListener('load', function(e){if(obj.load==null||typeof(obj.load) != "function"){return;};obj.load(e);}, false);
	xhr.upload.addEventListener('error', function(e){if(obj.error==null|typeof(obj.error) != "function"){return;};obj.error(e);}, false);
	xhr.onreadystatechange = function(e){
		if( e.target.readyState == 4 && e.target.status == 200){
			if(obj.success==null||typeof(obj.success) != "function"){return;};
			obj.success(eval("("+e.target.response+")"));
			}};
	xhr.send(formData);
			
}