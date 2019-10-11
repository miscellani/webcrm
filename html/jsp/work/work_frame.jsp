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
  <!-- frameset  border="2" bordercolor="000000"  中间格子设置 -->
 <frameset rows="50%,50%,50%"  border="5" bordercolor="FFD700" width="1000px" noresize="noresize"  framespacing="0">
 
	<frameset cols="70%,30%" border="5" bordercolor="FFD700"> 
			    <frame src="<%=basePath%>servlet/WorkOperateServlet"  scrolling="Yes" name="mainFrame"/>
				<frame src="<%=basePath%>servlet/WorkLogServlet"   scrolling="Yes" name="mainFrame"/>
	
	</frameset>	
	    
	
		    <frame width="1000px" src="<%=basePath%>servlet/WorkObserveOneServlet"  scrolling="No" noresize="noresize" />

	
	
		    <frame src="<%=basePath%>servlet/WorkObserveTwoServlet"  scrolling="No" noresize="noresize" />			

	
	
</frameset>



</html>
