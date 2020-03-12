package test.web.servlet.cases;





import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class SearchPopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0设置编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		//1.获取参数
		String kw = request.getParameter("kw");
		//System.out.println(kw);
		//service  //dao   a  
		//2.调用service完成模糊操作 返回值list
		//List<Object> list = new KeyWordService().findKw4Ajax(kw);
		//3.将数据集合[a,aa,aaa]遍历返回给search.jsp
		List<String> list =new ArrayList();
		list.add("1");
		list.add("2");
		if(list!=null&&list.size()>0){
			String str = list.toString();
			str =str.substring(1, str.length()-1);
			//System.out.println(str);
			response.getWriter().println(str);
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}