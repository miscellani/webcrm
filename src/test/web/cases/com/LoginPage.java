package test.web.cases.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.com.zl.selenium.OpWebDriver;
import base.com.zl.selenium.Page;
import test.web.WebInit;

public class LoginPage extends Page {

	

	public LoginPage(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}

   //0 //*[@id="userAccount"]
	public final String operCode_x = "//*[@id=\"userAccount\"]";
	public final String passWord_x = "//*[@id=\"userPwd\"]";
	public final String vercode_x="//*[@id=\"imgCode\"]";
	public final String login_btn_x = "//*[@id=\"login\"]";

	public final String identifyCode_x = "//*[@id=\"VerifyImg\"]";
	public final String popup_x = "//*[@id=\"popup_error\"]";
	public final String popupCancel_x = "//*[@id=\"popup_error\"]/div/div[2]/p/input";

	//1
	
	public final String staffId_x = "//*[@id=\"STAFF_ID\"]";
	public final String passWord1_x = "//*[@id=\"PASSWORD\"]";
	public final String orgation_i = "LoginOrg";
	public final String station_i = "LoginSta";
	public final String loginBtn_x = "//*[@id=\"loginBtn\"]";
	//登陆主页向导提升框
	public final String UIstep1_x = "//*[@id=\"UI-step1\"]/div[2]/div/div/div[2]/button[1]"; 

	
	//操作员切换语言
	public final String opTit_x="//*[@id=\"staff_min\"]/div[2]/div";
	//更多功能 
	public final String morefunc_x="//*[@id=\"more_btn\"]";
	//切换语言 
	public final String switchyy_x="//*[@id=\"sidebtn_change_lang\"]/div[2]";
     //选择简体中文 
	public final String choiceyy_x="//*[@id=\"lang_segment_span\"]/span[2]";
    //确定语言
	public final String comfyy_x="//*[@id=\"languageDialog\"]/div/div[2]/div[2]/button[2]";
	
		
	
	
	public final String titleName="登录";

	
	public void locateWeb(){
		OpWebDriver opWebDriver = new OpWebDriver();
		opWebDriver.get(this.webDriver,WebInit.mWebUrl);
	}
	
	
	
	public void login(String opId, String pw) throws Exception {
		

		if(WebInit.pointLogin.equals("0")){
			
			//*[@id="OPER_CODE"]
            
			this.input(By.xpath(this.operCode_x), opId);
			Thread.sleep(1000);
			this.input(By.xpath(this.passWord_x), pw);
			Thread.sleep(1000);
			this.input(By.xpath(this.vercode_x),"2019");
			Thread.sleep(1000);
			this.jsclick(By.xpath(this.login_btn_x));
			//System.out.println("-----进入我的工作台");
			//Log.info("进入我的工作台");
			Thread.sleep(2000);
			
			//进入前台后的提示框
			this.jsclick(By.xpath(this.UIstep1_x));
			Thread.sleep(2000);
			
			
			//切换语言
			this.click(By.xpath(this.opTit_x));
			
			Thread.sleep(2000);
			this.click(By.xpath(this.morefunc_x));
			Thread.sleep(2000);
			this.click(By.xpath(this.switchyy_x));
			Thread.sleep(2000);
			this.click(By.xpath(this.choiceyy_x));
			Thread.sleep(2000);
			this.click(By.xpath(this.comfyy_x));
			Thread.sleep(3000);
		}else{
			//增加语言选择
			Thread.sleep(2000);
			this.click(By.xpath("//*[@id=\"LOCALE_span\"]"));

			Thread.sleep(2000);
			this.click(By.xpath("//*[@id=\"LOCALE_float\"]/div[2]/div/div/ul/li[2]/div"));
			Thread.sleep(2000);
			

			this.input(By.xpath(this.staffId_x), opId);
			Thread.sleep(1000);
			this.input(By.xpath(this.passWord1_x), pw);
			Thread.sleep(1000);
			

			
			
			this.jsclick(By.xpath(this.loginBtn_x));
			Thread.sleep(2000);
			

			//进入前台后的提示框
			this.jsclick(By.xpath(this.UIstep1_x));

		}
		
		

		
		
		
		///图片识别登录
		
		/*else{
			while (true) {
				this.inputOperatorId(opId);
				this.inputPassWord(pw);
				this.inputCode();
				this.submit();
				Thread.sleep(2000);
				String attrValue = this.webDriver.findElement(By.xpath(this.popup_x)).getAttribute("style");
				if (attrValue.equals("DISPLAY: block")) {
					// this.cancel();
					opWebDriver.reflash(this.webDriver);

				} else {
					break;
				}

			}
			this.submit();
			Thread.sleep(2000);
			//Log.info("进入我的工作台");
		}*/




	}


/*	public void loginFlow(String operatorId, String passWord) throws Exception {
		OpWebDriver opWebDriver = new OpWebDriver();
		while (true) {
			this.inputOperatorId(operatorId);
			this.inputPassWord(passWord);
			this.inputCode();
			this.submit();
			Thread.sleep(2000);
			String attrValue = this.webDriver.findElement(By.xpath(this.popup_x)).getAttribute("style");
			if (attrValue.equals("DISPLAY: block")) {
				// this.cancel();
				opWebDriver.reflash();

			} else {
				break;
			}

		}
		this.submit();
		Thread.sleep(2000);
		System.out.println("-----进入我的工作台");

	}*/

	private void inputOperCole(String operCode) {

		this.input(By.xpath(this.operCode_x), operCode);

	}


	private void inputPassWord(String passWord) {

		this.input(By.xpath(this.passWord_x), passWord);

	}

	// 获取验证码
/*	private String getIdentifyCode() throws Exception {

		this.click(By.xpath(this.identifyCode_x));
		Thread.sleep(1000);
		WebElement element = this.getElement(By.xpath(this.identifyCode_x));
		OpElement opElement = new OpElement();
		return opElement.getImageVerifyCode(element);
		//String mpath = opElement.elementShot(element);
		//return opElement.ocrCode(mpath, "eng");

	}*/




}
