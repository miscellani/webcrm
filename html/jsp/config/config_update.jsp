
<%@page import="test.sec.bean.WebAdminBean"%>
<%@page import="test.config.bean.WebConfigBean"%>
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

		<title>更新配置信息</title>

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

		                WebAdminBean  adminInfo  =   (WebAdminBean)request.getSession().getAttribute("adminInfo");

						WebConfigBean configInfo = (WebConfigBean)request.getAttribute("configInfo");
						String paraType =configInfo.getParaType();
						String id = Integer.toString(configInfo.getId());
						String operate =(String)request.getAttribute("operate");
						System.out.println(operate);
	%>
	
	
		<form action="<%=basePath%>servlet/ConfigUpdateServlet?id=<%=id%>&operate=<%=operate%>"  name="form1" onsubmit="return validator(this)"   method="post"
			name="form2" onsubmit="return checkForm('form2');">
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						配置信息
						<input type="hidden" name="operatorId"
							value="" />
					</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							配置类型：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
                        <select id="1" name="paraType">
                         <option selected = "selected" value="numberkey" <%if (paraType!=null&&paraType.equals("numberkey")){%> selected = "true" <% } %> >号码配置</option>                    
                        <option value="runparam" <%if (paraType!=null&&paraType.equals("runparam")){%> selected = "true" <% } %> >运行配置</option>   
                        <option value="pageOp" <%if (paraType!=null&&paraType.equals("pageOp")){%> selected = "true" <% } %> >页面操作</option>                     
                        <option value="comOp" <%if (paraType!=null&&paraType.equals("comOp")){%> selected = "true" <% } %> >公共操作</option>      
      					<option value="dbOp" <%if (paraType!=null&&paraType.equals("dbOp")){%> selected = "true" <% } %> >数据库操作</option>     					
     				 	<option value="dbCheck" <%if (paraType!=null&&paraType.equals("dbCheck")){%> selected = "true" <% } %> >数据库校验</option>
      				 	<option value="pageCheck" <%if (paraType!=null&&paraType.equals("pageCheck")){%> selected = "true" <% } %> >页面校验</option>    				 	
     				 	<option value="dataUtil" <%if (paraType!=null&&paraType.equals("dataUtil")){%> selected = "true" <% } %> >数据操作</option>
     				 	
   				       </select>                        
					</td>
					
	
					<td bgcolor="#FFFDF0">
						<div align="center" >
							配置编码：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input placeholder="必填" type="text" name="paraCode" value="<%=configInfo.getParaCode()%>" maxlength="120" style="width: 300px" valid="required"  errmsg="配置编码不能为空">
						&nbsp;
					</td>
					
	
					

				</tr>
	
													<td bgcolor="#FFFDF0">
						<div align="center" >
							创建人：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input placeholder="必填" type="text" name="creater" value="<%=configInfo.getCreater()%>" maxlength="120" style="width: 150px" valid="required"  errmsg="创建人不能为空">
						&nbsp;
					</td>
					
				

				</tr>
				
				
							
				

					
					
					
					
					
					
					
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							配置说明：
						</div>
					</td>
					
					

					
					<td colspan="6" bgcolor="#FFFFFF">
						<textarea placeholder="必填，请说明清楚，编写提示用" rows="8" name="remark" valid="required"  errmsg="配置说明不能为空"
							style="width: 100%; resize: none;"><%=configInfo.getRemark()%></textarea>		
					</td>
					
					
				</tr>	
				
				
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							参数1(sql)：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<textarea placeholder="Sq语句，如: select t.user_id from ins1.um_susbcriber_region t where access_num='&#36;&#123;billid}&#125;'" rows="8" name="para1" valid="required"  errmsg="配置参数1不能为空"
							style="width: 100%; resize: none;"><%=configInfo.getPara1()%></textarea>		
					</td>
				</tr>				
				
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							参数2(入参)：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<textarea placeholder="入参，按顺序插入sql语句变量中" rows="8" name="para2"
							style="width: 100%; resize: none;"><%=configInfo.getPara2()%></textarea>		
					</td>
				</tr>					
				


				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							参数3(出参)：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<textarea placeholder="返回值类型" rows="8" name="para3"
							style="width: 100%; resize: none;"><%=configInfo.getPara3()%></textarea>		
					</td>
				</tr>	
				
				
				
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							参数4(缺省)：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<textarea placeholder="无" rows="8" name="para4"
							style="width: 100%; resize: none;"><%=configInfo.getPara4()%></textarea>		
					</td>
				</tr>	
				
								
			</table>
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="25%" ailgn="center"></td>

					<td  width="25%" ailgn="center"><input  <%if( (!operate.equals("add"))&&!adminInfo.getUserAdmin().equals("admin")&&(!adminInfo.getUserAdmin().equals(configInfo.getCreater()))){ %>disabled="true" <%} %>  type="submit" name="submit"   value="提交"></td>
					<td width="25%" ailgn="center"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					<td width="25%" ailgn="center"></td>

				</tr>
			</table>

		</form>
	</body>
</html>
