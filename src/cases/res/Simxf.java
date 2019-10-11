package cases.res;

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

public class Simxf {
	private final String out_x = "//*[@id=\"part-OrganizeName\"]/span/span";
	private final String outlet_x = "//*[@id=\"OrganizeId_span\"]";
	private final String amout_x = "//*[@id=\"Amount\"]";
	private final String resspec_x = "//*[@id=\"RES_SPEC_ID_span\"]";
	private final String product_x = "//*[@id=\"RES_SKU_ID_span\"]";
	private final String shij_x = "//*[@id=\"topEditDiv\"]/ul/li[6]/div[2]/span/span[1]/span";
	private final String stnum_x = "//*[@id=\"Iccid\"]";
	private final String chax_x = "//*[@id=\"html\"]/body/div[1]/div[1]/div[6]/button[1]";
	private final String assgin_x = "//*[@id=\"html\"]/body/div[1]/div[1]/div[6]/button[2]";
	private final String submit_x = "//*[@id=\"html\"]/body/div[1]/div[1]/div[6]/button[3]";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Simxf(){}
	public Simxf(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> SIMxfTestFlow(String stnum,String Outlet,String resspec,String product) throws Exception {
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
			saveData = saveData +"stnum="+stnum+"|";
			resultMap.put("saveData", saveData);
			saveData = saveData +"Outlet="+Outlet+"|";
			resultMap.put("saveData", saveData);
			saveData = saveData +"resspec="+resspec+"|";
			resultMap.put("saveData", saveData);
			saveData = saveData +"product="+product+"|";
			resultMap.put("saveData", saveData);
			comOp.intomenu("SIM卡下发");
			 dbOp.updateData("update RES.RES_SIM_CARD_ORIGIN set res_state=1,delivery_org_id=null,delivery_date=null where IMSI=454129999999995");
//click(out_x)
//waitamoment("3")
			pageOp.choicebytext(By.xpath(this.outlet_x),Outlet);
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.stnum_x),stnum);
			pageOp.input(By.xpath(this.amout_x),"1");
			pageOp.choicebytext(By.xpath(this.resspec_x),resspec);
			comOp.waitamoment("2");
			pageOp.choicebytext(By.xpath(this.product_x),product);
			comOp.waitamoment("2");
			pageOp.setfixdate(By.xpath(this.shij_x),"2019","8","5");
			comOp.waitamoment("5");
			pageOp.click(By.xpath(this.chax_x));
			pageOp.aclick(By.xpath(this.chax_x));
			pageOp.click(By.xpath(this.assgin_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.submit_x));
			} catch (Exception e) {
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


	public HashMap<String,String> SIMxfTestFlowCheck() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String stnum = "";
			String Outlet = "";
			String resspec = "";
			String product = "";
			resultMap.put("message", "");
			resultMap.put("result", "正确");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			stnum = saveDataMap.get("stnum");
			Outlet = saveDataMap.get("Outlet");
			resspec = saveDataMap.get("resspec");
			product = saveDataMap.get("product");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "检验步骤获取变量值报错");
			}
	try {
			resultMap  = dbCheck.checksql("1","SELECT * FROM RES.RES_SIM_CARD_ORIGIN T where IMSI=454129999999995 and res_state=2 and delivery_org_id='21000100'","校验下发是否成功",resultMap);
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

