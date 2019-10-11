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

public class Hmxf {
	private final String Search_x = "//*[@id=\"part-distribute2Post\"]/div[1]/div/div[2]/button/span[1]";
	private final String NDC_x = "//*[@id=\"NDC_span\"]";
	private final String range_x = "//*[@id=\"RANGE\"]";
	private final String from_x = "//*[@id=\"FROM\"]";
	private final String to_x = "//*[@id=\"TO\"]";
	private final String spec_x = "//*[@id=\"RES_SPEC_ID_span\"]";
	private final String ok_x = "//*[@id=\"queryForm\"]/div[2]/div/div[3]/button";
	private final String g_x = "//*[@id=\"check-distribute2Post\"]";
	private final String fa_x = "//*[@id=\"distributeBtn-distribute2Post\"]";
	private final String deal_x = "//*[@id=\"POOL_ID_span\"]/span";
	private final String bei_x = "//*[@id=\"REMARKS\"]";
	private final String ojk_x = "//*[@id=\"submitForm\"]/div[2]/div/div[3]/button";
	private final String ojbk_x = "/html/body/div[13]/div[1]/div[2]/div[2]/div[2]/button";
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Hmxf(){}
	public Hmxf(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> hmxfcase() throws Exception {
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
			String accessnum=comOp.getnumberbykey("p_phone_state,1");
			saveData = saveData +"accessnum="+accessnum+"|";
			resultMap.put("saveData", saveData);
			comOp.intomenu("号码下发");
			pageOp.click(By.xpath(this.Search_x));
			comOp.waitamoment("2");
//选择号头
			String n=dataUtil.substring(accessnum,"1","1");
			saveData = saveData +"n="+n+"|";
			resultMap.put("saveData", saveData);
			pageOp.choicebytext(By.xpath(this.NDC_x),n);
			comOp.waitamoment("2");
//选择中间号
			String m=dataUtil.substring(accessnum,"2","3");
			saveData = saveData +"m="+m+"|";
			resultMap.put("saveData", saveData);
			pageOp.input(By.xpath(this.range_x),m);
			comOp.waitamoment("2");
//起始段
			String b=dataUtil.substring(accessnum,"4","8");
			saveData = saveData +"b="+b+"|";
			resultMap.put("saveData", saveData);
			pageOp.input(By.xpath(this.from_x),b);
			comOp.waitamoment("2");
//结束段
			String c=dataUtil.substring(accessnum,"4","8");
			saveData = saveData +"c="+c+"|";
			resultMap.put("saveData", saveData);
			pageOp.input(By.xpath(this.to_x),c);
			comOp.waitamoment("2");
//资源规格选择移动网络号码物联网号码固网号码PAIR号码
			pageOp.input(By.xpath(this.spec_x),"移动网络号码");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.ok_x));
			comOp.waitamoment("2");
//全选
			pageOp.click(By.xpath(this.g_x));
			comOp.waitamoment("2");
//下发
			pageOp.click(By.xpath(this.fa_x));
//选择号码池
			pageOp.choicebytext(By.xpath(this.deal_x),"亚信");
//备注
			pageOp.input(By.xpath(this.bei_x),"ces");
//点击提交
			pageOp.click(By.xpath(this.ojk_x));
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


	public HashMap<String,String> hmxfcaseCheck() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String accessnum = "";
			String n = "";
			String m = "";
			String b = "";
			String c = "";
			resultMap.put("message", "");
			resultMap.put("result", "正确");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			accessnum = saveDataMap.get("accessnum");
			n = saveDataMap.get("n");
			m = saveDataMap.get("m");
			b = saveDataMap.get("b");
			c = saveDataMap.get("c");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "检验步骤获取变量值报错");
			}
	try {
			resultMap  = dbCheck.checksql("1"," select count(*) from res.res_postpaid_phone_num t where t.res_state='2' and misndn='"+accessnum+"'","ces",resultMap);
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

