package cases.cust;

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

public class Zhgl {
	private final String choiceType_x = "//*[@id=\"addButton\"]";
	private final String groupName_x = "//*[@id=\"GROUP_NAME\"]";
	private final String searchGroup_x = "//*[@id=\"qryBox\"]/button/span";
	private final String searchGroupById_x = "//*[@id=\"GROUP_QUERY_TYPE_span\"]";
	private final String searchGroupByIdInput_x = "//*[@id=\"GROUP_ID\"]";
	private final String cleanManagerButton_x = "//*[@id=\"qryCondGroupID\"]/div[2]/div[1]/ul/li[2]/div[2]/span/span";
	private final String noSelectManagerButton_x = "//*[@id=\"clearSelect\"]";
	private final String searchGroupButton_x = "//*[@id=\"qryCondGroupIDBtn\"]";
	private final String selectGroup_x = "//*[@id=\"groupRows\"]/tr/td[3]";
	private final String shitu_x = "//*[@id=\"openViewButton\"]";
	private final String accountinfo_x = "//*[@id=\"UI-menu\"]/li[4]/div";
	private final String xinjianzhbutton_x = "//*[@id=\"newButton\"]";
	private final String BillLanguage_x = "//*[@id=\"bill_LANGUAGE_span\"]/span[1]";
	private final String AccountPeriod_x = "//*[@id=\"acctInfo_BILL_CYCLE_span\"]";
	private final String EmailReminds_x = "//*[@id=\"EmailAlert\"]/input";
	private final String Printpaperbills_x = "//*[@id=\"bill_PAPER_BILLS_span\"]/span[2]";
	private final String PaymentMethod_x = "//*[@id=\"payment_PAY_METHOD_span\"]/span";
	private final String AreaInfo_x = "//*[@id=\"contactDiv\"]/ul/li[1]";
	private final String AddressLanguage_x = "//*[@id=\"CUSTOMER_ADDRESS_LANGUAGE_span\"]/span[1]";
	private final String Area_x = "//*[@id=\"CUSTOMER_AREA_NAME\"]";
	private final String District_x = "//*[@id=\"CUSTOMER_DISTRICT_NAME\"]";
	private final String Street_x = "//*[@id=\"CUSTOMER_STREET_NAME\"]";
	private final String StreetNo_x = "//*[@id=\"address_STREET_NO\"]";
	private final String Estate_x = "//*[@id=\"CUSTOMER_ESTATE_NAME\"]";
	private final String Building_x = "//*[@id=\"CUSTOMER_BUILDING_NAME\"]";
	private final String Block_x = "//*[@id=\"CUSTOMER_BLOCK_NAME\"]";
	private final String Floor_x = "//*[@id=\"CUSTOMER_FLAT_NAME\"]";
	private final String Flat_x = "//*[@id=\"CUSTOMER_FLAT_NAME\"]";
	private final String ContactInformation_x = "//*[@id=\"contactDiv\"]/ul/li[2]";
	private final String Email_x = "//*[@id=\"contact_EMAIL\"]";
	private final String MattersAttention_x = "//*[@id=\"contact_ATTENTION\"]";
	private final String MobilePhone_x = "//*[@id=\"contact_CONT_NUMBER\"]";
	private final String HomePhone_x = "//*[@id=\"contact_FAMILY_NUMBER\"]";
	private final String HomeFax_x = "//*[@id=\"contact_FAMILY_FAX\"]";
	private final String OfficePhone_x = "//*[@id=\"contact_FAX_NUMBER\"]";
	private final String OfficeFax_x = "//*[@id=\"contact_FAX_NUMBER\"]";
	private final String backbutton2_x = "//*[@id=\"editAcctInfoPopupItem\"]/div[1]/div";
	private final String submitbutton_x = "//*[@id=\"saveAcctButton\"]";
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Zhgl(){}
	public Zhgl(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> XJZH() throws Exception {
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
			comOp.intomenu("客户资料管理");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.searchGroup_x));
			comOp.waitamoment("2");
			pageOp.choicebyindex(By.xpath(this.searchGroupById_x),"2");
			comOp.waitamoment("2");
			String groupId=dbOp.searchString("select t.group_id from party.cb_enterprise t where t.op_id='91110066' and rownum=1 and t.data_status=1 and t.group_status=2 order by t.create_date desc");
			saveData = saveData +"groupId="+groupId+"|";
			resultMap.put("saveData", saveData);
			pageOp.input(By.xpath(this.searchGroupByIdInput_x),groupId);
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.searchGroupButton_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.selectGroup_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.shitu_x));
			comOp.waitamoment("2");
			comOp.switchpage("3");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.accountinfo_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.xinjianzhbutton_x));
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.BillLanguage_x),"1");
			comOp.waitamoment("2");
			pageOp.choicebyindex(By.xpath(this.AccountPeriod_x),"1");
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.EmailReminds_x),"0");
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Printpaperbills_x),"0");
			comOp.waitamoment("2");
			pageOp.choicebyindex(By.xpath(this.PaymentMethod_x),"1");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.AreaInfo_x));
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.AddressLanguage_x),"1");
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Area_x),"香港");
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.District_x),"香港仔");
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Street_x),"香港仔大道");
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Street_x),"香港仔大道");
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Block_x),"2栋");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.ContactInformation_x));
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Email_x),"123@163.com");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.submitbutton_x));
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


	public HashMap<String,String> XJZHCheck() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String groupId = "";
			resultMap.put("message", "");
			resultMap.put("result", "正确");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			groupId = saveDataMap.get("groupId");
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

