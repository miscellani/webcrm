package test.config.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.config.bean.WebConfigBean;
import test.config.dao.WebConfigDao;



public class ConfigUpdateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ConfigUpdateServlet() {
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
		String operate = request.getParameter("operate");
		String id =request.getParameter("id");
		WebConfigBean configInfo = new WebConfigBean();
		WebConfigDao configInfoDao = new WebConfigDao();
		if(operate.equals("del")){
			try {
				configInfoDao.deleteOneConfig(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ArrayList<WebConfigBean>  list = new ArrayList<WebConfigBean>() ;
		try {
			list=  (ArrayList<WebConfigBean>) configInfoDao.getConfigInfo(null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.setAttribute("list", list);				
		request.setAttribute("paraType", "");
		request.setAttribute("paraCode", "");		
		request.setAttribute("remark", "");		
		
		
		
			request.getRequestDispatcher("/jsp/config/config_list.jsp").forward(request, response);

		}else{
			
			configInfo.setPara1("");
			configInfo.setPara2("");
			configInfo.setPara3("");
			configInfo.setPara4("");
			configInfo.setParaCode("");
			configInfo.setParaType("");
			configInfo.setRemark("");
			configInfo.setId(0);
			configInfo.setCreater("");

			
			
			if(operate.equals("update")){
				try {
					configInfo = configInfoDao.getOneConfigInfo(request.getParameter("id"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
										
			}
			request.setAttribute("operate", operate);
			request.setAttribute("configInfo", configInfo);
		
			request.getRequestDispatcher("/jsp/config/config_update.jsp").forward(request, response);

		}
	

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
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		
		


					
					request.setCharacterEncoding("utf-8");
					String operate = request.getParameter("operate");
					boolean result = false;
					WebConfigBean configInfo = new WebConfigBean();
					WebConfigDao configInfoDao = new WebConfigDao();

					if(operate.equals("update")){
						configInfo.setId(Integer.parseInt(request.getParameter("id")));
					
						configInfo.setParaCode(request.getParameter("paraCode"));
						configInfo.setParaType(request.getParameter("paraType"));
						configInfo.setPara1(request.getParameter("para1"));
						configInfo.setPara2(request.getParameter("para2"));
						configInfo.setPara3(request.getParameter("para3"));
						configInfo.setPara4(request.getParameter("para4"));
						configInfo.setRemark(request.getParameter("remark"));
						configInfo.setCreater(request.getParameter("creater"));


						try {
							result = configInfoDao.updateOneConfig(configInfo);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						
						configInfo.setParaCode(request.getParameter("paraCode"));
						configInfo.setParaType(request.getParameter("paraType"));
						configInfo.setPara1(request.getParameter("para1"));
						configInfo.setPara2(request.getParameter("para2"));
						configInfo.setPara3(request.getParameter("para3"));
						configInfo.setPara4(request.getParameter("para4"));
						configInfo.setRemark(request.getParameter("remark"));
						configInfo.setCreater(request.getParameter("creater"));

						try {
							result = configInfoDao.addOneConfig(configInfo);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}



					if (result) {
						request.setAttribute("result", "配置更新成功");
					}else{
						request.setAttribute("result", "配置更新失败");
					}

					request.getRequestDispatcher("/jsp/config/config_save.jsp").forward(request, response);
					
					
					
					
					
				
				
				
		
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
