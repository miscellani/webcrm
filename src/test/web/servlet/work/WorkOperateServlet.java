package test.web.servlet.work;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.sec.bean.WebAdminBean;
import test.sec.dao.WebAdminDao;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.dao.WebCaseExecuteDao;


public class WorkOperateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WorkOperateServlet() {
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
		WebAdminBean adminInfo = new WebAdminBean();
		WebAdminDao adminInfoDao = new WebAdminDao();
		adminInfo=   (WebAdminBean) request.getSession().getAttribute("adminInfo");
		try {
			
			request.setCharacterEncoding("utf-8");
			String searchValue = request.getParameter("searchValue");
			try {
				searchValue = new String(searchValue.getBytes("ISO-8859-1"), "UTF-8");

			} catch (Exception e) {
				// TODO: handle exception
				
				searchValue =adminInfo.getUserAdmin();
			}

			//String optionString = request.getParameter("optionString");
		//	String  UserId  =  request.getParameter("userId");

			
			
			
			//查询数据库获取当前执行状态，以及各种数据

			
			
			WebCaseExecuteDao caseInfoDao = new WebCaseExecuteDao();			
			List<WebCaseBean>  list=  caseInfoDao.getCaseInfo(null, null, null, searchValue, null, null)	;	
			request.setAttribute("list", list);
					String string = "no";
			if(  !(caseInfoDao.getRunningStatus().equals("1"))  ){
				string="yes";
			}
			request.setAttribute("running", string);


			request.setAttribute("adminInfo", adminInfo);
			request.getRequestDispatcher("/jsp/work/work_operate.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
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
			throws ServletException, IOException {
		

		
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
