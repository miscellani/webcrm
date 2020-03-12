package base.com.zl.com;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.com.zl.check.PageCheck;
import base.com.zl.db.DbOperate;
import base.com.zl.log.Log;
import base.com.zl.selenium.OpWebDriver;
import base.com.zl.selenium.Page;
import base.com.zl.utils.DataUtil;
import test.config.dao.WebConfigDao;
import test.web.cases.com.MMainPage;

public class ComOperate {
	
	WebDriver webDriver;
	String  mainCaseId;
	public ComOperate() {
	}
	public ComOperate(WebDriver webDriver) {
		this.webDriver =webDriver;
		// TODO Auto-generated constructor stub
	}

	public ComOperate(WebDriver webDriver,String mainCaseId) {
		this.webDriver =webDriver;
		this.mainCaseId=mainCaseId;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 选择套餐组件
	 * @param orderid
	 * @throws Exception 
	 */
	public void choiceretplan(String offerid) throws Exception{
		
		//输入，查询，进入详情
		Page page= new Page(webDriver);
		this.switchframebyid("RatePlanFrame");
		page.input(By.xpath("//*[@id=\"searchOfferkeyWord\"]"), offerid);
		page.click(By.xpath("//*[@id=\"searchOffersearchButton\"]"));
		Thread.sleep(4000);
		page.click(By.xpath("//*[@id=\"offerId_"+offerid+"\"]"));
		Thread.sleep(3000);	
		
		this.switchframebyid("iframe1");

		try {
	        this.clearconfiguredvas();

		} catch (Exception e) {
			// TODO: handle exception
			Log.info("没有商品");
		}
	    page.click(By.xpath("//*[@id=\"OFFER_BUTTON_SUBMIT\"]"));
		Thread.sleep(2000);	
		page.click(By.xpath("/html/body/div[11]/div[1]/div[2]/div[2]/button"));
		Thread.sleep(2000);			
        this.switchframetomenu();
	
	}

	/**
	 * 取消勾选必填属性的VAS
	 */
	public void clearconfiguredvas(){ 
		Page page= new Page(webDriver);                                     
		this.webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS); 
		ArrayList<WebElement> list = page.getelementlist(By.xpath("//*[@id=\"scrollBar\"]/div[2]/div/div/div[6]/ul[1]/li"));
	    for(WebElement el: list){
		//	ArrayList<WebElement> lists = (ArrayList<WebElement>) el.findElements(By.xpath("button"));
			ArrayList<WebElement> lists = (ArrayList<WebElement>) page.getlistbyele(By.xpath("button"),el);

            if(  lists.size()==1 ){    
            	
            	if(lists.get(0).getText().equals("Not configured")){
                	el.findElement(By.xpath("div[2]/input")).click();
                	
            	}
            }
	    }	
		this.webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	
	
	
	

  	/** 5 OK
  	 * 进入业务菜单页面,无视号码登录 1.清理2.操作3.判断是否成功
  	 * @param menuName
  	 * @return
  	 * @throws Exception
  	 */
  	public boolean intomenu(String menuName) throws Exception {
        MMainPage mMainPage = new MMainPage(webDriver);

		 //清除操作
        Thread.sleep(3000);
        mMainPage.confirmjspop();
  		mMainPage.closeErrorPop();
  		mMainPage.clearTab();
  		
  		//清理集团的中栏
  		/**
  		 *   		String strings ="";

  		 if(mMainPage.implicitlwaitelement(By.xpath("//*[@id=\"groupList\"]"), "2")){
  	  		 strings =mMainPage.getattrvalue(By.xpath("//*[@id=\"groupList\"]"), "class");

  		 }
  		 */
 
  		String strings =mMainPage.getattrvalue(By.xpath("//*[@id=\"groupList\"]"), "class");
  		if(strings.equals("list list-show")){
  			mMainPage.click(By.xpath("//*[@id=\"groupList\"]/div[1]/div[1]/div"));
  		}
  		Thread.sleep(2000);
        return  mMainPage.intoMenuByInput(menuName);

  	}
	
	
	 
	   /**  3 OK
	    * 直接传入号码登录用户 1.先清理(如js弹出框) 2.判断登出  3.登入  4.清理(营销，销户号码等等js提示框) 5.判断登入成功
	    * @param billId
	    * @return
	    * @throws Exception
	    */
	 public boolean loginbynumber(String billId) throws Exception {
			System.out.println(this.mainCaseId+"直接登录号码\n");
	        MMainPage mMainPage = new MMainPage(webDriver);
	        
		    //清除操作
	        Thread.sleep(3000);
	  		mMainPage.confirmjspop();
	  		mMainPage.closeErrorPop();
	  		mMainPage.clearTab();

	        
	  		
	  		//增加判断登出集团还是个人
	  		boolean isGroup =false;
	  		if(billId.length()>11){
	  			isGroup=true;
	  		}
	  		//4 OK
		    this.logout(isGroup);


	        


	        if(mMainPage.loginNumber(billId,isGroup)){
	        	System.out.println("直接登陆号码成功："+billId);
		         return true;
	        }
             return false;

	  	} 
		
		   /** 1. OK
		    * 查询号码并登录,循环5次   1.查号码 2.判断登出 3 清除 4 .登入 5.判断登入成功
		    * @param sqlKey
		    * @param param ,以,号分割
		    * @return
		    * @throws Exception
		    */
		 public String loginbykey(String sqlKey) throws Exception {
		  		
		          MMainPage mMainPage = new MMainPage(webDriver);
		          WebConfigDao webConfigDao = new WebConfigDao();

			  		
		  		int t = 0;
		  		String number = "";
		  		while (true) {
		  			if (t == 6) {
		  				number="";
		  				System.out.println(this.mainCaseId+"超过5尝试登录失败");			
		  				break;
		  			}
		  			
		  			//先登出号码登陆接口里一已有
		  			//this.logoutUser();
                    //查号码 
		  			number = webConfigDao.getNumberByKey(sqlKey);
		  			//number = "13628917519";

		  			t++;		  			
		  			//判断登录是否成功  3 OK
		  			if(this.loginbynumber(number)){
		  				System.out.println("当前号码" + number + "登录成功"+"\n");			

		  				break;
		  			}
		  			
	  				System.out.println("第"+t+"次登陆失败-"+"当前号码" + number + "登录成功"+"\n");			

		  		}
		  		
		  		return number;
		  	}	
		
		 
		 
	 
         // 4 OK
	     //先登出 ,不管是集团业务还是个人业务，都要先退出上一次登录
		 private void logout(boolean isGroup) throws Exception{

			MMainPage pMainPage = new MMainPage(webDriver);
			pMainPage.switchDefaults();
            
/*			if(isGroup){
				pMainPage.changePG("group");
			}else{
				pMainPage.changePG("person");

			}
			
			//先判断是否在登陆状态
			if(!pMainPage.isLogined(isGroup)){
				return ;
			}*/
			pMainPage.logout(isGroup);
			Thread.sleep(2000);
			
			 
/*						if(isGroup){
							pMainPage.changePG("group");
						}else{
							pMainPage.changePG("person");
						}*/
						
			System.out.println(this.mainCaseId+"当前号码退出");
			
		} 
		 
			/**  无用
			 * 登出用户状态登出
			 * 增加参数，退出个人和集团登陆
			 * @throws Exception
			 */
/*		public void logout() throws InterruptedException{
			MMainPage pMainPage = new MMainPage(webDriver);
			pMainPage.switchDefaults();
			pMainPage.changePG("person");
			//先判断是否在登陆状态
			if(!pMainPage.isLogined(false)){
				return ;
			}
			pMainPage.logout(false);
			Thread.sleep(2000);
		}*/
		 
		 
		 
		 
	 
	
	/** 2  OK 最常用的
	 * 获取满足查询条件的号码登录业务菜单；循环登录5次，防止业务限制或进入失败，一定会有新登录号码动作 
	 * 
	 * @param sqlKey
	 * @param MenuName
	 * @return
	 * @throws Exception
	 */
	 public String logintomenu(String sqlKey,String menuName) throws Exception {
	
		String phoneNum="";
		boolean b =false;
		int i=0;
		//新增isGroup标识
		boolean isGroup = sqlKey.contains("g_");
		
		while(true){
			if(i==5){
				break;
			}
			i++;
			phoneNum = this.loginbykey(sqlKey);
			b = this.intomenu(menuName);
			if(b){
				break;
			}
			
			this.logout(isGroup);
		}

		Thread.sleep(1000);
		return phoneNum;

	}
	
	 /**
     * 不传SQLkey参数登录并进入菜单
     * 用于直接使用当前号码操作下一个业务的场景，
     * 通常该业务没有什么特殊校验规则，正常号码就可用，
     * 然而如果当前未登录状态，或者进入菜单失败（比如rp不好上一个业务是销户,停机等），会调loginUserAndIntoMenu查询normal号码登录并进入菜单
     * @throws Exception 
     */
/*    public String loginedthenintomenu(String menuName) throws Exception{

    	MMainPage mainPage = new MMainPage(this.webDriver);

		
		if(mainPage.isLogined()){			
			System.out.println("当前号码复用");				
			//已经是登录状态，直接进菜单
			boolean b =this.intomenu(menuName);
			
			//判断是否进入菜单成功
			if(!b){
				//不成功重新登录
				return this.loginthenintomenu("normal", menuName);
		
			}   
			    //成功进入菜单返回号码
			      return mainPage.getCurrentNumber();
			
			
			
		}else{
			//重新登录进菜单
			return this.loginthenintomenu("normal",menuName);
	
		}

    

    }*/
    
    
	
    


	 
	 

	
	
	/** 6
	 * 获取前台订单，获取失败则校验不通过,目前暂不做复杂处理
	 * @return 订单号
	 * @throws Exception 
	 */
	public String getorderid() throws Exception   {

		//HashMap resultMap = new HashMap();
		//pMainPage.displaywaitelement(By.xpath(pMainPage.orderStatus_x), "40");
		//resultMap =pageCheck.checkTextEquals(pMainPage.getOrderStatus(), "业务受理成功", "校验业务受理是否成功",resultMap);
		
		//return this.getOrder();
		
		
		
		OpWebDriver opWebDriver= new OpWebDriver();
		MMainPage pMainPage = new MMainPage(webDriver);
		//opWebDriver.switchFrameByIndex(this.webDriver,1);	
		pMainPage.displaywaitelement(By.xpath(pMainPage.p_orderId_x), "100");
		int nn=0;
		String orderid="get order failed";
		//public final String p_orderId_x= "//*[@id=\"submitSucDiv\"]/div[2]/div/div[2]/div[2]/span[2]";
		//public final String g_orderId_x="//*[@id=\"flowId\"]";
		
		orderid = pMainPage.gettext(By.xpath(pMainPage.p_orderId_x));

/*		while (true) {
			orderid = pMainPage.gettext(By.xpath(pMainPage.p_orderId_x));
			if(orderid.length()>8){
				break;
			}
			Thread.sleep(1000);
			nn++;
			if(nn==30){
				break;
			}
		}*/
    	return  orderid;
    	
    	
    	
    	
    	

	}
	
	/**  7
	 * 获取集团前台订单，获取失败则校验不通过,目前暂不做复杂处理
	 * @return 订单号
	 * @throws Exception 
	 */
	public String getgrouporderid() throws Exception   {

		//HashMap resultMap = new HashMap();
		MMainPage pMainPage = new MMainPage(this.webDriver);		
		//pMainPage.displaywaitelement(By.xpath(pMainPage.orderStatus_x), "40");
		//PageCheck pageCheck = new PageCheck(webDriver);
		//resultMap =pageCheck.checkTextEquals(pMainPage.getOrderStatus(), "业务受理成功", "校验业务受理是否成功",resultMap);
		
		//return this.getGroupOrder();
		
		
		
		   
    	
				OpWebDriver opWebDriver= new OpWebDriver();
				//opWebDriver.switchFrameByIndex(this.webDriver,1);	
				//pMainPage.displaywaitelement(By.xpath(pMainPage.orderId_x), "30");
				int nn=0;
				String orderid="get order failed";
				//public final String p_orderId_x= "//*[@id=\"submitSucDiv\"]/div[2]/div/div[2]/div[2]/span[2]";
				//public final String g_orderId_x="//*[@id=\"flowId\"]"; 
				pMainPage.displaywaitelement(By.xpath(pMainPage.g_orderId_x), "30");
				orderid = pMainPage.gettext(By.xpath(pMainPage.g_orderId_x));

		/*		while (true) {
					if(orderid.length()>8){
						break;
					}
					Thread.sleep(1000);
					nn++;
					if(nn==30){
						break;
					}
				}*/
		    	return  orderid;
		    
		    	
		    	
		    	
		    	

	}	
	
	

	
	/** 8
	 * BCE菜单校验，用户进入菜单办理业务限制 ，两次机会
	 * @param sqlKey 号码关键字
	 * @param param  号码sql中对应的变量，以,号分割
	 * @param menuName 菜单名
	 * @param containsContent 菜单校验包含的内容
	 * @return
	 * @throws Exception
	 */
/*	public HashMap<String, String> menucheck(String sqlKey,String menuName,String containsContent) throws Exception{
	
         Page page = new Page(webDriver);
         PageCheck pageCheck = new PageCheck(webDriver,this.mainCaseId);
         HashMap<String, String> resultMap = new HashMap<>();
         resultMap.put("message", "");
		 resultMap.put("result", "成功");
		 String phoneNum="";
		 boolean b =false;
	
			phoneNum = this.loginbykey(sqlKey);
			b = this.intomenu(menuName);
			if(!b){
				
				String bceTip_xpath="/html/body/div/div/div[2]/div/div[2]";
				try{
					String pageconent = page.gettext(By.xpath(bceTip_xpath));
					pageCheck.checkcontains(pageconent, containsContent, menuName+"-规则校验", resultMap,this.mainCaseId);
										resultMap= pageCheck.checkcontains(pageconent, containsContent, menuName+"-规则校验", resultMap,this.mainCaseId);
					if(resultMap.get("result").equals("成功")){
						resultMap.put("result", "正确");
					}
				}catch (Exception e) {
					// TODO: handle exception
				   e.printStackTrace();
					DataUtil dataUtil = new DataUtil();
			         resultMap.put("message", "菜单未进入，但是校验页面文本报错\n"+dataUtil.getTrace(e)+"\n");
					 resultMap.put("result", "失败");
				}

			}else{
				 resultMap.put("result", "错误");
		         resultMap.put("message", "菜单进入，限制无效");

			}
		

		return resultMap;
		

	}*/

	




	
	

		

	
	
	
	
	





	

	
    //获取个人订单号
    private String getOrder() throws InterruptedException{   
    	
		OpWebDriver opWebDriver= new OpWebDriver();
		MMainPage pMainPage = new MMainPage(webDriver);
		//opWebDriver.switchFrameByIndex(this.webDriver,1);	
		pMainPage.displaywaitelement(By.xpath(pMainPage.p_orderId_x), "100");
		int nn=0;
		String orderid="get order failed";
		//public final String p_orderId_x= "//*[@id=\"submitSucDiv\"]/div[2]/div/div[2]/div[2]/span[2]";
		//public final String g_orderId_x="//*[@id=\"flowId\"]";
		
		orderid = pMainPage.gettext(By.xpath(pMainPage.p_orderId_x));

/*		while (true) {
			orderid = pMainPage.gettext(By.xpath(pMainPage.p_orderId_x));
			if(orderid.length()>8){
				break;
			}
			Thread.sleep(1000);
			nn++;
			if(nn==30){
				break;
			}
		}*/
    	return  orderid;
    }
    
    //获取集团订单号
    private String getGroupOrder() throws InterruptedException{   
    	
		OpWebDriver opWebDriver= new OpWebDriver();
		MMainPage pMainPage = new MMainPage(webDriver);
		//opWebDriver.switchFrameByIndex(this.webDriver,1);	
		//pMainPage.displaywaitelement(By.xpath(pMainPage.orderId_x), "30");
		int nn=0;
		String orderid="get order failed";
		//public final String p_orderId_x= "//*[@id=\"submitSucDiv\"]/div[2]/div/div[2]/div[2]/span[2]";
		//public final String g_orderId_x="//*[@id=\"flowId\"]"; 
		pMainPage.displaywaitelement(By.xpath(pMainPage.g_orderId_x), "30");
		orderid = pMainPage.gettext(By.xpath(pMainPage.g_orderId_x));

/*		while (true) {
			if(orderid.length()>8){
				break;
			}
			Thread.sleep(1000);
			nn++;
			if(nn==30){
				break;
			}
		}*/
    	return  orderid;
    }
    
    

    /** comop自带
     * 等待
     * @param s
     * @throws InterruptedException
     */
    public void waitamoment(String s) throws InterruptedException{
    	int ss=Integer.parseInt(s);
    	Thread.sleep(ss*1000);
    }

    /** comop自带
     * 切换页面
     * @param num
     * @throws Exception 
     */
    public void switchpage(String num) throws Exception{
    	
    	this.switchframetodefault();
    	this.switchframe("1", num);
    	
    }
    
    
    /** comop自带
     * 根据iframeid切到子frame
     * @param num
     * @throws Exception 
     */
	public void switchframebyid(String frameid) throws InterruptedException {
		//this.webDriver.switchTo().defaultContent();
        List<WebElement> frames = this.webDriver.findElements(By.tagName("iframe"));

        for(WebElement WebElement:frames){
        	String content =WebElement.getAttribute("id");
        	if(frameid.equals(content)){
        		webDriver.switchTo().frame(frameid);
        		break;

        	}
        }
        Thread.sleep(2000);

	}
	
	
    /** comop自带
     * 切回到父frame
     * @param num
     * @throws Exception 
     */
	public void switchparentframe(){
		webDriver.switchTo().parentFrame();
		
	}
    
    /** comop自带
     * 切换到业务默认的业务受理菜单中
     * @throws Exception
     */
    public  void switchframetomenu() throws Exception{
        MMainPage mMainPage = new MMainPage(webDriver);
       // mMainPage.switchToMenu();
        mMainPage.switchDefaults();
		

        mMainPage.switchFrame("1", "2");
	}
    /** comop自带
     * 切换到登录操作员后的初始默认页
     * @throws Exception
     */
    public void switchframetodefault(){
        MMainPage mMainPage = new MMainPage(webDriver);
        mMainPage.switchDefaults();
    }
    /** comop自带
     * 任意切换frame
     * @throws Exception
     */
    public void switchframe(String y,String x) throws Exception{
    	Page page = new Page(webDriver);
    	page.switchFrame(y, x);
    }
    

	/** comop自带
	 * 根据号码编码查询号码
	 * @param key
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String getnumberbykey(String key) throws Exception{
		String accessNum="";
		
		WebConfigDao webConfigDao =new WebConfigDao();
		accessNum= webConfigDao.getNumberByKey(key);
		
		//新增将测试数据放入临时表逻辑
		DbOperate dbOperate = new DbOperate();
		String caseid="1";
		String testdata="18665717801";
		dbOperate.insertData("insert into WEB_TEST_DATA (CASE_ID, TEST_DATA, DONE_DATE) values ("+caseid+", '"+testdata+"', sysdate) ");
		
		
		return accessNum;
	} 
    

}
