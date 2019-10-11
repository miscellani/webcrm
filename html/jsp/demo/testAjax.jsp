<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
       <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>smart search</title>
 <style type="text/css">
 .mouseOver {
     background: #00B2EE;
     color: white;
     width: 200px;
 }
 .mouseOut {
     background: #EDEDED;
     width: 200px;
 }
 </style>
 
 </head>
 <body>
     <div>
         <input type="text"  name="keyWords"  id="123"  style="width:200px; height:20px;" onkeyup="getMoreContens(123,'paramsql',456)" onfocus="getMoreContens(123,'paramsql',456)" onblur="clearContent(456)" /> <input
             type="button" id="button" value="百度一下">
             <div id="popDiv">
             <table id="content_table">
                 <tbody id="456">
                 </tbody>
             </table>
         </div>
     </div>
 </body>
 <!--  OK-->
 <script type="text/javascript">
         var xmlHttp;
     //获取用户输入的关联信息的函数
     function getMoreContens(id,name,tid) {
         //获取用户输入
         var content = document.getElementById(id).value;
        // var paratype = "paramsql";
         
         if (content == "") {
             clearContent();
             return;
         }
         xmlHttp = createXmlHttp();
         //给服务器发送数据
         var url = "<%=basePath%>servlet/TestAjaxServlet?keywords="+encodeURI(content)+"&paratype="+name; 
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
                 document.getElementById(id).value=this.innerText;
             }
             var text=document.createTextNode(nextNode);
             td.appendChild(text);
             tr.appendChild(td);
             document.getElementById(tid).appendChild(tr);
             }
     }
 </script>
 </html>