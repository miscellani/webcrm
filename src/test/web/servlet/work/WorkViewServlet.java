package test.web.servlet.work;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WorkViewServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WorkViewServlet() {
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

		
		
		request.getRequestDispatcher("/jsp/work/work_frame_iframe.jsp").forward(request, response);

/*		try {
			
			request.setCharacterEncoding("utf-8");
			String searchValue = request.getParameter("searchValue");
			try {
				searchValue = new String(searchValue.getBytes("ISO-8859-1"), "UTF-8");

			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println(searchValue);

			String optionString = request.getParameter("optionString");
			String  UserId  =  request.getParameter("userId");

			
			
			CaseInfoDao caseInfoDao = new CaseInfoDao();			
			List<CaseInfo>  list=  caseInfoDao.getCaseInfo(searchValue, optionString);			
			request.setAttribute("list", list);
					
			request.getRequestDispatcher("/view/customer/customer_execute.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/

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
