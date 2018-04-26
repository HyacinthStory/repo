<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/tag.jsp" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图片管理</title>
<script src="${ctx}/js/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<style type="text/css">
.hidden{
	display: none;
}
body {
	front:SimSun;
}
#box-main{
	border: 4px solid #FF9933; margin: 16px;display:none;
}
.clear{
	clear: both;
}
.img_item{
	float: left; margin: 20px; border: 1px solid #ccc; width: 180px;
}
.img_item img{
	float: left; height: 150px; width: 180px;border: none;
}
.img_item a{
	float: right; margin: 5px 0 5px 5px; text-decoration: none; border: 1px solid #ccc;  line-height: 26px; padding:  0 5px;
	background-color: #ccc; color: #696969; cursor: pointer;
}
.img_item a:hover{ border:1px solid #222 }
.mainimg{
	color: #FF9933;float: right;font-weight: bold; margin: 5px 0 5px 5px; font-size: 14px;line-height: 26px;
}


.btns{
	margin-left: 20px;
}
.btns button input{
	font-size: 16px;font-family: Microsoft YaHei;line-height: 26px; padding: 0px 8px;
}

#upload{ font-size: 16px;  padding: 6px; }

.btnshow{ width: 100px; height: 30px; line-height: 30px; vertical-align: middle; font-size: 30px;  margin: 10px 0 0 0;}
#uploadin{ opacity: 0; filter:alpha(opacity=0); z-index:10; margin-left: -110px}
.btnupload{ border: 1px solid #ccc; display: inline-block; ;
	text-align: center; z-index:11; font-size: 16px; background-color: #fff;}
</style>
</head>

<body>
   	<div class="btns">
   		<form id="uploadForm" action="${ctx}/${url_upload}" method="post" enctype="multipart/form-data">
   			<button id="upload" type="button">上传图片</button>
   			<div id="ieshow">
	   			<span class="btnupload btnshow " >上传图片</span>
	   	 		<input class="btnshow" name="otherimgs" id="uploadin" type="file" multiple  accept="image/bmp,image/png,image/GIF,image/jpg,image/jpeg">
   			</div>
   		</form>
   	</div>
   	<div>
   	<input type="hidden" id="imgname" value="${imgname}">
   	<c:if test="${imgname!='' && imgname!=null }">
	   	<div class="img_item" id="box-main">
	   			<img id="main_img" alt="" src="${path }/${imgname}">
	   		<%-- <c:if test="${imgname=='' || imgname==null }">
	   			<img id="main_img" alt="" src="">
	   		</c:if> --%>
	   		<%-- <a onclick="delFile('${imgname }',this)" id="mbtn" href="javascript:" >删除</a><span class="mainimg">主图片</span> --%>
	   		<a id="mbtn" href='${ctx}/${url_delete}&imgName=${imgname}'>删除</a><span class="mainimg">主图片</span>
	   	</div>
   	</c:if> 
    <c:forEach items="${otherimgs}" var="img" varStatus="status" >  
    	<c:if test="${img!='' && img!=null }">
    		<div class="img_item">
		   		<img alt="" src="${path }/${img}">
		   		<%-- <a onclick="delFile('${img }',this)" href="javascript:">删除</a>
		   		<a onclick="setMain('${img }',this)" href="javascript:">设置为主图片</a> --%>
		   		<a href='${ctx}/${url_delete}&imgName=${img}'>删除</a>
		   		<a href='${ctx}/${url_setmain}&imgName=${img}'>设置为主图片</a>
		   	</div>
    	</c:if>             	
   	</c:forEach>
   	
   	<script type="text/javascript">
   		window.onload=function(){
   			var imgname=$("#imgname").val();
   			if(imgname!='' && imgname!=null){
   				$("#box-main").css("display","block");
   			}
   		}
   		
		$('#upload').click(function(){
			$('#uploadin').click();
		});
		$('#uploadin').change(function(){
			var filepath=$(this).val();
			var extStart=filepath.lastIndexOf(".");
			var ext=filepath.substring(extStart,filepath.length).toUpperCase();
			if('.BMP.bmp.PNG.png.GIF.gif.JPG.jpg.JPEG.jpeg'.indexOf(ext)<0){
				alert('图片限于png,gif,jpeg,jpg格式');
				return;
			}
			$('#uploadin').after('正在上传中...').css('display','none');
			$('#upload').after('正在上传中...').css('display','none');
			$('#uploadForm').submit();
			
		});
			
   		
   		/* function setMain(img,p){
   			$.ajax({
   				url:'${ctx}/${url_setmain}',
   				data:'imgName='+img,
  				success:function(data){
  					if(data && data==='Y'){
  						console.log("设置主图片成功!");
  					}
  					$("#box-main").css("display","block")
   					var m=$('#main_img');
   					var oimg=$(p).siblings('img');
   					var msrc=m.attr('src');
   					m.attr('src',oimg.attr('src'));
   					if(msrc!=null && msrc!=''){
   						//对主图片路径进行分析，如果有指定后缀(只判断.)，则把该路径放在其他图片路径中，否则删除其他图片div
   						var srcSuffixIndex=msrc.lastIndexOf(".");
   						if(srcSuffixIndex>0){
   							oimg.attr('src',msrc);
   						}else{
   							$(p).parent().remove();
   						}
   					}else{
   						$(p).parent().remove();
   					}
   				},
   				error: function(){
   					alert('操作失败');
   				}
   			})
   		}
   		
   		function delFile(img,p){
   			$.ajax({
   				url:'${ctx}/${url_delete}&imgName='+img,
   				success:function(data){
	   				if($(p).attr('id')=='mbtn'){
	   					$(p).siblings('img').attr('src','');
	   					$(p).parent().remove()
	   				}else{
	   					$(p).parent().remove();
	   				}
   				},
   				error:function(){
   					alert('删除失败');
   				}
   			})
   		} */
   		
   		    if (!!window.ActiveXObject || "ActiveXObject" in window)  {
   		    	$('#upload').remove();
   		    }
   		    else  {
   		       $('#ieshow').css('display','none');
   		    }
   	
   		
   	</script>
  </div> 
</body>
</html>