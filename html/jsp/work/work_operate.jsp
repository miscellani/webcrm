
<%@page import="java.util.ArrayList"%>
<%@page import="test.web.cases.bean.WebCaseBean"%>
<%@page import="test.sec.bean.WebAdminBean"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>CASE信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">

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
	font-size: 15px;
}

	a{
	text-decoration: none;
	color: #033d61;
	font-size: 12px;
}

A:hover {
	COLOR: #f60; TEXT-DECORATION: underline
}

td {
    white-space: nowrap; /* 自适应宽度*/
    word-break:  keep-all; /* 避免长单词截断，保持全部 */
}
</style>

    <script type="text/javascript">
	var errori ='<%=request.getAttribute("opType")%>';
	if(errori=="stop"){
		alert("执行已中止，请稍后刷新操作！！");
	}
	if(errori=="init"){
		alert("执行初始化完成！！");
	}
    </script>



  <%
						WebAdminBean  adminInfo  =   (WebAdminBean)request.getSession().getAttribute("adminInfo");
                        String admin= adminInfo.getUserAdmin();
						System.out.println(admin);
						String module =(String)request.getAttribute("module");
						String caseName =(String)request.getAttribute("caseName");
						
						
						String tester =(String)request.getAttribute("tester");
                        if(tester==null){
                        	tester =admin;
                        }

						String caseLevel =(String)request.getAttribute("caseLevel");
						String result =(String)request.getAttribute("result");
						String running =(String)request.getAttribute("running");
						String menuName =(String)request.getAttribute("menuName");
						String ip =(String)adminInfo.getIp();
						System.out.println(ip);
	%>


<script>
var  highlightcolor='#c1ebff';
var  clickcolor='#51b2f6';




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
<script type="text/javascript">

		
	function query(){
	
	        var module = document.getElementById('1').value;      
	        var caseName =  document.getElementById('2').value;
	        var tester =  document.getElementById('3').value;
	        var caseLevel =  document.getElementById('4').value;
	        var result =  document.getElementById('5').value;
	        var menuName =  document.getElementById('6').value;

			window.location="<%=basePath%>servlet/CaseQueryServlet?running=<%=running%>&execute=yes&module="+module+"&caseName="+caseName+"&tester="+tester+"&caseLevel="+caseLevel+"&result="+result+"&menuName="+menuName;
	
	}
	
	
	function continueExecute(){
	
			    var continueType = document.getElementById('13').value;      
	
	window.location="<%=basePath%>servlet/WorkExecuteServlet?filterType=continueX&continueType="+continueType;
	}


	function filterExecute(){
	
		    var module = document.getElementById('1').value;      
	        var caseName =  document.getElementById('2').value;
	        var tester =  document.getElementById('3').value;
	        var caseLevel =  document.getElementById('4').value;
	        var result =  document.getElementById('5').value;
	        var menuName =  document.getElementById('6').value;
	        
	window.location="<%=basePath%>servlet/WorkExecuteServlet?filterType=filter&module="+module+"&caseName="+caseName+"&tester="+tester+"&caseLevel="+caseLevel+"&result="+result+"&menuName="+menuName;
	}


    function init(){
    
    	window.location="<%=basePath%>servlet/WorkExecuteServlet?init=init";
    
    }



function running(){

		   document.getElementById('11').setAttribute("disabled", true);  
		   		   document.getElementById('12').setAttribute("disabled", true);      
		   
		   		   document.getElementById('13').setAttribute("disabled", true);      
		       return true;
}




    function jump(){
        var rows=document.getElementById("table1").rows;
        if(rows.length>0){
            for(var i=1;i<rows.length;i++){
              (function(i){
                var temp=rows[i].cells[0].childNodes[1].value;
                var obj=rows[i];
                obj.ondblclick=function(){alert(temp);};
                })(i)
            }
        }
    }
    
    window.onload=function(){jump();}


