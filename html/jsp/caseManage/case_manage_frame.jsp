<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String self = (String)request.getParameter("self");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>中心业务运维平台</title>

  </head>
  
 <frameset  rows="100%" frameborder="1" border="1"  bordercolor="#1873aa"     scrolling="No" noresize="noresize"  framespacing="0">
					
			 <frame  src="<%=basePath%>servlet/CaseQueryServlet?self=<%=self%>"   noresize="noresize" />
		     <!--<frame src="<%=basePath%>servlet/CaseDivideServlet"   noresize="noresize" />-->
			

	</frameset>

</html>
