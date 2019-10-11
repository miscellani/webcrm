package test.web.servlet.com;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.sec.bean.WebAdminBean;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.dao.WebCaseDao;
import test.web.com.bean.WebComponentBean;
import test.web.com.dao.WebComponentDao;

public class ComQueryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComQueryServlet() {
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
		
		//String  self  =  (String) request.getParameter("self");
	//	request.setAttribute("self", self);
		
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
			optionString="4";
		}
		
		WebComponentDao webComponentDao = new WebComponentDao();			
		List<WebComponentBean> list;

			list = webComponentDao.getComInfo(searchValue, optionString);
		
		request.setAttribute("list", list);
		request.setAttribute("searchValue", searchValue);		
		request.setAttribute("optionString", optionString);		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		request.getRequestDispatcher("/jsp/comdev/comdev_list.jsp").forward(request, response);
	
			

		
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
