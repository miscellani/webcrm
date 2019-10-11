package test.web.servlet.cases;

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


public class CaseUpdateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CaseUpdateServlet() {
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


		int caseId = Integer.parseInt(request.getParameter("caseid"));

		WebCaseDao caseInfoDao = new WebCaseDao();
		WebCaseBean caseInfo = null;
			try {
				 caseInfo = caseInfoDao.getOneCaseInfo(caseId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("caseinfo", caseInfo);
			request.setAttribute("execute", "no");


		request.getRequestDispatcher("/jsp/caseManage/case_update.jsp").forward(
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
		
		WebCaseBean caseInfo = new WebCaseBean();
		caseInfo.setCaseId(Integer.parseInt(request.getParameter("caseId")));
		caseInfo.setModule(request.getParameter("module"));
		caseInfo.setMenuName((request.getParameter("menuName")));
		caseInfo.setCaseName(request.getParameter("caseName"));
		caseInfo.setCaseLevel(request.getParameter("caseLevel"));
		String menuCode = request.getParameter("menuCode");
		menuCode =menuCode.substring(0, 1).toUpperCase() + menuCode.substring(1).toLowerCase();
		caseInfo.setMenuCode(menuCode);
		caseInfo.setCaseCode(request.getParameter("caseCode"));
		caseInfo.setPageElement(request.getParameter("pageElement").isEmpty()?"":request.getParameter("pageElement"));
		caseInfo.setOpStep(request.getParameter("opStep"));
		caseInfo.setCheckStep(request.getParameter("checkStep").isEmpty()?"":request.getParameter("checkStep"));
		caseInfo.setTester(request.getParameter("tester"));
		caseInfo.setParamName(request.getParameter("paramName").isEmpty()?"":request.getParameter("paramName"));
		caseInfo.setParamValue(request.getParameter("paramValue").isEmpty()?"":request.getParameter("paramValue"));

		
		HashMap map = new HashMap();
		
		

		//校验元素页
		String pageelement =request.getParameter("pageElement").isEmpty()?"":request.getParameter("pageElement");					
		//校验操作页
		String oppage =request.getParameter("opStep");		
		//校验校验页
		String checkpage =request.getParameter("checkStep").isEmpty()?"":request.getParameter("checkStep");
		
		if(pageelement.contains("；")||pageelement.contains("“")||pageelement.contains("‘")){
			
			
			request.setAttribute("result", "页面编辑栏包含全角分号或双引号或单引号，请排查!");
			request.setAttribute("caseinfo", caseInfo);

			request.getRequestDispatcher("/jsp/caseManage/case_update.jsp").forward(request, response);

			
		}else if(oppage.contains("；")||oppage.contains("“")||oppage.contains("‘")){
			
			request.setAttribute("result", "操作栏包含全角分号或双引号或单引号，请排查!");
			request.setAttribute("caseinfo", caseInfo);
			request.getRequestDispatcher("/jsp/caseManage/case_update.jsp").forward(request, response);

			
		}else if(checkpage.contains("；")||checkpage.contains("“")||checkpage.contains("‘")){
			
			
			request.setAttribute("result", "校验栏包含全角分号或双引号或单引号，请排查!");
			request.setAttribute("caseinfo", caseInfo);
			request.getRequestDispatcher("/jsp/caseManage/case_update.jsp").forward(request, response);

		
		
		
		}else{
			WebCaseDao caseInfoDao = new WebCaseDao();

			map.put("result", true);
			try {
				map = caseInfoDao.updateOneCase(caseInfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("result", false);

			}
		
		
			if ((Boolean) map.get("result")) {
				request.setAttribute("result", "CASE修改成功");
				request.getRequestDispatcher("/jsp/caseManage/case_save.jsp").forward(request, response);

			}else{
				request.setAttribute("result", map.get("message"));
				request.setAttribute("caseinfo", caseInfo);

				request.getRequestDispatcher("/jsp/caseManage/case_update.jsp").forward(request, response);

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
