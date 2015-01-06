package example.web.testcase;



import org.testng.Assert;
import org.testng.annotations.Test;

import example.projecttool.BATest;

/**
 * @author xian_crazy QQ：330126160
 * @version 2014年11月12日 下午5:02:18
 * @see
 */
public class LogInOut extends BATest{
	

	@Test
	public void login(){
	
		String username=BATest.getDbtool().getValuebyKey("用户名", "userData", "序号", "1");
		String pw=BATest.getDbtool().getValuebyKey("密码", "userData", "序号", "1");
	pf.loginpage.login(username, pw);
	pf.mainMenuPage.toHome();
	Assert.assertEquals(BATest.getDbtool().getValuebyKey("昵称", "userData", "序号", "1"), 
			pf.mainMenuPage.getNickname());
	}
	
	
	@Test(dependsOnMethods="login")
	public void logout(){
		pf.mainMenuPage.logOut();
	}

	
	

}
