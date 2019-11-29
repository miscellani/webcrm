package test.web.cases.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import test.config.dao.WebConfigDao;
import test.web.cases.bean.WebCaseBean;
import base.com.zl.db.ConfigDbOperate;

public class WebCaseExecuteDao {

	/**
	 * 返回执行用例信息到前台
	 * 
	 * @param value
	 * @param optionString
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public List<WebCaseBean> getCaseInfo(String module, String menuname, String caseName, String tester,
			String caseLevel, String result) throws Exception {

		String sqlString = "select t.caseid,t.module,t.menuname,t.casename,t.caselevel,t.menucode,t.casecode,t.paramname,t.paramvalue,t.pageelement,t.opstep,t.checkstep,t.tester,t.result,t.savedata,t.remark,t.fee from web_case_current t  where 1=1 ";

		if (module != null && !module.equals("")) {
			sqlString = sqlString + " and t.module like '%" + module + "%'";
		}

		if (menuname != null && !menuname.equals("")) {
			sqlString = sqlString + " and t.menuname like '%" + menuname + "%'";
		}
		if (caseName != null && !caseName.equals("")) {
			sqlString = sqlString + " and t.caseName like '%" + caseName + "%'";
		}
		if (tester != null && !tester.equals("")) {
			sqlString = sqlString + " and t.tester like '%" + tester + "%'";
		}
		if (caseLevel != null && !caseLevel.equals("")) {
			sqlString = sqlString + " and t.caseLevel in  ('" + caseLevel + "')";
		}
		if (result != null && !result.equals("")) {
			sqlString = sqlString + " and t.result = '" + result + "'";
		}

		//module,menucode,
		sqlString = sqlString + " order by t.tester,t.caseid desc";

		ResultSet res = null;
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		List<Object> list = null;
		list = configDbOperate.searchObject("test.web.cases.bean.WebCaseBean", sqlString);
		ArrayList<WebCaseBean> list1 = new ArrayList<>();
		for (Object bean : list) {
			list1.add((WebCaseBean) bean);
		}

		/*
		 * try {
		 * 
		 * res =dbOperate.searchResultSet(sqlString); while (res.next()) {
		 * WebCaseBean caseinfo = new WebCaseBean();
		 * caseinfo.setCaseid(res.getInt("caseid"));
		 * caseinfo.setMainCaseId(res.getString("maincaseid"));
		 * caseinfo.setChildCaseid(res.getInt("childcaseid"));
		 * caseinfo.setType(res.getString("type"));
		 * caseinfo.setModule(res.getString("module"));
		 * caseinfo.setCasename(res.getString("casename"));
		 * caseinfo.setCaselevel(res.getString("caselevel"));
		 * caseinfo.setInputhead(res.getString("inputhead"));
		 * caseinfo.setInputbody(res.getString("inputbody"));
		 * caseinfo.setInputparam(res.getString("inputparam"));
		 * caseinfo.setOutputparam(res.getString("outputparam"));
		 * caseinfo.setUrl(res.getString("url"));
		 * caseinfo.setCheckpoint(res.getString("checkpoint"));
		 * caseinfo.setTester(res.getString("tester"));
		 * caseinfo.setResult(res.getString("result"));
		 * caseinfo.setFee(res.getInt("fee")); list.add(caseinfo); } } catch
		 * (Exception e) { // TODO: handle exception e.printStackTrace();
		 * }finally{ try { dbOperate.closeConnction(); } catch (Exception e2) {
		 * // TODO: handle exception System.out.println("关闭连接失败"); }
		 */
		return list1;

	}

	/**
	 * 返回单个执行CASE
	 * 
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public WebCaseBean getExecutCaseInfo(String caseId) throws Exception {

		// String sqlString="select
		// t.caseid,t.module,t.menuname,t.casename,t.caselevel,t.menucode,t.casecode,t.pageelement,t.opstep,t.checkstep,t.tester,m.result,m.savedata,m.remark,m.fee
		// from web_case t,web_case_current m where t.caseid = m.caseid(+) and
		// t.caseid='"+caseId+"'";
		String sqlString = "select t.caseid,t.module,t.menuname,t.casename,t.caselevel,m.menucode,t.casecode,t.paramname,t.paramvalue,m.pageelement,t.opstep,t.checkstep,t.tester,t.result,t.savedata,t.remark,t.fee from web_case_current  t, web_webelement m where t.menucode = m.menucode and t.caseid='"
				+ caseId + "'";

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		ResultSet res = null;
		WebCaseBean caseinfo = new WebCaseBean();

		WebCaseBean webCaseBean = (WebCaseBean) configDbOperate
				.searchObject("test.web.cases.bean.WebCaseBean", sqlString).get(0);
		/*
		 * try { res =dbOperate.searchResultSet(sqlString);
		 * 
		 * while (res.next()) { caseinfo.setCaseid(res.getInt("caseid"));
		 * caseinfo.setMainCaseId(res.getString("maincaseid"));
		 * caseinfo.setChildCaseid(res.getInt("childcaseid"));
		 * caseinfo.setType(res.getString("type"));
		 * caseinfo.setModule(res.getString("module"));
		 * caseinfo.setCasename(res.getString("casename"));
		 * caseinfo.setCaselevel(res.getString("caselevel"));
		 * caseinfo.setInputhead(res.getString("inputhead"));
		 * caseinfo.setInputbody(res.getString("inputbody"));
		 * caseinfo.setInputparam(res.getString("inputparam"));
		 * caseinfo.setOutputparam(res.getString("outputparam"));
		 * caseinfo.setUrl(res.getString("url"));
		 * caseinfo.setCheckpoint(res.getString("checkpoint"));
		 * caseinfo.setTester(res.getString("tester"));
		 * caseinfo.setResult(res.getString("result"));
		 * caseinfo.setRequestbody(res.getString("requestBody"));
		 * caseinfo.setResponsebody1(res.getString("responseBody1"));
		 * caseinfo.setResponsebody2(res.getString("responseBody2"));
		 * caseinfo.setResponsebody3(res.getString("responseBody3"));
		 * caseinfo.setRemark(res.getString("remark"));
		 * caseinfo.setFee(res.getInt("fee"));
		 * 
		 * } } catch (Exception e) { // TODO: handle exception
		 * e.printStackTrace();
		 * 
		 * }finally{ try { dbOperate.closeConnction(); } catch (Exception e2) {
		 * // TODO: handle exception System.out.println("关闭连接失败"); } return
		 * caseinfo; }
		 */
		return webCaseBean;

	}

	/**
	 * 返回一组执行CASE
	 * 
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	/*
	 * @SuppressWarnings("finally") public List<WebCaseBean>
	 * getExecutCaseInfo(String mainCaseId) throws Exception{
	 * 
	 * String
	 * sqlString="select caseid,maincaseid,childcaseid,type,module,casename,caselevel,inputhead,inputbody,inputparam,outputparam,url,checkpoint,tester,result,requestBody,responseBody1,responseBody2,responseBody3,remark,fee from sp_case_current  where maincaseid='"
	 * +mainCaseId+"' order by childcaseid asc";
	 * 
	 * DbOperate dbOperate = new DbOperate(); ResultSet res = null;
	 * 
	 * ArrayList<WebCaseBean> list = new ArrayList<WebCaseBean>();
	 * 
	 * try { res =dbOperate.searchResultSet(sqlString);
	 * 
	 * while (res.next()) { WebCaseBean caseinfo = new WebCaseBean();
	 * caseinfo.setCaseid(res.getInt("caseid"));
	 * caseinfo.setMainCaseId(res.getString("maincaseid"));
	 * caseinfo.setChildCaseid(res.getInt("childcaseid"));
	 * caseinfo.setType(res.getString("type"));
	 * caseinfo.setModule(res.getString("module"));
	 * caseinfo.setCasename(res.getString("casename"));
	 * caseinfo.setCaselevel(res.getString("caselevel"));
	 * caseinfo.setInputhead(res.getString("inputhead"));
	 * caseinfo.setInputbody(res.getString("inputbody"));
	 * caseinfo.setInputparam(res.getString("inputparam"));
	 * caseinfo.setOutputparam(res.getString("outputparam"));
	 * caseinfo.setUrl(res.getString("url"));
	 * caseinfo.setCheckpoint(res.getString("checkpoint"));
	 * caseinfo.setTester(res.getString("tester"));
	 * caseinfo.setResult(res.getString("result"));
	 * caseinfo.setRequestbody(res.getString("requestBody"));
	 * caseinfo.setResponsebody1(res.getString("responseBody1"));
	 * caseinfo.setResponsebody2(res.getString("responseBody2"));
	 * caseinfo.setResponsebody3(res.getString("responseBody3"));
	 * caseinfo.setRemark(res.getString("remark"));
	 * caseinfo.setFee(res.getInt("fee")); list.add(caseinfo); } } catch
	 * (Exception e) { // TODO: handle exception e.printStackTrace();
	 * 
	 * }finally{ try { dbOperate.closeConnction(); } catch (Exception e2) { //
	 * TODO: handle exception System.out.println("关闭连接失败"); }
	 * 
	 * return list; }
	 * 
	 * 
	 * }
	 */

	/**
	 * 更新一个执行CASE
	 * 
	 * @param caseinfo
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public boolean updateExecuteCase(WebCaseBean caseinfo) throws SQLException {
		ConfigDbOperate configDbOperate = new ConfigDbOperate();

		int i = 0;

		try {

			configDbOperate.getConnction();

			String sql = "update web_case_current set result=?,savedata=?,remark=?,fee=? where caseid=?";

			// 创建实例
			configDbOperate.ps = configDbOperate.conn.prepareStatement(sql);
			configDbOperate.ps.setString(1, caseinfo.getResult());
			configDbOperate.ps.setString(2, caseinfo.getSaveData());
			String remark = caseinfo.getRemark();
			if (remark.length() > 4000) {
				remark = remark.substring(0, 3000);
			}
			configDbOperate.ps.setString(3, remark);
			configDbOperate.ps.setInt(4, caseinfo.getFee());
			configDbOperate.ps.setInt(5, caseinfo.getCaseId());
			i = configDbOperate.ps.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			configDbOperate.closeConnction();
			if (i > 0) {
				return true;
			} else {
				return false;

			}
		}

	}

	/**
	 * 返回当前执行结果比例
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap> getExecuteResult() throws Exception {
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		ArrayList<HashMap> result = null;

		result = configDbOperate.searchMaps("select count(*) 总数,count(case result when '未执行' then result end ) 未执行,"
				+ "count(case result when '成功' then result end ) 成功,"
				+ "count(case result when '失败' then result end ) 失败,"
				+ "count(case result when '正确' then result end ) 正确,"
				+ "count(case result when '错误' then result end ) 错误 " + "from web_case_current where caselevel <>'debug' ");

		return result;

	}

	/**
	 * 过滤获取执行结果，给邮件统计用
	 * 
	 * @param map
	 *            启动传入的map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap> getPartyExecuteResult(HashMap<String, String> map) throws Exception {

		ArrayList<String> list = new ArrayList<String>();
		WebCaseExecuteDao caseInfoExecuteDao = new WebCaseExecuteDao();
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		String filterType = (String) map.get("filterType");
		ArrayList<HashMap> resultList = null;

		if (filterType.equals("all")) {

			resultList = configDbOperate.searchMaps("select count(*) 总数,count(case result when '未执行' then result end ) 未执行,"
					+ "count(case result when '成功' then result end ) 成功,"
					+ "count(case result when '失败' then result end ) 失败,"
					+ "count(case result when '正确' then result end ) 正确,"
					+ "count(case result when '错误' then result end ) 错误 " + "from web_case_current where caselevel <>'debug' ");

		} else {
			String menuName = map.get("menuName");
			String caseName = map.get("caseName");
			String module = map.get("module");
			String tester = map.get("tester");
			String caseLevel = map.get("caseLevel");
			String result = map.get("result");

			StringBuffer sql = new StringBuffer();

			sql.append("select count(*) 总数,count(case result when '未执行' then result end ) 未执行,"
					+ "count(case result when '成功' then result end ) 成功,"
					+ "count(case result when '失败' then result end ) 失败,"
					+ "count(case result when '正确' then result end ) 正确,"
					+ "count(case result when '错误' then result end ) 错误 " + "from web_case_current where  1=1 and caselevel <>'debug'  ");
			
			
			
			if (module != null && !(module.equals(""))) {
				sql.append(" and module like '%" + module + "%'");

			}
			if (menuName != null && !(menuName.equals(""))) {
				sql.append(" and menuName like '%" + menuName + "%'");

			}
			if (caseName != null && !(caseName.equals(""))) {
				sql.append(" and caseName like '%" + caseName + "%'");

			}
			if (tester != null && !(tester.equals(""))) {
				sql.append(" and tester like '%" + tester + "%'");

			}

			if (caseLevel != null && !(caseLevel.equals(""))) {
				sql.append(" and caseLevel in ('" + caseLevel + "')");

			}

			if (result != null && !(result.equals(""))) {
				sql.append(" and result='" + result + "'");

			}

			resultList = configDbOperate.searchMaps(sql.toString());

		}

		return resultList;

	}

	/**
	 * 返回失败分布
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getErrCaseDivide() throws Exception {

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		String sql = "select * from (select t.module as name,count(*) as mum from web_Case_current t where  caselevel <>'debug' and result in ('错误','失败') group by module) order by mum asc";
		String string = "";
		String[] strings = new String[2];

		ArrayList<HashMap> list = configDbOperate.searchMaps(sql);

		int num = 0;
		StringBuffer all = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			string = "";
			string = "['" + string + (String) list.get(i).get("NAME") + "'";
			string = string + "," + (String) list.get(i).get("MUM") + "]";
			System.out.println(string.toString());
			num = num + Integer.parseInt((String) list.get(i).get("MUM"));
			all.append("," + string);
		}

		String sall;
		try {
			sall = all.toString().substring(1);

		} catch (Exception e) {
			// TODO: handle exception
			sall = "['无数据',0]";
		}

		strings[0] = "[" + sall + "]";
		strings[1] = String.valueOf(num);

		return strings;

	}

	/**
	 * 获取超时花费时间
	 * 
	 * @return
	 * @throws Exception
	 */

	public String[] getoverFee() throws Exception {
		String[] overFee = new String[3];
		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		WebConfigDao configInfoDao = new WebConfigDao();
		int value = Integer.parseInt((String) configInfoDao.getRunParam("outTime"));
		String sql = "select t.caseid,t.casename,t.fee as FEE from web_case_Current t  where caselevel <>'debug'  and fee >=" + value;

		ArrayList<HashMap> list = configDbOperate.searchMaps(sql);
		if (list == null || list.size() < 1) {
			value = 0;
			sql = " select * from (select t.caseid,t.casename,t.fee as FEE from web_case_Current t  where caselevel <>'debug' and  rownum<50 order by fee desc ) order by fee asc ";
			list = configDbOperate.searchMaps(sql);
		}

		if (list == null || list.size() < 1) {
			value = 0;
			sql = " select '无数据' as caseid,' ' as casename,0 as fee from dual ";
			list = configDbOperate.searchMaps(sql);
		}

		StringBuffer caseString = new StringBuffer("");
		StringBuffer feeString = new StringBuffer("");
		String caseID = "";
		String fee = "";
		for (int i = 0; i < list.size(); i++) {
			HashMap map = list.get(i);

			caseString.append(",'" + map.get("CASEID") + "_" + map.get("CASENAME") + "'");
			feeString.append("," + map.get("FEE"));
		}
		overFee[0] = "[" + caseString.toString().substring(1) + "]";
		overFee[1] = "[" + feeString.toString().substring(1) + "]";
		overFee[2] = Integer.toString(value);

		return overFee;

	}

	public String getCurrentResultRemarks() throws SQLException {

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		String string = null;

		ArrayList<String> stringList = configDbOperate.searchStrings("select t.remarks from web_control t");
		if (stringList != null) {
			string = stringList.get(0);
		}

		return string;

	}

	public String getRunningStatus() throws Exception {

		ConfigDbOperate configDbOperate = new ConfigDbOperate();
		String string;

		try {
			string = configDbOperate.searchStrings("select t.stop from web_control t").get(0);

		} catch (Exception e) {
			// TODO: handle exception
			string = "1";
		}

		return string;
	}

}
