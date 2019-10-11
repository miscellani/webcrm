package base.com.zl.db;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import base.com.zl.log.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import test.config.dao.WebConfigDao;
import test.web.WebInit;


public class DbOperate extends DbPoolOperate {
	
	public String mainCaseId;
	public Connection conn;
	public Statement stmt = null;
	public ResultSet results = null;
	public PreparedStatement ps =null;
	
	
    public DbOperate(){

    }
    public DbOperate(String mainCaseId) {
		   this.mainCaseId=mainCaseId;
			// TODO Auto-generated constructor stub
	}
	
    /*public DbOperate(String dbUser,String dbPass){
		this.dbUser=dbUser;
		this.dbPass=dbPass;
	}*/
	
	 

	public   void getConnction() throws Exception {

/*		 if(!(this.conn==null)&&!this.conn.isClosed()){
			 return ;
		 }
		 
		 Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		 this.conn =  DriverManager.getConnection(dbUrl,dbUser,dbPass);*/
		 

			this.conn = DbPoolOperate.getConnection();

		
	}
	

	
	public void closeConnction(Connection conns,Statement sts,ResultSet rss,PreparedStatement pres) throws SQLException {
       DbPoolOperate.close(conns, sts, rss,pres);

	}
	public void closeConnction() throws SQLException{
	       DbPoolOperate.close(conn, stmt, results,ps);

	}
	
	/**
	 * 只返回结果集，供dao调用
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public ResultSet searchResultSet(String sql){
		
		String sqls = this.splitTable(sql);
		try {			
        this.getConnction();
		stmt = (Statement) conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		 results = ((java.sql.Statement) stmt).executeQuery(sqls);
		} catch (Exception e) {
			// TODO: handle exception
            e.printStackTrace();
            Log.info("语句执行异常:"+sql);
		}finally{
			return results ;
		}

		
		
		
		
	}
	
	
	
	
	/**
	 * 查询表数据基本方法
	 * 
	 * @param 考虑分表问题
	 *            ，region,年月分表 ，传入的SQL表名需要统一传值再进行匹配 ，如:select * from
	 *            so1.ord_cust_f_region_yearmonth ;
	 * @return ArrayList
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<HashMap> searchMaps(String sql) throws SQLException  {

		 sql = this.splitTable(sql);

		ArrayList<HashMap> arrayList = new ArrayList<HashMap>();	

		
		try {
			
        this.getConnction();
		stmt = (Statement) conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		results = ((java.sql.Statement) stmt).executeQuery(sql);
		
		
		int lienum = results.getMetaData().getColumnCount();
		while (results.next()) {
			//ArrayList listChildArrayList= new ArrayList();
			HashMap<Object, Object> map = new HashMap();
			for (int i = 1; i < (lienum + 1); i++) {
				String liename = results.getMetaData().getColumnLabel(i);
				Object bObject = results.getObject(i);			
				if(bObject==null){
					continue;
				}
				if(bObject.getClass().getName().contains("BigDecimal")){
					map.put(liename, bObject.toString());
					
				}else{
					
					map.put(liename, bObject);
				}

				

			}
			arrayList.add(map);
		}
		} catch (Exception e) {
			// TODO: handle exception
            Log.info("语句执行异常:"+sql);
            e.printStackTrace();
		}finally{
			this.closeConnction();

		}


			return arrayList;

	}
	
	/**
	 * 查询单个字段
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("finally")
	public String searchString(String sql){
		
		String string = "";
		try {
			string= this.searchStrings(sql).get(0);

		} catch (Exception e) {
			// TODO: handle exception
            Log.info("查询数据为空:"+sql);
            e.printStackTrace();
		}finally{
			return string;
		}
		

		
	}
	
	@SuppressWarnings("finally")
	public HashMap<?, ?> searchMap(String sql){
		
		HashMap<?, ?> map = new HashMap<Object, Object>();
		
		try {
			map=	this.searchMaps(sql).get(0);
		} catch (Exception e) {
			// TODO: handle exception
            Log.info("查询数据为空:"+sql);
            e.printStackTrace();
		}finally{
			
			return map;
		}
	}
	
	
	
	/**
	 * 查询表数据 基本方法 ： sql如： select bill_id from so1.ord_cust_f_771_201611 where
	 * user_id='878999990' and region="771"
	 * 
	 * @param tableName
	 *            如：so1.ord_cust_f_region_yearmonth
	 * @param condition
	 *            如：user_id='878999990' and region="771"
	 * @param result
	 *            如：bill_id
	 * @return ArrayList
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<HashMap> searchMap(String tableName, String condition,
			String result) throws SQLException {

		// tableName = this.splitTable(tableName);
		String sql = "select from where ";
		// 插入表名
		if (condition.isEmpty()) {
			sql = sql.replace("from where", "from " + tableName + " where 1=1");
		} else {
			sql = sql.replace("from where", "from " + tableName + " where ");
			sql = sql.replace("where", "where " + condition);
		}

		if (result == null) {
			sql = sql.replace("select from", "select * from");

		} else {
			sql = sql.replace("select from", "select " + result + " from");
		}
		ArrayList<HashMap> list = this.searchMaps(sql);
		return list;

	}

	
	
	
	
	
	/**
	 * 带有 in 的map数据查询
	 * 如：select t.offer_user_relat_id,offer_inst_id,user_id from so1.ins_off_ins_user_771 t where offer_id ='212000000102' and user_id in ('2041773040','1113351937','2018569631')
	 * @param sql  ：select t.offer_user_relat_id,offer_inst_id,user_id from so1.ins_off_ins_user_region t where offer_id ='"+userId+"' and user_id in ()
	 * @param conditionList ,装载多个查询条件的list
	 * @return hashmap
	 * @throws SQLException
	 * @throws Exception
	 */
	
