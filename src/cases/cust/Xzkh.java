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

public class Xzkh {
	private final String choiceType_x = "//*[@id=\"addButton\"]";
	private final String groupName_x = "//*[@id=\"GROUP_NAME\"]";
	private final String CusttypeSelect_x = "//*[@id=\"groupInfo_GROUP_STATUS_span\"]";
	private final String Groupfl_x = "//*[@id=\"groupInfo_GROUP_CATEGORY_span\"]";
	private final String IDType_x = "//*[@id=\"groupInfo_GROUP_BUSI_LICENCE_TYPE_span\"]";
	private final String IDName_x = "//*[@id=\"groupInfo_GROUP_LICENCE_NO\"]";
	private final String CustPassword_x = "//*[@id=\"groupInfo_PASSWORD\"]";
	private final String HQ_x = "//*[@id=\"groupInfo_SUPER_GROUP_NAME\"]";
	private final String Email_x = "//*[@id=\"groupInfo_EMAIL\"]";
	private final String Coname_x = "//*[@id=\"contact_CONTACT_NAME\"]";
	private final String Fiphone_x = "//*[@id=\"contact_FAMILY_PHONE\"]";
	private final String CIden_x = "//*[@id=\"contact_IDEN_NR\"]";
	private final String ConEmail_x = "//*[@id=\"contact_EMAIL\"]";
	private final String Part_x = "//*[@id=\"addPart\"]/div/div/div[6]/ul[3]/li/div[2]/span/span[1]/span";
	private final String Down_x = "//*[@id=\"_Wade_DropDownCalendar\"]/div[7]/button[3]";
	private final String EmployeesStaff_x = "//*[@id=\"groupInfo_ENT_EMPLOYEE_COUNT\"]";
	private final String GroupType_x = "//*[@id=\"groupInfo_ENTERPRISE_SIZE_CODE_span\"]";
	private final String Submit_x = "//*[@id=\"submitButton\"]";
	private final String uptnc_x = "//*[@id=\"groupExInfo_SCAN_FILE_URL\"]";
	private final String updown_x = "//*[@id=\"groupExInfo_SCAN_FILE_URL\"]";
	private final String by_x = "//*[@id=\"addPart\"]/div/div/div[2]/ul[6]/li/div[2]/span/span[6]/form/input";
	private final String Quyu_x = "//*[@id=\"CUSTOMER_AREA_NAME\"]";
	private final String Xingzheng_x = "//*[@id=\"CUSTOMER_DISTRICT_NAME\"]";
	private final String uptn_x = "//*[@id=\"addPart\"]/div/div/div[2]/ul[2]/li/div[2]/span/span[6]/form/input";
	private final String groupno_x = "/html/body/div[18]/div[1]/div[2]/div[1]/div[2]";
	private final String Street_x = "//*[@id=\"CUSTOMER_STREET_NAME\"]";
	private final String Floor_x = "//*[@id=\"CUSTOMER_FLOOR_NAME\"]";
	private final String Birthday_x = "//*[@id=\"operator_OPERATOR_BRITHDAY\"]";
	private final String IdenNo_x = "//*[@id=\"operator_OPERATOR_IDEN_NR\"]";
	private final String Estate_x = "//*[@id=\"CUSTOMER_ESTATE_NAME\"]";
	private final String Qa_x = "//*[@id=\"addPart\"]/div/div/div[4]/ul/li[2]/div[2]/span/span";
	private final String Qa1_x = "//*[@id=\"addressInfoTable\"]/div[1]/div/table/tbody/tr[1]";
	private final String Qb_x = "//*[@id=\"addPart\"]/div/div/div[4]/ul/li[3]/div[2]/span/span";
	private final String Qb1_x = "//*[@id=\"addressInfoTable\"]/div[1]/div/table/tbody/tr[1]";
	private final String Qc_x = "//*[@id=\"addPart\"]/div/div/div[4]/ul/li[4]/div[2]/span/span";
	private final String Qc1_x = "//*[@id=\"addressInfoTable\"]/div[1]/div/table/tbody/tr[1]";
	private final String Qd_x = "//*[@id=\"addPart\"]/div/div/div[4]/ul/li[7]/div[2]/span/span";
	private final String Qd1_x = "//*[@id=\"addressInfoTable\"]/div[1]/div/table/tbody/tr[1]";
	private final String Qf_x = "//*[@id=\"addPart\"]/div/div/div[4]/ul/li[9]/div[2]/span/span";
	private final String Qf1_x = "//*[@id=\"addressInfoTable\"]/div[1]/div/table/tbody/tr[1]";
	private final String Operator_x = "//*[@id=\"operator_OPERATOR_IDEN_TYPE_span\"]";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Xzkh(){}
	public Xzkh(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> testflow() throws Exception {
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
			pageOp.click(By.xpath(this.choiceType_x));
			comOp.waitamoment("2");
			comOp.switchpage("3");
			comOp.waitamoment("2");
			pageOp.clearinput(By.xpath(this.groupName_x));
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.groupName_x),"测试自动化新");
//choicebyindex(CusttypeSelect_x,"4")
			comOp.waitamoment("2");
			pageOp.choicebyindex(By.xpath(this.Groupfl_x),"1");
//waitamoment("2")
//choicebytext(IDType_x,"BR")
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.IDName_x),"12345625");
			pageOp.input(By.xpath(this.CustPassword_x),"123123");
			pageOp.input(By.xpath(this.HQ_x),"测试总部");
			pageOp.input(By.xpath(this.Email_x),"tanghuan3@asiainfo.com");
			comOp.waitamoment("2");
			pageOp.uploadfile(By.xpath(this.uptn_x),"c:\\upload\\cust\\qiyezhengjian.txt");
			comOp.waitamoment("2");
