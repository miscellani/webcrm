package test.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.beust.jcommander.Strings;
import com.sun.jna.platform.unix.X11.XClientMessageEvent.Data;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler.CompilationTask;

import test.config.dao.WebConfigDao;
import test.web.WebInit;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.com.LoginPage;
import test.web.cases.com.MMainPage;
import test.web.cases.com.WorkDeskPage;
import test.web.cases.dao.WebCaseDao;
import test.web.cases.dao.WebCaseExecuteDao;
import test.web.com.bean.WebComponentBean;
import base.com.zl.db.ConfigDbOperate;
import base.com.zl.log.Log;
import base.com.zl.selenium.OpWebDriver;
import base.com.zl.utils.DataUtil;
import base.com.zl.utils.DateUtil;
import base.com.zl.utils.FileUtil;
import javax.tools.*;

public class ComHandle {





	/**
	 * 
	 * 
	 * @param content
	 *            前台编写内容，
	 * @param module
	 *            模块如so
	 * @throws Exception
	 */
	public void createClassFile(ArrayList<WebComponentBean> list, String filePath)
			throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		FileUtil fileUtil = new FileUtil();
		DataUtil dataUtil = new DataUtil();
		CaseHandle caseHandle = new CaseHandle();
		//
		WebComponentBean webComponentBean = (WebComponentBean) list.get(0);
		String moduleCode = webComponentBean.getModuleCode();
	    TranslateLogic translateLogic = new TranslateLogic();
		stringBuffer.append(translateLogic.createComHead());
		
		stringBuffer.append("public class " + moduleCode + " {\n");		
		// 先输入页面元素定义,构造方式(组件侧的元素是分开保存的，要合起来)
		StringBuffer pageElement =new StringBuffer();
		for(WebComponentBean web: list){
			if(web.getPageElement()!=null){
				pageElement.append(web.getPageElement()+"\n");	

			}
		}
				
		
		stringBuffer.append(translateLogic.createVar(pageElement.toString(), moduleCode));

		
		
		

		// 循环生成每个CASE的操作方法和校验方法
		for (int a = 0; a < list.size(); a++) {
			WebComponentBean webComponentBeantemp = (WebComponentBean) list.get(a);			
			String opStep = webComponentBeantemp.getOpStep();
			//将分号先转移成 ascii59 ；
			opStep = opStep.replace("\\;", "ascii59");
			
			
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
			
			
			
			

			String paramName = webComponentBeantemp.getParamName();
			String paramValue = webComponentBeantemp.getParamValue();
			String outParamValue = webComponentBeantemp.getOutParam();
			String[] paramNames =null;
			String[] paramValues =null;

            if(outParamValue==null){
            	outParamValue=" void ";
            }

			String comCode= webComponentBeantemp.getComCode();

			// 循环生成操作方法
			StringBuffer opStringBufferTemp = new StringBuffer("");
            ArrayList<String> dataVarName =new ArrayList<String>();

			if(paramName==null){
				opStringBufferTemp.append("	public "+outParamValue+" "
						+comCode
						+ "() throws Exception {\n");
			}else{
				
				//修改参数分割
				paramNames = paramName.split("\\,");
				paramValues = paramValue.split("\\,");
				
				StringBuffer paramTemp = new StringBuffer();

				for(int i=0;i<paramNames.length;i++){
					
					paramTemp.append(paramNames[i]+" "+paramValues[i]+",");
				}
				
				
				String param=paramTemp.toString();
				param = param.substring(0,param.length()-1);
				opStringBufferTemp.append("	public "+outParamValue+" "
						+comCode
						+ "("+param+") throws Exception {\n");
			}



				
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



			           String backLine="";
				for (int k = 0; k < opStepsList.size(); k++) {
					String lineContentString = opStepsList.get(k);		
				
					lineContentString = lineContentString.replace("\n", "").replace("\r", "");					
					if( (lineContentString==null) || (lineContentString.equals("")) || (lineContentString.equals("null")) ){
						continue;
					}
					
					
					 backLine="";
					
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

					}else if(lineContentString.contains("return")){
						backLine = "			"+lineContentString+";\n";
					
					}else{
												
						backLine = translateLogic.TranslateNormal(lineContentString);
						
					}
					
					//如果是调用了其他组件（new类），判断是否已经定义，有则不NEW，无则不改
					//if(test.equals("abc")){com.testmoduleone.testcodeone("123","456")
					String classmethod = dataUtil.patternText(lineContentString, "com.(.*)\\(");
					
					
					if( (classmethod!=null)&&(!classmethod.equals(""))){
						
						String[] classmethods= classmethod.split("\\.");
						
						String newClass = classmethods[0]+" "+classmethods[0]+" = new "+classmethods[0]+"(webDriver,caseId,null);";
						if(!opStringBufferTemp.toString().contains(newClass)){
							String opStringBufferTempTemp = opStringBufferTemp.toString().replace("OpWebDriver opWebDriver = new OpWebDriver();", "OpWebDriver opWebDriver = new OpWebDriver();\n"+newClass);
							opStringBufferTemp =new StringBuffer(opStringBufferTempTemp);
						}
											
						backLine=backLine.replace(newClass, "");

					}
					
					
					
					
					
					
					
					
					
					
					
					///opStringBufferTemp.append(backLine);

					
					
					//有返回值
					String backLineTemp= backLine.trim();
					if(backLineTemp.startsWith("String ")||backLineTemp.startsWith("ArrayList<WebElement> ")||backLineTemp.startsWith("String ")
							||backLineTemp.startsWith("WebElement ")||backLineTemp.startsWith("boolean ")||backLineTemp.startsWith("int ")){
						
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
						
					}else{
						//无返回值
						opStringBufferTemp.append(backLine);
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
				
				}



			
			
			
		
			opStringBufferTemp.append("		}\n\n\n");	
	
			
			
			stringBuffer.append(opStringBufferTemp.toString());
			

			
			



		}

