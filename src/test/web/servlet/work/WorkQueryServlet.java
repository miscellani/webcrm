package test.web.servlet.work;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.web.cases.dao.WebCaseDao;


public class WorkQueryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WorkQueryServlet() {
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

		
	    request.setCharacterEncoding("utf-8");
	    WebCaseDao caseInfoDao = new WebCaseDao();
	    String[] s=new String[2];
			try {
				 s =caseInfoDao.getCaseDivide();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    
				request.setAttribute("divide", s);
		request.getRequestDispatcher("/jsp/caseManage/case_observe_divide.jsp").forward(request, response);
		
			 
			// request.getRequestDispatcher("/view/frame/center.jsp").forward(request, response);
			 
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
/*			
				request.setCharacterEncoding("utf-8");
				String  addTime=request.getParameter("addTime");
				
				String  addTime1=request.getParameter("addTime1");
				
				String  addTime2=request.getParameter("addTime2");
				
				try {


					
					 request.getRequestDispatcher("/view/frame/center.jsp").forward(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				
		

			
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
