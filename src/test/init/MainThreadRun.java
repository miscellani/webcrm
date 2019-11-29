package test.init;

import java.util.HashMap;

import base.com.zl.log.Log;
import base.com.zl.utils.DataUtil;
import test.web.WebInit;



public class MainThreadRun implements Runnable{
	
	
	private HashMap<String, String> map;
	
 
	public MainThreadRun(HashMap<String, String> map){
		this.map =map;
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			new WebInit().running(map,"","", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DataUtil dataUtil =  new DataUtil();
			Log.info("自动化测试异常:-----\n");
			Log.info(dataUtil.getTrace(e));

		}
		
	}
	
}