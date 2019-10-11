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

public class LoginPage {
	private final String point_operCode_x = "//*[@id=\"userAccount\"]";
	private final String point_passWord_x = "//*[@id=\"userPwd\"]";
	private final String point_vercode_x = "//*[@id=\"imgCode\"]";
	private final String point_login_btn_x = "//*[@id=\"login\"]";
	private final String point_UIstep1_x = "//*[@id=\"UI-step1\"]/div[2]/div/div/div[2]/button[1]";
	private final String ponit_opTit_x = "//*[@id=\"staff_min\"]/div[2]/div";
//非单点登录
	private final String staffId_x = "//*[@id=\"STAFF_ID\"]";
	private final String passWord_x = "//*[@id=\"PASSWORD\"]";
	private final String login_x = "//*[@id=\"loginBtn\"]";
	private final String UIstep1_x = "//*[@id=\"UI-step1\"]/div[2]/div/div/div[2]/button[1]";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public LoginPage(){}
	public LoginPage(WebDriver webDriver){
	this.webDriver = webDriver;
	}
	public LoginPage(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public  void  login(String pointLogin,String opId,String pw) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
       if(pointLogin.equals("1")){
       			       }else{
       						comOp.waitamoment("2");
			pageOp.input(By.xpath(this.staffId_x),opId);
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.passWord_x),pw);
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.login_x));
			comOp.waitamoment("2");
//香港操作员登录后弹出操作指导框
//jsclick(UIstep1_x)
}		}


}

