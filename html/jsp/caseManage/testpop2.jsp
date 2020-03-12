<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索页面</title>

</head>
<script src="<%=basePath%>resource/js/jqplot/jquery.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		//文本框keyup的时候发送ajax
		$("#tid").keyup(function(){
			//alert('123');
			//获取文本框中的值
			var $value = $(this).val();
			//alert($value);
			//内容为空的时候不发送ajax
			if($value!=null&&$value!=''){
				//清空div
				$("#did").html("");
				
				$.post(
				"<%=basePath%>servlet/SearchPopServlet",
				"kw="+$value,
				function(d){
					//alert(d);
					var arr = d.split(",");
					//遍历数组
					$(arr).each(function(){
						//可以将每一个值放入一个div,将其插入到id为did的div
						$("#did").append($("<div>"+this+"</div>"));
						
					});
					//将div显示
					$("#did").show();
				
				}

				);
				
			}else{
				//内容为空的时候，将div隐藏
				$("#did").hide();
				
				
			}
			
			
		});
		
		
		
		
	})




</script>


<body>
	<center>
		<div>
			<h1>搜索</h1>
			<div>
				<!-- <input name="kw" id="tid"> -->
				<textarea rows="40" id="tid"  name="kw" 
							style="font-size:15px;height:100%;width: 100%; resize: none;"></textarea>
				<input type="button" value="搜索一下">
			
			</div>
			<div id="did" style="border:1px solid red;width:241px;height:100px;position: relative;display: none"></div>
		
		
		</div>
	
	</center>

</body>
</html>