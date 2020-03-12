package test.web.servlet.cases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.com.zl.db.ConfigDbOperate;
import base.com.zl.utils.DataUtil;
import base.com.zl.utils.FileUtil;
import test.init.CaseHandle;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.dao.WebCaseDao;
import test.web.com.bean.WebComponentBean;
import test.web.com.dao.WebComponentDao;




public class CaseReleaseServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CaseReleaseServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String caseId = request.getParameter("caseId");

		FileUtil fileUtil = new FileUtil();
		CaseHandle caseHandle = new CaseHandle();

		WebCaseDao webCaseDao = new WebCaseDao();
		ArrayList<WebCaseBean> list = null;
		try {
			list = webCaseDao.getReleaseCase(caseId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String rootPath = System.getProperty("catalina.home");
		if (rootPath != null) {
			rootPath = rootPath + File.separator + "webapps" + File.separator + "webcrm"
					+ File.separator + "WEB-INF";

		} else {
			rootPath = System.getProperty("user.dir") + File.separator + "html"
					+ File.separator + "WEB-INF";

		}
		
		WebCaseBean webCaseBean = (WebCaseBean) list.get(0);
		String module = webCaseBean.getModule();
		String menuCode=webCaseBean.getMenuCode();
		
		//String	 pathFile = rootPath + File.separator + "classes"+ File.separator + "test" + File.separator + "web"+ File.separator + "cases" + File.separator + module+ File.separator + menuCode;
		String	 pathFile = rootPath + File.separator + "classes"+ File.separator +  "cases" + File.separator + module+ File.separator + menuCode;

		
		
		
		String  compilerResult="";
		String message="";
		try {			
			caseHandle.createClassFile(list, pathFile+".java");
			
			
			
			
			
			DataUtil dataUtil = new DataUtil();
			//获取脚本文件中所有有调用的组件类名 ，编译时一起编译
			ArrayList<String> comlist = new ArrayList<String>();
			
			for(WebCaseBean webCaseBean2:list){
				String opstep =webCaseBean2.getOpStep();
				String[] opstepsplit = opstep.split("\\;");
				for(int i=0;i<opstepsplit.length;i++){
					String classmethod = dataUtil.patternText(opstepsplit[i], "com.(.*)\\(");  
					String classname = classmethod.split("\\.")[0];
					
					if(  (!classmethod.equals("") )&& (!comlist.contains(classname) ) ){
						System.out.println(classname);
						
						comlist.add(classname);

					}
				}
				
				
				
				
				//System.out.println(className);
				//String[] classmethod = className.split("\\.");
				
			}
			
			
			//CASE编译之前判断是否调用的组件已经编译发布
			WebComponentDao webComponentDao = new WebComponentDao();
			for(String commodule : comlist){
				
				String noRelease = webComponentDao.isRelease(commodule);
				if( !noRelease.equals("")){
					request.setAttribute("result", "CASE发布失败,请发布组件："+noRelease);
    				request.getRequestDispatcher("/jsp/caseManage/case_save.jsp").forward(request, response);
    				break;
				}
				
				
			}
			
			
			
			
			
			

			compilerResult = caseHandle.compilerJavaFile(rootPath,module,pathFile,comlist);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					/*DataUtil dataUtil = new DataUtil();
					message=dataUtil.getTrace(e);*/
					compilerResult ="脚本转义失败，请检查脚本内容";
				}

                  if(compilerResult.equals("true")){
          			try {
						webCaseDao.updateReleaseCase(caseId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("result", "CASE发布成功");
    				request.getRequestDispatcher("/jsp/caseManage/case_save.jsp").forward(request, response);

                  }else{
                	compilerResult = new DataUtil().replaceBlank(compilerResult);
  					request.setAttribute("result", "发布失败 : "+compilerResult);
      				request.getRequestDispatcher("/jsp/caseManage/case_save.jsp").forward(request, response);

                  }



		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
