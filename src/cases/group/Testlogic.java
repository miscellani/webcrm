package cases.group;

import base.com.zl.com.ComOperate;
import base.com.zl.utils.DataUtil;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.com.zl.selenium.Page;
import base.com.zl.db.DbOperate;
import base.com.zl.db.ConfigDbOperate;
import java.util.Map;
import base.com.zl.check.DbCheck;
import base.com.zl.check.PageCheck;
import base.com.zl.selenium.OpWebDriver;
import java.io.File;

public class Testlogic {
//asdfasdf
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Testlogic(){}
	public Testlogic(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> test() throws Exception {
			String saveData = "";
			OpWebDriver opWebDriver = new OpWebDriver();
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver);
			HashMap resultMap  = new HashMap<>();
			resultMap.put("message", "");
			resultMap.put("result", "成功");
			resultMap.put("saveData", saveData);
			try{
			comOp.logout();
			String abc="123";
			saveData = saveData +"abc="+abc+"|";
			resultMap.put("saveData", saveData);
			String bc="456";
			saveData = saveData +"bc="+bc+"|";
			resultMap.put("saveData", saveData);
       if(1==2){
       			String a="456";
			saveData = saveData +"a="+a+"|";
			resultMap.put("saveData", saveData);
			comOp.loginbynumber("852111111637");
}       if(1<=2){
       			String b="456";
			saveData = saveData +"b="+b+"|";
			resultMap.put("saveData", saveData);
			comOp.loginbynumber("852111111637");
}			String c="456";
			saveData = saveData +"c="+c+"|";
			resultMap.put("saveData", saveData);
       if(1>=2){
       			String cdddd="456";
			saveData = saveData +"cdddd="+cdddd+"|";
			resultMap.put("saveData", saveData);
			comOp.loginbynumber("852111111637");
}       if(c.contains("1")){
       			String ee="456";
			saveData = saveData +"ee="+ee+"|";
			resultMap.put("saveData", saveData);
			comOp.loginbynumber("852111111637");
}       if(c.equals("1")){
       			String g="456";
			saveData = saveData +"g="+g+"|";
			resultMap.put("saveData", saveData);
			comOp.loginbynumber("852111111637");
       }else if(c.equals("1")){
       			String ag="dds";
			saveData = saveData +"ag="+ag+"|";
			resultMap.put("saveData", saveData);
       }else{
       			String agg="dds";
			saveData = saveData +"agg="+agg+"|";
			resultMap.put("saveData", saveData);
}       cycleId=0;
       while( cycleId<3+1){
       cycleId++;
       			String aggg="dds";
			saveData = saveData +"aggg="+aggg+"|";
			resultMap.put("saveData", saveData);
}			} catch (Exception e) {
			// TODO: handle exception
			String errMessage = dataUtil.getTrace(e);
			String message  =(String)resultMap.get("message");
			message = message+errMessage+"\n";
			resultMap.put("result", "失败");
			resultMap.put("message", message);
			String screenpath = testDir + caseId+File.separator +"执行操作步骤异常 "+ ".png";
			opWebDriver.screenShot(webDriver,screenpath);
			resultMap.put("exception", e);
			}finally{
			resultMap.put("saveData", saveData);
			return resultMap;
			}
		}


	public HashMap<String,String> testCheck() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String abc = "";
			String bc = "";
			String a = "";
			String b = "";
			String c = "";
			String cdddd = "";
			String ee = "";
			String g = "";
			String ag = "";
			String agg = "";
			String aggg = "";
			resultMap.put("message", "");
			resultMap.put("result", "正确");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			abc = saveDataMap.get("abc");
			bc = saveDataMap.get("bc");
			a = saveDataMap.get("a");
			b = saveDataMap.get("b");
			c = saveDataMap.get("c");
			cdddd = saveDataMap.get("cdddd");
			ee = saveDataMap.get("ee");
			g = saveDataMap.get("g");
			ag = saveDataMap.get("ag");
			agg = saveDataMap.get("agg");
			aggg = saveDataMap.get("aggg");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "检验步骤获取变量值报错");
			}
	try {
			} catch (Exception e) {
			// TODO: handle exception
			String errMessage = dataUtil.getTrace(e);
			String message  =resultMap.get("message");
			message = message+errMessage+"\n";
			resultMap.put("result", "错误");
			resultMap.put("message", message);
			}finally{
			return resultMap;
			}
			}


}

