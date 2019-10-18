<%@page import="test.web.com.bean.WebComponentBean"%>
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

		<title>组件详情</title>

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
		<form action="<%=basePath%>servlet/ComUpdateServlet"
			method="post" name="form2" onsubmit="return validator(this);" >
			<%
				WebComponentBean comInfo = (WebComponentBean) request
						.getAttribute("comInfo");

			%>




			
			
			
			 <table class=editTable cellSpacing=1 cellPadding=0 width="100%"
                align=center border=0>
                <tr class=editHeaderTr>
                    <td class=editHeaderTd colSpan=7>
                                                       组件编辑栏
                        <input type="hidden" name="comId"
                            value="<%=Integer.toString(comInfo.getComId())%>" />
                    </td>
                </tr>
                
  		
  									
					
			
								
					<tr>

					<td bgcolor="#FFFDF0">
						<div align="center">
							内部组件：
						</div>
					</td>
					<td bgcolor="#FFFFFF">
						<input type="text" style=" width: 200px"   valid="required"  errmsg="组件模块不能为空!"   name="moduleCode"							
						<%if(comInfo.getModuleCode()!=null){%> value="<%=comInfo.getModuleCode()%>"<%}%>>
						&nbsp;
					</td>
                       
                     <td bgcolor="#FFFDF0">
						<div align="center">
							组件编码：
						</div>
					</td>
					   <td  bgcolor="#FFFFFF">
						<input type="text" style=" width: 200px"   valid="required"  errmsg="组件编码不能为空!"   name="comCode"							
						<%if(comInfo.getComCode()!=null){%> value="<%=comInfo.getComCode()%>"<%}%>>
						&nbsp;
					     </td>
				</tr>
				
		
									<tr>

					<td bgcolor="#FFFDF0">
						<div align="center">
							组件形参：
						</div>
					</td>
					<td  bgcolor="#FFFFFF">
						<input type="text" style=" width: 500px"     name="paramName"							
						<%if(comInfo.getParamName()!=null){%> value="<%=comInfo.getParamName()%>"<%}%>>
						&nbsp;
					</td>
                       
                     <td bgcolor="#FFFDF0">
						<div align="center">
							组件实参：
						</div>
					</td>
					   <td  bgcolor="#FFFFFF">
						<input type="text" style=" width: 500px"     name="paramValue"							
						<%if(comInfo.getParamValue()!=null){%> value="<%=comInfo.getParamValue()%>"<%}%>>
						&nbsp;
					     </td>
				</tr>
		
	
				
				
				<tr>
                       
                       
                       				    <td bgcolor="#FFFDF0">
						<div align="center">
							组件出参：
						</div>
					</td>
					   <td  bgcolor="#FFFFFF">
						<input type="text" style=" width: 500px"       name="outParam"							
						P
						<%if(comInfo.getOutParam()!=null){%> value="<%=comInfo.getOutParam()%>"<%}%>>
						&nbsp;
					     </td>
					     
					     
                     <td bgcolor="#FFFDF0">
                        <div align="center">
                                                              开发人：
                        </div>
                    </td>
										<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style=" width: 200px"   valid="required"  errmsg="开发人员不能为空!"   name="tester"							
						<%if(comInfo.getTester()!=null){%> value="<%=comInfo.getTester()%>"     <%}else{%> value="<%=adminInfo.getUserAdmin()%>"  <%}%>>
						&nbsp;
					</td>       
                				
                      
				</tr>
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							模块私有：
						</div>
					</td>
                    <td bgcolor="#FFFFFF">
                          <select name="isPrivate" style="width: 145px" >
                          <option align="left" value="是" <%if (comInfo.getIsPrivate()!=null&&comInfo.getIsPrivate().equals("是")){%> selected = "true" <% } %>>是</option>
                          <option align="left" value="否" <%if (comInfo.getIsPrivate()!=null&&comInfo.getIsPrivate().equals("否")){%> selected = "true" <% } %>>否</option>                          
                          
                          </select>            
                       </td>                       
				</tr>
	

					<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							组件功能说明：
						</div>
					</td>
					<td colspan="6" bgcolor="#FFFFFF">
																
                                
								<div id="popDiv">
                                      <table id="content_table" style="font-size:15px;width: 1000px">
                                         <tbody id="656" style="color:#FF0000">
                                         </tbody>
                                      </table>
                                </div>
						<textarea placeholder="组件功能说明"	 valid="required"  errmsg="组件功能说明不能为空!"  					
						rows="8" id="323" name="comName" 
							style="font-size:20px;width: 100%; resize: none;"><%if(comInfo.getComName()==null){%><%}else{%><%=comInfo.getComName()%><%}%></textarea>
	
					</td>
				</tr>
				
				
				
				
				
								<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							页面定义<br>(同一菜单编码共用,<br>修改请慎重)：
						</div>
					</td>
					<td  bgcolor="#FFFFFF">
						<textarea rows="40" id="123" name="pageElement"  placeholder="如 ：choiceType_x=/html/body/div[1]/div[1]/div[2]/ul/li[2]/div[2];  
    
    说明:choiceType-自定义页面元素变量 
          _x - 根据后缀判断查找元素方式，目前支持_x xpath路径; _i 元素id属性值 ; _c 元素属性值  
      	 /html/body/div[1]/div[1]/div[2]/ul/li[2]/div[2] :页面查找路径,通过浏览器工具可查看获取"
							style="font-size:15px;height:100%;width: 100%; resize: none;"><%if(comInfo.getPageElement()==null){%><%}else{%><%=comInfo.getPageElement()%><%}%></textarea>
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
 
						<textarea placeholder="举例:
