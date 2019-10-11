<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>运维主界面</title>

  </head>
    <!-- frameset  border="2" bordercolor="000000"  中间格子设置 -->
 <frameset rows="70,*"     scrolling="Yes" noresize="noresize"  framespacing="2" border="0" bordercolor="#000000" >
	<frame frameborder="10" src="<%=basePath%>jsp/top.jsp?" name="topFrame" scrolling="No"  noresize="noresize" id="topFrame" />
	<frameset cols="166,*"> 
		<frame src="<%=basePath%>jsp/left.jsp" bordercolor="#AAAAAA"  frameborder="0" noresize="noresize" />
		<frame src="<%=basePath%>servlet/WorkQueryServlet"  name="mainFrame"  frameborder="0" scrolling="Yes" />
	</frameset>
</frameset>
</html>
