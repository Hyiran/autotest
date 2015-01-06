package example.projecttool;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import crazy.seleiumTools.BrowsersDriverPro.BrowsersType;
import crazy.seleiumTools.WebTestPro;
import crazy.tools.Dbtool.DBType;




/**
 *case»ùÀà
 */
public class  BATest  extends WebTestPro {
	
	public static PageF pf=null;

	public BATest(){
		
		
		super.setHomeurl(ItemPro_example.WEB_HOMEURL_TEST_QT);
		driverPro.setBrowserstype(BrowsersType.nfirefox);
		dbtool.setDBconnPar(DBType.mysql, "TestData", "192.168.0.56", "3306", "root", "root");
		super.setPfClass(PageF.class);
		
	}
	
	@BeforeTest
	public void load_Test() {
		pf= super.getPf();
	}
	

	@AfterTest
	public void release_Test() {	
		super.quitdriver(2);
		super.closedbtool();
		
	}


	
	
	



}
