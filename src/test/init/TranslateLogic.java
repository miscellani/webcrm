package test.init;


import base.com.zl.db.ConfigDbOperate;
import base.com.zl.utils.DataUtil;

public class TranslateLogic {
	
	
	/**
	 * 创建用例文件头信息package  import
	 * @param module
	 * @return
	 */
	public String createHead(String module){
		
		
		
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append("package cases." + module + ";\n\n");	
		//stringBuffer.append("import cases.com.*;\n");	
		stringBuffer.append("import base.com.zl.com.ComOperate;\n");		
		stringBuffer.append("import base.com.zl.utils.DataUtil;\n");		
		stringBuffer.append("import java.util.ArrayList;\n");
		stringBuffer.append("import java.util.HashMap;\n");
		stringBuffer.append("import org.openqa.selenium.WebElement;\n");
		stringBuffer.append("import org.openqa.selenium.By;\n");
		stringBuffer.append("import org.openqa.selenium.WebDriver;\n");
		stringBuffer.append("import base.com.zl.selenium.Page;\n");
		stringBuffer.append("import base.com.zl.db.DbOperate;\n");
		stringBuffer.append("import base.com.zl.db.ConfigDbOperate;\n");		
		stringBuffer.append("import java.util.Map;\n");
		stringBuffer.append("import base.com.zl.check.DbCheck;\n");
		stringBuffer.append("import base.com.zl.check.PageCheck;\n");		
		stringBuffer.append("import base.com.zl.selenium.OpWebDriver;\n");
		stringBuffer.append("import java.io.File;\n\n");
		///结束	
		return stringBuffer.toString();
		
	}
	
	
	/**
	 * 创建组件文件头信息package  import
	 * @param module
	 * @return
	 */
	public String createComHead(){
		
		
		
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append("package cases.com;\n\n");	
		stringBuffer.append("import base.com.zl.com.ComOperate;\n");		
		stringBuffer.append("import base.com.zl.utils.DataUtil;\n");		
		stringBuffer.append("import java.util.ArrayList;\n");
		stringBuffer.append("import java.util.HashMap;\n");
		stringBuffer.append("import org.openqa.selenium.WebElement;\n");
		stringBuffer.append("import org.openqa.selenium.By;\n");
		stringBuffer.append("import org.openqa.selenium.WebDriver;\n");
		stringBuffer.append("import base.com.zl.db.DbOperate;\n");
		stringBuffer.append("import base.com.zl.db.ConfigDbOperate;\n");		
		stringBuffer.append("import java.util.Map;\n");
		stringBuffer.append("import base.com.zl.check.DbCheck;\n");
		stringBuffer.append("import base.com.zl.check.PageCheck;\n");		
		stringBuffer.append("import base.com.zl.selenium.OpWebDriver;\n");
		stringBuffer.append("import base.com.zl.selenium.Page;\n");
		stringBuffer.append("import java.io.File;\n\n");
		///结束	
		return stringBuffer.toString();
		
	}
	
	
	
	
	/**
	 * 创建类变量及构造方法
	 * @param pageElement
	 * @param menuName
	 * @return
	 */
	public String createVar(String pageElement,String moduleCode){
		StringBuffer stringBuffer = new StringBuffer();

		String[] strings= new String[0];
		if(pageElement!=null){
			strings= pageElement.split("\\;");

		}

		for (int i = 0; i < strings.length; i++) {
			strings[i] = strings[i].replace("\n", "").replace("\r", "").replace(" ", "");

			if( (strings[i]==null) || (strings[i].equals("")) || (strings[i].equals("null")) ){
				continue;
			}
			
			if (strings[i].startsWith("//")) {
				stringBuffer.append(strings[i] + "\n");
			} else {
				String[] stringss = strings[i].split("=");
				stringBuffer.append("	private final String " + stringss[0].replace("\n", "").replace("\r", ""));
				System.out.println(stringss[0]);
				String a = stringss[1];
				if (stringss.length > 2) {
					a = a + "=" + stringss[2];
				}
				a = a.replace("\"", "\\\"");
				stringBuffer.append(" = \"" + a + "\";\n");
				// + "=\"" + stringss[1].replace("\"", "\\\"")
				// + "\";\n");

			}
			
		}
		stringBuffer.append("	public int cycleId=0;\n" );
		stringBuffer.append("	public String caseId;\n" );
		stringBuffer.append("	public String testDir;\n" );
		stringBuffer.append("	public WebDriver webDriver;\n");
		stringBuffer.append("	public "+moduleCode+"(){}\n");
		
		stringBuffer.append("	public "+moduleCode+"(WebDriver webDriver){\n");
		stringBuffer.append("	this.webDriver = webDriver;\n");
		stringBuffer.append("	}\n");
		
		stringBuffer.append("	public "+moduleCode+"(WebDriver webDriver,String caseId,String testDir){\n");
		stringBuffer.append("	this.webDriver = webDriver;\n");
		stringBuffer.append("	this.caseId = caseId;\n");
		stringBuffer.append("	this.testDir = testDir;\n");

		
		stringBuffer.append("	}\n");
		stringBuffer.append("\n");
	
		
		
		return stringBuffer.toString();
		
	}
	
	
	
	
	
	

	
	
	
	public String TranslateNormal(String lineContentString) throws Exception{
		
		//1.传入ABC，调用通用转义方法（抽离封装出来）

		CaseHandle caseHandle = new CaseHandle();
		lineContentString = lineContentString.trim();
		if(lineContentString==null ||lineContentString.equals("") || lineContentString.equals(" ")){
			return "			" + "";
		}
		
		if(lineContentString.startsWith("break")){
			return "			" + "break;\n";
		}
		
		if(lineContentString.contains("try{")){
			return "			" + lineContentString+"\n";

		}
		
		if(lineContentString.contains("catch")){
			return "			" + lineContentString+"\n";

		}
		
		if (lineContentString.startsWith("//")) {
			return lineContentString+"\n";

		}
		
        
		
		
		
		
		String javaString ="";
		
        if(lineContentString.contains("this.")){
			return "			" + lineContentString+ ";\n";
		   //增加BCE规则校验特殊处理 ,取消
		}/*else if(lineContentString.contains("menucheck")){
		
			
			//return "			" + "resultMap = comOp."+lineContentString.split("\\)")[0]+",caseId)"
					return "			" + "resultMap = comOp."+lineContentString+ ";\n";
		}*/else if (lineContentString.contains("=\"")){
				return "			String "+ lineContentString + ";\n";


			
		} else  {

			

				
				
				
				
			//获取配置类型合并
			String lineContentStringtemp="";
			ConfigDbOperate configDbOperate = new ConfigDbOperate();
			String paraType="";

            if(lineContentString.startsWith("update")||lineContentString.startsWith("del")||lineContentString.startsWith("insert")){
            	
				String[] lineContentStringtemps = lineContentString.split("\\(");
				lineContentStringtemp = lineContentStringtemps[0];
				paraType=configDbOperate.searchString("select t.paratype from web_config t where paracode  = '"+lineContentStringtemp+"'");
				lineContentString = paraType+"."+lineContentString;

            }else if (lineContentString.contains("com.")){
				DataUtil dataUtil = new DataUtil();
				//com.testmoduleone.testcodeone("123","456")
				String className = dataUtil.patternText(lineContentString, "com.(.*)\\(");
				String[] classmethod = className.split("\\.");
				
				StringBuffer returnScript =new StringBuffer();
				//returnScript.append("			"+classmethod[0]+" "+classmethod[0]+" = new "+classmethod[0]+"();\n");
				
				if(lineContentString.contains("=com.")){
					
					//添加返回值定义
					String returnType=configDbOperate.searchString("select t.outparam from web_component t where t.modulecode='"+classmethod[0]+"' and comcode='"+classmethod[1]+"' ");
					returnScript.append("			"+returnType+" "+lineContentString.replace("com.", "")+";\n");

				}else{
					returnScript.append("			"+lineContentString.replace("com.", "")+";\n");

				}
				
				return returnScript.toString();
				
			}else{
            	
				String[] lineContentStringtemps = lineContentString.split("=");
				if(lineContentStringtemps.length>1){
					lineContentStringtemp =lineContentStringtemps[1];
				}else{
					lineContentStringtemp =lineContentStringtemps[0];

				}
				lineContentStringtemps = lineContentStringtemp.split("\\(");
				
					lineContentStringtemp =lineContentStringtemps[0];
					System.out.println(lineContentString);
					paraType=configDbOperate.searchString("select t.paratype from web_config t where paracode  = '"+lineContentStringtemp.trim()+"'");
					lineContentString = lineContentString.replaceFirst(lineContentStringtemp, paraType+"."+lineContentStringtemp);

            }
			

			
			
			
			if (lineContentString.contains("pageOp")) {

				 javaString = caseHandle
						.transPageOpCode(lineContentString);
				return "			" + javaString + ";\n";
				
				
			} else if (lineContentString.contains("comOp")) {
				 javaString = caseHandle
						.transComOpCode(lineContentString);
				return "			" + javaString + ";\n";


			} else if (lineContentString.contains("dbOp")) {
				 javaString = caseHandle
						.transDbOpCode(lineContentString);
				return "			" + javaString + ";\n";

			} else if (lineContentString.contains("pageCheck")) {
				return "			resultMap = "
						+ lineContentString.substring(0,
								lineContentString.length() - 1)
						+ ",resultMap,caseId);\n";


			} else if (lineContentString.contains("dbCheck")) {
				 javaString = caseHandle
						.transDbCheckCode(lineContentString);
				return "			" + javaString + ";\n";
				
			}else if(lineContentString.contains("dataUtil")) {
				 javaString = caseHandle.transNormalCode(lineContentString);
					return "			" + javaString + ";\n";
			}else{
				return "			" + lineContentString+";\n";

			}

		}
		
		
		
		
		
	}
	
