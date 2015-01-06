package crazy.seleiumTools;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import crazy.tools.BasicTools;
import crazy.tools.Dbtool;

/**
 * 
 * @author xian_crazy QQ：330126160
 * @version 2014年10月30日 上午10:18:04
 * @see
 */
public class WebTools extends BasicTools {
	private WebDriver dr = null;
	private Alert alert = null;
	public Webscroll webscroll = null;
	public Actions a = null;
	

	// private Dbconn conn=null;

	// public WebTools(WebDriver driver) {
	// this.dr = driver;
	// webscroll = new Webscroll(this.dr);
	// }

	public WebTools(WebDriver driver, Dbtool dbtool) {
		super(driver, dbtool);
		this.dr = driver;
		webscroll = new Webscroll(this.dr);
		a = new Actions(dr);
		
	}

	/**
	 * 判断是否有alert弹出框
	 */
	public boolean isAlertPresent() {
		try {
			for (int i = 0; i < 10; i++) {
				if (this.dr.switchTo().alert() != null) {
					this.alert = this.dr.switchTo().alert();
					break;
				}

				this.threadSleep(1);
			}

			return true;
		} catch (NoAlertPresentException Ex) {
			this.pt("提示框不存在");
			return false;
		}
	}

	/**
	 * 读取弹出框的内容alert
	 */
	public String getAlert() {
		this.isAlertPresent();
		String str = alert.getText();

		return str;
	}

	/**
	 * 点击弹出框alert的确定按钮
	 */
	public void clickAlertOkBotton() {
		this.isAlertPresent();
		this.threadSleep(3);
		this.alert.accept();
	}

	/**
	 * 通过uiSelector xPath 找元素，并等待50秒<br/>
	 * 传入参数elementName为当前page在excel中的elementName<br/>
	 * 如果传入的是List 本方法通过&&进行分割 excel中List选择器需要通过&&进行标注，如：<br/>
	 * 序号------元素名称 --------elementName---------UiSelecter <br/>
	 * 1-------手机号输入框------phoneNoEditText-----new
	 * UiSelector().className(\"android.widget.EditText\")&&0<br/>
	 * 
	 * 
	 * @param uiSelector
	 */

	public WebElement getWebElement(String tablenameindb, String elementnameindb,
			SelectorType type, int timeout) {

		return this.setHighLight(super.getElement(tablenameindb, elementnameindb, type, timeout));

		// return super.getElement(tablenameindb, elementnameindb, type,
		// timeout);

	}

	// 通过注入jQuery
	public void injectjQuery() {
		JavascriptExecutor jse = (JavascriptExecutor) dr;
		jse.executeScript(" var headID = document.getElementsByTagName(\"head\")[0];"
				+ "var newScript = document.createElement('script');"
				+ "newScript.type = 'text/javascript';" + "newScript.src = "
				+ "'http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';"
				+ "headID.appendChild(newScript);");
	}
	
	/**
	 * 运行js脚本
	 * @see
	 *
	 */
	public void runjs(String jsstr) {
		JavascriptExecutor jse = (JavascriptExecutor) dr;
		jse.executeScript(jsstr);
	}
	