test=&#34abc&#34&#59  
ab=searchmap(&#34select t.bill_id,t.user_id from so1.ins_user_791 t where bill_id=&#39&#34+test+&#34&#39&#34)&#59
switchframetomenu()&#59
click(choiceType_x)&#59
switchframe(&#34 1 &#34,&#34 0 &#34)&#59"
						
						valid="required"  errmsg="操作步骤不能为空!"  rows="40" id="223" name="opStep" onkeyup="getMoreContens(223,'opStep',556)" onfocus="getMoreContens(223,'opStep',556)" 
							style="font-size:15px;width: 100%; resize: none;"><%if(comInfo.getOpStep()==null){%><%}else{%><%=comInfo.getOpStep()%><%}%></textarea>
					</td>
				</tr>	
				
				
				
	
	
	



             </table>
			
			
			
			
			
			
			
			
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="36%"></td>

					<td width="17%"><input type="submit" name="submit"  value="提交"></td> 					
					<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					
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
         //var menuCode= document.getElementById('199').value;
        // var paratype = "paramsql";
         
         if (content == "") {
             clearContent();
             return;
         }
         xmlHttp = createXmlHttp();
         //给服务器发送数据
         type="com";
         var url = "<%=basePath%>servlet/CaseAjaxServlet?keywords="+encodeURI(content)+"&paratype="+name+"&type="+type; 
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
                 //document.getElementById(id).value=this.innerText;
                 
                 
                 
                 
                 // document.getElementById(id).value=document.getElementById(id).value+this.innerText;
                 var bb=this.innerText;
                 var origintext=document.getElementById(id).value;
               //  alert("提示:"+bb);
               //  alert("原文:"+origintext);
                 var temptext="";
                 temptextlist=origintext.match(/([\d\D]*);{1,}/);
                 //匹配删除输入不完整的部分
                 if(temptextlist== undefined || temptextlist.length == 0){
                 	//alert("未匹配");  	
                 }else{
                 	//alert("有匹配:"+temptextlist); 
                 
                 	temptext=temptextlist[temptextlist.length-1]+";\n";
                 }
               
                //alert("截取原文:"+temptext);            
                var newstring=bb.match(/(.*)>{3}/)[1]+";";
                
               // alert("匹配提示结果:"+newstring);
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
