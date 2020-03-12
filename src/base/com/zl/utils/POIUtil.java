package base.com.zl.utils;

import java.io.IOException;

/*import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
*/
/**
 * Created by wdnnccey on 17/2/27.
 * <p>
 * <p>
 * 2017年02月27日21:27:26，
 *
 */
public class POIUtil {
	/* private static List<File> test(String fileDir) {  
	        List<File> fileList = new ArrayList<File>();  
	        File file = new File(fileDir);  
	        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹  
	        if (files == null) {// 如果目录为空，直接退出  
	            return null;  
	        }  
	        // 遍历，目录下的所有文件  
	        for (File f : files) {  
	            if (f.isFile()) {  
	                fileList.add(f);  
	            } else if (f.isDirectory()) {  
	                System.out.println(f.getAbsolutePath());  
	                test(f.getAbsolutePath());  
	            }  
	        }  
	        
	        return fileList;

	    }  */
	 
	 /*public static void main(String[] args) throws IOException {
    	
    }
    	
    	
    	  
    	List<File> lists= test("C://zll//"); 
        FileUtil fileUtil = new FileUtil();
        for (File f1 : lists) {  
           // System.out.println(f1.getName());  
            String fileNameString=f1.getName();
            File excelFile = new File("C://zll//"+fileNameString); //替换你文档地址
             if(!fileNameString.contains("xlsx")){
            	 continue;
            	 
             }
            // System.out.println(fileNameString);
            XSSFWorkbook wb = null;
            try {
                wb = new XSSFWorkbook(new FileInputStream(excelFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            int numberOfSheets = wb.getNumberOfSheets();
            XSSFSheet sheet = wb.getSheetAt(0);
            String caseName="";
            //String newCaseName="";
            String lastCaseName="";
            int caseNum=0;
            for (int i=1;i<sheet.getLastRowNum()+1;i++) {
            	String newCaseName="";
                
            	try {
                    newCaseName =sheet.getRow(i).getCell(3).toString();  

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(fileNameString);
				}
                    


            	if(newCaseName.equals(lastCaseName)||lastCaseName.equals("")){
            		caseNum++;
            		lastCaseName=newCaseName;
            	}else{
            		System.out.println(lastCaseName+","+caseNum);
            		//fileUtil.saveContent("c:\\zll\\save.txt", lastCaseName+","+caseNum);
            		caseNum=1;
            		lastCaseName=newCaseName;
            	}
            	
            }
        	System.out.println(lastCaseName+","+caseNum);
    		//fileUtil.saveContent("c:\\zll\\save.txt", lastCaseName+","+caseNum);
            
        }  
    

        
    }*/
}