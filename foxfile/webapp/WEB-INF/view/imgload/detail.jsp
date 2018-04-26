<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/comm/tag.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图片管理</title>
<script src="${ctx}/js/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<style type="text/css">

.hidden {
	display: none;
}

body {
	front: SimSun;
}

#box-main {
	border: 4px solid #FF9933;
	margin: 16px;
}

.clear {
	clear: both;
}

.img_item {
	float: left;
	margin: 20px;
	border: 1px solid #ccc;
	width: 180px;
}

.img_item img {
	float: left;
	height: 150px;
	width: 180px;
	border: none;
}

.img_item a {
	float: right;
	margin: 5px 0 5px 5px;
	text-decoration: none;
	border: 1px solid #ccc;
	line-height: 26px;
	padding: 0 5px;
	background-color: #ccc;
	color: #696969;
	cursor: pointer;
}

.img_item a:hover {
	border: 1px solid #222
}

.mainimg {
	color: #FF9933;
	float: right;
	font-weight: bold;
	margin: 5px 0 5px 5px;
	font-size: 14px;
	line-height: 26px;
}

.subimg {
	margin-bottom: 60px;
}

.btns {
	margin-left: 20px;
}

.btns button input {
	font-size: 16px;
	font-family: Microsoft YaHei;
	line-height: 26px;
	padding: 0px 8px;
}

#upload {
	font-size: 16px;
	padding: 6px;
}

.btnshow {
	width: 100px;
	height: 30px;
	line-height: 30px;
	vertical-align: middle;
	font-size: 30px;
	margin: 10px 0 0 0;
}

#uploadin {
	opacity: 0;
	filter: alpha(opacity = 0);
	z-index: 10;
	margin-left: -110px
}

.btnupload {
	border: 1px solid #ccc;
	display: inline-block;;
	text-align: center;
	z-index: 11;
	font-size: 16px;
	background-color: #fff;
}
</style>
</head>

<body>
	<div>
		<c:if test="${imgname!='' && imgname!=null}">
			<div class="img_item" id="box-main">
					<img id="main_img" alt="" src="${path}/${imgname}">
					<a href="${path}/${imgname}" download>下载</a>
					<span class="mainimg">主图片</span>
			</div>
		</c:if>
		<c:forEach items="${otherimgs}" var="img" varStatus="status">
			<c:if test="${img!='' && img!=null}">
				<div class="img_item subimg" id="box-others">
					<img alt="" src="${path}/${img}"> 
					<a href="${path}/${img}" download>下载</a>
				</div>
			</c:if>
		</c:forEach>
	</div>
</body>
</html>