	//   while(n<3){
	 //  abc;abc;
	

	public String translateWhile(String content) throws Exception{
		
		
		
		String[] strings =content.split("\\{");
		StringBuffer linebuffer = new StringBuffer();
		String logicCont = strings[0];
		String lineCont = "";

		if(strings.length>1){
			 lineCont = strings[1];

		}
		DataUtil dataUtil = new DataUtil();
		String logicintString =dataUtil.patternText(content, "while\\((.*)\\)\\{");
		if(logicintString.contains("\"")){
			logicintString.replace("\"", "");
		}

		//替换前段
/*      cycle=0;
		while( cycle<6+1){
			cycle++;
        	

        */
		linebuffer.append("       cycleId=0;\n");
		linebuffer.append("       while( cycleId<"+logicintString.split("<")[1]+"){\n");
		linebuffer.append("       cycleId++;\n");
		//替换后段
		
		String[] lineConts =lineCont.split(";");
		for(int i =0;i<lineConts.length;i++){
			String line = this.TranslateNormal(lineConts[i]);
			linebuffer.append("       "+line);

		}
		
		
		
		//1.替换white条件
		//2.传入ABC，调用通用转义方法（抽离封装出来）
		return linebuffer.toString();
		
	}
	
	
	
	
	

	//if(n = < > <= >= ){
	  //abc;
	  //abc;
	public String translateIf(String content) throws Exception{
		
		//1.替换if条件
		//2.传入ABC，调用通用转义方法（抽离封装出来）
		String[] strings =content.split("\\{");
		StringBuffer linebuffer = new StringBuffer();
		String logicCont = strings[0];
		String lineCont = "";

		if(strings.length>1){
			 lineCont = strings[1];

		}
		DataUtil dataUtil = new DataUtil();
		String logicString =dataUtil.patternText(content, "if\\((.*)\\)\\{");
		if(logicString.contains("\"")){
			logicString.replace("\"", "");
			
		}
			//替换前段   < >   <=    >= =     equals contains
/*			        int a=9;
					if(a<6){
						//abc;
			        	
					}
					if(a<=6){
						//abc;
			        	
					}
					if("".equals(logicCont)){
						//abc;
			        	
					}
					if("".contains(logicCont)){
						//abc;
			        	
					}*/
		
		if(logicString.contains("<")||logicString.contains(">")){
			linebuffer.append("       "+logicCont+"{\n");			
		}else if(logicString.contains("==")){
			logicCont=logicCont.replace("==", "==");
			linebuffer.append("       "+logicCont+"{\n");			

		}else if(logicString.contains("!=")){
			logicCont=logicCont.replace("!=", "!=");
			linebuffer.append("       "+logicCont+"{\n");			

		}
		else if(logicString.contains("equals")){
			linebuffer.append("       "+logicCont+"{\n");			

		}else if(logicString.contains("contain")){
			linebuffer.append("       "+logicCont+"{\n");			

		}else{
			linebuffer.append("       "+logicCont+"{\n");	
		}
		//替换后段
		
		String[] lineConts =lineCont.split(";");
		for(int i =0;i<lineConts.length;i++){
			

			String line = this.TranslateNormal(lineConts[i]);
			linebuffer.append("       "+line);

		}
		
		return linebuffer.toString();
		
	}
	
	
	
	

