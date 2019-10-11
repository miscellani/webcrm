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

public class Simrk {
	private final String search_x = "//*[@id=\"part-simcardSearch\"]/div[1]/div/div[2]/button";
	private final String pono_x = "//*[@id=\"PO_NO_span\"]";
	private final String ok_x = "//*[@id=\"ok\"]";
	private final String user_x = "//*[@id=\"User\"]";
//��һ��Ԫ�أ���ѡ�еڶ���Ԫ�أ���Ϊ//*[@id="simcardSearch"]/div[1]/div/table/tbody/tr[2]/td[1]
	private final String dytys_x = "//*[@id=\"simcardSearch\"]/div[1]/div/table/tbody/tr/td[1]";
	private final String shangchuan_x = "//*[@id=\"part-simcardSearch\"]/div[1]/div/div[1]/span/span[6]/form/input";
	private final String save_x = "//*[@id=\"part-simcardSearch\"]/div[1]/div/div[1]/button";
	private final String qued_x = "//html/body/div[6]/div[1]/div[2]/div[2]/button[1]";
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Simrk(){}
	public Simrk(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> simrkmb(String pono) throws Exception {
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
			resultMap.put("result", "�ɹ�");
			resultMap.put("saveData", saveData);
			try{
			saveData = saveData +"pono="+pono+"|";
			resultMap.put("saveData", saveData);
			 dbOp.delData("delete from RES.RES_SIM_CARD_ORIGIN where IMSI=454120500963163");
			 dbOp.updateData("update RES.RES_SIM_APPLY_ORDER set status='o' where po_no=1988");
			 dbOp.updateData("update RES.RES_SIM_APPLY_ORDER_DET set status='o' where APPLY_ID=1988");
			comOp.intomenu("SIM���ƿ��������");
			pageOp.click(By.xpath(this.search_x));
			comOp.waitamoment("2");
//pono����ֵ�Ǳ仯�ģ�ÿ��Ҫ��
//����ֻѡ��pono���������Ԫ�ز���ѡ��
			pageOp.choicebytext(By.xpath(this.pono_x),pono);
			comOp.waitamoment("2");
//��user���ÿմ�
			pageOp.input(By.xpath(this.user_x),"");
			pageOp.click(By.xpath(this.ok_x));
			pageOp.click(By.xpath(this.dytys_x));
			comOp.waitamoment("2");
			pageOp.uploadfile(By.xpath(this.shangchuan_x),"C:\\upload\\res\\PTC1988_822.txt");
			comOp.waitamoment("5");
			pageOp.click(By.xpath(this.save_x));
//�ȴ�2�룬ҳ�浯��ȷ����Ϣ
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.qued_x));
			} catch (Exception e) {
			// TODO: handle exception
			String errMessage = dataUtil.getTrace(e);
			String message  =(String)resultMap.get("message");
			message = message+errMessage+"\n";
			resultMap.put("result", "ʧ��");
			resultMap.put("message", message);
			String screenpath = testDir + caseId+File.separator +"ִ�в��������쳣 "+ ".png";
			opWebDriver.screenShot(webDriver,screenpath);
			resultMap.put("exception", e);
			}finally{
			resultMap.put("saveData", saveData);
			return resultMap;
			}
		}


	public HashMap<String,String> simrkmbCheck() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String pono = "";
			resultMap.put("message", "");
			resultMap.put("result", "��ȷ");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			pono = saveDataMap.get("pono");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "���鲽���ȡ����ֵ����");
			}
	try {
			resultMap  = dbCheck.checksql("1","select count(*) from  RES.RES_SIM_APPLY_ORDER a, RES.RES_SIM_APPLY_ORDER_DET b where a.po_no=b.apply_id and a.status=b.status and a.status='c' and a.po_no='"+pono+"'","У���Ƿ����ɹ��Ƿ�������뵥״̬",resultMap);
			resultMap  = dbCheck.checksql("1","SELECT count(*) FROM RES.RES_SIM_CARD_ORIGIN where IMSI='454120500963163' and res_state=1","У����������Ƿ�����",resultMap);
			} catch (Exception e) {
			// TODO: handle exception
			String errMessage = dataUtil.getTrace(e);
			String message  =resultMap.get("message");
			message = message+errMessage+"\n";
			resultMap.put("result", "����");
			resultMap.put("message", message);
			}finally{
			return resultMap;
			}
			}


}

