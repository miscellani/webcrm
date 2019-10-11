package cases.com;

import base.com.zl.com.ComOperate;
import base.com.zl.utils.DataUtil;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.com.zl.db.DbOperate;
import base.com.zl.db.ConfigDbOperate;
import java.util.Map;
import base.com.zl.check.DbCheck;
import base.com.zl.check.PageCheck;
import base.com.zl.selenium.OpWebDriver;
import base.com.zl.selenium.Page;
import java.io.File;

public class PublicOp {
	private final String groupList0_x = "//*[@id=\"groupList\"]";
	private final String groupList1_x = "//*[@id=\"groupList\"]/div[1]/div[1]/div";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public PublicOp(){}
	public PublicOp(WebDriver webDriver){
	this.webDriver = webDriver;
	}
	public PublicOp(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public  void  logout(boolean isGroup) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
MainPage MainPage = new MainPage(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			pageOp.switchDefaults();
			MainPage.logout(isGroup);
			comOp.waitamoment("2");
		}


	public boolean loginbynumber(String billId) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
PublicOp PublicOp = new PublicOp(webDriver);
MainPage MainPage = new MainPage(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			comOp.waitamoment("3");
			pageOp.confirmjspop();
			MainPage.closeErrorPop();
			MainPage.clearTab();
       if(billId.length()>11){
       						boolean isGroup=dataUtil.getboolean("true");
			PublicOp.logout(isGroup);
			boolean bb=MainPage.loginNumber(billId,isGroup);
         if(bb){
       						    return true;
  }			    return false;
       }else{
       						boolean isGroup=dataUtil.getboolean("false");
			PublicOp.logout(isGroup);
			boolean bb=MainPage.loginNumber(billId,isGroup);
         if(bb){
       						    return true;
  }			    return false;
}		}


	public String loginbykey(String sqlKey) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
PublicOp PublicOp = new PublicOp(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
//number=""
			int t=dataUtil.getint("0");
       cycleId=0;
       while( cycleId<6){
       cycleId++;
       						String number=comOp.getnumberbykey(sqlKey);
			boolean b=PublicOp.loginbynumber(number);
         if(b){
       						    return number;
  }//number=""
}			return "";
		}


	public boolean intomenu(String menuName) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
MainPage MainPage = new MainPage(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			comOp.waitamoment("3");
			pageOp.confirmjspop();
			MainPage.closeErrorPop();
			MainPage.clearTab();
			String strings=pageOp.getattrvalue(By.xpath(this.groupList0_x),"class");
       if(strings.equals("list list-show")){
       						pageOp.click(By.xpath(this.groupList1_x));
}			comOp.waitamoment("2");
			boolean b=MainPage.intoMenuByInput(menuName);
			return b;
		}


	public String logintomenu(String sqlKey,String menuName) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
PublicOp PublicOp = new PublicOp(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
       cycleId=0;
       while( cycleId<6){
       cycleId++;
       						String phonenum=PublicOp.loginbykey(sqlKey);
			boolean b=PublicOp.intomenu(menuName);
         if(b){
       						comOp.waitamoment("2");
			    return phonenum;
       }else{
       			          if(sqlKey.contains("g_")){
       						PublicOp.logout(true);
       }else{
       						PublicOp.logout(false);
       }
       			  }}			comOp.waitamoment("2");
			return "";
		}


}

