package test.web.cases.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.com.zl.selenium.Page;

public class WorkDeskPage {
	
	WebDriver webDriver;
	public WorkDeskPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		// TODO Auto-generated constructor stub
	}


	public final String business_person_x = "/html/body/div[1]/div/div/div/div[2]/div/ul/li[1]/a/span[2]";
	public final String business_group_x = "/html/body/div[1]/div/div/div/div[2]/div/ul/li[1]/a/span[2]";

	public final String titleName="客户关系";
	
	public void gotoBusiness(String type) throws Exception {
	    Page page= new Page(this.webDriver);
		Thread.sleep(3000);
		if(type.equals("person")){
			page.click(By.xpath(this.business_person_x));	
		}else{
			page.click(By.xpath(this.business_group_x));	
		}

	}

}
