package base.com.zl.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PipedReader;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.websocket.Session;

import test.web.WebInit;

import base.com.zl.utils.DataUtil;
import base.com.zl.utils.FileUtil;

/**
 * 新线程
 */
public class LogThread extends Thread {

	private BufferedReader reader;
	private Session session;

	public LogThread(PipedReader pipedReader, Session session) {
		this.reader = new BufferedReader(pipedReader);
		this.session = session;

	}

	/*
	 * @Override public void run() {
	 * 
	 * String line = null; FileUtil fileUtil = new FileUtil();
	 * 
	 * while(true){
	 * 
	 * 
	 * 
	 * try { line =fileUtil.getFileContent(Init.logPath).toString(); line = new
	 * String(line.getBytes("8859_1"),"gbk"); String[] lines = line.split("\n");
	 * 
	 * int num =lines.length;
	 * 
	 * 
	 * 
	 * if(num<4){ for(int i=0;i<num;i++){
	 * session.getBasicRemote().sendText(lines[i] + "<br>"); Thread.sleep(1000);
	 * 
	 * } }else{
	 * 
	 * session.getBasicRemote().sendText(lines[0] + "<br>"); Thread.sleep(1000);
	 * session.getBasicRemote().sendText(lines[1] + "<br>"); Thread.sleep(1000);
	 * for(int i=3;i<num;i++){ session.getBasicRemote().sendText(lines[i] +
	 * "<br>");
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * if(line.contains("测试结束")){ break; }
	 * 
	 * } catch (Exception e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); String meString =new DataUtil().getTrace(e1);
	 * if(meString.contains(" closed session")){ break; }
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

	/*
	 * @Override public void run() { CaseInfoExecuteDao caseInfoExecuteDao = new
	 * CaseInfoExecuteDao();
	 * 
	 * String line =""; while(true){ try { line=Init.logQueue.poll() ;
	 * if(line==null){ if(caseInfoExecuteDao.getRunningStatus().equals("1")){
	 * break; } Thread.sleep(1000);
	 * 
	 * continue; } } catch (Exception e) { // TODO Auto-generated catch block
	 * System.out.println("日志队列获取数据异常"); continue; }
	 * 
	 * 
	 * 
	 * if(line.contains("测试结束")){ break; } try {
	 * session.getBasicRemote().sendText(line+ "<br>"); } catch (IOException e)
	 * { // TODO Auto-generated catch block String meString =new
	 * DataUtil().getTrace(e); if(meString.contains(" closed session")){ break;
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 */

	@Override
	public void run() {

		System.out.println("启动消费者线程！");
		boolean isRunning = true;
		try {
			while (isRunning) {
				System.out.println("正从队列获取数据...");
				String line = WebInit.logQueue.poll(60, TimeUnit.SECONDS);// 有数据时直接从队列的队首取走，无数据时阻塞，在60s内有数据，取走，超过60s还没数据，返回失败
				if (null != line) {
					System.out.println("拿到数据：" + line);
					System.out.println("正在消费数据：" + line);

					try {
						session.getBasicRemote().sendText(line + "<br>");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						String meString = new DataUtil().getTrace(e);
						if (meString.contains(" closed session")) {
							break;
						}

					}

				} else {
					// 超过还没数据，认为所有生产线程都已经退出，自动退出消费线程。
					isRunning = false;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("失败1！");

			Thread.currentThread().interrupt();
			System.out.println("失败2！");

		} finally {
			System.out.println("退出消费者线程！");

				//session.getBasicRemote().sendText("无更多日志，日志打印结束" + "<br>");
                       System.out.println("无更多日志，日志打印结束\n");
			

		}
	}

}