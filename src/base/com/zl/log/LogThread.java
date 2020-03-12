package base.com.zl.log;

import base.com.zl.utils.DataUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import test.web.WebInit;


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