	/**
	 * 运行js脚本
	 * @see
	 * @param element  =jsstr中的arguments[0];
	 * @return String
	 */
	public String runjs(String jsstr,WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) dr;
		return (String) jse.executeScript(jsstr,element);
	}
	
	

	public WebElement setHighLight(WebElement element) {

		if (element == null) {
			return element;
		} else {
			JavascriptExecutor js = (JavascriptExecutor) this.dr;
			js.executeScript(
					"element = arguments[0];"
							+ "original_style = element.getAttribute('style');"
							+ "element.setAttribute('style', original_style + \";"
							+ "background: red;  border: 1px solid black\");"
							+ "setTimeout(function(){element.setAttribute('style', original_style);}, 200);",
					element);
			// a.moveToElement(element).perform();
			// a.moveByOffset(0, 0).perform();

			return element;
		}

	}

	/**
	 * 通过uiSelector xPath 找元素list，并等待50秒<br/>
	 * 传入参数elementName为当前page在excel中的elementName<br/>
	 * 如果传入的是List 本方法通过&&进行分割 excel中List选择器需要通过&&进行标注，如：<br/>
	 * 序号------元素名称 --------elementName---------UiSelecter <br/>
	 * 1-------手机号输入框------phoneNoEditText-----new
	 * UiSelector().className(\"android.widget.EditText\")&&0<br/>
	 * 
	 * 
	 * @param uiSelector
	 */

	public List<WebElement> getWebElements(String tablenameindb, String elementnameindb,
			SelectorType type) {

		return super.getElements(tablenameindb, elementnameindb, type, 50);

	}

	/**
	 * 获取List中的随机Webelement start表示list中随机范围起始下标值,end 表示随机范围结束下标
	 * ，如果end大于list最大下标，取最大下标
	 */
	public WebElement getRandomWebElementFromList(List<WebElement> list, int start, int end) {
		super.pt("list.size = " + list.size());
		int j = list.size() - 1;
		end = end > j ? j : end;
		int r = this.getRandomInt(start, end);
		super.pt("本次选择值为：list[" + r + "]=" + list.get(r).getText());
		return list.get(r);
	}

	/**
	 * 获取List中的最后一个Webelement 值
	 */
	public WebElement getLastWebElementFromList(List<WebElement> list) {
		super.pt("list.size = " + list.size());
		int r = list.size() - 1;
		super.pt("本次选择值为：list[" + r + "]=" + list.get(r).getText());
		return list.get(r);
	}

	// 鼠标右键单击
	public void rightClickTheMouse(WebElement element) {

		this.setHighLight(element);
		Actions actions = new Actions(this.dr);
		actions.contextClick(element).perform();

	}

	// 左键双击鼠标
	public void doubleClickTheLeftMouseButton(WebElement element) {

		this.setHighLight(element);
		Actions action = new Actions(this.dr);
		action.doubleClick(element).perform();

	}

	// 左键单击鼠标
	public void clickTheLeftMouseButton(WebElement element) {

		Actions action = new Actions(this.dr);
		action.click(element).perform();

	}

	// 鼠标拖动元素到指定元素上
	public void dragElementToElementByMouse(WebElement source, WebElement target) {

		this.setHighLight(source);

		Actions action = new Actions(this.dr);
		action.dragAndDrop(source, target);
		this.setHighLight(target);

	}

	// 将目标元素拖拽到指定的区域里
	public void dragElementToArea(WebElement element, int xOffset, int yOffset) {

		Actions action = new Actions(this.dr);
		action.dragAndDropBy(element, xOffset, yOffset);

	}

	public void dragAndDrop(WebElement e, int x, int y) {
	a.clickAndHold(e).perform();
	a.moveByOffset(x, y).release().perform();
		
		//a.dragAndDropBy(e, x, y);
	}

	public void dragAndDrop(WebElement e, WebElement toe) {
		a.dragAndDrop(e, toe).perform();
	}

	/**
	 * 滚动条滚动到该元素所在处
	 */
	public WebElement scrollerToElement(WebElement el) {
		this.webscroll.scrollToWebElement(el);
		return el;
	}

	public Select getDrop_DownList(String tablenameindb, String elementnameindb, SelectorType type) {
		Select select = new Select(this.getWebElement(tablenameindb, elementnameindb, type, 10));

		return select;
	}

	// ===================弹出新窗口跳转方法集=====================

	public boolean toNewWindows(String newWindowsTitle) {
		// String currentWindow = null;
		Set<String> handles = null;
//		if(!this.dr.getTitle().equals(newWindowsTitle)){
		
		WebDriver window = null;
		boolean t = false;
		for (int i = 0; i < 5; i++) {
			// currentWindow = this.dr.getWindowHandle();// 获取当前窗口句柄
			handles = this.dr.getWindowHandles();// 获取所有窗口句柄
			Iterator<String> it = handles.iterator();
			while (it.hasNext()) {

				// if (currentWindow == it.next()) {
				// continue;
				// }

				try {
					window = this.dr.switchTo().window(it.next());// 切换到新窗口
				} catch (TimeoutException e) {
					super.rpt(window.getTitle() + "  页面加载超过50秒  不等待加载完毕 继续执行");
				}
				super.pt("the" + i + "times search :one of titles is:"
						+ window.getTitle());

				if (window.getTitle().equals(newWindowsTitle)) {
					super.rpt("New page title is:" + window.getTitle());
					t = true;
					break;
				}
				// window.close();//关闭新窗口
			}
			if (t) {
				break;
			}
			super.threadSleep(1);
		}
		BrowersSize.maxBrowers(this.dr);
		
		if(this.dr.getTitle().equals(newWindowsTitle)){
			return true;
		}
		return false;	
	}

	public void toNewFrame(WebElement frameElement) {
		this.dr.switchTo().frame(frameElement);
	}

	public void toDefaultFrame() {
		this.dr.switchTo().defaultContent();
		
	}

	// firefox  ie64位中文版 文件上传
	public void clickfileUploadElementThenUploadByAutoItforfireFox_64(WebElement el,
			String fileallURL) {

		int i = fileallURL.lastIndexOf("\\");
		String str = fileallURL.substring(i + 1);

		fileallURL = fileallURL.replace("\\" + str, "   " + str);
		el.click();
		this.getRuntimeexec(System.getProperty("user.dir") + "\\tool\\fileUploadByAutoItforfireFox_64.exe"
				+ "  " + fileallURL);
	}

	// cmd命令调用
	public void getRuntimeexec(String cmdStr) {

		Process p = null;
		try {
			p = Runtime.getRuntime().exec(cmdStr);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			// super.pt(p.toString());
		} finally {

		}

	}

	public WebElement getWebElementByReplaceSelectorOfindb(String tablenameindb,
			String elementnameindb, SelectorType type, String valueOfvar) {
		return this.setHighLight(super.getWebElementByReplaceSelectorOfindb(tablenameindb,
				elementnameindb, type, valueOfvar, 10));

	}

	/**
	 * 鼠标拉框 点击鼠标 拉一个框 松开鼠标 x,y坐标为elPoint的相对坐标
	 * 
	 */
	public void getFrameInElByMouse(WebElement el, String  elDescription,int sx, int sy, int ex, int ey) {
		super.rpt("在"+elDescription+"中拉框");
	
		sy = (el.getSize().height / 2 < sy || sy <= (int) (el.getSize().height * 0.15)) ? el
				.getSize().height / 4 : sy;
		sx = (el.getSize().width / 2 < sx || sx <= (int) (el.getSize().width * 0.15)) ? el
				.getSize().width / 4 : sx;

		ey = (el.getSize().height < ey || ey <= sy) ? (int) (el.getSize().height * 0.5) : ey;
		ex = (el.getSize().width < ey || ex <= sx) ? (int) (el.getSize().width * 0.5) : ex;

		super.pt("sx:" + sx + "  sy:" + sy + "  ex:" + ex + "  ey:" + ey);
		a.moveToElement(el, sx, sy).perform();
		a.clickAndHold().perform();
		a.moveByOffset(ex, ey).perform();
		a.release().perform();
	}
	


	
	
}
