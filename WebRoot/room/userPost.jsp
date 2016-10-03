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
    
    <title>My JSP 'userPost.jsp' starting page</title>
    
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
   	 <h1 align="center">这里是您的帖子，对，全是您的~</h1>
   	 <br/>
   	 <div align="center"><a href="<c:url value='/PostServlet?method=findAll' />">回到主页</a></div>
   	 <h2 align="center">帖子数：${sessionScope.session_user.postnumber }</h2>
  	 <c:forEach items="${userPostList }" var="post">
		<div class="icon" align="center" border="1">
			图片：<br/>
			<a href="<c:url value='/PostServlet?method=load&pid=${post.pid }'/>"><img src="<c:url value='/${post.pimage }'/>" width="30%" height="300" border="0"/></a>
      		<br/>
   			标题：<a href="<c:url value='/PostServlet?method=load&pid=${post.pid }'/>">${post.ptitle }</a>
   			<br/>
   			楼主：${post.uname }<br/>
   			评论数：${post.pcomment }<br/>
   			赞：${post.pzan }<br/>
   			时间：${post.ptime }<br/>
		</div>
		<hr/>
	 </c:forEach>
  </body>
</html>
