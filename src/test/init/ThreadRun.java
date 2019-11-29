package test.init;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import test.web.WebInit;
import test.web.cases.com.LoginPage;
import base.com.zl.db.ConfigDbOperate;
import base.com.zl.log.Log;
import base.com.zl.selenium.OpWebDriver;
import base.com.zl.utils.DataUtil;

public class ThreadRun implements Runnable{
	
	private ArrayList perDivideCaseList;
	private String userIp;
	
	public  ThreadRun(String user,ArrayList list){
		perDivideCaseList = list;
		userIp = user;
	}
	


	public void run() {
		// TODO Auto-generated method stub

		OpWebDriver opWebDriver = new OpWebDriver();
		String[] runConfig = userIp.split("\\,");
		WebDriver webDriver = null;
		
		
		CaseHandle caseHandle = new CaseHandle();
		String stop = "0";
		String currentCaseNum = "";
		for (int j = 0; j < perDivideCaseList.size() ; j++) {	

          ///落到循环启动关闭
			try {
				webDriver = opWebDriver.openRBrower(runConfig[0],runConfig[1]);
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			cases.com.LoginPage loginPage = new cases.com.LoginPage(webDriver);
			ConfigDbOperate configDbOperate = new ConfigDbOperate();
			DataUtil dataUtil = new DataUtil();
			try {
				opWebDriver.get(webDriver,WebInit.mWebUrl);			
				loginPage.login(WebInit.pointLogin,WebInit.opId, WebInit.opPass);
			} catch (Exception e) {
				// TODO: handle exception
				Log.info("-----操作员登陆失败,请查看环境和配置");
				Log.info("-----操作员登陆失败,请查看环境和配置");
				Log.info("-----操作员登陆失败,请查看环境和配置");
				e.printStackTrace();

			}
			
			
			
			
			
			
			
				
			try {
				webDriver = caseHandle.excuteCases(webDriver, runConfig[0],runConfig[1],(String) perDivideCaseList.get(j));

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Log.info("执行case异常："+(String) perDivideCaseList.get(j)+"\n");
				Log.info(dataUtil.getTrace(e1)+"\n");

			}
			
			
				//判断控制是否结束运行
				try {
					
					stop= configDbOperate.searchStrings("select t.stop from web_control t").get(0);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(stop.equals("1")){


					break;
					
				}
				
				try {
					//添加关闭浏览器
					opWebDriver.close(webDriver);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					// TODO: handle finally clause
					Log.info("case执行结束:id=："+(String) perDivideCaseList.get(j)+"\n");
					WebInit.downCurrentCaseNum();
					//currentCaseNum =WebInit.getCurrentCaseNum();
					//Log.info("当前待执行CSAE数："+currentCaseNum+"个\n");

				}

				


		}
		
		WebInit.downCurrentThreadNum();
		Log.info("当前用户执行完毕，还剩执行用户数："+WebInit.getCurrentThreadNum()+"个\n");

		
	}



}