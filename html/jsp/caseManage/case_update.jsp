<%@page import="test.sec.bean.WebAdminBean"%>
<%@page import="test.web.cases.bean.WebCaseBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.println(basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>用例详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<LINK href="<%=basePath%>resource/css/admin.css" type=text/css
			rel=stylesheet>
			
			
										<%
						WebAdminBean  adminInfo  =   (WebAdminBean)request.getSession().getAttribute("adminInfo");		
	%>
		
			<script type="text/javascript">	
			if('${result}'!=''){
				alert('${result}');

			}
			</script>
			<script type="text/javascript" src="<%=basePath%>resource/js/CheckForm.js"></script>

		<script type="text/javascript"
			src="<%=basePath%>resource/js/My97DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=basePath%>resource/js/FormValid.js"></script>
	</head>

	<body>
		<form action="<%=basePath%>servlet/CaseUpdateServlet"
			method="post" name="form2" onsubmit="return validator(this);" >
			<%
				WebCaseBean caseinfo = (WebCaseBean) request
						.getAttribute("caseinfo");

			%>




			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						用例详情
						<input type="hidden" name="caseId"
							value="<%=Integer.toString(caseinfo.getCaseId())%>" />
					</td>
				</tr>
				
				
	
			
								
					<tr>

					<td bgcolor="#FFFDF0">
						<div align="center">
							业务模块：
						</div>
					</td>
                    <td bgcolor="#FFFFFF">
                          <select name="module" style="width: 145px" >
                          <option align="left" value="so" <%if (caseinfo.getModule()!=null&&caseinfo.getModule().equals("so")){%> selected = "true" <% } %>>个人业务</option>
                          <option align="left" value="res" <%if (caseinfo.getModule()!=null&&caseinfo.getModule().equals("res")){%> selected = "true" <% } %>>资源管理</option>
                          <option align="left" value="sec" <%if (caseinfo.getModule()!=null&&caseinfo.getModule().equals("sec")){%> selected = "true" <% } %>>系统管理</option>
                          <option align="left" value="search" <%if (caseinfo.getModule()!=null&&caseinfo.getModule().equals("search")){%> selected = "true" <% } %>>综合查询</option>
                          <option align="left" value="cust" <%if (caseinfo.getModule()!=null&&caseinfo.getModule().equals("cust")){%> selected = "true" <% } %>>客户管理</option>
                          <option align="left" value="group" <%if (caseinfo.getModule()!=null&&caseinfo.getModule().equals("group")){%> selected = "true" <% } %>>集团业务</option>
                          <option align="left" value="terminal" <%if (caseinfo.getModule()!=null&&caseinfo.getModule().equals("terminal")){%> selected = "true" <% } %>>终端业务</option>
                          <option align="left" value="product" <%if (caseinfo.getModule()!=null&&caseinfo.getModule().equals("product")){%> selected = "true" <% } %>>产商品中心</option> 
                             </select>            
                       </td>
                       
                       	<td bgcolor="#FFFDF0">
						<div align="center">
							测试人员：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style=" width: 145px"   valid="required"  errmsg="测试人员不能为空!"   name="tester"							
						<%if(caseinfo.getTester()!=null){%>
						 value="<%=caseinfo.getTester()%>"     <%}%>>
						&nbsp;
					</td>
				</tr>
				
				
				
				
				

				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							业务菜单：
						</div>
					</td>
					<td  bgcolor="#FFFFFF">
						<input type="text" style=" width: 145px"  name="menuName" valid="required"  errmsg="业务菜单不能为空!" 							
						<%if(caseinfo.getMenuName()!=null){%> value="<%=caseinfo.getMenuName()%>"     <%}%>>
						
					</td>
	
					<td bgcolor="#FFFDF0">
                        <div align="center">
                                                              菜单编码：
                        </div>
                    </td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id ="199" type="text" style=" width: 145px"  name="menuCode" valid="required"  errmsg="菜单编码不能为空!" 
						<%if(caseinfo.getMenuCode()!=null){%> value="<%=caseinfo.getMenuCode()%>"     <%}%>>
					</td>
                       
					
				</tr>





				
				

				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							用例名称：
						</div>
					</td>
					<td bgcolor="#FFFFFF">
						<input type="text" style=" width: 145px"  name="caseName" valid="required"  errmsg="用例名称不能为空!" 							
						<%if(caseinfo.getCaseName()!=null){%> value="<%=caseinfo.getCaseName()%>"     <%}%>>
						
					</td>
	
					<td bgcolor="#FFFDF0">
                        <div align="center">
                                                              用例编码：
                        </div>
                    </td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style=" width: 145px"  name="caseCode" valid="required"  errmsg="用例编码不能为空!" 
						<%if(caseinfo.getCaseCode()!=null){%> value="<%=caseinfo.getCaseCode()%>"     <%}%>>
					</td>
                       					
				</tr>
				
				
				
				
				
				
				
				
				
	
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							用例参数名：
						</div>
					</td>
					<td bgcolor="#FFFFFF">
						<input type="text" style=" width: 100%"  name="paramName" 						
						<%if(caseinfo.getParamName()!=null){%> value="<%=caseinfo.getParamName()%>"     <%}%>>
						
					</td>
	
                        <td bgcolor="#FFFDF0">
						<div align="center">
							用例级别：
						</div>
					</td>
                    <td bgcolor="#FFFFFF">
                          <select name="caseLevel" style="width: 145px" >
      					<option align="left" value="1" <%if (caseinfo.getCaseLevel()!=null&&caseinfo.getCaseLevel().equals("1")){%> selected = "true" <% } %>>1级</option>
     				 	<option align="left" value="2" <%if (caseinfo.getCaseLevel()!=null&&caseinfo.getCaseLevel().equals("2")){%> selected = "true" <% } %>>2级</option>
     				 	<option align="left" value="3" <%if (caseinfo.getCaseLevel()!=null&&caseinfo.getCaseLevel().equals("3")){%> selected = "true" <% } %>>3级</option>
     				 	<option align="left" value="A" <%if (caseinfo.getCaseLevel()!=null&&caseinfo.getCaseLevel().equals("A")){%> selected = "true" <% } %>>A级</option>
     				 	<option align="left" value="debug" <%if (caseinfo.getCaseLevel()!=null&&caseinfo.getCaseLevel().equals("debug")){%> selected = "true" <% } %>>debug级</option>
   				          </select>            
   				    </td>       
                       					
				</tr>
							
				
				
				
				
				<tr>
                       
                       					<td bgcolor="#FFFDF0">
                        <div align="center">
                                                              用例参数值：
                        </div>
                    </td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style=" width: 42%"  name="paramValue" 
						<%if(caseinfo.getParamValue()!=null){%> value="<%=caseinfo.getParamValue()%>"     <%}%>>
					</td>            
                				
                      
				</tr>

							

								<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							页面定义<br>(同一菜单编码共用,<br>修改请慎重)：
						</div>
					</td>
					<td  bgcolor="#FFFFFF">
						<textarea rows="40" id="123" name="pageElement" 
							style="font-size:15px;height:100%;width: 100%; resize: none;"><%if(caseinfo.getPageElement()==null){%><%}else{%><%=caseinfo.getPageElement()%><%}%></textarea>
					</td>
					


				
				
										<td bgcolor="#FFFDF0">
						<div align="center">
							操作步骤：
							                               <div id="popDiv" >
                                      <table id="content_table" style="font-size:15px;width: 100%">
                                         <tbody id="556" style="color:#FF0000"  >
                                         </tbody>
                                         
                                      </table>
                                </div>
						</div>
					</td>
					<td  bgcolor="#FFFFFF">
 
						<textarea  valid="required"  errmsg="操作步骤不能为空!"  rows="40" id="223" name="opStep" onkeyup="getMoreContens(223,'opStep',556)" onfocus="getMoreContens(223,'opStep',556)" 
							style="font-size:15px;width: 100%; resize: none;"><%if(caseinfo.getOpStep()==null){%><%}else{%><%=caseinfo.getOpStep()%><%}%></textarea>
					</td>
				</tr>	
				
				
					<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							校验步骤：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
																
                                
								<div id="popDiv">
                                      <table id="content_table" style="font-size:15px;width: 1000px">
                                         <tbody id="656" style="color:#FF0000">
                                         </tbody>
                                      </table>
                                </div>
						<textarea rows="8" id="323" name="checkStep" onkeyup="getMoreContens(323,'checkStep',656)" onfocus="getMoreContens(323,'checkStep',656)" 
							style="font-size:20px;width: 100%; resize: none;"><%if(caseinfo.getCheckStep()==null){%><%}else{%><%=caseinfo.getCheckStep()%><%}%></textarea>
	
					</td>
				</tr>				
	
	
	
	
				
				
				
				
				<!--   全量信息 -->
				
				<%
		        ArrayList<String> fileList = 	 (ArrayList)request.getAttribute("fileList");						
				String isexecute = (String)request.getAttribute("execute"); 
				System.out.println(isexecute);
				if(isexecute!=null&&isexecute.equals("yes")){
				
				
				
				%>
				
			<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							执行结果：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style=" width: 145px"   valid=""   errmsg=""  name="result"
							value="<%=caseinfo.getResult()%>">
					</td>

			</tr>
				
	

				
				
		
       				
					<td bgcolor="#FFFDF0">
						<div align="center">
							业务数据:
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<input id="123"   name="inputparam" style="width: 100%; resize: none;" value="<%=caseinfo.getSaveData()%>"></input>


					</td>
				</tr>
	
	
	
				
								<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							执行记录：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
						<textarea rows="50" name="remark"
							style="width: 100%; resize: none;"><%=caseinfo.getRemark()%></textarea>
		
					</td>
				</tr>
				
				
					 <tr>
       	
       	
       					<td bgcolor="#FFFDF0">
						<div align="center">
							截图:
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
                        <!--   <img src="http://127.0.0.1:8080/webcrm/resource\images\test\run\autotest_20181226101815\3030\执行操作步骤异常 .png"/>-->
                         <%for(String fileName:fileList){ %>
                         </br>
                         <img style="width: 100%; resize: none;" src="<%=fileName%>"/> </br>
                         <% }%>
   
					
					
					</td>
				</tr>
				
				
				
				
				
				
				
				<% }%>
				
				
				
				
				
				
				
			</table>
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="36%"></td>
				<% isexecute = (String)request.getAttribute("execute"); 
				if(isexecute==null||!isexecute.equals("yes")){
				
				   if( adminInfo.getUserAdmin().equals(caseinfo.getTester() ) ||adminInfo.getUserAdmin().equals("admin")){
				    				    System.out.println(adminInfo.getUserAdmin());
				      System.out.println(caseinfo.getTester());%>

					<td width="17%"><input type="submit" name="submit"  value="提交"></td> <%}  %>
					
					
					
					<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					<%} %>
					<td width="43%"></td>
				</tr>
			</table>
		</form>
	</body>
	 <script type="text/javascript">
         var xmlHttp;
     //获取用户输入的关联信息的函数
     function getMoreContens(id,name,tid) {
         //获取用户输入
         var content = document.getElementById(id).value;
         var menuCode= document.getElementById('199').value;
        // var paratype = "paramsql";
         
         if (content == "") {
             clearContent();
             return;
         }
         xmlHttp = createXmlHttp();
         //给服务器发送数据
         type="case";
         var url = "<%=basePath%>servlet/CaseAjaxServlet?keywords="+encodeURI(content)+"&paratype="+name+"&menuCode="+menuCode+"&type="+type; 
         xmlHttp.open("GET",url,true);
         
         xmlHttp.onreadystatechange =     //回调函数
             function callback()
         {
             if(xmlHttp.readyState == 4)
                 {
                 if(xmlHttp.status == 200){
                     var result = decodeURIComponent(xmlHttp.responseText);
                     var json = eval("("+result+")");
                     clearContent(tid);
                     intelliSense(json,id,tid);
                 }
                 }
         };
 
         xmlHttp.send(null);
     }
     
     //创建XMLHttpRequest
     function createXmlHttp()
     {
         var xmlHttp;
         if(window.XMLHttpRequest)
             {
             xmlHttp = new XMLHttpRequest();
             //兼容某些Mozilla浏览器的响应头，强制设置为text/xml，具体参看：http://www.cnblogs.com/perseverancevictory/p/3690769.html
                if(xmlHttp.overrideMimeType){  
                    xmlHttp.overrideMimeType("text/xml");
                }
             }
         //兼容IE
         else if(window.ActiveXObject)
             {
             xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
             if(!xmlHttp)
                 {
                 xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                 }
             }
         return xmlHttp;
     }
 
     function clearContent(tid)
     {
         var contentTableBody = document.getElementById(tid);
         var size = contentTableBody.childNodes.length;
         for(var i=size-1;i>0;i--)
             {
             contentTableBody.removeChild(contentTableBody.childNodes[i]);
             }
     }
     function intelliSense(json,id,tid)
     {
         //获取关联数据的长度
         var size = json.length;
         for(var i=0;i<size;i++)
             {
             var nextNode = json[i];
             var tr= document.createElement("tr");
             var td= document.createElement("td");
             tr.setAttribute("border", "0");
             tr.setAttribute("bgcolor", "#EDEDED");
             td.setAttribute("width", "200px");
             td.onmouseover=function()
             {
                 this.className = 'mouseOver';
             }
             td.onmouseout=function()
             {
                 this.className = 'mouseOut';
             }
             td.onclick=function()
             {
                 //var bb=this.innerText;
                // document.getElementById(id).value=document.getElementById(id).value + bb.match(/(.*)>{3}/)[1]+";";
                
                
                
                
                 // document.getElementById(id).value=document.getElementById(id).value+this.innerText;
                 var bb=this.innerText;
                 var origintext=document.getElementById(id).value;
                 alert("提示:"+bb);
                 alert("原文:"+origintext);
                 var temptext="";
                 temptextlist=origintext.match(/([\d\D]*);{1,}/);
                 //匹配删除输入不完整的部分
                 if(temptextlist== undefined || temptextlist.length == 0){
                 	alert("未匹配");  	
                 }else{
                 	alert("有匹配:"+temptextlist); 
                 
                 	temptext=temptextlist[temptextlist.length-1]+";\n";
                 }
               
                alert("截取原文:"+temptext);            
                var newstring=bb.match(/(.*)>{3}/)[1]+";";
                
               alert("匹配提示结果:"+newstring);
                 document.getElementById(id).value=temptext + newstring;
                 
                 
                 
                 

             }
             var text=document.createTextNode(nextNode);
             td.appendChild(text);
             tr.appendChild(td);
             document.getElementById(tid).appendChild(tr);
             }
     }
 </script>
 
</html>
