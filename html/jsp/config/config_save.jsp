<%@page import="test.sec.bean.WebAdminBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>My JSP 'customerCondition_save.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<%
						WebAdminBean  adminInfo  =   (WebAdminBean)request.getSession().getAttribute("adminInfo");
	%>
		<script type="text/javascript">
		
			alert('${result}');
			window.location="<%=basePath%>servlet/ConfigQueryServlet?userId=<%= adminInfo.getOperatorId()  %> ";
			/*等同于超链接 <a>*/
		</script>

	</head>

</html>
