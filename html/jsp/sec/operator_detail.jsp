<%@page import="test.sec.bean.WebAdminBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


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

		<title>查询操作员信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<LINK href="<%=basePath%>resource/css/admin.css" type=text/css
			rel=stylesheet>
<script type="text/javascript"
			src="<%=basePath%>resource/js/CheckForm.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>resource/js/My97DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=basePath%>resource/js/FormValid.js"></script>
	</head>

	<body>
	  <%
						WebAdminBean adminInfo = (WebAdminBean)request.getAttribute("adminInfo");

						
	%>
		<form action="<%=basePath%>servlet/OperatorUpdateServlet"  name="form1" onsubmit="return validator(this)"   method="get"
			name="form2" onsubmit="return checkForm('form2');">
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						查看操作员信息
						<input type="hidden" name="operatorId"
							value="<%=Integer.toString(adminInfo.getOperatorId())%>" />
						
					</td>
				</tr>

				<tr>
					<td width="13%" bgcolor="#FFFDF0">
						<div align="center">
							账号：
						</div>
					</td>
					<td width="41%" colspan="3" bgcolor="#FFFFFF">
						<input readonly type="text" name="userAdmin" value="<%=adminInfo.getUserAdmin()%>"  maxlength="20" style="width: 145px"  errmsg="账号不能为空!">
					</td>
					
					<td bgcolor="#FFFDF0">
						<div align="center" >
							密码：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input readonly type="password" name="userPass" value="<%=adminInfo.getUserPass()%>"  maxlength="20" style="width: 145px"   errmsg="密码不能为空!">
						&nbsp;
					</td>
					
					
					

				</tr>
				<tr>
					<td width="13%" bgcolor="#FFFDF0">
						<div align="center">
							姓名：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input readonly type="text" name="userName" value="<%=adminInfo.getUserName()%>"  maxlength="10" style="width: 145px"  errmsg="员工姓名不能为空!" >
						&nbsp;
					</td>

					<td bgcolor="#FFFDF0">
						<div align="center">
							手机：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input readonly type="text" style="width: 145px"  value="<%=adminInfo.getUserMobile()%>"  regexp="^1[3|4|5|8][0-9]\d{8}$"   errmsg="请输入正确的手机号码!"  name="userMobile">
						&nbsp;
					</td>
				</tr>




				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							工号：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input readonly type="text" style="width: 145px"  value="<%=adminInfo.getUserStaff()%>"    errmsg="请输入正确的工号!"  maxlength="20"  name="userStaff" >
						&nbsp;
					</td>
					
				<td bgcolor="#FFFDF0">
						<div align="center">
							E_mail：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input readonly type="text" style="width: 145px" value="<%=adminInfo.getUserEmail()%>"   errmsg="Email格式不对!" name="userEmail">
						&nbsp;
					</td>

				</tr>

				

				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							使用状态：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="isUsed" style="width: 145px">
      					 <option align="left" value="<%=adminInfo.getIsUsed()%>"><%if(adminInfo.getIsUsed().equals("0")){%>无效<%}else{%>有效<% }%></option>
      					

   				 </select>            
					</td>
	
				</tr>

	
			</table>
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="25%"></td>
					<td width="17%"><input type="submit" name="submit"  value="修改"></td>
					<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					<td width="43%"></td>
				</tr>
			</table>

		</form>
	</body>
</html>
