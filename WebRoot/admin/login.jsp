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
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
	<h1>管理员登录</h1>
	${msg }
	<br>
	<form action="<c:url value='/AdminUserServlet' />" method="post">
		<input type="hidden" name="method" value="login" />
		管理员用户名：<input type="text" name="username" value="${username }" />${errors.username}<br/>
		管理员密码：<input type="password" name="password" value="${password }" />${errors.username}<br/>
		<input type="submit" value="登录" />
	</form>
</body>
</html>
