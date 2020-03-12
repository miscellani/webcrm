package base.com.zl.selenium;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.com.zl.utils.DateUtil;


public class Page {

	private Actions action;
    public WebDriver webDriver;
	public Page(WebDriver webDriver) {


        this.webDriver= webDriver;
		action = new Actions(this.webDriver);
	}
	
	
	/**
	 * 访问链接地址
	 * @param url
	 */
	public void openurl(String url){
		OpWebDriver opWebDriver = new OpWebDriver();
		opWebDriver.get(this.webDriver, url);
	}
	
/*	*//**
	 * 截图
	 *//*
	public void screenShot(){
		
        screenpath = WebInit.testDir + mainCaseId+File.separator +"wrong_" + remark + ".png";
        opWebDriver.screenShot(this.webDriver,screenpath);
		
	}*/

	/**
	 * 获取元素
	 * 
	 * @param by
	 *            查找方式
	 * @return WebElement元素对象
	 * @throws InterruptedException 
	 */
	public WebElement getelement(By by)  {

		WebElement element=this.webDriver.findElement(by);
		this.hLightElement(element);
		return element;

	}
	
	/**
	 * 获取元素
	 * 
	 * @param by
	 *            查找方式
	 * @return WebElement元素对象
	 * @throws InterruptedException 
	 */
	public WebElement getelebyelement(WebElement ele,String xpath)  {
		WebElement element=ele.findElement(By.xpath(xpath));
		this.hLightElement(element);
		return element;


	}
	
	
	/**
	 * 获取元素list
	 * 
	 * @param by
	 * @return list 内含WebElement
	 */
	public ArrayList<WebElement> getelementlist(By by) {

	
		return (ArrayList<WebElement>) this.webDriver.findElements(by);

	}
	
	

	/**
	 * 根据索引从页面元素list中获取元素
	 * 
	 * @param indix 索引文本值
	 * @return WebElement元素对象
	 * @throws InterruptedException 
	 */
	public WebElement getelebylist(ArrayList<WebElement> list,String index)  {

		WebElement element=list.get(Integer.parseInt(index));
		this.hLightElement(element);
		return element;

	}
	
	///////////////获取li的一系列操作，开始
	
	
	

	
	/**
	 * 从某元素中获取元素list
	 * 
	 * @param by
	 * @return list 内含WebElement
	 */
	public ArrayList<WebElement> getlistbyele(By by,WebElement element) {
		this.modifyimplicitlwaittime(2);
		ArrayList<WebElement> listweb=new ArrayList<WebElement>();
		try {
			listweb=(ArrayList<WebElement>) element.findElements(by);
		} finally {
			// TODO: handle finally clause
			this.modifyimplicitlwaittime(10);
		}		
		return listweb;
	}
	
	
	
	

	/**
	 * 轮询元素list中每一个元素，返回包含某文本值的元素
	 * 
	 * @param by
	 * @param context
	 * @return WebElement元素对象
	 */               
	public WebElement geteleinlistbytext(By by, String context) {

		for (WebElement webElement : this.getelementlist(by)) {
			this.hLightElement(webElement);
			if (webElement.getText().contains(context)) {
				return webElement;
			}
		}
		return null;

	}

	/**
	 * 轮询元素list中每一个元素，匹配属性及属性值返回元素
	 * 
	 * @param by
	 * @param attrName
	 * @param Value
	 * @return WebElement元素对象
	 */
	public WebElement geteleinlistbyattr(By by, String attrName,
			String Value) {
         String  attrname="";
		for (WebElement webElement : this.getelementlist(by)) {
			try{
				attrname=webElement.getAttribute(attrName);
			}catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			if ( (attrname!=null) && (attrname.equals(Value)) ) {
				return webElement;
			}
		}
		return null;
	}

	/**
	 * 轮询元素list中每一个元素，根据索引返回元素
	 * 
	 * @param list
	 * @param index
	 * @return WebElement元素对象
	 */                
	public WebElement geteleinlistbyindex(By by, String index) {
		               
		WebElement webElement = this.getelementlist(by).get(Integer.parseInt(index));
		this.hLightElement(webElement);
		return webElement;

	}

	
	
	
	
	
	
