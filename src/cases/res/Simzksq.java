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

public class Simzksq {
	private final String vendor_x = "//*[@id=\"condition_SUPPLIER_span\"]";
	private final String orderdate_x = "//*[@id=\"editMain\"]/div[2]/ul/li[2]/div[2]/span/span[1]/span";
	private final String transportkey_x = "//*[@id=\"condition_transportKey\"]";
	private final String otatk_x = "//*[@id=\"condition_OTA_KEY\"]";
	private final String opkey_x = "//*[@id=\"condition_opKey\"]";
	private final String quantity_x = "//*[@id=\"condition_quantity\"]";
	private final String testsim_x = "//*[@id=\"condition_testSim\"]";
	private final String imsistart_x = "//*[@id=\"condition_imsiHead_span\"]";
	private final String resspec_x = "//*[@id=\"condition_RES_SPEC_span\"]";
	private final String productcode_x = "//*[@id=\"condition_RES_SKU_ID_span\"]";
	private final String size_x = "//*[@id=\"SIZE_TYPE_span\"]";
	private final String network_x = "//*[@id=\"NETWORK_TYPE_span\"]";
	private final String tijiao_x = "//*[@id=\"Submit\"]";
	private final String queren_x = "//html/body/div[13]/div[1]/div[2]/div[2]/button[1]";
	private final String pono_x = "//*[@id=\"retPoItem\"]";
	private final String xiazai_x = "//*[@id=\"Export\"]";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public Simzksq(){}
	public Simzksq(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public HashMap<String,String> SIMzksqTextFlow(String vendor,String imsi,String resspec,String productcode) throws Exception {
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
			saveData = saveData +"vendor="+vendor+"|";
			resultMap.put("saveData", saveData);
			saveData = saveData +"imsi="+imsi+"|";
			resultMap.put("saveData", saveData);
			saveData = saveData +"resspec="+resspec+"|";
			resultMap.put("saveData", saveData);
			saveData = saveData +"productcode="+productcode+"|";
			resultMap.put("saveData", saveData);
			comOp.intomenu("SIM���ƿ�����");
			pageOp.choicebytext(By.xpath(this.vendor_x),vendor);
			comOp.waitamoment("1");
			pageOp.setdate(By.xpath(this.orderdate_x),"2019","03");
			pageOp.input(By.xpath(this.transportkey_x),"84");
			pageOp.input(By.xpath(this.otatk_x),"5");
			pageOp.input(By.xpath(this.opkey_x),"3");
//�����������
			pageOp.input(By.xpath(this.quantity_x),"1");
//testsim��ѡ��ʹ�����ͨSIM��������Ҫ��productcode
//click(testsim_x)
//���п�ѡ���һ��45412��45413
			pageOp.choicebytext(By.xpath(this.imsistart_x),imsi);
			comOp.waitamoment("1");
//RES_SPEC����ѡ�񣺺󸶷�SIM��,Ԥ����SIM��,E-SIM��
			pageOp.choicebytext(By.xpath(this.resspec_x),resspec);
			comOp.waitamoment("1");
//����res_specѡ��󸶷ѣ�productcode:SIMPLUGDA8,Ԥ����Ϊsku2,esimΪsku3�������б䶯��������
			pageOp.choicebytext(By.xpath(this.productcode_x),productcode);
			comOp.waitamoment("1");
			pageOp.choicebytext(By.xpath(this.size_x),"4ff");
			comOp.waitamoment("1");
			pageOp.choicebytext(By.xpath(this.network_x),"4G_TRIO");
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.tijiao_x));
//�ȴ�ҳ��3�룬��������ȷ����Ϣ
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.queren_x));
//�ȴ�ҳ��5�룬���ݿ��ύ��Ҫʱ��
			comOp.waitamoment("5");
			String pono=pageOp.gettext(By.xpath(this.pono_x));
			saveData = saveData +"pono="+pono+"|";
			resultMap.put("saveData", saveData);
			pageOp.click(By.xpath(this.xiazai_x));
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


	public HashMap<String,String> SIMzksqTextFlowCheck() throws Exception {
			DataUtil dataUtil = new DataUtil();
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			HashMap<String,String> resultMap  = new HashMap<String,String>();
			String savaData = "";
			HashMap<String,String> saveDataMap  = new HashMap<String,String>();
			String vendor = "";
			String imsi = "";
			String resspec = "";
			String productcode = "";
			String pono = "";
			resultMap.put("message", "");
			resultMap.put("result", "��ȷ");
			try{
			savaData = configDbOp.searchString("select savedata from web_case_current where caseid = '"+caseId+"'");
			saveDataMap = dataUtil.stringToMap(savaData);
			vendor = saveDataMap.get("vendor");
			imsi = saveDataMap.get("imsi");
			resspec = saveDataMap.get("resspec");
			productcode = saveDataMap.get("productcode");
			pono = saveDataMap.get("pono");
			}catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", "���鲽���ȡ����ֵ����");
			}
	try {
			resultMap  = dbCheck.checksql("1","select count(*) from  RES.RES_SIM_APPLY_ORDER a, RES.RES_SIM_APPLY_ORDER_DET b where a.po_no=b.apply_id and a.po_no='"+pono+"'","У��������������Ƿ���ȷ",resultMap);
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


	public HashMap<String,String> simzksqmb2(String vendor,String imsi,String resspec) throws Exception {
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
			saveData = saveData +"vendor="+vendor+"|";
			resultMap.put("saveData", saveData);
			saveData = saveData +"imsi="+imsi+"|";
			resultMap.put("saveData", saveData);
			saveData = saveData +"resspec="+resspec+"|";
			resultMap.put("saveData", saveData);
			comOp.intomenu("SIM���ƿ�����");
			pageOp.choicebytext(By.xpath(this.vendor_x),vendor);
			comOp.waitamoment("1");
			pageOp.setdate(By.xpath(this.orderdate_x),"2019","03");
			pageOp.input(By.xpath(this.transportkey_x),"84");
			pageOp.input(By.xpath(this.otatk_x),"5");
			pageOp.input(By.xpath(this.opkey_x),"3");
//�����������
			pageOp.input(By.xpath(this.quantity_x),"1");
//testsim��ѡ��ʹ�����ͨSIM��������Ҫ��productcode
			pageOp.click(By.xpath(this.testsim_x));
//���п�ѡ���һ��45412��45413
			pageOp.choicebytext(By.xpath(this.imsistart_x),imsi);
			comOp.waitamoment("1");
//RES_SPEC����ѡ�񣺺󸶷�SIM��,Ԥ����SIM��,E-SIM��
			pageOp.choicebytext(By.xpath(this.resspec_x),resspec);
			comOp.waitamoment("1");
//����res_specѡ��󸶷ѣ�productcode:SIMPLUGDA8,Ԥ����Ϊsku2,esimΪsku3�������б䶯��������
//choicebytext(productcode_x,productcode)
//waitamoment("1")
			pageOp.choicebytext(By.xpath(this.size_x),"2ff");
			comOp.waitamoment("1");
			pageOp.choicebytext(By.xpath(this.network_x),"4G_TRIO");
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.tijiao_x));
//�ȴ�ҳ��3�룬��������ȷ����Ϣ
			comOp.waitamoment("3");
			pageOp.click(By.xpath(this.queren_x));
//�ȴ�ҳ��5�룬���ݿ��ύ��Ҫʱ��
			comOp.waitamoment("5");
			String pono=pageOp.gettext(By.xpath(this.pono_x));
			saveData = saveData +"pono="+pono+"|";
			resultMap.put("saveData", saveData);
			pageOp.click(By.xpath(this.xiazai_x));
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


	public HashMap<String,String> simzksqmb2Check() throws Exception {
			return this.SIMzksqTextFlowCheck();
			}


	public HashMap<String,String> dssd() throws Exception {
			return this.SIMzksqTextFlow("Schlumberger,Suites","45412","Ԥ����SIM��","sku2");
		}


	public HashMap<String,String> dssdCheck() throws Exception {
			return this.SIMzksqTextFlowCheck();
			}


	public HashMap<String,String> sqyfxnk() throws Exception {
			return this.simzksqmb2("Schlumberger,Suites","45413","PREPAID_SIM");
		}


	public HashMap<String,String> sqyfxnkCheck() throws Exception {
			return this.SIMzksqTextFlowCheck();
			}


}

