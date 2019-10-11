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

public class Jtywsl {
	private final String Choice_x = "//*[@id=\"mainOfferSelect\"]/div[2]/ul/li";
	private final String IDC_x = "//*[@id=\"110200000005\"]";
	private final String Button_x = "//*[@id=\"cata_100870_null\"]/div[2]/button";
	private final String keyWords_x = "//*[@id=\"keyWords\"]";
	private final String Qry_x = "//*[@id=\"searchButton\"]";
	private final String Cata_x = "//*[@id=\"cata_109920_null\"]/div[2]/button";
	private final String Set_x = "//*[@id=\"MIAN_DIV_TOM\"]/div[2]/span";
	private final String ChildOff_x = "//*[@id=\"ChildOfferChooseTree_803000006274_315909\"]";
	private final String ChildOffa_x = "//*[@id=\"ChildOfferChooseTree_803000006270_100861\"]";
	private final String PSubmit_x = "//*[@id=\"productPopupItem\"]/div[3]/button[2]";
	private final String Sett_x = "//*[@id=\"div_122090003239\"]/div[1]/div[2]/span";
	private final String Setti_x = "//*[@id=\"prodSpecUL\"]/li/div[2]/span";
	private final String Location_x = "//*[@id=\"pam_217400001_span\"]";
	private final String Period_x = "//*[@id=\"pam_217400002_span\"]";
	private final String Number_x = "//*[@id=\"pam_217400003\"]";
	private final String Power_x = "//*[@id=\"pam_217400005\"]";
	private final String Connect_x = "//*[@id=\"pam_217400006_span\"]";
	private final String Quantity_x = "//*[@id=\"pam_217400010\"]";
	private final String Remote_x = "//*[@id=\"pam_217400008\"]";
	private final String BUTTONs_i = "pam_SUBMIT_BUTTON";
	private final String ASubmit_x = "//*[@id=\"productPopupItem\"]/div[3]/button[2]";
	private final String Seta_x = "//*[@id=\"div_100861\"]/div[1]/div[2]/span";
	private final String Setta_x = "//*[@id=\"li_100861\"]/div[2]/span";
	private final String license_x = "//*[@id=\"pam_217300002\"]";
	private final String Setb_x = "//*[@id=\"div_315909\"]/div[1]/div[2]/span";
	private final String Settb_x = "//*[@id=\"li_315909\"]/div[2]/span";
	private final String Acc_x = "//*[@id=\"i_AcctInfoPart\"]/ul/li/div[2]/span/span";
	private final String Groupfl_x = "//*[@id=\"mySelect_span\"]";
	private final String APeriod_x = "//*[@id=\"acctInfo_BILL_CYCLE_span\"]";
	private final String PaymentM_x = "//*[@id=\"payment_PAY_METHOD_span\"]";
	private final String Email_x = "//*[@id=\"contact_EMAIL\"]";
	private final String Area_x = "//*[@id=\"CUSTOMER_AREA_NAME\"]";
	private final String District_x = "//*[@id=\"CUSTOMER_DISTRICT_NAME\"]";
	private final String Save_x = "//*[@id=\"saveAcctButton\"]";
	private final String SubmitG_x = "//*[@id=\"OpenSubmit\"]/button[2]";
	private final String OK_x = "/html/body/div[22]/div[1]/div[2]/div[2]/button";
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Jtywsl(){}
	public Jtywsl(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> testIDC01() throws Exception {
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
			String abc=comOp.loginbykey("g_groupid");
			saveData = saveData +"abc="+abc+"|";
			resultMap.put("saveData", saveData);
			comOp.waitamoment("1");
			comOp.intomenu("集团业务受理");
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Choice_x));
//选择物联网商品
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.IDC_x));
//选择IDC商品
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Cata_x));
//设置主产品
			pageOp.click(By.xpath(this.Set_x));
			comOp.waitamoment("1");
//勾选产品
			pageOp.click(By.xpath(this.ChildOff_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.ChildOffa_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.PSubmit_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Sett_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Setti_x));
			comOp.waitamoment("2");
//设置基础产品属性
			pageOp.choicebyindex(By.xpath(this.Location_x),"2");
			pageOp.choicebyindex(By.xpath(this.Period_x),"1");
			comOp.waitamoment("1");
			pageOp.input(By.xpath(this.Number_x),"12");
			pageOp.input(By.xpath(this.Power_x),"12");
			comOp.waitamoment("1");
			pageOp.choicebyindex(By.xpath(this.Connect_x),"2");
			pageOp.input(By.xpath(this.Quantity_x),"13");
			pageOp.input(By.xpath(this.Remote_x),"13");
			comOp.waitamoment("2");
			ArrayList<WebElement> list1 = pageOp.getelementlist(By.id(this.BUTTONs_i));
			pageOp.click(By.id(this.BUTTONs_i));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.ASubmit_x));
			comOp.waitamoment("2");
//设置资费产品属性
			pageOp.click(By.xpath(this.Seta_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Setta_x));
			comOp.waitamoment("2");
//问题
			pageOp.input(By.xpath(this.license_x),"2");
			comOp.waitamoment("2");
		//	pageOp.setattrvalue(By.id(this.BUTTONs_i), "stype", "display: block;");
			ArrayList<WebElement> list2 = pageOp.getelementlist(By.id(this.BUTTONs_i));

			pageOp.eclick(By.id(this.BUTTONs_i));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.ASubmit_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Setb_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Settb_x));
			comOp.waitamoment("1");
			pageOp.click(By.id(this.BUTTONs_i));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.PSubmit_x));
//创建账户
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Acc_x));
			comOp.waitamoment("3");
//切换到账户信息界面选择
			comOp.switchframe("1","0");
			comOp.waitamoment("1");
//新建账户信息
			pageOp.choicebyindex(By.xpath(this.Groupfl_x),"1");
			comOp.waitamoment("5");
			pageOp.choicebyindex(By.xpath(this.APeriod_x),"1");
			comOp.waitamoment("1");
			pageOp.choicebyindex(By.xpath(this.PaymentM_x),"1");
			pageOp.input(By.xpath(this.Email_x),"66@qq.com");
			pageOp.input(By.xpath(this.Area_x),"九龙");
			pageOp.input(By.xpath(this.District_x),"1号街");
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Save_x));
			comOp.waitamoment("2");
			comOp.switchparentframe();
			pageOp.click(By.xpath(this.OK_x));
//切回元界面
			comOp.waitamoment("2");
			comOp.switchframebyid("navframe_121");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.SubmitG_x));
//获取订单
			comOp.waitamoment("30");
			String orderId=comOp.getorderid();
			saveData = saveData +"orderId="+orderId+"|";
			resultMap.put("saveData", saveData);
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


	public HashMap<String,String> testIDC01Check() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String abc = "";
			String orderId = "";
			resultMap.put("message", "");
			resultMap.put("result", "正确");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			abc = saveDataMap.get("abc");
			orderId = saveDataMap.get("orderId");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "检验步骤获取变量值报错");
			}
	try {
			resultMap = dbCheck.checksql("1","select  count(*)  from ord.om_order_f_yyyymm where order_id in ('"+orderId+"')","校验订单是否竣工",resultMap);
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

