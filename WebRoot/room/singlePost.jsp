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
    
    <title>My JSP 'singlePost.jsp' starting page</title>
    
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
    <h1 align="center">hello!这个帖子~，你自己看吧</h1>
    <br/>
    <div align="center"><a href="<c:url value='/PostServlet?method=findAll' />">回到主页</a></div>
    <br/>
    <div class="icon" align="center" border="1">
	图片：<br/>
	<img src="<c:url value='/${post.pimage }'/>" width="30%" height="300" border="0"/>
     <br/>
   	标题：${post.ptitle }
    <br/>
    <div style="width:30%;background-color:#666;word-wrap:break-word; overflow:hidden;">内容：${post.pcontent }</div>
   	楼主：${post.uname }
   	<br/>
   	评论数：${post.pcomment }<br/>
   	赞：${post.pzan }<br/>
   	时间：${post.ptime }<br/>
   	</div>
   	
   	<br/>
   	<h3 align="center">评论</h3>
   	<c:forEach items="${commentList }" var="comment">
   		<div style="width:30%;background-color:#666;word-wrap:break-word; overflow:hidden;margin:0 auto;">
   			${comment.uname }:${comment.ccomment }
   		</div>
   		<br/>
   	</c:forEach>
   	
   	<br/>
   	<h6 align="center">评论啦</h6><br/>
   	<div class="icon" align="center" border="1">
   	<form action="<c:url value='/CommentServlet?method=add&pid=${post.pid }'/>" method="post">
    	<textarea rows="10" cols="30" name="ccomment"></textarea><h6 align="center">${errors.Comment }</h6><br/>
    	<input type="submit" value="评论"/>
	</form>
	</div>
  </body>
</html>
