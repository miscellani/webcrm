package test.config.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.config.bean.WebConfigBean;
import base.com.zl.db.ConfigDbOperate;
import base.com.zl.db.DbOperate;
import base.com.zl.log.Log;
import base.com.zl.utils.DataUtil;
import base.com.zl.utils.DateUtil;


public class WebConfigDao {
	

	
	
	/**
	 * 返回配置信息
	 * @param value
	 * @param optionString
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public ArrayList getConfigInfo(String paratType,String paraCode,String remark) throws Exception{
		
		String sqlString="select id,paratype,paracode,para1,para2,para3,para4,remark,state,creater from web_config where state='1'  ";	
        if( !(paratType==null||paratType.equals("")) ){
        	
        	sqlString=sqlString + " and paraType='"+paratType+"'";
        	
        }
        if( !(paraCode==null||paraCode.equals("")) ){
        	
        	sqlString=sqlString+ " and paraCode like '%"+paraCode+"%'";
        	
        	
        }
        if( !(remark==null||remark.equals("")) ){
       
        	sqlString =sqlString + " and remark like '%"+remark+"%'" ;
        }
		sqlString = sqlString +" order by paratype ,remark";
		ResultSet res = null;
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		ArrayList<Object> list =configDbOperate.searchObject("test.config.bean.WebConfigBean", sqlString);
		ArrayList<WebConfigBean>  lists = new ArrayList<>();
		for(Object bean : list){
			
			
			WebConfigBean beans = (WebConfigBean) bean ;
			
			if( (beans.getPara1()!=null)&&(beans.getPara1().length()>30) ){
				beans.setPara1(beans.getPara1().substring(0, 30));

			}
			
			if( (beans.getRemark()!=null)&&(beans.getRemark().length()>100) ){
				beans.setRemark(beans.getRemark().substring(0, 100));

			}
			
			
			
			
			
			lists.add((WebConfigBean) beans);
		}
		return lists;
		
		
	}
	
	
	
	/**
	 * 返回号码sql
	 * @param value
	 * @param optionString
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public String getAccessSql(String paraCode) throws Exception{
		
		String sqlString="select para1 from web_config where state='1' and paratype='numberkey' ";	

        if( !(paraCode==null||paraCode.equals("")) ){
        	
        	sqlString=sqlString+ " and paraCode = '"+paraCode+"'";
     	
        }
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		String sql =configDbOperate.searchString(sqlString);
		return sql;
		
		
	}
	
	
	/**
	 * 返回单个配置信息
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public WebConfigBean getOneConfigInfo(String id) throws Exception{
		
		String sqlString="select id,paratype,paracode,para1,para2,para3,para4,remark,state,creater from web_config where id ='" +id+ "'";	

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		ResultSet res = null;
		WebConfigBean WebConfigBean = new WebConfigBean();
		ArrayList<Object> list =configDbOperate.searchObject("test.config.bean.WebConfigBean", sqlString);
           return (test.config.bean.WebConfigBean) list.get(0);
/*		try {
			 res =dbOperate.searchResultSet(sqlString);

			while (res.next()) {
				WebConfigBean.setId(res.getInt("id"));
				String typeString =res.getString("paratype");
				WebConfigBean.setParaType(typeString);
				WebConfigBean.setParaCode(res.getString("paracode"));
				WebConfigBean.setPara1(res.getString("para1"));
				WebConfigBean.setPara2(res.getString("para2"));
				WebConfigBean.setPara3(res.getString("para3"));
				WebConfigBean.setPara4(res.getString("para4"));
				WebConfigBean.setRemark(res.getString("remark"));
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}finally{
			try {
                dbOperate.closeConnction();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("关闭连接失败");
			}
			return WebConfigBean;
		}*/


		
		
			
		}
		
	/**
	 * 更新一个配置信息
	 * @param caseinfo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public boolean updateOneConfig(WebConfigBean WebConfigBean) throws Exception {
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		
		int i=0;
		PreparedStatement pre = null;

		try {

			configDbOperate.getConnction();


				String sql = "update web_config set paratype=?,paracode=?,para1=?,para2=?,para3=?,para4=?,remark=?,state='1',creater=?  where id=?";

			
			
				// 创建实例
				configDbOperate.ps=configDbOperate.conn.prepareStatement(sql);		
				configDbOperate.ps.setString(1, WebConfigBean.getParaType());
				configDbOperate.ps.setString(2, WebConfigBean.getParaCode());
				configDbOperate.ps.setString(3, WebConfigBean.getPara1().isEmpty()?" ":WebConfigBean.getPara1());
				configDbOperate.ps.setString(4, WebConfigBean.getPara2().isEmpty()?" ":WebConfigBean.getPara2());
				configDbOperate.ps.setString(5, WebConfigBean.getPara3().isEmpty()?" ":WebConfigBean.getPara3());
				configDbOperate.ps.setString(6, WebConfigBean.getPara4().isEmpty()?" ":WebConfigBean.getPara4());
				configDbOperate.ps.setString(7, WebConfigBean.getRemark().isEmpty()?" ":WebConfigBean.getRemark());
				configDbOperate.ps.setString(8, WebConfigBean.getCreater().isEmpty()?" ":WebConfigBean.getCreater());
				configDbOperate.ps.setInt(9, WebConfigBean.getId());

				i = configDbOperate.ps.executeUpdate();
				
	            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			configDbOperate.closeConnction();
	
			if (i > 0) {
				return true;
			}else{
				return false;

			}
		}
	
	}


	
	


	
	
	
	/**
	 * 添加配置信息
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public boolean addOneConfig(WebConfigBean WebConfigBean) throws Exception{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		PreparedStatement pre = null;	
		String sql = "insert into web_config values (web_config_sequence.nextval,?,?,?,?,?,?,?,'1',?)";
		int i=0;
		try {
			configDbOperate.getConnction();
		
			// 创建实例
			configDbOperate.ps=configDbOperate.conn.prepareStatement(sql);
					
			configDbOperate.ps.setString(1, WebConfigBean.getParaType());
			configDbOperate.ps.setString(2, WebConfigBean.getParaCode());
			configDbOperate.ps.setString(3, WebConfigBean.getPara1().isEmpty()?" ":WebConfigBean.getPara1());
			configDbOperate.ps.setString(4, WebConfigBean.getPara2().isEmpty()?" ":WebConfigBean.getPara2());
			configDbOperate.ps.setString(5, WebConfigBean.getPara3().isEmpty()?" ":WebConfigBean.getPara3());
			configDbOperate.ps.setString(6, WebConfigBean.getPara4().isEmpty()?" ":WebConfigBean.getPara4());
			configDbOperate.ps.setString(7, WebConfigBean.getRemark().isEmpty()?" ":WebConfigBean.getRemark());
			configDbOperate.ps.setString(8, WebConfigBean.getCreater().isEmpty()?" ":WebConfigBean.getCreater());


						 i = configDbOperate.ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
			configDbOperate.closeConnction();

			
			if (i > 0) {
				return true;
			}else{
				return false;

			}
		}
	
	}
	
	
	
	@SuppressWarnings("finally")
	public boolean deleteOneConfig(String id) throws SQLException{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		String sql = "delete web_config where id='"+id+"'";
		configDbOperate.delData(sql);
		return true;
		
	
	}

	
	
	
	
	
	

	/**
	 * 获取配置参数
	 * @throws SQLException 
	 * @throws Exception 
	 */
	
 private List<String> getConfigParam(String paraType,String paraCode,String state) throws SQLException{
	 String sql = "select para1 from web_config where paratype='"+paraType+"' and paraCode='"+paraCode+"' and state='"+state+"'" ;

	 if(paraCode==null){
		 sql = "select para1 from web_config where paratype='"+paraType+"' and  state='"+state+"'" ;
	 }
	 ArrayList<String>  strings = null;

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		  strings=configDbOperate.searchStrings(sql);

		 return strings;
		 
 }
	
 /**
	 * 获取配置参数
	 * @throws Exception 
	 */
	
