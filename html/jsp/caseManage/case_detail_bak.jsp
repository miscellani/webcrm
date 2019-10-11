<%@page import="test.web.cases.bean.WebCaseBean"%>
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

		<title>CASE详情</title>

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
	
	</head>

	<body>
		<form action=" servlet/CaseUpdateServlet "
			method="get" name="form2" onSubmit="return checkForm('form2');">
			<%
				WebCaseBean caseinfo = (WebCaseBean) request
						.getAttribute("caseinfo");
			%>

			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						用例详情
						<input type="hidden" name="caseid"
							value="<%=Integer.toString(caseinfo.getCaseId())%>" />
					</td>
				</tr>
				
				
					<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							用例主编号：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style=" width: 145px"   valid=""   errmsg=""  name="maincaseid"
							value="<%=caseinfo.getMainCaseId()%>">
						&nbsp;
					</td>
					<td bgcolor="#FFFDF0">
						<div align="center">
							用例子编号：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style=" width: 145px"  maxlength="50" name="childcaseid"
							value="<%=caseinfo.getChildCaseid()%>">
						&nbsp;
					</td>
				</tr>
				

				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							平台名称：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style=" width: 145px"  name="module"
							value="<%=caseinfo.getModule()%>">
					</td>
	
									<td bgcolor="#FFFDF0">
                        <div align="center">
                            模块类型：
                        </div>
                    </td>
                    <td bgcolor="#FFFFFF">
                          <select name="type" style="width: 145px" >
                          <option align="left" value="T模块">T模块</option>
                          <option align="left" value="S模块">S模块</option>
                          <option align="left" value="R模块">R模块</option>
                          <option align="left" value="星状网">星状网</option>

                             </select>            
                       </td>
                       
					
				</tr>

				<tr>
				
				                       
                                        

					<td bgcolor="#FFFDF0">
						<div align="center">
							测试人员：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style=" width: 145px"   name="tester"
							value="<%=caseinfo.getTester()%>">
					</td>

                				<td bgcolor="#FFFDF0">
						<div align="center">
							用例级别：
						</div>
					</td>
                    <td bgcolor="#FFFFFF">
                          <select name="caselevel" style="width: 145px" >
      					<option align="left" value="1">1级</option>
     				 	<option align="left" value="2">2级</option>
     				 	<option align="left" value="3">3级</option>

   				          </select>            
   				    </td>       
                       

				</tr>



				
			<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							访问地址：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<input name="url"
							style="width: 100%; resize: none;"  value=""></input>
					</td>
				</tr>				
 
               			<tr> 
       					<td bgcolor="#FFFDF0">
						<div align="center">
							用例描述：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<input style="width: 100%; resize: none;"  name="casename"
							value="<%=caseinfo.getCasename()%>">
						
					</td>
       		</tr>
       		
       		
       		
       				<tr>
       				
       				
					<td bgcolor="#FFFDF0">
						<div align="center">
							入参：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<input name="inputparam"
							style="width: 100%; resize: none;"  value=<%if(caseinfo.getInputparam()==null){%><%}else{%><%=caseinfo.getInputparam()%><%}%>>
			
		</input>
					</td>
				</tr>				
				
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							出参：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<input name="outputparam"
							style="width: 100%; resize: none;"  value=<%if(caseinfo.getOutputparam()==null){%><%}else{%><%=caseinfo.getOutputparam()%><%}%>></input>
					</td>
				</tr>
				
				         
                
                
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							初始报文头：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<textarea rows="40" name="inputhead"
							style="width: 100%; resize: none;"><%if(caseinfo.getInputhead()==null){%><%}else{%><%=caseinfo.getInputhead()%><%}%></textarea>
					</td>
										<td bgcolor="#FFFDF0">
						<div align="center">
							初始报文体：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<textarea rows="40" name="inputbody"
							style="width: 100%; resize: none;"><%=caseinfo.getInputbody()%></textarea>
					</td>
				</tr>
				
	



							
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							检查点：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<textarea rows="10" name="checkpoint"
							style="width: 100%; resize: none;"><%if(caseinfo.getCheckpoint()==null){%><%}else{%><%=caseinfo.getCheckpoint()%><%}%>%></textarea>
		
					</td>
				</tr>
				
				
				
			</table>
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="36%"></td>
					<td width="17%"><input type="submit" name="submit"  value="修改CASE"></td>
					<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					<td width="43%"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