    public void clicklistbytext(By by, String context){
    	WebElement webElement = this.geteleinlistbytext(by, context);
		this.hLightElement(webElement);
    	webElement.click();
    }
    public void clicklistbyattrvalue(By by, String attrName,String Value){
    	WebElement webElement = this.geteleinlistbyattr(by, attrName, Value);
    	webElement.click();
    }
    
    public void clicklistbyindex(By by, String index){
    	WebElement webElement = this.geteleinlistbyindex(by, index);
		this.hLightElement(webElement);
    	webElement.click();
    }
    
    public void jsclicklistbytext(By by, String context) throws InterruptedException{
    	WebElement webElement = this.geteleinlistbytext(by, context);
    	this.jsclickelement(webElement);
    }
    public void jsclicklistbyattrvalue(By by, String attrName,String Value) throws InterruptedException{
    	WebElement webElement = this.geteleinlistbyattr(by, attrName, Value);
    	this.jsclickelement(webElement);
    }
    
    public void jsclicklistbyindex(By by, String index) throws InterruptedException{
    	WebElement webElement = this.geteleinlistbyindex(by, index);
    	this.jsclickelement(webElement);
    }
    	
        /**
         * 从下拉列表中根据文本值选择某枚举
         * @throws InterruptedException 
         */
        public void choicebytext(By by, String context) throws InterruptedException{
        	//Thread.sleep(2000);

        	WebElement webElement = this.getelement(by);
        	webElement.click();
        	//this.jsclick(by);
        	this.hLightElement(webElement);
        	Thread.sleep(2000);
        	String eId=webElement.getAttribute("id");
        	eId=eId.replace("_span", "");
        	//  //*[@id="IS_BUSI_DATA_CARD_float"]/div[2]/div/div/ul/li
        	String listXpath ="//*[@id=\""+eId+"_float\"]/div[2]/div/div/ul/li";
        	this.clicklistbytext(By.xpath(listXpath), context);
        	Thread.sleep(2000);
        }
        
        /**
         * 从下拉列表中根据某属性值选择某枚举
         * @throws InterruptedException 
         */
        public void choicebyattrvalue(By by, String attrName,String Value) throws InterruptedException{
        	//Thread.sleep(2000);

        	WebElement webElement = this.getelement(by);
        	webElement.click();
        	//this.jsclick(by);
        	Thread.sleep(2000);
        	String eId=webElement.getAttribute("id");
        	eId=eId.replace("_span", "");
        	//  //*[@id="IS_BUSI_DATA_CARD_float"]/div[2]/div/div/ul/li
        	String listXpath ="//*[@id=\""+eId+"_float\"]/div[2]/div/div/ul/li";
        	this.clicklistbyattrvalue(By.xpath(listXpath), attrName,Value);
        	Thread.sleep(2000);
        }
        
