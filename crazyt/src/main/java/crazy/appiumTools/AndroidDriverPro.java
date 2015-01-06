package crazy.appiumTools;

import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * 
 * @author xian_crazy QQ：330126160
 * @version 2014年10月30日  上午10:16:32
 * @see
 */
public class AndroidDriverPro {
	private File classpathRoot;
	private  AndroidDriver dr;
	private String appfileurl;
	private String DEVICE_NAME;
	private String PLATFORM_VERSION;
	private String APP_PACKAGE;
	private String APP_ACTIVITY;
	private String REMOTE_ADDRESS;
	
	public AndroidDriverPro setAppfileurl(String appfileurl) {
		this.appfileurl = appfileurl;
		return this;
	}


	public AndroidDriverPro setDEVICE_NAME(String dEVICE_NAME) {
		DEVICE_NAME = dEVICE_NAME;
		return this;
	}


	public AndroidDriverPro setPLATFORM_VERSION(String pLATFORM_VERSION) {
		PLATFORM_VERSION = pLATFORM_VERSION;
		return this;
	}


	public AndroidDriverPro setAPP_PACKAGE(String aPP_PACKAGE) {
		APP_PACKAGE = aPP_PACKAGE;
		return this;
	}


	public AndroidDriverPro setAPP_ACTIVITY(String aPP_ACTIVITY) {
		APP_ACTIVITY = aPP_ACTIVITY;
		return this;
	}


	public AndroidDriverPro setREMOTE_ADDRESS(String rEMOTE_ADDRESS) {
		REMOTE_ADDRESS = rEMOTE_ADDRESS;
		return this;
	}



	

	public AndroidDriver gerAndroidDriver(){
		System.out.println("手机设备初始化开始,请稍后");
		 classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/apps");
		File app = new File(appDir, appfileurl);


		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
				"Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
				DEVICE_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
				PLATFORM_VERSION);
		capabilities.setCapability(MobileCapabilityType.APP,
				app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
				APP_PACKAGE);
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,
				APP_ACTIVITY);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
		capabilities.setCapability("unicodeKeyboard",true);

		try {
		
			dr = new  AndroidDriver(new URL(REMOTE_ADDRESS),capabilities);
			System.out.println("手机设备初始化完成");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 配置avd上网
		if (dr.getNetworkConnection().wifiEnabled()&&dr.getNetworkConnection().dataEnabled()) {
			System.out.println("设备已经允许网络数据连接");
		} else {
			System.out.println("设置设备网络数据连接");
			NetworkConnectionSetting connection = new NetworkConnectionSetting(
					false, true, true);
			dr.setNetworkConnection(connection);		
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return dr;
	}
	
}
