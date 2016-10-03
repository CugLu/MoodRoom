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

<title>My JSP 'regist.jsp' starting page</title>

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
	注册！不注册是不行的~
	<br>
	${msg }
	<form action="<c:url value='/UserServlet' />" method="post">
		<input type="hidden" name="method" value="regist" />
		用户名：<input type="text" name="uname" value="${form.uname}" />${errors.username}<br/>
		密码：<input type="password" name="upassword" value="${form.upassword}" />${errors.password}<br/>
		重复密码：<input type="password" name="repassword" />${errors.repassword}<br/>
		邮箱：<input type="text" name="uemail" value="${form.uemail }">${errors.email}<br/>
		<input type="submit" value="注册" />
	</form>
</body>                                                                                                                                                                                                  
</html>
