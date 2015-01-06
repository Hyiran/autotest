package crazy.seleiumTools;
/*
 * 作者：叶浩\西安-疯子\xian-crazy
 * github：https://github.com/xian-crazy/CrazyWebDriverBin.git
 */

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * 
 * @author xian_crazy QQ：330126160
 * @version 2014年10月30日  上午10:17:56
 * @see
 */
public class Webscroll {
	private WebDriver drr = null;

	public Webscroll(WebDriver driver) {
		this.drr = driver;
	}


	//滚动到指定元素位置 
	public void scrollToWebElement (WebElement element){
		
		((JavascriptExecutor) drr).executeScript("arguments[0].scrollIntoView();",element ); 
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	
	/**
	 * 如果element的id在页面中唯一，可以使用该方法进行滚动其偏移容器使element出现在视图中
	 *  @author xian_crazy QQ：330126160
	 */
	public void scrollToElement(WebElement element){
		JavascriptExecutor	jse=(JavascriptExecutor) drr;	
		
		String js="var el=document.getElementById(\""+element.getAttribute("id")+"\");"
				//定位到有滚动条的容器
				+ "var fdiv=el.offsetParent;"
				//如果容器可视高度小于el的偏移高度，则进行拖动容器滚动条
				+ "while(fdiv.offsetHeight<el.offsetTop+el.offsetHeight){"
				//滚动条向下滚动
				+ "fdiv.scrollTop=fdiv.scrollTop+el.offsetHeight;"
				//如果滚动条滚动到el出现，则停止滚动
				+ "if(fdiv.scrollTop+fdiv.offsetHeight-el.offsetHeight>el.offsetTop+el.offsetHeight){"
				+ "break;}"
				+ "}"
				;
		jse.executeScript(js);
	}
	
	public void scrollToTop (){
		JavascriptExecutor jse = (JavascriptExecutor)drr;
		jse.executeScript("window.scrollBy(0,0)", "");
 
	}
	
	
	
}