public HashMap getConfigParams(String paraType,String paraCode,String state)throws Exception{
	 String sql = "select para1,para2,para3,para4,remark from web_config where paratype='"+paraType+"' and paraCode='"+paraCode+"' and state='"+state+"'" ;

	 if(paraCode==null){
		 sql = "select para1,para2,para3,para4,remark from web_config where paratype='"+paraType+"' and  state='"+state+"'" ;
	 }
	 HashMap<?, ?>  strings = null;

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		  strings=configDbOperate.searchMap(sql);

		 return strings;
		 
}	
	
	/**
	 * 传入sqlKey获取满足查询条件的数据，只支持查一行一列数据
	 * @param sqlKey 查询SQL语句的唯一标识
	 * @return 电话号码
	 * @throws SQLException
	 * @throws Exception
	 */
	public String getParamSqlByKey(String sqlKey) throws SQLException {

		String paraCode=sqlKey;
		ConfigDbOperate configDbOperate = new ConfigDbOperate();

       String sql= this.getConfigParam("paramsql", paraCode, "1").get(0);
       
       //if(sql.contains("from")){

    	   sql= " select *  from  ( "+sql +"  ) where rownum< 100 order by dbms_random.value";
    	   sql =configDbOperate.searchStrings(sql).get(0); 
       //}


			return sql;
	
	}
	

	