	public ArrayList<HashMap> searchMap(String sql,ArrayList conditionList) throws SQLException  {
		
		String conditionContent = "";
		for (int i = 0; i < conditionList.size(); i++) {
			conditionContent = conditionContent + "'"
					+ conditionList.get(i) + "',";
		}
		sql = sql.replace("()", "("+conditionContent+")");
		sql = sql.replace(",)", ")");
		
		return  this.searchMaps(sql);
		
		
	}
	
	
	
	
	
	
	
	/**
	 * 查询表数据基本方法,查询单列数据，如通过offerid查询下挂的所有产品ID 返回一个list集合
	 * 
	 * @param 考虑分表问题
	 *            ，region,年月分表 ，传入的SQL表名需要统一传值再进行匹配 ，如:select bill_id from
	 *            so1.ord_cust_f_region_yearmonth where bill_id='13768789888'
	 *            and user_id='08789966666';
	 * @return ArrayList
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<String> searchStrings(String sql) throws SQLException   {

		 sql = this.splitTable(sql);


		ArrayList<String> arrayList = new ArrayList<String>();	

		try {
			this.getConnction();

		stmt = (Statement) conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		results = ((java.sql.Statement) stmt).executeQuery(sql);
		
		while (results.next()) {
				Object bObject = results.getObject(1);			
				if(bObject==null){
					continue;
				}
				if(bObject.getClass().getName().contains("BigDecimal")){
					arrayList.add(bObject.toString());
					
				}else{
					
					arrayList.add((String) bObject);
				}			

		}

		}catch (Exception e) {
				// TODO: handle exception
            e.printStackTrace();
            Log.info("语句执行异常:"+sql);

			}finally{
				this.closeConnction();

			}

	
		return arrayList;

		
	}

	
	

	
	
	
	
	
	
	
	
	
	
	/**
	 * 查询list数据
	 * @param tableName
	 * @param condition
	 * @param result
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList<String> searchList(String tableName, String condition,
			String result) throws SQLException   {

		String sql = "select from where ";
		// 插入表名
		if (condition.isEmpty()) {
			sql = sql.replace("from where", "from " + tableName + " where 1=1");
		} else {
			sql = sql.replace("from where", "from " + tableName + " where ");
			sql = sql.replace("where", "where " + condition);
		}

		if (result == null) {
			sql = sql.replace("select from", "select * from");

		} else {
			sql = sql.replace("select from", "select " + result + " from");
		}
		return this.searchStrings(sql);

	}
	

	

	/**
	 * 带有 in 的 list查询
	 * 如：select offer_inst_id from so1.ins_off_ins_user_771 t where offer_id ='212000000102' and user_id in ('2041773040','1113351937','2018569631')
	 * @param sql  ：select offer_inst_id from so1.ins_off_ins_user_region t where offer_id ='"+userId+"' and user_id in ()
	 * @param conditionList ,装载多个查询条件的list
	 * @return hashmap
	 * @throws SQLException
	 * @throws Exception
	 */
	
	public ArrayList<String> searchList(String sql,ArrayList conditionList) throws SQLException {
		
		String conditionContent = "";
		for (int i = 0; i < conditionList.size(); i++) {
			conditionContent = conditionContent + "'"
					+ conditionList.get(i) + "',";
		}
		sql = sql.replace("()", "("+conditionContent+")");
		sql = sql.replace(",)", ")");
		
		return  this.searchStrings(sql);
		
		
	}


