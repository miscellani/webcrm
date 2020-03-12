package base.com.zl.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import test.web.WebInit;

public class OrlDBUtils {

	private Connection conn;
	private Statement stmt = null;
	private ResultSet results = null;



	//private final static OrlDBUtils orldbutils = new OrlDBUtils();

/*	private OrlDBUtils() {	

		try {
			this.getConnction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

/*	public static OrlDBUtils getInstance() throws Exception {

		return orldbutils;

	}*/

	private void unConnction() {

		
		try {
			
			
			if (!(this.results == null)) {

				this.results.close();
			}
			if (!(this.stmt == null)) {

				this.stmt.close();
			}

			if (!(this.conn.isClosed())) {
				this.conn.close();

			}
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	private  void getConnction() throws Exception {

/*		
		 if(Init.dbUrl.equals("")){
			 
				HashMap enivMap = new FileUtil().getEnvironmentConfig(ConfigConst.version);
				

				Init.dbUrl  = (String) enivMap.get("dbUrl");
				Init.baseUser = (String) enivMap.get("dbUser");
				Init.basePass = (String) enivMap.get("dbPass");
				Init.region = (String) enivMap.get("region");
			 

			 
		 }*/
		 Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		conn = DriverManager.getConnection("jdbc:oracle:thin:@10.177.216.62:1521:nctestdb","qry","qry");
	   //conn = DriverManager.getConnection("jdbc:oracle:thin:@10.177.216.61:1521:nclinkdb","base","Abcd123");

	}


	
	private boolean insertData(String tablename, Object obj)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, Exception, Exception {

		String tablenamequan = tablename;

		if (tablename.indexOf(".") > 0) {
			String[] strtname = tablename.split("\\.");
			tablename = strtname[1];
		}

		String tablenametemp = "";

		if (tablename.indexOf("_") > 0) {
			String[] tablenamesplit = tablename.split("_");
			for (int i = 0; i < tablenamesplit.length; i++) {
				tablenamesplit[i] = tablenamesplit[i].substring(0, 1)
						.toUpperCase()
						+ tablenamesplit[i].substring(1).toLowerCase();
				tablenametemp = tablenametemp + tablenamesplit[i];
			}

		} else {

			tablenametemp = tablename.substring(0, 1).toUpperCase()
					+ tablename.substring(1).toLowerCase();
		}

		String tablenamebean = tablenametemp + "Bean";

		Class<?> tableclass = Class.forName("bean." + tablenamebean);
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
		sql = "insert into " + tablenamequan + " values ";
		sql = sql.replace("values", "(" + fieldnameresult + ") values");
		sql = sql.replace("values", " values(" + fieldvalueresult + ")");


		PreparedStatement ps = conn.prepareStatement(sql);
		ps.execute();


		return true;

	}


	private boolean insertData(String sql) throws SQLException {

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.execute();
		return true;

	}


	private boolean deleteData(String tablename, String condition)
			throws Exception {
		String sql = "delete from " + tablename + " where " + condition;

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.execute();

		return true;
	}


	private boolean deleteData(String sql) throws Exception {

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.execute();

		return true;
	}



	private boolean update(String tablename, String condition, String result)
			throws SQLException {
		String sql = "update  set  where ";
		sql = sql.replace("where ", "where " + condition);
		sql = sql.replace("set ", "set " + result);
		sql = sql.replace("update ", "update " + tablename);
		stmt = (Statement) conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		((java.sql.Statement) stmt).executeUpdate(sql);
		return true;

	}


	private boolean update(String sql) throws SQLException {

		stmt = (Statement) conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		((java.sql.Statement) stmt).executeUpdate(sql);

		return true;

	}
	
	private boolean bpUpdate(String sql){
		stmt = null;
		String[] strsqlStrings = sql.split(";");
	       try {
	    	   stmt = conn.createStatement();
	            for (int i = 0; i < strsqlStrings.length; i++) {
	            	stmt.addBatch(strsqlStrings[i]);// 将所有的SQL语句添加到Statement中
	            }
	            // 一次执行多条SQL语句
	             stmt.executeBatch();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return true;
		
		
	}
	
	


	private ArrayList<Object> searchDataBeans(String tablename,
			String condition, String result) throws Exception {
		String sql = "select from where ";
		if (condition.isEmpty()) {
			sql = sql.replace("from where", "from " + tablename + " where 1=1");
		} else {
			sql = sql.replace("from where", "from " + tablename + " where ");
			sql = sql.replace("where", "where " + condition);
		}

		if (result != null) {
			sql = sql.replace("select from", "select " + result + " from");

		} else {
			sql = sql.replace("select from", "select * from");
		}


		Statement stmt = (Statement) conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet results = ((java.sql.Statement) stmt).executeQuery(sql);

		ArrayList<Object> list = new ArrayList<Object>();
		if (tablename.indexOf(".") > 0) {
			String[] strtname = tablename.split("\\.");
			tablename = strtname[1];
		}
		String[] tablenamesplit = tablename.split("_");
		String tablenametemp = "";
		for (int i = 0; i < tablenamesplit.length; i++) {
			tablenamesplit[i] = tablenamesplit[i].substring(0, 1).toUpperCase()
					+ tablenamesplit[i].substring(1).toLowerCase();
			tablenametemp = tablenametemp + tablenamesplit[i];
		}
		String tablenamebean = tablenametemp + "Bean";
		System.out.println(tablenamebean);
		Class<?> tableclass = Class.forName("bean." + tablenamebean);
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

		return list;
	}


	

	private ArrayList<HashMap> searchData(String sql) throws SQLException {

		stmt = (Statement) conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		results = ((java.sql.Statement) stmt).executeQuery(sql);
		int lienum = results.getMetaData().getColumnCount();
		ArrayList<HashMap> arrayList = new ArrayList<HashMap>();	
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


		return arrayList;

	}

	private ArrayList<String> searchDataList(String sql) throws SQLException {

		stmt = (Statement) conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		results = ((java.sql.Statement) stmt).executeQuery(sql);
		int lienum = results.getMetaData().getColumnCount();
		ArrayList<String> arrayList = new ArrayList<String>();	
		
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


		return arrayList;

	}
	
	
	public static void main(String[] s) throws Exception{
		OrlDBUtils orlDBUtils = new OrlDBUtils();
		HttpRequestUtil requestUtil = new HttpRequestUtil();
		for(int i=12;i<100;i++){
			Thread.sleep(5000);
			orlDBUtils.getConnction();
			String sql_number="select res_id from res.res_phone_num_origin_791 t where  res_id like '1340700%' and res_spec_id='10000' and manage_status='2' and reserve_fee='0' and rownum<2";
			String number= orlDBUtils.searchDataList(sql_number).get(0);
			String sql_sim=" select distinct dd.icc_id  from res.res_sim_card_origin_791 dd,    (select start_imsi, end_imsi   from res.res_imsi_hlr   where hlr_code in      (select number_hlr_hflag      from res.res_number_hlr cc,        (select '"+number+"' phone_n from dual) dd     where cc.net_id = substr(dd.phone_n, 0, 3)       and cc.hlr_segment = substr(dd.phone_n, 4, 4)      )  ) cc,    (SELECT start_imsi,end_imsi    FROM res.res_phone_imsi_map   WHERE '"+number+"' >= start_msisdn     and '"+number+"' <= end_msisdn   and rownum<2 ) r where dd.imsi >= cc.start_imsi   and dd.imsi <= cc.end_imsi   AND dd.imsi BETWEEN r.start_imsi AND r.end_imsi   and dd.manage_status = 2   and rownum < 2";
			String sim = orlDBUtils.searchDataList(sql_sim).get(0);

			//218 offerid=211099135397  
			//现场 offerid=211000791503
			//String json="{\"_trace_context\":\"248a3078b4159a65a1e16774.248a3078b4159a65a1e16775.1\",\"Request\":{\"BusiParams\":{\"BusinessId\":\"500000020001\",\"BusinessRemark\":\"选号入网\",\"OrderInvoice\":{\"IsPrintVatSpecialInvoice\":0,\"IsPrintPrestoreInvoice\":0},\"ResSimCard\":{\"IccId\":\""+sim+"\"},\"User\":{\"BillId\":\""+number+"\",\"Password\":\"F472CD90E05DE8\"},\"Customer\":{\"CustNationality\":\"1\",\"CustGroupProvince\":\"2\",\"CustType\":\"1\",\"Credential\":{\"CredentialExpireDate\":\"20991212\",\"CredentialAddress\":\"V0版本批量开户证件地址"+String.valueOf(i)+"\",\"CredentialName\":\"名字"+String.valueOf(i)+"\",\"CredentialInputMode\":\"1\",\"CredentialCode\":\"8787989\",\"CredentialGender\":\"0\",\"CredentialType\":2},\"Contact\":{\"ContactFax\":\"88886666\",\"ContactAddress\":\"客户联系人地址客户联系人地址\",\"ContactName\":\"联系人"+String.valueOf(i)+"\",\"ContactEmail\":\"contact@email.com\",\"ContactPostCode\":654355,\"ContactPhone\":\"18665717801\"},\"CustBonusPoint\":\"100\",\"CustProfession\":\"3\"},\"Assuror\":{\"AssureType\":\"2\",\"AssureMode\":\"1\",\"AssureBusinessType\":\"3\",\"AssureRemark\":\"担保备注\",\"AssureApprover\":\"担保审批人姓名\",\"AssureCustId\":\"888888888889\"},\"OfferList\":[{\"OfferId\":\"211000791503\",\"ProductList\":[]}],\"OrderSettle\":{\"TotalCharges\":15000,\"ChargeDetail\":[{\"PriceItemId\":\"21000013\",\"PriceDetailType\":\"PRICE_PLAN_CASH_PAY\",\"PricePlanId\":\"230000000122\",\"PriceItemValue\":\"15000\"}]},\"OrderPayment\":{\"PaymentStatus\":\"1\"}},\"BusiCode\":\"OI_SubmitGsmServiceAccessOrder\"},\"PubInfo\":{\"InterfaceId\":\"6000\",\"RequestClientPort\":\"10111\",\"InterfaceType\":\"6\",\"OpId\":\"96660067\",\"RequestClientName\":\"crm-web-wt-g1-srv1\",\"VerifyType\":\"0\",\"TransactionId\":\"DB1484554640919\",\"CountyCode\":\"9109\",\"StationId\":\"99999999\",\"OrgId\":\"71011620\",\"extInfo\":{},\"ClientMac\":\"\",\"ClientIP\":\"\",\"TransactionTime\":\"20170116161720\",\"RegionCode\":\"791\",\"RequestClientIp\":\"10.176.248.52\"}}";
			String json= "{\"_trace_context\":\"248a3078b415d5d6ef2b8358.248a3078b415d5d6ef2b8359.1\",\"Request\":{\"BusiParams\":{\"BusinessId\":\"500000020001\",\"BusinessRemark\":\"\",\"Invoice\":{\"IsPrintVatSpecialInvoice\":0,\"IsPrintPrestoreInvoice\":0},\"ResSimCard\":{\"IccId\":\""+sim+"\"},\"User\":{\"BillId\":\""+number+"\",\"Password\":\"BAA7B3889F94AC\"},\"Customer\":{\"CustType\":\"1\",\"Credential\":{\"CredentialExpireDate\":\"20991212\",\"CredentialAddress\":\"自动开户测试地址自动开户测试地址"+String.valueOf(i)+"\",\"CredentialCheckType\":\"4\",\"CredentialName\":\"自动开户"+String.valueOf(i)+"\",\"CredentialInputMode\":\"1\",\"CredentialCode\":\"3274592734"+String.valueOf(i)+"\",\"CredentialGender\":\"0\",\"CredentialType\":\"2\"}},\"OfferList\":[{\"OfferId\":\"211000910383\"},{\"OfferId\":\"212030501300\"},{\"OfferId\":\"212035001900\"},{\"OfferId\":\"212035001400\"},{\"OfferId\":\"212035000100\"},{\"OfferId\":\"212030502701\"},{\"OfferId\":\"212030502301\"},{\"OfferId\":\"212030502901\"},{\"OfferId\":\"212030502801\"},{\"OfferId\":\"212035002500\"},{\"OfferId\":\"212030502601\"},{\"OfferId\":\"212035000200\"}],\"AttrList\":[],\"OrderSettle\":{\"TotalCharges\":18000,\"ChargeDetail\":[{\"FactMoney\":\"10000\",\"PriceItemId\":\"21000013\",\"PriceDetailType\":\"PRICE_PLAN_CASH_PAY\",\"ShouldMoney\":\"10000\",\"ItemNo\":\"0\",\"PriceItemName\":\"预交话费\",\"PricePlanId\":\"230021000013\",\"FeeType\":\"8\",\"DiscountMoney\":null,\"PriceItemValue\":\"10000\"},{\"FactMoney\":\"8000\",\"PriceItemId\":\"23000002\",\"PriceDetailType\":\"PRICE_PLAN_PKG_CARDFEE\",\"ShouldMoney\":\"8000\",\"ItemNo\":\"1\",\"PriceItemName\":\"SIM卡费\",\"PricePlanId\":\"230023000002\",\"FeeType\":\"1\",\"DiscountMoney\":null,\"PriceItemValue\":\"8000\"}]},\"OrderPayment\":{\"PaymentType\":\"0\",\"PaymentStatus\":\"1\"}},\"BusiCode\":\"OI_SubmitGsmServiceAccessOrder\"},\"PubInfo\":{\"InterfaceId\":\"6000\",\"RequestClientPort\":\"10111\",\"InterfaceType\":\"6\",\"OpId\":\"96660010\",\"RequestClientName\":\"crm-web-wt-g1-srv1\",\"VerifyType\":\"0\",\"TransactionId\":\"DB1500511138489\",\"CountyCode\":\"9109\",\"StationId\":\"99999999\",\"OrgId\":\"71011620\",\"extInfo\":{},\"ClientMac\":\"\",\"ClientIP\":\"\",\"TransactionTime\":\"20170720 083858\",\"RegionCode\":\"791\",\"RequestClientIp\":\"10.176.248.52\"}}";
			String prejson="{\"_trace_context\":\"248a3078b415d5d89bbe2922.248a3078b415d5d89bbe2923.1\",\"Request\":{\"BusiParams\":{\"TradeType\":0,\"BillId\":\""+number+"\"},\"BusiCode\":\"OI_PreOccupyPhoneNum\"},\"PubInfo\":{\"InterfaceId\":\"6000\",\"RequestClientPort\":\"10111\",\"InterfaceType\":\"6\",\"OpId\":\"96660010\",\"RequestClientName\":\"crm-web-wt-g1-srv1\",\"VerifyType\":\"0\",\"TransactionId\":\"DB1500512893923\",\"CountyCode\":\"9109\",\"StationId\":\"99999999\",\"OrgId\":\"71011620\",\"extInfo\":{},\"ClientMac\":\"\",\"ClientIP\":\"\",\"TransactionTime\":\"20170720 090813\",\"RegionCode\":\"791\",\"RequestClientIp\":\"10.176.248.52\"}}";
			System.out.println(json);
			System.out.println("第"+i+"个");
			String resString = "wu";
			try {
	/*			  String pse = requestUtil.sendPost("http://10.176.248.55:50001/CRMService", prejson,null);

				 resString= requestUtil.sendPost("http://10.176.248.55:50001/CRMService", json,null);*/

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exce
				if( !e.getMessage().contains("Read timed out")){
					System.exit(0);
				}
			}
			System.out.println(resString);
/*			if(resString.indexOf("0000")>0){
				orlDBUtils.update("update res.res_phone_num_origin_791 t set t.manage_status ='5'where res_id='"+number+"'");
			    orlDBUtils.update("update res.res_sim_card_origin_791 t set t.manage_status='5' where icc_id='"+sim+"'");						
			}*/
			
			orlDBUtils.unConnction();
		}

	}
	



	protected HashMap<String, String> saveDataB(String url) throws Exception {
		return null;/*
		String sqlb = null;
		HashMap<String, String> rmessage = new HashMap<String, String>();
		rmessage.put("message", "导入成功!!");
		rmessage.put("result", "1");
		Workbook book;
		Sheet sheet;
		book = Workbook.getWorkbook(new File(url));
		sheet = book.getSheet(0);

		if (sheet.getRows() < 2) {
			rmessage.put("message", "文件无数据，你逗我？");
			rmessage.put("result", "0");
			book.close();
			return rmessage;
		}

		conn.setAutoCommit(false);
		Statement stmtp = conn.createStatement();
		for (int row = 1; row < sheet.getRows(); row++) {

			stmtp.addBatch(sqlb);

		}
		try {
			stmtp.executeBatch();
		} catch (Exception e) {
			rmessage.put("message", "数据库执行失败!!");
			rmessage.put("result", "0");

		} finally {
			book.close();
			conn.close();
			stmtp.close();
			return rmessage;
		}
	*/}

}
