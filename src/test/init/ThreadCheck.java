package test.init;

import java.util.ArrayList;

import test.web.WebInit;
import base.com.zl.db.ConfigDbOperate;
import base.com.zl.log.Log;
import base.com.zl.selenium.OpWebDriver;

public class ThreadCheck implements Runnable {

	private ArrayList perDivideCaseList;
	private String userIp;



	// 新增非前台自动化并行
	public ThreadCheck(ArrayList list) {
		perDivideCaseList = list;

	}

	public void run() {
		// TODO Auto-generated method stub

		ConfigDbOperate configDbOperate = new ConfigDbOperate();

		CaseHandle caseHandle = new CaseHandle();
		String stop = "2";

		String currentCaseNum="";
		for (int j = 0; j < perDivideCaseList.size(); j++) {

			try {

				caseHandle.checkCases((String) perDivideCaseList.get(j));

			} catch (Exception e1) {
				// TODO Auto-generated catch block

				Log.info("校验case异常_" + (String) perDivideCaseList.get(j) + "\n");

				e1.printStackTrace();
			}

			// 判断控制是否结束运行
			try {
				
				stop = configDbOperate.searchStrings("select t.stop from web_Control t")
						.get(0);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

			if (stop.equals("1")) {

				break;
			}

			//Init.currentNum--;
			WebInit.downCurrentCaseNum();
			currentCaseNum =WebInit.getCurrentCaseNum();
			Log.info("当前待校验CSAE数_" + currentCaseNum + "个\n");
		}

		
		
		
		
		
		//Init.threadUserNum--;
		WebInit.downCurrentThreadNum();
		Log.info("当前校验线程数_" + WebInit.getCurrentThreadNum() + "个\n");

	}

}