package test.sec.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.sec.bean.WebAdminBean;
import test.sec.dao.WebAdminDao;


public class OperatorDeleteServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OperatorDeleteServlet() {
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

		//将String转换为int
		int operatorId =Integer.parseInt(request.getParameter("operatorId"));
		WebAdminDao adminInfoDao = new WebAdminDao();
		boolean mark = false;
		try {
			mark = adminInfoDao.deleteOneAdmin(operatorId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebAdminBean adminInfo = new WebAdminBean();
		
		if(mark){
			request.setAttribute("result", "员工删除成功");
		}else{
			request.setAttribute("result", "员工删除失败");
		}
		request.getRequestDispatcher("/jsp/sec/operator_save.jsp").forward(request, response);
		
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