   /**
    * update 语句
    * @param sql
 * @throws SQLException 
    */
    public void updateData(String sql) throws SQLException {
    	
		String sqle = this.splitTable(sql);
		try {
			
	        this.getConnction();
			stmt = (Statement) conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			((java.sql.Statement) stmt).executeUpdate(sqle);
			} catch (Exception e) {
				// TODO: handle exception
	            Log.info("语句执行异常:"+sqle);
	            e.printStackTrace();
			}finally{
				this.closeConnction();
			}
 	
    }
    /**
     * 批量update 语句,语句之间;分隔
     * @param sql
     * @throws SQLException 
     */
     public void bpUpdateData(String sql) throws SQLException{
     	
 		String sqle = this.splitTable(sql);
		String[] strsqlStrings = sqle.split(";");
	       try {
				this.getConnction();

	    	   stmt = conn.createStatement();
	            for (int i = 0; i < strsqlStrings.length; i++) {
	            	stmt.addBatch(strsqlStrings[i]);// 将所有的SQL语句添加到Statement中
	            }
	            // 一次执行多条SQL语句
	             stmt.executeBatch();
	        } catch (Exception e) {
	            e.printStackTrace();
	            Log.info("语句执行异常:"+sqle);

	        }finally{
	        	this.closeConnction();

	        }
	       
     	
     }
	
	
     /**
      * 删除 语句
      * @param sql
     * @throws SQLException 
     * @throws Exception 
      */
      public void delData(String sql) throws SQLException {
      	
  		String sqle = this.splitTable(sql);


		try {
			this.getConnction();
			ps = conn.prepareStatement(sqle);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
            Log.info("语句执行异常:"+sqle);
            e.printStackTrace();
		}finally{
			this.closeConnction();

		}


		
      }
	
      /**
       * 插入 语句
       * @param sql
     * @throws SQLException 
      * @throws Exception 
       */
       public void insertData(String sql) throws SQLException {
       	
   		String sqle = this.splitTable(sql);
   	        System.out.println(sqle);

   			
   	        try {
   			
   			this.getConnction();
   			ps = conn.prepareStatement(sqle);
   			ps.execute();
   			} catch (Exception e) {
   				// TODO: handle exception
  	             e.printStackTrace();

   	             Log.info("语句执行异常:"+sqle);
   			}finally{
   				this.closeConnction();
   			}

   		
   	        

       }

       public void excutePro(String commd) throws SQLException{
    	   CallableStatement c=conn.prepareCall(commd);
           //执行存储过程
           c.execute();
       }
       
       

	/**
	 * 传入javabean保存数据
	 * @param tableName
	 * @param obj
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 * @throws Exception
	 */
   	public  void insertObject(String tableName, Object obj)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, Exception, Exception {

		String tablenamebean = obj.getClass().getName();

		Class<?> tableclass = Class.forName(tablenamebean);
		Field[] fields = tableclass.getDeclaredFields();

		String fieldnameresult = "";
		Object fieldvalueresult = null;
		for (int i = 0; i < fields.length; i++) {
			String fieldname = fields[i].getName();
			String methodname = "get" + fieldname.substring(0, 1).toUpperCase()
					+ fieldname.substring(1);
			Method m = tableclass.getMethod(methodname);

			Object fieldvalue = m.invoke(obj);

			if (fieldnameresult.isEmpty()) {
				fieldnameresult = fieldname;
				fieldvalueresult = "'" + fieldvalue + "'";

			} else if (fields[i].getType().getName().indexOf("Timestamp") > -1) {
				if (fieldvalue.toString().indexOf(".") > -1) {
					fieldvalue = fieldvalue.toString().replace(".0", "");
				}
				fieldnameresult = fieldnameresult + "," + fieldname;
				fieldvalueresult = fieldvalueresult + ",to_date('" + fieldvalue
						+ "','yyyy-mm-dd hh24:mi:ss')";

			} else {
				fieldnameresult = fieldnameresult + "," + fieldname;
				fieldvalueresult = fieldvalueresult + ",'" + fieldvalue + "'";

			}

		}
		String sql;
		sql = "insert into " + tableName + " values ";
		sql = sql.replace("values", "(" + fieldnameresult + ") values");
		sql = sql.replace("values", " values(" + fieldvalueresult + ")");

        this.insertData(sql);



	}
   	
    public static void main(String[] ss) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, Exception{
 	   ConfigDbOperate dbOperate = new ConfigDbOperate();
/*        int id = Integer.parseInt(dbOperate.searchList("select sp_admin_sequence.nextval from dual").get(0));
 	   
 	   SpAdminBean spAdminBean = new SpAdminBean();
 	   spAdminBean.setIsUsed("1");
 	   spAdminBean.setOperatorId(id);
 	   spAdminBean.setUserAdmin("zlll");
 	   spAdminBean.setUserEmail("442881699@qq.com");
 	   spAdminBean.setUserMobile("18665717801");
 	   spAdminBean.setUserName("name");
 	   spAdminBean.setUserPass("123123");
 	   spAdminBean.setUserStaff("9527");
 	   
 	   dbOperate.insertObject("sp_admin", spAdminBean);*/
 	   
 	  ArrayList<Object> list = dbOperate.searchObject("test.db.crm.SpAdminBean", "select * from sp_admin");
    }
    	

