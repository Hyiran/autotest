package crazy.appiumTools;

import crazy.tools.BasicTools;
import crazy.tools.Dbtool;
import crazy.tools.Dbtool.DBType;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;

/**
 * 测试用例基类
 * 
 * @author Administrator
 * @see 父类public属性如下：<br/>
 * 
 *      public AndroidDriver driver;<br/>
 *      public Dbtool dbtool;<br/>
 *      public Tools tool;<br/>
 */
public class AppTestPro {
	public AndroidDriver driver;

	private File classpathRoot;

	private AndroidDriverPro adr;
	public static Dbtool dbtool=new Dbtool();//数据库连接

	public AppTestPro setDriver(AndroidDriver dr) {
		this.driver = dr;
		return this;
	}

	// public void setDbtool(Dbtool dbtool) {
	// this.dbtool = dbtool;
	// }

	

	public AppTestPro setDbtool(Dbtool dbtool) {
		AppTestPro.dbtool = dbtool;
		return this;
	}

	public BasicTools tool = new BasicTools();

	private   String aPP_ACTIVITY;
	private  String aPP_PACKAGE;
	private  String appfileurl;
	private  String dEVICE_NAME;
	private  String pLATFORM_VERSION;
	private  String rEMOTE_ADDRESS;
	


	



	public  void setaPP_ACTIVITY(String aPP_ACTIVITY1) {
		aPP_ACTIVITY = aPP_ACTIVITY1;
	
	}

	public  void setaPP_PACKAGE(String aPP_PACKAGE1) {
		aPP_PACKAGE = aPP_PACKAGE1;
		
	}

	public  void setAppfileurl(String appfileurl1) {
	appfileurl = appfileurl1;
		
	}

	public  void setdEVICE_NAME(String dEVICE_NAME1) {
		dEVICE_NAME = dEVICE_NAME1;
		
	}

	public  void setpLATFORM_VERSION(String pLATFORM_VERSION1) {
		pLATFORM_VERSION = pLATFORM_VERSION1;
	
	}

	public  void setrEMOTE_ADDRESS(String rEMOTE_ADDRESS1) {
		rEMOTE_ADDRESS = rEMOTE_ADDRESS1;
		
	}

	public File getClasspathRoot() {
		return classpathRoot;
	}


	
	public AndroidDriver getAndroidDriver(){
		adr=new AndroidDriverPro();
		adr.setAPP_ACTIVITY(aPP_ACTIVITY)
		.setAPP_PACKAGE(aPP_PACKAGE)
		.setAppfileurl(appfileurl)
		.setDEVICE_NAME(dEVICE_NAME)
		.setPLATFORM_VERSION(pLATFORM_VERSION)
		.setREMOTE_ADDRESS(rEMOTE_ADDRESS);
		return adr.gerAndroidDriver();
		
	}
	
	public void driverSetUp()  {
		this.driver = this.getAndroidDriver();
		dbtool = dbtool.getConnection();
	}

	public void driverTearDown()  {

		driver.quit();
		dbtool.close();
		System.out.println("AndroidTestCase结束");
	}

	// @Test
	public void test() {

		System.out.println(driver.getPageSource());
		// System.out.println(dr.getCurrentUrl());
		System.out.println(driver.getAppStrings());
		// System.out.println(dr.getTitle());
	}

}
