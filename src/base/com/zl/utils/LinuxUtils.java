package base.com.zl.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;*/


public class LinuxUtils {
	
	public void linkLinux() throws IOException{/*
		
		  

		    String hostname="";
		    String username="";
		    String password="";
	        Connection conn = new Connection(hostname);
	        Session ssh = null;
	            conn.connect();
	            boolean isconn = conn.authenticateWithPassword(username, password);
	            if (!isconn) 
	            {
	                System.out.println("用户名称或者是密码不正确");
	            } 
	            else 
	            {
	                ssh = conn.openSession(); 
	                ssh.execCommand("tailapp.sh|grep 95550012 > test.txt");
                
	                StringBuffer sBuffer = null;
            
	                InputStream is1 = new StreamGobbler(ssh.getStdout());
                    BufferedReader brs1 = new BufferedReader(new InputStreamReader(is1));                  
                		                String line1 = null; 
	                while (null != (line1 = brs1.readLine())){
	                	sBuffer.append(line1+"\n");
	                }      

	                is1.close();
	                brs1.close();

	            ssh.close();
	            conn.close();
	            }	           

	*/}
}
