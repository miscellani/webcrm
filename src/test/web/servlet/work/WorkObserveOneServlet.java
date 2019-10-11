package test.web.servlet.work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.web.cases.dao.WebCaseExecuteDao;


public class WorkObserveOneServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WorkObserveOneServlet() {
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
		
        WebCaseExecuteDao caseInfoExecuteDao = new WebCaseExecuteDao();      
        ArrayList<HashMap> sumResult = null;
		try {
			sumResult = caseInfoExecuteDao.getExecuteResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int allNum=0;
        int idleNum=0;
        int successNum=0;
        int failNum=0;
        int correctNum=0;
        int wrongNum=0;
        
        if( sumResult!=null&& (sumResult.size()>0) ){
		    allNum = Integer.parseInt((String) sumResult.get(0).get("总数")) ;
		    idleNum= Integer.parseInt((String) sumResult.get(0).get("未执行")) ;
		    successNum= Integer.parseInt((String) sumResult.get(0).get("成功")) ;
		    failNum= Integer.parseInt((String) sumResult.get(0).get("失败")) ;
		    correctNum= Integer.parseInt((String) sumResult.get(0).get("正确"));
		    wrongNum= Integer.parseInt((String) sumResult.get(0).get("错误"));
        }
            request.setAttribute("allNum", String.valueOf(allNum)) ;  
            String executeResult = "[['未执行',"+String.valueOf(idleNum)+"],['成功',"+String.valueOf(successNum)+"],['失败',"+String.valueOf(failNum)+"],['正确',"+String.valueOf(correctNum)+"],['错误',"+String.valueOf(wrongNum)+"]]";
                    
            		
            		
            String[] errDivides= new String[2];	
            request.setAttribute("executeResult", executeResult);
            try {
            	errDivides=caseInfoExecuteDao.getErrCaseDivide();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
            String errDivide = errDivides[0];
            String errNum = String.valueOf( errDivides[1]);
            request.setAttribute("errDivide", errDivide);
            request.setAttribute("errNum", errNum);
           
			request.getRequestDispatcher("/jsp/work/work_observe_one.jsp").forward(request, response);
			
			
		
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
