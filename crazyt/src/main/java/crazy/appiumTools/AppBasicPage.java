package crazy.appiumTools;

import java.util.List;

import crazy.tools.BasicTools;
import crazy.tools.BasicTools.SelectorType;
import crazy.tools.Dbtool;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AppBasicPage  {

	private BasicTools btool;
	private AndroidTools atool;
	private String tablenameindb = this.getClass().getSimpleName();

	public AppBasicPage(AndroidDriver adriver, Dbtool dbtool) {
		this.btool=new BasicTools(adriver, dbtool);
		this.atool = new AndroidTools(adriver, dbtool);
	}

	public AndroidElement getAndroidElement(String elementnameindb,
			SelectorType type) {

		return this.atool.getAndroidElement(tablenameindb, elementnameindb,
				type,50);
	}

	/**
	 * By.xpath
	 * 
	 * @param elementnameindb
	 * @return
	 */
	public AndroidElement getAndroidElement(String elementnameindb) {

		return this.atool.getAndroidElement(tablenameindb, elementnameindb,
				SelectorType.xPath,50);
	}
	
	public AndroidElement getAndroidElementFast(String elementnameindb) {

		return this.atool.getAndroidElement(tablenameindb, elementnameindb,
				SelectorType.xPath,3);
	}

	public List<AndroidElement> getAndroidElements(String elementnameindb,
			SelectorType type) {
		return this.atool.getAndroidElements(tablenameindb, elementnameindb,
				type,50);
	}
	
	

	/**
	 * 获取List中的随机AndroidElement i表示list中随机范围起始下标值
	 */
	private AndroidElement getRandomAndroidElementFromList(
			List<AndroidElement> list, int i) {

		return this.atool.getRandomAndroidElementFromList(list, i);
	}

	/**
	 * By.xpath
	 * 
	 * @param elementnameindb
	 * @return
	 */
	public List<AndroidElement> getAndroidElements(String elementnameindb) {
		return this.atool.getAndroidElements(tablenameindb, elementnameindb,
				SelectorType.xPath,50);
	}
	
	/**
	 * By.xpath
	 * 
	 * @param elementnameindb
	 * @return i  秒
	 */
	public List<AndroidElement> getAndroidElements(String elementnameindb,int i) {
		return this.atool.getAndroidElements(tablenameindb, elementnameindb,
				SelectorType.xPath,i);
	}

	public void pt(String str) {
		this.atool.pt(str);
	}

	public void tap(AndroidElement ael) {
		this.atool.getAndroidAction().tapOneTime(ael);
	}

	public void swipeToLeft(AndroidElement ael) {
		this.atool.getAndroidAction().swipeToLeft(ael);
	}
	
	public AppBasicPage tapTheAELByswipeAncestor(String ancestorElementnameindb,String TextOfchildEL) {
		this.tap(this.atool
				.getELByitsTextfromParentELInSwip(
						this.getAndroidElement(ancestorElementnameindb), TextOfchildEL));
		return this;
	}
	

	

	public int getPhoneHalfOfH() {
		return this.atool.getAndroidAction().getH2();
	}

	public void adbinputtext(AndroidElement ael, String str) {
		this.atool.adbinuttext(ael, str);
	}

	public AppBasicPage adbinputtext(String elementnameindb, String inputstr) {
		this.atool.adbinuttext(this.getAndroidElement(elementnameindb),
				inputstr);
		return this;
	}

	public AppBasicPage tapAndroidElement(String elementnameindb) {
		this.pt(elementnameindb);
		this.tap(this.getAndroidElement(elementnameindb));
		return this;
	}

	/**
	 * tapList中的随机AndroidElement i表示list中随机范围起始下标值
	 */
	public AppBasicPage tapRandomAndroidElementFromList(String elementnameindb,
			int i) {
		this.pt(elementnameindb);
		this.tap(this.getRandomAndroidElementFromList(
				this.getAndroidElements(elementnameindb), i));
		return this;
	}
	
	/**
	 * tapList中的第i个AndroidElement 
	 */
	public AppBasicPage tapOneOfAndroidElementFromList(String elementnameindb,
			int i) {
		this.pt(elementnameindb);
		this.tap(
				this.getAndroidElements(elementnameindb).get(i));
		return this;
	}

	/**
	 * 获取AndroidElement的text值
	 * 
	 * @param elementnameindb
	 * @return
	 */
	public String getAndroidElementText(String elementnameindb) {

		return this.getAndroidElement(elementnameindb).getText();
	}
	
	public void open_notification(){
		this.atool.getAndroidAction().open_notification();
	}
	
	public void threadSleep( int i){
		this.btool.threadSleep(i);
	}

	/**
	 * @see
	 * @param ael
	 * @return
	 */
	public String getXpathOfElement(AndroidElement ael) {
		
		return this.btool.getXpathOfElement(ael);
	}

	/**
	 * @see
	 * @param string
	 * @param xpath
	 * @param xpath2
	 * @param i
	 */
	public AndroidElement getElementBynewselctor(String string, SelectorType xpath, String xpath2, int i) {
		
		return (AndroidElement) this.btool.getElementBynewselctor(string, xpath, xpath2, i);
	}
	


}
