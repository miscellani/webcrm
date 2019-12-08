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
import oracle.net.aso.i;

public class WebInit {
	//运行参数
	//计算超时时间
	//并行线程数，默认5
	public static int userNum =5;
	
	public static BlockingQueue<String> logQueue;
	public static   AtomicInteger currentNum=new AtomicInteger(0);
	public static  AtomicInteger threadUserNum = new AtomicInteger(0);
	
	public static String filterType;
	public static String workSpace;
	//public String fileName = "autocase.xls";
	//public String caseFilePath = "ConfigConst.workspace" + File.separator + fileName;
	//public String resultFilePath = "ConfigConst.workspace" + File.separator + "result_" + fileName;
	//public String reportFilePath = "ConfigConst.workspace" + File.separator + "report_" + fileName;


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
	

	@SuppressWarnings({ "finally", "unchecked" })
	public void running(HashMap map,String className, String methodName, String params) throws Exception {
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

		
		
		// 1
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
			
			//更新当前case数据
			dbOperate.delData("delete web_case_current t where caseid='"+caseId+"'");
			dbOperate
					.insertData("insert into web_case_current select t.* from web_case t where result='已发布' and caseid='"+caseId+"'");
			dbOperate
			.insertData("update web_case_current set result='未执行' where caseid='"+caseId+"'");

			//先删除原文件夹	
			String caseDirLastFail= WebInit.testDir+caseId+File.separator;
			FileUtil fileUtil = new FileUtil();
			fileUtil.delDir(new File(caseDirLastFail));

		    File casefoldertemp = new File(caseDirLastFail);
		    casefoldertemp.mkdir();
			
			


			 webDriver = opWebDriver.openRBrower(ipConfig[0],ipConfig[1]);
				//LoginPage loginPage = new LoginPage(webDriver);
				//loginPage.locateWeb();
				opWebDriver.get(webDriver,WebInit.mWebUrl);
				
                cases.com.LoginPage loginPage = new cases.com.LoginPage(webDriver);
				loginPage.login(WebInit.pointLogin,WebInit.opId, WebInit.opPass);
				String paramValue = (String) map.get("paramValue");
				hashMap = caseHandle.reflectMethod(webDriver, module,menuCode, caseCode, paramValue,caseId);
    			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				hashMap.put("message", dataUtil.getTrace(e));

			     String screenpath = WebInit.testDir + caseId+File.separator +"执行操作步骤异常"+ ".png";
	    			hashMap.put("result", "失败");
	                Log.info("caseId="+caseId+":调试用例异常,"+dataUtil.getTrace(e));
				     opWebDriver.screenShot(webDriver,screenpath);

			} finally {

				String endDate = dateUtil.nows();
                ArrayList<Integer> fee = dateUtil.getFeeTime(startDate, endDate);
                webCaseBean.setCaseId(Integer.parseInt(caseId));
               // webCaseBean.setFee( fee.get(2)*60*1000+fee.get(3)*1000+fee.get(4));

                //修改为秒
                webCaseBean.setFee( fee.get(2)*60);

                String result = (String)hashMap.get("result");
                webCaseBean.setResult(result);
                webCaseBean.setSaveData((String)hashMap.get("saveData"));

	            String startdate= new DateUtil().now();
                webCaseBean.setRemark("调试时间："+startdate+"\n"+(String)hashMap.get("message"));
                
                //why要更新到初始，垃圾
				/*dbOperate.delData("delete web_case_current t where caseid='"+caseId+"'");
				dbOperate
						.insertData("insert into web_case_current select t.* from web_case t where caseid='"+caseId+"'");
				*/
				webCaseExecuteDao.updateExecuteCase(webCaseBean);
                
                if(!result.equals("成功")){
    				System.out.println("caseId="+caseId+":调试用例结束,ip="+ipConfig[0]+",browertype="+ipConfig[1]);
    	            Log.info("caseId="+caseId+":执行步骤结果为"+result+",不再进行校验阶段验证");
    	            Log.info("caseId="+caseId+":调试用例结束,ip="+ipConfig[0]+",browertype="+ipConfig[1]);

                	return;
                }

			}
	            Thread.sleep(15000);
	           // hashMap.put("result", "正确");
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
		DateUtil dateUtils = new DateUtil();
		CaseHandle caseHandle = new CaseHandle();
		OpWebDriver opWebDriver = new OpWebDriver();
		DateUtil dateChaUtils = new DateUtil();
		ConfigDbOperate dbOperate = new ConfigDbOperate();
		 if(filterType.equals("all")||filterType.equals("filter")){
	            Log.info("重新执行开始----------");

			 

				String datenum;
				// 处理原文件夹
				String oldDir = null;
				ArrayList<String> arrayList = dbOperate.searchStrings("select currentdir from web_Control t");
				if (arrayList.size() > 0) {
					oldDir = arrayList.get(0);

				}

				if (!(oldDir == null || oldDir.equals(""))) {

					String[] dirName = oldDir.split("\\\\");
					File filetemp = new File(oldDir);
					filetemp.renameTo(
							new File(workSpace + File.separator + dirName[dirName.length-1] + "_his" + File.separator));

				}
	            Log.info("处理原工作目录---------");

				// 创建新的文件夹
				datenum = dateChaUtils.nowToNum();
				testDir = workSpace + File.separator + "autotest_" + datenum + File.separator;
				File testfolder = new File(testDir);
				testfolder.mkdirs();
	            Log.info("创建新工作目录---------");

				// 更新控制表数据到最新，包括结束标识0开始时间等等
				if (dbOperate.searchStrings("select stop from web_control").size() > 0) {
					
					//如果是全新执行
					if(filterType.equals("all")){
					//新增将当轮执行CASE转移到历史表
					dbOperate.insertData(" insert into web_case_exehis select t1.controlid,t2.* from  web_control t1,web_case_current t2 ");
					//新增更新历史执行数据路径
					dbOperate.updateData("update web_control set currentdir= SUBSTR (currentdir,0,length(currentdir)-1)||'_his\\'");
					dbOperate.insertData("insert into web_control_his select * from web_control");
					}
					String id = dbOperate.searchString("select web_control_sequence.nextval from dual");
					dbOperate.updateData("update web_control t set controlid='"+id+"' ,stop=0,currentdir='" + testDir + "',startdate='"
							+ startDate + "',enddate='' , finish=0");
					
					
				} else {
					dbOperate.insertData(
							"insert into web_control values (web_control_sequence.nextval,'"+testDir+"','0','"+ startDate+ "','','','0')");
				}
	            Log.info("更新控制数据---------");


		
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
		///操作等待结束	
			//判断控制是否结束运行
			String stop="";
			while (true) {
				Thread.sleep(5000 * 2);
				if (getCurrentThreadNum().equals("0")||getCurrentThreadNum().contains("-")) {
					System.out.println("执行线程已全部循环退出");
					break;

				}
				

				try {
					
					stop= dbOperate.searchStrings("select t.stop from web_control t").get(0);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(stop.equals("1")){


					break;
					
				}
				
				
	            Log.info("业务操作执行中....当前执行用户数：");
			    Log.info(""+WebInit.getCurrentThreadNum()+"个\n");
		        Log.info("当前待执行CSAE数："+WebInit.getCurrentCaseNum()+"个\n");



				//System.out.println(getCurrentThreadNum());
	           // Log.info(getCurrentThreadNum());
	            
			}		
			
			
			
			
            Log.info("业务操作阶段结束---------");
            Log.info("开始筛选校验case阶段---------");

			///执行阶段完成后，进行数据校验阶段
			//如果是继续执行方式，需要重新获取当前成功状态的CASE ,失败和错误的CASE不校验
			if(filterType.equals("continueX")){
				
				 lists = new ArrayList();
				 HashMap<String, String> maps = new HashMap<String, String>();
				 maps.put("result", "成功");
				 lists = caseHandle.collectCase("filter", maps);

                currentNum.set(lists.size());
    			 allDivededCaseLists = caseHandle
    					.divideCaseListe(userNum, lists);
			}
			

			//前台修改为停止？？
			String finish =dbOperate.searchStrings("select t.finish from web_Control t").get(0);
			//配置不校验
			String isCheck = WebConfigDao.getRunParam("check");
			if( (!finish.equals("1")) &&(!isCheck.equals("0")) ){
				
				//等但3分钟订单竣工
				Log.info("-------1分钟后启动校验程序------");
				Thread.sleep(1000*60);
				
				
				
                currentNum.set(lists.size());
				this.threadUserNum.set(userNum);
				Log.info("本次收集校验case数:" + currentNum.get());

			
				
	            Log.info("数据校验阶段开始---------");

			for (int r = 0; r < userNum; r++) {
				ThreadCheck threadCheck = new ThreadCheck(
						allDivededCaseLists.get(r));
				Thread t = new Thread(threadCheck);
				t.start();
			}
			
			
			
			//线程全部结束才更新状态
			 stop="0";
			while (true) {
				Thread.sleep(1000 * 2);
			//	stop =dbOperate.searchStrings("select t.stop from web_Control t").get(0);
				if (getCurrentThreadNum().equals("0")||getCurrentThreadNum().contains("-")) {
					System.out.println("校验线程已全部循环退出");

					break;

				}
				
				
				try {
					
					stop= dbOperate.searchStrings("select t.stop from web_control t").get(0);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(stop.equals("1")){


					break;
					
				}
				
				
			System.out.println("有校验线程尚未关闭");
            Log.info("业务数据校验执行中...");

			System.out.println(getCurrentThreadNum());
            Log.info(getCurrentThreadNum());

			}	
			
            Log.info("数据校验阶段结束---------");
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
			    
			
				while (true) {
				Thread.sleep(1000 * 2);
				if (getCurrentThreadNum().equals("0")||getCurrentThreadNum().contains("-")) {
					break;

				}
				
				try {
					
					stop= dbOperate.searchStrings("select t.stop from web_control t").get(0);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(stop.equals("1")){


					break;
					
				}
				
				
				
			}
			Log.info("本轮测试结束---------");
			System.out.println("---测试结束");
			
			
			
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
