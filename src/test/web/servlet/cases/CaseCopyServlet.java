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



public class CaseCopyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CaseCopyServlet() {
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


		request.getRequestDispatcher("/jsp/caseManage/case_copy.jsp").forward(
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
			throws ServletException, IOException {/*

		request.setCharacterEncoding("utf-8");
		

		WebCaseBean caseInfo = new WebCaseBean();
		caseInfo.setCaseId(Integer.parseInt(request.getParameter("caseid")));
	      caseInfo.setModule(request.getParameter("module"));
	      caseInfo.setMenuName(request.getParameter("menuName"));
	      caseInfo.setCaseLevel(request.getParameter("caseLevel"));
			String menuCode = request.getParameter("menuCode");
			menuCode =menuCode.substring(0, 1).toUpperCase() + menuCode.substring(1).toLowerCase();
			caseInfo.setMenuCode(menuCode);	      
			caseInfo.setCaseCode(request.getParameter("caseCode"));
	      caseInfo.setPageElement(request.getParameter("pageElement"));
	     
	      String opStep =request.getParameter("opStep");
	      caseInfo.setOpStep(opStep);
	      String checkStep =request.getParameter("checkStep");

	      caseInfo.setCheckStep(checkStep);
	      caseInfo.setTester(request.getParameter("tester"));
	      caseInfo.setTester("未执行");


		

		WebCaseDao caseInfoDao = new WebCaseDao();
		HashMap map = new HashMap();
		try {
			map = caseInfoDao.updateOneCase(caseInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



			request.setAttribute("result", map.get("message"));


		//request.getRequestDispatcher("/view/customer/customer_save.jsp").forward(request, response);
	*/}


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
