package test.config;

public class ConfigConst {
	
     //浏览器类型ie fire , chrome
	public static final String browsertype="chrome";
	//工作目录
	public static final String  workspace = "F:\\selenium";
	//测试环境,对应envconfig.xml环境配置文件的name
	public static final String version="run";
    // 1:调试模式 ,0:本地批量模式 
	public static String isDebug = "1";
	//CASE收集类型    all（全部执行）,selected（选择执行）,continue（继续执行未执行用例）,fail(执行不成功用例)
	//public static final String filterType="all";
	
	//远程电脑配置
	public static String remoteIp  = "127.0.0.1:4444";


	

}
