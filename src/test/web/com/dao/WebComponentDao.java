package test.web.com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.core.Is;

import base.com.zl.db.ConfigDbOperate;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.dao.WebCaseDao;
import test.web.com.bean.WebComponentBean;

public class WebComponentDao{
	
	

	
	
	/**
	 * 返回编辑用例信息
	 * @param value
	 * @param optionString
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public ArrayList<WebComponentBean> getComInfo(String value,String optionString) throws Exception{
		
		String sqlString="select t.comid,t.modulecode,t.comcode,t.comname,t.paramname,t.paramvalue,t.pageelement,t.opstep,t.tester,t.result,t.outparam,t.isprivate from web_component t where 1=1   ";	
		if(value!=null&&!value.equals("")){
			
           if(optionString.equals("1")){
            	   
            	   sqlString=sqlString +" and   t.modulecode like '%"+value+"%'" ;
               }else if (optionString.equals("2")) {
            	   sqlString=sqlString +" and  t.comcode like '%"+value+"%'" ;

			   }else if (optionString.equals("3")) {
            	   sqlString=sqlString +" and  t.comname like '%"+value+"%'" ;

			   }else if(optionString.equals("4")){
            	   sqlString=sqlString +" and  t.tester like '%"+value+"%'" ;

			   }else if(optionString.equals("5")){
            	   sqlString=sqlString +" and  t.result like '%"+value+"%'" ;

			   }
		}
		sqlString = sqlString+" order by modulecode,t.comcode,comid desc";
		
		//ResultSet res = null;
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		ArrayList<Object>  list= configDbOperate.searchObject("test.web.com.bean.WebComponentBean", sqlString);
	     ArrayList<WebComponentBean> list1 = new ArrayList<>() ;
	     for(Object bean : list){
	    	 list1.add((WebComponentBean) bean);
	     }
		return list1;

		
	}
	
	

	
	
	
	
	
	
	/**
	 * 返回单个CASE
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public WebComponentBean getOneComInfo(int comId) throws Exception{
		
		String sqlString="select t.comid,t.modulecode,t.comcode,t.comname,t.paramname,t.paramvalue,t.pageelement,t.opstep,t.tester,t.result,t.outparam,t.isprivate from web_component t where  t.comId='"+comId+"'";	

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		WebComponentBean webComponentBean = new WebComponentBean();
		ArrayList<Object> list = configDbOperate.searchObject("test.web.com.bean.WebComponentBean", sqlString);
		webComponentBean  =(WebComponentBean) list.get(0);

			return webComponentBean;

		}
	
	

	
	
	
	
	/**
	 * 获取需要编译的comlist
	 * @param value
	 * @param optionString
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public ArrayList<WebComponentBean> getReleaseComponent(String comId) throws Exception{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();

		String sqlString="select modulecode from web_component  where comid ='"+comId+"' ";	

		
	     String moduleCode = configDbOperate.searchString(sqlString);
	     
	     //按照模块增量发布
       // sqlString="select caseid,module,menuname,casename,caselevel,t.menucode,casecode,paramname,paramvalue,m.pageelement,opstep,checkstep,tester,result,savedata,remark,fee from web_case  t, web_webelement m where t.menucode = m.menucode(+) and t.menucode='"+menuCode+"'  and （t.result='已发布' or caseid='"+caseId+"')";	
		//按照模块全量发布
        sqlString="select t.comid,t.modulecode,t.comcode,t.comname,t.paramname,t.paramvalue,t.pageelement,t.opstep,t.tester,t.result,t.outparam,t.isprivate from web_component t  where  t.moduleCode='"+moduleCode+"'";	

		//ResultSet res = null;
		ArrayList<Object>  list= configDbOperate.searchObject("test.web.com.bean.WebComponentBean", sqlString);
	     ArrayList<WebComponentBean> list1 = new ArrayList<>() ;
	     for(Object bean : list){
	    	 list1.add((WebComponentBean) bean);
	     }
		return list1;


		
	}
	
	
	/**
	 * 更新编译成功的组件状态
	 * @param value
	 * @param optionString
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public void updateReleaseComponent(String comId) throws Exception{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();

		String sqlString="select modulecode from web_component  where comid ='"+comId+"' ";			
	     String modulecode = configDbOperate.searchString(sqlString);	     		
	     configDbOperate.updateData("update web_component set result='已发布' where modulecode='"+modulecode+"'");

	       
	}
	
	
	
	/**
	 * 更新一个组件
	 * @param caseinfo
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public HashMap updateOneComponent(WebComponentBean comInfo) throws SQLException {

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		int i=0;
		HashMap map = new HashMap();
		int  comId = comInfo.getComId();
		String moduleCode =  comInfo.getModuleCode();
		String comCode =  comInfo.getComCode();
		String sqls ="select  comid||'|'||modulecode||'|'||comcode||'|'||paramname||'|'||outparam from web_component where modulecode='"+moduleCode+"' and comcode='"+comCode+"' and comid<>'"+comId+"'" ;
		
		ArrayList stringList= configDbOperate.searchStrings(sqls);
		if(stringList.size()>0){
			
			
			map.put("result", false);
			map.put("message", "组件模块+组件编码重复!保存失败");
			
			
			return map;

		}
		

		
		
		
		
    
        //新增逻辑，提前获取原数据，如果组件修改了模块名，组件名，入参或出参，才更新CASE
        //
	    sqls ="select  comid||'|'||modulecode||'|'||comcode||'|'||paramname||'|'||outparam from web_component where modulecode='"+moduleCode+"' and comcode='"+comCode+"' and comid='"+comId+"'" ;
		
	   
			 stringList= configDbOperate.searchStrings(sqls);

			 String oldcom =null;
		try {
		
			oldcom= (String) stringList.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("result", false);
			map.put("message", "不能修改组件模块or组件编码!提交失败");
			
			
			return map;
		}
		
		String[] oldcoms=oldcom.split("\\|");
        String oldmodulecode=  oldcoms[1];
        String oldcomcode=oldcoms[2];
        String oldparamname="";
        try {
            oldparamname=oldcoms[3];
		} catch (Exception e) {
			// TODO: handle exception
		}
        String outparam="";
        try {
        	outparam=oldcoms[4];
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        
        
        
        
        
		
		
		

		try {

			configDbOperate.getConnction();


				String sql = "update web_component set modulecode =?,comcode=?,comname=?,paramname=?,paramvalue=?,pageelement=?,opstep=?,"+
			                 "tester=?,result=?,outparam=?,isprivate=? where comid=?";

				// 创建实例
				configDbOperate.ps=configDbOperate.conn.prepareStatement(sql);	
				configDbOperate.ps.setString(1, comInfo.getModuleCode());
				configDbOperate.ps.setString(2, comInfo.getComCode());
				configDbOperate.ps.setString(3, comInfo.getComName());
				configDbOperate.ps.setString(4, comInfo.getParamName());
				configDbOperate.ps.setString(5, comInfo.getParamValue());
				configDbOperate.ps.setString(6, comInfo.getPageElement());
				configDbOperate.ps.setString(7, comInfo.getOpStep());
				configDbOperate.ps.setString(8, comInfo.getTester());
				configDbOperate.ps.setString(9, "未发布");
				configDbOperate.ps.setString(10, comInfo.getOutParam());
				configDbOperate.ps.setString(11, comInfo.getIsPrivate());
				configDbOperate.ps.setInt(12, comInfo.getComId());
				i = configDbOperate.ps.executeUpdate();
				configDbOperate.closeConnction();

				//1.获取所有调用过这个组件的组件，更新组件状态到未发布
		        ArrayList<String> relCom = this.getRelCom(moduleCode, comCode);
		        
		        String updateSql="";
		        for(String comIdtemp : relCom){
			        updateSql ="update web_component set result='未发布' where comid='"+comIdtemp+"'";
                    configDbOperate.updateData(updateSql);                   	        	
		        }
		        
                //2。更新同模块组件
                updateSql = "update web_component set result='未发布' where modulecode='"+moduleCode+"'";
                configDbOperate.updateData(updateSql);                   	        	

                
                

                //3.获取所有调用过这个组件的模块的CASE 
                if( !oldmodulecode.equals(comInfo.getModuleCode())  || !oldcomcode.equals(comInfo.getComCode()) ||  !oldparamname.equals(comInfo.getParamName()) || !outparam.equals(comInfo.getOutParam()) ){
                
                
                
                WebCaseDao webCaseDao = new WebCaseDao();
		        ArrayList<String> relCase = webCaseDao.getRelCase(moduleCode);
                //更新这些CASE状态到未发布
		        updateSql="";
		        for(String caseID : relCase){
		        	//更新同时删除执行当前CASE
			        updateSql ="update web_case set result='未发布' where caseid='"+caseID+"'";
                    configDbOperate.updateData(updateSql);                       
                    configDbOperate.updateData("delete web_case_current where caseid='"+caseID+"'");    
                    
                    
                    //然后还要更新和删除这个CASE的菜单下的所有CASE
                    configDbOperate.updateData("update  web_case t set result='未发布'   where t.menucode in ( select  menucode from web_case t where t.caseid='"+caseID+"' )");    
                    configDbOperate.updateData("delete  web_case_current t  where t.menucode in ( select  menucode from web_case t where t.caseid='"+caseID+"' )");    

                    
                    

		        }
                
		        
                }
		        
		        
		        
		        
		        
		        
		        
		        
		        
				
				//configDbOperate.updateData("update web_case set result='未发布' where menucode='"+menuCode+"'");
				//configDbOperate.delData("delete web_case_current where menucode='"+menuCode+"'");
				
				
				
	            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			configDbOperate.closeConnction();

			if (i > 0) {
				map.put("result", true);
				map.put("message", "组件保存成功");
				return map;
			}else{
				map.put("result", false);
				map.put("message", "组件保存失败");
				return map;

			}
		}
	
	}



	

	/**
	 * 添加组件
	 * @param caseInfo
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public HashMap addOneComponent(WebComponentBean webComponentBean) throws Exception{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		HashMap map = new HashMap();
		map.put("result", true);

		int i=0;
		if(configDbOperate.searchStrings("select comid from web_component where modulecode='"+webComponentBean.getModuleCode()+"' and comcode='"+webComponentBean.getComCode()+"'" ).size()>0){
			
			map.put("result", false);
			map.put("message", "组件模块+组件编码重复!保存失败");
			
			
			return map;

		}
		
		String index = configDbOperate.searchString("select   WEB_COMPONENT_SEQUENCE.nextval from dual");
		webComponentBean.setComId(Integer.parseInt(index));


		configDbOperate.insertObject("WEB_COMPONENT", webComponentBean);
		return map;


	
	}
	
	
	/**
	 * 通过模块和方法和查询所有关联调用的组件
	 * @param comId
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<String> getRelCom(String moduleCode,String comCode) throws SQLException{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();		
		String searchCode ="com."+moduleCode+".";
		return configDbOperate.searchStrings("select t.comid from web_component t  where t.opstep like '%"+searchCode+"%'");
			
	}
	
	
	
	/**
	 * 校验组件是否已发布
	 * @param comID
	 * @return
	 * @throws SQLException 
	 */
	public String isRelease(String modulecode) throws SQLException{
		String noRelease="";
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		String isRelease = "select modulecode||'.'||comcode from web_component where modulecode='"+modulecode+"'  and result='未发布'";
		ArrayList<String>  noRel  = configDbOperate.searchStrings(isRelease);
		boolean result =true;
		if( !(noRel.isEmpty()) ){
			noRelease=noRel.toString();
			
		}
		return noRelease  ;
		
	}
	
	
	
	
	

		
	
	/**
	 * 删除组件
	 * @param comId
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public String  deleteOneComponent(int comId) throws Exception{
		
		//需要判断该组件是否有被使用，如果被CASE和其他组件调用，不允许删除 ************************
		ConfigDbOperate configDbOperate = new ConfigDbOperate();		
		WebComponentBean webComponentBean = new WebComponentBean();
		webComponentBean =  this.getOneComInfo(comId);
		String moduleCode= webComponentBean.getModuleCode();
		String comCode =  webComponentBean.getComCode();
        ArrayList<String> relCom = this.getRelCom(moduleCode, comCode);
        ArrayList<String> relCase = new WebCaseDao().getRelCase(moduleCode);

        //存在组件或CASE调用关系
        if( (relCom.size()>0)||(relCase.size()>0) ){
        	
        	return "组件："+relCom.toString()+";case:"+relCase.toString();
        }
		
		//插入历史表
		String sqlhis = "insert into web_component_his  select * from web_component where comid='"+comId+"'";
		configDbOperate.insertData(sqlhis);	
		String sql = "delete web_component where comid='"+comId+"'";
		configDbOperate.delData(sql);


	
			return "";
	
	}
	
	

}