</script>
 </head>
  
  <body >
   <form action="<%=basePath%>servlet/WorkExecuteServlet?filterType=all" method="post">
  

	
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" >
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
         <td width="12" height="30"></td>
         <td>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td  ailgn="left"  width="5%" class="STYLE1" align="left">&nbsp;&nbsp;&nbsp;&nbsp;业务模块：<input class="STYLE1" id="1" <%if (module!=null){%> value="<%=module%>"  <%}else  {%> value="" <%} %> type="text" name="module" style="width: 70px" /></td><td>&nbsp;&nbsp;&nbsp;</td>
            <td  ailgn="left" width="5%" class="STYLE1" align="left">用例描述：<input class="STYLE1" id="2"  <%if (caseName!=null){%> value="<%=caseName%>"  <%}else  {%> value="" <%} %> type="text" name="caseName"  style="width: 100px"/></td><td>&nbsp;&nbsp;&nbsp;</td>
            <td  ailgn="left" width="15%" class="STYLE1" align="left">测试人员 ：<input class="STYLE1" id="3" <%if (tester!=null){%> value="<%=tester%>"  <%}else  {%> value="" <%} %> type="text" name="tester" style="width: 70px"/></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td width="15%" ailgn="right" class="STYLE1"><input class="STYLE1" type="submit" id="11"  <%if(running.equals("yes")||!admin.equals("admin")){%> disabled=true <%}%> value="全量执行"  /></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 
             <td width="15%" ailgn="right" class="STYLE1"><input class="STYLE1" type="button" id="14" onclick="init()" <%if(running.equals("yes")||!admin.equals("admin")){%> disabled=true <%}%> value="初始化" /></td><td>&nbsp;&nbsp;&nbsp;</td>            
            <td  width="15%" lass="STYLE1">&nbsp;&nbsp;</td>        
            <td  width="15%" lass="STYLE1">&nbsp;&nbsp;</td>                     
                         
          </tr>
          <tr>
          
          </tr>
        </table></td>
        <td width="16">
        	 <input   type="hidden"   name="userId"   value="<%=adminInfo.getOperatorId() %>"  />
        </td>
      </tr>
    </table></td>
  </tr>



  <tr>
    <td height="30" >
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"></td>
        <td>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
              <td  width="5%" ailgn="left" class="STYLE1">菜单名称 : <input class="STYLE1" id="6"  <%if (menuName!=null){%> value="<%=menuName%>"  <%}else  {%> value="" <%} %> type="text" name="menuName"  style="width: 70px"/>           
   				</td><td>&nbsp;&nbsp;&nbsp;</td>
            <td  width="5%" ailgn="left" class="STYLE1">用例级别 :&nbsp;&nbsp;<select id="4" name="caseLevel">
      					<option class="STYLE1" ></option>   
      					<option value="A" <%if (caseLevel!=null&&caseLevel.equals("A")){%> selected = "true" <% } %> >A级</option>         
      					<option value="1" <%if (caseLevel!=null&&caseLevel.equals("1")){%> selected = "true" <% } %> >1级</option>
     				 	<option value="2" <%if (caseLevel!=null&&caseLevel.equals("2")){%> selected = "true" <% } %> >2级</option>
     				 	<option value="3" <%if (caseLevel!=null&&caseLevel.equals("3")){%> selected = "true" <% } %> >3级</option>

   				 </select>            
   				</td><td>&nbsp;&nbsp;&nbsp;</td>
            <td width="5%" ailgn="center" class="STYLE1">执行结果：<select id="5" name="result"  >
                  		<option class="STYLE1" ></option>                        
      					<option value="未执行" <%if (result!=null&&result.equals("未执行")){%> selected = "true" <% } %> >未执行</option>
     				 	<option value="成功" <%if (result!=null&&result.equals("成功")){%> selected = "true" <% } %> >成功</option>
     				 	<option value="失败" <%if (result!=null&&result.equals("失败")){%> selected = "true" <% } %> >失败</option>
     				 	<option value="正确" <%if (result!=null&&result.equals("正确")){%> selected = "true" <% } %> >正确</option>
     				 	<option value="错误" <%if (result!=null&&result.equals("错误")){%> selected = "true" <% } %> >错误</option>     				 	     				 	
   				 </select>            
   				</td><td>&nbsp;&nbsp;&nbsp;</td>   				
   				
   				
            <td width="15%" ailgn="right" class="STYLE1">&nbsp;&nbsp;<input class="STYLE1" type="button" value="查询"  onclick="query()" /></td><td>&nbsp;&nbsp;&nbsp;</td>
            <td width="15%" ailgn="right" class="STYLE1">&nbsp;&nbsp;<input class="STYLE1" type="button" id="12" <%if(running.equals("yes")||!admin.equals("admin")){%> disabled=true <%}%> value="过滤执行"  onclick="filterExecute()" /></td><td>&nbsp;&nbsp;&nbsp;</td>
            <td width="15%" ailgn="right" class="STYLE1">&nbsp;&nbsp;&nbsp;<input class="STYLE1" type="button" id="13" <%if(!admin.equals("admin")){%> disabled=true <%}%> <%if(running.equals("yes")){%> name="停止执行" value="停止执行" <%}else{%>  name="继续执行" value="继续执行" <%}%>   onclick="continueExecute()" /></td><td>&nbsp;&nbsp;&nbsp;</td>             
                       
          </tr>
          <tr>
          
          </tr>
        </table></td>
        <td width="16">
        	 <input   type="hidden"   name="userId"   value="<%=adminInfo.getOperatorId() %>"  />
        </td>
      </tr>
    </table></td>
  </tr>
  
  
  
  
  
  
  
    <tr>
    <td>
    <table id="table1" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8">&nbsp;</td>
        <td>
            <tr>
            <td width="5%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">编号</span></div></td>
            <td width="5%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">模块</span></div></td>
            <td width="10%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">用例编码</span></div></td>
           
           
            <td width="10%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">菜单名称</span></div></td>
            <td width="30%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">用例描述</span></div></td>
            <td width="5%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">级别</span></div></td>		
			<td width="5%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">测试人员</span></div></td>	
			<td width="5%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">执行结果</span></div></td>							
			<td width="10%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF" class="STYLE1"><div align="center">基本操作</div></td>
			
         </tr>      
        <div >
        <table id="123" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">



		<% ArrayList<WebCaseBean> list = (ArrayList<WebCaseBean>)request.getAttribute("list");
					if(list!=null && list.size()>0){
							for(int i=0;i<list.size();i++){ 
							WebCaseBean caseinfo = list.get(i);
			%>
 <tr>
            <td height="22" bgcolor="#FFFFFF"style="width: 5%"><div align="center"><span class="STYLE1"><%=caseinfo.getCaseId() %> </span></div></td>
            <td height="22" bgcolor="#FFFFFF"style="width: 5%"><div align="center"><span class="STYLE1"><%=caseinfo.getModule() %> </span></div></td>
 
            <td height="22" bgcolor="#FFFFFF"style="width: 10%"><div align="center"><span class="STYLE1"><%=caseinfo.getCaseCode() %> </span></div></td>           
            
            <td height="22" bgcolor="#FFFFFF"style="width: 10%"><div align="center"><span class="STYLE1"><%=caseinfo.getMenuName() %> </span></div></td>
            <td height="22" bgcolor="#FFFFFF"style="width: 30%"><div align="center"><span class="STYLE1"><%=caseinfo.getCaseName()%></span></div></td>            
            <td height="22" bgcolor="#FFFFFF"style="width: 5%"><div align="center"><span class="STYLE1"><%=caseinfo.getCaseLevel()%></span></div></td>
			<td height="22" bgcolor="#FFFFFF"style="width: 5%"><div align="center"><span class="STYLE1"><%=caseinfo.getTester() %></span></div></td>	
			<td height="22" bgcolor="#FFFFFF"style="width: 5%"><div align="center"><span class="STYLE1"><%if(caseinfo.getResult()==null){%>未执行<%}else{%><%=caseinfo.getResult()%><%}%> </span></div></td>			
            <td height="22" bgcolor="#FFFFFF"style="width: 10%"><div align="center"><span class="STYLE4"><img src="<%=basePath%>resource/images/exe.gif"  />            
            <a href="<%=basePath%>servlet/WorkExecuteServlet?admin=<%=admin%>&filterType=choice&ip=<%=ip%>&caseId=<%=Integer.toString(caseinfo.getCaseId())%>">执行</a>&nbsp; <img src="<%=basePath%>resource/images/edt.gif"/>            
            <a target="_blank" href="<%=basePath%>servlet/CaseQueryDetailServlet?execute=yes&result=<%= caseinfo.getResult()%>&caseid=<%=Integer.toString(caseinfo.getCaseId())%>">详情</a>&nbsp; 
          </tr>
                                                   <%}
                       }else{ %>
          						<tr>
										<td height="20" bgcolor="#FFFFFF" colspan="22"  align="center">
											<div align="center">
												<span class="STYLE1">没有客户相关信息</span>
											</div>
										</td>
									</tr>
                          <%} %>
         
         

     
     </table>
         </td>
        <td width="8" >&nbsp;</td>
      </tr>
    </table>
    </td>
  </table>
  
  <table>

         <div style="height:30px" align="right" >
         
         <span id="spanFirst">第一页</span>
         <span id="spanPre">上一页</span>
        <span id="spanNext">下一页</span>
        <span id="spanLast">最后一页</span>
              第<span id="spanPageNum"></span>页/共<span id="spanTotalPage"></span>页
              <span width="8" >&nbsp;</span>
         </div>        
         
 </table>

