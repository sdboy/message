<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>404</title> 
<style type="text/css"> 
*{ padding:0px; margin:0px;} 
html,body{ height:100%;} 
#wrapper{ min-height:100%; text-align:center; font-size:30px; font-family:Arial, Helvetica, sans-serif; position:relative;} 
*html #wrapper{ height:100%;} 
#inner{ width:280px; height:320px; text-align:center; position:absolute; top:50%; margin-top:-160px; left:50%; margin-left:-140px; border:0px solid red} 
	#tip1{
		color:#484848;
		font-size:14px;
		line-height:14px;
		height:14px;
		margin-top:60px;
		border:0px solid red;
	}
	#tip2{
		color:#999999;
		font-size:12px;
		line-height:30px;
		height:30px;
		margin-bottom:10px;
		border:0px solid red;
	}
</style> 
</head> 
   
<body> 
<s:actionerror theme="custom" cssClass="error" />
<div id="wrapper"> 
	<div id="inner"> 
		<img align="absmiddle" src="../image/404.png" >
		<div id="tip1">抱歉，您访问的地址不存在!</div>
		<div id="tip2"></div>
	</div> 
</div> 
<p style="display:none">
	输出异常对象本身:<s:property value="exception.message"/>
	输出异常堆栈信息:<s:property value="exceptionStack" />
</p>
</body> 
</html>