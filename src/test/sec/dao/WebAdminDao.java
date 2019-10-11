package test.sec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.com.zl.db.ConfigDbOperate;
import test.sec.bean.WebAdminBean;

public class WebAdminDao {
	

	
	
	/**
	 * 返回员工信息到前台
	 * @param value
	 * @param optionString
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public List<WebAdminBean> getAdminInfo(String userAdmin,String userPass){
		
		String sqlString="select operator_id,user_admin,user_pass,user_name,user_mobile,user_staff,user_email,is_used,ip from web_admin  ";	
        if(userAdmin!=null&&userPass!=null){
        	
        	sqlString=sqlString + "where user_admin='"+userAdmin+"' and user_pass='"+userPass+"'";
        	
        }
		
		ResultSet res = null;
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		List<WebAdminBean> list = null;
		try {
			
			 res =configDbOperate.searchResultSet(sqlString);
			 list = new ArrayList<WebAdminBean>();
			while (res.next()) {
				WebAdminBean adminInfo = new WebAdminBean();
				adminInfo.setOperatorId(res.getInt("operator_id"));
				adminInfo.setUserAdmin(res.getString("user_admin"));
				adminInfo.setUserPass(res.getString("user_pass"));
				adminInfo.setUserName(res.getString("user_name"));
				adminInfo.setUserMobile(res.getString("user_mobile"));
				adminInfo.setUserStaff(res.getString("user_staff"));
				adminInfo.setUserEmail(res.getString("user_email"));
				adminInfo.setIsUsed(res.getString("is_used"));
				adminInfo.setIp(res.getString("ip"));

				list.add(adminInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
			try {
				configDbOperate.closeConnction();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("关闭连接失败");
			}

			return list;
		}
		

		
	}
	/**
	 * 返回单个员工信息
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public WebAdminBean getOneAdminInfo(int operatorId) throws Exception{
		
		String sqlString="select operator_id,user_admin,user_pass,user_name,user_mobile,user_staff,user_email,is_used from web_admin  where operator_id='"+operatorId+"'";	

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		ResultSet res = null;
		WebAdminBean adminInfo = new WebAdminBean();

		try {
			 res =configDbOperate.searchResultSet(sqlString);

			while (res.next()) {
				adminInfo.setOperatorId(res.getInt("operator_id"));
				adminInfo.setUserAdmin(res.getString("user_admin"));
				adminInfo.setUserPass(res.getString("user_pass"));
				adminInfo.setUserName(res.getString("user_name"));
				adminInfo.setUserMobile(res.getString("user_mobile"));
				adminInfo.setUserStaff(res.getString("user_staff"));
				adminInfo.setUserEmail(res.getString("user_email"));
				adminInfo.setIsUsed(res.getString("is_used"));
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}finally{
			try {
                configDbOperate.closeConnction();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("关闭连接失败");
			}
			return adminInfo;
		}


		
		
			
		}
		
	/**
	 * 更新一个员工信息
	 * @param caseinfo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public boolean updateOneAdmin(WebAdminBean adminInfo) throws Exception {
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		
		int i=0;



		try {

			configDbOperate.getConnction();


				String sql = "update web_admin set user_admin=?,user_pass=?,user_name=?,user_mobile=?,user_staff=?,user_email=?,is_used=?  where operator_id=?";

			
			
				// 创建实例
				configDbOperate.ps=configDbOperate.conn.prepareStatement(sql);		
				configDbOperate.ps.setString(1, adminInfo.getUserAdmin());
				configDbOperate.ps.setString(2, adminInfo.getUserPass());
				configDbOperate.ps.setString(3, adminInfo.getUserName());
				configDbOperate.ps.setString(4, adminInfo.getUserMobile().isEmpty()?" ":adminInfo.getUserMobile());
				configDbOperate.ps.setString(5, adminInfo.getUserStaff().isEmpty()?" ":adminInfo.getUserStaff());
				configDbOperate.ps.setString(6, adminInfo.getUserEmail().isEmpty()?" ":adminInfo.getUserEmail());
				configDbOperate.ps.setString(7, adminInfo.getIsUsed());
				configDbOperate.ps.setInt(8, adminInfo.getOperatorId());
                      System.out.println(adminInfo.getOperatorId());

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
	 * 添加员工
	 * @param caseInfo
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public boolean addOneAdmin(WebAdminBean adminInfo) throws Exception{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		String sql = "insert into web_admin values (web_admin_sequence.nextval,?,?,?,?,?,?,?,null)";
		int i=0;
		try {
			configDbOperate.getConnction();
		
			// 创建实例
			configDbOperate.ps=configDbOperate.conn.prepareStatement(sql);
					
			configDbOperate.ps.setString(1, adminInfo.getUserAdmin());
			configDbOperate.ps.setString(2, adminInfo.getUserPass());
			configDbOperate.ps.setString(3, adminInfo.getUserName());
			configDbOperate.ps.setString(4, adminInfo.getUserMobile().isEmpty()?" ":adminInfo.getUserMobile());
			configDbOperate.ps.setString(5, adminInfo.getUserStaff().isEmpty()?" ":adminInfo.getUserStaff());
			configDbOperate.ps.setString(6, adminInfo.getUserEmail().isEmpty()?" ":adminInfo.getUserEmail());
			configDbOperate.ps.setString(7, adminInfo.getIsUsed());


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
	public boolean deleteOneAdmin(int operatorId) throws SQLException{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();


		String sql = "delete web_admin where operator_id='"+operatorId+"'";
		configDbOperate.delData(sql);

		configDbOperate.closeConnction();
			return true;
		
	
	}



}
