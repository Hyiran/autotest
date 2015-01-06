package example.projecttool;

import org.openqa.selenium.WebDriver;

import crazy.seleiumTools.PageFactory;
import crazy.tools.Dbtool;
import example.web.Page.LoginPage;
import example.web.Page.MainMenuPage;

/**
 * @author xian_crazy QQ：330126160
 * @version 2014年11月13日 上午9:19:46
 * 页面工厂，将页面类在此类中声明
 */
public class PageF extends PageFactory{

	public MainMenuPage mainMenuPage;
	public LoginPage loginpage;


	public PageF(WebDriver driver, Dbtool dbtool) {
		super(driver, dbtool);
	}



}
