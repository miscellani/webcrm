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
<script src="<%=basePath%>resource/js/jqplot/excanvas.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.pieRenderer.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.donutRenderer.min.js"></script>




<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.canvasTextRenderer.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.canvasAxisLabelRenderer.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.barRenderer.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.pointLabels.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.highlighter.js"></script>

</head>
<body bgcolor="FFFFE0">
<% 

      String executeResult = (String)request.getAttribute("executeResult");
      String errDivides = (String)request.getAttribute("errDivide");
      String errNum = (String)request.getAttribute("errNum");
      String allNum = (String)request.getAttribute("allNum");


%>

<table width="100%">
<tr style="height:40px; background-color:none;"><tr>
<tr>

<td width="30%">
<div id="chart"  >
<script>
        var data = <%=executeResult%>; 


    $(function () {



      $.jqplot('chart', [data], {
             seriesColors: ["#FFFFFF","#FFD700","#808080","#32CD32","#FF6347"],
             title: '执行结果分布(总数'+<%=allNum%>+'个)',
             seriesDefaults: {
              renderer: $.jqplot.PieRenderer,
              rendererOptions: {
                  showDataLabels: true
              }
          },
          legend: {
              show: true,
              location: "e"
          },
          grid: {
              gridLineColor: '#FFFFE0', //设置整个图标区域网格背景线的颜色
              background: '#FFFFE0', //设置整个图标区域的背景色  
              borderColor: '#FFFFE0',//设置图表的(最外侧)边框的颜色
              shadowWidth: 0 ,//设置阴影区域的宽度
              borderWidth: 0, //设置图表的(最外侧)边框宽度
              shadow: false //为整个图标（最外侧）边框设置阴影，以突出其立体效果             
          }
      });
  });

	</script>
</div>	
</td>

<td width="10%"></td>

<td  width="50%" >
<div id="chart1" >
	<script>

     
           var line1 = <%=errDivides%>;
           // var line1 = [['彩云',1],['飞信',1],['平台名称',1],['移动梦网流程',8]];
  
          $.jqplot('chart1', [line1], {
          
          markerOptions: {lineWidth: 2},
          
                seriesDefaults:{
                lineWidth: 1,        
                renderer:$.jqplot.BarRenderer, 
                pointLabels: { show: true } 
            },
               grid:{  
                backgroundColor: "transparent"  
                } ,
              title: '失败CASE分布(总数'+<%=errNum%>+'个)',
              seriesColors: ["#808080"],
              series: [{ 
              renderer: $.jqplot.BarRenderer
              }],
              axesDefaults: {
                  tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                 tickOptions: {
                     fontFamily: 'Georgia',
                     angle: -30,
                     fontSize: '10pt',
                     formatString: "%1.0f",
                     show:true,
                     showGridline:false//是否在图表区域显示刻度值方向的网格
                 }
             },
             axes: {
                 xaxis: {
                     renderer: $.jqplot.CategoryAxisRenderer
                 }
             },
              grid: {
              gridLineColor: '#FFFFE0', //设置整个图标区域网格背景线的颜色
              background: '#FFFFE0', //设置整个图标区域的背景色  
              borderColor: '#FFFFE0',//设置图表的(最外侧)边框的颜色
              shadowWidth: 0 ,//设置阴影区域的宽度
              borderWidth: 0, //设置图表的(最外侧)边框宽度
              shadow: false //为整个图标（最外侧）边框设置阴影，以突出其立体效果             
             }
         });
</script>
</div>

</td>
</tr>
</table>
</body>
</html>
