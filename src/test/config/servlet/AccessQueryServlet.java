package test.config.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.com.zl.db.DbOperate;
import base.com.zl.log.Log;
import base.com.zl.utils.DataUtil;
import test.config.bean.WebConfigBean;
import test.config.dao.WebConfigDao;



public class AccessQueryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AccessQueryServlet() {
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

		String paraCode = request.getParameter("paraCode");
		if(paraCode==null){
			paraCode="";
		}

			paraCode = new String(paraCode.getBytes("ISO-8859-1"), "UTF-8");
		
		String id = request.getParameter("id");
		String  remark = request.getParameter("remark");

		// = new String(remark.getBytes("ISO-8859-1"), "UTF-8");

		WebConfigDao configInfoDao = new WebConfigDao();

		try {
			remark = configInfoDao.getOneConfigInfo(id).getRemark();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

			
			String param = request.getParameter("param");
			if(param==null){
				param="";
		}
			
			
			String op = request.getParameter("op");			
			ArrayList<String> accessList = new  ArrayList<String>();
			
			String sql ="";
			try {
				sql=   configInfoDao.getAccessSql(paraCode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(op!=null){
				
		
	    		
		
		
		
		//替换参数 
		if(param!=null&&(!param.equals(""))){
			
	    	String[] paramValues =param.split(",");
	    	
	    	DataUtil dataUtil = new DataUtil();
	    	for(int i=0;i<paramValues.length;i++){
	    		String pValue = dataUtil.patternText(sql, "(\\$\\{[^\\$]*\\})");
	    		sql=sql.replace(pValue, paramValues[i]);
	    	}
	    	

		}

         if(sql.contains("${")){
 			accessList.add("输入参数不全，请查看备注说明");

         }
    	
    	
    	
		//更新地市后缀,包裹随机长度
		DbOperate dbOperate = new DbOperate();
		sql=dbOperate.splitTable(sql);
		
		//清除尾部分号
		sql= sql.replace(";", "");
		
		
		
		sql= " select *  from  ( "+sql +"  ) where rownum< 102 order by dbms_random.value";
		
		String  uatDb = null ;
		 try {
			 uatDb =configInfoDao.getRunParam("uatdb");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] uatDbs = uatDb.split("\\|"); 

		try {
			dbOperate.getUatDbConnction(uatDbs[0], uatDbs[1], uatDbs[2]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {


			dbOperate.stmt = (Statement) dbOperate.conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			dbOperate.results = ((java.sql.Statement) dbOperate.stmt).executeQuery(sql);
		
		while (dbOperate.results.next()) {
				Object bObject = dbOperate.results.getObject(1);			
				if(bObject==null){
					continue;
				}
				if(bObject.getClass().getName().contains("BigDecimal")){
					accessList.add(bObject.toString());
					
				}else{
					
					accessList.add((String) bObject);
				}			

		}

		}catch (Exception e) {
				// TODO: handle exception
            e.printStackTrace();
            //Log.info("语句执行异常:"+sql);

			}finally{

				dbOperate.closeUatDbConnction(dbOperate.conn, dbOperate.stmt, dbOperate.results, dbOperate.ps);
			}
		
		
		if(accessList.isEmpty()){
			accessList.add("未查询到数据");
		}
		
			}else{
				if(accessList.isEmpty()){
					accessList.add("请查询！");
				}
			}
		
		


		request.setAttribute("list", accessList);		
		request.setAttribute("id", id);	
	    request.setAttribute("remark", remark);
		request.setAttribute("paraCode", paraCode);	
		request.setAttribute("param", param);	
		request.setAttribute("sql", sql);			

		request.getRequestDispatcher("/jsp/config/access_list.jsp").forward(request, response);
		




		
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