</form>


<script type="text/javascript">
var theTable = document.getElementById("123");
var totalPage = document.getElementById("spanTotalPage");
var pageNum = document.getElementById("spanPageNum");
var spanPre = document.getElementById("spanPre");
var spanNext = document.getElementById("spanNext");
var spanFirst = document.getElementById("spanFirst");
var spanLast = document.getElementById("spanLast");
var numberRowsInTable = theTable.rows.length;
var pageSize = 20;
var page = 1;
//下一页
function next() {
    hideTable();
    currentRow = pageSize * page;
    maxRow = currentRow + pageSize;
    if ( maxRow > numberRowsInTable )
    maxRow = numberRowsInTable;
    for ( var i = currentRow; i< maxRow; i++ ) {
        theTable.rows[i].style.display = '';
    }
        page++;
    if ( maxRow == numberRowsInTable ){
        nextText();
        lastText();
    }
    showPage();
    preLink();
    firstLink();
}

//上一页
function pre() {
    hideTable();
    page--;
    currentRow = pageSize * page;
    maxRow = currentRow - pageSize;
    if ( currentRow > numberRowsInTable )
    currentRow = numberRowsInTable;
    for ( var i = maxRow; i< currentRow; i++ ) {
        theTable.rows[i].style.display = '';
    }
    if ( maxRow == 0 ) {
        preText();
        firstText();
    }
    showPage();
    nextLink();
    lastLink();
}
//第一页
function first() {
    hideTable();
    page = 1;
    for ( var i = 0; i<pageSize; i++ ) {
        theTable.rows[i].style.display = '';
    }
    showPage();

    preText();
    nextLink();
    lastLink();
}

