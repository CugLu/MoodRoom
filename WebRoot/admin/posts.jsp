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

<title>My JSP 'posts.jsp' starting page</title>

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
	<h1 align="center">帖子管理</h1>
	<br>
	<hr/>
	
	<h2 align="center">hot-post</h2>
	<c:forEach items="${hotPostList }" var="hotPost">
		<div align="center">
			楼主：${hotPost.uname }&nbsp;&nbsp;|&nbsp;&nbsp;
			标题：${hotPost.ptitle }&nbsp;&nbsp;|&nbsp;&nbsp;
			赞：${hotPost.pzan }&nbsp;&nbsp;|&nbsp;&nbsp;
			评论：${hotPost.pcomment }&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<c:url value='AdminPostServlet?method=delete&pid=${hotPost.pid }'/>">删除</a>
		</div>
		<br/>
	</c:forEach>
	
	<br/>
	<hr/>
	
	<h2 align="center">cold-post</h2>
	<c:forEach items="${coldPostList }" var="coldPost">
		<div align="center">
			楼主：${coldPost.uname }&nbsp;&nbsp;|&nbsp;&nbsp;
			标题：${coldPost.ptitle }&nbsp;&nbsp;|&nbsp;&nbsp;
			赞：${coldPost.pzan }&nbsp;&nbsp;|&nbsp;&nbsp;
			评论：${coldPost.pcomment }&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<c:url value='AdminPostServlet?method=delete&pid=${coldPost.pid }'/>">删除</a>
		</div>
		<br/>
	</c:forEach>
	
	<hr/>
</body>
</html>
