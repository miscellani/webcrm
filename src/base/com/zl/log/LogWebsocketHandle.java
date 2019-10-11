package base.com.zl.log;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Writer;
import java.net.InetAddress;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.xml.DOMConfigurator;

@ServerEndpoint("/log")
public class LogWebsocketHandle {

  private PipedReader reader;
  private Writer writer;


  /**
   * WebSocket请求开启
   */
  @OnOpen
  public void onOpen(Session session) {
		DOMConfigurator.configure("Log4j.xml");
         System.out.println("打印日志");
    Logger root = Logger.getRootLogger();
    try {
      // 获取本服务器id
      String hostAddress = InetAddress.getLocalHost().getHostAddress();
      // MDC是key-value结构，有兴趣的可以去了解下，在log4j的配置中设置 %X{ip},在日志中输出
      MDC.put("ip",hostAddress);
      Appender appender = root.getAppender("fileAppender");
      // 通过管道流进行线程间的通讯
      reader = new PipedReader();
      writer = new PipedWriter( reader) ;
      ((WriterAppender) appender).setWriter(writer);
      // 启动新的线程
      LogThread thread = new LogThread(reader, session);
      thread.start();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * WebSocket请求关闭，关闭请求时调用此方法，关闭流
   */
  @OnClose
  public void onClose() {
    try {
      if(reader != null) {
        reader.close();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    try {
      if(writer != null) {
        writer.close();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  @OnError
  public void onError(Throwable thr) {
    thr.printStackTrace();
  }
}