//填入区域信息
			pageOp.click(By.xpath(this.Qa_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Qa1_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Qb_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Qb1_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Qc_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Qc1_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Qd_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Qd1_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Qf_x));
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.Qf1_x));
			comOp.waitamoment("1");
			pageOp.input(By.xpath(this.Floor_x),"3");
			comOp.waitamoment("2");
//设置生日
//setfixdate(Birthday_x,"1993","01","06")
			pageOp.choicebyindex(By.xpath(this.Operator_x),"1");
			comOp.waitamoment("2");
			String IdenNo=dbOp.searchString("select t.iden_nr from party.CB_IDENTIFICATION t where iden_type_id='1000007' and data_status=1 and iden_nr is not null and iden_nr!='-' and iden_nr!='0' and rownum=1 and iden_nr not like '%*%' ");
			saveData = saveData +"IdenNo="+IdenNo+"|";
			resultMap.put("saveData", saveData);
			pageOp.input(By.xpath(this.IdenNo_x),IdenNo);
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.EmployeesStaff_x),"10000");
//waitamoment("2")
//choicebyindex(GroupType_x,"1")
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Submit_x));
			comOp.waitamoment("3");
			pageOp.click(By.xpath(this.groupno_x));
			comOp.waitamoment("1");
			String groupn=pageOp.gettext(By.xpath(this.groupno_x));
			saveData = saveData +"groupn="+groupn+"|";
			resultMap.put("saveData", saveData);
			comOp.waitamoment("1");
//groupno=substring(groupn,"25","36")
			String groupno=dataUtil.substring(groupn,"12","23");
			saveData = saveData +"groupno="+groupno+"|";
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


	public HashMap<String,String> testflowCheck() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String IdenNo = "";
			String groupn = "";
			String groupno = "";
			resultMap.put("message", "");
			resultMap.put("result", "正确");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			IdenNo = saveDataMap.get("IdenNo");
			groupn = saveDataMap.get("groupn");
			groupno = saveDataMap.get("groupno");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "检验步骤获取变量值报错");
			}
	try {
			resultMap  = dbCheck.checksql("1","select count(*) from  party.cb_enterprise a where a.group_id='"+groupno+"'","校验入库数据数据是否正确",resultMap);
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

