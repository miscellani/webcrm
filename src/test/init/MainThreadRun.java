package test.init;

import java.util.HashMap;

import test.web.WebInit;



public class MainThreadRun implements Runnable{
	
	
	private HashMap<String, String> map;
	
 
	public MainThreadRun(HashMap<String, String> map){
		this.map =map;
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			new WebInit().running(map,"","", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}