        /**
         * 从下拉列表中根据索引值选择某枚举
         * @throws InterruptedException 
         */
        public void choicebyindex(By by, String index) throws InterruptedException{
        	//Thread.sleep(2000);
        	WebElement webElement = this.getelement(by);
        	webElement.click();

        	//this.jsclick(by);
        	this.hLightElement(webElement);
        	Thread.sleep(2000);
        	String eId=webElement.getAttribute("id");
        	eId=eId.replace("_span", "");
        	//  //*[@id="IS_BUSI_DATA_CARD_float"]/div[2]/div/div/ul/li
        	String listXpath ="//*[@id=\""+eId+"_float\"]/div[2]/div/div/ul/li";
        	this.clicklistbyindex(By.xpath(listXpath), index);
        	Thread.sleep(2000);
        }	
    	


	
        /**
         * 设置日期，支持写死设置固定日期和动态设置日期
         * @param year ，正整数 或者带＋，－号的整数
         * @param month ，正整数 或者带＋，－号的整数
         * @throws InterruptedException 
         */
        public void setdate(By by,String year,String month) throws InterruptedException{
	    	int nian=Integer.parseInt(year);
        	int yue=Integer.parseInt(month);
        	
        	
        	
        	//获取当前年月
        	DateUtil dateUtil = new DateUtil();
        	String currentnian = dateUtil.nowToYear();
        	String currentyue = dateUtil.nowToMonth();
            if(currentyue.startsWith("0")){
            	currentyue = currentyue.substring(1);
            }
        	
        	//确定设置年
        	if(year.contains("+")){
        		//.先计算出 目标年月                                           
        		  // -----如果是当前年加上 增加的月大于12 ，目标年+1 ,目标月-12
        		year=year.substring(1);
        		nian = Integer.parseInt(currentnian)+Integer.parseInt(year);
                year = Integer.toString(nian);

        	}else if(year.contains("-")){
     		   //-----如果是当前年减去 减去的月小于1  ，目标年-1，目标月+12
        		year=year.substring(1);
        		nian = Integer.parseInt(currentnian)-Integer.parseInt(year);
                year = Integer.toString(nian);
                
        	}
        	
        	//设置月
        	if(month.contains("-")){
        		//.先计算出 目标年月                                           
        		  // -----如果是当前月加上 增加的月大于12 ，目标年+1 ,目标月-12
        		   //-----如果是当前月减去 减去的月小于1  ，目标年-1，目标月+12
        		
        		month = month.substring(1);
        		if(month.startsWith("0")){
        			month=month.substring(1);
        		}
        		if( !(Integer.parseInt(currentyue) -Integer.parseInt(month)>0 )){
        			yue =Integer.parseInt(currentyue)  -Integer.parseInt(month) +12;
        			
        			year= Integer.toString(Integer.parseInt(year)-1);
        		}else{
        			yue =Integer.parseInt(currentyue)  -Integer.parseInt(month) ;

        		}
        		

        	}else if(month.contains("+")){
        		
        		month = month.substring(1);
        		if(month.startsWith("0")){
        			month=month.substring(1);
        		}
        		if( Integer.parseInt(currentyue) +Integer.parseInt(month)>12 ){
        			yue =Integer.parseInt(currentyue)  +Integer.parseInt(month) -12;
        			
        			year= Integer.toString(Integer.parseInt(year)+1);
        		}else{
        			yue =Integer.parseInt(currentyue)  + Integer.parseInt(month) ;

        		}
        		
        	}
        	
        	
        	
        	//先点开日期组建
        	this.click(by);
        	Thread.sleep(2000);
        	//点开年份栏
        	this.click(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[1]/div[3]/span[1]"));
        	Thread.sleep(2000);
        	//获取栏内最小年份和最大年份
        	String firstY = "";
        	String lastY="";
        	
            firstY = this.gettext(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[1]/ul/li[1]"));
            lastY = this.gettext(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[1]/ul/li[10]"));
            firstY= firstY.replace("\"", "");
            lastY=  lastY.replace("\"", "");
            int first = Integer.parseInt(firstY);
            int last = Integer.parseInt(lastY);
          
            int num=0;
            while(true){
            	num++;
            	if(num>10){
            		break;
            	}
            if(nian<first){
            	//点击年份减一轮
            	this.click(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[1]/span[1]"));
            	Thread.sleep(2000);
            	first = first-10;
            }else if(nian>last){
            	this.click(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[1]/span[2]"));
            	Thread.sleep(2000);
            	last = last+10;
            	
            }else{
            	//在年框中，选择年份勾选
            	this.clicklistbytext(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[1]/ul/li"), year);
            	Thread.sleep(2000);
            	break;
            }
            
            }
            
            //在月框中选择月份
                yue =yue -1;
                this.clicklistbyindex(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[2]/ul/li"), Integer.toString(yue));
                Thread.sleep(2000);
                
             //点击确定关闭日期选择       
                this.click(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[7]/button[3]"));
                Thread.sleep(2000);
            
            

        }
	
        
    
        
        
        /**
         * 设置固定日期
         * @param year ，年
         * @param month ，月
         * @param day ，日
         * @throws InterruptedException 
         */
        public void setfixdate(By by,String year,String month,String day) throws InterruptedException{
	    	int nian=Integer.parseInt(year);
        	int yue=Integer.parseInt(month);
        	int ri=Integer.parseInt(day);
        	
        	
        	//获取当前年月
        	DateUtil dateUtil = new DateUtil();
        	String currentnian = dateUtil.nowToYear();
        	String currentyue = dateUtil.nowToMonth();
            if(currentyue.startsWith("0")){
            	currentyue = currentyue.substring(1);
            }

        	
        	
        	
        	//先点开日期组建
        	this.click(by);
        	Thread.sleep(2000);
        	//点开年份栏
        	this.click(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[1]/div[3]/span[1]"));
        	Thread.sleep(2000);
        	//获取栏内最小年份和最大年份
        	String firstY = "";
        	String lastY="";
        	
            firstY = this.gettext(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[1]/ul/li[1]"));
            lastY = this.gettext(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[1]/ul/li[10]"));
            firstY= firstY.replace("\"", "");
            lastY=  lastY.replace("\"", "");
            int first = Integer.parseInt(firstY);
            int last = Integer.parseInt(lastY);
          
            int num=0;
            while(true){
            	num++;
            	if(num>10){
            		break;
            	}
            if(nian<first){
            	//点击年份减一轮
            	this.click(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[1]/span[1]"));
            	Thread.sleep(2000);
            	first = first-10;
            }else if(nian>last){
            	this.click(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[1]/span[2]"));
            	Thread.sleep(2000);
            	last = last+10;
            	
            }else{
            	//在年框中，选择年份勾选
            	this.clicklistbytext(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[1]/ul/li"), year);
            	Thread.sleep(2000);
            	break;
            }
            
            }
            
            //在月框中选择月份
                yue =yue -1;
                this.clicklistbyindex(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[2]/div[2]/ul/li"), Integer.toString(yue));
                Thread.sleep(2000);
                
                
                
             //选择日   
                ArrayList<WebElement> list=  this.getelementlist(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[4]/ul/li"));
                for(WebElement e:list){
                	if(e.getText().equals("")){
                		continue;
                		
                	}
                	if( (e.findElement(By.xpath("span")).getText()).equals(day) ){
                		e.findElement(By.xpath("span")).click();
                		break;
                	}
                }
                Thread.sleep(2000);
             //点击确定关闭日期选择       
                try{
                    this.click(By.xpath("//*[@id=\"_Wade_DropDownCalendar\"]/div[7]/button[3]"));
	
                }catch (Exception e) {
					// TODO: handle exception
				}
                Thread.sleep(2000);
            
            

        }
        
        
        
        
        
        
    /**
     * 上传文件    
     * @param by
     * @param filepath c:\\test.txt"
     * @throws InterruptedException 
     */
	public void uploadfile(By by,String filepath) throws InterruptedException{
		
		WebElement webElement = this.webDriver.findElement(by);
		this.hLightElement(webElement);
		webElement.sendKeys(filepath);
		Thread.sleep(2000);

	}
	
	/**
	 * 点击元素
	 * 
	 * @param by
	 * @throws InterruptedException 
	 */
	public void click(By by) throws InterruptedException  {
		
		
		WebElement element=this.webDriver.findElement(by);		
		this.hLightElement(element);
		element.click();
		Thread.sleep(2000);
	}

	/**
	 * 单击元素,当普通点击不生效的时候使用JS操作
	 * 
	 * @param By
	 * @throws InterruptedException
	 */
	public void jsclick(By by) throws InterruptedException {
		
		WebElement element=this.webDriver.findElement(by);
		this.hLightElement(element);
		((JavascriptExecutor) this.webDriver).executeScript(
				"arguments[0].click();", element);
		Thread.sleep(2000);

	}
	
	
	/**
	 * Action点击元素
	 * @param webElement
	 * @throws InterruptedException
	 */
	public void aclick(By by) throws InterruptedException{
		WebElement element=this.webDriver.findElement(by);
		this.hLightElement(element);
        Actions actions = new Actions(this.webDriver);
        actions.moveToElement(element).click();
		Thread.sleep(2000);
	}
	
	/**
	 * 回车单击元素,终极操作，流弊
	 * 
	 * @param By
	 * @throws InterruptedException
	 */
	public void eclick(By by) throws InterruptedException {
		
		WebElement element=this.webDriver.findElement(by);
		this.hLightElement(element);
        element.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}	
	
	
	

	
	
	
	
	/**
	 * 双击某元素
	 * @param by
	 * @throws InterruptedException
	 */
	public  void dclick(By by) throws InterruptedException {

         Actions act = new Actions(this.webDriver);
         WebElement element=this.webDriver.findElement(by);
 		this.hLightElement(element);
            act.doubleClick(element).build().perform();
    		Thread.sleep(2000);
         }
	
	
	
	
	/**
	 * 单击元素
	 * 
	 * @param By
	 * @throws InterruptedException
	 */
	public void clickelement(WebElement webElement) throws InterruptedException {
		
		this.hLightElement(webElement);
/*		((JavascriptExecutor) this.webDriver).executeScript(
				"arguments[0].click();", webElement);*/
		webElement.click();
	}
	
	
	/**
	 * js单击元素
	 * 
	 * @param By
	 * @throws InterruptedException
	 */
	public void jsclickelement(WebElement webElement) throws InterruptedException {
		
		this.hLightElement(webElement);
		((JavascriptExecutor) this.webDriver).executeScript(
				"arguments[0].click();", webElement);

	}
	
	
	
	
	
	

	/**
	 * 清除文本框元素内容
	 * 
	 * @param by
	 * @throws InterruptedException 
	 */
	public void clearinput(By by) {

		WebElement element=this.webDriver.findElement(by);
		this.hLightElement(element);
		element.clear();
		//this.onBlur();

	}

	/**
	 * 文本框设值
	 * 
	 * @param by
	 * @param content
	 *            设置内容
	 * @throws InterruptedException 
	 */
	public void input(By by, String content) {
		
		WebElement webElement = this.webDriver.findElement(by);
		this.hLightElement(webElement);

		webElement.clear();
		webElement.sendKeys(content);
		// this.onBlur();

	}

	/**
	 * js方式设值
	 * 
	 * @param by
	 * @param content
	 * @throws InterruptedException 
	 */
	public void jsinput(By by, String content)  {
		WebElement webElement = this.webDriver.findElement(by);
		this.hLightElement(webElement);
		((JavascriptExecutor) this.webDriver).executeScript(
				"arguments[0].setAttribute(arguments[1],arguments[2])",
				webElement, "value", content);
		//this.onBlur();

	}

	/**
	 * 模拟设置
	 * @param theKey
	 */
	public void sendKey(Keys theKey) {

		action.sendKeys(theKey).perform();
		action.sendKeys(Keys.NULL);

	}
	
	
	
	
	/**
	 * 失去焦点
	 */
	public void onBlur() {
		action.keyDown(Keys.TAB).build().perform();
		action.keyUp(Keys.TAB).build().perform();
	}

	/**
	 * 获取元素文本内容，非文本框内容
	 * 
	 * @param by
	 * @return 文本内容
	 */
	public String gettext(By by) {

		try {
			
			WebElement webElement = this.webDriver.findElement(by);
			this.hLightElement(webElement);
			return webElement.getText();

		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("-----获取文本内容抛错");
			e.printStackTrace();
			return "";
		}

	}
	
	/**
	 * 获取元素文本内容，非文本框内容
	 * 
	 * @param by
	 * @return 文本内容
	 */
	public String geteletext(WebElement webElement){
		this.hLightElement(webElement);
		return webElement.getText();
	}
	      

	/**
	 * 获取文本框中输入的内容
	 * 
	 * @param by
	 * @return 文本框内容
	 * @throws InterruptedException
	 */
	public String getinput(By by) {

		try {
						
			Thread.sleep(1000);			
			WebElement webElement = this.webDriver.findElement(by);
			this.hLightElement(webElement);
			return (String) ((JavascriptExecutor) this.webDriver)
					.executeScript("arguments[0].value",
							webElement);

		} catch (Exception e) {
			// TODO: handle exception

			//System.out.println("-----获取文本内容抛错");
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 根据元素属性名获取对应的属性值
	 * 
	 * @param by
	 * @param attrName
	 * @return 元素属性value值
	 * @throws InterruptedException 
	 */
	public String getattrvalue(By by, String attrName) {
		
		WebElement webElement = this.webDriver.findElement(by);
		return webElement.getAttribute(attrName);

	}
	
	/**
	 * 根据元素属性名获取对应的属性值
	 * 
	 * @param by
	 * @param attrName
	 * @return 元素属性value值
	 * @throws InterruptedException 
	 */
	public String getattrvaluebyele(WebElement ele, String attrName) {
		

		return ele.getAttribute(attrName);

	}
	
	
	
	/**
	 * 根据元素属性名设置值
	 * @param by
	 * @param attrName
	 * @param attrValue
	 */
	public void setattrvalue(By by,String attrName,String attrValue){
		WebElement webElement = this.webDriver.findElement(by);
/*	((JavascriptExecutor) this.webDriver).executeScript(
			"arguments[0].setAttribute(arguments[1],arguments[2])",
			webElement, attrName, attrValue);*/
    ((JavascriptExecutor) this.webDriver).executeScript(
    		"arguments[0].setAttribute('"+attrName+"',arguments[1]);",
    		webElement,attrValue);


	}
	
	
	/**
	 * 判断元素是否可见
	 * @param by
	 * @return
	 */
	public String isdisplay(By by){
		WebElement webElement = this.webDriver.findElement(by);
		this.hLightElement(webElement);
		if(webElement.isDisplayed()){
			return "true";
		}else{
			return "false";
		}
	}

	/**
	 * 判断radio,checkbox是否被选中
	 * 
	 * @param by
	 * @return boolean
	 * @throws InterruptedException 
	 */
	public boolean isselected(By by)  {
		
		WebElement webElement = this.webDriver.findElement(by);
		this.hLightElement(webElement);

		if (webElement.isSelected()) {

			return true;
		}
		return false;

	}

	/**
	 * 通过文本选择下拉列表某项
	 * 
	 * @param by
	 * @param content
	 *            下拉选项文本值
	 * @throws InterruptedException
	 */
	public void selectbytext(By by, String content)  {

		
		WebElement webElement = this.webDriver.findElement(by);
		this.hLightElement(webElement);
		Select sl = new Select(webElement);
		sl.selectByVisibleText(content);

	}

	/**
	 * 通过文本取消选择
	 * 
	 * @param by
	 * @param content
	 *            下拉选项文本值
	 * @throws InterruptedException
	 */
	public void cancelselectbytext(By by, String content)
			throws InterruptedException {

		WebElement webElement = this.webDriver.findElement(by);
		this.hLightElement(webElement);
		Select sl = new Select(webElement);
		sl.deselectByVisibleText(content);

	}

	/**
	 * @通过索引选择单选下拉列表，从0开始
	 * @param element
	 * @param attr
	 * @return
	 * @throws InterruptedException
	 */
	public void selectbyindex(By by, String n)  {
		WebElement webElement = this.webDriver.findElement(by);
		this.hLightElement(webElement);
		Select sl = new Select(webElement);
		sl.selectByIndex(Integer.parseInt(n));

	}

	/**
	 * 通过索引取消选择，从0开始
	 * 
	 * @param by
	 * @param n
	 * @throws InterruptedException
	 */
	public void cancelselectbyindex(By by, String n)  {
		WebElement webElement = this.webDriver.findElement(by);
		this.hLightElement(webElement);
		Select sl = new Select(webElement);
		sl.deselectByIndex(Integer.parseInt(n));

	}

	/**
	 * 显式等待某元素出现，通常等待的元素需要操作  :显示等待是针对于某个特定的元素设置的等待时间，如果在规定的时间范围内，没有找到元素，则会抛出异常，如果在规定的时间内找到了元素，则直接执行，即找到元素就执行相关操作。
	 * 
	 * @param by
	 *            查找方式
	 * @param waitTime
	 *            等待时间 单位秒
	 * @param by
	 * @param waitTime
	 */
	public WebElement displaywaitelement(final By by, String waitTime) {

		OpWebDriver opWebDriver = new OpWebDriver();
		//opWebDriver.switchFrameByIndex(this.webDriver,1);
		WebDriverWait wait = new WebDriverWait(this.webDriver, Integer.parseInt(waitTime));
		
		return wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				
				return d.findElement(by);
			}
		}

		);

	}

  /**
   * 临时修改等待时间
   * @param i
   */
	   public void modifyimplicitlwaittime(int i){
		this.webDriver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);

	}
	/**
	 * 
	 * 隐式等待元素，用于只是判断元素是否存在 隐式等待是其实可以理解成在规定的时间范围内，浏览器在不停的刷新页面，直到找到相关元素或者时间结束。
	 * 
	 * @param by
	 * @param waitTime
	 * @return boolean
	 */
	public boolean implicitlwaitelement(By by, String waitTime) {

		this.webDriver.manage().timeouts()
				.implicitlyWait(Integer.parseInt(waitTime), TimeUnit.SECONDS);

		try {
			this.webDriver.findElement(by);
			this.webDriver.manage().timeouts()
					.implicitlyWait(10, TimeUnit.SECONDS);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			//this.webDriver.findElement(by);
			this.webDriver.manage().timeouts()
					.implicitlyWait(10, TimeUnit.SECONDS);
			return false;
		}

	}
	
	
	
	/**
	 * 光标移除，用于输入框值校验js触发
	 * @throws Exception 
	 */
	public void unfocus() throws Exception{
		
		Actions action = new Actions(webDriver);
/*		action.sendKeys(Keys.TAB).perform();
		action.sendKeys(Keys.NULL);*/
		action.keyDown(Keys.SHIFT).sendKeys(Keys.TAB).keyUp(Keys.SHIFT).perform();
		Thread.sleep(2000);
	}
	
    /**
     * 判断是否存在js弹出框
     * @return
     */
	public boolean isExistJsPop() {

		Alert alert = null;
		try {

			alert = this.webDriver.switchTo().alert();

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;

	}

	/**
	 * 获取js弹出框文本内容
	 * @return
	 */
	public String getJsPopText() {
		Alert alert = null;
		try {
			alert = webDriver.switchTo().alert();
			return alert.getText();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}



	}

	/**
	 * 设置js文本输入框内容
	 * @param text
	 */
	public void setJsPopText(String text) {
		Alert alert = null;
		try {
			alert = this.webDriver.switchTo().alert();
			alert.sendKeys(text);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}

	/**
	 * 点击确定js弹出框
	 */
	public void confirmjspop() {
		Alert alert = null;

		try {
			alert = webDriver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			// TODO: handle exception

			return;
		}

	}
	
	/**
	 * 点击取消js弹出框
	 */
	
	public void cancelJsPop(WebDriver webDriver) {

		try {
			Alert alert = webDriver.switchTo().alert();

			alert.dismiss();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}

	}
	
	//----------------------------iframe切换

	//只适用于2层
	public void switchFrameByIndex(WebDriver webDriver,int indexid) {
		webDriver.switchTo().defaultContent();
		webDriver.switchTo().frame(indexid);
	}
	//先查找到iframe对象，然后根据元素切换
	public void switchFrameByElement(WebDriver webDriver,By by) {
		webDriver.switchTo().defaultContent();
        WebElement frame = webDriver.findElement(by);
		webDriver.switchTo().frame(frame);
	}
	
	

	//切回到默认
	public void switchDefaults() {
		webDriver.switchTo().defaultContent();

	}
	

	
	//切换iframe cen =0 默认 ,i=0默认   ; 1,1竖一次，横一次
	public void switchFrame(String cen,String i) throws Exception{		
		int cenNum = Integer.parseInt(cen);
		int iNum = Integer.parseInt(i);

		if(cen.equals("0")){
			//webDriver.switchTo().defaultContent();
			
		}else {
			//webDriver.switchTo().defaultContent();
			for(int j=0; j<cenNum-1;j++){
				webDriver.switchTo().frame(0);
			}
			//每掉一次竖切一次
			webDriver.switchTo().frame(iNum);
		}	
  			
	}
	
	
	
	/**
	 * 滚动到底部
	 * @param webDriver
	 */
	public void scrolltobottom(WebDriver webDriver) {

		((JavascriptExecutor) webDriver)
				.executeScript("window.scrollTo(0,document.body.scrollHeight)");

	}
	
	
	
	
	
	
	
	
	
    private void hLightElement(WebElement element) {
        
    	//if(ConfigConst.isDebug.equals("1")){
            ((JavascriptExecutor) this.webDriver).executeScript("arguments[0].setAttribute('style',arguments[1]);",element,"background:yellow;border:2px solid red;");


    	//}
    }

}