   	/**
   	 * 查询获取javabean
   	 * @param tablename
   	 * @param sql
   	 * @return
   	 * @throws Exception
   	 */
	public ArrayList<Object> searchObject(String className,
			String sql) throws Exception {

        this.results  =  this.searchResultSet(sql);
		ArrayList<Object> list = new ArrayList<Object>();

	

		Class<?> tableclass = Class.forName(className);
		Field[] fields = tableclass.getDeclaredFields();
		while (results.next()) {
			Object obj = tableclass.getConstructor().newInstance();
			for (int i = 0; i < fields.length; i++) {
				String fieldname = fields[i].getName();
				String methodname = "set"
						+ fieldname.substring(0, 1).toUpperCase()
						+ fieldname.substring(1);
				Method m = tableclass
						.getMethod(methodname, fields[i].getType());

				results.findColumn(fieldname);

				Object val = results.getObject(fieldname);
				if (val instanceof java.math.BigDecimal
						&& fields[i].getType().toString().equals("int")) {
					val = ((BigDecimal) val).intValue();
				}
				if (val != null) {
					m.invoke(obj, val);
				}

			}
			list.add(obj);
		}


			this.closeConnction();
			return list;
		

	}  	
   	
   	
       
       
       
       
       






	
	
	
	

	public String splitTable(String sql) {

		if ((sql.indexOf("_region") > -1)&&(!sql.contains("'_region'")) ) {
			sql = sql.replace("_region", "_" + this.region);
		}
		
		if (sql.indexOf("_yyyymm") > -1) {
			sql = sql.replace("_yyyymm", "_"+WebInit.year+WebInit.month);
		}else if (sql.indexOf("_yyyy_mm") > -1) {
			sql = sql.replace("_yyyy_mm", "_"+WebInit.year+"_"+WebInit.month);
		}else if(sql.indexOf("_yyyy") > -1){
			sql = sql.replace("_yyyy", "_"+WebInit.year);

		}
		
		
		return sql;
	}

	
	

	
	
	public   void getUatDbConnction(String dbUrl,String dbUser,String dbPass) throws Exception {
		 Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		 this.conn =  DriverManager.getConnection(dbUrl,dbUser,dbPass);
		  
		 
	}
	public void closeUatDbConnction(Connection conn,Statement st,ResultSet rs,PreparedStatement pre){


	         if(rs!=null){
	             try{
	                 //关闭存储查询结果的ResultSet对象
	                 rs.close();
	             }catch (Exception e) {
	                 e.printStackTrace();
	             }
	             rs = null;
	         }
	         if(st!=null){
	             try{
	                 //关闭负责执行SQL命令的Statement对象
	                 st.close();
	             }catch (Exception e) {
	                 e.printStackTrace();
	             }
	         }
	         
	         if(pre!=null){
	             try{
	                 //将Connection连接对象还给数据库连接池
	                 conn.close();
	             }catch (Exception e) {
	                 e.printStackTrace();
	             }
	         } 
	         
	         if(conn!=null){
	             try{
	                 //将Connection连接对象还给数据库连接池
	                 conn.close();
	             }catch (Exception e) {
	                 e.printStackTrace();
	             }
	         }
	     }
	
	
	/**
	 * 只用于封装的操作配置，解决修改数据配置后需要再编译问题
	 * @param key
	 * @param param 
	 * @return
	 * @throws Exception 
	 */
	public Object dbop(String key,String param) throws Exception{
		WebConfigDao webConfigDao = new WebConfigDao();
		String[] searchSql = webConfigDao.getDbOpByKey("dbOp", key, param);
		String sql = searchSql[0];
		String returnType = searchSql[2];
		Object object = null;
		//'"+
		sql = sql.replace("'\"+", "\'");
		sql = sql.replace("+\"'", "\'");
		sql = sql.replace("\"", "");

		if(sql.startsWith("select ")){
			if(returnType.equals("String")){
				object = (String)this.searchString(sql);
			}if(returnType.equals("Strings")){
				object = (ArrayList<String>)this.searchStrings(sql);
			}if(returnType.equals("Map")){
				object = (HashMap<String, String>)this.searchMap(sql);
			}if(returnType.equals("Maps")){
				object = (ArrayList<HashMap>)this.searchMaps(sql);
			}

		}else if(sql.startsWith("update ")){

			this.updateData(sql);
		}else if(sql.startsWith("delete ")){

			this.delData(sql);
		}else if(sql.startsWith("insert ")){

			this.insertData(sql);
		}
	
		return object;
		
	}
}
