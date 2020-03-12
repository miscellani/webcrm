package test.web.cases.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.core.Is;

import base.com.zl.db.ConfigDbOperate;
import test.web.cases.bean.WebCaseBean;

public class WebCaseDao{
	
	

	
	
	/**
	 * 返回编辑用例信息
	 * @param value
	 * @param optionString
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public ArrayList<WebCaseBean> getCaseInfo(String value,String optionString) throws Exception{
		
		String sqlString="select caseid,module,menuname,casename,caselevel,t.menucode,casecode,paramname,paramvalue,m.pageelement,opstep,checkstep,tester,result,savedata,remark,fee from web_case t, web_webelement m where t.menucode = m.menucode(+)  ";	
		if(value!=null&&!value.equals("")){
			
           if(optionString.equals("1")){
            	   
            	   sqlString=sqlString +" and   t.module like '%"+value+"%'" ;
               }else if (optionString.equals("2")) {
            	   sqlString=sqlString +" and  t.menuname like '%"+value+"%'" ;

			   }else if (optionString.equals("3")) {
            	   sqlString=sqlString +" and  t.casename like '%"+value+"%'" ;

			   }else if(optionString.equals("4")){
            	   sqlString=sqlString +" and  t.caselevel like '%"+value+"%'" ;

			   }else if(optionString.equals("5")){
            	   sqlString=sqlString +" and  t.tester like '%"+value+"%'" ;

			   }else if(optionString.equals("6")){
            	   sqlString=sqlString +" and  t.result like '%"+value+"%'" ;

			   }
		}
		sqlString = sqlString+" order by module,t.menucode,caseid desc";
		
		//ResultSet res = null;
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		ArrayList<Object>  list= configDbOperate.searchObject("test.web.cases.bean.WebCaseBean", sqlString);
	     ArrayList<WebCaseBean> list1 = new ArrayList<>() ;
	     for(Object bean : list){
	    	 list1.add((WebCaseBean) bean);
	     }
		return list1;
		/*List<CaseInfo> list = null;
		try {
			
			 res =dbOperate.searchResultSet(sqlString);
			 list = new ArrayList<CaseInfo>();
			while (res.next()) {
				CaseInfo caseinfo = new CaseInfo();
				caseinfo.setCaseid(res.getInt("caseid"));
				caseinfo.setMainCaseId(res.getString("maincaseid"));	
				caseinfo.setChildCaseid(res.getInt("childcaseid"));
				caseinfo.setType(res.getString("type"));				
				caseinfo.setModule(res.getString("module"));
				caseinfo.setCasename(res.getString("casename"));
				caseinfo.setCaselevel(res.getString("caselevel"));
				caseinfo.setInputhead(res.getString("inputhead"));
				caseinfo.setInputbody(res.getString("inputbody"));
				caseinfo.setInputparam(res.getString("inputparam"));
				caseinfo.setOutputparam(res.getString("outputparam"));
				caseinfo.setUrl(res.getString("url"));
				caseinfo.setCheckpoint(res.getString("checkpoint"));			
				caseinfo.setTester(res.getString("tester"));			
				list.add(caseinfo);
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
			return list;
		}
		*/

		
	}
	
	

	
	
	
	
	
	
	/**
	 * 返回单个CASE
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public WebCaseBean getOneCaseInfo(int caseId) throws Exception{
		
		String sqlString="select caseid,module,menuname,casename,caselevel,t.menucode,casecode,paramname,paramvalue,m.pageelement,opstep,checkstep,tester,result,savedata,remark,fee from web_case  t, web_webelement m where t.menucode = m.menucode(+) and t.caseid='"+caseId+"'";	

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		WebCaseBean webCaseBean = new WebCaseBean();
		ArrayList<Object> list = configDbOperate.searchObject("test.web.cases.bean.WebCaseBean", sqlString);
				webCaseBean  =(WebCaseBean) list.get(0);

			return webCaseBean;

		}
	
	

	
	
	
	
	/**
	 * 获取需要编译的caselist
	 * @param value
	 * @param optionString
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public ArrayList<WebCaseBean> getReleaseCase(String caseId) throws Exception{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();

		String sqlString="select menucode from web_case  where caseid ='"+caseId+"' ";	

		
	     String menuCode = configDbOperate.searchString(sqlString);
	     
	     //按照模块增量发布
       // sqlString="select caseid,module,menuname,casename,caselevel,t.menucode,casecode,paramname,paramvalue,m.pageelement,opstep,checkstep,tester,result,savedata,remark,fee from web_case  t, web_webelement m where t.menucode = m.menucode(+) and t.menucode='"+menuCode+"'  and （t.result='已发布' or caseid='"+caseId+"')";	
		//按照模块全量发布
        sqlString="select caseid,module,menuname,casename,caselevel,t.menucode,casecode,paramname,paramvalue,m.pageelement,opstep,checkstep,tester,result,savedata,remark,fee from web_case  t, web_webelement m where t.menucode = m.menucode(+) and t.menucode='"+menuCode+"'";	

		//ResultSet res = null;
		ArrayList<Object>  list= configDbOperate.searchObject("test.web.cases.bean.WebCaseBean", sqlString);
	     ArrayList<WebCaseBean> list1 = new ArrayList<>() ;
	     for(Object bean : list){
	    	 list1.add((WebCaseBean) bean);
	     }
		return list1;


		
	}
	
	
	/**
	 * 更新编译成功的CASE状态,并更新到执行表中
	 * @param value
	 * @param optionString
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public void updateReleaseCase(String caseId) throws Exception{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();

		String sqlString="select menucode from web_case  where caseid ='"+caseId+"' ";			
	     String menuCode = configDbOperate.searchString(sqlString);	     		
	     configDbOperate.updateData("update web_Case set result='已发布' where menuCode='"+menuCode+"'");
	     configDbOperate.delData("delete web_case_current t where menucode='"+menuCode+"'");
	     configDbOperate.insertData("insert into web_case_current select * from web_Case where menucode='"+menuCode+"'");
	     configDbOperate.updateData("update web_case_current set result='未执行' where menuCode='"+menuCode+"'");


	       
	}
	
	
	
	/**
	 * 更新一个CASE
	 * @param caseinfo
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public HashMap updateOneCase(WebCaseBean caseinfo) throws SQLException {

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		int i=0;
		HashMap map = new HashMap();
		String sqls ="select caseid from web_case where menucode='"+caseinfo.getMenuCode()+"' and casecode='"+caseinfo.getCaseCode()+"' and caseid<>'"+caseinfo.getCaseId()+"'" ;
		if(configDbOperate.searchStrings(sqls).size()>0){
			
			
			map.put("result", false);
			map.put("message", "菜单编码+用例编码重复!保存失败");
			
			
			return map;

		}
		
		
	
		String menuCode=caseinfo.getMenuCode();
		String element = caseinfo.getPageElement();
		
		//如果页面元素有值，就更新或新增
		if(( (element!=null) &&(!element.equals("") )&&( !element.equals("null")))&&((menuCode!=null) &&(!menuCode.equals("") )&&( !menuCode.equals("null"))) ){
			
			
			if(configDbOperate.searchStrings("select menucode from web_webelement where menucode='"+menuCode+"'" ).size()>0){
				
				configDbOperate.updateData("update web_webelement set pageelement='"+element+"' where menucode='"+menuCode+"'");				

			}else{
				configDbOperate.insertData("insert into web_webelement (MENUCODE, PAGEELEMENT) values ('"+menuCode+"', '"+element+"')");
			}
			
			
		}

		
		//如果页面元素无值，并且菜单编码是新增，则插入一条web_webelement 空数据
		if(( (element==null) ||(element.equals("") )||( element.equals("null"))) ){
			
			
			if( ! (configDbOperate.searchStrings("select menucode from web_webelement where menucode='"+menuCode+"'" ).size()>0) ){
				
				configDbOperate.insertData("insert into web_webelement (MENUCODE, PAGEELEMENT) values ('"+menuCode+"', '')");

			}
			
			
		}		
		
		

		try {

			configDbOperate.getConnction();


				String sql = "update web_case set module=?,menuname=?,casename=?,caselevel=?,menucode=?,casecode=?,pageelement=?,opstep=?,"+
			                 "checkstep=?,tester=?,result=?,paramname=?,paramvalue=? where caseid=?";

				// 创建实例
				configDbOperate.ps=configDbOperate.conn.prepareStatement(sql);	
				configDbOperate.ps.setString(1, caseinfo.getModule());
				configDbOperate.ps.setString(2, caseinfo.getMenuName());
				configDbOperate.ps.setString(3, caseinfo.getCaseName());
				configDbOperate.ps.setString(4, caseinfo.getCaseLevel());
				configDbOperate.ps.setString(5, caseinfo.getMenuCode());
				configDbOperate.ps.setString(6, caseinfo.getCaseCode());
				configDbOperate.ps.setString(7, "");
				//dbOperate.ps.setString(7, caseinfo.getPageElement().isEmpty()?"":caseinfo.getPageElement());
				configDbOperate.ps.setString(8, caseinfo.getOpStep().isEmpty()?"":caseinfo.getOpStep());
				configDbOperate.ps.setString(9, caseinfo.getCheckStep().isEmpty()?"":caseinfo.getCheckStep());
				configDbOperate.ps.setString(10, caseinfo.getTester());
				configDbOperate.ps.setString(11, "未发布");

				configDbOperate.ps.setString(12, caseinfo.getParamName().isEmpty()?"":caseinfo.getParamName());
				configDbOperate.ps.setString(13, caseinfo.getParamValue().isEmpty()?"":caseinfo.getParamValue());
				configDbOperate.ps.setInt(14, caseinfo.getCaseId());
				i = configDbOperate.ps.executeUpdate();
				configDbOperate.closeConnction();

				//将菜单下的所有CASE更新为未发布，同时删除当前case表中的数据				
				configDbOperate.updateData("update web_case set result='未发布' where menucode='"+menuCode+"'");
				configDbOperate.delData("delete web_case_current where menucode='"+menuCode+"'");
				
				
				
	            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			configDbOperate.closeConnction();

			if (i > 0) {
				map.put("result", true);
				map.put("message", "用例更新成功");
				return map;
			}else{
				map.put("result", true);
				map.put("message", "用例更新失败");
				return map;

			}
		}
	
	}



	

	/**
	 * 添加case
	 * @param caseInfo
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	public HashMap addOneCase(WebCaseBean caseInfo) throws Exception{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		HashMap map = new HashMap();
		map.put("result", true);

		int i=0;
		if(configDbOperate.searchStrings("select caseid from web_case where menucode='"+caseInfo.getMenuCode()+"' and casecode='"+caseInfo.getCaseCode()+"'" ).size()>0){
			
			map.put("result", false);
			map.put("message", "菜单编码+用例编码重复!保存失败");
			
			
			return map;

		}
		
		
		
		
		String index = configDbOperate.searchString("select   web_case_sequence.nextval from dual");
		caseInfo.setCaseId(Integer.parseInt(index));
		
		String menuCode=caseInfo.getMenuCode();
		String element = caseInfo.getPageElement();
		
		//页面元素有值
		if(  ((element!=null) &&(!element.equals("") )&&( !element.equals("null")))&&((menuCode!=null) &&(!menuCode.equals("") )&&( !menuCode.equals("null")))      ){
			
			
			if(configDbOperate.searchStrings("select menucode from web_webelement where menucode='"+menuCode+"'" ).size()>0){
				
				configDbOperate.updateData("update web_webelement set pageelement='"+element+"' where menucode='"+menuCode+"'");				

			}else{
				configDbOperate.insertData("insert into web_webelement (MENUCODE, PAGEELEMENT) values ('"+menuCode+"', '"+element+"')");
			}
			
			
		}

		
		
		//如果页面元素无值，并且菜单编码是新增，则插入一条web_webelement 空数据
		if(( (element==null) ||(element.equals("") )||( element.equals("null"))) ){
			
			
			if( ! (configDbOperate.searchStrings("select menucode from web_webelement where menucode='"+menuCode+"'" ).size()>0) ){
				
				configDbOperate.insertData("insert into web_webelement (MENUCODE, PAGEELEMENT) values ('"+menuCode+"', '')");

			}
			
			
		}	
		
		// 如果菜单编码在元素表中存在
		   //如果有值就更新一下
		   //无值就不处理
		//如果菜单编码在元素表中不存在
		   //不管有没有值都插一条数据
		
		
		
		
		
		
		
		caseInfo.setPageElement("");
		configDbOperate.insertObject("web_case", caseInfo);
		return map;

	
	}
	
	/**
	 * 通过模块查询所有关联调用的CASE（包括直接调用该组件的CASE，以及这些CASE的同菜单CASE）
	 * @param comId
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<String> getRelCase(String moduleCode) throws SQLException{
		
		ConfigDbOperate configDbOperate = new ConfigDbOperate();		
		return configDbOperate.searchStrings("select t.caseid  from web_case t where t.menucode in ( select t.menucode from web_case t  where t.opstep like '%com."+moduleCode+".%' ) ");
		
	}
	
	

	
	
	
	@SuppressWarnings("finally")
	public boolean deleteOneCase(int caseId) throws SQLException{
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		//插入历史表
		String sqlhis = "insert into web_case_his  select * from web_case where caseid='"+caseId+"'";
		configDbOperate.insertData(sqlhis);	
		String sql = "delete web_case where caseid='"+caseId+"'";
		configDbOperate.delData(sql);
		sql = "delete web_case_current where caseid='"+caseId+"'";
		configDbOperate.delData(sql);

	
			return true;
	
	}
	
	
	public String[] getCaseDivide() throws Exception{
		
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		String sql ="select * from (select t.module as name,count(*) as mum from web_Case t group by module) order by mum asc";
		ArrayList<HashMap> list = null;
			list = configDbOperate.searchMaps(sql);


         int num =0;
		StringBuffer all =new StringBuffer();
		for(int i=0;i<list.size();i++){
			String  strings = "";
			strings = "['"+strings+(String) list.get(i).get("NAME")+"'";
			strings = strings +","+(String) list.get(i).get("MUM")+"]";
			System.out.println(strings.toString());
			num =num+ Integer.parseInt((String) list.get(i).get("MUM"));
			all.append(","+strings);
		}
		String sall = all.toString().substring(1);

		String[] strings= new String[3];
		
		strings[0]="["+sall+"]";
		
		strings[1] = String.valueOf(list.size());
		strings[2] = String.valueOf(num) ;		
		return  strings;
		
	}
	
	

}
