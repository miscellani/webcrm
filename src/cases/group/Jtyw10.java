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

public class Jtyw10 {
	private final String Choice_x = "//*[@id=\"mainOfferSelect\"]/div[2]/ul/li";
	private final String M2m_x = "//*[@id=\"110200000006\"]";
	private final String Button_x = "//*[@id=\"cata_100870_null\"]/div[2]/button";
	private final String keyWords_x = "//*[@id=\"keyWords\"]";
	private final String Qry_x = "//*[@id=\"searchButton\"]";
	private final String Cata_x = "//*[@id=\"cata_100955_null\"]/div[2]/button";
	private final String Set_x = "//*[@id=\"MIAN_DIV_TOM\"]/div[1]/div";
	private final String PSubmit_x = "//*[@id=\"productPopupItem\"]/div[3]/button[2]";
	private final String Sett_x = "//*[@id=\"div_220000000016\"]/div[1]/div[2]/span";
	private final String Setti_x = "//*[@id=\"prodSpecUL\"]/li/div[1]";
	private final String Administrators_x = "//*[@id=\"pam_217200029\"]";
	private final String Number_x = "//*[@id=\"pam_217200030\"]";
	private final String Manager_Phone_x = "//*[@id=\"pam_217200033\"]";
	private final String BUTTON_x = "//*[@id=\"pam_SUBMIT_BUTTON\"]";
	private final String ASubmit_x = "//*[@id=\"productPopupItem\"]/div[3]/button[2]";
	private final String Sett1_x = "//*[@id=\"div_315668\"]/div[1]/div[2]/span";
	private final String Setti1_x = "//*[@id=\"li_120156687731\"]/div[2]/span";
	private final String BUTTON1_i = "pam_SUBMIT_BUTTON";
	private final String price_x = "//*[@id=\"pam_217200035\"]";
	private final String BUTTON_price_x = "/html/body/div[4]/div/div/div[2]/div[4]/div[2]/div/div/div/div[2]/button[2]";
	private final String Price_x = "//*[@id=\"pcha_315669\"]";
	private final String ASubmit1_x = "//*[@id=\"productPopupItem\"]/div[3]/button[2]";
	private final String Acc_x = "//*[@id=\"i_AcctInfoPart\"]/ul/li/div[2]/span/span";
	private final String Groupfl_x = "//*[@id=\"mySelect_span\"]";
	private final String APeriod_x = "//*[@id=\"acctInfo_BILL_CYCLE_span\"]";
	private final String PaymentM_x = "//*[@id=\"payment_PAY_METHOD_span\"]";
	private final String Email_x = "//*[@id=\"contact_EMAIL\"]";
	private final String link_address_x = "//*[@id=\"CUSTOMER_ADDRESS_LANGUAGE_span\"]/span[1]";
	private final String Area_x = "//*[@id=\"CUSTOMER_AREA_NAME\"]";
	private final String Street_x = "//*[@id=\"CUSTOMER_STREET_NAME\"]";
	private final String Building_x = "//*[@id=\"CUSTOMER_BUILDING_NAME\"]";
	private final String Estate_x = "//*[@id=\"CUSTOMER_ESTATE_NAME\"]";
	private final String Block_x = "//*[@id=\"CUSTOMER_BLOCK_NAME\"]";
	private final String District_x = "//*[@id=\"CUSTOMER_DISTRICT_NAME\"]";
	private final String Floor_x = "//*[@id=\"CUSTOMER_FLOOR_NAME\"]";
	private final String Flat_x = "//*[@id=\"CUSTOMER_FLAT_NAME\"]";
	private final String Save_x = "//*[@id=\"saveAcctButton\"]";
	private final String SubmitG_x = "//*[@id=\"OpenSubmit\"]/button[2]";
	private final String OK_x = "//*[@id=\"wade_messagebox-73e2ed8028b1d147b66d3bfe01846ab2_btns\"]/button/span[2]";
	private final String ExpireDate_x = "//*[@id=\"normalOfferTime\"]/div[2]/ul/li[2]/div[2]";
	private final String QucickSet_x = "//*[@id=\"calendar2\"]/div[7]/button[2]";
	private final String Next2050Today_x = "//*[@id=\"calendar2\"]/div[6]/ul/li[8]";
	private final String DateOk_x = "//*[@id=\"calendar2\"]/div[7]/button[3]";
	private final String AresInfo_x = "//*[@id=\"contactDiv\"]/ul/li[1]";
	private final String AresOK_x = "//*[@id=\"addressSubmit\"]";
	private final String Account_x = "//*[@id=\"contactDiv\"]/ul/li[2]";
	private final String contactOK_x = "//*[@id=\"contactSubmit\"]";
	private final String Account_address_x = "//*[@id=\"CUSTOMER_FLOOR_ID\"]";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Jtyw10(){}
	public Jtyw10(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> jthfxso() throws Exception {
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
			String abc=comOp.loginbykey("g_groupid_nonfetion");
			saveData = saveData +"abc="+abc+"|";
			resultMap.put("saveData", saveData);
			comOp.waitamoment("2");
			comOp.intomenu("集团业务受理");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Choice_x));
//选择和飞信商品
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.M2m_x));
//查询和飞信套餐
			pageOp.input(By.xpath(this.keyWords_x),"RCS_Fee");
			pageOp.click(By.xpath(this.Qry_x));
			comOp.waitamoment("5");
			pageOp.click(By.xpath(this.Cata_x));
			comOp.waitamoment("2");
//设置主产品
			pageOp.click(By.xpath(this.Set_x));
			comOp.waitamoment("2");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.PSubmit_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Sett_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Setti_x));
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Administrators_x),"张三");
			pageOp.input(By.xpath(this.Number_x),"8877777");
			pageOp.input(By.xpath(this.Manager_Phone_x),"18787178787");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.BUTTON_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.ASubmit_x));
			comOp.waitamoment("3");
//创建账户
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Acc_x));
			comOp.waitamoment("3");
//切换到账户信息界面选择
//switchframe("1","0")
//waitamoment("2")
//新建账户信息
			pageOp.choicebyindex(By.xpath(this.Groupfl_x),"1");
			comOp.waitamoment("5");
			pageOp.choicebyindex(By.xpath(this.APeriod_x),"1");
			comOp.waitamoment("2");
			pageOp.choicebyindex(By.xpath(this.PaymentM_x),"1");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.AresInfo_x));
			comOp.waitamoment("2");
//click(link_address_x)
			pageOp.input(By.xpath(this.Area_x),"九龙");
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.District_x),"1号街");
			pageOp.input(By.xpath(this.Street_x),"41122");
			pageOp.input(By.xpath(this.Building_x),"895222");
			pageOp.input(By.xpath(this.Estate_x),"23002");
			pageOp.input(By.xpath(this.Estate_x),"335545");
			pageOp.setattrvalue(By.xpath(this.Account_address_x),"FLOOR_ID","56");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.AresOK_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Account_x));
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Email_x),"66@qq.com");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.contactOK_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Save_x));
			comOp.waitamoment("2");
//switchparentframe()
			pageOp.click(By.xpath(this.OK_x));
//切回原界面
			comOp.waitamoment("2");
			comOp.switchframebyid("navframe_121");
			comOp.waitamoment("2");
//设置失效时间后提交订单
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.ExpireDate_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.QucickSet_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Next2050Today_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.DateOk_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.SubmitG_x));
			comOp.waitamoment("2");
//获取订单
			comOp.waitamoment("30");
			String orderId=comOp.getgrouporderid();
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


	public HashMap<String,String> jthfxsoCheck() throws Exception {
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

