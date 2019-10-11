package base.com.zl.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;



import base.com.zl.utils.FileUtil;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConfigDbPoolOperate {
     private static ComboPooledDataSource configds = null;
 	//环境配置
 	public static String region=null;
 	public static String dbUrl=null;
 	public static String dbUser=null;
 	public static String dbPass=null;	
 	public static int maxPoolSize=5;

     //在静态代码块中创建数据库连接池
     static{
         try{
             //通过代码创建C3P0数据库连接池
             /*ds = new ComboPooledDataSource();
             ds.setDriverClass("com.mysql.jdbc.Driver");
             ds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbcstudy");
             ds.setUser("root");
             ds.setPassword("XDP");
             ds.setInitialPoolSize(10);
             ds.setMinPoolSize(5);
             ds.setMaxPoolSize(20);*/
             
             //通过读取C3P0的xml配置文件创建数据源，C3P0的xml配置文件c3p0-config.xml必须放在src目录下
        	 configds = new ComboPooledDataSource();//使用C3P0的默认配置来创建数据源
        	 configds.setDriverClass("oracle.jdbc.driver.OracleDriver");  		
             if(region==null){
          		HashMap enivMap = new FileUtil().getEnvironmentConfig("config");

         		dbUrl=(String) enivMap.get("dbUrl");
        		dbUser=(String) enivMap.get("dbUser");
        		dbPass = dbUser.split(":")[1];
        		dbUser =dbUser.split(":")[0];
        		region= (String) enivMap.get("region");
        		maxPoolSize = Integer.parseInt((String) enivMap.get("maxPoolSize"));
        		configds.setJdbcUrl(dbUrl);
        		configds.setUser(dbUser);
        		configds.setPassword(dbPass);
        		configds.setInitialPoolSize(1);
        		configds.setMinPoolSize(1);
        		configds.setMaxPoolSize(maxPoolSize);
        		configds.setMaxIdleTime(5);
             }



             //ds = new ComboPooledDataSource("MySQL");//使用C3P0的命名配置来创建数据源
             
         }catch (Exception e) {
             e.printStackTrace();

             configds.setJdbcUrl("jdbc:oracle:thin:@10.3.3.218:1521:jxcrm");
             configds.setUser("aiqry");
             configds.setPassword("aiqry");
             configds.setInitialPoolSize(1);
             configds.setMinPoolSize(1);
             configds.setMaxPoolSize(1);
             configds.setMaxIdleTime(1);
          }

         
     }
     

     public static Connection getConnection() throws SQLException{
         //从数据源中获取数据库连接
 		System.out.println("已使用的配置库链接"+configds.getNumBusyConnections());// 正在使用连接数

         return configds.getConnection();

     }
     

     public static void close(Connection conn,Statement st,ResultSet rs,PreparedStatement pre) throws SQLException{
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
           		System.out.println("将配置库链接对象归还连接池成功，当前已链接数:"+configds.getNumBusyConnections());// 正在使用连接数

             }catch (Exception e) {
                 e.printStackTrace();
           		System.out.println("warning ----- 将配置库链接对象归还连接池失败，当前已链接数:"+configds.getNumBusyConnections());// 正在使用连接数

             }
         } 
         
         if(conn!=null){
             try{
                 //将Connection连接对象还给数据库连接池
                 conn.close();
            		System.out.println("将配置库链接对象归还连接池成功,当前已链接数:"+configds.getNumBusyConnections());// 正在使用连接数

             }catch (Exception e) {
                 e.printStackTrace();
            		System.out.println("warning ----- 将配置库链接对象归还连接池失败,当前已链接数:"+configds.getNumBusyConnections());// 正在使用连接数

             }
         }
     }
 }