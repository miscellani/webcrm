package base.com.zl.selenium;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpWebDriver {

	private WebDriverWait wait;


	public static void main(String[] s) throws MalformedURLException{
		OpWebDriver opWebDriver = new OpWebDriver();
		//WebDriver webDriver =opWebDriver.openBrower("chrome",1);
	    WebDriver webDriver =opWebDriver.openRBrower("10.3.17.11","chrome");
	    //WebDriver webDriver =opWebDriver.openRBrower("127.0.0.1","chrome");

		opWebDriver.get(webDriver, "http://www.baidu.com");
	}
	public WebDriver openBrower(String name, int waittime) {

		String platform = "ie";
		WebDriver webDriver = null;
		if (platform.equals("mobile")) {/*
			try {
				 webDriver = new AndroidDriver("http://localhost:8888/wd/hub");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (waittime > 0) {
				webDriver.manage().timeouts()
						.implicitlyWait(waittime, TimeUnit.SECONDS);
			}
			webDriver.manage().window().maximize();
			return webDriver;

		*/}

		if (name.indexOf("ie") > -1) {

			System.setProperty("webdriver.ie.driver","C:\\Program Files (x86)\\Internet Explorer\\IEDriverServer_64.exe");

			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);


			try {
				webDriver = new InternetExplorerDriver(ieCapabilities);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			

			if (waittime > 0) {
				webDriver.manage().timeouts()
						.implicitlyWait(waittime, TimeUnit.SECONDS);
			}

			webDriver.manage().window().maximize();
			return webDriver;

			
			
			
		} else if (name.indexOf("fire") > -1) {

			System.setProperty("webdriver.firefox.bin",
					"D:\\Mozilla Firefox\\firefox.exe");
			webDriver = new FirefoxDriver();

			if (waittime > 0) {
				webDriver.manage().timeouts()
						.implicitlyWait(waittime, TimeUnit.SECONDS);
			}

			webDriver.manage().window().maximize();
			return webDriver;

		} else  if (name.indexOf("chrome") > -1){

			//System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
			//System.setProperty("webdriver.chrome.driver","C:\\Users\\ZL\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");

			
            try{
			webDriver = new ChromeDriver();
            }catch (Exception e) {
				// TODO: handle exception
            	e.printStackTrace();
			}

			if (waittime > 0) {
				webDriver.manage().timeouts()
						.implicitlyWait(waittime, TimeUnit.SECONDS);
			}

			webDriver.manage().window().maximize();
			return webDriver;

		}else if(name.indexOf("edge") > -1){
			System.setProperty("webdriver.edge.driver","E:\\MicrosoftWebDriver.exe");
			webDriver = new EdgeDriver();
			return webDriver;

		}
		return webDriver;

	}

	public WebDriver openRBrower(String IP,String name) throws MalformedURLException {

		String platform = "window";
		WebDriver webDriver = null;
		int waittime =10;
		if (platform.equals("mobile")) {/*
			try {
				 webDriver = new AndroidDriver("http://localhost:8888/wd/hub");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (waittime > 0) {
				webDriver.manage().timeouts()
						.implicitlyWait(waittime, TimeUnit.SECONDS);
			}
			return webDriver;

		*/}

		if (name.indexOf("ie") > -1) {

			System.setProperty("webdriver.ie.driver",
					"C:\\IEDriverServer.32.exe");

			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			//IE安全设置
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);

			//10.16.179.101
				try {
					webDriver =  new RemoteWebDriver(new URL("http://" + IP+":4444/wd/hub"),ieCapabilities);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}




			if (waittime > 0) {
				webDriver.manage().timeouts()
						.implicitlyWait(waittime, TimeUnit.SECONDS);
			}

			webDriver.manage().window().maximize();
			return webDriver;

			
			
			
		} else if (name.indexOf("fire") > -1) {


            DesiredCapabilities ieCapabilities = DesiredCapabilities.firefox();
			System.setProperty("webdriver.firefox.bin",
					"C:\\firefox.exe");			
			webDriver =  new RemoteWebDriver(new URL("http://" + IP+":4444/wd/hub"),ieCapabilities);

			if (waittime > 0) {
				webDriver.manage().timeouts()
						.implicitlyWait(waittime, TimeUnit.SECONDS);
			}

			webDriver.manage().window().maximize();
			return webDriver;

		} else if(name.indexOf("edge") > -1){
			
            DesiredCapabilities ieCapabilities = DesiredCapabilities.edge();
			System.setProperty("webdriver.edge.driver","C:\\MicrosoftWebDriver.exe");
			webDriver =  new RemoteWebDriver(new URL("http://" + IP+":4444/wd/hub"),ieCapabilities);

			if (waittime > 0) {
				webDriver.manage().timeouts()
						.implicitlyWait(waittime, TimeUnit.SECONDS);
			}

			webDriver.manage().window().maximize();
			return webDriver;

		}else {
			
			
			
		        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		        chromePrefs.put("profile.default_content_settings.popups", 0);
		        ChromeOptions options = new ChromeOptions();
		        options.setExperimentalOption("prefs",chromePrefs);
		        
                //添加插件，暂时写死，后续改造
				//File plugPath = new File("C:\\extensions\\alwayscleardownloads.crx");
			//	options.addExtensions(plugPath);

				
		        DesiredCapabilities cap = DesiredCapabilities.chrome();
		        cap.setCapability(ChromeOptions.CAPABILITY, options);
	        
	        
	        
	        
			String driverPath="";
			String rootPath = System.getProperty("catalina.home");
			if (rootPath != null) {
				//放在tomcat bin目录下
				driverPath = "chromedriver.exe";

			} else {
				driverPath="C:\\chromedriver.exe";

			}
			System.out.println("chromdriver路径:"+driverPath);
			
			
          //  DesiredCapabilities ieCapabilities = DesiredCapabilities.chrome();

			System.setProperty("webdriver.chrome.driver",
					driverPath);
			webDriver =  new RemoteWebDriver(new URL("http://" + IP+":4444/wd/hub"),cap);

			//webDriver = new ChromeDriver();

			if (waittime > 0) {
				webDriver.manage().timeouts()
						.implicitlyWait(waittime, TimeUnit.SECONDS);
			}

			webDriver.manage().window().maximize();
			return webDriver;

		}

	}
	
	public void get(WebDriver webDriver,String url) {

		webDriver.get(url);
		//webDriver.navigate().to(url);
        System.out.println("-----进入网站页面");
	}

	public void close(WebDriver webDriver) {
		webDriver.close();
	}

	public void quit(WebDriver webDriver) {
		webDriver.quit();
	}

	public void reflash(WebDriver webDriver) {
		webDriver.navigate().refresh();
	}

	public String getTitle(WebDriver webDriver) {
		return webDriver.getTitle();
	}

	public void forward(WebDriver webDriver) {
		webDriver.navigate().forward();
	}

	public void back(WebDriver webDriver) {
		webDriver.navigate().back();

	}

	public void maxsize(WebDriver webDriver) {
		webDriver.manage().window().maximize();
	}

	public void setPoint(WebDriver webDriver,int x, int y) {

		webDriver.manage().window().setPosition(new Point(x, y));
	}

	public void setSize(WebDriver webDriver,int width, int height) {

		webDriver.manage().window().setSize(new Dimension(width, height));

	}

	public String geturl(WebDriver webDriver) {
		return webDriver.getCurrentUrl();
	}

