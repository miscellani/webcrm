<%@page import="test.web.com.bean.WebComponentBean"%>
<%@page import="test.web.cases.bean.WebCaseBean"%>
<%@page import="test.sec.bean.WebAdminBean"%>
<%@ page language="java" 
import="java.util.*" pageEncoding="UTF-8"%>
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

td {
    white-space: nowrap; /* 自适应宽度*/
    word-break:  keep-all; /* 避免长单词截断，保持全部 */
}
</style>
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
<script type="text/javascript">
	function add(){  
		//window.location="<%=basePath%>servlet/ComAddServlet";
			
			 window.open("<%=basePath%>servlet/ComAddServlet","_blank");           
		}





</script>
 </head>
  
  <body>
   <form action="<%=basePath%>servlet/ComQueryServlet" method="post">
  
  <%
						WebAdminBean  adminInfo  =   (WebAdminBean)request.getSession().getAttribute("adminInfo");
						String os =(String)request.getAttribute("optionString");
						System.out.println((String)request.getAttribute("searchValue"));
	%>
	
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="<%=basePath%>resource/images/tab_05.gif">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="<%=basePath%>resource/images/tab_03.gif" width="12" height="30" /></td>
                <td>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td  class="STYLE4" align="center">&nbsp;&nbsp;查询内容：<input <%if ((String)request.getAttribute("searchValue")!=null){%> value="<%=(String)request.getAttribute("searchValue")%>"  <%}%> type="text" name="searchValue" style="width: 150px"/></td>
            <td class="STYLE4">&nbsp;&nbsp;查询方式：<select name="optionString" style="width: 100px">
      					<option value="1" <%if (os!=null&&os.equals("1")){%> selected = "true" <% } %> >模块</option>
     				 	<option value="2" <%if (os!=null&&os.equals("2")){%> selected = "true" <% } %> >组件编码</option>
     				 	<option value="3" <%if (os!=null&&os.equals("3")){%> selected = "true" <% } %> >组件名称</option>
     				 	<option value="4" <%if (os!=null&&os.equals("4")){%> selected = "true" <% } %> >开发人员</option>
     				 	<option value="5" <%if (os!=null&&os.equals("5")){%> selected = "true" <% } %> >发布状态</option>


   				 </select>            
   				</td>
            <td class="STYLE4">&nbsp;&nbsp;<input  type="submit" value="查询" style="width:50px"/></td>
            <td class="STYLE4">&nbsp;&nbsp;<input type="button" value="添加"  onclick="add()"  style="width:50px"/>
            
            
            </td>    
                    
          </tr>
        </table></td>
        <td width="16"><img src="<%=basePath%>resource/images/tab_07.gif" width="16" height="30" />
        	 <input   type="hidden"   name="userId"   value="<%=adminInfo.getOperatorId() %>"  />
        </td>
      </tr>
    </table></td>
  </tr>


    <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="1">
      <tr>
        

        
        <div><table id="123"  border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">

            <tr>
            <td width="5%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">编号</span></div></td>
            <td width="10%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">组件模块</span></div></td>            
            <td width="15%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">组件编码</span></div></td>
            <td width="25%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">组件说明</span></div></td>     
            <td width="5%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">私有</span></div></td>          
            <td width="10%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">开发人</span></div></td>         
            <td width="5%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">状态</span></div></td>			
			<td width="15%" height="22" background="<%=basePath%>resource/images/bg2.gif" bgcolor="#FFFFFF" class="STYLE1"><div align="center">基本操作</div></td>
			
            </tr>
		<% List<WebComponentBean> list = (List<WebComponentBean>)request.getAttribute("list");
					if(list!=null && list.size()>0){
							for(int i=0;i<list.size();i++){ 
								WebComponentBean webComponentBean = list.get(i);
			%>
 <tr>
            <td height="22" bgcolor="#FFFFFF"style="width: 5%"><div align="center"><span class="STYLE1"><%=webComponentBean.getComId()%> </span></div></td>
            <td height="22" bgcolor="#FFFFFF"style="width: 10%"><div align="center"><span class="STYLE1"><%=webComponentBean.getModuleCode()%> </span></div></td>
            <td height="22" bgcolor="#FFFFFF"style="width: 15%"><div align="center"><span class="STYLE1"><%=webComponentBean.getComCode()%> </span></div></td>
            <td height="22" bgcolor="#FFFFFF"style="width: 25%"><div align="center"><span class="STYLE1"><%=webComponentBean.getComName()%> </span></div></td>
            <td height="22" bgcolor="#FFFFFF"style="width: 5%"><div align="center"><span class="STYLE1"><%=webComponentBean.getIsPrivate()%> </span></div></td>   
            <td height="22" bgcolor="#FFFFFF"style="width: 10%"><div align="center"><span class="STYLE1"><%=webComponentBean.getTester()%> </span></div></td>                       
			<td height="22" bgcolor="#FFFFFF"style="width: 5%"><div align="center"><span class="STYLE1"><%=webComponentBean.getResult()%></span></div></td>				
            <td height="22" bgcolor="#FFFFFF"style="width: 15%"><div align="center"><span class="STYLE4">
            <img src="<%=basePath%>resource/images/release.png"  />            
            <a   target="_blank" href="<%=basePath%>servlet/ComReleaseServlet?execute=no&comId=<%=Integer.toString(webComponentBean.getComId())%>">发布</a>&nbsp; 
            <img src="<%=basePath%>resource/images/copy.gif"  />            


          
            <a target="_blank" href="<%=basePath%>servlet/ComQueryDetailServlet?execute=no&comId=<%=Integer.toString(webComponentBean.getComId())%>">详情</a>&nbsp; 
            
            <%if(adminInfo.getUserAdmin().equals("admin") ||(adminInfo.getUserAdmin().equals(webComponentBean.getTester()))){%>
            <img src="<%=basePath%>resource/images/del.gif"/>
            <a href="<%=basePath%>servlet/ComDeleteServlet?comId=<%=Integer.toString(webComponentBean.getComId())%>">删除</a></span></div></td>
            <%}%>
         
         
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
         </div>
         
        <td width="8" >&nbsp;</td>
      </tr>
    </table>
    </td>
  </table>
  <table>
      <tr>
         <div style="height:30px" align="right" ;>
         <span id="spanFirst">第一页</span>
         <span id="spanPre">上一页</span>
        <span id="spanNext">下一页</span>
        <span id="spanLast">最后一页</span>
              第<span id="spanPageNum"></span>页/共<span id="spanTotalPage"></span>页
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
var pageSize = 30;
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


</body>
</html>
