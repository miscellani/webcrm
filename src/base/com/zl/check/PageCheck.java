package base.com.zl.check;


import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import java.io.File;

import base.com.zl.selenium.OpWebDriver;
import test.web.WebInit;



public class PageCheck {
	
	public WebDriver webDriver ;
	public String mainCaseId;

	public PageCheck(){
		
	}
	public PageCheck(WebDriver webDriver){
		this.webDriver= webDriver;
		
	}
	public PageCheck(WebDriver webDriver,String mainCaseId){
		this.webDriver= webDriver;
		this.mainCaseId=mainCaseId;
		
	}

	
	/**
	 * 直接设置WEB校验结果
	 * @param result
	 * @param checkmessage
	 * @throws Exception 
	 */
	private boolean webCheck(boolean result, String checkmessage) throws Exception {
		return result;/*

		String screenpath = null;
		OpWebDriver opWebDriver = new OpWebDriver();
		
		if(!("ConfigConst.isDebug".equals("2"))){
			
			if (result) {

				screenpath = "SpInit.caseDir" + "success_" + checkmessage + ".png";
				opWebDriver.screenShot(this.webDriver,screenpath);

			} else {
				//SpInit.caseresult = "false";
				//screenpath = SpInit.caseDir + "fail_" + checkmessage + ".png";
				opWebDriver.screenShot(this.webDriver,screenpath);
			}
			
		}else{
	
				DbOperate dbOperate = new DbOperate();
				String sql="select t.caseid from sf.autotest_case t where t.caseid='"+caseId+"'";
				dbOperate.getConnction();
				ArrayList<String> strings = dbOperate.searchStrings(sql);
				if(strings.size()==0){				
						System.out.println(caseId+"错误|要校验的CASE未找到："+caseId+"\n");				

	                 return false;				
				}
				//目录应该是新启动的目录，所以是本身的CaseID，校验的是非启动时创建的文件夹，所以要传校验的CASEID
			    String caseDir = "SpInit.testDir" + this.mainCaseId + File.separator;			    

			    
				if(!result){
					//判断如果CASEID不是当前主CASEID才更新数据库				
					if(this.mainCaseId!=caseId){
						 sql ="update sf.autotest_Case t set failnum=failnum+1,result='fail' where caseid='"+caseId+"'";	
					     dbOperate.updateData(sql);

					}else{
						
						 sql ="update sf.autotest_Case t set result='fail' where caseid='"+caseId+"'";	
					     dbOperate.updateData(sql);
					}
					
					
					 screenpath = caseDir + "fail_" + checkmessage + ".png";
					 opWebDriver.screenShot(this.webDriver,screenpath);	

				}else{

					//判断如果CASEID不是当前主CASEID才更新数据库
					if(this.mainCaseId!=caseId){
						 sql ="update sf.autotest_Case t set  result='success' , successnum=successnum+1,lastsuccess=sysdate where caseid='"+caseId+"'";	
						 dbOperate.updateData(sql);	
					}
					
					//成功时主CASE不做更新


					screenpath = caseDir + "success_" + checkmessage + ".png";
					opWebDriver.screenShot(this.webDriver,screenpath);
				}
				
		
		}
			
		System.out.println(caseId+"web校验|"+result+"|"+checkmessage);				
        
		return result;

	*/}
	
	/**
	 * 校验字符串包含某文本
	 * @param webContent 页面获取的字符串
	 * @param content 目标字符串
	 * @param checkmessage  校验信息
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> checkcontains(String webContent, String expectContent,String remark,HashMap<String, String> resultMap,String caseID) throws Exception {
		
	    String screenpath = null;
	    OpWebDriver opWebDriver = new OpWebDriver();
        String messageString  =resultMap.get("message");
		if (webContent.contains(expectContent)) {

			 //webCheck(true, remark);
			 messageString =messageString +"正确_"+remark+"\n";

		        screenpath = WebInit.testDir + mainCaseId+File.separator +"right_" + remark + ".png";
		        opWebDriver.screenShot(this.webDriver,screenpath);

		}else{
			 //webCheck(false, remark);
			 messageString =messageString +"错误_caseid:"+caseID+"_"+remark+"\n";
			 messageString=messageString+"当前值:"+webContent+"\n";
			 messageString=messageString+"期望值:"+expectContent+"\n";
			 resultMap.put("result", "错误");
		        screenpath = WebInit.testDir + mainCaseId+File.separator +"wrong_" + remark + ".png";
		        opWebDriver.screenShot(this.webDriver,screenpath);
		}
		 resultMap.put("message", messageString);
		return resultMap;

	
	}

	/**
	 * 检查文本值是否相等
	 * 
	 * @param webContent
	 *            页面值
	 * @param content
	 *            目标值
	 * @param checkmessage
	 *            校验信息
	 * @return boolean
	 * @throws Exception 
	 */
	public HashMap<String, String> checkequals(String webContent, String expectContent,String remark,HashMap<String, String> resultMap,String caseID) throws Exception {
		
	    String screenpath = null;
	    OpWebDriver opWebDriver = new OpWebDriver();
        String messageString  =resultMap.get("message");
        if(messageString==null){
        	messageString="";
        }
 		if (webContent.equals(expectContent)) {

			 //webCheck(true, remark);
			 messageString =messageString +"正确_"+remark+"\n";
		     screenpath = WebInit.testDir + mainCaseId+File.separator +"right_" + remark + ".png";
		     opWebDriver.screenShot(this.webDriver,screenpath);

		}else{
			 //webCheck(false, remark);
			 messageString =messageString +"错误_caseid:"+caseID+"_"+remark+"\n";
			 messageString=messageString+"当前值:"+webContent+"\n";
			 messageString=messageString+"期望值:"+expectContent+"\n";
			 resultMap.put("result", "错误");
		     screenpath = WebInit.testDir + mainCaseId+File.separator +"wrong_" + remark + ".png";
		     opWebDriver.screenShot(this.webDriver,screenpath);
		}
		 resultMap.put("message", messageString);
		 
		return resultMap;

	
	}

	
	
	
	
	
	
	
	
	
	
	
	
}





