package test.web.servlet.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.dao.WebCaseDao;
import test.web.com.bean.WebComponentBean;
import test.web.com.dao.WebComponentDao;


public class ComUpdateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComUpdateServlet() {
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


		int comId = Integer.parseInt(request.getParameter("comId"));

		WebComponentDao webComponentDao = new WebComponentDao();
		WebComponentBean comInfo = null;
			try {
				comInfo = webComponentDao.getOneComInfo(comId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("comInfo", comInfo);
			request.setAttribute("execute", "no");


		request.getRequestDispatcher("/jsp/comdev/comdev_update.jsp").forward(
				request, response);
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
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		WebComponentBean comInfo = new WebComponentBean();
		comInfo.setComId(Integer.parseInt(request.getParameter("comId")));
		comInfo.setModuleCode((request.getParameter("moduleCode")));
		comInfo.setComCode((request.getParameter("comCode")));
		comInfo.setComName(request.getParameter("comName"));
		comInfo.setOpStep(request.getParameter("opStep"));
		comInfo.setPageElement(request.getParameter("pageElement").isEmpty()?"":request.getParameter("pageElement"));
		comInfo.setTester(request.getParameter("tester"));
		comInfo.setParamName(request.getParameter("paramName").isEmpty()?"":request.getParameter("paramName"));
		comInfo.setParamValue(request.getParameter("paramValue").isEmpty()?"":request.getParameter("paramValue"));
		comInfo.setOutParam(request.getParameter("outParam").isEmpty()?"":request.getParameter("outParam"));
		comInfo.setIsPrivate(request.getParameter("isPrivate"));		
		comInfo.setResult(request.getParameter("result"));

		
		HashMap map = new HashMap();
		
		

		//校验元素页
		String pageelement =request.getParameter("pageElement").isEmpty()?"":request.getParameter("pageElement");					
		//校验操作页
		String oppage =request.getParameter("opStep");		
	
		if(pageelement.contains("；")||pageelement.contains("“")||pageelement.contains("‘")){
			
			
			request.setAttribute("result", "页面编辑栏包含全角分号或双引号或单引号，请排查!");
			request.setAttribute("comInfo", comInfo);

			request.getRequestDispatcher("/jsp/comdev/comdev_update.jsp").forward(request, response);

			
		}else if(oppage.contains("；")||oppage.contains("“")||oppage.contains("‘")){
			
			request.setAttribute("result", "操作栏包含全角分号或双引号或单引号，请排查!");
			request.setAttribute("comInfo", comInfo);
			request.getRequestDispatcher("/jsp/comdev/comdev_update.jsp").forward(request, response);

			
		}else{
			WebComponentDao webComponentDao = new WebComponentDao();

			map.put("result", true);
			try {
				map = webComponentDao.updateOneComponent(comInfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("result", false);

			}
		
		
			if ((Boolean) map.get("result")) {
				request.setAttribute("result", "组件修改成功,请重新发布调用关系的组件和CASE");
				request.getRequestDispatcher("/jsp/caseManage/case_save.jsp").forward(request, response);

			}else{
				request.setAttribute("result", map.get("message"));
				request.setAttribute("comInfo", comInfo);
				request.getRequestDispatcher("/jsp/comdev/comdev_update.jsp").forward(request, response);

			}
		
		}
		
		

		
		

		
		
	}


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
