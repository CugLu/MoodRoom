<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'post.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	发帖啦！！！
	<br>
	${msg }
	<form action="<c:url value='/PostServlet?method=add'/>" method="post" enctype="multipart/form-data">
		帖子标题：<input style="width: 150px; height: 20px;" type="text" name="ptitle"/><br/>
    	帖子图片：<input style="width: 200px; height: 20px;" type="file" name="pimage"/><br/>
    	帖子内容：<br/>
    	<textarea rows="25" cols="40" name="pcontent"></textarea><br/>
    	<input type="submit" value="发帖啦"/>
	</form>
	
</body>
</html>
