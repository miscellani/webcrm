package test.web.servlet.work;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.web.cases.dao.WebCaseExecuteDao;

public class WorkObserveTwoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WorkObserveTwoServlet() {
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

		WebCaseExecuteDao caseInfoExecuteDao = new WebCaseExecuteDao();
		String[] feeStrings= new String[3];
		try {
			feeStrings= caseInfoExecuteDao.getoverFee();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("caseId", feeStrings[0]);
		request.setAttribute("overFee", feeStrings[1]);	
		request.setAttribute("outTime", feeStrings[2]);	
		
 	
			request.getRequestDispatcher("/jsp/work/work_observe_two.jsp").forward(request, response);
			
			


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