		stringBuffer.append("}\n");

		fileUtil.deleteFile(filePath);
		fileUtil.createFile(filePath, stringBuffer.toString().replace("ascii59", ";"));
	}


	

	

	
	
	
	
/*	*//**
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
	 *//*
	public String  compilerJavaFile(String rootPath,String module,String filePath) throws Exception {
	
        String pathJar = rootPath+File.separator+"lib"+File.separator+"selzl.jar;"+rootPath+File.separator+"lib"+File.separator+"selenium"+File.separator+"selenium-java-3.4.0"+File.separator+"selenium-server-standalone-3.4.0.jar";
        System.out.println("加载编译jar包路径:"+pathJar);
        String pathClass=rootPath+File.separator+"classes"+File.separator+"cases"+File.separator+"com"+File.separator+"testmoduleone.class";
        
        
        //临时更新原来成功的class文件
      //  String oldClassFilePath=filePath+".class";// E:\apache-tomcat-8.5.34\webapps\webcrm\WEB-INF\classes\cases\com\testmoduleone.class
        String newJavaFilePath=filePath+".java";  //E:\apache-tomcat-8.5.34\webapps\webcrm\WEB-INF\classes\cases\com\testmoduleone.java
         	//JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
         	int result =0;

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


         	
         	
         	
         	
         	
         	
		
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		// 获取编译器实例
        JavaCompiler compilers = ToolProvider.getSystemJavaCompiler();
        // 获取标准文件管理器实例
        StandardJavaFileManager fileManager = compilers.getStandardFileManager(null, null, null);
        
        boolean rs = false;
        try {

            // 得到filePath目录下的所有java源文件
            File sourceFile = new File(newJavaFilePath);
            
            List<File> sourceFileList = new ArrayList<File>();
            sourceFileList.add(sourceFile);
            // 没有java文件，直接返回
            if (sourceFileList.size() == 0) {
                System.out.println(filePath + "目录下查找不到任何java文件");
                return "目录下查找不到任何java文件";
            }
            // 获取要编译的编译单元
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sourceFileList);
            
            

            //Iterable<String> options = Arrays.asList("-encoding", "GBK", "-classpath", pathJar,      "-d", newClassFilePath,   "-sourcepath", newJavaFilePath);
            //参数1 文件输出，可不指定，我们采用javac命令的-d参数来指定class文件的生成目录
            //参数2 第二个参数为文件管理器实例，该文件管理器实例的作用就是将我们需要动态编译的java源文件转换为getTask需要的编译单元
            //参数3 第三个参数DiagnosticCollector<JavaFileObject> diagnostics是在编译出错时，存放编译错误信息。
            //参数4 第四个参数为编译命令选项，就是javac命令的可选项，这里我们主要使用了-d和-sourcepath这两个选项
            //参数5 第五个参数为类名称
            //参数6 第六个参数为上面提到的编译单元，就是我们需要编译的java源文件
            *//** 
            * 编译选项，在编译java文件时，编译程序会自动的去寻找java文件引用的其他的java源文件或者class。 -sourcepath选项就是定义java源文件的查找目录， -classpath选项就是定义class文件的查找目录，-d就是编译文件的输出目录。 
            *//* 
            String newClassFilePath=rootPath + File.separator + "classes"+ File.separator ;
            Iterable<String> options = Arrays.asList("-encoding", "GBK", "-classpath", pathJar,  "-sourcepath", newJavaFilePath);
            
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
        



         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
	}*/
	

	public static void main(String[] ss) throws Exception {
		ComHandle caseHandle = new ComHandle();
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

	//	String message = caseHandle.compilerJavaFile(rootPath,module,pathFile);
	}
	

}
