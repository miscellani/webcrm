package task;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.TimerTask;
 
import javax.servlet.ServletContext;

import base.com.zl.db.ConfigDbOperate;
import base.com.zl.db.DbOperate;
import base.com.zl.log.Log;
import base.com.zl.utils.DataUtil;
import base.com.zl.utils.DateUtil;
import test.config.bean.WebConfigBean;
import test.config.dao.WebConfigDao;
import test.web.WebInit;
 
public class CCoreTask extends TimerTask{
	private ServletContext sc = null;
	private static boolean running=false;
 
	//constructor method of CCoreTask
	public CCoreTask(ServletContext sc){
		this.sc = sc;
	}
 
	@Override
	public void run() {
		
		DataUtil dataUtil = new DataUtil();

		    try {
		    	//启动1分钟后再执行
				//Thread.sleep(1000*60*1);
		    	

			WebConfigDao webConfigDao = new WebConfigDao();
			//String smoke = webConfigDao.getRunParam("smoke");
			String smoke = webConfigDao.getRunParam("smoketime");

		    //if(!(smoke.equals("0"))){
			DateUtil dateUtil = new  DateUtil();
			//20180412121212
			String shi = dateUtil.nowToNum();
			shi = shi.substring(8, 11);
            String smokerun = webConfigDao.getRunParam("smoke");
	    	ConfigDbOperate configDbOperate = new ConfigDbOperate();
            String nowtime= dateUtil.now();
			if(smoke.equals(shi)&&smokerun.equals("0")){
		    	System.out.println("开始冒烟测试");
		    	//Log.info("-----定时冒烟开始-----");
		    	configDbOperate.updateData("update web_config set para1='1' where paratype='runparam' and paracode='smoke'");
	            
			   
		    	HashMap<String, String> map = new HashMap<>();
		    	//map.put("filterType", "filter");
		    	map.put("filterType", "all");

				map.put("caseLevel", "1','A");

				
/*				if(!smoke.equals("1")){
					map.put("env", smoke);
				}*/


/*	
					map.put("module", "");
						map.put("module", module);
						map.put("caseName", caseName);
						map.put("tester", tester);

						map.put("result", result);	
						map.put("menuName", menuName);
						map.put("admin", admin);*/
						
						
						
				new WebInit().running(map,"","", "");

		    }

	    	//Log.info("-----定时冒烟时辰未到-----");
	    	System.out.println("当前时间："+nowtime+"-----定时冒烟时辰未到-----");
		    
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.info("冒烟测试异常:-----\n");
				Log.info(dataUtil.getTrace(e));

			}finally{
				
		    	ConfigDbOperate configDbOperate = new ConfigDbOperate();
				try {
					configDbOperate.updateData("update web_config set para1='0' where paratype='runparam' and paracode='smoke'");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	}
 
}

