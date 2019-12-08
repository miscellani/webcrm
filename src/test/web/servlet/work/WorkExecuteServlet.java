package test.web.servlet.work;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.config.ConfigConst;
import test.init.CaseHandle;
import test.init.MainThreadRun;
import test.sec.bean.WebAdminBean;
import test.web.WebInit;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.com.LoginPage;
import test.web.cases.dao.WebCaseDao;
import test.web.cases.dao.WebCaseExecuteDao;
import base.com.zl.com.ComOperate;
import base.com.zl.db.ConfigDbOperate;
import base.com.zl.log.Log;

public class WorkExecuteServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WorkExecuteServlet() {
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


	
                  this.doPost(request, response);
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
		String init= request.getParameter("init");
		String admin= request.getParameter("admin");

		
		//初始化操作
        if(init!=null&&init.equals("init")){
        	
        	
			WebCaseExecuteDao caseInfoDao = new WebCaseExecuteDao();	
			ConfigDbOperate dbOperate = new ConfigDbOperate();
			try {
				dbOperate.updateData("update web_control t set stop='1',finish='1'");
				//删除掉
				//dbOperate.delData("delete web_case_current ");
				//dbOperate.insertData("insert into web_case_current select * from web_case where result='已发布'");
				//dbOperate.updateData("update web_case_current set result='未执行'");

			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			List<WebCaseBean> list = null;
			try {
				list = caseInfoDao.getCaseInfo(null, null, null, null, null, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			request.setAttribute("list", list);			
			request.setAttribute("running", "no");
			request.setAttribute("opType", "init");
			
			request.getRequestDispatcher("/jsp/work/work_operate.jsp").forward(request, response);
			return;
        }
		
        
        
        
		String filterType= request.getParameter("filterType");
		String running ="no";
		
		
		//获取停止/继续执行类型
		String continueType =null;
		if(filterType.equals("continueX")){			
			 //continueType = new String(request.getParameter("continueType").getBytes("ISO-8859-1"), "UTF-8");
			continueType = request.getParameter("continueType");
		}
		
		//停止执行
		if(continueType!=null&&continueType.equals("停止执行")){
			WebCaseExecuteDao caseInfoDao = new WebCaseExecuteDao();	
			ConfigDbOperate dbOperate = new ConfigDbOperate();
			try {
				dbOperate.updateData("update web_control t set stop='1',finish='1'");
				
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			List<WebCaseBean> list = null;
			try {
				list = caseInfoDao.getCaseInfo(null, null, null, null, null, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			request.setAttribute("list", list);
			
			request.setAttribute("running", running);
			request.setAttribute("opType", "stop");
			
			request.getRequestDispatcher("/jsp/work/work_operate.jsp").forward(request, response);
		}else{
			

			

			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("smoke", "0");
			
			
			
			map.put("filterType", filterType);
			
			//这两个是前台传入 filterType all 全量  continue 过滤 filter 单个choice
				
			if(filterType.equals("filter")){
				String module = "";
				String caseName = "";
				String tester = "";
				String caseLevel = "";
				String result = "";
				String menuName = "";
				module = new String(request.getParameter("module").getBytes("ISO-8859-1"), "UTF-8");

				//menuName = new String(request.getParameter("menuName").getBytes("ISO-8859-1"), "UTF-8");
				menuName = request.getParameter("menuName");

				 caseName = request.getParameter("caseName");
			     tester = request.getParameter("tester");
				 caseLevel =  request.getParameter("caseLevel");
			     result = request.getParameter("result");

					map.put("module", module);
					map.put("caseName", caseName);
					map.put("tester", tester);
					map.put("caseLevel", caseLevel);
					map.put("result", result);	
					map.put("menuName", menuName);
					map.put("admin", admin);

					//单个执行
			}else if(filterType.equals("choice")){			
				String caseId = request.getParameter("caseId");
				String ip = request.getParameter("ip");
                //获取远程个体ip
				//ip = request.getRemoteAddr()+","+ip.split(",")[1];
				ip = request.getRemoteAddr()+",chrome";

				System.out.println("ip="+ip);
				WebCaseDao caseInfoDao = new WebCaseDao();
				WebCaseBean webCaseBean = new WebCaseBean();
				try {
					webCaseBean = caseInfoDao.getOneCaseInfo(Integer.parseInt(caseId));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String module = webCaseBean.getModule();
				String menuCode = webCaseBean.getMenuCode();
				String caseCode = webCaseBean.getCaseCode();
				String paramValue=webCaseBean.getParamValue();
				
					try {
						map.put("caseId", caseId);
						map.put("filterType", "choice");
						map.put("module", module);
						map.put("menuCode", menuCode);
						map.put("caseCode", caseCode);
						map.put("ip", ip);
						map.put("paramValue", paramValue);
						map.put("admin", admin);

				 		MainThreadRun mainThreadRun = new MainThreadRun(map);
				 		Thread t = new Thread(mainThreadRun);
				 		t.start();	
				 		} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					   }	
					
					
	


				List<WebCaseBean> list = null;
				try {

					WebCaseExecuteDao webCaseDao = new WebCaseExecuteDao();
					list = webCaseDao.getCaseInfo(null, null, null, admin, null, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				request.setAttribute("list", list);				
				request.setAttribute("running", "no");
			
				WebAdminBean adminInfo = new WebAdminBean();
				adminInfo.setUserAdmin(admin);
				request.setAttribute("adminInfo", adminInfo);

				
				request.getRequestDispatcher("/jsp/work/work_operate.jsp").forward(request, response);

				return;
	
			}
			
			

		

	   //全量执行
	     try {
	 		MainThreadRun mainThreadRun = new MainThreadRun(map);
	 		Thread t = new Thread(mainThreadRun);
	 		t.start();	
	 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }	          
	     
			WebCaseExecuteDao caseInfoDao = new WebCaseExecuteDao();			
			List<WebCaseBean> list = null;
			try {
				list = caseInfoDao.getCaseInfo(null, null, null, null, null, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			request.setAttribute("list", list);
			
			request.setAttribute("running", "yes");	
			request.getRequestDispatcher("/jsp/work/work_operate.jsp").forward(request, response);
			
			
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
