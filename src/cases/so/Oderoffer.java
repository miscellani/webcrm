package cases.so;

import base.com.zl.com.ComOperate;
import cases.com.PublicOp;
import cases.com.PublicOp;
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

public class Oderoffer {
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Oderoffer(){}
	public Oderoffer(WebDriver webDriver){
	this.webDriver = webDriver;
	}
	public Oderoffer(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> Oderoffertow() throws Exception {
			String saveData = "";
			OpWebDriver opWebDriver = new OpWebDriver();
PublicOp PublicOp = new PublicOp(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			HashMap resultMap  = new HashMap<>();
			resultMap.put("message", "");
			resultMap.put("result", "�ɹ�");
			resultMap.put("saveData", saveData);
			try{
			String number=PublicOp.logintomenu("p_normal,1","��Ʒ���� ");
			saveData = saveData +"number="+number+"|";
			resultMap.put("saveData", saveData);
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


	public HashMap<String,String> OderoffertowCheck() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String number = "";
			resultMap.put("message", "");
			resultMap.put("result", "��ȷ");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			number = saveDataMap.get("number");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "���鲽���ȡ����ֵ����");
			}
	try {
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


	public HashMap<String,String> addoffer() throws Exception {
			String saveData = "";
			OpWebDriver opWebDriver = new OpWebDriver();
            PublicOp PublicOp = new PublicOp(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			HashMap resultMap  = new HashMap<>();
			resultMap.put("message", "");
			resultMap.put("result", "�ɹ�");
			resultMap.put("saveData", saveData);
			try{
			String number=PublicOp.logintomenu("p_normal,1","��Ʒ���� ");
			saveData = saveData +"number="+number+"|";
			resultMap.put("saveData", saveData);
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


	public HashMap<String,String> addofferCheck() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String number = "";
			resultMap.put("message", "");
			resultMap.put("result", "��ȷ");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			number = saveDataMap.get("number");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "���鲽���ȡ����ֵ����");
			}
	try {
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

