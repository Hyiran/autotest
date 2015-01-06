package crazy.tools;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class PublicLib {

	public AndroidDriver androidDriver = null;

	public PublicLib(AndroidDriver androidDriver) {
		this.androidDriver = androidDriver;
	}

	/**
	 * 场景：同一个页面有10个ImageView对象，而我们的目标元素的index为4，而同为ImageView且index为4的页面只有2个。
	 * 那我们就可以根据这2个条件来组合查询条件，提高效率
	 * 
	 * @param classname
	 * @param index
	 * @return
	 */
	public List<WebElement> getElementsByClassAndIndex(String classname,
			int index) {
		List<WebElement> list = null;
		list = androidDriver
				.findElementsByAndroidUIAutomator("new UiSelector().className("
						+ classname + ").index(" + index + ")");
		return list;
	}

	/**
	 * 场景：同一个页面有10个ImageView对象，而其中index为4的有5个，而这时我们发现我们的目标元素的是clickable的。
	 * 然后review页面发现，同时满足上述条件的只有2个。
	 * 
	 * @param classname
	 * @param index
	 * @return
	 */
	public List<WebElement> getElementsByClassAndIndexAndClickable(
			String classname, int index) {
		List<WebElement> lis = null;
		lis = androidDriver
				.findElementsByAndroidUIAutomator("new UiSelector().className("
						+ classname + ").index(" + index + ").clickable(true)");
		return lis;
	}

	/**
	 * 场景：在分析页面元素的时候发现，页面相对比较简单，而且其中只有目标元素的index为4.
	 * 
	 * @param index
	 * @return
	 */
	public WebElement getElementByIndex(int index) {
		return androidDriver
				.findElementByAndroidUIAutomator("new UiSelector().index("
						+ index + ")");
	}
	
	/**
	 * 场景：在分析页面元素的时候发现，页面相对比较简单，而且其中目标元素的index为4只有2个.
	 * @param index
	 * @return
	 */
	public List<WebElement> getElementsByIndex(int index) {
		List<WebElement> lis = null;
		lis = androidDriver
				.findElementsByAndroidUIAutomator("new UiSelector().index("
						+ index + ")");
		return lis;
	}

	/**
	 * 关于输入框有默认text，且当你选中时，不会清空的清空的处理。
	 * 
	 * @param el
	 */
	public void clear(WebElement el) {
		el.click(); // 选中输入框
		androidDriver.sendKeyEvent(123);// 将光标移到最后
		String txt = el.getText(); // 获取字符串长度
		for (int i = 0; i < txt.length(); i++) {
			androidDriver.sendKeyEvent(67);// 一个个的删除。。。。。
		}
	}

	/**
	 * 获取当前页面的xml
	 * 
	 * @return String
	 */
	public String getPageSource() {
		return androidDriver.getPageSource();
	}

	/**
	 * appium不支持中文输入 参考了robotium的以js方式为元素直接设置value的做法
	 * 利用Selenium中Webdriver执行js方法实现中文输入
	 * 
	 * @param id
	 * @param str
	 */
	public void inputChinese(String id, String str) {
		JavascriptExecutor jse = (JavascriptExecutor) androidDriver;
		jse.executeScript("document.getElementById('" + id + "').value='" + str
				+ "'");

	}

	/**
	 * 切换到webView中，此方法还有待改进,没有经过测试
	 */
	public void SwitchtoWebViewModel() throws Exception {
		try {
			Set<String> contextNames = androidDriver.getContextHandles();
			for (String contextName : contextNames) {
				System.out.println(contextName);
				// 用于返回被测app是NATIVE_APP还是WEBVIEW，如果两者都有就是混合型App
				if (contextName.contains("WEBVIEW")) {
					androidDriver.context(contextName);
					// appDriver.switchTo().window("WEBVIEW");
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 对于定位不到的元素，暂时可以使用模拟点击x，y轴坐标的方式实现click事件 打开设置-开发者选项-指针位置来获取元素的x，y轴坐标
	 * 
	 * @param x
	 *            被点击元素的x轴坐标
	 * @param y
	 *            被点击元素的y轴坐标
	 */
	public void clickPoint(int x, int y) {
//		androidDriver.swipe(x, y, x, y, 1);// 最后一个参数为移动速度，越大移动速度越慢
		
		TouchAction ta = new TouchAction(androidDriver);
		androidDriver.performTouchAction(ta.tap(x, y));
	}

	/**
	 * 获取指定元素的高
	 * 
	 * @param webElement
	 * @return
	 */
	public int getHeighByElement(WebElement webElement) {
		return webElement.getSize().getHeight();
	}

	/**
	 * 获取指定元素的宽
	 * 
	 * @param webElement
	 * @return
	 */
	public int getWidthByElement(WebElement webElement) {
		return webElement.getSize().getWidth();
	}

	/**
	 * 获取指定元素的x轴的起始坐标
	 * 
	 * @param webElement
	 * @return
	 */
	public int getStartXByElement(WebElement webElement) {
		return webElement.getLocation().getX();
	}

	/**
	 * 获取指定元素的y轴的起始坐标
	 * 
	 * @param webElement
	 * @return
	 */
	public int getStartYByElement(WebElement webElement) {
		return webElement.getLocation().getY();
	}

	/**
	 * 获取指定元素的x轴的结束坐标
	 * @param webElement
	 * @return
	 */
	public int getEndXByElement(WebElement webElement) {
		return this.getStartXByElement(webElement) + this.getHeighByElement(webElement);
	}
	
	/**
	 * 获取指定元素的y轴的结束坐标
	 * @param webElement
	 * @return
	 */
	public int getEndYByElement(WebElement webElement) {
		return this.getStartYByElement(webElement) + this.getWidthByElement(webElement);
	}

}
