<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>中心业务运维平台</title>

  </head>
  
  <body>
  <iframe src="<%=basePath%>jsp/work/work_frame.jsp"  scrolling="Yes" style="height:200%;width:100%;border-width:0px;"></iframe>
  </body>

</html>
