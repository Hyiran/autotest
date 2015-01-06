package crazy.seleiumTools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import crazy.seleiumTools.BrowsersDriverPro.BrowsersType;
import crazy.tools.BasicTools;
import crazy.tools.Dbtool;
import crazy.tools.Dbtool.DBType;

/**
 * @author Administrator
 * @param <T>
 * 
 * @see 父类public属性如下：<br/>
 * 
 *      public WebDriver driver;<br/>
 *      public Dbtool dbtool;<br/>
 *      public Tools tool;<br/>
 */
public class WebTestPro {

	private static String homeurl=null;
	private static String dbname=null;
	
	public static BrowsersDriverPro driverPro=new BrowsersDriverPro();

	private static WebDriver driver=null;  //driver
	public static Dbtool dbtool=new Dbtool();//数据库连接
	
	
	
	private static Class <?> pageclass=null;
	public static BasicTools btool = new BasicTools();//通用工具
	
	
	


	private static  Dbtool newDbtool(){
		System.out.println(dbname);
		dbtool=dbtool.getConnection();
		return dbtool;
	}
	
	private static WebDriver newDriver(int pageLoadTimeout) {	
		
		WebTestPro.driver = driverPro.getDriver();
		BrowsersDriverPro.setPageLoadTimeout(pageLoadTimeout);
		WebTestPro.driver.manage().timeouts()
				.pageLoadTimeout(BrowsersDriverPro.getPageLoadTimeout(), TimeUnit.SECONDS);
		try {
			WebTestPro.driver.get(homeurl);
		} catch (TimeoutException e) {
			 btool.rpt(WebTestPro.driver.getTitle() + "  页面加载超过"
					+ BrowsersDriverPro.getPageLoadTimeout() + "秒  不等待加载完毕 继续执行");
		}
		BrowersSize.maxBrowers(driver);
		return driver;
	}
	
	
	
	private static   <T>T newPf(Class<T> mpageclass){	
		T mypage = null;
		try {
			mypage = mpageclass.getConstructor(WebDriver.class,Dbtool.class)
						.newInstance(WebTestPro.getDriver(),WebTestPro.getDbtool());
		} catch (Exception e) {
	
			e.printStackTrace();
		} 
		return  mypage;
	}
	


	
	public WebTestPro quitdriver(int endWaitTime) {
		WebTestPro.btool.threadSleep(endWaitTime);
		try {
			WebTestPro.btool.cmd("taskkill /F /im plugin-container.exe");
			driver.quit();
			driver=null;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// this.tool.cmd("taskkill /F /IM IEDriverServer_64_ie8.exe");
		return this;
	}
	
	public WebTestPro closedbtool() {		
			dbtool.close();
			dbtool=null;
			return this;
	}
	
	

	

	public static void setHomeurl(String homeurl) {
		WebTestPro.homeurl = homeurl;
	}


	/**
	 * 
	 * 设置测试项目的数据库名
	 * @param dbname
	 */
	public static void setDbname(String dbname) {
		WebTestPro.dbname = dbname;
	}




	public static WebDriver getDriver() {
		if(driver==null){
			driver=newDriver(120);
		}
		return driver;
	}

	public static Dbtool getDbtool() {
		if(dbtool==null){
			dbtool=newDbtool();
		}

		return dbtool;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T>T getPf() {

		return (T) newPf(WebTestPro.pageclass);
		
	}

	public static void setPfClass(Class <?> pageClass) {
		WebTestPro.pageclass=pageClass;

	}


	
	


}
