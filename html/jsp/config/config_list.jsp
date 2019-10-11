<%@page import="java.util.ArrayList"%>
<%@page import="test.config.bean.WebConfigBean"%>
<%@page import="test.sec.bean.WebAdminBean"%>
<%@ page language="java" pageEncoding="UTF-8"%>
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
	 overflow: hidden; 
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
table
{
 table-layout:fixed;
}

</style>
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
	
	        var paraType = document.getElementById('1').value;      
	        var paraCode =  document.getElementById('2').value;
	        var remark =  document.getElementById('3').value;


			window.location="<%=basePath%>servlet/ConfigQueryServlet?paraType="+paraType+"&paraCode="+paraCode+"&remark="+remark;
	
	}
	
	

	function add(){
	

			window.location="<%=basePath%>servlet/ConfigUpdateServlet?operate=add";
	
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
  
  <body>
   <form action="<%=basePath%>servlet/ConfigQueryServlet" method="get">
  
  <%
						WebAdminBean  adminInfo  =   (WebAdminBean)request.getSession().getAttribute("adminInfo");
						String paraType =(String)request.getAttribute("paraType");
						String paraCode =(String)request.getAttribute("paraCode");
						String remark =(String)request.getAttribute("remark");
						
	%>
	
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" >
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
         <td width="12" height="30"></td>
         <td>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">

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
              <td  width="20%" ailgn="left" class="STYLE4">配置类型：<select id="1" name="paraType">
      					<option  ></option>
      					<option value="runparam" <%if (paraType!=null&&paraType.equals("runparam")){%> selected = "true" <% } %> >运行配置</option>     					    
      					<option value="pageOp" <%if (paraType!=null&&paraType.equals("pageOp")){%> selected = "true" <% } %> >页面操作</option>  
      					<option value="comOp" <%if (paraType!=null&&paraType.equals("comOp")){%> selected = "true" <% } %> >公共操作</option>       					   					       
     				 	<option value="dbOp" <%if (paraType!=null&&paraType.equals("dbOp")){%> selected = "true" <% } %> >数据库操作</option>
      				 	<option value="dataUtil" <%if (paraType!=null&&paraType.equals("dataUtil")){%> selected = "true" <% } %> >数据操作</option>     				 	
      				 	<option value="dbCheck" <%if (paraType!=null&&paraType.equals("dbCheck")){%> selected = "true" <% } %> >数据库校验</option>    				 	
      					<option value="numberkey" <%if (paraType!=null&&paraType.equals("numberkey")){%> selected = "true" <% } %> >号码配置</option>        					

   				 </select>            
   				</td>
   			<td  ailgn="left" width="25%" class="STYLE4" align="left">配置编码 ：<input  id="2" <%if (paraCode!=null){%> value="<%=paraCode%>"  <%}else  {%> value="" <%} %> type="text" name="paraCode" style="width: 150px"/></td>

			
   			<td  ailgn="left" width="25%" class="STYLE4" align="left">配置说明 ：<input  id="3" <%if (remark!=null){%> value="<%=remark%>"  <%}else  {%> value="" <%} %> type="text" name="remark" style="width: 150px"/></td>
            <td width="8%" ailgn="right" class="STYLE4"><input type="button" value="查询" onclick="query()"/></td>
            <td width="15%" ailgn="right" class="STYLE4"><input type="button" value="添加"  onclick="add()" /></td><td>&nbsp;&nbsp;&nbsp;</td>
                       
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

    <table id="table1" width="100%" border="0" cellspacing="1" cellpadding="1">

        <tr>
            <td width="5%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">参数类型</span></div></td>
            <td width="20%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">参数编码</span></div></td>
            <td width="20%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">sql值</span></div></td>
            <td width="45%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">说明</span></div></td>						
			<td width="10%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF" class="STYLE1"><div align="center">基本操作</div></td>
			
        </tr> 

             </table>
           <table id="123" width="100%" border="0" cellspacing="1" cellpadding="1"  bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">

           <%ArrayList<WebConfigBean> list = (ArrayList<WebConfigBean>)request.getAttribute("list");			
		   for(int i=0;i<list.size();i++){ 
		   WebConfigBean configInfo = list.get(i);
			%>
           <tr>
            <td height="22" bgcolor="#FFFFFF" width= "5%"><div align="center"><span class="STYLE1"><%if(configInfo.getParaType().equals("runparam")){%>运行配置<%}else if(configInfo.getParaType().equals("dataUtil")){ %>数据操作<%}else if(configInfo.getParaType().equals("numberkey")){ %>号码配置<%}else if(configInfo.getParaType().equals("pageOp")){ %>页面操作<%}else if(configInfo.getParaType().equals("dbOp")){ %>数据库操作<%}else if(configInfo.getParaType().equals("pageCheck")){ %>页面校验<%}else if(configInfo.getParaType().equals("dbCheck")){ %>数据库校验<%}else if(configInfo.getParaType().equals("comOp")){ %>公共操作<%}%></span></div></td>
            <td height="22" bgcolor="#FFFFFF" width= "20%"><div align="center"><span class="STYLE1"><%=configInfo.getParaCode() %> </span></div></td>
            <td height="22" bgcolor="#FFFFFF" width= "20%"><div align="center"><span class="STYLE1"><%=configInfo.getPara1()%> </span></div></td>
            <td class="STYLE4" height="22"   bgcolor="#FFFFFF" width= "45%"><div align="left"><span class="STYLE4"><%=configInfo.getRemark()%></span></div></td>
            <td height="22" bgcolor="#FFFFFF" width= "10%"><div align="center"><span class="STYLE4">
            <img src="<%=basePath%>resource/images/edt.gif"  />
            <a href="<%=basePath%>servlet/ConfigUpdateServlet?operate=update&id=<%=Integer.toString(configInfo.getId())%>">详情</a>&nbsp; 
            
            <%if(adminInfo.getUserAdmin().equals("admin")){%>
                <img src="<%=basePath%>resource/images/del.gif"/>
                <a href="<%=basePath%>servlet/ConfigUpdateServlet?operate=del&id=<%=Integer.toString(configInfo.getId())%>">删除</a></span></div></td>
              
            <%}%>

          
          
          
          
          </tr><%}%>
             </table>    
      </tr>



  
  
  
  
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
