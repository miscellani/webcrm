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

public class Jtywhfxecdel11 {
	private final String Choice_x = "//*[@id=\"mainOfferSelect\"]/div[2]/ul/li";
	private final String login_group_x = "//*[@id=\"groupFn\"]";
	private final String M2m_x = "//*[@id=\"90012\"]";
	private final String Button_x = "//*[@id=\"cata_100870_null\"]/div[2]/button";
	private final String keyWords_x = "//*[@id=\"keyWords\"]";
	private final String Qry_x = "//*[@id=\"searchButton\"]";
	private final String Cata_x = "/html/body/div[4]/div/div/div[1]/div[1]/div[3]/div/div[1]/ul/li[1]/div[2]/button[2]/span[2]";
	private final String Cancel_reason_x = "//*[@id=\"cond_REMOVE_REASON_span\"]";
	private final String Cancel_x = "//*[@id=\"DelSubmit\"]/button[1]";
	private final String Group_id_x = "//*[@id=\"groupQueryTypeValueInput\"]";
	private final String Query_x = "//*[@id=\"groupLogin\"]/div[3]/button[2]";
	private final String Select_group_x = "//*[@id=\"groupList\"]/div[1]/div[2]/ul/li/div[1]";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Jtywhfxecdel11(){}
	public Jtywhfxecdel11(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> Jtywecdel03() throws Exception {
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
			String group_id=dbOp.searchString("select t.group_id from ins1.um_offer_852 a,party.cb_enterprise t ,party.cm_customer b where a.offer_id=100955and a.cust_id=b.cust_id and t.orga_enterprise_id=b.party_id and a.data_status=1 and t.expire_date>sysdate and not exists (select *from ins1.um_subscriber_rel_852 c where c.subscriber_ins_id=a.subscriber_ins_id and c.data_status=1 and c.expire_date>sysdate)and not  exists (select *from  ord.om_line f  where  f.subscriber_ins_id=a.subscriber_ins_id)");
			saveData = saveData +"group_id="+group_id+"|";
			resultMap.put("saveData", saveData);
			pageOp.click(By.xpath(this.login_group_x));
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.Group_id_x),group_id);
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Query_x));
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Select_group_x));
			comOp.waitamoment("5");
			comOp.intomenu("集团业务受理");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Choice_x));
//选择和飞信商品
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.M2m_x));
			comOp.waitamoment("2");
//查询和飞信套餐
			pageOp.input(By.xpath(this.keyWords_x),"RCS_Fee");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Qry_x));
			comOp.waitamoment("5");
			pageOp.click(By.xpath(this.Cata_x));
			comOp.waitamoment("2");
//设置主界面
			pageOp.choicebyindex(By.xpath(this.Cancel_reason_x),"1");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.Cancel_x));
			comOp.waitamoment("2");
//获取订单
//waitamoment("30")
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


	public HashMap<String,String> Jtywecdel03Check() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String group_id = "";
			String orderId = "";
			resultMap.put("message", "");
			resultMap.put("result", "正确");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			group_id = saveDataMap.get("group_id");
			orderId = saveDataMap.get("orderId");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "检验步骤获取变量值报错");
			}
	try {
			resultMap  = dbCheck.checksql("1","select count(*) from  ord.om_order_f_201905 t where t.order_id='"+orderId+"'","校验入库数据数据是否正确",resultMap);
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

