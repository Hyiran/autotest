package example.web.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author xian_crazy QQ：330126160
 * @version 2014年11月12日  下午6:19:02
 * @see  主菜单点击验证是否正确跳转（已经实现位置服务三个菜单）
 */
public class MainMenuCheck extends ClassConfig{


	@Test(groups =  "level0" )   
	public void mainMenuCheckDingWei(){
	
		pf.mainMenuPage.clickWeiZhi().clickDingWei();
		Assert.assertEquals(super.getDriver().getTitle(), "定位服务-易流云平台");
	}
	
	@Test(groups =  "level0" )   
	public void mainMenuCheckGuiJi(){
		pf.mainMenuPage.clickWeiZhi().clickGuiJi();
		Assert.assertEquals(super.getDriver().getTitle(), "轨迹回放-易流云平台");
	}
	
	@Test
	public void mainMenuCheckWuLiu(){
		pf.mainMenuPage.clickWeiZhi().clickWuLiu();
		Assert.assertEquals(super.getDriver().getTitle(), "物流地图-易流云平台");
	}

}
