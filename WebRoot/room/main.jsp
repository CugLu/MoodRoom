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

<title>My JSP 'main.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<script type="text/javascript">
function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();//大多数浏览器
	} catch (e) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
}

function send(Spid) {
	var xmlHttp = createXMLHttpRequest();
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			
			var div = document.getElementById(Spid+Spid);
			div.innerHTML = "赞："+xmlHttp.responseText;
		}
	};
	xmlHttp.open("Post", "/MoodRoom/PostServlet?method=changeZan", true);
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	var div = document.getElementById(Spid);
	var args="pid="+Spid+"&text="+div.innerHTML;
	xmlHttp.send(args);
	
	if(div.innerHTML=="赞一个")
		div.innerHTML="取消赞";
	else
		div.innerHTML="赞一个";
}
</script>

</head>

<body>
	<h1 align="center">这里是主页</h1>
	<br/>
	<c:choose>
		<c:when test="${empty sessionScope.session_user}">
			<div align="center">
				<a href="<c:url value='/users/login.jsp'/>" target="_parent">登录</a> |&nbsp; 
				<a href="<c:url value='/users/regist.jsp'/>" target="_parent">注册</a>
			</div>
		</c:when>
		<c:otherwise>
			<div align="center">
				欢迎您：${sessionScope.session_user.uname }&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="<c:url value='/PostServlet?method=findUserPost&uname=${sessionScope.session_user.uname }'/>">个人中心</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="<c:url value='/room/post.jsp'/>">发帖</a>
			</div>
		</c:otherwise>
	</c:choose>
	<hr/>
	<c:forEach items="${pb.beanList }" var="post">
		<div class="icon" align="center">
			图片：<br/>
			<a href="<c:url value='/PostServlet?method=load&pid=${post.pid }'/>"><img src="<c:url value='/${post.pimage }'/>" width="30%" height="300" border="0"/></a>
      		<br/>
   			标题：<a href="<c:url value='/PostServlet?method=load&pid=${post.pid }'/>">${post.ptitle }</a>
   			<br/>
   			楼主：${post.uname }<br/>
   			评论数：${post.pcomment }<br/>
   			<h2 id="${post.pid }${post.pid }">赞：${post.pzan }</h2>
   			时间：${post.ptime }<br/>
		</div>
		<div align="center">
		<button id="zan" style="background-image:url('<c:url value='/image/zan.jpg' />');background-repeat:no-repeat" onclick="send('${post.pid }')"><h3 id="${post.pid }">赞一个</h3></button>
		</div>
		<hr/>
	</c:forEach>
<%--
给出分页相差的链接
 --%>	
<center>
第${pb.pc }页/共${pb.tp }页

<a href="${pb.url }&pc=1">首页</a>
<c:if test="${pb.pc > 1 }">
<a href="${pb.url }&pc=${pb.pc-1}">上一页</a>
</c:if>

<%-- 计算begin、end --%>
<c:choose>
	<%-- 如果总页数不足10页，那么把所有的页数都显示出来！ --%>
	<c:when test="${pb.tp <= 10 }">
		<c:set var="begin" value="1" />
		<c:set var="end" value="${pb.tp }" />
	</c:when>
	<c:otherwise>
		<%-- 当总页数>10时，通过公式计算出begin和end --%>
		<c:set var="begin" value="${pb.pc-5 }" />
		<c:set var="end" value="${pb.pc+4 }" />	
		<%-- 头溢出 --%>
		<c:if test="${begin < 1 }">
			<c:set var="begin" value="1" />
			<c:set var="end" value="10" />
		</c:if>	
		<%-- 尾溢出 --%>
		<c:if test="${end > pb.tp }">
			<c:set var="begin" value="${pb.tp - 9 }" />
			<c:set var="end" value="${pb.tp }" />
		</c:if>	
	</c:otherwise>
</c:choose>
<%-- 循环遍历页码列表 --%>
<c:forEach var="i" begin="${begin }" end="${end }">
	<c:choose>
		<c:when test="${i eq pb.pc }">
			[${i }]
		</c:when>
		<c:otherwise>
			<a href="${pb.url }&pc=${i}">[${i }]</a>	
		</c:otherwise>
	</c:choose>
	
</c:forEach>

<c:if test="${pb.pc < pb.tp }">
<a href="${pb.url }&pc=${pb.pc+1}">下一页</a>
</c:if>
<a href="${pb.url }&pc=${pb.tp}">尾页</a>
</center>

</body>
</html>
