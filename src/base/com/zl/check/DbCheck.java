package base.com.zl.check;

import java.util.HashMap;

import base.com.zl.db.DbOperate;

public class DbCheck  extends DbOperate{
	
	
	
	//***************************校验
	

/*	
		*//**
		 * 直接设置数据库校验结果
		 * 
		 * @param result
		 * @param checkmessage
		 * @param searchremarks
		 * @throws Exception 
		 * @throws IOException
		 *//*
		public void dBcheck(boolean result, String checkMessage) throws Exception
				{

			String path = null;
				
			if(!("ConfigConst.isDebug".equals("2"))){
		      	if (!result) {
		      		
					WebInit.caseresult = "false";

			   }     	

			}else{
				

				String sql="select t.caseid from sf.autotest_case t where t.caseid=''";
				ArrayList<String> strings = this.searchStrings(sql);
				if(strings.size()==0){				
						//Log.saveLog(caseId,"错误|要校验的CASE未找到："+caseId+"\n");				

	                 return false;				
				}
			    

			    
				if(!result){
					//判断如果CASEID不是当前主CASEID立即更新最终结果				
					if("this.mainCaseId"!=caseId){
						 sql ="update sf.autotest_Case t set failnum=failnum+1,result='fail' where caseid=''";	
						 this.updateData(sql);

					}else{
						//主CASE只更新临时标识
						 sql ="update sf.autotest_Case t set result='fail' where caseid=''";	
						 this.updateData(sql);
					}
					


				}else{

					//判断如果CASEID不是当前主CASEID立即更新最终结果
					if("this.mainCaseId"!=caseId){
						 sql ="update sf.autotest_Case t set  result='success' , successnum=successnum+1,lastsuccess=sysdate where caseid='"+caseId+"'";	
						 this.updateData(sql);	
					}
					
					//成功时主CASE不做任何临时更新

				}
				
					
			}
							

			 //Log.saveLog(caseId,"db校验|"+result + "|"+ checkMessage+"\n");

			 return result;

		}*/
		

		
		

		public HashMap<String, String> checksql(String checkNum,String sql,String remark,HashMap<String, String> resultMap) throws Exception{
			
	        String message  =resultMap.get("message");
	        if(message==null){
	        	message ="";
	        }
	        message= message+"执行校验语句:"+sql+"\n";

				 
			String num=this.searchString(sql);
			if(num.equals(checkNum)){
				 message =message +"正确_"+remark+"\n";
				
			}else{
				 message =message +"错误_"+remark+"\n";
				 resultMap.put("result", "错误");
			}
			 resultMap.put("message", message);

			return resultMap;
			
		}
		

}
