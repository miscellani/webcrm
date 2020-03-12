<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta charset="utf-8">
  <title>tail log</title>
　<!-- 此boot.js不需要-->
  <script src="<%=basePath%>resource/js/jqplot/jquery.min.js"></script>
 
 
<style>
div {line-height:18px; 

</style>
  
 

</style>
 
</head>
<body>

 <td  width="15%" ailgn="right" class="STYLE4"><input type="button" value="清屏"  onclick="clearContext()" /></td><td>&nbsp;&nbsp;&nbsp;</td>
<td width="15%" ailgn="right" class="STYLE4"><input type="button" value="打印"  onclick="getLog()" /></td><td>&nbsp;&nbsp;&nbsp;</td>
<div vailgn="top">
  <div style="background-color:black" id="ck1" name="product" class="mini-checkbox"  checked = "true" readOnly="false" text="是否滚动"
       onvaluechanged="onValueChanged" style="left: 20px"></div>

</div>



<div class="bg" style="background-color:black" style="font-size:15px" id="log-container" style="height: 480px; overflow-y: scroll;padding: 10px;">
  <div id="test">
  </div>
</div>



</body>





</body>
<script>

  // 控制是否滚动显示日志
  var checked = true;
  function onValueChanged(e) {
    checked = this.getChecked();
  }

function getLog() {




    // 指定websocket路径,此地址建议根据用js动态获取10.168.0.229 10.168.3.81
    var websocket = new WebSocket('ws://127.0.0.1:8080/webcrm/log');
    websocket.onmessage = function(event) {

      // 接收服务端的实时日志并添加到HTML页面中（error显示红色）
      if (event.data.search("ERROR") != -1) {
        $("#log-container div").append(event.data).css("color", "#FFFFFF");
      } else {
        $("#log-container div").append(event.data).css("color", "#FFFFFF");
      }

      // 是否滚动

        // 滚动条滚动到最低部
        $("#log-container").scrollTop( $("#log-container div").height() - $("#log-container").height());
      var div = document.getElementById("test");
       div.scrollTop = div.scrollHeight;
    };
  }





  // 清屏日志
  function clearContext() {
    $("#log-container div").empty();
  }

</script>
</body>
</html>