/*	public void killwebDriver(String broswerType) {
		if (broswerType.indexOf("ie") > -1) {
			WindowsUtils.tryToKillByName("iexplore.exe");
		} else if (broswerType.indexOf("fire") > -1) {
			WindowsUtils.tryToKillByName("firefox.exe");

		}

	}*/

	public void screenShot(WebDriver webDrivers,String savePath) throws Exception {
		if (this.isExistPop(webDrivers)) {
			try {
				Robot robot = new Robot();
				BufferedImage bi = robot.createScreenCapture(new Rectangle(
						1500, 800));
				ImageIO.write(bi, "jpg", new File(savePath));

			} catch (AWTException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.cancelPopBox(webDrivers);

		} else {

		((JavascriptExecutor) webDrivers).executeScript("window.scrollTo(800, 800)"); 
			File scrFile = ((TakesScreenshot) webDrivers)
					.getScreenshotAs(OutputType.FILE);
			try {
				org.apache.commons.io.FileUtils.copyFile(scrFile, new File(
						savePath));
			} catch (Exception e) {
				// TODO: handle
				e.printStackTrace();
			}

		}

	}

	public void waitTitleContains(WebDriver webDriver,String waitvalue, int timeOutInSeconds) {

		wait = new WebDriverWait(webDriver, timeOutInSeconds);
		wait.until(ExpectedConditions.titleContains(waitvalue));
	}

	public void waitToBeSelected(WebDriver webDriver,WebElement element, int timeOutInSeconds) {

		wait = new WebDriverWait(webDriver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}

	public void waitToBeClickAble(WebDriver webDriver,WebElement element, int timeOutInSeconds) {

		wait = new WebDriverWait(webDriver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitText(WebDriver webDriver,WebElement element, String value, int timeOutInSeconds) {

		wait = new WebDriverWait(webDriver, timeOutInSeconds);
		wait.until(ExpectedConditions.textToBePresentInElement(element, value));
	}

	public void waitValue(WebDriver webDriver,WebElement element, String value, int timeOutInSeconds) {

		wait = new WebDriverWait(webDriver, timeOutInSeconds);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element,
				value));
	}

	public String getHandle(WebDriver webDriver) {

		return webDriver.getWindowHandle();

	}
	public Set getHandles(WebDriver webDriver){
		
		return 		webDriver.getWindowHandles();

		
	}
	/**
	 * 将window切换到当前
	 * @throws Exception
	 */
	
	public void switchWindow(WebDriver webDriver) throws Exception{	
		Thread.sleep(3000);
		OpWebDriver opWebDriver = new OpWebDriver();
	    Set set= opWebDriver.getHandles(webDriver)  ;
		String rightHandle="";
        Iterator<String> it = set.iterator();

       while(it.hasNext()){
       	rightHandle=it.next();          	
       }
       opWebDriver.switchWindowByHandle(webDriver, rightHandle);
		Thread.sleep(1000);
/*		if(opWebDriver.getHandles(webDriver).size()==1){
			try {
				this.switchFrame(2,0);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}*/
       	
	}
	
	public void switchWindowByHandle(WebDriver webDriver,String handleid) {

		webDriver.switchTo().window(handleid);
		

	}
	

	public String getPageSource(WebDriver webDriver) {

		return webDriver.getPageSource();

	}

	public boolean isExistPop(WebDriver webDriver) {

		Alert alert = null;
		try {

			alert = webDriver.switchTo().alert();

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;

	}

	public String getPopBoxtText(WebDriver webDriver) {
		Alert alert = null;
		alert = webDriver.switchTo().alert();
		return alert.getText();

	}

	public void setPopBoxText(WebDriver webDriver,String text) {
		Alert alert = null;
		alert = webDriver.switchTo().alert();
		alert.sendKeys(text);

	}

	public void confirmPopBox(WebDriver webDriver) {
		Alert alert = null;

		try {
			alert = webDriver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			// TODO: handle exception

			return;
		}

	}

	public void cancelPopBox(WebDriver webDriver) {

		try {
			Alert alert = webDriver.switchTo().alert();
			alert.dismiss();
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}

	}


	
	
	
	public Set getCookie(WebDriver webDriver) {

		return webDriver.manage().getCookies();
	}

	public org.openqa.selenium.Cookie getCookieByName(WebDriver webDriver,String cookiename) {

		return webDriver.manage().getCookieNamed(cookiename);

	}

	public void addCookie(WebDriver webDriver,String name, String value) {
		org.openqa.selenium.Cookie cookie = new org.openqa.selenium.Cookie(
				name, value);
		webDriver.manage().addCookie(cookie);
	}

	public void deleteCookieByName(WebDriver webDriver,String cookiename) {

		webDriver.manage().deleteCookieNamed(cookiename);
	}

	public void deleteAllCookies(WebDriver webDriver) {

		webDriver.manage().deleteAllCookies();
	}

	public void scrollToBottom(WebDriver webDriver) {

		((JavascriptExecutor) webDriver)
				.executeScript("window.scrollTo(0,document.body.scrollHeight)");

	}

	public void scrollToElement(WebDriver webDriver,WebElement element) {

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView();", element);

	}

	public void scrollto(WebDriver webDriver,int xOffset, int yOffset) {

		((JavascriptExecutor) webDriver)
				.executeScript("window.scrollBy(xOffset,yOffset)");

	}

	public void elementHlight(WebDriver webDriver,WebElement element) {

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].setAttribute('style',arguments[1]);", element,
				"background: yellow;border: 2px;solid red;");

	}

}
