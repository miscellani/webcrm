package test.web.cases.com;


import java.util.ArrayList;
import base.com.zl.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class MMainPage extends Page{
	
	public MMainPage(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}

	
	
	
    public final String titleName="客户关系管理系统CRM";
    
    
    //-----------------------------------------------登陆号码栏
    //-----------------------------------------------登陆号码栏
   
    //无密码登陆操作元素 //*[@id="LOGIN_VLI"] 
    public final String nonePass_x ="//*[@id=\"LOGIN_VLI\"]";
    
    //是否已经勾选无密码框
    public final String isPass ="//*[@id=\"LOGIN_VLI\"]";
    //点击无密码登陆框
    public final String noPass_x = "//*[@id=\"NO_PASSWORD_LOGIN_CHECKBOX\"]";
    //输入号码文本框
    public final String loginNum_x = "//*[@id=\"LOGIN_NUM\"]";
    //登陆  
    public final String login_x = "//*[@id=\"login\"]/div[3]";   
    //个人注销
    public final String logout_x = "//*[@id=\"logoutButton\"]";
    //判断登陆状态
    public final String isLogin_x = "//*[@id=\"login\"]";
    //判断是否登陆成功
    public final String successLogin_c ="c_msg-phone-v"; 
    //当前登录号码
	public final String loginNumber_x="//*[@id=\"S_ACCESS_NUM\"]";

	
	//personFn 个人栏
	public final String personFn_x="//*[@id=\"personFn\"]";	
	//groupFn 集团栏
	public final String groupFn_x="//*[@id=\"groupFn\"]";

	//*[@id="groupLogoutFn"] 展示为未登陆状态
	public final String groupLogout_x="//*[@id=\"groupLogoutFn\"]";
	
	
	//个人tab标识 
	public final String personFlag_x="//*[@id=\"personFn\"]";
	//集团tab标识
	public final String groupFlag_x="//*[@id=\"groupFn\"]";
	
	//集团是否有list
	public final String groupList_x="//*[@id=\"groupList\"]/div[1]/div[2]/ul/li";
	//集团注销控件
	public final String  groupLoginFn_x = "//*[@id=\"groupLoginFn\"]/ul/li[1]";
	//集团编码输入框
	public final String groupId_x = "//*[@id=\"groupQueryTypeValueInput\"]";
	//集团编号查询按钮
	public final String groupSearch_x="//*[@id=\"groupLogin\"]/div[3]/button[2]";
	
/*    private void setNonePassLogin() throws Exception{
        this.setattrvalue(By.xpath(this.nonePass_x), "style", "display: none;");
        Thread.sleep(1000);
    }*/
/*    private void clickNoPassLogin() throws Exception{
    	
    	String styple =this.getattrvalue(By.xpath(isPass),"style");
    	if(!styple.contains("display")){
        	this.jsclick(By.xpath(this.noPass_x));

    	}
        Thread.sleep(1000);
    }*/
    private void inputBillId(String content) throws Exception{
    	this.input(By.xpath(this.loginNum_x), content);
        Thread.sleep(1000);
    }
    private void login() throws Exception{
      //  OpDevice opDevice = new OpDevice(this.webDriver);
     //   WebElement webElement = this.webDriver.findElement(By.xpath(this.login_x));
       // opDevice.focalElement(webElement, "500");
       // opDevice.mouseSuspend(this.webDriver.findElement(By.xpath(this.login_x)), "500");
      //  opDevice.mouseClick();
      //  opDevice.mouseClickElement(this.webDriver.findElement(By.xpath(this.login_x)), "500");
    	//this.jsClick(By.xpath(this.login_x));
    	this.dclick(By.xpath(this.login_x));
        Thread.sleep(1000);
    }
    
    //**************1登陆号码操作
    public boolean loginNumber(String billId,boolean isGroup) throws Exception{
    	this.switchDefaults();
    	//新增
        this.closeSaleDialog();
    	
    	//this.clickNoPassLogin();
    	//点击无密码登录
    	String styple =this.getattrvalue(By.xpath(isPass),"style");
    	if(!styple.contains("display")){
        	this.jsclick(By.xpath(this.noPass_x));

    	}
        Thread.sleep(1000);
    	
    	
    	if(isGroup){
    		
    		this.input(By.xpath(groupId_x), billId);
    		Thread.sleep(1000);
    		this.click(By.xpath(groupSearch_x));
	
    		//等待右边list 是否有值
    		boolean b = implicitlwaitelement(By.xpath(groupList_x), "3");
    		if(!b){
    			return false;
    		}
    		
    		//点击集团登陆
    		Thread.sleep(2000);
    		this.click(By.xpath(groupList_x));
    		Thread.sleep(2000);
    		
    		//集团号码登陆后是否有需要清理的步骤？
    		
    		
    		return true;
    		
    		
    		

    	}else{
    		
    	
    	
    	this.inputBillId(billId);
    	this.login();
    	
    	
    	//等待号码加载
    	//this.waitNumberLoading();


    	int i =0;
    	String content="";
    	try {
        	while(i<21){
        		i++;
        		content =this.getattrvalue(By.xpath(this.numberLoading_x), "style");
        		if(content.contains("display: none;")){
        			break;
        		}  
        		Thread.sleep(1000);
        	}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	

    	
    
    	
    	
    	
    	
    	
    	
        Thread.sleep(5000);
        this.closeSaleDialog();
    	}
    	
        //判断是否登陆成功
        boolean b = this.isLogined(isGroup);
        return b;
    }
    //登出号码 ,增减集团和个人判断
    
    
    //**************2.退出登录
    public void logout(boolean isGroup) throws InterruptedException{
    	this.switchDefaults();
    	//新增
        this.closeSaleDialog();
   	
        	//先切到个人登录退出登录        	
    		this.changePG("person");  		
			//先判断是否在登陆状态
			if(this.isLogined(false)){
		    	this.click(By.xpath(this.logout_x));
	            Thread.sleep(1000);
			}
		
        	//再切到集团登录退出登录        	
    		this.changePG("group");  		
			//先判断是否在登陆状态
			if(this.isLogined(true)){
		    	this.click(By.xpath(this.groupLoginFn_x));
	            Thread.sleep(1000);

			}
		
           //判断业务切到业务登录页
			if(!isGroup){
				this.changePG("person");  
			}
		
        	
        }
        
        
        

        
        
        
        
        
        
        

    
    
    //**************3.判断是否登陆状态  增加是否集团
    public boolean isLogined(boolean isGroup) throws InterruptedException{
    	this.switchDefaults();
    	//新增
        this.closeSaleDialog();
        
    	boolean t =false;
    	if(isGroup){
    		//切到集团
    	    changePG("group");
            String content = this.getattrvalue(By.xpath(this.groupLogout_x), "style"); 
            if(content.contains("display: none;")){
            	t=true;
            	
            }
    	}else{
    	 		
        //切到个人   		
    	    changePG("person");

        String content = this.getattrvalue(By.xpath(this.isLogin_x), "style");
        if(content.contains("display: none;")){
        	t=true;
        	
        }
    	}
        return t;   	
    }
    
    /**************4.
     * 切换到集团或者个人 person group
     * @throws InterruptedException 
     */
    public void changePG(String type) throws InterruptedException{
    	
    	

    	
    	String flagClass="";

    	if(type.equals("person")){
    		flagClass= getattrvalue(By.xpath(personFlag_x), "class");

     		if(flagClass.contains("on")){
    			return;
    		}
    		
    		
    	    this.click(By.xpath(personFn_x));
    	    Thread.sleep(1000);

               return; 
    	}else{
    		
    		flagClass= getattrvalue(By.xpath(groupFlag_x), "class");

     		if(flagClass.contains("on")){
    			return;
    		}
    	    this.click(By.xpath(groupFn_x)); 
    	    Thread.sleep(1000);
    	}



    }
    
    
    
    //关闭登陆成功的提示页面,一正五号这种
/*    private void closeLoginedTip(){
    	
    	this.modifyimplicitlwaitTime(2);
    	try{
        	WebElement webElement = this.getelement(By.className(this.successLogin_c));
        	String content = webElement.getAttribute("style");
        	if(!content.contains("display: none;")){

        		//登陆成功提示是在第三个div,登陆不成功提示是在第二个，所以统一取最后一个
            	ArrayList<WebElement>	list =(ArrayList<WebElement>) webElement.findElements(By.xpath("div/div[2]/div"));
            	list.get(list.size()-1).findElement(By.xpath("button")).click();
        	}
    	}catch (Exception e) {
			// TODO: handle exception
    		//抛错代表提示页面没有，不用处理
    		System.out.println("登陆后无提示，无需处理");
		}finally {
	    	this.modifyimplicitlwaitTime(10);

		}

    	
    }*/
    
    
	//获取当前登录的号码
/*	private String getCurrentNumber(){
		return this.gettext(By.xpath(this.loginNumber_x));
		
	}*/
	
	
	
	
	
	
    
    //-----------------------------------------------table栏 
    //-----------------------------------------------table栏  太慢
    public final String tab_x="//*[@id=\"tab_ct_ul\"]/li"; 
    
    //*******************5.清除菜单tab
    public  void clearTab(){
    	this.switchDefaults();
    	//新增
        this.closeSaleDialog();
    	
    	
        this.modifyimplicitlwaittime(2);
    	ArrayList<WebElement> list =this.getelementlist(By.xpath(this.tab_x));
        this.modifyimplicitlwaittime(10);
    	int s=list.size();
    	for(int i=0;i<s;i++){
    		
    		WebElement listwebElementtemp = (WebElement) list.get(s-1-i);
    		ArrayList<WebElement> listwebElement = (ArrayList<WebElement>) listwebElementtemp.findElements(By.xpath("div[2]/div"));
    		
    		if( listwebElement.size()==2){
    			WebElement webElement = listwebElement.get(1);
        		webElement.click();

    		}else{
    			
    			WebElement webElement = listwebElement.get(0);

        		webElement.click();

    			
    		}
    	}
    }
    
 
    
    
    
    
    
    //-----------------------------------------------菜单输入栏
    //-----------------------------------------------菜单输入栏
    public final String menuInput_x = "//*[@id=\"menu_search\"]";
    public final String menuSearch_x ="//*[@id=\"button_search\"]";
    public final String menuChoice_x ="/html/body/div/div/div[1]/ul/li";
    //菜单进入是否成功
    //错误提示 
    public final String isMenuErrTip_x="/html/body/div/div/div[2]/div[4]/button";
    //提示信息
    public final String isMenuTip_x="/html/body/div[9]/div/div[2]/div[2]/button";
    private void inputMenu(String menuName){
    	this.input(By.xpath(this.menuInput_x), menuName);
    }
    private void searchMenu() throws InterruptedException{
    	this.click(By.xpath(this.menuSearch_x));

    }

/*    private void choiceMenus() throws Exception{
    	this.switchDeafults();
    	this.switchFrame("1","1");
    	this.switchFrame("1","0");
    	this.click(By.xpath(this.menuChoice_x));
    }*/ 
    
/*    //***********************切到菜单frame
	public void switchToMenu() throws Exception{
		this.switchDeafults();
		
    	ArrayList<WebElement> list =this.getelementlist(By.xpath(this.tab_x));
        int num = list.size();
        this.switchFrame("1", Integer.toString(num));
		this.switchFrame("1", "2");
	}*/
	


	
	
	
    //*************6.输入菜单查询进入
    public boolean  intoMenuByInput(String menuName) throws Exception{
    	
        this.switchDefaults();
        this.switchFrame("1", "0");
       // this.switchFrame(1, 2);
    	this.inputMenu(menuName);
    	this.searchMenu();
        Thread.sleep(2000);
       // this.choiceMenu();
        //选择菜单
    	this.switchDefaults();
    	this.switchFrame("1","1");
    	this.switchFrame("1","0");
    	this.click(By.xpath(this.menuChoice_x));
        //不等待会来不及加载frame
        Thread.sleep(2000);
        this.waitMenuLoading();
    	//this.switchdeafults();
    	//加载完成后页面就在这个层级
        //判断是否进入菜单成功，加载完成后两秒2
        try{
        	this.modifyimplicitlwaittime(2);
            WebElement webElement= this.displaywaitelement(By.xpath(this.isMenuErrTip_x), "2");
        	//this.implicitlwaitelement(By.xpath(this.isMenuErrTip_x), "2");
            System.out.println("进入菜单失败");
            //点击关闭
/*            if(bceCheck){
                return false ;

            }*/
            webElement.click();
        	this.modifyimplicitlwaittime(10);

            return false ;
            

        }catch (Exception e) {
			// TODO: handle exception
        	this.modifyimplicitlwaittime(10);


		}
        System.out.println("没有错误提示，确定是否有提示框");

        try{
        	this.modifyimplicitlwaittime(2);
             WebElement webElement= this.displaywaitelement(By.xpath(this.isMenuTip_x), "2");
        	//this.implicitlwaitelement(By.xpath(this.isMenuTip_x), "2");

            System.out.println("存在进菜单提示框");
            //点击关闭
           // webElement.click();
            
        	this.modifyimplicitlwaittime(10);

            this.click(By.xpath(this.isMenuTip_x));
            return true ;
            

        }catch (Exception e) {
			// TODO: handle exception
            System.out.println("没有进菜单提示框");
        	this.modifyimplicitlwaittime(10);

            return true ;

		}
        
    }

    
    
    
    
    
    
    
    
    //-----------------------------------------------页面提示框类
    //-----------------------------------------------页面提示框类
    //号码登陆页面加载提示 
    public final String numberLoading_x="//*[@id=\"x-wade-loading-global\"]";
     
    //登陆号码推荐框
    public final String saleDialog_x="/html/body/div[25]";
    public final String saleDialogs_x="/html/body/div";
    //登陆号码推荐关闭
    public final String cancelSale_x="/html/body/div[25]/div[1]/div[1]/div[2]/div[1]";
    //菜单加载页面提示
    public final String navLoading_x="//*[@id=\"navframe_loading_156\"]";

    
/*    //.等待页面加载
    private void waitNumberLoading() throws Exception{
    	int i =0;
    	String content="";
    	try {
        	while(i<21){
        		i++;
        		content =this.getattrvalue(By.xpath(this.numberLoading_x), "style");
        		if(content.contains("display: none;")){
        			break;
        		}  
        		Thread.sleep(1000);
        	}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	

    	
    }*/
    //******************7.关闭销售栏
    private void closeSaleDialog(){
    	

    	
    	ArrayList<WebElement> webLists =  this.getelementlist(By.xpath(this.saleDialogs_x));
    	
    	for (WebElement ele:webLists){
    		
    		//dialog 
    		String param1 = ele.getAttribute("x-wade-uicomponent");
    		//c_dialog
    		String param2 = ele.getAttribute("class");
    		//display: none;
    		String param3 = ele.getAttribute("style");
    		if( (param1!=null)  && (param1.contains("dialog")) && ( (param2!=null) && param2.contains("c_dialog"))&&((param3!=null)&& (!param3.contains("display: none")) )){
        		WebElement divEle = ele.findElement(By.xpath("div[1]/div[1]/div[2]/div[1]"));
                             divEle.click();                             
    		}
    	}
	
    	
    }
  
    
    

	public final String errorPopup_x = "//div[@x-wade-uicomponent=\"messagebox\" and @class=\"c_msg c_msg-h c_msg-phone-v c_msg-popup c_msg-error\"]";
	//public final String errorPopup_x = "//div[@id=\"CARD_TYPE_CODE_float\" and @class=\"c_float\"]";

   // public final String errorPopupCancel_x = errorPopup_x+"/div/div[2]/p/input";
	//*****************8关闭错误提示框，使用时注意Frame设置,注意查看是那种弹出框
	public void closeErrorPop() throws InterruptedException {
		MMainPage mainPage = new MMainPage(this.webDriver);        

    	this.switchDefaults();
		
        try{
        	this.modifyimplicitlwaittime(2);
             WebElement webElement=this.webDriver.findElement(By.xpath(mainPage.errorPopup_x));
             String styleCont = webElement.getAttribute("style");
             //错误提示展示关闭掉
             if(!styleCont.contains("display")){
                 System.out.println("存在错误提示框");
                  this.setattrvalue(By.xpath(mainPage.errorPopup_x), "style", "z-index: 6000; display: none;");
                 //webElement.findElement(By.xpath("div/div[2]/div[2]/button")).click();
             }
             
             
            
        	this.modifyimplicitlwaittime(10);

            

        }catch (Exception e) {
			// TODO: handle exception
            System.out.println("没有错误提示框");
        	this.modifyimplicitlwaittime(10);


		}
        
        
        
        
	}
	
	
	
	
	
	
    //*******************9  有点慢，貌似在菜单ifame差不到iframe等了10秒  修改方案，加载的时候页面在最外面，获取展示属性值是否为不展示，消失后切入菜单页
    private void waitMenuLoading() throws Exception{
       	Thread.sleep(2*1000);
    	int i =0;
    	//String content="";
    	this.switchDefaults();
    	String menuLoad = "/html/body/div/div[2]";

    	while(i<11){
    		i++;
               String loadStyle=this.getattrvalue(By.xpath(menuLoad), "style");

        		if(loadStyle.contains("display: none;")){
        			break;
   
        		}
    		Thread.sleep(1000);
    	}
    	
    	//加载完成后页面进入菜单页面
    	this.switchFrame("1", "2");
    }
    
    
    
    
    
    
    
    
    
    
    
    
	public final String head1_x ="/html/body/div[1]/div/div/div/div[2]/div/ul/li[1]/a/span[2]";	
	public final String number_i = "phone_id";

	
	public final String messagePopup_x = "//*[@id=\"popup_message\"]";
	public final String messagePopupCancel_x = "//*[@id=\"popup_message\"]/div/div[2]/p/input";
	public final String messagePopContent_x=messagePopup_x+"/div/div[2]/h3";
	
	public final String marketPopup_x = "//*[@id=\"popup_marketTips\"]";
	public final String marketPopupCancel_x = "//*[@id=\"popup_marketTips\"]/div/div[2]/img";
	
	
	public final String appComfirom_x = "";
	public final String loadPopup_x = "//*[@id=\"popup_loading\"]";	
	public final String logoutUser_x = "//*[@id=\"form_logout_user_btn\"]";

	public final String menuResult_x = "//*[@id=\"menuResultDiv\"]/div[1]/div[1]/ul[1]/table/tbody/tr/td[2]/h3/a";
	public final String businessTitle_x = "//*[@id=\"busimain\"]/div[1]/h3";
	public final String bcePre_x = "//*[@id=\"data_msg_id\"]/div[2]/h3";
	public final String bcePreContent_x = "//*[@id=\"data_msg_id\"]/div[2]/p";		
	public final String orderStatus_x = "//*[@id=\"submitSucDiv\"]/div[2]/div[2]/div[1]/div[1]";
	////*[@id="flowId"]
	//                              
	public final String p_orderId_x= "//*[@id=\"submitSucDiv\"]/div[2]/div/div[2]/div[2]/span[2]";
	//public final String g_orderId_x="//*[@id=\"flowId\"]"; 
	public final String g_orderId_x= "//*[@id=\"submitSucDiv\"]/div[2]/div/div[2]/div[2]/span[2]"; 
	                                 //*[@id="submitSucDiv"]/   div[2]/div/div[2]/div[2]/span[2]
	//子框架主页标签                                                     
	public final String biaoQian_x="//*[@id=\"busimain\"]/div/a[2]";
	// 提交二次确认框
	public final String comFirm_x = "//*[@id=\"secondCheck0\"]/div/div[2]/p/input[2]";
	//frame页签
	public final String searchtab_x="/html/body/div[4]/div[1]/div[3]/div/ul/li";
	
	
	
	//*********************10.关闭message提示框,使用时注意Frame设置,注意查看是那种弹出框  ,******************暂时未使用
	public void closeMessagePop() throws InterruptedException {
		
		MMainPage mainPage = new MMainPage(this.webDriver);
		String attrValue = this.webDriver.findElement(By.xpath(mainPage.messagePopup_x)).getAttribute("style");
		if (attrValue.contains("block")) {
			mainPage.click(By.xpath(mainPage.messagePopupCancel_x));
		}	
	}
	
	
/*    //获取订单状态
    public String getOrderStatus() throws InterruptedException{  	
    	return this.getelement(By.xpath(this.orderStatus_x)).getText();
    	
    }*/
/*    //获取BCE规则内容
	public String getBceRuleContent(){
		
		String string="";
		try {
			 string=this.gettext(By.xpath(this.bcePreContent_x));
		} catch (Exception e) {
			// TODO: handle exception
			return string;
		}
		
		return string;
		
	}
	*/


/*	*//**
	 * 判断当前是否在登录状态
	 * @param s 判断时间s
	 * @return
	 *//*
	public boolean isLogined(String s){	    
		MMainPage pMainPage = new MMainPage(webDriver);
	     //判断是否登录状态
		return this.implicitlwaitelement(By.xpath(pMainPage.logoutUser_x), s);  
	
	}
	*/

	
	

}
