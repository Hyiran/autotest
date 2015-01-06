package example.web.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import example.projecttool.BATest;
import example.projecttool.LogIOtool;

/**
 * @author xian_crazy QQ：330126160
 * @version 2014年12月2日  下午12:40:50<br/>
 * 
 */
public class ClassConfig extends BATest{
	
	@BeforeTest(groups = { "level0" })
	public void login(){
		LogIOtool.login(pf,	super.getDbtool());
	}
	@AfterTest(groups = { "level0" })
	public void logout(){
		LogIOtool.logout(pf);
	}
	
	@AfterClass(groups = { "level0" })
	public void toHomePage(){
		super.getDriver().close();
		pf.mainMenuPage.toNewWindws("");
	}
	
}
