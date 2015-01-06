package example.web.Page;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import crazy.seleiumTools.WebBasicPage;
import crazy.tools.Dbtool;

/**
 * @author xian_crazy QQ：330126160
 * @version 2014年11月12日  下午5:43:37
 * @see
 */
public class MainMenuPage extends WebBasicPage{
	/**
	 * @param wdriver
	 * @param dbtool
	 */
	public MainMenuPage(WebDriver wdriver, Dbtool dbtool) {
		super(wdriver, dbtool);
	}
	
	public String getNickname(){
		return super.getWebElementTextBy160SpaceSplit("昵称");
	}

	
	public MainMenuPage clickWeiZhi(){
		super.clickWebElement("位置服务");
		return this;
	}
	
	public MainMenuPage clickDingWei(){
		super.clickWebElement("定位服务");
		
		super.toNewWindws("定位服务-易流云平台");
		return this;
	}
	
	public MainMenuPage clickGuiJi(){
		super.clickWebElement("轨迹回放");
		super.toNewWindws("轨迹回放-易流云平台");
		return this;
	}
	
	public MainMenuPage clickWuLiu(){
		super.clickWebElement("物流地图");
		super.toNewWindws("物流地图-易流云平台");
		return this;
	}
	
	
	
	public MainMenuPage clickTongJiBaoBiao(){
		super.clickWebElement("统计报表");
		return this;
	}
	
	public MainMenuPage logOut(){
		super.hovermouseFornewElementThenClick("昵称", "退出");
		super.toNewWindws("易流云平台");
		return this;
	}
	
	/**
	 * 到统计报表的子报表
	 * @see
	 * @param subMenuName
	 */
	public void toTongJiBaoBiao_SubMenuPage(String subMenuName){
		boolean t=true;
		for(int i=0;i<3&&t;i++){
		t=!super.hovermouseFornewElementThenClick("统计报表", subMenuName)		
		.toNewWindws("易流云平台");
		}
		Assert.assertEquals(t, false,"统计报表菜单未找到"+subMenuName);
		super.getWebElementInTime("隐藏报表统计", 10);
		super.threadSleep(5);
	}
	
	/**
	 * 打开统计报表菜单中第一个报表
	 */
	public void  open1stReport(){
		this.toTongJiBaoBiao_SubMenuPage(super.findWebElement("当前可选报表").getAttribute("text"));
	}
	
	
	/**
	 * 到首页
	 */
	public void toHome(){
		while(!super.getDriver().getTitle().equals("首页-易流云平台")){
			super.clickWebElement("首页");
			super.toNewWindws("首页-易流云平台");
		}
	}
	
	
	
	
	


}
