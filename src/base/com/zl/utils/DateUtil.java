package base.com.zl.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
	  
    

    /**
     * 日期格式字符串转为DATE对象
     * @param str
     * @return
     */
	public Date stringToDate(String str){		
		Date date = new Date(); 
		DateFormat sdf = null ;
		String dateformat1 = "yyyy-MM-dd HH:mm:ss";
		String dateformat2 = "yyyy/MM/dd HH:mm:ss";
		
        if(str.indexOf("/")>-1){
             sdf = new SimpleDateFormat(dateformat2);      	
        }else if (str.indexOf("-")>-1) {
             sdf = new SimpleDateFormat(dateformat1);   			
		}
        try {   
            date = sdf.parse(str); 

        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        return date;		
	}
	
    /**
     * DATE对象转为字符串
     * @param dt
     * @return
     */
	public String dateToString(Date dt,String dateSign){
		

		DateFormat sdf = null ;
		String dateformat = "";
		if(dateSign.indexOf("-")>-1){
			 dateformat = "yyyy-MM-dd HH:mm:ss";	

		}else{
			 dateformat = "yyyy/MM/dd HH:mm:ss";

		}
		String dateString ="";
		sdf = new SimpleDateFormat(dateformat);
		try {
		    dateString = sdf.format(dt);

		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return dateString;
	}
	
    /**
     * 时间戳对象转为字符串
     * @param dt
     * @return
     */
	public String timestampToString(Timestamp ts,String dateSign){
		String str = null;
		String dateformat ="";
		if (dateSign.equals("-")){
			dateformat = "yyyy-MM-dd HH:mm:ss";
		}else{
			dateformat = "yyyy/MM/dd HH:mm:ss";
		}		
		DateFormat sdf = new SimpleDateFormat(dateformat);
		try {

			str = sdf.format(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;		
	}

    /**
     * 时间戳对象转为date对象
     * @param ts
     * @return
     */
	public Date timestampToDate(Timestamp ts){
		Date dt = new Date();
		try {
			dt = ts;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return dt;
		
	}
	
    /**
     * date对象转为时间戳对象
     * @param dt
     * @return
     */
    public Timestamp dateToTimestamp(Date dt){    	
    	DateUtil dateChaUtil = new DateUtil();
    	return  dateChaUtil.stringToTimestamp(dateChaUtil.dateToString(dt,"-"));
    	   	
    }
    
	/**
	 * 获取当前日期字符串
	 * @return
	 */
	public String now(){

		String dateformat1 = "yyyy-MM-dd HH:mm:ss";	
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat1);
		return sdf.format(date);

		
	}

	/**
	 * 获取当前日期字符串精确到毫秒
	 * @return
	 */
	public String nows(){

		String dateformat1 = "yyyy-MM-dd HH:mm:ss.SSS";	
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat1);
		return sdf.format(date);

		
	}
	
   /**
    * 当前日期转为数字格式字符串20180412121212
    * @return
    */
	public String nowToNum(){	
		
		String datenum = "";
		String dateformat1 = "yyyy-MM-dd HH:mm:ss";
		
		
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat1);
		String str = sdf.format(date);
		datenum = this.dateStringToNumString(str);
		return datenum;
		
	}
	
    /**
     * 荡起日期转为年月数字格式字符串
     * @return
     */
	public String nowToYearMonth(){
		
		String datenum = "";
		String dateformat1 = "yyyy-MM";
		
		
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat1);
		String str = sdf.format(date);
		String[] strings= str.split("-");
		return strings[0]+strings[1];
		
	}
	
    /**
     * 当前时间转为年数字格式字符串
     * @return
     */
	public String nowToYear(){
		
		String datenum = "";
		String dateformat1 = "yyyy";
		
		
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat1);
		String str = sdf.format(date);
		
		return str;
		
	}
	
    /**
     * 当前日期获取月字符串
     * @return
     */
	public String nowToMonth(){
		
		String datenum = "";
		String dateformat1 = "MM";
		
		
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat1);
		String str = sdf.format(date);


		return str;
	}
	

	
    /**
     * 当前时间获取小时字符串
     * @return
     */
	public String nowToshi(){
		
		String datenum = "";
		String dateformat1 = "yyyy-MM-dd HH:mm:ss";
		
		
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat1);
		String str = sdf.format(date);
		datenum = str.substring(11,13);
		return datenum;
	
	}
	
	
	
	public int nowToMS(){
		Calendar c = Calendar.getInstance();
		int fen=c.get(Calendar.MINUTE);
		int miao=c.get(Calendar.SECOND);
		return fen*60+miao;
	}
	
    /**
     * 数字格式字符转为日期格式字符串
     * @param num
     * @param fromat
     * @return
     */
	public String numToDate(String num,String fromat){
		
		String num1=null;
		String numtemp=null;
		
		if(fromat.equals("-")){
			
			num1= num.substring(0, 4);
			num1=num1+"-";
			numtemp= num.substring(4,6);
			num1=num1+numtemp+"-";
			numtemp= num.substring(6,8);
			num1=num1+numtemp+" ";
			numtemp= num.substring(8,10);
			num1=num1+numtemp+":";
			numtemp= num.substring(10,12);
			num1=num1+numtemp+":";
			numtemp= num.substring(12,14);
			num1=num1+numtemp;
			
		}else if(fromat.equals("/")){
			
			num1= num.substring(0, 4);
			num1=num1+"/";
			numtemp= num.substring(4,6);
			num1=num1+numtemp+"/";
			numtemp= num.substring(6,8);
			num1=num1+numtemp+" ";
			numtemp= num.substring(8,10);
			num1=num1+numtemp+":";
			numtemp= num.substring(10,12);
			num1=num1+numtemp+":";
			numtemp= num.substring(12,14);
			num1=num1+numtemp;
			
		}else{
			
			num1= num.substring(0, 4);
			num1=num1+"-";
			numtemp= num.substring(4,6);
			num1=num1+numtemp+"-";
			numtemp= num.substring(6,8);
			num1=num1+numtemp+" ";
			
		}
		
		
		return num1;
		
	}
	
	
	
