package base.com.zl.utils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;

public class FileUtil {

	
	/**
	 * 锁定并追加文件
	 * @throws Exception 
	 */
	
	public void addFile(String filePath,String addContent) throws Exception{
    	File file = new File(filePath);
    	RandomAccessFile write = null;
    	FileChannel channel = null;
    	FileLock lock = null;

    		write = new RandomAccessFile(file, "rws");
    		channel = write.getChannel();


    		while(true){
    			int i =0;
    			try {
    				lock = channel.lock();//尝试获得文件锁，若文件正在被使用，则一直等待
    				break;
				} catch (Exception e) {
					
					e.printStackTrace();
					i++;
				}
    			if(i==100){
    				Thread.sleep(2000);
    				 i =0;
    			}
    			
    		}


    		String content = readFile(write, addContent);
    		write.seek(0);//替换原有文件内容
    		write.write(content.getBytes());
			lock.release();

			channel.close();
			write.close();

    }

	
	private String readFile(RandomAccessFile reader, String configStr) {
		StringBuffer sb = new StringBuffer();


				String txt = new String();
				try {
					while ((txt = reader.readLine()) != null) {
						System.out.println("循环中");
						sb.append(new String(txt.getBytes("8859_1"),"gbk")+"\n" );



					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				sb.append( new DateUtil().now()+"--"+configStr);
			return sb.toString();

	}
	
	
	
	/**
	 * 锁定文件，获取内容后删除文件内容
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public StringBuffer getFileContent(String filePath) throws IOException, InterruptedException{
    	File file = new File(filePath);
    	RandomAccessFile write = null;
    	FileChannel channel = null;
    	FileLock lock = null;

    		write = new RandomAccessFile(file, "rws");
    		channel = write.getChannel();

            
    		while(true){
    			int i =0;
    			try {
    				lock = channel.lock();//尝试获得文件锁，若文件正在被使用，则一直等待
    				break;
				} catch (Exception e) {
					
					e.printStackTrace();
					i++;
				}
    			if(i==100){
    				Thread.sleep(2000);
    				 i =0;
    			}
    			
    		}
    				StringBuffer sb = new StringBuffer();


    				String txt = new String();
    				try {
    					while ((txt = write.readLine()) != null) {
    						
    						sb.append(txt + "\n");

    						System.out.println("循环报错1");

    						
    					}
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
						System.out.println("循环报错2");

    				}

    				channel.close();
    				write.close();
    				//file.delete();
    				try {
        				lock.release();

					} catch (Exception e) {
						// TODO: handle exception
						
					}


			return sb;
	}
	
	
	
	
	
	
	/**
	 * 删除文件夹及下挂的所有文件和文件夹
	 * @param file
	 * @return
	 */
	public boolean delDir(File file){
		

		if (file.isDirectory()) {
            String[] children = file.list();

            for(int i=0; i<children.length; i++) {
                boolean success = delDir(new File(file, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
		return file.delete();
		
	}

    /**
     * 普通追加文件
     * @param path
     * @param content
     * @return
     * @throws IOException
     */
	public boolean addContent(String path, String content) throws IOException {

		File f = new File(path);
		boolean rewrite = f.exists();
		FileWriter bwriter = new FileWriter(f, true);
	
		if (rewrite) {

			bwriter.append(content + "\n");
			bwriter.flush();
			// sBufferWriter.close();
			bwriter.close();

		} else {
			f.createNewFile();
			bwriter.append(content + "\n");
			bwriter.flush();
			// sBufferWriter.close();
			bwriter.close();

		}

		return rewrite;

	}
	
	/**
	 * 普通创建文件
	 * @param path
	 * @param content
	 * @throws IOException
	 */
	public void createFile(String path, String content) throws IOException {
        this.deleteFile(path);
		File f = new File(path);
		FileWriter bwriter = new FileWriter(f, true);
	

			f.createNewFile();
			bwriter.append(content + "\n");
			bwriter.flush();
			// sBufferWriter.close();
			bwriter.close();

		


	}	
	
	
	

	/**
	 * 获取文件内容 
	 * @param path
	 * @param charsetname
	 * @param line 0 全量   X某行
	 * @return
	 * @throws IOException
	 */
	public String getContent(String path, String charsetname,int line)
			throws IOException {
		String string = null;
		StringBuffer stringBuffer = new StringBuffer();
		InputStream inputStream = new FileInputStream(path);
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, charsetname);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		if(line==0){
			while ((string = bufferedReader.readLine()) != null) {
				stringBuffer.append(string + "\n");
			}
		}else{
			int num=0;
			while ((string = bufferedReader.readLine()) != null) {

				if(num==line){
					stringBuffer.append(string + "\n");
                     break;
				}
				num++;
			}
			
			
		}

		string = stringBuffer.toString();

		inputStream.close();
		inputStreamReader.close();
		bufferedReader.close();

		return string;
	}

	public Properties getProperties(String filePath) {

		File f = new File(filePath);
		try {

			InputStream in = new FileInputStream(f);
			Properties props = new Properties();
			props.load(in);
			in.close();
			return props;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;

		}

	}
	
 /**
  * 获取文件夹下的所有文件
  * @param fileDirPath
  * @return
  */
	    public  List<File> getFileInDir(String fileDirPath) {  
	        List<File> fileList = new ArrayList<File>();  
	        File file = new File(fileDirPath);  
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
	                this.getFileInDir(f.getAbsolutePath());  
	            }  
	        }  
	        
	        return fileList;

	    }  
	  

	
    /**
     * 获取某文件夹下所有的文件名
     * @param fileDirPath
     * @return
     */
	public ArrayList<String> getAllFileName(String fileDirPath){
		ArrayList<String> list = new ArrayList<String>();
		try{
			List<File> fileList = this.getFileInDir(fileDirPath);
			String fileName="";
			for(File file : fileList){
				fileName = file.getName();
				//System.out.println(fileName);
				list.add(fileName);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}

		return list;
		
	}
	public void deleteFile(String filePath) {

		File file = new File(filePath);
		if (file.exists()) {
			file.delete();

		}

	}

	
	
/*//***********************************excel处理

*//**
 * 获取xls格式文件的第N个sheet
 * @param xlsFilePath
 * @param index
 * @return
 * @throws BiffException
 * @throws IOException
 *//*
	public Sheet getXlsSheet(String xlsFilePath,int index) throws BiffException, IOException{
		Workbook allcasebook = Workbook.getWorkbook(new File(xlsFilePath));
		return  allcasebook.getSheet(index);
	}
	*//**
	 * 获取xlsx格式文件的第N个sheet
	 * @param xlsFilePath
	 * @param index
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 *//*
	public XSSFSheet getXlsxSheet(String xlsFilePath,int index) throws FileNotFoundException, IOException{
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(xlsFilePath)));
        return  wb.getSheetAt(index);	
        
	}
	*//**
	 * 获取xls格式表格的第t个 sheet
	 * @param book
	 * @param t
	 * @return
	 * @throws Exception
	 *//*
	public  Sheet getExcel(Workbook book, int t) throws Exception {

		Sheet sheet;
		sheet = book.getSheet(t - 1);

		book.close();

		return sheet;

	}*/
	
/*	*//**
	 * 获取xlsx文件单元格的值
	 * @param XSSFSheet
	 * @param xid
	 * @param yid
	 * @return
	 *//*	
	public String getContentById(XSSFSheet sheet,int xid ,int yid){
		
		return sheet.getRow(xid).getCell(yid).toString();
		
	}
	*//**
	 * 获取xls文件单元格的值
	 * @param sheet
	 * @param xid
	 * @param yid
	 * @return
	 *//*
	public String getContentById(Sheet sheet, int xid, int yid) {

		return sheet.getCell(xid, yid).getContents();

	}*/

	public HashMap getEnvironmentConfig(String name)
			throws ParserConfigurationException, SAXException, IOException {
		String path = System.getProperty("catalina.home");
		if(path!=null){
			 path = path+File.separator+"webapps"+File.separator+"webcrm"+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+"test"+File.separator+"config"+File.separator+"envConfig.xml";

		}else{
			 path = System.getProperty("user.dir")+File.separator+"html"+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+"test"+File.separator+"config"+File.separator+"envConfig.xml";

		}
		System.out.println("路径："+path);
		
		
	
		//String path = "."+File.separator+"envConfig.xml";
		File f = new File(path);
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		dbf = DocumentBuilderFactory.newInstance();
		db = dbf.newDocumentBuilder();
		Document dt = db.parse(f);
		Element element = null;
		element = dt.getDocumentElement();
		HashMap map = new HashMap();
		NodeList FatherLists = element.getChildNodes();
		for (int i = 0; i < FatherLists.getLength(); i++) {

			Node Fathernode = FatherLists.item(i);
			if ("Version".equals(Fathernode.getNodeName())) {
				if (name.equals(Fathernode.getAttributes().getNamedItem("name")
						.getNodeValue())) {
					NodeList childDetail = Fathernode.getChildNodes();
					for (int j = 0; j < childDetail.getLength(); j++) {
						Node childnodechild = childDetail.item(j);
						if (childnodechild instanceof Element) {
							map.put(childnodechild.getNodeName(),
									childnodechild.getTextContent());
						}
					}
				}
			}
		}
		return map;
	}

	

    
	public static void main(String[] s){
		FileUtil fileUtil = new FileUtil();
		//fileUtil.compilerJavaFile("");
		
		
	}
	
}
