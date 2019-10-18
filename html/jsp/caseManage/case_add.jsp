<%@page import="test.sec.bean.WebAdminBean"%>
<%@page import="test.web.cases.bean.WebCaseBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>增加CASE</title>
    
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

		
		
		
		
			<script type="text/javascript"
			src="<%=basePath%>resource/js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/My97DatePicker/WdatePicker.js"></script>

<script language="JavaScript" type="text/javascript" src="<%=basePath%>resource/js/FormValid.js"></script>
  </head>
  
  <body>
<form action="<%=basePath%>servlet/CaseAddServlet?op=add"  name="form1" onsubmit="return validator(this);"   method="post">
			<%
				WebCaseBean caseinfo = (WebCaseBean) request
						.getAttribute("caseinfo");
			if(caseinfo==null){
				caseinfo =  new WebCaseBean();
			}
			%>
			
            <table class=editTable cellSpacing=1 cellPadding=0 width="100%"
                align=center border=0>
                <tr class=editHeaderTr>
                    <td class=editHeaderTd colSpan=7>
                        用例编辑栏
                        <input type="hidden" name="caseId"
                            value="" />
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
						<%if(caseinfo.getTester()!=null){%> value="<%=caseinfo.getTester()%>"     <%}else{%> value="<%=adminInfo.getUserAdmin()%>"  <%}%>>
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
						<input type="text" style=" width: 145px"  name="menuName" valid="required"  errmsg="业务菜单不能为空!" placeholder="与菜单名同名"		 					
						<%if(caseinfo.getMenuName()!=null){%> value="<%=caseinfo.getMenuName()%>"     <%}%>>
						
					</td>
	
					<td bgcolor="#FFFDF0">
                        <div align="center">
                                                              菜单编码：
                        </div>
                    </td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id="199" type="text" style=" width: 145px"  name="menuCode" valid="required"  errmsg="菜单编码不能为空!"  placeholder="例：申请停机->Sqtj"	
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
						<input type="text" style=" width: 145px"  name="caseName" valid="required"  errmsg="用例名称不能为空!" 	placeholder="用例描述"							
						<%if(caseinfo.getCaseName()!=null){%> value="<%=caseinfo.getCaseName()%>"     <%}%>>
						
					</td>
	
					<td bgcolor="#FFFDF0">
                        <div align="center">
                                                              用例编码：
                        </div>
                    </td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style=" width: 145px"  name="caseCode" valid="required"  errmsg="用例编码不能为空!"  placeholder="例：测试流程->testFlow"	
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
						<input type="text" style=" width: 100%"  name="paramName" 		placeholder="如 ：billid|value|userid,与参数值对应，且同时必填约束"				
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
   				        <option align="left" selected = "selected" value="debug" <%if (caseinfo.getCaseLevel()!=null&&caseinfo.getCaseLevel().equals("debug")){%> selected = "true" <% } %>>debug级</option>
   				         
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
						<input type="text" style=" width: 42%"  name="paramValue" 	placeholder="如 ：18665717801|1|89109,与参数名对应，且同时必填约束"		
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
						<textarea rows="40" id="123" name="pageElement"  placeholder="如 ：choiceType_x=/html/body/div[1]/div[1]/div[2]/ul/li[2]/div[2];  
    
    说明:choiceType-自定义页面元素变量 
          _x - 根据后缀判断查找元素方式，目前支持_x xpath路径; _i 元素id属性值 ; _c 元素属性值  
      	 /html/body/div[1]/div[1]/div[2]/ul/li[2]/div[2] :页面查找路径,通过浏览器工具可查看获取"
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
 
						<textarea placeholder="举例:
test=&#34abc&#34&#59  
ab=searchmap(&#34select t.bill_id,t.user_id from so1.ins_user_791 t where bill_id=&#39&#34+test+&#34&#39&#34)&#59
switchframetomenu()&#59
click(choiceType_x)&#59
switchframe(&#34 1 &#34,&#34 0 &#34)&#59"
						valid="required"  errmsg="操作步骤不能为空!"  rows="40" id="223" name="opStep" onkeyup="getMoreContens(223,'opStep',556)" onfocus="getMoreContens(223,'opStep',556)"  
							style="font-size:15px;width: 100%; resize: none;"><%if(caseinfo.getOpStep()==null){%><%}else{%><%=caseinfo.getOpStep()%><%}%></textarea>
					</td>
				</tr>	
				<!--valid="required"  errmsg="操作步骤不能为空!"  rows="40" id="223" name="opStep" onkeyup="getMoreContens(223,'opStep',556)" onfocus="getMoreContens(223,'opStep',556)" onblur="clearContent(556)"-->
				
				
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
						<textarea placeholder="
举例:						
orderfinish(&#34 1 &#34,orderId)&#59
sql(&#34 1&#34 ,&#34select * from ins.um_subscirber_region where t.access_num=&#39 18665717801 &#39&#34,&#34校验用户数&#34)&#59
orderfinish(&#34 186657178701 &#34,&#34 54984646 &#34)&#59"						
						rows="8" id="323" name="checkStep" onkeyup="getMoreContens(323,'checkStep',656)" onfocus="getMoreContens(323,'checkStep',656)" "
							style="font-size:20px;width: 100%; resize: none;"><%if(caseinfo.getCheckStep()==null){%><%}else{%><%=caseinfo.getCheckStep()%><%}%></textarea> <!-- onblur="clearContent(656) -->
	
					</td>
				</tr>				
	
	
	



             </table>

				
            </table>

<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
	<tr bgcolor="#ECF3FD">
					<td width="25%"></td>
					<td width="17%"><input type="submit" name="submit"  value="提交"></td>
					<td width="17%"><input type="reset" name="reset"  value="重置"></td>
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
        var menuCode= document.getElementById('199').value;
       

               
               
               
/*          var t = document.getElementById('id');
               t.onclick = function () {
                var v = this.value;
                // 开始到光标位置的内容
                  var cv = '';
                  if ('selectionStart' in t) {
                    cv = v.substr(0, t.selectionStart);
                  } else {
                  var oSel = document.selection.createRange();
                  oSel.moveStart('character', -t.value.length);
                  cv = oSel.text;
                  }
                  // 获取当前是几行
                  var cl = cv.split('\n').length - 1;
                  // 当前行的内容
                  var content = v.split('\n')[cl]; */
         
         
         
        // var paratype = "paramsql";
         
         if (content == "") {
            clearContent();
             return;
         }
         xmlHttp = createXmlHttp();
         //给服务器发送数据
         var type="case";
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

