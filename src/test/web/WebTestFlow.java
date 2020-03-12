package test.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import test.config.ConfigConst;
import test.config.dao.WebConfigDao;
import test.init.CaseHandle;
import test.init.ThreadCheck;
import test.init.ThreadRun;
import test.mail.Sendmail;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.com.LoginPage;
import test.web.cases.dao.WebCaseExecuteDao;
import base.com.zl.db.ConfigDbOperate;
import base.com.zl.log.Log;
import base.com.zl.selenium.OpWebDriver;
import base.com.zl.utils.DataUtil;
import base.com.zl.utils.DateUtil;
import base.com.zl.utils.FileUtil;


public class WebTestFlow {
	//运行参数
	//计算超时时间
	//并行线程数，默认5
	public static int userNum =5;
	
	public static BlockingQueue<String> logQueue;
	public static   AtomicInteger currentNum=new AtomicInteger(0);
	public static  AtomicInteger threadUserNum = new AtomicInteger(0);
	public static String filterType;
	public static String workSpace;
	public static int currentLine = 0;
	public static String caseresult = "";
	public static String testDir = "";
	public static String year = new DateUtil().nowToYear();
	public static String month = new DateUtil().nowToMonth();
	public static String pWebUrl = "";
	public static String mWebUrl ="";
	public static String pointLogin = "";
	public static String soaUrl = "";
	public static String orgId = "";
	public static String opId = "";
	public static String opPass = "";

	private String[] ipConfigs;
	


