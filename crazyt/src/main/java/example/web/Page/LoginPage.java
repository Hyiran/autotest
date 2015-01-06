package example.web.Page;

import org.openqa.selenium.WebDriver;

import crazy.seleiumTools.WebBasicPage;
import crazy.tools.Dbtool;

/**
 * @author xian_crazy QQ：330126160
 * @version 2014年11月12日  下午3:03:50
 * @see
 */
public class LoginPage extends WebBasicPage{

	/**
	 * @param wdriver
	 * @param dbtool
	 */
	public LoginPage(WebDriver wdriver, Dbtool dbtool) {
		super(wdriver, dbtool);
		// TODO Auto-generated constructor stub
	}
	
	public LoginPage login(String username,String password){
		super.btool.rpt("输入用户名密码登录");
		super.editInputElement("用户名", username)
		.editInputSecret("密码", password)
		.clickWebElement("登录");
		return this;
	}

}
