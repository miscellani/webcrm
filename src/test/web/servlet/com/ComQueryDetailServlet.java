package test.web.servlet.com;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import base.com.zl.db.ConfigDbOperate;
import base.com.zl.utils.FileUtil;
import test.web.WebInit;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.dao.WebCaseDao;
import test.web.cases.dao.WebCaseExecuteDao;
import test.web.com.bean.WebComponentBean;
import test.web.com.dao.WebComponentDao;



public class ComQueryDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComQueryDetailServlet() {
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

		int comId = Integer.parseInt(request.getParameter("comId"));
		String execute = (String)request.getParameter("execute");
		WebComponentDao webComponentDao = new WebComponentDao();
		WebComponentBean comInfo = null;



			try {
				comInfo=webComponentDao.getOneComInfo(comId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		request.setAttribute("comId", comId);
		request.setAttribute("execute", execute);
		request.setAttribute("comInfo", comInfo);
		
        //页面判断是否要追加记录字段
		//request.getRequestDispatcher("/jsp/caseManage/case_detail.jsp").forward(request, response);
		
		request.getRequestDispatcher("/jsp/comdev/comdev_update.jsp").forward(request, response);
	
		
		
		
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