	public void uatRunning(HashMap map,String className, String methodName, String params) throws Exception {
		logQueue = new LinkedBlockingQueue<String>(100);
		WebConfigDao WebConfigDao = new WebConfigDao();
		HashMap<String, String> runMap = WebConfigDao.getRunParams();

		DOMConfigurator.configure("Log4j.xml");
		
		String path = System.getProperty("catalina.home");
		
		if(path!=null){
			// workSpace = path+File.separator+"webapps"+File.separator+"webcrm"+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+"test"+File.separator+"run";
			 workSpace = path+File.separator+"webapps"+File.separator+"webcrm"+File.separator+"resource"+File.separator+"run";

		}else{
			workSpace = System.getProperty("user.dir")+File.separator+"html"+File.separator+"resource"+File.separator+"run";

		}
		
		
		HashMap enivMap = new FileUtil().getEnvironmentConfig("run");
		String env = (String) enivMap.get("env");


		pWebUrl = (String) enivMap.get("pWebUrl");
		mWebUrl = (String) enivMap.get("mWebUrl");
		String dbUser = (String) enivMap.get("dbUser");
		String[] dbUserStrings = dbUser.split("\\|");

		orgId = (String) enivMap.get("orgId");
		pointLogin = (String) enivMap.get("pointLogin");
		opId = (String) enivMap.get("opId");
		opPass = (String) enivMap.get("opPass");

		filterType = (String) map.get("filterType");	
        String prov=(String) enivMap.get("prov");
		String smoke = (String) map.get("smoke");
		
		DateUtil dateUtil = new DateUtil();
		String startDate = dateUtil.nows();
		String startDate_ = dateUtil.now();

		
		
		// 单调模式
		Log.info("过滤条件:"+filterType);
		if (filterType==null||filterType.equals("choice")) {
		
			CaseHandle caseHandle = new CaseHandle();
            WebCaseBean webCaseBean = new WebCaseBean();
			WebCaseExecuteDao webCaseExecuteDao = new WebCaseExecuteDao();
			ConfigDbOperate dbOperate = new ConfigDbOperate();
			DataUtil dataUtil  = new DataUtil() ;
			HashMap<String, String> hashMap = new HashMap<>();
			OpWebDriver opWebDriver = new OpWebDriver();
			WebDriver webDriver = null;
			String module = (String) map.get("module");
			String menuCode = (String) map.get("menuCode");
			String caseCode = (String) map.get("caseCode");
			String caseId = (String) map.get("caseId");
			String ip = (String) map.get("ip");
			
            String[] ipConfig= ip.split("\\,");
			try{



			
            Log.info("caseId="+caseId+":调试用例开始,ip="+ipConfig[0]+",browertype="+ipConfig[1]);
			

			//获取当前testdir
			testDir = dbOperate.searchString("select currentdir from web_control ");
			
/*			//更新当前case数据
			dbOperate.delData("delete web_case_current t where caseid='"+caseId+"'");
			dbOperate
					.insertData("insert into web_case_current select t.* from web_case t where result='已发布' and caseid='"+caseId+"'");
			dbOperate
			.insertData("update web_case_current set result='未执行' where caseid='"+caseId+"'");*/

			//更新单条CASE执行数据
			dbOperate.updateData("update web_case_current set result='未执行',savedata='',remark='',fee='' where caseid='"+caseId+"'");
			
			
			
			
			//先删除原文件夹	
			String caseDirLastFail= WebTestFlow.testDir+caseId+File.separator;
			FileUtil fileUtil = new FileUtil();
			fileUtil.delDir(new File(caseDirLastFail));

		    File casefoldertemp = new File(caseDirLastFail);
		    casefoldertemp.mkdir();
			
			


			 webDriver = opWebDriver.openRBrower(ipConfig[0],ipConfig[1]);

				opWebDriver.get(webDriver,WebTestFlow.mWebUrl);
				
                cases.com.LoginPage loginPage = new cases.com.LoginPage(webDriver);
				loginPage.login(WebTestFlow.pointLogin,WebTestFlow.opId, WebTestFlow.opPass);
				String paramValue = (String) map.get("paramValue");
				hashMap = caseHandle.reflectMethod(webDriver, module,menuCode, caseCode, paramValue,caseId);
    			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				hashMap.put("message", dataUtil.getTrace(e));

			     String screenpath = WebTestFlow.testDir + caseId+File.separator +"执行操作步骤异常"+ ".png";
	    			hashMap.put("result", "失败");
	                Log.info("caseId="+caseId+":调试用例异常,"+dataUtil.getTrace(e));
				     opWebDriver.screenShot(webDriver,screenpath);

			} finally {

				String endDate = dateUtil.nows();
                ArrayList<Integer> fee = dateUtil.getFeeTime(startDate, endDate);
                webCaseBean.setCaseId(Integer.parseInt(caseId));
                //修改为秒
                webCaseBean.setFee( fee.get(2)*60);

                String result = (String)hashMap.get("result");
                webCaseBean.setResult(result);
                webCaseBean.setSaveData((String)hashMap.get("saveData"));

	            String startdate= new DateUtil().now();
                webCaseBean.setRemark("调试时间："+startdate+"\n"+(String)hashMap.get("message"));                
				webCaseExecuteDao.updateExecuteCase(webCaseBean);
                
                if(!result.equals("成功")){
    				System.out.println("caseId="+caseId+":调试用例结束,ip="+ipConfig[0]+",browertype="+ipConfig[1]);
    	            Log.info("caseId="+caseId+":执行步骤结果为"+result+",不再进行校验阶段验证");
    	            Log.info("caseId="+caseId+":调试用例结束,ip="+ipConfig[0]+",browertype="+ipConfig[1]);

                	return;
                }

			}
			  //15秒后校验CASE
	            Thread.sleep(15000);

	            try {
					hashMap = caseHandle.reflectMethod(webDriver, module,menuCode, caseCode+"Check",null,caseId);

				} catch (Exception e) {
					// TODO: handle exception
		            hashMap.put("result", "校验报错");
		            
		            hashMap.put("message", dataUtil.getTrace(e));
		            Log.info("caseId="+caseId+":用例校验报错:\n"+hashMap.get("message")+"\n");
				}
	            
	            
	            
	            
	            

				String remark = webCaseBean.getRemark();
				webCaseBean.setRemark(hashMap.get("message")+remark+"\n");
				webCaseBean.setResult(hashMap.get("result"));
				webCaseExecuteDao.updateExecuteCase(webCaseBean);
	            Log.info("caseId="+caseId+":校验步骤结束");
	            Log.info("caseId="+caseId+":调试用例结束,结果="+hashMap.get("result"));

				return;
				
		}
		
		
		//冒烟模式
		
		DateUtil dateUtils = new DateUtil();
		CaseHandle caseHandle = new CaseHandle();
		DateUtil dateChaUtils = new DateUtil();
		ConfigDbOperate dbOperate = new ConfigDbOperate();
		
		//初始数据没有，新增一条
		if (dbOperate.searchStrings("select stop from web_control").size() < 1) {
			dbOperate.insertData(
					"insert into web_control values (web_control_sequence.nextval,'"+testDir+"','0','"+ startDate+ "','','','0')");
		}
		
		if(filterType.equals("all")||filterType.equals("filter")){
            Log.info("重新执行开始----------");
			// 创建新的文件夹
            String datenum;
			datenum = dateChaUtils.nowToNum();
			testDir = workSpace + File.separator + "autotest_" + datenum + File.separator;
			File testfolder = new File(testDir);
			testfolder.mkdirs();
            Log.info("创建新工作目录---------");
            //原执行计划挪历史
			dbOperate.insertData("insert into web_control_his select * from web_control");
			//更新新执行计划
			String id = dbOperate.searchString("select web_control_sequence.nextval from dual");
			dbOperate.updateData("update web_control t set controlid='"+id+"' ,stop=0,currentdir='" + testDir + "',startdate='"
					+ startDate + "',enddate='' , finish=0");
		}else{			
			 
	            Log.info("增量执行开始---------");

				// 更新控制表，给过滤执行，继续执行用
				dbOperate
						.updateData("update web_control t set stop='0',finish='0'"); 
				// 从总控表中获取当前执行总路径，给到testdir
				String oldDir = dbOperate.searchStrings("select currentdir from web_control t").get(0);
				testDir = oldDir;

		 } 
		 
		 
		 
		 
		////收集执行CASE
			 
			ArrayList lists = new ArrayList();
			lists = caseHandle.collectCase(filterType, map);
            currentNum.set(lists.size());
            
    		String ipsConfig = runMap.get("threadUser");
    		ipConfigs = null;
        	//早上冒烟不传此参数
        	if(smoke==null){
    		
        		String ipsConfigT= ipsConfig.split("\\|")[0];
             ipConfigs = ipsConfigT.split("\\|");
        	}else{       		
        	 ipConfigs = ipsConfig.split("\\|");
        		      		
        	}
            //设置并行ip
			if(map.get("caseId")!=null){
				userNum=1;
				
			}else{
				userNum= ipConfigs.length;
			}
			Log.info("本次收集执行case数:" + currentNum.get());

			
			//查询运行配置获取并行数
			ArrayList<ArrayList> allDivededCaseLists = caseHandle
					.divideCaseListe(userNum, lists);			
			
			Log.info("CASE分配完成，用户数:" + userNum+"个");
			this.threadUserNum.set(userNum);
			
			
            Log.info("业务操作阶段开始---------");

	    ////操作CASE阶段
			for (int r = 0; r < userNum; r++) {
				ThreadRun threadRun = new ThreadRun(ipConfigs[r],
						allDivededCaseLists.get(r));
				Thread t = new Thread(threadRun);
				t.start();
			}			

           //一分钟后启动校验流程
			Thread.sleep(1000*60);
			String stop="";
			//获取是否校验配置
			String isCheck = WebConfigDao.getRunParam("check");
			
			if(isCheck.equals("0")){
				//不校验流程只需要等待执行阶段结束
				while (true) {
					//每10秒钟获取一次执行状态
					Thread.sleep(1000 * 10);
		            String CurrentThreadNum =  getCurrentThreadNum();
					stop= dbOperate.searchStrings("select t.stop from web_control t").get(0);
		            if ( CurrentThreadNum.equals("0")||CurrentThreadNum.contains("-")||stop.equals("1")) {
			            Log.info("执行阶段结束");
			            break;
		            }

					//执行步骤仍然在进行中
		            Log.info("业务操作执行中....当前执行用户数：");
				    Log.info(""+WebTestFlow.getCurrentThreadNum()+"个\n");
			        Log.info("当前待执行CSAE数："+WebTestFlow.getCurrentCaseNum()+"个\n");
            
				}
			}else{
				//走校验流程
				String cancelflag="0";
				while (true) {
					
					//执行步骤仍然在进行中
		            Log.info("业务操作执行中....当前执行用户数：");
				    Log.info(""+WebTestFlow.getCurrentThreadNum()+"个\n");
			        Log.info("当前待执行CSAE数："+WebTestFlow.getCurrentCaseNum()+"个\n");	
			        
					//每10秒钟获取一次校验数据
					Thread.sleep(1000 * 10);
		            Log.info("开始筛选校验case阶段---------");
		            String CurrentThreadNum =  getCurrentThreadNum();
		            stop= dbOperate.searchStrings("select t.stop from web_control t").get(0);	            		            
					//如果执行阶段已经完成，本次获取校验数据后退出校验
					if (getCurrentThreadNum().equals("0")||getCurrentThreadNum().contains("-")||stop.equals("1")) {
						System.out.println("执行CASE步骤已完成，校验步骤接近尾声");
						cancelflag="1";
					}
					
					//-------------调用校验
					//获取当前状态为成功的CASE
					 lists = new ArrayList();
					 HashMap<String, String> maps = new HashMap<String, String>();
					 maps.put("result", "成功");
					 lists = caseHandle.collectCase("filter", maps);

						for (int j = 0; j < lists.size(); j++) {

							try {

								caseHandle.checkCases((String) lists.get(j));

							} catch (Exception e1) {
								// TODO Auto-generated catch block

								Log.info("校验case异常_" + (String) lists.get(j) + "\n");

								e1.printStackTrace();
							}


						}						    			
				
						
						
						
						
						
						
						
						
					if(cancelflag.equals("1")){
			            Log.info("校验阶段结束---------");
						break;
					}
				
		            
				}
			
			}
			


			
			
			
			
			
			dbOperate
			.updateData("update web_control t set finish='1' ,stop='1'");

			// 处理报告
            Log.info("处理测试报告---------");

			String endDate = dateUtils.nows();
			String endDate_ = dateUtils.now();

			//收集结果保存到数据库,如果不是全量执行则需要增量period周期
            //数据库求时间和
			int period=0;
			if(filterType.equals("all")){
				   ArrayList<Integer> feeArrayList = dateUtils.getFeeTime(startDate, endDate);
                     period = feeArrayList.get(1)*60 + feeArrayList.get(2);
			}else{
				WebCaseExecuteDao caseInfoExecuteDao = new WebCaseExecuteDao();
			        String resultString = caseInfoExecuteDao.getCurrentResultRemarks()
			        		.split(",")[0];
			        
			        if(resultString==null){
			        	resultString="0";
			        }else{
			        	resultString=resultString.split(",")[0];
			        }
			        period= period+ Integer.parseInt(resultString);
			}
			
			
				

				int allNum=0;
				//请求结果数据库求值
				int idleNum=0;
				int successNum=0;
				int failNum=0;

				//最终执行结果数
				int correctNum=0;
				int wrongNum=0;


				WebCaseExecuteDao caseInfoExecuteDao = new WebCaseExecuteDao();
                 
                 ArrayList<HashMap> sumResult = caseInfoExecuteDao.getExecuteResult();
                 if( sumResult!=null&& (sumResult.size()>0) ){
 				    allNum = Integer.parseInt((String) sumResult.get(0).get("总数")) ;
				    idleNum= Integer.parseInt((String) sumResult.get(0).get("未执行")) ;
				    successNum= Integer.parseInt((String) sumResult.get(0).get("成功")) ;
				    failNum= Integer.parseInt((String) sumResult.get(0).get("失败")) ;
				    correctNum= Integer.parseInt((String) sumResult.get(0).get("正确"));
				    wrongNum= Integer.parseInt((String) sumResult.get(0).get("错误"));
                 }


			   String remarks = period+","+allNum+","+idleNum+","+successNum+","+ failNum+","+ correctNum+","+ wrongNum;
			    
				dbOperate
				.updateData("update web_control t set remarks='"+remarks+"',enddate='"
						+ endDate + "'");
			    
			

			
			
			
			////判断发送邮件
			String mail = WebConfigDao.getRunParam("mail");
			String[] mails = mail.split("\\|");
			if(mails[0].equals("0")){
				return;				
			}
			
			////获取发送人信息
			HashMap configMap = new FileUtil().getEnvironmentConfig("config");
			String mailSend = (String) configMap.get("mail");
			String[] mailSends = mailSend.split("\\|");
			String mailSendAdd = mailSends[0];
			String mailSendUser = mailSends[1];
			String mailSendPass = mailSends[2];
			//获取收件人和抄送人

			String mailSendTo ;
			String mailSendCC ;
			
			mailSendTo = mails[1];
            ArrayList sendToList = new ArrayList<>();
            if((mailSendTo!=null)||(!mailSendTo.isEmpty())){   
            	
            	
            	//早上冒烟不传此参数
            	if(smoke!=null){
            		sendToList.add(mailSendAdd);
               }else{
            		
            	
			    String[] mailSendTos = mailSendTo.split("\\;");
                     for(String to:mailSendTos){            	
            	        sendToList.add(to);
                     }
                }
             }
	
            mailSendCC =  mails[2];
            ArrayList sendCCList = new ArrayList<>();

            if( ((mailSendCC!=null)||(!mailSendCC.isEmpty()))&&(smoke==null)){
            	
            
			  String[] mailSendCCs = mailSendCC.split("\\;");
              for(String cc:mailSendCCs){            	
            	 sendCCList.add(cc);
              }
              }

			Log.info("收集邮件地址---------");           
            //获取邮件主题
            String subject ="";  		
            //获取正文
            String mailContent="\n,冒烟时间 : "+startDate_+"--"+endDate_+"\n";
            
            
            //邮件的标题
            String now = dateUtil.now();
            subject=env+"--"+prov+"CRM页面自动化冒烟测试结果-"+now;
            //发送邮件
            Sendmail sendmail = new Sendmail();
            sendmail.sendSimpleMail(map,mailSend, sendToList, sendCCList, subject, mailContent);
            
			
            
	}

	
	
	public void factoryRunning(HashMap map,String className, String methodName, String params){
		
	}
	
	
	
	
	public  static synchronized  void putLogQueue(String message){
		
/*		try {
			logQueue.put(new DateUtil().now()+"--"+message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		if(! (logQueue.offer(new DateUtil().now()+"--"+message) ) ){
			logQueue.remove();
			logQueue.offer(new DateUtil().now()+"--"+message);
		}
		
		
	}
	
	public static synchronized  void downCurrentCaseNum(){
		
		currentNum.decrementAndGet();
	}
	
	public static synchronized void downCurrentThreadNum(){
		
		
		threadUserNum.decrementAndGet();
	}

	public static synchronized String getCurrentCaseNum(){
		
		return currentNum.toString();

	}
	
	public static synchronized String getCurrentThreadNum(){
		return threadUserNum.toString();
		
		
	}
	
	
	
}
