package base.com.zl.log;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import test.web.WebInit;



import base.com.zl.utils.FileUtil;
public class Log {
    
    //初始化一个logger对象
    private static Logger Log= Logger.getLogger(Log.class.getName());
    private static FileUtil fileUtil = new FileUtil();

    
    

    //定义一个静态方法，可以打印自定义的某个测试用例开始执行的日志信息
    public static void startTestCase(String sTestCaseName){
        Log.info("****************测试用例"+sTestCaseName+"执行开始****************");     
    }
    
    //定义静态方法，打印自定义的某个测试用例的结束执行的信息
    public static void endTestCase(String sTestCaseName){
        
        Log.info("****************测试用例"+sTestCaseName+"执行结束****************");        
        Log.info("------------");
        
    }
    //打印自定义的info级别日志信息
    public static void info(String message) {
    	
    	WebInit.putLogQueue(message);
    	
    	

        
    }
    
    public static void warn(String message){
        Log.warn(message);
        
    }
    public static void error(String message){
        Log.error(message);
        
    }
    
    public static void fatal(String message){
        Log.fatal(message);
        
    }
    
    public static void debug(String message){
        Log.debug(message);
        
    }
    
    

    
    
    
    
}