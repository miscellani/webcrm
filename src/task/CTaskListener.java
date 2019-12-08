package task;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jcraft.jsch.ConfigRepository.Config;

import test.config.dao.WebConfigDao;
 
public class CTaskListener implements ServletContextListener {
	private java.util.Timer timer = null;
 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		timer = new java.util.Timer(true);
		sce.getServletContext().log("initializing system core task...");
		//定时三十分钟 60*30*1000
        WebConfigDao webConfigDao = new WebConfigDao();
        String interval = null;
        try {
			 interval = webConfigDao.getRunParam("interval");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("定时任务已启动，每"+interval+"分钟轮询一次");

        int fen = Integer.parseInt(interval);
		timer.schedule(new CCoreTask(sce.getServletContext()), new Date(), 60*fen*1000);

	}
 
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().log("system core auto task ended.");
		timer.cancel();
	}
 

}