/*	*//**
	 * 获取KEY值
	 * @param sqlKey
	 * @param linkValue
	 * @return
	 * @throws Exception
	 *//*
	public String getParamSqlByKey(String paramType,String sqlKey,String linkValue)  throws Exception {
        DbOperate dbOperate = new DbOperate();

		String paraCode=sqlKey;
        String s="";
        String sql= this.getConfigParam("paramsql", paraCode, "1").get(0);
        HashMap<String,String> resultMap = new HashMap<String,String>();

        if(linkValue==null){
      	   sql= " select *  from  ( "+sql +"  ) where rownum< 100 order by dbms_random.value";
        	resultMap =dbOperate.searchMaps(sql).get(0);
    		for(String mapValue : resultMap.values()){

            	s=mapValue+"|"+s;
        		}
            	       	
            	return s.substring(0,s.length()-1);
        }
        
        if(linkValue.contains(",")){

        	String[] paramValues =linkValue.split(",");
        	DataUtil dataUtil = new DataUtil();
        	for(int i=0;i<paramValues.length;i++){
        		String pValue = dataUtil.patternText(sql, "(\\$\\{[^\\$]*\\})");
        		sql=sql.replace(pValue, paramValues[i]);
        	}
      	   sql= " select *  from  ( "+sql +"  ) where rownum< 100 order by dbms_random.value";

        	resultMap =dbOperate.searchMaps(sql).get(0);

    		for(String mapValue : resultMap.values()){

        	s=mapValue+"|"+s;
    		}
        	
    	
        	return s.substring(0,s.length()-1);
        }
        int start = sql.indexOf("$");
        int end = sql.indexOf("}");
        
        sql = sql.substring(0, start) +linkValue +sql.substring(end+1);
 	   sql= " select *  from  ( "+sql +"  ) where rownum< 100 order by dbms_random.value";
             System.out.println(sql);

           //s =dbOperate.searchStrings(sql).get(0);
             resultMap =dbOperate.searchMaps(sql).get(0);
     		for(String mapValue : resultMap.values()){

            	s=mapValue+"|"+s;
        		}
	
			return s.substring(0,s.length()-1);
		


		
	}*/



	
	
	/**
	 * 返回运行配置参数
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public String   getRunParam(String paraCode) throws SQLException{
		ArrayList<String> s = null;
		ConfigDbOperate configDbOperate = new ConfigDbOperate();

			String	sqlString ="select para1 from web_config where paratype='runparam' and paracode='"+paraCode+"'";		
              s=configDbOperate.searchStrings(sqlString);
			return s.get(0);
	
	}
	/**
	 * 获取所有运行参数
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> getRunParams() throws Exception  {
		String sql = "select paraCode,para1 from web_config where paratype='runparam' ";		
		HashMap<String, String> maps = new HashMap<String, String>();
		ConfigDbOperate configDbOperate = new ConfigDbOperate();

    		ArrayList<HashMap> listMaps = configDbOperate.searchMaps(sql);
    		for( HashMap<String, String> map :listMaps ){
    			
   			 String paramName = map.get("PARACODE");
   			 String paramValue =  map.get("PARA1");
   			 maps.put(paramName, paramValue);
    		}

			
			return maps;
		

	}
	
	
	
	

	
	
	
	
	///////////////////////////////////////////////////////////
	
	/**
	 * 获取sq1
	 * @param sqlKey
	 * @param param 对应配置SQL中的值，顺序替换，如 18665717801,197523475943,8768348
	 * @return
	 * @throws Exception
	 */
	public String[]  getSqlByKey(String paraType,String sqlKey,String param) throws Exception {
		DataUtil dataUtil = new DataUtil();
        HashMap<String,String> sqlMap= this.getConfigParams(paraType, null, "1");
        String sql =sqlMap.get("PARA1");
        String returnType =sqlMap.get("PARA2");
         String[]  valueStrings=new String[2];
         valueStrings[0] =sql;
         valueStrings[1] =returnType;

        if(param==null){
        	return valueStrings;
        }
        String[] params= param.split(",");
        for(String s:params){
        	String pValue = dataUtil.patternText(sql, "(\\$\\{[^\\$]*\\})");
        	sql=sql.replace(pValue, s);
            valueStrings[0] =sql;

        }
        
    	return valueStrings;


	}
	
	
