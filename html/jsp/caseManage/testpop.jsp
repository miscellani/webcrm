<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>111</title>
<style>
*{
    margin:0;
    padding:0;
}
body {
    background-color:#fefffa;
    color:#fff;
    font:14px/1.3 Arial,sans-serif;
}
header {
    background-color:#212121;
    box-shadow: 0 -1px 2px #111111;
    display:block;
    height:70px;
    position:relative;
    width:100%;
    z-index:100;
}
header h2{
    font-size: 22px;
    font-weight: normal;
    left: 40%;
    margin-left: -300px;
    padding: 22px 0;
    position: absolute;
    width: 1000px;
}
header a.stuts,a.stuts:visited{
    border: none;
    color: #FCFCFC;
    font-size: 14px;
    left: 50%;
    line-height: 31px;
    margin: 23px 0 0 110px;
    position: absolute;
    text-decoration: none;
    top: 0;
}
header .stuts span {
    font-size:22px;
    font-weight:bold;
    margin-left:5px;
}
.container {
    background: url("/imagesforcode/201304/scene.jpg") no-repeat scroll center top transparent;
    color: #000000;
    height: 535px;
    margin: 20px auto;
    overflow: hidden;
    position: relative;
    width: 1030px;
}
.dialog {
    background-color: rgba(163, 154, 77, 0.9);
    color: #FFFFFF;
    display: none;
    height: 140px;
    left: 343px;
    line-height: 24px;
    padding: 100px 30px;
    position: absolute;
    text-align: center;
    top: 97px;
    width: 280px;
    z-index: 10;

    -moz-border-radius: 170px;
    -ms-border-radius: 170px;
    -o-border-radius: 170px;
    -webkit-border-radius: 170px;
    border-radius: 170px;
}
.dialog .close {
    background-color: #65683b;
    cursor: pointer;
    font-size: 22px;
    font-weight: bold;
    height: 36px;
    line-height: 36px;
    position: absolute;
    right: 10px;
    top: 60px;
    width: 36px;

    -moz-border-radius: 18px;
    -ms-border-radius: 18px;
    -o-border-radius: 18px;
    -webkit-border-radius: 18px;
    border-radius: 18px;
}
.labels p {
    display: none;
}
.labels a {
    background-color: rgba(203, 189, 58, 0.8);
    color: #FFFFFF;
    display: none;
    height: 50px;
    padding: 30px 0 0;
    position: absolute !important;
    text-align: center;
    text-decoration: none;
    width: 80px;

    -moz-border-radius: 40px;
    -ms-border-radius: 40px;
    -o-border-radius: 40px;
    -webkit-border-radius: 40px;
    border-radius: 40px;
}
.labels > a {
    background-color: rgba(203, 189, 58, 0.8);

    -moz-transition: .3s;
    -ms-transition: .3s;
    -o-transition: .3s;
    -webkit-transition: .3s;
    transition: .3s;
}
.labels a:hover {
    background-color: rgba(128, 128, 128, 0.8);
}
.labels a span {
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    border-top: 15px solid rgba(203, 189, 58, 0.8);
    bottom: -14px;
    height: 0;
    left: 30px;
    position: absolute;
    width: 0;

    -moz-transition: .3s;
    -ms-transition: .3s;
    -o-transition: .3s;
    -webkit-transition: .3s;
    transition: .3s;
}
.labels a:hover span {
    border-top: 15px solid rgba(128, 128, 128, 0.8);
}
#label1 {
    left: 720px;
    top: 215px;
}
#label2 {
    left: 495px;
    top: 290px;
}
#label3 {
    left: 450px;
    top: 115px;
}
#label4 {
    left: 270px;
    top: 170px;
}
#label5 {
    left: 570px;
    top: 65px;
}
#label6 {
    left: 275px;
    top: 30px;
}
</style>
<script src="<%=basePath%>resource/js/jqplot/jquery.min.js"></script>
<script src="<%=basePath%>resource/js/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script>
jQuery(function(){
    $('.labels a#label1').fadeIn(100).effect('bounce', { times:3 }, 300, function() {
        $('.labels a#label2').fadeIn(100).effect('bounce', { times:3 }, 300, function() {
            $('.labels a#label3').fadeIn(100).effect('bounce', { times:3 }, 300, function() {
                $('.labels a#label4').fadeIn(100).effect('bounce', { times:3 }, 300, function() {
                    $('.labels a#label5').fadeIn(100).effect('bounce', { times:3 }, 300, function() {
                        $('.labels a#label6').fadeIn(100).effect('bounce', { times:3 }, 300);
                    });
                });
            });
        });
    });
    $('.dialog .close').click(function() {
        $(this).parent().fadeOut(500);
        return false;
    });
    $('.labels a').click(function() {
        $('.dialog p').html( $(this).find('p').html() ).parent().fadeIn(500);
        return false;
    });
    $('.container').click(function() {
        $('.dialog').fadeOut(500);
    });
});
</script>
</head>
<body>
<div class="container">
  <div class="labels"> <a id="label1" class="label" href="#">sofa
    <p>A sofa, is an item of furniture designed to seat more than one person, and providing support for the back and arms.</p>
    <span /> </a> <a id="label2" class="label" href="#">television
    <p>Television (TV) is a telecommunication medium for transmitting and receiving moving images that can be monochrome (black-and-white) or colored, with or without accompanying sound.</p>
    <span /> </a> <a id="label3" class="label" href="#">chest
    <p>In many video games, especially role-playing video games, treasure chests contain various items, currency, and sometimes monsters.</p>
    <span /> </a> <a id="label4" class="label" href="#">workplace
    <p>A virtual workplace is a workplace that is not located in any one physical space.</p>
    <span /> </a> <a id="label5" class="label" href="#">entrance
    <p>A door is a movable structure used to open and close off an entrance, typically consisting of a panel that swings on hinges or that slides or rotates inside of a space.</p>
    <span /> </a> <a id="label6" class="label" href="#">safe room
    <p>A safe room or panic room is a fortified room which is installed in a private residence or business to provide a safe shelter, or hiding place, for the inhabitants in the event of a break-in, home invasion, tornado, or other threat.</p>
    <span /> </a> </div>
  <div class="dialog">
    <p></p>
    <a class="close">X</a> </div>
</div>
</body>
</html>