<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/tag.jsp" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>地推文件、图片管理</title>
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
   		<form id="uploadForm" action="${ctx}/uploadimgs.do?usefor=onlineLoan" method="post" enctype="multipart/form-data" >
   			<button id="upload" type="button">上传图片</button>
   			<div id="ieshow">
	   			<span class="btnupload btnshow " >上传图片</span>
	   	 		<input class="btnshow" name="otherimgs" id="uploadin" type="file" multiple  accept="image/bmp,image/png,image/GIF,image/jpg,image/jpeg">
   			</div>
   		</form>
   		<script type="text/javascript">
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
   		</script>
   	</div>
</body>
</html>