<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(basePath);

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
  <head>
    <base href="<%=basePath%>">
    <title>自动化测试平台_用户登录</title>
    <script type="text/javascript">
	var errori ='<%=request.getParameter("error")%>';
	if(errori=="yes"){
		alert("用户或密码错误！请重新输入!!!");
	}
    </script>


<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #FFFFFF;
	overflow:hidden;
}
.STYLE1 {
	color: #000000;
	font-size: 12px;
}
#in1{
	background-image: url('<%=basePath%>resource/images/dl_2.png');
	height:18px;
	width:49px;
	border: 0px;
}
-->
</style>
<script language="JavaScript" type="text/javascript" src="<%=basePath%>resource/js/FormValid.js"></script>
  <script language="JavaScript" type="text/javascript">

</script>	
  </head>
  
<body bgcolor=red>
 <form name="from1" action="<%=basePath%>servlet/LoginCheckServlet" method ="post"  onsubmit="return validator(this);" >
   <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="962" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="235" background="<%=basePath%>resource/images/login/login_01.png"></td>
      </tr>
      <tr>
        <td height="53"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="394" height="53" ></td>
            <td width="206" "><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="15%" height="25"><div align="right"><span class="STYLE1">用户</span></div></td>
                <td width="57%" height="25"><div align="center">
                <!-- valid="required|regexp" regexp="^[A-Za-z0-9]+$" errmsg="用户名不能为空1!|账号只能由字母和数字组成!" -->
                  <input id="123" type="text" name="userAdmin"    style="width:105px; height:17px; background-color:#FFFFFF; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
                </div></td>
                <td width="27%" height="25"></td>
              </tr>
              <tr>
                <td height="25"><div align="right"><span class="STYLE1">密码</span></div></td>
                <td height="25"><div align="center">
                  <input type="password" name="userPass" valid="required"   errmsg="密码不能为空1!" style="width:105px; height:17px; background-color:#FFFFFF; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
                </div></td>
                <td height="25"><div align="left">
                   <input type="submit" id="in1" value="" background="<%=basePath%>resource/images/dl.gif"></div></td>
              </tr>
            </table></td>
            <td width="362" ></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="213">
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>

<script type="text/javascript" src="<%=basePath%>resource/js/CheckForm.js"></script>
  </body>

</html>
