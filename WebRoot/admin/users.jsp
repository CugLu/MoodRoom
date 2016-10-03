<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'users.jsp' starting page</title>

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
	<h1 align="center">用户管理</h1>
	<br>
	<hr/>
	
	<h2 align="center">活跃用户</h2>
	<c:forEach items="${activeUserList }" var="activeUser">
		<div align="center">
			用户名：${activeUser.uname }&nbsp;&nbsp;|&nbsp;&nbsp;
			email：${activeUser.uemail }&nbsp;&nbsp;|&nbsp;&nbsp;
			帖子数：${activeUser.postnumber }&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<c:url value='AdminUserServlet?method=delete&uid=${activeUser.uid }'/>">删除</a>
		</div>
		<br/>
	</c:forEach>
	
	<br/>
	<hr/>
	
	<h2 align="center">不活跃用户</h2>
	<c:forEach items="${unactiveUserList }" var="unactiveUser">
		<div align="center">
			用户名：${unactiveUser.uname }&nbsp;&nbsp;|&nbsp;&nbsp;
			email：${unactiveUser.uemail }&nbsp;&nbsp;|&nbsp;&nbsp;
			帖子数：${unactiveUser.postnumber }&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<c:url value='AdminUserServlet?method=delete&uid=${unactiveUser.uid }'/>">删除</a>
		</div>
		<br/>
	</c:forEach>
	
	<hr/>
</body>
</html>
