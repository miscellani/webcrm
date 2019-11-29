package cases.com;

import base.com.zl.com.ComOperate;
import base.com.zl.utils.DataUtil;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.com.zl.db.DbOperate;
import base.com.zl.db.ConfigDbOperate;
import java.util.Map;
import base.com.zl.check.DbCheck;
import base.com.zl.check.PageCheck;
import base.com.zl.selenium.OpWebDriver;
import base.com.zl.selenium.Page;
import java.io.File;

public class CustOp {
	private final String shougong_x = "//*[@id=\"editInfoButton\"]";
	private final String custname_x = "//*[@id=\"PARTY_NAME\"]";
	private final String zhengjianhao_x = "//*[@id=\"IDEN_NR\"]";
	private final String checkzhengjianhao_x = "//*[@id=\"IDEN_NR_BUTTON\"]";
	private final String oneidfive_x = "/html/body/div[38]/div/div[2]/div[2]/button";
	private final String zhengjiandiz_x = "//*[@id=\"IDEN_ADDRESS\"]";
	private final String mingzu_x = "//*[@id=\"NATION_span\"]";
	private final String iden_eff_date_x = "//*[@id=\"IDEN_EFF_DATE\"]";
	private final String iden_exp_date_x = "//*[@id=\"IDEN_EXP_DATE\"]";
	private final String birth_date_x = "//*[@id=\"BIRTH_DATE\"]";
	private final String other_info_x = "//*[@id=\"OtherInfo\"]";
	private final String linkname_x = "//*[@id=\"CONTACTS_NAME\"]";
	private final String addr_x = "//*[@id=\"ADDR_DETAIL_NAME\"]";
	private final String otherinfo_confirm_x = "//*[@id=\"CustOtherInfoPart\"]/div/div[6]/button";
	private final String customerinfo_confirm_x = "//*[@id=\"submitButton\"]";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public CustOp(){}
	public CustOp(WebDriver webDriver){
	this.webDriver = webDriver;
	}
	public CustOp(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public  void  createcust(String busi) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
PageOp PageOp = new PageOp(webDriver,caseId,null);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			HashMap resultMap  = new HashMap<>();
			resultMap.put("message", "");
			resultMap.put("result", "成功");
			pageOp.click(By.xpath(this.shougong_x));
			pageOp.input(By.xpath(this.custname_x),"autotest");
			String id="510322198404130014";
			 dbOp.updateData("update party.cb_identification t set party_id=11111111,t.iden_nr ='510322198404130000' where t.iden_nr ='"+id+"'");
			pageOp.input(By.xpath(this.zhengjianhao_x),id);
			pageOp.click(By.xpath(this.checkzhengjianhao_x));
			comOp.waitamoment("2");
			PageOp.closewarntip();
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.zhengjiandiz_x),"广东省广州市天河区亚信研发中心");
			pageOp.choicebytext(By.xpath(this.mingzu_x),"汉族");
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.iden_eff_date_x),"2000-10-21");
			pageOp.input(By.xpath(this.iden_exp_date_x),"2040-10-21");
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.other_info_x));
			pageOp.input(By.xpath(this.addr_x),"zhanglin");
			pageOp.click(By.xpath(this.otherinfo_confirm_x));
			pageOp.click(By.xpath(this.customerinfo_confirm_x));
			comOp.waitamoment("2");
			PageOp.closesucesstip();
		}


}

