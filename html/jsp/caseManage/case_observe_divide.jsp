<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<title>Menu4</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/js/jquery-easyui-1.4.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/js/jquery-easyui-1.4.2/themes/icon.css">

<link href="<%=basePath%>resource/js/jqplot/jquery.jqplot.min.css" rel="stylesheet" />
<script src="<%=basePath%>resource/js/jqplot/jquery.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/jquery.jqplot.min.js"></script>


<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.canvasTextRenderer.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.canvasAxisLabelRenderer.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.barRenderer.min.js"></script>



<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.pointLabels.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.highlighter.js"></script>

<% 
String[] divide = (String[])request.getAttribute("divide");
String divide1 =divide[0];

String divide2 =divide[1];
String divide3 =divide[2];
%>

</head>
<body>

<div style="height:100px"></div>

<table>
<tr>

<div id="chart1" width="100%" height="100%">
<script>

           var line1 = <%=divide1%>;


          $.jqplot('chart1', [line1], {
          
               seriesDefaults:{
                renderer:$.jqplot.BarRenderer, 
                pointLabels: { 
                	 show: true ,
                     formatString: "%1.0f",
                     } 
               },

               
               grid:{  
                backgroundColor: "transparent"  ,
                } ,
              title: '菜单CASE分布',
              seriesColors: ["#FFA500"],
              series: [{ renderer: $.jqplot.BarRenderer }],
              axesDefaults: {
                  tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                 tickOptions: {
                     fontFamily: 'Georgia',
                     angle: -30,
                     fontSize: '10pt',
                     formatString: "%1.0f"

                 }
             },
             axes: {
                 xaxis: {
                     renderer: $.jqplot.CategoryAxisRenderer
                 }
             }
         });
</script>
</div>



<td  ailgn ="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</td>
<br><br><br><br>
<td  valign="top" ailgn="center"> 总共CASE数<%=divide3%>个，分布于<%=divide2%>个模块</td>
</td>
</tr>
</table>
</body>
</html>