	  // }else if(a>b){
	   //abc;
	public String translateElseIf(String content) throws Exception{
		
		String[] strings =content.split("\\{");
		StringBuffer linebuffer = new StringBuffer();
		String logicCont = strings[0];
		String lineCont = "";

		if(strings.length>1){
			 lineCont = strings[1];

		}
		DataUtil dataUtil = new DataUtil();
		String logicString =dataUtil.patternText(content, "\\}else if\\((.*)\\).*\\{");
		if(logicString.contains("\"")){
			logicString.replace("\"", "");
			
		}


		
		
		if(logicString.contains("<")||logicString.contains(">")||logicString.contains("=")){
			linebuffer.append("       "+logicCont+"{\n");			
		}else if(logicString.contains("equals")){
			linebuffer.append("       "+logicCont+"{\n");			

		}else if(logicString.contains("contain")){
			linebuffer.append("       "+logicCont+"{\n");			

		}
		//替换后段
		
		String[] lineConts =lineCont.split(";");
		for(int i =0;i<lineConts.length;i++){
			String line = this.TranslateNormal(lineConts[i]);
			linebuffer.append("       "+line);

		}
		
		return linebuffer.toString();
		
		
		

	}
	
      //}else{
      //abc;
	public String translateElse(String content) throws Exception{

		
		String[] strings =content.split("\\{");
		StringBuffer linebuffer = new StringBuffer();
		String logicCont = strings[0];
		String lineCont = "";

		if(strings.length>1){
			 lineCont = strings[1];

		}
		DataUtil dataUtil = new DataUtil();


		linebuffer.append("       }else{\n");			

		//替换后段
		
		String[] lineConts =lineCont.split(";");
		for(int i =0;i<lineConts.length;i++){
			String line = this.TranslateNormal(lineConts[i]);
			linebuffer.append("       "+line);

		}
		
		return linebuffer.toString();
		
		
		

	
	}
	
	
	// for(WebElement w:list){  //高级对象使用
	  // abc;
	public String translateFor(String content) throws Exception{
		
		String[] strings =content.split("\\{");
		StringBuffer linebuffer = new StringBuffer();
		String logicCont = strings[0];
		String lineCont="";
		if(strings.length>1){
		 lineCont = strings[1];
		}
		
		linebuffer.append("       "+logicCont+"{\n");			
		//替换后段		
		String[] lineConts =lineCont.split(";");
		for(int i =0;i<lineConts.length;i++){
			if(!lineConts[i].isEmpty()){
				
			
			String line = this.TranslateNormal(lineConts[i]);
			linebuffer.append("       "+line);
			}
		}		
		return linebuffer.toString();
		
	}
	
	
	public static void main(String[] s) throws Exception{
		TranslateLogic translateLogic = new TranslateLogic();
		//translateLogic.translateHalf("}}abc;abc;");
/*		translateLogic.translateHalf("}}}");
*
*
*/        
		 String ss="  number=getnumberbykey(sqlKey)";
		 translateLogic.TranslateNormal(ss);
	}
	//}}abc;abc; 或者 }abc;abc;
	public String translateHalf(String content) throws Exception{
		DataUtil dataUtil = new DataUtil();
		String logicCont = "";
		String lineCont = dataUtil.patternText(content, "\\}((?!\\}).*)");
		logicCont = content.replace(lineCont,"");

		if(lineCont.length()==0){
		return content;	
		}
		
		StringBuffer linebuffer = new StringBuffer();
		linebuffer.append("       "+logicCont+"\n");			

		//替换后段		
		String[] lineConts =lineCont.split(";");
		for(int i =0;i<lineConts.length;i++){
			String line = this.TranslateNormal(lineConts[i]);
			linebuffer.append("       "+line);

		}		
		return linebuffer.toString();
		
	}
	
	
//	// } 或者}}
//	public String translateEnd(String content){
//		return content;
//		
//	}
	


}
