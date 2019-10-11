package test.sec.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.sec.bean.WebAdminBean;
import test.sec.dao.WebAdminDao;




public class OperatorUpdateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OperatorUpdateServlet() {
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
		
		int operatorId = Integer.parseInt(request.getParameter("operatorId"));

		WebAdminDao adminInfoDao = new WebAdminDao();
		WebAdminBean adminInfo = null;
			try {
				adminInfo = adminInfoDao.getOneAdminInfo(operatorId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			request.setAttribute("adminInfo", adminInfo);


		request.getRequestDispatcher("/jsp/sec/operator_update.jsp").forward(
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
		// 获取页面文本框的值
		String userAdmin = request.getParameter("userAdmin");
		String userPass = request.getParameter("userPass");
		String userName = request.getParameter("userName");
		String userMobile = request.getParameter("userMobile");
		String userStaff = request.getParameter("userStaff");
		String userEmail = request.getParameter("userEmail");
		String isUsed = request.getParameter("isUsed");
		String operatorId=request.getParameter("operatorId");
		
		//创建员工对象
		WebAdminBean adminInfo = new WebAdminBean();
		
		//将值传入
		
		adminInfo.setUserAdmin(userAdmin);
		adminInfo.setUserPass(userPass);
		adminInfo.setUserName(userName);
		adminInfo.setUserMobile(userMobile);
		adminInfo.setUserStaff(userStaff);
		adminInfo.setUserEmail(userEmail);
		adminInfo.setIsUsed(isUsed);
		adminInfo.setOperatorId(Integer.parseInt(operatorId));
		
		
        WebAdminDao adminInfoDao = new WebAdminDao();
		boolean mark = false;
		try {
			mark = adminInfoDao.updateOneAdmin(adminInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (mark){		
			request.setAttribute("result", "操作员修改成功");
		}else{
			request.setAttribute("result", "操作员修改失败");
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
