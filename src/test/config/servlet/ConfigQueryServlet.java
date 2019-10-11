package test.config.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.config.bean.WebConfigBean;
import test.config.dao.WebConfigDao;



public class ConfigQueryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ConfigQueryServlet() {
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


		
		request.setCharacterEncoding("utf-8");
		String paraType = request.getParameter("paraType");		
		if(paraType==null){
			paraType="";
		}
		String paraCode = request.getParameter("paraCode");
		if(paraCode==null){
			paraCode="";
		}
		String remark = request.getParameter("remark");

	//	String remark = new String(request.getParameter("remark").getBytes("ISO-8859-1"), "utf-8");

		if(remark==null){
			remark="";
		}
			paraType = new String(paraType.getBytes("ISO-8859-1"), "UTF-8");
			paraCode = new String(paraCode.getBytes("ISO-8859-1"), "UTF-8");
			//remark = new String(remark.getBytes("ISO-8859-1"), "GBK");



		
			ArrayList<WebConfigBean>  list = new ArrayList<WebConfigBean>() ;
		WebConfigDao configInfoDao = new WebConfigDao();			
		try {
			list=  (ArrayList<WebConfigBean>) configInfoDao.getConfigInfo(paraType, paraCode, remark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.setAttribute("list", list);				
		request.setAttribute("paraType", paraType);
		request.setAttribute("paraCode", paraCode);		
		request.setAttribute("remark", remark);								
		request.getRequestDispatcher("/jsp/config/config_list.jsp").forward(request, response);
		




		
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
