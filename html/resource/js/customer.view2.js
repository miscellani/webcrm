$(function() {

  //模拟数据
  var data = [['成功', 100], ['失败', 200], ['未执行', 300]];

  //创建饼图
  $("#dataForPie").highcharts({
    chart : {
      margin: [50, 80, 50,80] ,
      plotBackgroundColor : null,
      plotBorderWidth : null,
      plotShadow : false
    },
    colors:[

               '#ADFF2F',
               '#FF4500', 
               '#DCDCDC'
          ],
          
    title : {
      text : '执行结果统计'
    },
    plotOptions : {
      pie : {
        allowPointSelect : true,
        cursor : 'pointer',
        dataLabels : {
          enabled : true,

          formatter: function() {
            var html = '';
            html += '<b>' + this.point.name + '</b>:';
            html += this.percentage.toFixed(1) + '%</a>';
            return html;
          }
        },
        showInLegend : true
      }
    },

    series : [ {
      type : 'pie',
      name : '执行结果统计',
      data : data
    } ]
  });

});
