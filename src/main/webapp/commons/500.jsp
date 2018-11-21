<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title></title>
<style>
.error{
	position: absolute;
	top: 50%;
	left: 50%;
	width: 300px;
	height: 300px;
	margin-top: -150px;
	margin-left: -150px;
}
</style>
</head>
<body>
<s:actionerror theme="custom" cssClass="error" />
<div class="error">
	<img src="../image/error.png" />
</div>
<p style="display:none">
	输出异常对象本身:<s:property value="exception.message"/>
	输出异常堆栈信息:<s:property value="exceptionStack" />
</p>
</body>
</html>