/**
 * 时间格式 ,获取时间差
 * @param startDate 20181026235958
 * @param endDate 20181026235958
 * @throws Exception
 */
	public ArrayList<Integer> getFeeTime(String startTime, String endTime) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date start = format.parse(startTime);
		Date end =   format.parse(endTime);
		long between = end.getTime() - start.getTime();
		
		long day = (between / (24 * 60 * 60 * 1000));
		long hour = (between / (60 * 60 * 1000) - day * 24);
		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		ArrayList<Integer> fee =new ArrayList<Integer>();
		fee.add(new Long(day).intValue());
		fee.add(new Long(hour).intValue());
		fee.add(new Long(min).intValue());
		fee.add(new Long(s).intValue());
		fee.add(new Long(ms).intValue());

		System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒" +ms + "豪秒");
        return fee;
	}
	
	
	
	private  Timestamp stringToTimestamp(String str){
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;		
	}
	
	private String dateStringToNumString(String str){
		String datenum = "";
		String[] strings = str.split(" ");

		String datestring = strings[0];
		if(datestring.indexOf("-")>-1){
			String[] datestr = datestring.split("-");
			datenum = datestr[0]+datestr[1]+datestr[2];
			
		}else if(datestring.indexOf("/")>-1){
			String[] datestr = datestring.split("/");
			datenum = datestr[0]+datestr[1]+datestr[2];
			
		}

		String timestring = strings[1];
	    String[] timestr= timestring.split(":");
		datenum = datenum+timestr[0]+timestr[1]+timestr[2];				
		return datenum;
		
	}
	
	
	
	public static void main1(String[] args) throws Throwable {
		
		
		DateUtil dateUtil = new DateUtil();
		String startTime = dateUtil.nows();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date start = format.parse(startTime);
		Thread.sleep(6000);
		String sendTime = dateUtil.nows();

		Date end =   format.parse(sendTime);
		long between = end.getTime() - start.getTime();
		long day = between / (24 * 60 * 60 * 1000);
		long hour = (between / (60 * 60 * 1000) - day * 24);
		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);

		System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒" +ms + "豪秒");

	}

	public static void main(String[] s) throws InterruptedException{

        DateUtil dateUtil = new DateUtil();

       String ss= dateUtil.nowToNum();
       System.out.println(ss.substring(8, 11));
	}
	
	    public void calculateDate(String year,String month){
	    	int nian=Integer.parseInt(year);
        	int yue=Integer.parseInt(month);
        	//获取当前年月
        	DateUtil dateUtil = new DateUtil();
        	String currentnian = dateUtil.nowToYear();
        	String currentyue = dateUtil.nowToMonth();
            if(currentyue.startsWith("0")){
            	currentyue = currentyue.substring(1);
            }
        	
        	//确定设置年
        	if(year.contains("+")){
        		//.先计算出 目标年月                                           
        		  // -----如果是当前年加上 增加的月大于12 ，目标年+1 ,目标月-12
        		year=year.substring(1);
        		nian = Integer.parseInt(currentnian)+Integer.parseInt(year);
                year = Integer.toString(nian);

        	}else if(year.contains("-")){
     		   //-----如果是当前年减去 减去的月小于1  ，目标年-1，目标月+12
        		year=year.substring(1);
        		nian = Integer.parseInt(currentnian)-Integer.parseInt(year);
                year = Integer.toString(nian);
                
        	}
        	
        	//设置月
        	if(month.contains("-")){
        		//.先计算出 目标年月                                           
        		  // -----如果是当前月加上 增加的月大于12 ，目标年+1 ,目标月-12
        		   //-----如果是当前月减去 减去的月小于1  ，目标年-1，目标月+12
        		
        		month = month.substring(1);
        		if(month.startsWith("0")){
        			month=month.substring(1);
        		}
        		if( !(Integer.parseInt(currentyue) -Integer.parseInt(month)>0 )){
        			yue =Integer.parseInt(currentyue)  -Integer.parseInt(month) +12;
        			
        			year= Integer.toString(Integer.parseInt(year)-1);
        		}else{
        			yue =Integer.parseInt(currentyue)  -Integer.parseInt(month) ;

        		}
        		

        	}else if(month.contains("+")){
        		
        		month = month.substring(1);
        		if(month.startsWith("0")){
        			month=month.substring(1);
        		}
        		if( Integer.parseInt(currentyue) +Integer.parseInt(month)>12 ){
        			yue =Integer.parseInt(currentyue)  +Integer.parseInt(month) -12;
        			
        			year= Integer.toString(Integer.parseInt(year)+1);
        		}else{
        			yue =Integer.parseInt(currentyue)  + Integer.parseInt(month) ;

        		}
        		
        	}
        	
        	
        	System.out.println(year + yue);
	    }

}
