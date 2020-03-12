package test.web.servlet.cases;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import base.com.zl.db.ConfigDbOperate;
import com.alibaba.fastjson.JSON;

public class CaseAjaxServlet extends HttpServlet {

    private static List<String> keyWordList = new ArrayList<String>();




    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	keyWordList.clear();
    	req.setCharacterEncoding("utf-8");
        String keyWord = req.getParameter("keywords");
        System.out.println("-"+keyWord+"-");
        String paratype = req.getParameter("paratype");
        String menuCode = req.getParameter("menuCode");
        //新增CASE类型或组件类型 case com
        String type = req.getParameter("type");
        String isPrivate = "'是','否'";
        if(type.equals("case")){
        	isPrivate="'否'";
        }
        
        
        
        if(paratype.equals("opStep")){
        	paratype = "comOp','pageOp','dataUtil','dbOp','pageCheck";       	
        }else{
        	paratype = "dbOp','dbCheck";       	

        }
        //拿第一个无;的
        //keyWord = keyWord.replace("\n", "").replaceAll("\r", "");
        String[]  oldkeyWord = keyWord.split("\\n");
        
        for(String string : oldkeyWord){
        	if(!string.contains(";")&&(!string.contains("{")) &&(!string.contains("}"))) {
        		keyWord=string;
        		break;
        	}
        	
        }
/*    	keyWord =oldkeyWord[0];
        if(oldkeyWord.length>1){
        	keyWord =oldkeyWord[oldkeyWord.length-1];
        }*/
        
        
        
    	ConfigDbOperate dbOperate = new ConfigDbOperate();
        String[] keyWords = keyWord.split("=");

        if(keyWords.length>1){
            keyWord = new String(keyWords[keyWords.length-1].getBytes("ISO-8859-1"), "UTF-8");

        }else{
            keyWord = new String(keyWords[0].getBytes("ISO-8859-1"), "UTF-8");

        }
        keyWord=keyWord.trim();
/*         keyWords = keyWord.split("\\.");

        if(keyWords.length<2){
        	
            if(paratype.equals("opStep")){
            	keyWordList.add("comOp---说明:公共页面操作类");
            	keyWordList.add("pageOp---说明:单元页面操作类");
            	keyWordList.add("dbOp---说明:数据库操作类");
            	keyWordList.add("pageCheck---说明:页面校验类");

            }else{
            	keyWordList.add("dbCheck---说明:数据库校验类");
            }
            
            keyWord = new String(keyWords[0].getBytes("ISO-8859-1"), "UTF-8");


        }else{*/
        	//paratype = new String(keyWords[0].getBytes("ISO-8859-1"), "UTF-8");


       /* }*/
        List<String> list;
        	if(keyWord.equals(".")){
            	try {
					keyWordList = dbOperate.searchStrings("select   case when para3 = '无' or para3= '' or para3 is null then '' else  'DefaultVar='  end       ||              paracode||'('||     case when para2 = '无' or para2 = '' then '' else para2 end         ||')>>>说明:'||remark||'---出参:'||para3          from web_Config where state='1' and  paratype in ('"+paratype+"')");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	list= keyWord==null?new ArrayList<String>():getDatas("");

        	}else if(keyWord.contains("this.")){
        		
        		
        		keyWord = keyWord.substring(5);
            	try {
					keyWordList = dbOperate.searchStrings("select 'this.'||casecode||'('||paramname||')>>>说明:'||t.casename||'--页面操作公共方法' from web_case t where t.menucode='"+menuCode+"' and paramname is not null union "
							+"select 'this.'||casecode||'Check()---说明:'||t.casename||'--校验操作公共方法' from web_case t where t.menucode='"+menuCode+"' and paramname is not null");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
        		
            	list = keyWord==null?new ArrayList<String>():getDatas(keyWord);

            	
            	//新增组件调用提示
        	}else if(keyWord.contains("com.") ){
        		
        		String[] keytemp = keyWord.split("\\.");
        		String module ="";
        		String code = "";
        		try {
            		module=keytemp[1];
				} catch (Exception e) {
					// TODO: handle exception
				}

        		try {
            		code=keytemp[2];

				} catch (Exception e) {
					// TODO: handle exception
				}

        		
        		
                String sql ="select  case when outparam = '无' or outparam= ''  or outparam is null then '' else  'DefaultVar='  end || 'com.'||modulecode||'.'||comcode||'('||case when paramvalue = '无' or paramvalue = '' then '' else paramvalue end                               ||')>>>说明:' ||t.comname  from web_component  t    where isprivate in ("+isPrivate+") and result='已发布' ";
            	try {

				if(!module.equals("")){
					sql=sql+"and modulecode like '"+module+"%'";
				}
				if(!code.equals("")){
					sql=sql+"and comcode like '"+code+"%'" ;
				}
            	
				keyWordList = dbOperate.searchStrings(sql);
            	
            	
            	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	
            	
        		
            	list = keyWord==null?new ArrayList<String>():getDatas(keyWord);
        		
        	}else{
            	try {
					keyWordList = dbOperate.searchStrings("select   case when para3 = '无' or para3= '' or para3 is null then '' else  'DefaultVar='  end       ||              paracode||'('||     case when para2 = '无' or para2 = '' then '' else para2 end ||')>>>说明:'||remark||'---出参:'||para3  from web_Config where state='1' and paracode like'"+keyWord+"%' and  paratype in ('"+paratype+"')");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 

            	
            	list = keyWord==null?new ArrayList<String>():getDatas(keyWord);

        	}

        
        System.out.println(keyWord);
       // System.out.println("select paracode   ||' 说明:'||remark||' 入参:'||para2||' 出参:'||para3 from web_Config where paracode like'%"+keyWord+"%' and  paratype in ('"+paratype+"')");
  
        String json = JSON.toJSONString(list);
        //防止乱码
        String encodeJSON = URLEncoder.encode(json,"UTF-8");
        resp.getWriter().write(encodeJSON);

    }

    public static List<String> getDatas(String keyWord) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < keyWordList.size(); i++) {
            if (keyWordList.get(i).contains(keyWord)) {
                list.add(keyWordList.get(i));
            }
        }
        return list;
    }
}