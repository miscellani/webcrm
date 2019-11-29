package test.web.servlet.com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import base.com.zl.db.ConfigDbOperate;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.dao.WebCaseDao;
import test.web.com.bean.WebComponentBean;
import test.web.com.dao.WebComponentDao;




public class ComAddServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComAddServlet() {
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
		
		request.getRequestDispatcher("/jsp/comdev/comdev_add.jsp").forward(request, response);

		
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

		WebComponentBean webComponentBean = new WebComponentBean();
		String op =request.getParameter("op");

		String moduleCode = request.getParameter("moduleCode");
		//moduleCode =moduleCode.substring(0, 1).toUpperCase() + moduleCode.substring(1).toLowerCase();
		webComponentBean.setModuleCode(moduleCode);
		
		String comCode = request.getParameter("comCode");
		webComponentBean.setComCode(comCode);
		
		webComponentBean.setTester(request.getParameter("tester"));
		webComponentBean.setComName(request.getParameter("comName"));
		webComponentBean.setParamName(request.getParameter("paramName"));
		webComponentBean.setParamValue(request.getParameter("paramValue"));	
		webComponentBean.setOutParam(request.getParameter("outParam"));		
		webComponentBean.setIsPrivate(request.getParameter("isPrivate"));			
		webComponentBean.setPageElement(request.getParameter("pageElement").isEmpty()?"":request.getParameter("pageElement"));
		webComponentBean.setResult("未发布");
		
	      String opStep =request.getParameter("opStep");
	      opStep = opStep.replace("'\"+", "'||chr(39)||'\"'||chr(43)||'").replace("+\"'", "'||chr(43)||'\"'||chr(39)||'");
	      webComponentBean.setOpStep(opStep);
	      
	      


        //校验新增的meucode是否存在
		//if( (op!=null) && (!op.equals("copy")) ){
		if( op.equals("add")) {
		
		ConfigDbOperate dbOperate = new ConfigDbOperate();
		ArrayList<String> stringss = null;
		try {
			stringss = dbOperate.searchStrings("select  moduleCode from web_component where moduleCode='"+moduleCode+"' and comCode='"+comCode+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(stringss.size()>0){
			request.setAttribute("result", "已存在的组件模块+组件编码,请修改组件编码");
			request.setAttribute("cominfo", webComponentBean);

			request.getRequestDispatcher("/jsp/comdev/comdev_add.jsp").forward(request, response);
			return;
		}
		
		
		
		
		}
		
		WebComponentDao webComponentDao = new WebComponentDao();
		HashMap map = new HashMap();
		map.put("result", true);

		try {
			map = webComponentDao.addOneComponent(webComponentBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", false);

		}


		if ((Boolean) map.get("result")) {
			request.setAttribute("result", "组件添加成功");
			request.getRequestDispatcher("/jsp/caseManage/case_save.jsp").forward(request, response);

		}else{
			request.setAttribute("result", map.get("组件保存失败"));
			request.setAttribute("cominfo", webComponentBean);

			request.getRequestDispatcher("/jsp/comdev/comdev_add.jsp").forward(request, response);

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