//最后一页
function last() {
    hideTable();
    page = pageCount();
    currentRow = pageSize * (page - 1);
    for ( var i = currentRow; i<numberRowsInTable; i++ ) {
        theTable.rows[i].style.display = '';
    }
    showPage();
    
    preLink();
    nextText();
    firstLink();
}

function hideTable() {
    for ( var i = 0; i<numberRowsInTable; i++ ) {
    theTable.rows[i].style.display = 'none';
    }
}

function showPage() {
    pageNum.innerHTML = page;
}

//总共页数
function pageCount() {
    var count = 0;
    if ( numberRowsInTable%pageSize != 0 ) count = 1; 
    return parseInt(numberRowsInTable/pageSize) + count;
}

//显示链接
function preLink() { spanPre.innerHTML = "<a href='javascript:pre();'>上一页</a>"; }
function preText() { spanPre.innerHTML = "上一页"; }

function nextLink() { spanNext.innerHTML = "<a href='javascript:next();'>下一页</a>"; }
function nextText() { spanNext.innerHTML = "下一页"; }

function firstLink() { spanFirst.innerHTML = "<a href='javascript:first();'>第一页</a>"; }
function firstText() { spanFirst.innerHTML = "第一页"; }

function lastLink() { spanLast.innerHTML = "<a href='javascript:last();'>最后一页</a>"; }
function lastText() { spanLast.innerHTML = "最后一页"; }

//隐藏表格
function hide() {
    for ( var i = pageSize; i<numberRowsInTable; i++ ) {
        theTable.rows[i].style.display = 'none';
    }

    totalPage.innerHTML = pageCount();
    pageNum.innerHTML = '1';
    
    nextLink();
    lastLink();
}

hide();
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





</body>
</html>
