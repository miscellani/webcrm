package base.com.zl.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;


public class HttpRequestUtil {/*

	public String toJsonFormat(String jsonStr) {
		class levelStr {
			public String getLevelStr(int level) {
				StringBuffer levelStr = new StringBuffer();
				for (int levelI = 0; levelI < level; levelI++) {
					levelStr.append("        ");
				}
				return levelStr.toString();
			}
		}
		int level = 0;
		StringBuffer jsonForMatStr = new StringBuffer();
		for (int i = 0; i < jsonStr.length(); i++) {
			char c = jsonStr.charAt(i);
			if (level > 0
					&& '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
				jsonForMatStr.append(new levelStr().getLevelStr(level));
			}
			switch (c) {
			case '{':
			case '[':
				jsonForMatStr.append(c + "\n");
				level++;
				break;
			case ',':
				jsonForMatStr.append(c + "\n");
				break;
			case '}':
			case ']':
				jsonForMatStr.append("\n");
				level--;
				jsonForMatStr.append(new levelStr().getLevelStr(level));
				jsonForMatStr.append(c);
				break;
			default:
				jsonForMatStr.append(c);
				break;
			}
		}
		return jsonForMatStr.toString();
	}

	public static void main(String[] s) {

		
		 * String TresponseXml=
		 * "<IncreOpr></IncreOpr><Status>02</Status></ConsuInfo><UserInfo>123</UserInfo>haisudhfashdf"
		 * ; String RresponseXml=
		 * "{\"IDType\":\"01\",\"Status\":\"02\",\"OprTime\":\"20181022111124\",\"UserInfo\":\"123\",\"BizOrderResult\":\"0000\","
		 * ; String SresponseXml=
		 * "<IncreOpr></IncreOpr><b:Status>02</b:Status></ConsuInfo><b:UserInfo>123</b:UserInfo>haisudhfashdf"
		 * ;
		 
		HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
		// httpRequestUtil.analyzeResponseXml(SresponseXml, "S模块",
		// "Status,UserInfo");
		DbOperate dbOperate = new DbOperate();
		String request = dbOperate.searchStrings(
				" select t.inputbody from sp_case t  where caseid='2007' ")
				.get(0);
		httpRequestUtil.sendPost(request,
				"http://172.16.79.23:18010/cboss/home", null);
	}

	
	 * @description 提交post请求
	 * 
	 * @param path 接口地址
	 * 
	 * @param request 请求报文
	 * 
	 * @return 接口返回信息
	 
	public String sendPost(String request, String path, String type) {
		String response = "";
		try {
			StringBuffer buffer = new StringBuffer();

			URL url = new URL(path);// 访问的地址
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");// 设置访问方式，默认为GET
			conn.setConnectTimeout(10 * 1000);// 设置连接超时时间为10秒
			conn.setReadTimeout(120 * 1000);// 设置读取超时时间为120秒
			// 使用 URL 连接进行输出，则将 DoOutput标志设置为 true
			conn.setDoOutput(true);

			if (type.equals("R模块")) {
				// R
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			} else if (type.equals("T模块")) {
				// T
				conn.setRequestProperty("Connection", "keep-alive");
				conn.setRequestProperty("Content-Type",
						"multipart/form-data; boundary=FB885oclSdK66CptEFam9SbQIGUkx0; charset=UTF-8");
				String pathsString;
				String[] paths = path.split("/");
				path = paths[2];
				conn.setRequestProperty("Host", path);

				String[] requests = new String[2];
				requests = request.split("\\$\\{zl\\}");
				buffer.append("--FB885oclSdK66CptEFam9SbQIGUkx0\r\n");
				buffer.append("Content-Disposition: form-data; name=\"xmlhead\"\r\n");
				buffer.append("Content-Type: text/plain; charset=UTF-8\r\n");
				buffer.append("Content-Transfer-Encoding: 8bit");

				// 提交的数据前要有两个回车换行
				buffer.append("\r\n\r\n");
				buffer.append(requests[0]);
				buffer.append("\r\n\r\n");
				// 第二个参数
				buffer.append("--FB885oclSdK66CptEFam9SbQIGUkx0\r\n");
				buffer.append("Content-Disposition: form-data; name=\"xmlbody\"\r\n");
				buffer.append("Content-Type: text/plain; charset=UTF-8\r\n");
				buffer.append("Content-Transfer-Encoding: 8bit");
				// 提交的数据前要有两个回车换行
				buffer.append("\r\n\r\n");
				buffer.append(requests[1]);
				buffer.append("\r\n\r\n");
				// body结束时 boundary前后各需添加两上横线，最添加添回车换行
				buffer.append("--FB885oclSdK66CptEFam9SbQIGUkx0--\r\n\r\n");
				request = buffer.toString();
			} else if (type.equals("S模块")) {
				conn.setRequestProperty("Content-Type",
						"application/soap+xml;charset=UTF-8");
				conn.setRequestProperty("SOAPAction", "");
				conn.setRequestProperty("User-Agent",
						"Jakarta Commons-HttpClient/3.1");
				conn.setRequestProperty("Host", path);

			} else if (type.equals("星状网")) {
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
				conn.setRequestProperty("Host", path);
				conn.setRequestProperty("User-Agent",
						"User-Agent: Apache-HttpClient/4.2.6 (java 1.5)");
				// request ="xmldata="+request;
			}

			OutputStream outStream;
			byte[] b = request.getBytes("UTF-8");
			conn.setRequestProperty("Content-Length", String.valueOf(b.length));

			try {
				outStream = conn.getOutputStream();// 返回写入到此连接的输出流
				outStream.write(b);// 发送报文
				outStream.close();

			} catch (Exception e) {
				System.out.println("可能存在网络不通！");
				e.printStackTrace();

			}

			System.out.println(conn.getResponseCode());
			System.out.println(conn.getResponseMessage());

			if (conn.getResponseCode() == 200) {
				System.out.println("连接接口成功,读取数据");
				// HTTP服务端返回的编码是UTF-8,故必须设置为UTF-8,保持编码统一,否则会出现中文乱码
				BufferedReader input = new BufferedReader(
						new InputStreamReader(
								(InputStream) conn.getInputStream(), "UTF-8"));
				String lineString;
				while ((lineString = input.readLine()) != null) {
					response = response + lineString;

				}
				input.close();
			}

			conn.disconnect();// 断开连接
			System.out.println("接口返回报文\n" + response);
			// System.out.print(response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("可能存在网络不通！");
		}
		return response;
	}

	public String[] getRequestXml(int caseId, String mainCaseAllParam)
			throws Exception {
		// 循环查询参数并替换到报文中，查询出一个参数值就替换一次参数文本，便于二次查询参数，前台编辑需要注意顺序,如果有查到是参数就替换报文（可能有两个）体和入参值，如果没有，就查数据key

	    ConfigInfoDao configInfoDao = new ConfigInfoDao();

		CaseInfo caseInfo = new CaseInfo();
		CaseInfoExecuteDao caseInfoExecuteDao = new CaseInfoExecuteDao();
		caseInfo = caseInfoExecuteDao.getExecutCaseInfo(caseId);
		String inputHeadOld = caseInfo.getInputhead();
		String inputBodyOld = caseInfo.getInputbody();

		if (inputHeadOld == null) {
			inputHeadOld = "";
		}

		// 先完成报文头和报文体的的系统参数替换
		String sysParamPattern;
		DataUtil dataUtil = new DataUtil();
		sysParamPattern = "\\$\\{(_[^$]*)\\}";

		while (inputHeadOld.contains("${_")) {
			String sysParamKey = dataUtil.patternText(inputHeadOld,
					sysParamPattern);
			String sysParam = configInfoDao.getSysParamByKey(sysParamKey);
			inputHeadOld = inputHeadOld.replace("${" + sysParamKey + "}",
					sysParam);
			inputBodyOld = inputBodyOld.replace("${" + sysParamKey + "}",
					sysParam);
		}
		while (inputBodyOld.contains("${_")) {
			String sysParamKey = dataUtil.patternText(inputBodyOld,
					sysParamPattern);
			String sysParam = configInfoDao.getSysParamByKey(sysParamKey);
			inputBodyOld = inputBodyOld.replace("${" + sysParamKey + "}",
					sysParam);
		}

		
		
		
		String inputParamOld = caseInfo.getInputparam();
		int paramNum =0;
		String[] inputParamOlds = null;
		if(inputParamOld!=null){
			
			inputParamOlds = inputParamOld.split(";");
			 paramNum = inputParamOlds.length;
		}

		String[] inputParamNews = new String[paramNum];

		// 先获取参数值
		for (int i = 0; i < paramNum; i++) {
			String inputParamTemp = inputParamOlds[i];
			String[] inputParamTemps = inputParamTemp.split("=");
			String inputParamLeft = inputParamTemps[0].replace("${", "")
					.replace("}", "");
			
			String inputParamRight = inputParamTemps[1];

			// 先替换最复杂的参数 ,
			// ${billId}|${userID}|${orderID}]=key(${billID1},"123",${billID1},"123")
			// -> 123,123,123,123 (=123|456|876) ,

			if (inputParamRight.indexOf(",") > 0) {
				inputParamLeft = inputParamLeft.replace("${", "").replace("}","");
				String[] inputParamLefts = inputParamLeft.split("\\|");
				String[] inputParamrights;
			    System.out.println(inputParamRight);
				String[] method = inputParamRight.split("\\(");
				String keyString = method[0];
				String keyValueString = "";
				String methodParamString = method[1].replace(")", "");
				inputParamRight =methodParamString.replace("${", "").replace("}","").replace("\"", "").replace(",", "|");
				
				String[] methodParamStrings = methodParamString.split(",");
				for (int m = 0; m < methodParamStrings.length; m++) {

					if (methodParamStrings[m].contains("${")) {
						String ParamRightValue = methodParamStrings[m].replace(
								"${", "").replace("}", "");
						String[] mainCaseAllParams = mainCaseAllParam
								.split(";");
						int mainCaseAllParamNum = mainCaseAllParams.length;
						for (int j = 0; j < mainCaseAllParamNum; j++) {
							String[] inputParamOldstemp = mainCaseAllParams[j]
									.split("=");
							if (inputParamOldstemp[0].equals(ParamRightValue)) {
								ParamRightValue = inputParamOldstemp[1];
								keyValueString = keyValueString
										+ ParamRightValue + ",";
								break;
							}

						}
					} else if (methodParamStrings[m].contains("\"")) {
						String ParamRightValue = methodParamStrings[m].replace(
								"\"", "");
						keyValueString = keyValueString + ParamRightValue + ",";
					}

				}
				keyValueString = keyValueString.substring(0,
						keyValueString.length() - 1);

				// 调用SQL查询接口，不定参数，不定返回
				inputParamRight=configInfoDao.getParamSqlByKey(keyString,
						keyValueString);
				inputParamrights = inputParamRight.split("\\|");

				// 这里要判断是否是不定数的参数：billId|userID|orderID=123|456|876
				String inputParamNew = "";
				for (int k = 0; k < inputParamLefts.length; k++) {
					inputParamNew = inputParamNew + inputParamLefts[k] + "="
							+ inputParamrights[k] + ";";
				}
				inputParamNew = inputParamNew.substring(0,
						inputParamNew.length() - 1);

				inputParamNews[i] = inputParamNew;
				mainCaseAllParam = mainCaseAllParam + ";" + inputParamNew;

			} else if (inputParamRight.indexOf("(${") > 0) {

				inputParamRight = this.replaceParamValueOne(inputParamRight,
						mainCaseAllParam);
				inputParamNews[i] = inputParamLeft + "=" + inputParamRight;
				mainCaseAllParam = mainCaseAllParam + ";" + inputParamNews[i];
			} else if (inputParamRight.contains("${")) {
				inputParamRight = this.replaceParamValueTwo(inputParamRight,
						mainCaseAllParam);
				inputParamNews[i] = inputParamLeft + "=" + inputParamRight;
				mainCaseAllParam = mainCaseAllParam + ";" + inputParamNews[i];
			} else if (inputParamRight.contains("(\"")) {
				inputParamRight = this.replaceParamValueThree(inputParamRight);
				inputParamNews[i] = inputParamLeft + "=" + inputParamRight;
				mainCaseAllParam = mainCaseAllParam + ";" + inputParamNews[i];
			} else {
				inputParamRight = this.replaceParamValueFour(inputParamRight);
				inputParamNews[i] = inputParamLeft + "=" + inputParamRight;
				mainCaseAllParam = mainCaseAllParam + ";" + inputParamNews[i];
			}

			// **************替换参数值到报文中，复合入参要循环处理

			String[] inputParamLeftsStrings = inputParamLeft.split("\\|");
			String[] inputParamRightsStrings = inputParamRight.split("\\|");
			for(int k=0; k < inputParamLeftsStrings.length;k++){
				inputHeadOld = inputHeadOld.replace("${" + inputParamLeftsStrings[k] + "}",
						inputParamRightsStrings[k]);
				inputBodyOld = inputBodyOld.replace("${" + inputParamLeftsStrings[k] + "}",
						inputParamRightsStrings[k]);
			}


		}


		
		//优化，最后将不需要保存的普通参数再做替换
		
		
		
		
		
		
		inputHeadOld = inputHeadOld.replace("${", "");
		inputHeadOld = inputHeadOld.replace("{", "");
		inputHeadOld = inputHeadOld.replace("}", "");

		inputBodyOld = inputBodyOld.replace("{", "");
		inputBodyOld = inputBodyOld.replace("${", "");
		inputBodyOld = inputBodyOld.replace("}", "");

		String[] strings = new String[4];
		strings[0] = inputHeadOld;
		strings[1] = inputBodyOld;
		strings[2] = Arrays.toString(inputParamNews).replace("[", "")
				.replace("]", "");
		strings[2] = strings[2].replace(",", ";");
		strings[3] = mainCaseAllParam;
		//crmDbOperate.closeConnction();
		return strings;

	}

	*//**
	 * 解析报文，返回重要值和报文段
	 *//*
	public String analyzeResponseXml(String responseXml, String type,
			String outPutParam) {

		outPutParam = outPutParam.replace("${", "").replace("}", "");
		// ;
		String[] outPutParamOld = outPutParam.split(";");
		int num = outPutParamOld.length;
		String[] outputParamNew = new String[num];
		String[] patternString = new String[num];

		DataUtil dataUtil = new DataUtil();

		// 设置匹配格式
		if (type.equals("T模块") || type.equals("星状网")) {

			for (int i = 0; i < num; i++) {

				patternString[i] = "<" + outPutParamOld[i] + ">([^<]*)</"
						+ outPutParamOld[i] + ">";

			}

		} else if (type.equals("R模块")) {

			for (int i = 0; i < num; i++) {

				patternString[i] = "\"" + outPutParamOld[i] + "\":\"([^\"]*)";

			}
		} else if (type.equals("S模块")) {
			for (int i = 0; i < num; i++) {

				patternString[i] = "<b:" + outPutParamOld[i] + ">([^<]*)</b:"
						+ outPutParamOld[i] + ">";

			}
		}
		String outPutParamValue = "";
		// 开始匹配替换
		for (int i = 0; i < num; i++) {
			outPutParamValue = dataUtil.patternText(responseXml,
					patternString[i]);
			outputParamNew[i] = outPutParamOld[i] + "=" + outPutParamValue;
		}

		return Arrays.toString(outputParamNew).replace("[", "")
				.replace("]", "");

	}

	// /有sqlKey有入参,并且是之前的参数 --${user_id}=userid(${bill_id1})
	private String replaceParamValueOne(String inputParamRight,
			String mainCaseAllParam) throws Exception {
	    ConfigInfoDao configInfoDao = new ConfigInfoDao();

		String linkValue = null;

		// 先获取已经存在的参数值
		String[] linkParam = inputParamRight.split("\\(\\$\\{");
		String linkParamLeft = linkParam[0];
		String linkParamRight = linkParam[1].replace("})", "");

		// 通过参数名获取之前得到的参数值 bill_id1=bill_id,user_id=userid(bill_id1)
		// ,
		String[] mainCaseAllParams = mainCaseAllParam.split(";");
		int mainCaseAllParamNum = mainCaseAllParams.length;
		for (int j = 0; j < mainCaseAllParamNum; j++) {
			String[] inputParamOldstemp = mainCaseAllParams[j].split("=");
			if (inputParamOldstemp[0].equals(linkParamRight)) {
				linkValue = inputParamOldstemp[1];
				break;
			}

		}

		inputParamRight = configInfoDao.getParamSqlByKey(linkParamLeft,
				linkValue);
		return inputParamRight;

	}

	// 无SQL，有入参，之前保存的参数作为入参 --${user_id}=${user_id1}
	private String replaceParamValueTwo(String inputParamRight,
			String mainCaseAllParam) throws Exception {

		// 先获取主CASE中保存的出参，入参值
		// ;
		inputParamRight = inputParamRight.replace("${", "").replace("}", "");
		String[] mainCaseAllParams = mainCaseAllParam.split(";");
		int mainCaseAllParamNum = mainCaseAllParams.length;
		for (int j = 0; j < mainCaseAllParamNum; j++) {
			String[] inputParamOldstemp = mainCaseAllParams[j].split("=");
			if (inputParamOldstemp[0].equals(inputParamRight)) {
				inputParamRight = inputParamOldstemp[1];
				break;
			}

		}

		return inputParamRight;
	}

	// 有sqlkey，有入参是常量， --${user_id}=userid("")
	private String replaceParamValueThree(String inputParamRight)
			throws Exception {
	    ConfigInfoDao configInfoDao = new ConfigInfoDao();
		// 先获取已经存在的参数值
		String[] linkParam = inputParamRight.split("\\(");
		String linkParamLeft = linkParam[0];
		String linkParamRight = linkParam[1].replace("\")", "").replace("\"",
				"");
		// 已经得到sqlkey的入参
		inputParamRight = configInfoDao.getParamSqlByKey(linkParamLeft,
				linkParamRight);

		return inputParamRight;

	}

	// 有sqlkey，无入参，直接使用 ${bill_id1}=billid
	private String replaceParamValueFour(String inputParamRight)
			throws Exception {
	    ConfigInfoDao configInfoDao = new ConfigInfoDao();
		inputParamRight = configInfoDao.getParamSqlByKey(inputParamRight);

		return inputParamRight;

	}

*/}