/** web
 * 获取数据库操作
 * @param paraType
 * @param sqlKey
 * @param param
 * @return
 */
	 
	public String[]  getDbOpByKey(String paraType,String sqlKey,String param) throws Exception {
		DataUtil dataUtil = new DataUtil();
        HashMap<String,String> sqlMap= this.getConfigParams(paraType, sqlKey, "1");
        String sql =sqlMap.get("PARA1");
        String input =sqlMap.get("PARA2");        
        String returnType =sqlMap.get("PARA3");
        String remark =sqlMap.get("REMARK");
         String[]  valueStrings=new String[4];
         valueStrings[0] =sql;
         valueStrings[1] =input;         
         valueStrings[2] =returnType;
         valueStrings[3] =remark;

        if(param==null||param.equals("")){
        	
            valueStrings[0] ="\""+valueStrings[0]+"\"";

        	return valueStrings;
        }
        String[] params= param.split(",");
        for(String s:params){
        	if(s.contains("\"")){
        		s =s.replace("\"", "");
        		
        	}else{
        		s="\"+"+s+"+\"";
        	}
        	String pValue = dataUtil.patternText(sql, "(\\$\\{[^\\$]*\\})");
        	sql=sql.replace(pValue, s);

        }
        valueStrings[0] ="\""+sql+"\"";

    	return valueStrings;


	}
	
	/** web
	 * 获取页面操作
	 * @param paraType
	 * @param sqlKey
	 * @param param
	 * @return
	 */
		 
		public String[]  getPageOpByKey(String paraType,String sqlKey) throws Exception {
			DataUtil dataUtil = new DataUtil();
	        HashMap<String,String> sqlMap= this.getConfigParams(paraType, sqlKey, "1");
	        String inputType =sqlMap.get("PARA2");
	        String returnType =sqlMap.get("PARA3");
	         String[]  valueStrings=new String[2];
	         valueStrings[0] =inputType;
	         valueStrings[1] =returnType;        
	    	return valueStrings;


		}	
	
		public  static void  main(String[] s) throws Exception{
			//String sqlKey=".time(1,4)";
			//DateUtil dateUtil = new DateUtil();
	/*		String now = dateUtil.nowToNum();
			String[] sqlKeys = sqlKey.split("\\(");
			String sqlKeysParam =sqlKeys[1].replace(")", "");
			String[] sqlKeysParams =sqlKeysParam.split(",");
			System.out.println( now.substring(Integer.parseInt(sqlKeysParams[0])-1,Integer.parseInt(sqlKeysParams[1])));*/

			WebConfigDao webConfigDao = new WebConfigDao();
			webConfigDao.getNumberByKey("normal,1");
		}

		
		
		
		
		
		/**  
		 * 获取操作号码（执行查询）
		 * @param sqlKey
		 * @param linkValue
		 * @return
		 * @throws Exception
		 */
		public String getNumberByKey(String sqlKey)  throws Exception {
			DbOperate dbOperate = new DbOperate();

	        String[] sqlKeys =  sqlKey.split(",");
			String paraCode=sqlKeys[0];
	        String s="";
	        String sql="";
	        sql= this.getConfigParam("numberkey", paraCode, "1").get(0);
	        HashMap<String,String> resultMap = new HashMap<String,String>();
	        //删除分号
	        sql  = sql.replace(";", "");
        	DataUtil dataUtil = new DataUtil();
	        
	        
	        
	        
	        //没有变量
	        if(sqlKeys.length==1){
		      	 Log.info("无入参numberkey:"+sql);
	        }else{
	        //有变量
	          String sqlParam =sqlKey.replace(paraCode+",", "");
	          if(sqlParam.contains(",")){

	        	String[] paramValues =sqlParam.split(",");

	        	for(int i=0;i<paramValues.length;i++){
	        		String pValue = dataUtil.patternText(sql, "(\\$\\{[^\\$]*\\})");
	        		sql=sql.replace(pValue, paramValues[i]);
	        	}
	      	 System.out.println(sql);
	      	 Log.info("有入参numberkey:"+sql);
	          }
	      	
	        
	        }
	        

	        
	        
	        //关联已用号码表过滤
	        String  outc =  dataUtil.patternText(sql, "select (.*?)from");
	        outc=outc.trim();
	        sql=sql +" and "+outc+" not in (select test_Data from web_test_data)";
	        sql= " select *  from  ( "+sql +"  ) where rownum< 100 order by dbms_random.value";

	      	   try{
		        	resultMap =dbOperate.searchMaps(sql).get(0);

	      	   }catch (Exception e) {
				// TODO: handle exception
	      		 resultMap.put("error", paraCode);
			}	        	
	    		for(String mapValue : resultMap.values()){

	        	s=mapValue+","+s;
	    		}
	        	return s.substring(0,s.length()-1);
	        	
	        	
	        	
	        
	        
	        
  
	        
	        
/*	        
	        //没有变量

	        if(sqlKeys.length==1){
	      	   sql= " select *  from  ( "+sql +"  ) where rownum< 100 order by dbms_random.value";
		      	 Log.info("无入参numberkey:"+sql);
		      	 
		      	 
		      	 
		      	 
		      	 
	      	   try{
		        	resultMap =dbOperate.searchMaps(sql).get(0);

	      	   }catch (Exception e) {
				// TODO: handle exception
	      		 resultMap.put("error", paraCode);
			}
	    		for(String mapValue : resultMap.values()){

	            	s=mapValue+","+s;
	        		}
	            	       	
	            	return s.substring(0,s.length()-1);
	        }
	        //多个变量
	        String sqlParam =sqlKey.replace(paraCode+",", "");
	        if(sqlParam.contains(",")){

	        	String[] paramValues =sqlParam.split(",");
	        	DataUtil dataUtil = new DataUtil();
	        	for(int i=0;i<paramValues.length;i++){
	        		String pValue = dataUtil.patternText(sql, "(\\$\\{[^\\$]*\\})");
	        		sql=sql.replace(pValue, paramValues[i]);
	        	}
	      	   sql= " select *  from  ( "+sql +"  ) where rownum< 100 order by dbms_random.value";
	      	 System.out.println(sql);
	      	 Log.info("多入参numberkey:"+sql);
	      	   try{
		        	resultMap =dbOperate.searchMaps(sql).get(0);

	      	   }catch (Exception e) {
				// TODO: handle exception
	      		 resultMap.put("error", paraCode);
			}	        	
	    		for(String mapValue : resultMap.values()){

	        	s=mapValue+","+s;
	    		}
	        	return s.substring(0,s.length()-1);
	        	
	        	
	        	
	        }
	        
	        //只有一个变量
	        int start = sql.indexOf("$");
	        int end = sql.indexOf("}");
	        
	        sql = sql.substring(0, start) +sqlParam +sql.substring(end+1);
	 	   sql= " select *  from  ( "+sql +"  ) where rownum< 100 order by dbms_random.value";
	             System.out.println(sql);

	           //s =dbOperate.searchStrings(sql).get(0);
		      	 Log.info("1入参numberkey:"+sql);
		      	 

		      	 try {
	             resultMap =dbOperate.searchMaps(sql).get(0);	             	            	             
		      	 Log.info("小丕权返回结果:"+resultMap.toString());

			     		for(String mapValue : resultMap.values()){

			            	s=mapValue+","+s;
			        		}
				} catch (Exception e) {
					// TODO: handle exception
			      	 DataUtil dataUtil = new DataUtil();
			      	 
			      	 Log.info("报错了"+dataUtil.getTrace(e));

				}

		      	 Log.info("最终返回结果:"+s.substring(0,s.length()-1));
				return s.substring(0,s.length()-1);*/
			


			
		}	
		
		
		
		

}
