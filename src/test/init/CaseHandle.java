package test.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.tools.JavaCompiler.CompilationTask;
import test.config.dao.WebConfigDao;
import test.web.WebInit;
import test.web.cases.bean.WebCaseBean;

import test.web.cases.dao.WebCaseDao;
import test.web.cases.dao.WebCaseExecuteDao;
import base.com.zl.db.ConfigDbOperate;
import base.com.zl.db.DbOperate;
import base.com.zl.log.Log;
import base.com.zl.selenium.OpWebDriver;

import base.com.zl.utils.DataUtil;
import base.com.zl.utils.DateUtil;
import base.com.zl.utils.FileUtil;
import javax.tools.*;

public class CaseHandle {
	
	public HashMap<String, String> reflectMethod(WebDriver webDriver,String module,String cname, String mname, String inputparam,String caseId)
			throws Exception {

		HashMap<String, String> resultMap = new HashMap<>();

		//cname = "test.web.cases."+module+ "."+cname;
		cname = "cases."+module+ "."+cname;

		Class<?> testclass = Class.forName(cname);
		//Object Testobj = testclass.getConstructor().newInstance();
		Object Testobj;
/*		if(webDriver==null){
			 Testobj = testclass.getConstructor().newInstance();

		}else{*/
			 Testobj = testclass.getConstructor(WebDriver.class,String.class,String.class).newInstance(webDriver,caseId,WebInit.testDir);

		//}
		if ( (inputparam==null)||(inputparam.equals("")) ) {
			Method testmethod = testclass.getMethod(mname);
			resultMap = (HashMap<String, String>)testmethod.invoke(Testobj);
		} else {
			
/*			if(mname.equals("menuCheck")){
				inputparam+="|"+caseId;
				
			}*/
			
			
			String[] paramvalue = inputparam.split("\\|");
			int parnum = paramvalue.length;

			Class[] mtype = new Class[parnum];
			for (int i = 0; i < parnum; i++) {
				mtype[i] = String.class;

			}
			Method testmethod = testclass.getMethod(mname, mtype);

			Object[] valueobj = new Object[parnum];
			for (int i = 0; i < parnum; i++) {

				valueobj[i] = paramvalue[i];
			}

			resultMap = (HashMap<String, String>) testmethod.invoke(Testobj, valueobj);
		}
	     return	resultMap;

	}
	
	
	
	/**
	 * 获取可以校验的CASE数据
	 * @return
	 */
	public ArrayList<String> getCheckCases(){
		
		//--新增号码使用表 caseid  number  使用时间
		//--在查询号码后插入 
		//在最终CASE执行成功后，更新使用时间
		//号码进入菜单失败重新登录时怎么处理？可能1个CASE关联多个号码
		//在执行失败或报错时通过CASEID将号码挪出

		//在查询可校验数据是关联号码使用表 ，时间大于1分钟，CASE执行状态为成功的数据，即为可校验的数据列
       
		//校验之后通过CASEID将号码从号码中挪出
		
		return null;
		
	}
	
	
	public WebDriver excuteCases(WebDriver webDriver,String ip,String name,String caseId) throws Exception {

		WebCaseExecuteDao webCaseExecuteDao = new WebCaseExecuteDao();
		WebCaseBean webCaseBean = webCaseExecuteDao.getExecutCaseInfo(caseId);
		OpWebDriver opWebDriver= new OpWebDriver();
		String module = webCaseBean.getModule(); 
		String businessMenu =webCaseBean.getMenuName();
		String classname = webCaseBean.getMenuCode(); 
		String interfacename = webCaseBean.getCaseCode();
		String paramValue = webCaseBean.getParamValue();

		DateUtil dateUtil =new DateUtil();
		String startTime= dateUtil.nows();
		


		ConfigDbOperate configDbOperate = new ConfigDbOperate(caseId);
		// 初始化CASE目录和DB结果		
		//先删除原文件夹		
			String caseDirLastFail= WebInit.testDir+caseId+File.separator;
			FileUtil fileUtil = new FileUtil();
			fileUtil.delDir(new File(caseDirLastFail));		
		String caseDir = WebInit.testDir + caseId + File.separator;
		File casefoldertemp = new File(caseDir);
		casefoldertemp.mkdir();
		
		//更新当前case数据
		configDbOperate.delData("delete web_case_current t where caseid='"+caseId+"'");
		configDbOperate
				.insertData("insert into web_case_current select t.* from web_case t where result='已发布' and caseid='"+caseId+"'");
		configDbOperate
		.insertData("update web_case_current set result='未执行' where caseid='"+caseId+"'");
		
		
		
		
		
        Log.info( "--------------执行CASE:" + module + "_" + businessMenu + "_"
				+ caseId + "开始-------------");



		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("result", "成功");
		hashMap.put("saveData", "");
		StringBuffer stringBuffer =  new StringBuffer();
		stringBuffer.append(module+ "_" + businessMenu + "_"+ caseId + "操作开始\n"+"开始时间："+startTime);

		HashMap reTurnMap = new HashMap<>();

		try {
			reTurnMap = this.reflectMethod(webDriver,module,classname, interfacename, paramValue,caseId);
			stringBuffer.append(reTurnMap.get("message"));
			hashMap.put("saveData", (String) reTurnMap.get("saveData"));

	

		} catch (Exception e) {
		     String screenpath = caseDir +"执行操作步骤异常 "+ ".png";
		     opWebDriver.screenShot(webDriver,screenpath);
			e.printStackTrace();
			DataUtil dataUtil = new DataUtil();
			hashMap.put("result", "失败");
			stringBuffer.append(module+ "_" + businessMenu + "_"+ caseId + "操作异常\n");
			stringBuffer.append(dataUtil.getTrace(e)+"\n");

            }
		//}

		Log.info("--------------执行CASE:" + module + "_" + businessMenu + "_"
				+ caseId + "结束-------------");
		
		String endTime= dateUtil.nows();
		stringBuffer.append(module + "_" + businessMenu + "_"+ caseId + "操作结束\n"+"开始时间："+endTime);
		//保存执行结果
		//------更新CASE为真实数据到数据库 请求头，请求体，请求参数，返回参数，返回报文123 ，访问结果


		webCaseBean.setResult((String)reTurnMap.get("result"));
		webCaseBean.setSaveData( ((String)reTurnMap.get("saveData") ).replace(" ", ""));
		webCaseBean.setRemark(stringBuffer.toString());

		ArrayList<Integer> fee = dateUtil.getFeeTime(startTime, endTime);
		webCaseBean.setFee(fee.get(2)*60+fee.get(3));
		webCaseExecuteDao.updateExecuteCase(webCaseBean);
		
		
		return webDriver;
	}



	
	public void checkCases(String caseId) throws Exception {

		WebCaseExecuteDao webCaseExecuteDao = new WebCaseExecuteDao();
		WebCaseBean webCaseBean = webCaseExecuteDao.getExecutCaseInfo(caseId);
		String module = webCaseBean.getModule(); 
		String businessMenu =webCaseBean.getMenuName();
		String classname = webCaseBean.getMenuCode(); 
		String interfacename = webCaseBean.getCaseCode();
		String opResult = webCaseBean.getResult();
		String remark = webCaseBean.getRemark();

		StringBuffer stringBuffer = new StringBuffer();
		
		if(remark!=null){
			stringBuffer.append(remark);
		}
        if(opResult.equals("失败")||opResult.equals("正确")||opResult.equals("错误")){
    		Log.info("---caseId:"+caseId+"---执行阶段结果为:"+opResult+",不进行校验");
            return;	
        }
		
		
		DateUtil dateUtil =new DateUtil();
		String startTime= dateUtil.nows();
		



			


        Log.info( "--------------校验CASE:" + module + "_" + businessMenu + "_"
				+ caseId + "开始-------------");

		stringBuffer.append(module + "_" + businessMenu + "_"+ caseId + "校验开始\n");




		HashMap<String, String> reTurnMap = new HashMap<>();

		try {
			reTurnMap = this.reflectMethod(null,module,classname, interfacename+"Check", null,caseId);
			stringBuffer.append(reTurnMap.get("message"));


		} catch (Exception e) {
			e.printStackTrace();
			DataUtil dataUtil = new DataUtil();
			reTurnMap.put("result", "错误");

			reTurnMap.put("message", dataUtil.getTrace(e)+"\n");
			stringBuffer.append(module + "_" + businessMenu + "_"+ caseId + "校验异常\n");
			stringBuffer.append(dataUtil.getTrace(e)+"\n");

			// TODO: handle exception




		}

		Log.info("--------------校验CASE:" + module + "_" + businessMenu + "_"
				+ caseId + "结束-------------");
		stringBuffer.append(module + "_" + businessMenu + "_"+ caseId + "校验结束\n");

		



		webCaseBean.setResult(reTurnMap.get("result"));	
		webCaseBean.setRemark(stringBuffer.toString());
		String endTime= dateUtil.nows();
		ArrayList<Integer> fee = dateUtil.getFeeTime(startTime, endTime);
        int executeFee = webCaseBean.getFee();		

        webCaseBean.setFee( fee.get(2)*60);
		webCaseExecuteDao.updateExecuteCase(webCaseBean);
        //将校验后的数据移除测试数据表
		DbOperate dbOperate = new DbOperate();
		dbOperate
		.updateData("delete web_test_data t where case_id='"+caseId+"'");
	
	}
	


