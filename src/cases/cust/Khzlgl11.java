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

public class Khzlgl11 {
	private final String Qry_x = "//*[@id=\"qryBox\"]/button";
	private final String QryType_x = "//*[@id=\"GROUP_QUERY_TYPE_span\"]";
	private final String GroupCode_x = "//*[@id=\"GROUP_ID\"]";
	private final String QryBtn_x = "//*[@id=\"qryCondGroupIDBtn\"]";
	private final String Select_x = "//*[@id=\"groupRows\"]/tr/td[4]";
	private final String View_x = "//*[@id=\"openViewButton\"]";
	private final String Memberinfo_x = "//*[@id=\"UI-menu\"]/li[3]/div";
	private final String AddMem_x = "//*[@id=\"addButtonInmember\"]/span[2]";
	private final String Accessnum_x = "//*[@id=\"ACCESS_NUM\"]";
	private final String QryNum_x = "//*[@id=\"qryCondByAccessNum\"]/div/ul/li/div[2]/span/button";
	private final String Positions_x = "//*[@id=\"searchPart\"]/div[5]/ul[1]/li[1]/div[2]/input";
	private final String Description_x = "//*[@id=\"searchPart\"]/div[5]/ul[2]/li/div[2]/input";
	private final String Memrela_x = "//*[@id=\"member_MEMBER_RELA_span\"]";
	private final String Confirm_x = "//*[@id=\"submitButton\"]";
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Khzlgl11(){}
	public Khzlgl11(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> testWncyxz11() throws Exception {
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
			pageOp.click(By.xpath(this.Qry_x));
			comOp.waitamoment("2");
			pageOp.choicebyindex(By.xpath(this.QryType_x),"2");
			comOp.waitamoment("2");
			String group_id=dbOp.searchString("select t.group_id from party.cb_enterprise t where t.op_id='91110066' and rownum=1 and t.data_status=1 and t.group_status=2 order by t.create_date desc");
			saveData = saveData +"group_id="+group_id+"|";
			resultMap.put("saveData", saveData);
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.GroupCode_x),group_id);
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.QryBtn_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Select_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.View_x));
			comOp.waitamoment("2");
			comOp.switchpage("3");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Memberinfo_x));
			comOp.waitamoment("5");
			comOp.switchframebyid("basicFrame");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.AddMem_x));
			comOp.waitamoment("2");
			comOp.switchpage("4");
			comOp.waitamoment("3");
			String access_num=dbOp.searchString("select t.access_num from ins1.um_subscriber_852 t where subscriber_type=1 and subscriber_status=1 and data_status=1 and rownum =1 and access_num not in(select a.access_num from party.cb_enterprise_member a, party.cb_enterprise_member_rel b where b.group_meb_id = a.group_meb_id and a.data_status = '1' and b.data_status = '1' and b.member_kind = 0 and b.member_belong= 1)");
			saveData = saveData +"access_num="+access_num+"|";
			resultMap.put("saveData", saveData);
			pageOp.input(By.xpath(this.Accessnum_x),access_num);
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.QryNum_x));
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Positions_x),"测试");
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Description_x),"描述");
			comOp.waitamoment("2");
			pageOp.scrolltobottom(webDriver);
			pageOp.choicebyindex(By.xpath(this.Memrela_x),"0");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Confirm_x));
			} catch (Exception e) {
			// TODO: handle exception
			String errMessage = dataUtil.getTrace(e);
			//*[@id="member_MEMBER_RELA_span"]
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


	public HashMap<String,String> testWncyxz11Check() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String group_id = "";
			String access_num = "";
			resultMap.put("message", "");
			resultMap.put("result", "正确");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			group_id = saveDataMap.get("group_id");
			access_num = saveDataMap.get("access_num");
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

