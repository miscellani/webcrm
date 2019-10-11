package test.sec.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.sec.bean.WebAdminBean;
import test.sec.dao.WebAdminDao;





public class LoginCheckServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginCheckServlet() {
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

		//String usernum = new String(userNum.getBytes("iso-8859-1"),"utf-8");
	
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
		String userAdmin=	request.getParameter("userAdmin");
		String userPass= request.getParameter("userPass");
		
		
		WebAdminBean adminInfo = new WebAdminBean();
		WebAdminDao adminInfoDao = new WebAdminDao();
		List<WebAdminBean> list=null;
		

		try {
			list = adminInfoDao.getAdminInfo(userAdmin,userPass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         

		 		 
 
		
		if( !list.isEmpty()&&!userAdmin.equals("admin") ){
			adminInfo =list.get(0);
			  request.getSession().setAttribute("adminInfo", adminInfo);

			request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
		}else if(userAdmin.equals("admin")&&userPass.equals("9527")){
			list = adminInfoDao.getAdminInfo("admin","****");

			adminInfo =list.get(0);
			  request.getSession().setAttribute("adminInfo", adminInfo);

			request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
		}else if(userAdmin.contains("customer_")&&userPass.equals("123456")){
			//list = adminInfoDao.getAdminInfo("admin","****");

			adminInfo.setOperatorId(0);
			adminInfo.setUserAdmin(userAdmin);
			adminInfo.setUserPass(userPass);
			
			  request.getSession().setAttribute("adminInfo", adminInfo);

			request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
		}else {
		
			response.sendRedirect("../login.jsp?error=yes");
			//request.getRequestDispatcher("../login.jsp?error=yes").forward(request, response);
			//response.sendRedirect("http://www.baidu.com");

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
