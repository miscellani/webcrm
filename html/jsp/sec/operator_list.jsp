<%@page import="test.sec.bean.WebAdminBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>操作员管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}

	a{
	text-decoration: none;
	color: #033d61;
	font-size: 12px;
}

A:hover {
	COLOR: #f60; TEXT-DECORATION: underline
}

-->
</style>

<script>
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}


	function add(){  
			window.location="<%=basePath%>servlet/UserAddServlet";
		}


</script>
<script>
var  highlightcolor='#c1ebff';
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
</script>
  </head>
  
  <body>
  
  <%
                  WebAdminBean  adminInfo  =   (WebAdminBean)request.getSession().getAttribute("adminInfo");
			 %>
  	<form  action="<%=basePath%>servlet/OperatorManageServlet" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">


	  <tr>
    <td height="30" background="<%=basePath%>resource/images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="<%=basePath%>resource/images/tab_03.gif" width="12" height="30" /></td>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">

        </table></td>
        <td width="16"><img src="<%=basePath%>resource/images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>


  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="<%=basePath%>resource/images/tab_12.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">账号</span></div></td>
            <td height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">姓名</span></div></td>
            <td height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF" style="width:5%"><div align="center"><span class="STYLE1">电话</span></div></td>
	    	<td height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF" style="width:5%"><div align="center"><span class="STYLE1">工号</span></div></td>
            <td height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">邮箱</span></div></td> 
	    	<td height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">状态</span></div></td>    
            <td height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF" class="STYLE1" style="width: 7%"><div align="center">基本操作</div></td>
          </tr>
          
          <% List<WebAdminBean> list = (List<WebAdminBean>)request.getAttribute("list");
			if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){ 
				WebAdminBean adminInfo1 = list.get(i);
			%>
          
          
          
          <tr>
	    	<td height="20" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1"><%=adminInfo1.getUserAdmin()%> </span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1"><%=adminInfo1.getUserName() %></span></div></td>
	    	<td height="20" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1"><%=adminInfo1.getUserMobile() %></span></div></td>
   	    	<td height="20" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1"><%=adminInfo1.getUserStaff() %> </span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1"><%=adminInfo1.getUserEmail() %></span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1"><% if(adminInfo1.getIsUsed().equals("0")){%>无效<%}else{%>有效<%} %></span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width: 7%"><div align="center"><span class="STYLE4">

            <img <% if  ( !adminInfo.getUserAdmin()  .equals( "admin") &&  !adminInfo1.getUserAdmin().equals(adminInfo.getUserAdmin() )  ) {%> style=" display: none  "  <%}%>   src="<%=basePath%>resource/images/edt.gif" width="16" height="16" />
            <a   <%  if  ( !adminInfo.getUserAdmin()  .equals( "admin") &&  !adminInfo1.getUserAdmin().equals(adminInfo.getUserAdmin() ) ) {%> style=" display: none  "  <%}%>    href="<%=basePath %>servlet/OperatorUpdateServlet?operatorId=<%=adminInfo1.getOperatorId()%>">编辑</a>&nbsp;
            
            <img    <%  if  ( !adminInfo.getUserAdmin()  .equals( "admin") &&  !adminInfo1.getUserAdmin().equals(adminInfo.getUserAdmin() ) ) {%> style=" display: none  "  <%}%>   src="<%=basePath%>resource/images/edt.gif" width="16" height="16" />
            <a  <%  if  ( !adminInfo.getUserAdmin()  .equals( "admin") &&  !adminInfo1.getUserAdmin().equals(adminInfo.getUserAdmin() ) ) {%> style=" display: none  "  <%}%>    href="<%=basePath %>servlet/OperatorQueryDetailServlet?operatorId=<%=adminInfo1.getOperatorId()%>">详情</a>&nbsp;
                       
            <img   <%  if  ( !adminInfo.getUserAdmin().equals( "admin")  ) {%> style=" display: none  "  <%}%>  src="<%=basePath%>resource/images/del.gif" width="16" height="16" />
            <a   <% if  ( !adminInfo.getUserAdmin()  .equals( "admin") )  {%> style=" display: none  "  <%}%>   href="<%=basePath %>servlet/OperatorDeleteServlet?operatorId=<%=adminInfo1.getOperatorId()%>">删除</a></span></div></td>
          
          
          
          
          </tr>
          <%}}else{ %>
							<tr>
								<td height="20" bgcolor="#FFFFFF" colspan="21"  align="center">
									<div align="center">
										<span class="STYLE1">没有员工相关信息</span>
									</div>
								</td>
							</tr>
		<%} %>
          </table></td>
        <td width="8" background="<%=basePath%>resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>

</table>
</form>
</body>
</html>
