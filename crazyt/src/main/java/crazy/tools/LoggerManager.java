package crazy.tools;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

public class LoggerManager {
    private static Logger logger =null;
    private static LoggerManager selog=null;
//    private  Logger logger ;
//    private  LoggerManager selog;
    
    public static  LoggerManager getLogger(Class<?> T) {
       if (logger == null) {
    	
            Properties props = new Properties();
            
            try {
                InputStream is = new FileInputStream("src/main/resources/log4j.properties");
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            PropertyConfigurator.configure(props);
          logger = Logger.getLogger(T);
              selog = new LoggerManager();
            
        }
        return selog;
    }

    
    
    /**
     * 重写log info方法  
     * @see
     * @param msg
     */
    public void info(String msg) {
        
        logger.info(msg);
      
    }
    
    /**
     * 重写log info方法  
     * @see
     * @param msg
     */
    public void info2logAndReporter(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        logger.info("*****"+msg);
        Reporter.log(sdf.format(ca.getTime()) + "=>" + msg);
    }
    
    public void debug(String msg) {
        logger.debug(msg);
    }
    
    public void warn(String msg) {
        logger.warn(msg);
        Reporter.log("Reporter:" + msg);
    }
    
    public void error(String msg) {
        logger.error(msg);
        Reporter.log("Reporter:" + msg);
    }
}
