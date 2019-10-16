<%@page import="org.apache.poi.hssf.record.PageBreakRecord.Break"%>
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

<%
WebAdminBean  adminInfo  =   (WebAdminBean)request.getSession().getAttribute("adminInfo");	
String paraCode = (String)request.getAttribute("paraCode");	
String remark = (String)request.getAttribute("remark");	
String param = (String)request.getAttribute("param");	
String id = (String)request.getAttribute("id");	
String sql = (String)request.getAttribute("sql");	

%>	
function query(){
	
    //var paraType = document.getElementById('1').value;      
    var paraCode =  document.getElementById('2').value;
    var remark =  document.getElementById('3').value;
    var param =  document.getElementById('4').value;


	window.location="<%=basePath%>servlet/AccessQueryServlet?id=<%=id%>&op=yes&param="+param+"&paraCode="+paraCode+"&remark="+remark;

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
    <td >
    <table width="100%" border="0" cellspacing="1" cellpadding="1">
      <tr width="100%" >
        <td width="1%"></td>
        


   			<td class="STYLE4"  width="5%"  align="left">配置编码 ： </td>		
   			<td  ailgn="left" width="10%"  align="left"><input  value="<%=paraCode%>" id="2" type="text" name="paraCode" /></td>
    		<td  class="STYLE4" ailgn="left" width="5%"  align="left">参  数： </td>	
    		<td class="STYLE4" ailgn="left" width="20%"  align="left"> <input value="<%=param%>"  style="width: 300px"  placeholder="多个参数以逗号分隔" value="<%=param%>"  id="4" type="text" name="param" /></td>            
            <td class="STYLE4" ailgn="left" width="7%" align="left"> <input type="button" value="查询" onclick="query()"/></td>


        <td width="7%">
        	 <input   type="hidden"   name="userId"   value="<%=adminInfo.getOperatorId() %>"  />
        </td>
        
      </tr>
      
      
      
        
      <tr height="10"  ></tr>
      
      
      <tr width="100%" >
            <td ></td>
  			<td class="STYLE4"   width="5%"  align="left">配置说明： </td>	         
  			
  			<td ></td>
  			<td class="STYLE4"   width="5%"  align="left">查询SQL： </td>	           
      </tr>
      <tr height="10"  ></tr>
       
      <tr width="100%" >
            <td width="5%"></td>
            <td  width="45%" align="left">
			<textarea  style="width: 400px" rows="8"  id="3" type="text" name="remark"><%=remark%> </textarea>		
			</td>
			
			<td width="5%"></td>
            <td  width="45%" align="left">
			<textarea  style="width: 800px" rows="8"  id="4" type="text" name="remark"><%=sql%> </textarea>		
			</td>

      </tr>
            
    </table></td>
  </tr>
  
  
   
  
    <tr height="30"></tr>
  
  
  
  
    <tr>

    <table id="table1" width="100%" border="0" cellspacing="1" cellpadding="1" >



             </table>
           <table id="123" width="100%" border="0" cellspacing="1" cellpadding="1"  bgcolor="b5d6e6">

           <%ArrayList<WebConfigBean> list = (ArrayList<WebConfigBean>)request.getAttribute("list");
           int size = list.size();
           if(!list.isEmpty()){
        	   
        	   
        	 %>  <tr>  <% 
           for(int j=0;j<60;j++){
           
             if((j%6)==0){ 
             System.out.println(j%6);%>
            	 
            	 </tr>
            <%              }
            
             
             if( (size-1)< j){ break;}%>

             <td height="22" bgcolor="#FFFFFF" width= "20%"><div align="center"><span class="STYLE1"><%=list.get(j) %> </span></div></td>



                       
                       
                 
                 
                 
                 <%}

           
          
           
           
           }%>
          
          
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


</body>
</html>
