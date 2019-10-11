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

public class MainPage {
	private final String groupLogout_x = "//*[@id=\"groupLogin\"]";
	private final String isLogin_x = "//*[@id=\"LOGIN_FORM\"]";
	private final String personFlag_x = "//*[@id=\"m_fn_ul\"]/li[1]";
	private final String groupFlag_x = "//*[@id=\"m_fn_ul\"]/li[2]";
	private final String tab_x = "//*[@id=\"tab_ct_ul\"]/li";
	private final String ele_x = "div[2]/div";
	private final String menuInput_x = "//*[@id=\"menu_search\"]";
	private final String menuSearch_x = "//*[@id=\"button_search\"]";
	private final String menulink_x = "//*[@id=\"menu_search_list\"]/li";
	private final String menuChoice_x = "/html/body/div/div/div[1]/ul/li";
//错误提示
	private final String isMenuErrTip_x = "/html/body/div/div/div[2]/div[4]/button";
	private final String isMenuTip_x = "/html/body/div[9]/div/div[2]/div[2]/button";
	private final String isPass_x = "//*[@id=\"LOGIN_VLI\"]";
	private final String noPass_x = "//*[@id=\"NO_PASSWORD_LOGIN_CHECKBOX\"]";
	private final String groupId_x = "//*[@id=\"groupQueryTypeValueInput\"]";
	private final String groupLogin_x = "//*[@id=\"groupLogin\"]/div[3]/button";
	private final String groupList_x = "//*[@id=\"groupList\"]/div[1]/div[2]/ul/li";
	private final String loginNum_x = "//*[@id=\"LOGIN_NUM\"]";
	private final String login_x = "//*[@id=\"LOGIN_BTN\"]";
	private final String  numberLoading_x = "//*[@id=\"x-wade-loading-global\"]";
//号码推荐框
	private final String saleDialogs_x = "/html/body/div";
	private final String saleDialogs1_x = "div[1]/div[1]/div[2]/div[1]";
	private final String logout_x = "//*[@id=\"LOGOUT_BTN\"]";
	private final String  groupLoginFn_x = "//*[@id=\"groupLoginRelFn\"]/ul/li";
	private final String errorPopup_x = "//div[@x-wade-uicomponent=\"messagebox\" and @class";
	private final String menuload_x = "/html/body/div/div[2]";
	public int cycleId=0;
	public String caseId;
	public String testDir;
	public WebDriver webDriver;
	public MainPage(){}
	public MainPage(WebDriver webDriver){
	this.webDriver = webDriver;
	}
	public MainPage(WebDriver webDriver,String caseId,String testDir){
	this.webDriver = webDriver;
	this.caseId = caseId;
	this.testDir = testDir;
	}

	public boolean isLogined(boolean isGroup) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
MainPage MainPage = new MainPage(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			pageOp.switchDefaults();
			MainPage.closeSaleDialog();
       if(isGroup){
       			//切到集团
			MainPage.changePG("group");
			String content=pageOp.getattrvalue(By.xpath(this.groupLogout_x),"style");
         if(content.contains("display: none;")){
       						    return true;
       }else{
       						    return false;
  }       }else{
       			//切到个人
			MainPage.changePG("person");
			String content=pageOp.getattrvalue(By.xpath(this.isLogin_x),"style");
         if(content.contains("display: none;")){
       						    return true;
       }else{
       						  return false;
  }}		}


	public  void  changePG(String type) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
       if(type.equals("person")){
       						String flagClass1=pageOp.getattrvalue(By.xpath(this.personFlag_x),"class");
         if(flagClass1.contains("on")){
       						    return;
  }			pageOp.click(By.xpath(this.personFlag_x));
			comOp.waitamoment("1");
			  return;
       }else{
       						String flagClass2=pageOp.getattrvalue(By.xpath(this.groupFlag_x),"class");
         if(flagClass2.contains("on")){
       						    return;
  }			pageOp.click(By.xpath(this.groupFlag_x));
			comOp.waitamoment("1");
}		}


	public  void clearTab() throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
MainPage MainPage = new MainPage(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			pageOp.switchDefaults();
			MainPage.closeSaleDialog();
			pageOp.modifyimplicitlwaittime(2);
			ArrayList<WebElement> list=pageOp.getelementlist(By.xpath(this.tab_x));
			pageOp.modifyimplicitlwaittime(10);
       for(int i=1;i<list.size();i++){
			WebElement listwebElementtemp=pageOp.getelebylist(list,"1");
			ArrayList<WebElement> list1=pageOp.getlistbyele(By.xpath(this.ele_x),listwebElementtemp);
           if(list1.size()==2){
       						WebElement ele=pageOp.getelebylist(list1,"1");
			pageOp.clickelement(ele);
       }else{
       						WebElement ele=pageOp.getelebylist(list1,"0");
			pageOp.clickelement(ele);
    }}		}


	public boolean intoMenuByInput(String menuName) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
MainPage MainPage = new MainPage(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			pageOp.switchDefaults();
			comOp.switchframe("1","0");
			pageOp.click(By.xpath(this.menuInput_x));
			comOp.waitamoment("2");
			pageOp.input(By.xpath(this.menuInput_x),menuName);
			comOp.waitamoment("2");
//点击放大镜目前无缓存，先修改为链接,注销的都是之前的
//click(menuSearch_x)
//修改为点击链接
			pageOp.click(By.xpath(this.menulink_x));
			comOp.waitamoment("2");
			pageOp.switchDefaults();
			comOp.switchframe("1","1");
//switchframe("1","0")
//click(menuChoice_x)
			comOp.waitamoment("2");
			MainPage.waitMenuLoading();
			try{
			pageOp.modifyimplicitlwaittime(2);
			WebElement webElement1=pageOp.displaywaitelement(By.xpath(this.isMenuErrTip_x),"2");
			pageOp.clickelement(webElement1);
			pageOp.modifyimplicitlwaittime(10);
			return false;
       }
       			catch(Exception e){
			pageOp.modifyimplicitlwaittime(10);
}//没有错误提示，确定是否有提示框
			try{
			pageOp.modifyimplicitlwaittime(2);
			WebElement webElement2=pageOp.displaywaitelement(By.xpath(this.isMenuTip_x),"2");
			pageOp.modifyimplicitlwaittime(10);
			pageOp.clickelement(webElement2);
			return true;
       }
       			catch(Exception e){
			pageOp.modifyimplicitlwaittime(10);
			return true;
}		}


	public boolean loginNumber(String billId,boolean isGroup) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
