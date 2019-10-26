package test.web.servlet.com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import base.com.zl.utils.DataUtil;
import base.com.zl.utils.FileUtil;
import test.init.CaseHandle;
import test.init.ComHandle;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.dao.WebCaseDao;
import test.web.com.bean.WebComponentBean;
import test.web.com.dao.WebComponentDao;




public class ComReleaseServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComReleaseServlet() {
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
		
		String comId = request.getParameter("comId");

		ComHandle comHandle = new ComHandle();
		CaseHandle caseHandle = new CaseHandle();
		WebComponentDao webComponentDao = new WebComponentDao();
		ArrayList<WebComponentBean> list = null;
		try {
			list = webComponentDao.getReleaseComponent(comId);
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
		
		WebComponentBean webComponentBean = (WebComponentBean) list.get(0);
		String module = webComponentBean.getModuleCode();
		String comCode=webComponentBean.getComCode();
		
		//String	 pathFile = rootPath + File.separator + "classes"+ File.separator + "test" + File.separator + "web"+ File.separator + "cases" + File.separator + module+ File.separator + menuCode;
		String	 pathFile = rootPath + File.separator + "classes"+ File.separator +  "cases" + File.separator + "com" + File.separator +module;

		
		
		
		String  compilerResult="";
		String message="";
		try {
			comHandle.createClassFile(list, pathFile+".java");
			DataUtil dataUtil = new DataUtil();
			//获取脚本文件中所有有调用的组件类名 ，编译时一起编译
			ArrayList<String> comlist = new ArrayList<String>();
			
			for(WebComponentBean webComponentBean2:list){
				String opstep =webComponentBean2.getOpStep();
				String[] opstepsplit = opstep.split("\\;");
				for(int i=0;i<opstepsplit.length;i++){
					String classmethod = dataUtil.patternText(opstepsplit[i], "com.(.*)\\(");  
					String classname = classmethod.split("\\.")[0];
					
					if(  (!classmethod.equals("") )&& (!comlist.contains(classname) ) ){
						System.out.println(classname);
						
						comlist.add(classname);

					}
				}
		
			}
			
			//将comlist中的class文件修改名字为备份
			System.out.println("comlist:"+comlist);
			compilerResult = caseHandle.compilerJavaFile(rootPath,module,pathFile,comlist);
			

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					/*DataUtil dataUtil = new DataUtil();
					message=dataUtil.getTrace(e);*/
					compilerResult ="脚本转义失败，请检查脚本内容";
					//将comlist中的class文件修改名字改回到原名字
				}

                  if(compilerResult.equals("true")){
                	  
                	  //将comlist中备份的class文件删除
                	  
                	  
          			try {
          				webComponentDao.updateReleaseComponent(comId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("result", "组件发布成功");
    				request.getRequestDispatcher("/jsp/caseManage/case_save.jsp").forward(request, response);

                  }else{
                	  compilerResult = new DataUtil().replaceBlank(compilerResult);
  					request.setAttribute("result", "组件发布失败 : "+compilerResult);
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
