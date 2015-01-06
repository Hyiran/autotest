package crazy.seleiumTools;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
/**
 * 
 * @author xian_crazy QQ：330126160
 * @version 2014年10月30日  上午10:17:15
 * @see
 */
public class BrowersSize {
	private  Dimension screenDims;
	private int width;
	private int height;

	
	//浏览器最大化
public static void maxBrowers(WebDriver driver){
	    
	   driver.manage().window().maximize();
	    
	    }
	//得到显示器尺寸
	public  Dimension getScreenDims() {
		screenDims = Toolkit.getDefaultToolkit().getScreenSize();
	    //System.out.println(screenDims.getHeight());
	    //System.out.println(screenDims.getWidth());
		return screenDims;
	}
	
	//设置浏览器窗口大小
	public void setBrowserSize(WebDriver driver, int width, int height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.moveTo(0,0)");
		this.width = width;
		this.height = height;
		js.executeScript("window.resizeTo(" + this.width + "," + this.height+ ")");
	}
	
	public void setBrowserSize(WebDriver driver, Dimension dimension){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.moveTo(0,0)");
		this.width = (int) dimension.getWidth();
		this.height = (int) dimension.getHeight();
		js.executeScript("window.resizeTo(" + this.width + "," + this.height+ ")");
	}

}
