$(function() {
	  var data = '["[Date.UTC(2015,3,24,1,2,3),55]","[Date.UTC(2015,3,29,1,3,3),34]","[Date.UTC(2015,4,1,1,3,4),6]","[Date.UTC(2015,4,1,1,3,5),1,0]","[Date.UTC(2015,4,1,1,3,6),44]","[Date.UTC(2015,4,1,1,3,7),29]","[Date.UTC(2015,4,1,1,3,8),66]","[Date.UTC(2015,4,1,1,3,9),44]","[Date.UTC(2015,4,1,1,3,10),6]","[Date.UTC(2015,4,1,1,3,11),9]","[Date.UTC(2015,4,1,1,3,12),5]","[Date.UTC(2015,4,1,1,3,13),7]","[Date.UTC(2015,4,1,1,3,14),17]","[Date.UTC(2015,4,1,1,3,15),6]","[Date.UTC(2015,4,1,1,3,16),5]","[Date.UTC(2015,4,1,1,3,17),5]","[Date.UTC(2015,4,1,1,3,18),4]","[Date.UTC(2015,4,1,1,3,19),3]","[Date.UTC(2015,4,1,1,3,20),2]","[Date.UTC(2015,4,1,1,3,21),43]","[Date.UTC(2015,4,1,1,3,22),22]","[Date.UTC(2015,4,1,1,3,23),32]","[Date.UTC(2015,4,1,1,3,24),23]","[Date.UTC(2015,4,1,1,3,25),12]","[Date.UTC(2015,4,1,1,3,26),33]","[Date.UTC(2015,5,1,1,3,27),80]"]';

  var reg = new RegExp('"', "g");
  resultText = data.replace(reg, "");
  var resultJson = eval(resultText);

  $('#dataForLine').highcharts('StockChart', {
	  chart: {
		    zoomType: 'x'
		  },
    rangeSelector : {
      inputDateFormat : '%Y-%m-%d %H:%M:%S',
      buttons : [],
      inputBoxWidth:150,
      selected : 5
    },

    xAxis : {
      dateTimeLabelFormats : {
        second : '%H:%M:%S',
        minute : '%H:%M',
        hour : '%H:%M',
        day : '%Y<br/>%m-%d',
        week : '%Y<br/>%m-%d',
        month : '%Y-%m',
        year : '%Y'
      },
      minTickInterval : 24 * 3600 * 1000
    },

    yAxis : {},

    navigator : {
      xAxis : {
        dateTimeLabelFormats : {
          second : '%H:%M:%S',
          minute : '%H:%M',
          hour : '%H:%M',
          day : '%Y-%m-%d',
          week : '%Y-%m-%d',
          month : '%Y-%m',
          year : '%Y'
        }
      }
    },

    title : {
      text : '执行时间统计'
    },

    tooltip : {
      xDateFormat : '%Y-%m-%d %H:%M:%S' 
    },

    series : [ {
      name : '消费时间',
      data : resultJson
    } ]
  });

});
