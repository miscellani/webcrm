package cases.terminal;

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

public class Sjyy {
	private final String Add_x = "//*[@id=\"MainPage\"]/div[1]/div/div[1]/button";
	private final String S_CustomerInfo_x = "//*[@id=\"ShowSaveCustDiv\"]/ul/li[1]";
	private final String I_ContractNum_x = "//*[@id=\"CONT_NUMBER\"]";
	private final String S_EmailPre_x = "//*[@id=\"EMAIL_PREFIX\"]";
	private final String S_EmailSuf_x = "//*[@id=\"MOBILE_CONTACT_EMAIL_SUFFIX_span\"]";
	private final String S_Sex_x = "//*[@id=\"MOBILE_GENDER_span\"]/span[1]";
	private final String SubmitCustomer_x = "//*[@id=\"MOBILE_BASEINFO_SUBMIT_BTN\"]";
	private final String S_ProgramCode_x = "//*[@id=\"RS_PROGRAM_CODE_span\"]";
	private final String S_handsetin_x = "//*[@id=\"AddReservePhoneDiv\"]/div[7]/ul/li[1]/div[2]/span";
	private final String S_handsetOne_x = "//*[@id=\"SelTerminalPopupItem\"]/div[4]/div/div/ul/li[1]/div/div[2]";
	private final String S_CollectOutlet_x = "//*[@id=\"AddReservePhoneDiv\"]/div[7]/ul/li[3]/div[2]";
	private final String S_CollectOutletOne_x = "//*[@id=\"SelCollectOutletPopupItem\"]/div[2]/div/ul/li[1]/div/div";
	private final String S_Deposit_x = "//*[@id=\"ShowPayMethodLi\"]/ul/li[1]/div[2]/span/span";
	private final String I_Cash_x = "//*[@id=\"keyWord\"]";
	private final String S_Search_x = "//*[@id=\"SelectPayMethodHalfPopupItem\"]/div[1]/div[2]/span/button";
	private final String S_PaymentMethod_x = "//*[@id=\"PayMethodsPart\"]/ul/li[1]/div[1]/div";
	private final String Submit_x = "//*[@id=\"CSSUBMIT_BUTTON\"]/span";
	private final String Get_ServerNo_x = "//*[@id=\"submitSucDiv\"]/div[2]/div/div[2]/span";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Sjyy(){}
	public Sjyy(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> sjyy_001() throws Exception {
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
			String accessnum=comOp.logintomenu("p_normal","手机预约");
			saveData = saveData +"accessnum="+accessnum+"|";
			resultMap.put("saveData", saveData);
//号码登陆
			comOp.waitamoment("2");
//等待2秒
			pageOp.click(By.xpath(this.Add_x));
//点击新增按钮
			comOp.waitamoment("2");
//等待2秒
			pageOp.click(By.xpath(this.S_CustomerInfo_x));
//点击客户信息按钮
			comOp.waitamoment("2");
//等待2秒
			String getRandom=dataUtil.getrandom("8");
			saveData = saveData +"getRandom="+getRandom+"|";
			resultMap.put("saveData", saveData);
			pageOp.input(By.xpath(this.I_ContractNum_x),getRandom);
			comOp.waitamoment("2");
//等待2秒
			pageOp.input(By.xpath(this.S_EmailPre_x),"1");
			comOp.waitamoment("2");
//等待2秒
			pageOp.choicebytext(By.xpath(this.S_EmailSuf_x),"qq.com");
			comOp.waitamoment("2");
//等待2秒
			pageOp.click(By.xpath(this.S_Sex_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.SubmitCustomer_x));
			comOp.waitamoment("2");
//等待2秒
//p_programcode=getnumberbykey("p_programcode")
			pageOp.choicebytext(By.xpath(this.S_ProgramCode_x),"iPhone preorder program");
			comOp.waitamoment("2");
//等待2秒
//选择programcode
			pageOp.click(By.xpath(this.S_handsetin_x));
//进入选择手机页面
			comOp.waitamoment("2");
//等待2秒
			pageOp.click(By.xpath(this.S_handsetOne_x));
//选择手机
			comOp.waitamoment("2");
//等待2秒
			pageOp.click(By.xpath(this.S_CollectOutlet_x));
			comOp.waitamoment("2");
//进入门店选择界面
			pageOp.click(By.xpath(this.S_CollectOutletOne_x));
			comOp.waitamoment("2");
//选择门店
			pageOp.click(By.xpath(this.S_Deposit_x));
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.I_Cash_x),"Cash");
			comOp.waitamoment("2");
//等待2秒
			pageOp.click(By.xpath(this.S_Search_x));
			comOp.waitamoment("2");
//等待2秒
			pageOp.click(By.xpath(this.S_PaymentMethod_x));
			comOp.waitamoment("2");
//等待2秒
			pageOp.click(By.xpath(this.Submit_x));
//业务提交
			String Reserver_no=pageOp.gettext(By.xpath(this.Get_ServerNo_x));
			saveData = saveData +"Reserver_no="+Reserver_no+"|";
			resultMap.put("saveData", saveData);
			String Reserver_no_end=dataUtil.substring(Reserver_no,"18","32");
			saveData = saveData +"Reserver_no_end="+Reserver_no_end+"|";
			resultMap.put("saveData", saveData);
			comOp.waitamoment("5");
//等待5秒
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


	public HashMap<String,String> sjyy_001Check() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String accessnum = "";
			String getRandom = "";
			String Reserver_no = "";
			String Reserver_no_end = "";
			resultMap.put("message", "");
			resultMap.put("result", "正确");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			accessnum = saveDataMap.get("accessnum");
			getRandom = saveDataMap.get("getRandom");
			Reserver_no = saveDataMap.get("Reserver_no");
			Reserver_no_end = saveDataMap.get("Reserver_no_end");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "检验步骤获取变量值报错");
			}
	try {
			resultMap  = dbCheck.checksql("1","select count(*) from  bassi.OM_RESERVE_HANDSET_ORDER a where a.reserve_no='"+Reserver_no_end+"'","校验入库数据数据是否正确",resultMap);
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

