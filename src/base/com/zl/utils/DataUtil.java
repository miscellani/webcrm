package base.com.zl.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import base.com.zl.log.Log;

public class DataUtil {


	/**
	 * 将string 转换为map   a=1,b=2,c=3 ->
	 * @param s
	 * @return
	 */
	public HashMap<String, String> stringToMap(String s){
		String[] strings = s.split("\\|");
		HashMap<String, String> map = new HashMap<>();
		for(String ss:strings){
			String[] sss=ss.split("=");
			if(sss.length<2){
				map.put(sss[0], " ");

			}else{
				map.put(sss[0], sss[1]);

			}
			
		}
		System.out.println(map);
		return map;
		
	}
	
	public static void main(String[] s){
		DataUtil dataUtil = new DataUtil();
		//dataUtil.stringToMap("ancas=12|abc=|nnn=9890");
		  // if(1==2){
			//String bdc="456";
		String aaa= " select t.access_num from uaser.disns where t.access_num in ( select t.aadd  from  userdd.ssdds)";
		System.out.println(dataUtil.patternText(aaa, "select (.*?)from"));
		
	}
	public long strToLong(String str) {

		return Long.parseLong(str);

	}

	public String longToStr(long l) {
		
		return Long.toString(l);

	}
	
	

	public String intToStr(int t){
		
		return Integer.toString(t);
	}
	
	public int strToInt(String s){
		
		return Integer.parseInt(s);
	}
	
	

	public String doubleToStr(double d){
		
		return Double.toString(d);
		
	}

	public double strToDouble(String s){
		
		return Double.parseDouble(s);
	}
	
	

	public long intToLong(int t){
		
		return Long.valueOf(t);
		
	}

	
	
	

	public String percent(int y, int z) {
		String baifenbi = "";
		double baiy = y * 1.0;
		double baiz = z * 1.0;
		double fen = baiy / baiz;
		DecimalFormat df1 = new DecimalFormat("##.00%"); // ##.00%
		baifenbi = df1.format(fen);
		System.out.println(baifenbi);
		return baifenbi;
	}

	
	
    /**
    * 把原始字符串分割成指定长度的字符串列表
    * 
    * @param inputString
    *            原始字符串
    * @param length
    *            指定长度
    * @return
    */
   public  List<String> getStrList(String inputString, int length) {
       int size = inputString.length() / length;
       if (inputString.length() % length != 0) {
           size += 1;
       }
       List<String> list = new ArrayList<String>();
       
       for (int index = 0; index < size; index++) {
           String childStr = this.substring(inputString, index * length,
                   (index + 1) * length);
           list.add(childStr);
       }       
      
       return  list;
   }


   /**
    * 分割字符串，如果开始位置大于字符串长度，返回空
    * 
    * @param str
    *            原始字符串
    * @param f
    *            开始位置
    * @param t
    *            结束位置
    * @return
    */
   private  String substring(String str, int f, int t) {
       if (f > str.length())
           return null;
       if (t > str.length()) {
           return str.substring(f, str.length());
       } else {
           return str.substring(f, t);
       }
   }
   
  
   
   
   
   
   
	public  String replaceBlank(String str) {     
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");       
		Matcher m = p.matcher(str);    
		String after = m.replaceAll("");     
		return after;
		}
	
	/**
	 * 匹配一个
	 * @param content
	 * @param pattern
	 * @return
	 */
	public String patternText(String content,String pattern){
		Pattern p= Pattern.compile(pattern);
		Matcher m = p.matcher(content);  
		String stringr="";
		if(m.find()){
			//System.out.println(m.groupCount());

			// System.out.println(m.group(0));
			 stringr =m.group(1);

		}

		return stringr;
	}
	/**
	 * 匹配多个
	 * @param content
	 * @param pattern
	 * @return
	 */
	public ArrayList<String> patternTexts(String content,String pattern){
		Pattern p= Pattern.compile(pattern);
		Matcher m = p.matcher(content);  
		ArrayList<String> stringr = new ArrayList<String>(); 
		if(m.find()){

			int num= m.groupCount();
			for(int i=0;i<num;i++){
				stringr.add(m.group(i+1));
			}
			 
		}

		return stringr;
	}
	
	

public  int appearNumber(String srcText, String findText) {
    int count = 0;
    Pattern p = Pattern.compile(findText);
    Matcher m = p.matcher(srcText);
    while (m.find()) {
        count++;
    }
    return count;
}


/**
 * 返回异常信息
 * @param t
 * @return
 */
public String getTrace(Throwable t) {
	StringWriter stringWriter = new StringWriter();
	PrintWriter writer = new PrintWriter(stringWriter);
	t.printStackTrace(writer);
	StringBuffer buffer = stringWriter.getBuffer();
	return buffer.toString();
}

/**
 * 获取随机数
 * @return
 */
public String getrandom(String  numString){ 
	int num = Integer.parseInt(numString);
	int a[] = new int[num];
    StringBuffer strings = new StringBuffer();
    for(int i=0;i<a.length;i ++) {

        a[i] = (int)(10*(Math.random()));

        strings.append(a[i]);
    }
    return strings.toString();
    
}


/**
 * 返回布尔值
 * @param b
 * @return
 */
public boolean getboolean(String b){
	if(b.equals("true")){
            return true;    		
	}
	return false;
}


/**
 * 返回init 
 * @param b
 * @return
 */
public int getint(String i){
        return  Integer.parseInt(i);
}

/**
 * 传入int返回对应的字符串
 * @param b
 * @return
 */
public String getstring(int stringinit){
	
	return   Integer.toString(stringinit)  ;
}


/**
 * 截取字符串， 从from 位,到to 位
 * @param s
 * @param from
 * @param to
 * @return
 */
public String substring(String s,String from,String to){
	int froms = Integer.parseInt(from);
	int tos = Integer.parseInt(to);
     
	s =  s.substring(froms-1, tos);
	
	return s;
	
}

/**
 * 设置日志输入，用于调试作用
 * @param log
 */
public void setlog(String log){
	
	Log.info(log);
}

/*	public static void main(String[] s ){
		DataUtil dat= new DataUtil();
		String content="select num from so1.ins_user_region where bill_id='${normal}' and user_id='${user_id}'";
		//System.out.println(dat.patternText(content, "\\$\\{([^\\$]*)\\}"));
		//System.out.println(dat.patternTexts(content, "\\$\\{([^$]*)\\}.*\\$\\{([^$]*)\\}.*"));
		//System.out.println(dat.appearNumber(content, "\\$\\{"));
		String ss =dat.patternText("fahsdfao${_abc(1,5)}sdfhais12312312312${_ddd(3,6)}jd", "\\$\\{([^$]*)\\}");
		System.out.println(ss);
		
		int a[] = new int[20];

	      for(int i=0;i<a.length;i ++) {

	          a[i] = (int)(10*(Math.random()));

	          System.out.print(a[i]);

	      }

		DataUtil dat= new DataUtil();
		String string="a=1,b=2,c=3";
		HashMap<String, String> map = dat.stringToMap(string);
		System.out.println(map);
		
		//System.out.println(dat.substring(string, "1", "2"));
		
		//  "\\}((?!\\}).*)"
		String ss ="+111sd+222ff+333+232222+";
		System.out.println(dat.patternTexts(ss, "\\+((?!\\+).*)"));
	}*/
	
	
	
	


}