MainPage MainPage = new MainPage(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			pageOp.switchDefaults();
//关闭销售弹出框
			MainPage.closeSaleDialog();
//点击无密码登录
//styple=getattrvalue(isPass_x,"style")
       //if(!styple.contains("display")){
       			//jsclick(noPass_x)
//}			comOp.waitamoment("1");
       if(isGroup){
       						pageOp.input(By.xpath(this.groupId_x),billId);
			comOp.waitamoment("1");
			pageOp.click(By.xpath(this.groupLogin_x));
			boolean b1=pageOp.implicitlwaitelement(By.xpath(this.groupList_x),"3");
         if(!b1){
       						    return false;
  }//点击集团登录
			comOp.waitamoment("2");
			pageOp.click(By.xpath(this.groupList_x));
			comOp.waitamoment("2");
			  return true;
       }else{
       						pageOp.input(By.xpath(this.loginNum_x),billId);
			comOp.waitamoment("1");
			pageOp.dclick(By.xpath(this.login_x));
			comOp.waitamoment("1");
//等待号码加载
//this.waitNumberLoading()
}			try{
       cycleId=0;
       while( cycleId<11){
       cycleId++;
       						String content=pageOp.getattrvalue(By.xpath(this.numberLoading_x),"style");
           if(content.contains("display: none;")){
       						break;
    }			comOp.waitamoment("1");
  }       }
       			catch(Exception e){
}			comOp.waitamoment("5");
			MainPage.closeSaleDialog();
//判断是否登录成功
			boolean b2=MainPage.isLogined(isGroup);
			return b2;
		}


	public  void closeSaleDialog() throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			ArrayList<WebElement> saleDialogs=pageOp.getelementlist(By.xpath(this.saleDialogs_x));
			String param1a="x-wade-uicomponent";
			String param2a="class";
			String param3a="style";
       for(WebElement ele:saleDialogs){
			String param1=pageOp.getattrvaluebyele(ele,param1a);
			String param2=pageOp.getattrvaluebyele(ele,param2a);
			String param3=pageOp.getattrvaluebyele(ele,param3a);
       if(param1!=null){
       			            if(param1.contains("dialog")){
       			              if(param2!=null){
       			                    if(param2.contains("c_dialog")){
       			                         if(param3!=null){
       			                             if(param2.contains("display: none")){
       						pageOp.click(By.xpath(this.saleDialogs1_x));
                       }                   }              }       }     }}}		}


	public  void  logout(boolean isGroup) throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
MainPage MainPage = new MainPage(webDriver);
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			pageOp.switchDefaults();
//先切到个人登录退出登录
			MainPage.changePG("person");
//先判断是否在登陆状态
			boolean b=dataUtil.getboolean("false");
			boolean bb=MainPage.isLogined(b);
       if(bb){
       						pageOp.click(By.xpath(this.logout_x));
			comOp.waitamoment("1");
}//再切到集团登录退出登录
			MainPage.changePG("group");
//先判断是否在登陆状态
			boolean c=dataUtil.getboolean("true");
			boolean cc=MainPage.isLogined(c);
       if(cc){
       						pageOp.click(By.xpath(this.groupLoginFn_x));
			comOp.waitamoment("1");
}//判断业务切到业务登录页
       if(!isGroup){
       						MainPage.changePG("person");
}		}


	public  void closeErrorPop() throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			pageOp.switchDefaults();
			try{
			pageOp.modifyimplicitlwaittime(2);
			String styleCont=pageOp.getattrvalue(By.xpath(this.errorPopup_x),"style");
//错误提示展示关闭掉
         if(!styleCont.contains("display")){
       						pageOp.setattrvalue(By.xpath(this.errorPopup_x),"style","z-index: 6000; display: none;");
  }			pageOp.modifyimplicitlwaittime(10);
       }
       			catch(Exception e){
//没有错误提示框
			pageOp.modifyimplicitlwaittime(10);
}		}


	public  void waitMenuLoading() throws Exception {
			OpWebDriver opWebDriver = new OpWebDriver();
			DataUtil dataUtil = new DataUtil();
			ComOperate comOp = new ComOperate(webDriver);
			Page pageOp = new Page(webDriver);
			DbOperate dbOp = new DbOperate();
			ConfigDbOperate configDbOp = new ConfigDbOperate();
			DbCheck dbCheck = new DbCheck();
			PageCheck pageCheck = new PageCheck(webDriver,caseId);
			comOp.waitamoment("2");
			pageOp.switchDefaults();
       cycleId=0;
       while( cycleId<11){
       cycleId++;
       						String loadstyle=pageOp.getattrvalue(By.xpath(this.menuload_x),"style");
         if(loadstyle.contains("display: none")){
       						break;
  }			comOp.waitamoment("1");
}//暂时修改点击菜单链接少一个框架
//加载完成后页面进入菜单页面
//switchframe("1","2")
			comOp.switchframe("1","1");
		}


}

