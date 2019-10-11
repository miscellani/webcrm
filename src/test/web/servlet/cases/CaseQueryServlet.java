package test.web.servlet.cases;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.sec.bean.WebAdminBean;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.dao.WebCaseDao;
import test.web.cases.dao.WebCaseExecuteDao;



public class CaseQueryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CaseQueryServlet() {
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

		doPost(request, response);

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
		
		
		String execute = request.getParameter("execute");
		
		if(execute!=null&&execute.equals("yes")){
			
			try {
				
				request.setCharacterEncoding("utf-8");
				String module = request.getParameter("module");
				String menuName = request.getParameter("menuName");
				String caseName = request.getParameter("caseName");
				String tester = request.getParameter("tester");
				String caseLevel = request.getParameter("caseLevel");
				String result = request.getParameter("result");
				String running = request.getParameter("running");

/*				try {
					module = new String(module.getBytes("ISO-8859-1"), "UTF-8");
					menuName = new String(menuName.getBytes("ISO-8859-1"), "UTF-8");
					caseName = new String(caseName.getBytes("ISO-8859-1"), "UTF-8");
					caseLevel = new String(caseLevel.getBytes("ISO-8859-1"), "UTF-8");
					tester = new String(tester.getBytes("ISO-8859-1"), "UTF-8");
					result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
									
				} catch (Exception e) {
					// TODO: handle exception
				}*/


				
				
				WebCaseExecuteDao caseInfoDao = new WebCaseExecuteDao();			
				List<WebCaseBean>  list=  caseInfoDao.getCaseInfo(module, menuName,caseName, tester, caseLevel, result);			
				request.setAttribute("module", module);	
				request.setAttribute("menuName", menuName);
				request.setAttribute("caseName", caseName);	
				request.setAttribute("caseLevel", caseLevel);	
				request.setAttribute("tester", tester);	
				request.setAttribute("result", result);	
				request.setAttribute("list", list);	
							
				request.setAttribute("running", running);	
			
				
				request.getRequestDispatcher("/jsp/work/work_operate.jsp").forward(request, response);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			


		
		
		
		
		try {
			
			request.setCharacterEncoding("utf-8");
	
			String searchValue = request.getParameter("searchValue");
			String optionString = request.getParameter("optionString");
			try {

				searchValue = new String(searchValue.getBytes("ISO-8859-1"), "UTF-8");
				optionString = new String(optionString.getBytes("ISO-8859-1"), "UTF-8");
			} catch (Exception e) {
				// TODO: handle exception
			}

			String  self  =  (String) request.getParameter("self");
			WebAdminBean  adminInfo  =   (WebAdminBean)request.getSession().getAttribute("adminInfo");
			String admin = adminInfo.getUserAdmin();
			if( self!=null&&self.equals("yes")){
				searchValue=admin;
				optionString="5";
			}
			
			WebCaseDao caseInfoDao = new WebCaseDao();			
			List<WebCaseBean>  list=  caseInfoDao.getCaseInfo(searchValue, optionString);			
			request.setAttribute("list", list);
			request.setAttribute("searchValue", searchValue);		
			request.setAttribute("optionString", optionString);		
			
			request.getRequestDispatcher("/jsp/caseManage/case_list.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
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
