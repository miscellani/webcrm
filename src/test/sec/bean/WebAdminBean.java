package test.sec.bean;

import java.io.Serializable;

public class WebAdminBean  implements Serializable{
	
	private int operatorId;
	
	public int getOperatorId(){
		return this.operatorId;
	}
	public void setOperatorId(int operatorId){
		this.operatorId =operatorId;
	}
	
	private String userAdmin;
	public String getUserAdmin(){
		return this.userAdmin;
	}
	public void setUserAdmin(String userAdmin){
		this.userAdmin =userAdmin;
	}
	
		
	private String userPass;
	public String getUserPass(){
		return this.userPass;
	}
	public void setUserPass(String userPass){
		this.userPass =userPass;
	}
	private String userName;
	public String getUserName(){
		return this.userName;
	}
	public void setUserName(String userName){
		this.userName =userName;
	}
	
	private String userMobile;
	public String getUserMobile(){
		return this.userMobile;
	}
	public void setUserMobile(String userMobile){
		this.userMobile =userMobile;
	}
	
	
	private String userStaff;
	public String getUserStaff(){
		return this.userStaff;
	}
	public void setUserStaff(String userStaff){
		this.userStaff =userStaff;
	}
	
	
	private String userEmail ="默认";
	public String getUserEmail(){
		return this.userEmail;
	}
	public void setUserEmail(String userEmail){
		this.userEmail =userEmail;
	}
	
	private String isUsed;
	public String getIsUsed(){
		return this.isUsed;
	}
	public void setIsUsed(String isUsed){
		this.isUsed =isUsed;
	}
	

	private String ip;
	public String getIp(){
		return this.ip;
	}
	public void setIp(String ip){
		this.ip =ip;
	}

}
