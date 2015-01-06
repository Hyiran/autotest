package crazy.seleiumTools;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import crazy.seleiumTools.BrowsersDriverPro.BrowsersType;
import crazy.tools.LogEventListener;

//import com.crazy.tools.LogEventListener;

/**
 * 
 * @author xian_crazy QQ：330126160
 * @version 2014年10月30日 上午10:17:32
 * @see
 */
public class BrowsersDriverPro {
	private WebDriver driver = null;

	private static DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");
	private static int pageLoadTimeout=120;
	private String driverPropertyFirfoxEXEurl=null;
	private String driverPropertyIEdriverUrl=null;
	private String driverPropertychromeDriverUrl=null;
	private String firebugUrl=null;
	private String firebugV=null;
	private String firepathUrl=null;
	private BrowsersType browserstype=null;

	/**
	 * 枚举浏览器类型firefox, ie, chrome;
	 * 
	 * @param browserstype
	 */
	public enum BrowsersType {
		nfirefox,firefox, ie, chrome;
	}

	public WebDriver getDriver() {
		switch (browserstype) {

		//创建新的firefox profile
		case nfirefox:
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
			
			File firebug = new File(getClass().getClassLoader().getResource("tool/firebug@software.joehewitt.com.xpi").getPath());
			File firepath = new File(getClass().getClassLoader().getResource("tool/FireXPath@pierre.tholence.com.xpi").getPath());
			FirefoxProfile firefoxprofile1 =  new FirefoxProfile();
			try {
				firefoxprofile1.addExtension(firebug);
				firefoxprofile1.addExtension(firepath);
				firefoxprofile1.setPreference("webdriver.accept.untrusted.certs", "true"); 
				firefoxprofile1.setPreference("extensions.firebug.currentVersion", "2.0.6");
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			driver=new EventFiringWebDriver(new FirefoxDriver(firefoxprofile1)).register(new LogEventListener());

			break;
			//使用系统默认firefox profile 并设置为隐私模式运行浏
		case firefox:
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
			ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile firefoxprofile = allProfiles.getDefaultProfile();
			firefoxprofile.setPreference("webdriver.accept.untrusted.certs", "true");
			firefoxprofile.setPreference("extensions.firebug.currentVersion", "2.0.7");
			// 配置浏览器不加载原配置，既，浏览器工作在隐私模式
			firefoxprofile.setPreference("browser.privatebrowsing.autostart", true);
			
			driver=new EventFiringWebDriver(new FirefoxDriver(firefoxprofile)).register(new LogEventListener());
			//driver=new FirefoxDriver(firefoxprofile);
		
			break;

		case ie:
			System.setProperty("webdriver.ie.driver", projectpath + "/tool/IEDriverServer_64_ie8.exe");
			caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(caps);
			//driver=new EventFiringWebDriver(new InternetExplorerDriver(caps)).register(new LogEventListener());
			
	
			break;
		
		case chrome:
			System.setProperty("webdriver.chrome.driver", projectpath + "/tool/chromedriver.exe");
			caps = DesiredCapabilities.chrome();
			caps.setCapability("chrome.switches", Arrays.asList("--start-maximized")); // 最大化browser
			// capabilities.setCapability("chrome.switches",
			// Arrays.asList("--proxy-server=http://your-proxy-domain:4443"));
			// //设置代理
			driver = new ChromeDriver(caps);
			break;
		}
		return driver;
	}
	
	public  static void setPageLoadTimeout(int time){
		BrowsersDriverPro.pageLoadTimeout=time;
	}
	
	public static int getPageLoadTimeout(){
		
		return pageLoadTimeout;
	}



	/**
	 * @return the firebug
	 */
	public String getFirebugUrl() {
		return firebugUrl;
	}

	/**
	 * @param firebug the firebug to set
	 */
	public void setFirebugUrl(String firebug) {
		this.firebugUrl = firebug;
	}

	/**
	 * @return the firepath
	 */
	public String getFirepathUrl() {
		return firepathUrl;
	}

	/**
	 * @param firepath the firepath to set
	 */
	public void setFirepathUrl(String firepath) {
		this.firepathUrl = firepath;
	}

	/**
	 * @return the driverPropertyFirfoxEXEurl
	 */
	public String getDriverPropertyFirfoxEXEurl() {
		return driverPropertyFirfoxEXEurl;
	}

	/**
	 * @param driverPropertyFirfoxEXEurl the driverPropertyFirfoxEXEurl to set
	 */
	public void setDriverPropertyFirfoxEXEurl(String driverPropertyFirfoxEXEurl) {
		this.driverPropertyFirfoxEXEurl = driverPropertyFirfoxEXEurl;
	}

	/**
	 * @return the driverPropertyIEdriverUrl
	 */
	public String getDriverPropertyIEdriverUrl() {
		return driverPropertyIEdriverUrl;
	}

	/**
	 * @param driverPropertyIEdriverUrl the driverPropertyIEdriverUrl to set
	 */
	public void setDriverPropertyIEdriverUrl(String driverPropertyIEdriverUrl) {
		this.driverPropertyIEdriverUrl = driverPropertyIEdriverUrl;
	}

	/**
	 * @return the driverPropertychromeDriverUrl
	 */
	public String getDriverPropertychromeDriverUrl() {
		return driverPropertychromeDriverUrl;
	}

	/**
	 * @param driverPropertychromeDriverUrl the driverPropertychromeDriverUrl to set
	 */
	public void setDriverPropertychromeDriverUrl(String driverPropertychromeDriverUrl) {
		this.driverPropertychromeDriverUrl = driverPropertychromeDriverUrl;
	}

	/**
	 * @return the firebugV
	 */
	public String getFirebugV() {
		return firebugV;
	}

	/**
	 * @param firebugV the firebugV to set
	 */
	public void setFirebugV(String firebugV) {
		this.firebugV = firebugV;
	}

	/**
	 * @return the browserstype
	 */
	public BrowsersType getBrowserstype() {
		return browserstype;
	}

	/**
	 * @param browserstype the browserstype to set
	 */
	public void setBrowserstype(BrowsersType browserstype) {
		this.browserstype = browserstype;
	}





}