	public String[] getThreadUser(String remoteIp) {
		return remoteIp.split("\\|");

	}

	/**
	 * 切断分配法
	 * 
	 * @param threadNum
	 * @param caselist
	 * @return
	 * @throws Exception
	 */
	public ArrayList divideCaseLists(int threadNum, ArrayList caselist)
			throws Exception {
		ArrayList lists = new ArrayList();
		int casesnum = caselist.size();
		int caseStartNum = 0;
		int caseEndNum = 0;
		int chuShu = casesnum / threadNum;
		for (int t = 0; t < threadNum; t++) {
			caseStartNum = t * chuShu;
			caseEndNum = (t + 1) * chuShu;
			ArrayList slist = new ArrayList();
			for (int k = caseStartNum; k < caseEndNum; k++) {
				try {
					slist.add(caselist.get(k));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			int yuShu = casesnum % threadNum;
			if ((yuShu != 0) && (t + 1 == threadNum)) {
				for (int m = 0; m < yuShu; m++) {
					slist.add(caselist.get(casesnum - 1 - m));
				}

			}
			lists.add(slist);
		}
		return lists;
	}

	/**
	 * 平均分配法
	 * 
	 * @param threadNum
	 * @param caselist
	 * @return
	 * @throws Exception
	 */
	public ArrayList divideCaseListe(int threadNum, ArrayList caselist)
			throws Exception {
		ArrayList lists = new ArrayList();
		int casesNum = caselist.size();
		for (int i = 0; i < threadNum; i++) {
			ArrayList list = new ArrayList();
			int chushu = casesNum / threadNum + 1;
			int m = i;
			int num = 0;
			while (true) {

				try {
					list.add(caselist.get(m));
				} catch (Exception e) {
					// TODO: handle exception
				}

				m = m + threadNum;
				num++;
				if (num == (chushu)) {
					break;

				}

			}
			lists.add(list);
		}
		return lists;
	}


	/**
	 * 获取CASE
	 * 
	 * @param platform
	 * @param filterType
	 * @param filtervalue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> collectCase(String filterType,HashMap<String, String> filtervalue) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		WebCaseExecuteDao caseInfoExecuteDao = new WebCaseExecuteDao();
		ConfigDbOperate configDbOperate = new ConfigDbOperate();

		if (filterType.equals("all")) {
			
			
			//新增将当轮执行CASE转移到历史表
			configDbOperate.insertData(" insert into web_case_exehis select t1.controlid,t2.* from  web_control t1,web_case_current t2 ");
			
			
			configDbOperate.delData("delete web_case_current t");
			configDbOperate
					.insertData("insert into web_case_current select t.* from web_case t where result='已发布' ");
			configDbOperate
			.updateData("update web_case_current set result='未执行'");
			
			list = configDbOperate
					.searchStrings("Select caseid from web_case_current where caselevel <>'debug' order by caseid asc");
			
			
			
			
			return list;
		} else if (filterType.equals("continueX")) {

			String sql = "Select caseid from web_case_current where result='未执行' and  caselevel <>'debug' order by caseid asc";
			list = configDbOperate.searchStrings(sql);
			
			return list;
		} else if (filterType.equals("filter")) {
			StringBuffer sql = new StringBuffer();
			StringBuffer sqlup = new StringBuffer();

			sql.append("select  caseid  from web_case_current  where 1=1 and caselevel <>'debug' ");
			sqlup.append("update web_case_current set result='未执行' where 1=1  and caselevel <>'debug' ");
			String menuName = filtervalue.get("menuName");
			String caseName = filtervalue.get("caseName");			
			String module = filtervalue.get("module");
			String tester = filtervalue.get("tester");
			String caseLevel = filtervalue.get("caseLevel");
			String result = filtervalue.get("result");

			if (module!=null&&!(module.equals(""))) {
				sql.append(" and module like '%" + module + "%'");
				sqlup.append(" and module like '%" + module + "%'");

			}
			if (menuName!=null&&!(menuName.equals(""))) {
				sql.append(" and menuName like '%" + menuName + "%'");
				sqlup.append(" and menuName like '%" + menuName + "%'");

			}

			if (caseName!=null&&!(caseName.equals(""))) {
				sql.append(" and caseName like '%" + caseName + "%'");
				sqlup.append(" and caseName like '%" + caseName + "%'");

			}
			

			if (tester!=null&&!(tester.equals(""))) {
				sql.append(" and tester like '%" + tester + "%'");
				sqlup.append(" and tester like '%" + tester + "%'");

			}

			if (caseLevel!=null&&!(caseLevel.equals(""))) {
				sql.append(" and caseLevel in ('" + caseLevel + "')");
				sqlup.append(" and caseLevel in ('" + caseLevel + "')");

			}

			if (result!=null&&!(result.equals(""))) {
				sql.append(" and result='" + result + "'");
				sqlup.append(" and result='" + result + "'");

			}
			sql.append(" order by caseid asc");
			list = configDbOperate.searchStrings(sql.toString());
			configDbOperate.updateData(sqlup.toString());
			
			return list;

		} else {
			// //点击选择CASE
			int caseId = Integer.parseInt(filtervalue.get("caseId"));
			list.add(Integer.toString(caseId));
			return list;
		}
	}



/*	private HashMap<String, String> getDbCase(int caseId) throws Exception {
		DbOperate dbOperate = new DbOperate(caseId);
		String sql = "select t.caseid,t.module,t.menuname,t.casename,t.caselevel,t.menucode,t.casecode,t.pageelement,t.opstep,t.checkstep,t.tester,t.result,t.savedata,t.remark,t.fee from web_case t where caseid='"
				+ caseId + "'";
		HashMap<String, String> hashMap = dbOperate.searchMaps(sql).get(0);
		
		return hashMap;
	}
*/


	/**
	 * 
	 * 
	 * @param content
	 *            前台编写内容，
	 * @param module
	 *            模块如so
	 * @throws Exception
	 */
	public void createClassFile(ArrayList list, String filePath)
			throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		FileUtil fileUtil = new FileUtil();
		DataUtil dataUtil = new DataUtil();
		//
		WebCaseBean webCaseBean = (WebCaseBean) list.get(0);
		String module = webCaseBean.getModule();
		String menuName = webCaseBean.getMenuCode();
        int  caseId = webCaseBean.getCaseId();
	    TranslateLogic translateLogic = new TranslateLogic();

		
		stringBuffer.append(translateLogic.createHead(module));

		
		stringBuffer.append("public class " + menuName + " {\n");		
		// 先输入页面元素定义,构造方式
		String pageElement =webCaseBean.getPageElement();	
		
		stringBuffer.append(translateLogic.createVar(pageElement, menuName));
	

		// 循环生成每个CASE的操作方法和校验方法
		for (int a = 0; a < list.size(); a++) {
			WebCaseBean webCaseBeantemp = (WebCaseBean) list.get(a);			
			String opStep = webCaseBeantemp.getOpStep();
			//将分号先转移成 ascii59 ；
			opStep = opStep.replace("\\;", "ascii59");
			
			
			
			String[] opSteps = opStep.split("\\;");
			
			
			String[] opStepstr = opStep.split("\\;");
			ArrayList<String> opStepsList = new ArrayList<>();
			//增加逻辑语句，需要二次分割换行符
			for(String s:opStepstr){
				String[] opStepstemp = s.split("\n");
			     for(String ss:opStepstemp){
			    	 opStepsList.add(ss);
			    	 System.out.print("++"+ss+"\n");
			     }
			}
			
			
			
			

			String paramName = webCaseBeantemp.getParamName();
			String paramValue = webCaseBeantemp.getParamValue();
			String[] paramNames =null;
			String[] paramValues =null;
		
			if(paramName!=null){
				//修改参数分割
				paramNames = paramName.split("\\|");
				paramValues = paramValue.split("\\|");
			}

			String caseCode= webCaseBeantemp.getCaseCode();

			// 循环生成操作方法
			StringBuffer opStringBufferTemp = new StringBuffer("");
            ArrayList<String> dataVarName =new ArrayList<String>();

			if(paramName==null){
				opStringBufferTemp.append("	public HashMap<String,String> "
						+caseCode
						+ "() throws Exception {\n");
			}else{
				StringBuffer paramTemp = new StringBuffer();
				for(String string :paramNames){
					paramTemp.append("String "+string+",");
				}
				String param=paramTemp.toString();
				param = param.substring(0,param.length()-1);
				opStringBufferTemp.append("	public HashMap<String,String> "
						+caseCode
						+ "("+param+") throws Exception {\n");
			}


			//增加this语句判断 ,修改判断语句
			if( !opStep.startsWith("this.")){
			//if( !opStep.contains("this.")){	
			
				opStringBufferTemp
				.append("			String saveData = \"\";\n");	

				
			opStringBufferTemp
			.append("			OpWebDriver opWebDriver = new OpWebDriver();\n");			
			opStringBufferTemp
			.append("			DataUtil dataUtil = new DataUtil();\n");	
			opStringBufferTemp
			.append("			ComOperate comOp = new ComOperate(webDriver);\n");		
			opStringBufferTemp
					.append("			Page pageOp = new Page(webDriver);\n");
			opStringBufferTemp
					.append("			DbOperate dbOp = new DbOperate();\n");
			opStringBufferTemp
			.append("			ConfigDbOperate configDbOp = new ConfigDbOperate();\n");			
			opStringBufferTemp
					.append("			DbCheck dbCheck = new DbCheck();\n");
			opStringBufferTemp
					.append("			PageCheck pageCheck = new PageCheck(webDriver,caseId);\n");
			opStringBufferTemp
					.append("			HashMap resultMap  = new HashMap<>();\n");



			opStringBufferTemp
			.append("			resultMap.put(\"message\", \"\");\n");
			opStringBufferTemp
			.append("			resultMap.put(\"result\", \"成功\");\n");
		
			opStringBufferTemp
			.append("			resultMap.put(\"saveData\", saveData);\n");	
			
	
			
			opStringBufferTemp
			.append("			try{\n");	
			

			if(paramNames!=null){
				
			
				//CASE入参保存值，只支持string
			for(int i=0;i<paramNames.length;i++){
				String varName=paramNames[i];
				String varvalue=paramValues[i];
				//增加带参数的方法赋值，  不能写死
/*				opStringBufferTemp.append("			"
						+ varName+"=\"" +varvalue+ "\";\n");*/
				
				//并保存到数据库
				opStringBufferTemp.append("			saveData = saveData +\""+varName+"=\"+"+varName+"+\"|\";\n");	
				dataVarName.add(varName);
				opStringBufferTemp.append("			resultMap.put(\"saveData\", saveData);\n");	

				
			}

			}






				for (int k = 0; k < opStepsList.size(); k++) {
					String lineContentString = opStepsList.get(k);					
					lineContentString = lineContentString.replace("\n", "").replace("\r", "");					
					if( (lineContentString==null) || (lineContentString.equals("")) || (lineContentString.equals("null")) ){
						continue;
					}
					
					
					String backLine="";
					
					if(lineContentString.contains("while(")){
						backLine = translateLogic.translateWhile(lineContentString);
						
					}else if(lineContentString.contains("}else{")){
						backLine = translateLogic.translateElse(lineContentString);

					}else if(lineContentString.contains("}else if(")){
						backLine = translateLogic.translateElseIf(lineContentString);

					}else if(lineContentString.contains("if(")){
						backLine = translateLogic.translateIf(lineContentString);

					}else if(lineContentString.contains("for(")){
						backLine = translateLogic.translateFor(lineContentString);

					}else if(lineContentString.contains("}")){
						backLine = translateLogic.translateHalf(lineContentString);

					}else if(lineContentString.contains("return ")){
						backLine = "			"+lineContentString+";\n";
					
					}else{
						
						
						backLine = translateLogic.TranslateNormal(lineContentString);
						
					}
					
					
					
					//如果是调用了其他组件（new类），判断是否已经定义，有则不NEW，无则不改
					String classmethod = dataUtil.patternText(lineContentString, "com.(.*)\\(");

					if( (classmethod!=null)&&(!classmethod.equals(""))){
						
						String[] classmethods= classmethod.split("\\.");
						
						String newClass = classmethods[0]+" "+classmethods[0]+" = new "+classmethods[0]+"(webDriver,caseId,null);";
						if(!opStringBufferTemp.toString().contains(newClass)){
							String opStringBufferTempTemp = opStringBufferTemp.toString().replace("OpWebDriver opWebDriver = new OpWebDriver();", "OpWebDriver opWebDriver = new OpWebDriver();\n"+newClass);
							opStringBufferTemp =new StringBuffer(opStringBufferTempTemp);
							
							//更新导入包 		stringBuffer.append("import base.com.zl.com.ComOperate;\n");
							String stringBufferTemp =stringBuffer.toString().replace("import base.com.zl.com.ComOperate;\n", "import base.com.zl.com.ComOperate;\n"+"import cases.com."+classmethods[0]+";\n");
							stringBuffer = new StringBuffer(stringBufferTemp);
							
						}
											
						backLine=backLine.replace(newClass, "");

                         if(backLine.contains("menucheck")){
							
							
							//return "			" + "resultMap = comOp."+lineContentString.split("\\)")[0]+",caseId)"
								backLine	= "			" + "resultMap = "+backLine+ "\n";
						}
					}
					
					
					
			
					//有返回值
					String backLineTemp= backLine.trim();
					if(backLineTemp.startsWith("String ")||backLineTemp.startsWith("String ")
							||backLineTemp.startsWith("boolean ")||backLineTemp.startsWith("int ")){
						
						String varNametemp = dataUtil.patternText(backLine, "(.*?)=");
						varNametemp=varNametemp.trim();
						String[] varNamelist=varNametemp.split(" ");
						String varType=varNamelist[0];
						String varname=varNamelist[1];
						if(opStringBufferTemp.toString().contains(varNametemp)){
							//重复变量
							opStringBufferTemp.append(backLine.replace(varType+" ", ""));
						}else{
							//不重复变量
							opStringBufferTemp.append(backLine);

						}
						//不判断变量类型直接保存
						opStringBufferTemp.append("			saveData = saveData +\""+varname+"=\"+"+varname+"+\"|\";\n");	
						opStringBufferTemp.append("			resultMap.put(\"saveData\", saveData);\n");	
						dataVarName.add(varname);
						
					}else{
						//无返回值
						opStringBufferTemp.append(backLine);
					}
										
				}



			opStringBufferTemp.append("			String screenpath = testDir + caseId+File.separator +\"执行操作步骤完成 \"+ \".png\";\n");	
			opStringBufferTemp.append("			opWebDriver.screenShot(webDriver,screenpath);\n");
			opStringBufferTemp.append("			} catch (Exception e) {\n");
			opStringBufferTemp.append("			// TODO: handle exception\n");
			//opStringBufferTemp.append("			DataUtil dataUtil = new DataUtil();\n");
			opStringBufferTemp.append("			String errMessage = dataUtil.getTrace(e);\n");	
			opStringBufferTemp.append("			String message  =(String)resultMap.get(\"message\");\n");	
			opStringBufferTemp.append("			message = message+errMessage+\"\\n\";\n");	
			opStringBufferTemp.append("			resultMap.put(\"result\", \"失败\");\n");	
			opStringBufferTemp.append("			resultMap.put(\"message\", message);\n");	
			opStringBufferTemp.append("			String screenpath = testDir + caseId+File.separator +\"执行操作步骤异常 \"+ \".png\";\n");	
			opStringBufferTemp.append("			opWebDriver.screenShot(webDriver,screenpath);\n");
			opStringBufferTemp.append("			resultMap.put(\"exception\", e);\n");
			
			
		     
		     
			opStringBufferTemp.append("			}finally{\n");	
			opStringBufferTemp.append("			resultMap.put(\"saveData\", saveData);\n");	
			//opStringBufferTemp.append("			resultMap.put(\"message\", stringBuffer.toString());\n");	

			opStringBufferTemp.append("			return resultMap;\n");	
			opStringBufferTemp.append("			}\n");	
			
			
			}else{
				
				//return  this.testparaName("we", "232");
				
				opStringBufferTemp.append("			return "+opSteps[0]+";\n");	

			}
			
			
			opStringBufferTemp.append("		}\n\n\n");	
	
			
			
			stringBuffer.append(opStringBufferTemp.toString());
			

			
			
			
			
			
			
			
			
			
			// 循环生成校验方法			
			String checkStep = webCaseBeantemp.getCheckStep();
			String[] checkSteps = new String[0];			
			if(checkStep!=null){
				 checkSteps = checkStep.split("\\;");
			}
			StringBuffer checkStringBufferTemp = new StringBuffer("");

			
			
			checkStringBufferTemp.append("	public HashMap<String,String> "
					+caseCode+"Check"
					+ "() throws Exception {\n");
			
			
			if((checkStep!=null)&&(checkStep.startsWith("this."))){
				checkStringBufferTemp.append("			return "+checkSteps[0]+";\n");	
	
			}else{
				

			
			checkStringBufferTemp
			.append("			DataUtil dataUtil = new DataUtil();\n");	

			checkStringBufferTemp
					.append("			DbOperate dbOp = new DbOperate();\n");
			checkStringBufferTemp
			        .append("			ConfigDbOperate configDbOp = new ConfigDbOperate();\n");
			checkStringBufferTemp
					.append("			DbCheck dbCheck = new DbCheck();\n");
			checkStringBufferTemp
					.append("			HashMap<String,String> resultMap  = new HashMap<String,String>();\n");
			checkStringBufferTemp
			.append("			String savaData = \"\";\n");		
			checkStringBufferTemp
			.append("			HashMap<String,String> saveDataMap  = new HashMap<String,String>();\n");

			for(String key:dataVarName){
				checkStringBufferTemp
				.append("			String "+key+" = \"\";\n");
			}
			
			checkStringBufferTemp
			.append("			resultMap.put(\"message\", \"\");\n");
			checkStringBufferTemp
			.append("			resultMap.put(\"result\", \"正确\");\n");
			checkStringBufferTemp
			.append("			try{\n");
			checkStringBufferTemp
			.append("			savaData = configDbOp.searchString(\"select savedata from web_case_current where caseid = '\"+caseId+\"'\");\n");	
			checkStringBufferTemp
			.append("			saveDataMap = dataUtil.stringToMap(savaData);\n");
			for(String key:dataVarName){
				checkStringBufferTemp
				.append("			"+key+" = saveDataMap.get(\""+key+"\");\n");
			}
			checkStringBufferTemp
			.append("			}catch (Exception e) {\n");
			checkStringBufferTemp
			.append("			// TODO: handle exception\n");
			
			//
			checkStringBufferTemp
			.append("			resultMap.put(\"message\", \"检验步骤获取变量值报错\");\n");
			checkStringBufferTemp
			.append("			}\n");
			

			
			
			
			checkStringBufferTemp.append("	try {\n");

			
			







			



				for (int k = 0; k < checkSteps.length; k++) {
					String lineContentString = checkSteps[k];
					lineContentString = lineContentString.replace("\n", "").replace("\r", "");
					lineContentString = lineContentString.trim();
					
					if( (lineContentString==null) || (lineContentString.equals("")) || (lineContentString.equals("null")) ){
						continue;
					}
					
					if (lineContentString.startsWith("//")) {
						checkStringBufferTemp.append(lineContentString+"\n");
					}
					
					
					if (lineContentString.contains("=\"")) {
						String varName = lineContentString.split("=")[0];
						if (checkStringBufferTemp.toString().contains(varName + "=")) {
							checkStringBufferTemp.append("			" + lineContentString
									+ ";\n");

						} else {
							checkStringBufferTemp.append("			String "
									+ lineContentString + ";\n");

						}

					}else if(lineContentString.contains("this.")){
						checkStringBufferTemp.append("			resultMap =" + lineContentString
								+ ";\n");
					}else{

						
						
						
						
						//获取类类型合并
						String lineContentStringtemp="";
						ConfigDbOperate configDbOperate = new ConfigDbOperate();
						String paraType="";
					
						if(lineContentString.startsWith("update")||lineContentString.startsWith("del")||lineContentString.startsWith("insert")){
			                String[] lineContentStringtemps = lineContentString.split("\\(");
			                lineContentStringtemp = lineContentStringtemps[0];
			                paraType=configDbOperate.searchString("select t.paratype from web_config t where paracode  = '"+lineContentStringtemp+"'");
			                lineContentString = paraType+"."+lineContentString;
							
							
						}else{
					/*		String[] lineContentStringtemps = lineContentString.split("=");

						
							if(lineContentStringtemps.length>1){
								lineContentStringtemp =lineContentStringtemps[1];
							}else{
								lineContentStringtemp =lineContentStringtemps[0];
			
							}
							
							
							lineContentStringtemps = lineContentStringtemp.split("\\(");*/
							String[] lineContentStringtemps = lineContentString.split("\\(");

								lineContentStringtemp =lineContentStringtemps[0];
							
							 paraType=configDbOperate.searchString("select t.paratype from web_config t where paracode ='"+lineContentStringtemp+"'");
							lineContentString = lineContentString.replace(lineContentStringtemp, paraType+"."+lineContentStringtemp);
							
						}

			
						
						
						if (lineContentString.contains("pageOp")) {

							String javaString = this
									.transPageOpCode(lineContentString);
							checkStringBufferTemp.append("			" + javaString + ";\n");
							System.out.println(javaString);
						} else if (lineContentString.contains("dbOp")) {
							String javaString = this
									.transDbOpCode(lineContentString);
							checkStringBufferTemp.append("			" + javaString + ";\n");
							System.out.println(javaString);

						} /*else if (lineContentString.contains("configDbOp")) {
							String javaString = this
									.transConfigDbOpCode(lineContentString);
							checkStringBufferTemp.append("			" + javaString + ";\n");
							System.out.println(javaString);

						}*/else if (lineContentString.contains("comOp")) {
							String javaString = this
									.transComOpCode(lineContentString);
							checkStringBufferTemp.append("			" + javaString + ";\n");
							System.out.println(javaString);

						} else if (lineContentString.contains("pageCheck")) {
							checkStringBufferTemp.append("			resultMap = "
									+ lineContentString.substring(0,
											lineContentString.length() - 1)
									+ ",resultMap);");
							System.out.println("			resultMap = "
									+ lineContentString.substring(0,
											lineContentString.length() - 1)
									+ ",resultMap);");

						} else if (lineContentString.contains("dbCheck")) {
							String javaString = this
									.transDbCheckCode(lineContentString);
							checkStringBufferTemp.append("			" + javaString + ";\n");
						}else{
							checkStringBufferTemp.append("			" + lineContentString + ";\n");

						}

					} 

				}



			

			checkStringBufferTemp.append("			} catch (Exception e) {\n");
			checkStringBufferTemp.append("			// TODO: handle exception\n");
			checkStringBufferTemp.append("			String errMessage = dataUtil.getTrace(e);\n");	
			checkStringBufferTemp.append("			String message  =resultMap.get(\"message\");\n");	
			checkStringBufferTemp.append("			message = message+errMessage+\"\\n\";\n");	
			checkStringBufferTemp.append("			resultMap.put(\"result\", \"错误\");\n");	
			checkStringBufferTemp.append("			resultMap.put(\"message\", message);\n");


			checkStringBufferTemp.append("			}finally{\n");	
			checkStringBufferTemp.append("			return resultMap;\n");	
			checkStringBufferTemp.append("			}\n");	
			
			}
			
			checkStringBufferTemp.append("			}\n\n\n");	
		
			stringBuffer.append(checkStringBufferTemp.toString());

		}

		stringBuffer.append("}\n");
		fileUtil.deleteFile(filePath);
		fileUtil.createFile(filePath, stringBuffer.toString().replace("ascii59", ";"));
	}

	// 都是带.的语句
	//新增修改，插入，删除转义
	public  String transDbOpCode(String orginCode) throws Exception {
		WebConfigDao WebConfigDao = new WebConfigDao();
		String keyParam;
		String key;
		String param = null;
		boolean isVar = false;
		
		// String[] formatParam = orginCode.split("=");
		String[] formatParam = new String[2];
		if (orginCode.contains("=dbOp")) {
			int index = orginCode.indexOf("=");
			formatParam[0] = orginCode.substring(0, index);
			formatParam[1] = orginCode.substring(index + 1, orginCode.length());

		} else {
			formatParam[1] = orginCode;

		}

		String varName = "";
		if (formatParam[0] != null) {
			varName = formatParam[0];

		}
		int indexs = orginCode.indexOf(".");

		keyParam = orginCode.substring(indexs + 1, orginCode.length());

		String[] keyParams = keyParam.split("\\(");
		if (keyParams.length == 2) {
			//int indexss = keyParam.indexOf("(");
			//param = keyParam.substring(indexss+2 , keyParam.length());
			
			
			param = keyParams[1];
			
			
			
			if(param.contains("\")")){
				param = param.replace("\")", "");
				param = param.substring(1);
                isVar= false;
			}else{
				param = param.replace(")", "");
                isVar= true;

			}
			
			

		}else if(keyParams.length > 2){
			
			int indexss = keyParam.indexOf("(");
			param = keyParam.substring(indexss+2 , keyParam.length());
			
		}
		
		
		
		key = keyParams[0];

		// 判断是否是直写Sql
		String paramTemp =param;
		
		//包裹获取最大行数

		
		
		if(!isVar){
			paramTemp="\""+paramTemp+"\"";
		}
		if (key.equals("searchstring")) {

			
			orginCode = "String" + " " + varName + "=dbOp.searchString("
					+ paramTemp + ")";
			orginCode = orginCode.replace("\")\")", "\")");
			return orginCode;
		} else if (key.equals("searchmap")) {

			orginCode = "HashMap<String,String>" + " " + varName
					+ "=(HashMap<String, String>)  dbOp.searchMap(" + paramTemp + ")";
			return orginCode;
		} else if (key.equals("searchstrings")) {

			orginCode = "ArrayList<String>" + " " + varName
					+ "=dbOp.searchStrings(" + paramTemp + ")";
			return orginCode;
		} else if (key.equals("searchmaps")) {

			orginCode = "ArrayList<HashMap>" + " " + varName
					+ "=dbOp.searchMaps(" + paramTemp + ")";
			return orginCode;
		}else if(key.equals("insert")){
			//新增插入
			orginCode = " dbOp.insertData(" + paramTemp + ")";
			return orginCode;
		}else if(key.equals("update")){			
			//新增修改
			orginCode = " dbOp.updateData(" + paramTemp + ")";
			orginCode = orginCode.replace("))", ")");
			return orginCode;
		}else if(key.equals("del")){			
			//新增删除
			orginCode = " dbOp.delData(" + paramTemp + ")";
			orginCode = orginCode.replace("))", ")");

			return orginCode;
		}

		
		//增加对dbOP 操作编码的判断
		if(key.equals("dbop")){
             String keyChild = (param.split("\\,")[0]);
             keyChild=keyChild.replace("\"", "");
             
 			HashMap map = WebConfigDao.getConfigParams("dbOp", keyChild, "1");
 			String returnValue = (String) map.get("PARA3");
 			if( (returnValue!=null)  && (!returnValue.equals("null")) && (!returnValue.equals("无")) ){
 				orginCode =orginCode.replace("=dbOp", "=("+returnValue+")dbOp");
 	 			orginCode = returnValue+" "+orginCode;

 			}
 			return orginCode;
		}
		
		
		
		String[] searchSql = WebConfigDao.getDbOpByKey("dbOp", key, param);
		String sql = searchSql[0];
		String returnType = searchSql[2];

		if (returnType == null) {
			//修改逻辑增加，新增，修改，删除
			if(sql.startsWith("update ")){
				orginCode = "dbOp.updateData(" + sql + ")";

			}else if(sql.startsWith("insert ")){
				orginCode = "dbOp.insertData(" + sql + ")";

			}else if(sql.startsWith("delete ")){
				orginCode = "dbOp.delData(" + sql + ")";

			}else{
				orginCode = "dbOp.searchString(" + sql + ")";

			}
			

		} else if (returnType.equals("String")) {

			orginCode = "String" + " " + varName + "=dbOp.searchString(" + sql
					+ ")";

		} else if (returnType.equals("Map")) {
			orginCode = "HashMap<String,String>" + " " + varName
					+ "=(HashMap<String, String>)dbOp.searchMap(" + sql + ")";

		} else if (returnType.equals("Strings")) {
			orginCode = "ArrayList<String>" + " " + varName
					+ "=dbOp.searchStrings(" + sql + ")";

		} else if (returnType.equals("Maps")) {
			orginCode = "ArrayList<HashMap>" + " " + varName
					+ "=dbOp.searchMaps(" + sql + ")";

		}else if(returnType.equals("Object")){
			
			 //param 从参数中抽取key 和返回值，组装返回语句
			

		}

		return orginCode;
	}

	
	

/*	// 都是带.的语句
	private String transConfigDbOpCode(String orginCode) throws Exception {
		WebConfigDao WebConfigDao = new WebConfigDao();
		String keyParam;
		String key;
		String param = null;
		// String[] formatParam = orginCode.split("=");
		String[] formatParam = new String[2];
		if (orginCode.contains("=configDbOp")) {
			int index = orginCode.indexOf("=");
			formatParam[0] = orginCode.substring(0, index);
			formatParam[1] = orginCode.substring(index + 1, orginCode.length());

		} else {
			formatParam[1] = orginCode;

		}

		String varName = "";
		if (formatParam[0] != null) {
			varName = formatParam[0];

		}
		int indexs = orginCode.indexOf(".");

		keyParam = orginCode.substring(indexs + 1, orginCode.length());

		String[] keyParams = keyParam.split("\\(");
		if (keyParams.length == 2) {
			param = keyParams[1].replace(")", "");
		}
		key = keyParams[0];

		// 判断是否是直写Sql
		if (key.equals("searchstring")) {

			orginCode = "String" + " " + varName + "=configDbOp.searchString("
					+ param + ")";
			return orginCode;
		} else if (key.equals("searchmap")) {

			orginCode = "HashMap<String,String>" + " " + varName
					+ "=(HashMap<String, String>)  configDbOp.searchMap(" + param + ")";
			return orginCode;
		} else if (key.equals("searchstrings")) {

			orginCode = "ArrayList<String>" + " " + varName
					+ "=configDbOp.searchStrings(" + param + ")";
			return orginCode;
		} else if (key.equals("searchmaps")) {

			orginCode = "ArrayList<HashMap>" + " " + varName
					+ "=configDbOp.searchMaps(" + param + ")";
			return orginCode;
		}

		String[] searchSql = WebConfigDao.getDbOpByKey("configDbOp", key, param);
		String sql = searchSql[0];
		String returnType = searchSql[2];

		if (returnType == null) {
			orginCode = "configDbOp.searchString(" + sql + ")";

		} else if (returnType.equals("String")) {

			orginCode = "String" + " " + varName + "=configDbOp.searchString(" + sql
					+ ")";

		} else if (returnType.equals("Map")) {
			orginCode = "HashMap<String,String>" + " " + varName
					+ "=(HashMap<String, String>)configDbOp.searchMap(" + sql + ")";

		} else if (returnType.equals("Strings")) {
			orginCode = "ArrayList<String>" + " " + varName
					+ "=configDbOp.searchStrings(" + sql + ")";

		} else if (returnType.equals("Maps")) {
			orginCode = "ArrayList<HashMap>" + " " + varName
					+ "=configDbOp.searchMaps(" + sql + ")";

		}

		return orginCode;
	}*/
	
	
	
	
	/**
	 * 
	 * @param String  类的实例名关键字 dbOp dataUtil
	 * @param orginCode
	 * @return
	 * @throws Exception 
	 */
	public String transNormalCode(String orginCode) throws Exception {
		WebConfigDao WebConfigDao = new WebConfigDao();
		String[] orginCodes=orginCode.split("\\=");
		if(orginCodes.length==1){		
			return orginCode;
		}
		
		String key =orginCodes[orginCodes.length-1];
	    key=key.split("\\(")[0];
	    String[] keys = key.split("\\.");
		String paratype = keys[0];
		String paracode = keys[1];
	
		HashMap<String, String> map =WebConfigDao.getConfigParams(paratype,paracode,"1");
		String returnType=map.get("PARA3");
		orginCode  =returnType + " "+orginCode;
		return orginCode;
	}
	
	
	
	public String transComOpCode(String orginCode) throws Exception {
		WebConfigDao WebConfigDao = new WebConfigDao();
		String keyParam;
		String key;
		String param = null;
		String[] formatParam = orginCode.split("=");
		String varName = "";
		if (formatParam.length == 2) {
			varName = formatParam[0];

		}
		String[] keyParamss = orginCode.split("\\.");
		keyParam = orginCode.split("\\.")[1];
		
		if(keyParamss.length>2){
			StringBuffer ss = new StringBuffer();
			for(int i=1;i<keyParamss.length;i++){
				ss.append(keyParamss[i]+".");
			}
			
			keyParam =ss.toString().substring(0,ss.length()-1);
		}
		
		

		
		
		

		String[] keyParams = keyParam.split("\\(");
		if (keyParams[1].length() > 1) {
			param = keyParams[1].replace(")", "");
		}
		key = keyParams[0];

		String[] webOp = WebConfigDao.getPageOpByKey("comOp", key);
		String inputType = webOp[0];
		String returnType = webOp[1];

		if (returnType == null ||returnType.equals("无")) {
			orginCode = "comOp." + key;

		} else {
			orginCode = returnType + " " + varName + "=comOp." + key;

		}

		if (param == null) {
			return orginCode + "()";
		}else{
			
			return orginCode +"("+param+")";
		}
/*		String by = param.split(",")[0];
		if (by.contains("_x")) {
			param = param.replace(by, "By.xpath(this." + by + ")");
		} else if (by.contains("_c")) {
			param = param.replace(by, "By.cssSelector(this." + by + ")");

		} else if (by.contains("_i")) {
			param = param.replace(by, "By.id(this." + by + ")");

		}
		orginCode = orginCode + "(" + param + ")";

		return orginCode;*/
	}
	
	
	
	public String transPageOpCode(String orginCode) throws Exception {
		WebConfigDao WebConfigDao = new WebConfigDao();
		String keyParam;
		String key;
		String param = null;
		String[] formatParam = orginCode.split("=");
		String varName = "";
		if (formatParam.length == 2) {
			varName = formatParam[0];

		}
		
		String[] keyParamss = orginCode.split("\\.");
		keyParam = orginCode.split("\\.")[1];

		if(keyParamss.length>2){
			StringBuffer ss = new StringBuffer();
			for(int i=1;i<keyParamss.length;i++){
				ss.append(keyParamss[i]+".");
			}
			
			keyParam =ss.toString().substring(0,ss.length()-1);
		}
		
		
		
		String[] keyParams = keyParam.split("\\(");
		if (keyParams[1].length() > 1) {
			param = keyParams[1].replace(")", "");
		}
		key = keyParams[0];

		String[] webOp = WebConfigDao.getPageOpByKey("pageOp", key);
		String inputType = webOp[0];
		String returnType = webOp[1];

		if (returnType == null ||returnType.equals("无")) {
			orginCode = "pageOp." + key;

		} else {
			orginCode = returnType + " " + varName + "=pageOp." + key;

		}

		if (param == null) {
			return orginCode + "()";
		}
		String by = param.split(",")[0];
		if (by.contains("_x")) {
			param = param.replace(by, "By.xpath(this." + by + ")");
		} else if (by.contains("_c")) {
			param = param.replace(by, "By.cssSelector(this." + by + ")");

		} else if (by.contains("_i")) {
			param = param.replace(by, "By.id(this." + by + ")");

		}
		orginCode = orginCode + "(" + param + ")";

		return orginCode;
	}

	public String transDbCheckCode(String orginCode) throws Exception {

		if (orginCode.contains(".checksql(")) {
			orginCode = orginCode.replace("${", "\"+");
			orginCode = orginCode.replace("}", "+\"");
			orginCode = orginCode.replace(".checksql", ".checksql");
			orginCode = orginCode.replace("\")", "\",resultMap)");
			orginCode = "resultMap  = " + orginCode;
		} else {
			String keys = orginCode.split("\\.")[1];
			String key = keys.split("\\(")[0];
			String orginParam = keys.split("\\(")[1].replace(")", "");
			String[] orginParamS = orginParam.split(",");
			String param = "";
			for (int i = 1; i < orginParamS.length; i++) {
				param = param + orginParamS[i] + ",";

			}
			param = param.substring(0, param.length() - 1);
			param = param.replace("${", "").replace("}", "");
			WebConfigDao WebConfigDao = new WebConfigDao();
			String[] sql = WebConfigDao.getDbOpByKey("dbCheck", key, param);
			orginCode = "resultMap = " + "dbCheck.checksql(" + orginParamS[0]
					+ "," + sql[0] + ",\"" + sql[3]
					+ "\",resultMap)";
		}

		return orginCode;

	}

	
	
/*	
	 //文本中的Java对象  
	 static class StringJavaObject extends SimpleJavaFileObject{  
	      //源代码  
	      private String content = "";  
	      //遵循Java规范的类名及文件  
	      public StringJavaObject(String _javaFileName,String _content){  
	            super(_createStringJavaObjectUri(_javaFileName),Kind.SOURCE);  
	            content = _content;  
	      }  
	      //产生一个URL资源路径  
	      private static URI _createStringJavaObjectUri(String name){  
	         //注意此处没有设置包名  
	         return URI.create("String:///" + name + Kind.SOURCE.extension);  
	      }  
	      //文本文件代码  
	      @Override  
	      public CharSequence getCharContent(boolean ignoreEncodingErrors)  
	             throws IOException {  
	         return content;  
	     }  
	 }*/
	
	
	
	
	/**
	 * 动态编译JAVA文件
	 * 
	 * @param path
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public String  compilerJavaFile(String rootPath,String module,String filePath,ArrayList<String> comlist) throws Exception {
	
       // String pathJar = rootPath+File.separator+"lib"+File.separator+"selzl.jar;"+rootPath+File.separator+"lib"+File.separator+"selenium"+File.separator+"selenium-java-3.4.0"+File.separator+"selenium-server-standalone-3.4.0.jar";
        String pathJar = rootPath+File.separator+"lib"+File.separator+"selzl.jar;"+rootPath+File.separator+"lib"+File.separator+"selenium"+File.separator+"selenium-java-3.4.0"+File.separator+"selenium-server-standalone-3.4.0.jar";

        System.out.println("加载编译jar包路径:"+pathJar);
        //
        
        String pathJar1 = rootPath+File.separator+"lib"+File.separator+"selzl.jar";
        String pathJar2 = rootPath+File.separator+"lib"+File.separator+"selenium"+File.separator+"selenium-java-3.4.0"+File.separator+"selenium-server-standalone-3.4.0.jar";

        

        
        
        
        
        //临时更新原来成功的class文件
        String oldClassFilePath=filePath+".class";
        String newJavaFilePath=filePath+".java";

         	JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
         	/*int result =0;

		try {
	        result = compiler.run(null, null, null, "-encoding", "GBK","-cp",pathJar,newJavaFilePath.toString(), newJavaFilePath);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
     
		 if(result==0){
       	 return true;
        }else{
       	 return false;
        }
*/

         	
         	
         	
         	
         	
         	
		
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		// 获取编译器实例
        JavaCompiler compilers = ToolProvider.getSystemJavaCompiler();
        // 获取标准文件管理器实例
        StandardJavaFileManager fileManager = compilers.getStandardFileManager(null, null, null);
        
        boolean rs = false;
        try {

            // 得到filePath目录下的所有java源文件
          
            List<File> sourceFileList = new ArrayList<File>();
            
            //加入当前目标java文件
            File sourceFile = new File(newJavaFilePath); 
            sourceFileList.add(sourceFile);
            

        	        
           //组件调用组件的情况，要递归查询所有关联的组件，先用最简单的方法，后续再优化
            FileUtil fileUtil = new FileUtil();
            ArrayList<String> ff = fileUtil.getAllFileName(rootPath+File.separator+"classes"+File.separator+"cases"+File.separator+"com"+File.separator);
        	for(String ftemp :ff ){
        		if(ftemp.contains(".java")){
                  String pathClass=rootPath+File.separator+"classes"+File.separator+"cases"+File.separator+"com"+File.separator+ftemp;
                  sourceFileList.add(new File(pathClass));
        		}

        	}
            
        	
        	
        	
        	/*  
            //加入组件Java文件
             if(!comlist.isEmpty()){
            	
            	for(String comname :comlist ){
                    String pathClass=rootPath+File.separator+"classes"+File.separator+"cases"+File.separator+"com"+File.separator+comname+".java";
                    //E:\apache-tomcat-8.5.34\webapps\webcrm\WEB-INF\classes\cases\com\MainPage.java
                    sourceFileList.add(new File(pathClass));

            	}
            	
            }
        	
        	*/
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
            
            // 没有java文件，直接返回
            if (sourceFileList.size() == 0) {
                System.out.println(filePath + "目录下查找不到任何java文件");
                return "目录下查找不到任何java文件";
            }
            // 获取要编译的编译单元  
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sourceFileList);
            String newClassFilePath=rootPath + File.separator + "classes"+ File.separator ;
            
            
            
            Iterable<String> options = Arrays.asList("-encoding", "GBK", "-classpath", pathJar, "-sourcepath", newJavaFilePath);
          //  Iterable<String> options = Arrays.asList("-encoding", "GBK", "-classpath", pathJar2+";"+pathJar1, "-sourcepath", newJavaFilePath);
     
            
            //Iterable<String> options = Arrays.asList("-encoding", "GBK", "-classpath", pathJar,      "-d", newClassFilePath,   "-sourcepath", newJavaFilePath);
            //参数1 文件输出，可不指定，我们采用javac命令的-d参数来指定class文件的生成目录
            //参数2 第二个参数为文件管理器实例，该文件管理器实例的作用就是将我们需要动态编译的java源文件转换为getTask需要的编译单元
            //参数3 第三个参数DiagnosticCollector<JavaFileObject> diagnostics是在编译出错时，存放编译错误信息。
            //参数4 第四个参数为编译命令选项，就是javac命令的可选项，这里我们主要使用了-d和-sourcepath这两个选项
            //参数5 第五个参数为类名称
            //参数6 第六个参数为上面提到的编译单元，就是我们需要编译的java源文件
            /** 
            * 编译选项，在编译java文件时，编译程序会自动的去寻找java文件引用的其他的java源文件或者class。 -sourcepath选项就是定义java源文件的查找目录， -classpath选项就是定义class文件的查找目录，-d就是编译文件的输出目录。 
            */ 
            
            

            CompilationTask compilationTask = compilers.getTask(null, fileManager, diagnostics, options, null, compilationUnits);
            // 运行编译任务
            
            
            
            
              rs = compilationTask.call();

              
              
              
              
        } catch (Exception e) {
			
        	// TODO: handle exception
        	
        	e.printStackTrace();
		}finally {
            fileManager.close();
            if(rs){
            	return "true";
            	
            }else{
            	
                
                
                List<Diagnostic<? extends JavaFileObject>> list = diagnostics.getDiagnostics();
                Diagnostic diagnostic = list.get(0);
                //77 -1
                System.out.println(diagnostic.getLineNumber());
                System.out.println(diagnostic.getCode());
                //ERROR NOTE
                System.out.println(diagnostic.getKind());
                System.out.println(diagnostic.getMessage(null));
                
                int fileLine =(int) diagnostic.getLineNumber();
                FileUtil fileUtil = new FileUtil();
                
               String errormesage=fileUtil.getContent(newJavaFilePath, "GBK", fileLine-1);
               errormesage = errormesage.replace("'", "");
                  //String errormesage = diagnostic.getMessage(null);
               
               //errormesage = "\r\n脚本位置:"+errormesage +"\r\n"+diagnostic.getCode()+"\r\n"+diagnostic.getMessage(null)+"ashdifhaisd \" lll";
                
               return (diagnostic.getMessage(null)+"(错误点:"+errormesage+")").replace("'", "");
                

            }
            
        }
        



         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
	}
	

	public static void main(String[] ss) throws Exception {
		CaseHandle caseHandle = new CaseHandle();
		FileUtil fileUtil = new FileUtil();


		WebCaseDao webCaseDao = new WebCaseDao();
		ArrayList list = webCaseDao.getCaseInfo("开户", "3");
		String rootPath = System.getProperty("catalina.home");
		if (rootPath != null) {
			rootPath = rootPath + File.separator + "webapps" + File.separator + "wecrm"
					+ File.separator + "WEB-INF";

		} else {
			rootPath = System.getProperty("user.dir") + File.separator + "html"
					+ File.separator + "WEB-INF";

		}
		
		WebCaseBean webCaseBean = (WebCaseBean) list.get(0);
		String module = webCaseBean.getModule();
		String menu=webCaseBean.getMenuCode();
		String	 pathFile = rootPath + File.separator + "classes"+ File.separator + "test" + File.separator + "web"+ File.separator + "cases" + File.separator + module+ File.separator + menu;

		caseHandle.createClassFile(list, pathFile+".java");

		String message = caseHandle.compilerJavaFile(rootPath,module,pathFile,null);
	}
	

}
