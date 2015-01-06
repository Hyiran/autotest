package crazy.tools;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;


/**
 * @author xian_crazy QQ：330126160
 * @version 2014年11月7日  上午8:50:17
 * @see
 */
/**
 * 用一个类扩展web driver自带的事件监听器，可以实现许多有趣的功能。 比如自动log a customer event listener
 */
public class LogEventListener implements WebDriverEventListener {
	//private Log log = LogFactory.getLog(this.getClass());

	//private LoggerManager log=LoggerManager.getLogger(this.getClass());

	//private String originalValue;
	private BasicTools btool=new BasicTools();

	public void beforeNavigateTo(String url, WebDriver selenium) {
		//log.info("navigating to:'" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver selenium) {
		//originalValue = element.getAttribute("value");
	}

	public void afterChangeValueOf(WebElement element, WebDriver selenium) {
		//log.info("changing value " + originalValue + " from '" + originalValue + "' to '" + element.getAttribute("value") + "'");
	}



	public void onException(Throwable error, WebDriver driver) {
//		if (error.getClass().equals(NoSuchElementException.class)) {
//			log.error("error: Element not found " + originalValue);
//		} else {
//			log.error("error:"+error.toString());
//		}

		Throwable cause = error.getCause();
//		 cause instanceof ScreenshotException
		if (cause instanceof WebDriverException) {
			System.out.println(driver.toString());
			btool.screenShot(driver);

		}
	}

	public void beforeNavigateBack(WebDriver selenium) {
	}

	public void beforeNavigateForward(WebDriver selenium) {
	}

	public void beforeClickOn(WebElement element, WebDriver selenium) {
	}

	public void beforeScript(String script, WebDriver selenium) {
	}

	public void afterClickOn(WebElement element, WebDriver selenium) {
		
	}

	public void afterFindBy(By by, WebElement element, WebDriver selenium) {
	}

	public void afterNavigateBack(WebDriver selenium) {
	}

	public void afterNavigateForward(WebDriver selenium) {
	}

	public void afterNavigateTo(String url, WebDriver selenium) {
	}

	public void afterScript(String script, WebDriver selenium) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeFindBy
	 * (org.openqa.selenium.By, org.openqa.selenium.WebElement,
	 * org.openqa.selenium.WebDriver)
	 */
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

}
