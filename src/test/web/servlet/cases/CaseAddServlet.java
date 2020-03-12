package test.web.servlet.cases;

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




public class CaseAddServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CaseAddServlet() {
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
		
		//  request.getRequestDispatcher("/jsp/caseManage/case_add.jsp").forward(request, response);

		request.getRequestDispatcher("/jsp/caseManage/testpop.jsp").forward(request, response);
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

		WebCaseBean caseInfo = new WebCaseBean();

		caseInfo.setModule(request.getParameter("module"));
		caseInfo.setMenuName(request.getParameter("menuName"));
		caseInfo.setCaseName(request.getParameter("caseName"));
		caseInfo.setCaseLevel(request.getParameter("caseLevel"));
		String menuCode = request.getParameter("menuCode");
		String op =request.getParameter("op");
		menuCode =menuCode.substring(0, 1).toUpperCase() + menuCode.substring(1).toLowerCase();
		caseInfo.setMenuCode(menuCode);
		caseInfo.setCaseCode(request.getParameter("caseCode"));
		caseInfo.setPageElement(request.getParameter("pageElement").isEmpty()?"":request.getParameter("pageElement"));
		
		caseInfo.setResult("未发布");
		
	      String opStep =request.getParameter("opStep");
	    // opStep = opStep.replace("'\"+", "'||chr(39)||'\"'||chr(43)||'").replace("+\"'", "'||chr(43)||'\"'||chr(39)||'");
	      caseInfo.setOpStep(opStep);
	      String checkStep =request.getParameter("checkStep").isEmpty()?"":request.getParameter("checkStep");
	    //  checkStep = checkStep.replace("'\"+", "'||chr(39)||'\"'||chr(43)||'").replace("+\"'", "'||chr(43)||'\"'||chr(39)||'");
	      caseInfo.setCheckStep(checkStep);
		caseInfo.setTester(request.getParameter("tester"));
		caseInfo.setParamName(request.getParameter("paramName").isEmpty()?"":request.getParameter("paramName"));
		caseInfo.setParamValue(request.getParameter("paramValue").isEmpty()?"":request.getParameter("paramValue"));

        //校验新增的meucode是否存在
		//if( (op!=null) && (!op.equals("copy")) ){
		if( op.equals("add")) {
		
		ConfigDbOperate dbOperate = new ConfigDbOperate();
		ArrayList<String> stringss = null;
		try {
			stringss = dbOperate.searchStrings("select menucode from web_case where menucode='"+menuCode+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(stringss.size()>0){
			request.setAttribute("result", menuCode+"-已存在的菜单编码,请到复制页面新增操作");
			request.setAttribute("caseinfo", caseInfo);

			request.getRequestDispatcher("/jsp/caseManage/case_add.jsp").forward(request, response);
			return;
		}
		
		
		
		
		}
		
		WebCaseDao caseInfoDao = new WebCaseDao();
		HashMap map = new HashMap();
		map.put("result", true);

		try {
			map = caseInfoDao.addOneCase(caseInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", false);

		}


		if ((Boolean) map.get("result")) {
			request.setAttribute("result", "CASE添加成功");
			request.getRequestDispatcher("/jsp/caseManage/case_save.jsp").forward(request, response);

		}else{
			request.setAttribute("result", map.get("message"));
			request.setAttribute("caseinfo", caseInfo);

			request.getRequestDispatcher("/jsp/caseManage/case_add.jsp").forward(request, response);

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
