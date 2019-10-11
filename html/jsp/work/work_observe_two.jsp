<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
<link href="<%=basePath%>resource/js/jqplot/jquery.jqplot.min.css" rel="stylesheet" />
<script src="<%=basePath%>resource/js/jqplot/jquery.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/jquery.jqplot.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/excanvas.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.barRenderer.min.js"></script>   
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.pointLabels.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.canvasTextRenderer.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.canvasAxisLabelRenderer.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.cursor.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.highlighter.min.js"></script>
<script src="<%=basePath%>resource/js/jqplot/plugins/jqplot.dateAxisRenderer.min.js"></script>    
    
    
    
    <% 
    String[] feeString = new String[2];
    String caseId = (String)request.getAttribute("caseId");
    String overFee =  (String)request.getAttribute("overFee");
       String outTime =  (String)request.getAttribute("outTime");   
    %>
    
    <script>
        $(function () {

            var tick2 = <%=caseId%>;
            var b2 = <%=overFee%>;;


            var plot2 = $.jqplot('chart2', [b2], {
                title: '用例执行超时间(<%if(outTime.equals("0")){%>TOP)<%}else{%>大于'+<%=outTime%>+'秒)<%}%>',
                legend: { show: true, location: 'ne' },  
                axesDefaults: { 
                    tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                    // labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                    tickOptions: {
                        fontSize: '10pt',
                        angle: 45
                    }
                },
                series: [
                    {
                        label: '执行时间',
                        // lineWidth: 8, //线条粗细 
                        markerOptions: { size: 10, style: "filledCircle" }, // 节点配置
                        color: '#FF0000'    // 数据点的颜色  
                    }
                ], //提示工具栏  
                //captureRightClick: true,//禁用右键
                seriesDefaults: {
                    pointLabels: { show: true, ypadding: -1 } //数据点标签
                    //renderer: $.jqplot.BarRenderer, //使用柱状图表示  
                    //柱状体组之间间隔  
                    //rendererOptions: {barMargin: 25}
                },
               grid: {
                 gridLineColor: '#FFFFE0', //设置整个图标区域网格背景线的颜色
                 background: '#FFFFE0', //设置整个图标区域的背景色  
                 borderColor: '#FFFFE0',//设置图表的(最外侧)边框的颜色
                 shadowWidth: 0 ,//设置阴影区域的宽度
                 borderWidth: 0, //设置图表的(最外侧)边框宽度
                 shadow: true //为整个图标（最外侧）边框设置阴影，以突出其立体效果          
                },
                axes: {
                    xaxis: {
                        label: "编号+描述",  //x轴显示标题
                        pad: 5,
                        renderer: $.jqplot.CategoryAxisRenderer, //x轴绘制方式
                        tickInterval: '1day',
                        ticks: tick2,
                        tickOptions: {

                            fontSize: '10pt'
                        },
                        mark: 'cross'
                    },
                    yaxis: {
                        label: "秒", // y轴显示标题
                        min: 0,
                        //tickInterval: 10,     //网格线间隔大小 
                        //小数点位
                        tickOptions: { formatString: '%.0f', fontSize: '10pt' }
                    }
                }
            });

            


        })
    </script>
</head>
<body bgcolor="FFFFE0">


<table >

<tr height="60px"> </tr>
<tr>

  <td width="10%"></td> 
 
 
  <td>
    <div id="chart2" style="width: 1000px; height: 400px;"></div>
  </td>
 
</tr>    
</table>


</body>


</html>