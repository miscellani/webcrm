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
<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.js"></script>
<style>
    html,body
    {
        height:100%;
        width:100%;
    }
</style>
<body>
    <div id="log-container" style="height: 100%; overflow-y: scroll; background: #333; color: #aaa; padding: 10px;">
        <div>
        </div>
    </div>
</body>
<script>
    $(document).ready(function() {
        // 指定websocket路径10.168.0.229
        var websocket = new WebSocket('ws://localhost:8080/websocket/ws.do');
        websocket.onmessage = function(event) {
            // 接收服务端的实时日志并添加到HTML页面中
            $("#log-container div").append(event.data + "<p> </p>");
            // 滚动条滚动到最低部
            $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
        };
    });
</script>
</body>
</head>
<body>


</body>
</html>
