package example.projecttool;

import org.testng.Assert;

import crazy.tools.Dbtool;

/**
 * @author xian_crazy QQ：330126160
 * @version 2014年12月2日  上午11:30:14
 * @see
 */
public class LogIOtool {

	
	public static void login(PageF pf,Dbtool dbtool){
		System.out.println(dbtool.toString());
		String username=dbtool.getValuebyKey("用户名", "userData", "序号", "1");
		String pw=dbtool.getValuebyKey("密码", "userData", "序号", "1");
	pf.loginpage.login(username, pw);
	pf.mainMenuPage.toHome();
	Assert.assertEquals(dbtool.getValuebyKey("昵称", "userData", "序号", "1"), 
			pf.mainMenuPage.getNickname());
	}
	
	public static void logout(PageF pf){
		pf.mainMenuPage.logOut();
	}
}
