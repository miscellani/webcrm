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

public class Hmdgl {
	private final String addBlackListBtn_x = "//*[@id=\"addBlackListBtn\"]";
	private final String familyName_x = "//*[@id=\"BLACK_LIST_CUSTOMER_FAMILY_NAME\"]";
	private final String firstName_x = "//*[@id=\"BLACK_LIST_CUSTOMER_FIRST_NAME\"]";
	private final String blackListType_x = "//*[@id=\"BLACK_LIST_TYPE_span\"]";
	private final String idenType_x = "//*[@id=\"BLACK_LIST_IDEN_TYPE_span\"]";
	private final String idenNr_x = "//*[@id=\"BLACK_LIST_IDEN_NR\"]";
	private final String remarkInfo_x = "//*[@id=\"BLACK_LIST_REMARK\"]";
	private final String submitBtn_x = "//*[@id=\"blackListSubmitBtn\"]";
	private final String okBtn_x = "/html/body/div[16]/div/div[2]/div[2]/button[1]";
	private final String okOpBtn_x = "/html/body/div[17]/div/div[2]/div[2]/button";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Hmdgl(){}
	public Hmdgl(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> AddBlackList() throws Exception {
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
//进入菜单
			comOp.intomenu("黑名单管理");
			comOp.waitamoment("2");
//点击新增按钮
			pageOp.click(By.xpath(this.addBlackListBtn_x));
			comOp.waitamoment("2");
//输入姓名原因等其余信息
			pageOp.input(By.xpath(this.familyName_x),"Liu");
			pageOp.input(By.xpath(this.firstName_x),"Yuan");
			pageOp.choicebyindex(By.xpath(this.blackListType_x),"2");
//choicebyindex(idenType_x,"0")
//查找输入可添加为黑名单的证件号
			String idenNr=dbOp.searchString("select iden_nr from party.cb_identification  where iden_type_id = '1000007' and data_status = 1 and iden_nr is not null and iden_nr != '-' and iden_nr != '0' and iden_nr not like '%*%'and iden_nr not in (select iden_nr from party.cm_cust_special_list where speci_list_type = 1 and iden_type = '1000007' and iden_nr is not null) and rownum=1");
			saveData = saveData +"idenNr="+idenNr+"|";
			resultMap.put("saveData", saveData);
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.idenNr_x),idenNr);
			pageOp.input(By.xpath(this.remarkInfo_x),"黑名单自动化测试新增"+idenNr);
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.submitBtn_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.okBtn_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.okOpBtn_x));
			comOp.waitamoment("10");
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


	public HashMap<String,String> AddBlackListCheck() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String idenNr = "";
			resultMap.put("message", "");
			resultMap.put("result", "正确");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			idenNr = saveDataMap.get("idenNr");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "检验步骤获取变量值报错");
			}
	try {
			resultMap  = dbCheck.checksql("1","select count(*) from  party.cm_cust_special_list t where t.speci_list_type = '1' and t.data_status = '1' and  t.iden_nr='"+idenNr+"'","校验入库数据数据是否正确",resultMap);
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

