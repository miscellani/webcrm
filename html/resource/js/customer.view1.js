$(function() {
  console.log('开始加载柱形图。。。');

  //模拟数据
  var data = [{
    code: '移动梦网',
    countNum:10
    },{
    code: '飞信',
    countNum:40
  },{
    code: '和多号',
    countNum:30
  },{
    code: '手机邮箱',
    countNum:40
  },{
    code: '手机支付',
    countNum:40
  }];
  //平台名称数组
  var plantArray = [];
  //业务类型值数组
  var categories = [];


  for(var i = 0; i < data.length; i++) {
    plantArray.push(data[i].countNum);
    categories.push(data[i].code);

  }

  //创建柱形图
  $('#dataForColumn').highcharts({
    chart: {
        type: 'column',
        margin: [50,200, 50,200] 
    },
    title: {
        text: '平台CASE数分布'
    },
    xAxis: {
        categories: categories,
        crosshair: true,
        labels: {
          rotation:1
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: '个数'
        }
    },
    tooltip: {
      formatter: function() {
        var html = '<span style="font-size:10px">' + this.x + '</span><table>';
        for (var i = 0; i < this.points.length; i ++) {
          var point = this.points[i];
            html += '<td style="padding:0">' + point.y + '个</b></a></td></tr>';
          
        }
        return html;
      },
      shared: true,
      useHTML: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    },

    series: [{
        name: '平台名称',
        data: plantArray,
        size:'20%',
		innerSize: '100%'
    }]
  });

});
