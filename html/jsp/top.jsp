<%@page import="test.sec.bean.WebAdminBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<script language=JavaScript>
		var timerID = null;
		var timerRunning = false;
		function stopclock (){
		if(timerRunning)
		clearTimeout(timerID);
		timerRunning = false;}
		function startclock () {
		stopclock();
		showtime();}
		function showtime () {
		var now = new Date();
		var months = now.getMonth()+1;
		var days = now.getDate();
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds()
		var timeValue =now.getFullYear()
		timeValue +="-"+months+"-"+days+" "
		timeValue += "" +((hours >= 12) ? "下午 " : "上午 " )
		timeValue += ((hours >12) ? hours -12 :hours)
		timeValue += ((minutes < 10) ? ":0" : ":") + minutes
		timeValue += ((seconds < 10) ? ":0" : ":") + seconds
		document.clock.thetime.value = timeValue;
		timerID = setTimeout("showtime()",1000);
		timerRunning = true;}


function exit(){

}
</SCRIPT>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}

.STYLE1 {
	font-size: 12px;
	color: #FFD700;
}

a {
	text-decoration: none;
	color: #FFD700;
	font-size: 12px;
}

.STYLE2 {
	font-size: 9px
}

.STYLE3 {
	color: #FFD700;
	font-size: 12px;
}

    input { 
    background:#FFFFFF;
    
    }


-->
</style>

	</head>


	<body bgcolor="#000000" onload=startclock()>
				<%
						WebAdminBean adminInfo = (WebAdminBean)request.getSession().getAttribute("adminInfo");
			 %>
		<form name=clock>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			
				<tr height="17">			
				</tr>			
			
				<tr>
				<td width="9%" height="17"></td>																		
		        <td width="77%" height="17" valign="bottom">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="5%" >
																		<div align="center">
																			<a href="<%=basePath%>servlet/WorkQueryServlet"
																				target="mainFrame">
																				&nbsp;&nbsp;首页
																			</a>
																		</div>
																	</td>
																	<td width="5%">
																		<div align="center">
																			<a href="<%=basePath%>login.jsp" target="_parent">
																			退出
																			</a>
																		</div>
																	</td>																	
																	<td width="10%"  >
															          <div align="center">
																        <a>
																                当前时间：
																        </a>
																        <td width="10%">
																          <input color="#FFFFFF" disabled="disabled" name=thetime
																		  style="border:0px solid #FFD700"
																		  size=25 >
																        </td>
															      
															         </div>
														           	</td>
														           	<td width="10%" >
															          <div align="center">
																        <a>
																                操作员：
																        </a>
																        <td width="10%">
																          <input color="#FFFFFF" disabled="disabled" value="<%=adminInfo.getUserAdmin()%>"
																		  style="border:0px solid #FFD700"
																		  size=25 >
																        </td>
															      
															         </div>
														           	</td>
														           																
														            <td>&nbsp;
														            </td>
																</tr>
															</table>
				</td>
														
														
														

				</tr>
		
				<tr height="17">
				</tr>
				
			</table>
		</form>
	</body>
</html>
