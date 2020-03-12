package test.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import base.com.zl.log.Log;
import base.com.zl.utils.FileUtil;
import test.config.dao.WebConfigDao;
import test.web.cases.bean.WebCaseBean;
import test.web.cases.dao.WebCaseExecuteDao;

 
public class Sendmail {
	
 
    public static void main(String[] s) throws Exception{
    	
    	

		
		////判断发送邮件
    	WebConfigDao webConfigDao = new WebConfigDao();
		String mail = webConfigDao.getRunParam("mail");
		String[] mails = mail.split("\\|");
		if(mails[0].equals("0")){
			return;				
		}
		
		////获取发送人信息
		HashMap configMap = new FileUtil().getEnvironmentConfig("config");
		String mailSend = (String) configMap.get("mail");
		String[] mailSends = mailSend.split("\\|");
		String mailSendAdd = mailSends[0];
		String mailSendUser = mailSends[1];
		String mailSendPass = mailSends[2];
		//获取收件人和抄送人

		String mailSendTo ;
		String mailSendCC ;
		
		mailSendTo = mails[1];
        ArrayList<String> sendToList = new ArrayList<>();
        if((mailSendTo!=null)||(!mailSendTo.isEmpty())){         
		String[] mailSendTos = mailSendTo.split("\\;");
        for(String to:mailSendTos){            	
        	sendToList.add(to);
        }
        }

        mailSendCC =  mails[2];
        ArrayList<String> sendCCList = new ArrayList<>();
        if((mailSendCC!=null)||(!mailSendCC.isEmpty())){
		  String[] mailSendCCs = mailSendCC.split("\\;");
          for(String cc:mailSendCCs){            	
        	 sendCCList.add(cc);
          }
          }

        
        //获取邮件主题
        String subject ="";
        
        //获取正文
        String mailContent="";
        
        //发送邮件
        Sendmail sendmail = new Sendmail();
        
        
        HashMap<String, String> map = new HashMap<>();
		map.put("filterType", "filter");
		map.put("menuName", "个人停机");
		map.put("caseName", "停机校");
		map.put("module", "so");
		//map.put("tester", "");
		map.put("caseLevel", "1");
		map.put("result", "未执行");
        sendmail.sendSimpleMail(map,mailSend, sendToList, sendCCList, subject, mailContent);
        
        
    }
    
    
    
    
    /**
     * 创建一封简单的邮件
     * @param fromUser  发送人信息 : zhanglin@asiainfo.com;zhanglin;4rfv$RFV
     * @param toUser  接受人邮件: list
     * @param CCUser  抄送人邮件:list
     * @param subject  主题
     * @param content  正文
     * @throws Exception
     */
    public void  sendSimpleMail(HashMap<String, String> map,String fromUser,List<String> toUser,List<String> CCUser,String subject,String content )
            throws Exception {
    	
    	
    	

        Properties prop = new Properties();
        prop.setProperty("mail.host", "mail.asiainfo.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、连上邮件服务器
        String[] fromUsers = fromUser.split("\\|");
        ts.connect("mail.asiainfo.com", fromUsers[1], fromUsers[2]);
        //4、创建邮件
        //Message message = createMixedMail(session); 
        
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(fromUsers[0]));
        
        
        //指明邮件的收件人

/*        for(String user :toUser){
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user));
            sendTo[i] = new InternetAddress(recipientCCList.get(i), "", "UTF-8");
        }*/

        if (toUser.size() > 0) {
            InternetAddress[] sendTo = new InternetAddress[toUser.size()];
            for (int i = 0; i < sendTo.length; i++) {
                sendTo[i] = new InternetAddress(toUser.get(i), "", "UTF-8");
            }
            message.addRecipients(MimeMessage.RecipientType.TO, sendTo);
      
        }
        //指明邮件的抄送人
            if (CCUser.size() > 0) {
                InternetAddress[] sendCC = new InternetAddress[CCUser.size()];
                for (int i = 0; i < sendCC.length; i++) {
                	sendCC[i] = new InternetAddress(CCUser.get(i), "", "UTF-8");
                }
                message.addRecipients(MimeMessage.RecipientType.CC, sendCC);
            }
        

        message.setSubject(subject);
        //邮件的文本内容
        
            //获取汇总结果
		    WebCaseExecuteDao caseInfoExecuteDao = new WebCaseExecuteDao();
			int allNum=0;
			//请求结果数据库求值
			int idleNum=0;
			int successNum=0;
			int failNum=0;

			//最终执行结果数
			int correctNum=0;
			int wrongNum=0;

            ArrayList<HashMap> sumResult = caseInfoExecuteDao.getPartyExecuteResult(map);
            if( sumResult!=null&& (sumResult.size()>0) ){
		    allNum = Integer.parseInt((String) sumResult.get(0).get("总数")) ;
		    idleNum= Integer.parseInt((String) sumResult.get(0).get("未执行")) ;
		    successNum= Integer.parseInt((String) sumResult.get(0).get("成功")) ;
		    failNum= Integer.parseInt((String) sumResult.get(0).get("失败")) ;
		    correctNum= Integer.parseInt((String) sumResult.get(0).get("正确"));
		    wrongNum= Integer.parseInt((String) sumResult.get(0).get("错误"));
           }

        StringBuffer mailContent = new StringBuffer();
       // mailContent.append("本轮冒烟测试执行总数:"+allNum+"个，未执行"+idleNum+"个，成功"+successNum+"个，失败"+failNum+"个，正确"+correctNum+"个，错误"+wrongNum+"个。\n\n");
        mailContent.append("本轮冒烟测试执行总数:"+allNum+"个");

        if(idleNum!=0){
        	mailContent.append("，未执行"+idleNum+"个");
        }
        if(successNum!=0){
        	mailContent.append("，执行成功"+successNum+"个");
        }
        if(failNum!=0){
        	mailContent.append("，执行失败"+failNum+"个");
        }
        int xtotal = correctNum+wrongNum;
        mailContent.append("，执行完成"+xtotal+"个");  
        
        mailContent.append("(其中通过"+correctNum+"个，错误"+wrongNum+"个)");  

        
        
        
/*        if(correctNum!=0){
        	mailContent.append("，正确"+correctNum+"个");
        }
        if(wrongNum!=0){
        	mailContent.append("，错误"+wrongNum+"个 ");
        }*/
        
        mailContent.append(content+"\n\n\n");

        mailContent.append("<p><p>");
        
        
        WebCaseExecuteDao webCaseExecuteDao = new WebCaseExecuteDao();
        ArrayList<WebCaseBean> caselist = (ArrayList<WebCaseBean>) webCaseExecuteDao.getCaseInfo(map.get("module"), map.get("menuName"), map.get("caseName"), map.get("tester"), map.get("caseLevel"),map.get("result"));
        
        mailContent.append("<html><body><table width=\"800\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class=\"query\">");
        mailContent.append("<tr><td align=\"center\">测试编码</td><td align=\"center\">业务名</td><td align=\"center\">案例描述</td><td width=\"70\" align=\"center\">运行结果</td><td width=\"70\" align=\"center\">测试人员</td></tr>");
        for(WebCaseBean caseBean:caselist){
        	String line ="";
    		line ="<td align=\"center\">"+caseBean.getCaseId()+"</td><td align=\"center\">"+caseBean.getMenuName()+"</td><td align=\"center\">"+caseBean.getCaseName()+"</td><td width=\"70\" align=\"center\">"+caseBean.getResult()+"</td><td width=\"70\" align=\"center\">"+caseBean.getTester()+"</td></tr>";
            String result =caseBean.getResult();
        	if(result.equals("未执行")){
        		line ="<tr bgcolor=\"#FFFFFF\">"+line;
        	}else if(result.equals("成功")){
        		line ="<tr bgcolor=\"#FFFFFF\">"+line;
        	}else if(result.equals("失败")){
        		line ="<tr bgcolor=\"#8B0000\">"+line;
        	}else if(result.equals("正确")){
        		line ="<tr bgcolor=\"#FFFFFF\">"+line;
        	}else if(result.equals("错误")){
        		line ="<tr bgcolor=\"#FF6347\">"+line;
        	}
        	mailContent.append(line);
        }
        mailContent.append("</table></body></html>");
        
/*        mailContent.append("<html><body><table width=\"300\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class=\"query\">"
        		+ "<tr><td align=\"center\">测试编码</td><td align=\"center\">测试案例名</td><td width=\"70\" align=\"center\">运行结果</td><td width=\"70\" align=\"center\">测试人员</td></tr>"
        		+ "<tr><td align=\"center\">测试开户流程</td><td width=\"70\" align=\"center\">失败</td><td width=\"70\" align=\"center\">zhanglin</td></tr>"
        		+ "</table></body></html>");*/
        
        message.setContent(mailContent.toString(), "text/html;charset=UTF-8");
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
		Log.info("冒烟报告邮件已发送--请查收邮件\n");

    }



/*    *//**
    * @Method: createMixedMail
    * @Description: 生成一封带附件和带图片的邮件
    * @Anthor:孤傲苍狼
    *
    * @param session
    * @return
    * @throws Exception
    *//* 
    public static MimeMessage createMixedMail(Session session) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        
        //设置邮件的基本信息
        message.setFrom(new InternetAddress("zhanglin@asiainfo.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("zhanglin@asiainfo.com"));
        message.setSubject("带附件和带图片的的邮件");
        
        //正文
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("测试发送","text/html;charset=UTF-8");
        
       // text.setContent("xxx这是女的xxxx<br/><img src='cid:aaa.jpg'>","text/html;charset=UTF-8");

        //图片
        MimeBodyPart image = new MimeBodyPart();
        image.setDataHandler(new DataHandler(new FileDataSource("src\\3.jpg")));
        image.setContentID("aaa.jpg");
        
        //附件1
        MimeBodyPart attach = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("src\\4.zip"));
        attach.setDataHandler(dh);
        attach.setFileName(dh.getName());
        
        //附件2
        MimeBodyPart attach2 = new MimeBodyPart();
        DataHandler dh2 = new DataHandler(new FileDataSource("src\\波子.zip"));
        attach2.setDataHandler(dh2);
        attach2.setFileName(MimeUtility.encodeText(dh2.getName()));
        
        //描述关系:正文和图片
        MimeMultipart mp1 = new MimeMultipart();
        mp1.addBodyPart(text);
        mp1.addBodyPart(image);
        mp1.setSubType("related");
        
        //描述关系:正文和附件
        MimeMultipart mp2 = new MimeMultipart();
        mp2.addBodyPart(attach);
        mp2.addBodyPart(attach2);
        
        //代表正文的bodypart
        MimeBodyPart content = new MimeBodyPart();
        content.setContent(mp1);
        mp2.addBodyPart(content);
        mp2.setSubType("mixed");
        
        message.setContent(mp2);
        message.saveChanges();
        
        message.writeTo(new FileOutputStream("E:\\MixedMail.eml"));
        //返回创建好的的邮件
        return message;
    }*/
    
    

}