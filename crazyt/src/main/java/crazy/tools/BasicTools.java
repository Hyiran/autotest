package crazy.tools;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author xian_crazy QQ：330126160
 * @version 2014年10月30日 上午10:18:16
 * @see
 */
public class BasicTools {

	private Dbtool dbtool;
	private WebDriver dr;
	private LoggerManager log=LoggerManager.getLogger(this.getClass());

	public BasicTools() {

	}

	public BasicTools(WebDriver driver, Dbtool dbtool) {
		this.dbtool = dbtool;
		this.dr = driver;

	}

	/**
	 * 从element中读取element的Xpath字符串
	 * 
	 * @param tablenameindb
	 * @param elementnameindb
	 * @param type
	 * @return
	 */
	public String getXpathOfElement(WebElement el) {

		String elstr = el.toString();
		String x = "-> xpath: ";
		int i = elstr.indexOf(x);
		
		String xpath = elstr.substring(i + x.length(), elstr.length() - 1);
		// this.pt("getXpathOfElement:" + xpath);
		return xpath;

	}

	/**
	 * 
	 * @param uiSelector
	 * @param waitTime
	 *            秒
	 * @param bool  true  表示必须在当前视图可见（宽值>0）  false找到但不一定可见
	 */
	public WebElement waitThenGetElement(String strSelector, int waitTime, SelectorType type,boolean bool) {
		WebElement element = null;
		int i = 0;
		
		
			
			boolean t1 = true;

			while (t1) {
				try {
					switch (type) {
					case UiSelector:
					element = ((AndroidDriver) dr).findElementByAndroidUIAutomator(strSelector);
					break;
					case xPath:
						element = dr.findElements(By.xpath(strSelector)).get(0);
					
						break;
					case CSS:
						element = dr.findElements(By.cssSelector(strSelector)).get(0);
						break;
					default:
						this.pt("waitThenGetElementappear类型错误");
					}
			
					TryForWaitReturn ret=tryForWait(element, t1,bool,i,waitTime);;
					t1=ret.getT();
					element=ret.getEl();
		
					
				} catch (Throwable e) {
					if (catchForWait(i, waitTime,false)) {
						break;
					}
				} finally {
					i++;
				}
			}

		// Assert.assertNotNull(element);

		return element;

	}
	/**
	 * tryForWait 方法返回类型
	 * @author xian_crazy QQ：330126160
	 * @version 2014年11月25日  上午10:07:10
	 * @see
	 */
	class TryForWaitReturn{
	 private	boolean t;
	 private	WebElement el;
	public boolean getT() {
		return t;
	}
	public void setT(boolean t) {
		this.t = t;
	}
	public WebElement getEl() {
		return el;
	}
	public void setEl(WebElement el) {
		this.el = el;
	}
	}
	
	

	/**
	 * 判断页面元素是否在当前视图显示
	 * @see
	 * @param element
	 * @param t
	 * @param bool
	 * @param i
	 * @param waitTime
	 * @return
	 */
	private TryForWaitReturn tryForWait(WebElement element, boolean t,boolean bool,int i,int waitTime) {
		int w = element.getSize().getWidth();
		int h=element.getSize().getHeight();

		TryForWaitReturn ret=new TryForWaitReturn();
		

 
		//元素宽度大于零 并且 bool=true 既元素在要在当前视图显示
		if (w > 0 && bool) {
			t = false;
			// this.pt(" get it！");
		}
		//元素宽度大于或者等于零 并且 bool=false 既元素不一定在当前视图显示
		if (w >= 0 && !bool) {
			t = false;
			
			// this.pt(" get it！");
			if (w == 0) {
				this.pt("找到元素但未在当前视图显示");
			}
		}

		//t=false时，说明元素定位到，则打印以下信息
		if(!t){
			this.pt(" get its H&&W :" +h  + "&&" + w);
			this.pt(" get its point :" + element.getLocation().getX() + "&&" + element.getLocation().getY());
			ret.setT(t);
			ret.setEl(element);
			return ret;
		//t=true时，说明元素找到但w=0，但 bool=true需要等待其在当前视图显示出来,如果超时则认为未找到
		}else{
			
			if (catchForWait(i, waitTime,true)) {
				t = false;	
				element=null;
				ret.setT(t);
				ret.setEl(element);
			}
		
			return ret;
		}
		
	}

	/**
	 * 
	 * @see
	 * @param i
	 * @param waitTime
	 * @param isfindel   true 表示已经找到元素  false 表示没有找到元素
	 * @return
	 */
	private boolean catchForWait(int i, int waitTime, boolean isfindel) {
		if (i == 0) {
			this.pt("等待" + waitTime + "秒……" );
			System.out.print(i);
		} else if (i == waitTime) {
			System.out.print(" " + i);
			if(!isfindel){
				this.pt(i+"秒后，定位失败");
			}else{
				this.pt(i+"秒后，已经找到，但其宽度为0");
			}
			
			return true;
		} else {
			System.out.print(" " + i);
		}
		
		this.threadSleep(1);
		return false;
	}

	/**
	 * 
	 * @param uiSelector
	 *            选择器结果为List
	 * @param waitTime
	 *            秒
	 * @waitElement_S_appear appear说明当前元素在当前页面通过选择器可以找到
	 * @param bool  true 表示元素的宽大于0  false 表示存在元素但是不一定在当前视图
	 */
	
	
	
	public List<WebElement> waitThenGetElement_S(String strSelector, int waitTime, SelectorType type,boolean bool) {
		List<WebElement> list = null;
		int i = 0;

			boolean t1 = true;
			while (t1) {
				try {
					
					switch (type) {
					case UiSelector:
					list = ((AndroidDriver) dr).findElementsByAndroidUIAutomator(strSelector);
					break;
					case xPath:
						
						list = dr.findElements(By.xpath(strSelector));
						
						break;
					default:
						this.pt("waitThenElement_S_appear类型错误");
					}
				
					WebElement element = list.get(0);
					TryForWaitReturn ret=tryForWait(element, t1,bool,i,waitTime);;
					t1=ret.getT();
					
					
				} catch (Throwable e) {
					if (catchForWait(i, waitTime,false)) {
						break;
					}
				} finally {
					i++;
				}
			}	
	
		return list;
	}

	private WebElement getElBySelectorInDB(String tablenameindb, String elementnameindb, SelectorType type, int timeout,boolean bool){
		
		
		WebElement webElement = null;
			this.pt("========"+ tablenameindb+ "  "+elementnameindb + "========开始定位===============================");
			String selector = this.getselectorFromdb(tablenameindb, elementnameindb, type).trim();
			this.pt("定位的元素"+type+"为：" + selector);
			webElement = this.waitThenGetElement(selector, timeout, type,bool);
			this.pt("========== "+elementnameindb + "============定位完毕===============================");
		return webElement;
	}
	
	
	
	
	
	public WebElement getElement(String tablenameindb, String elementnameindb, SelectorType type, int timeout) {
		return this.getElBySelectorInDB(tablenameindb, elementnameindb, type, timeout, true);
	}

	public WebElement findElement(String tablenameindb, String elementnameindb, SelectorType type, int timeout) {
		return this.getElBySelectorInDB(tablenameindb, elementnameindb, type, timeout, false);
	}

	public WebElement getWebElementByReplaceSelectorOfindb(String tablenameindb, String elementnameindb, SelectorType type, String valueOfvar, int timeout) {
		String newselector = this.getselectorFromdb(tablenameindb, elementnameindb, type).replace("%var%", valueOfvar);
		return this.getElementBynewselctor(elementnameindb, type, newselector, timeout);
	}
	public List<WebElement> getWebElementsByReplaceSelectorOfindb(String tablenameindb, String elementnameindb, SelectorType type, String valueOfvar, int timeout) {
		String newselector = this.getselectorFromdb(tablenameindb, elementnameindb, type).replace("%var%", valueOfvar);
		return this.getElementsBynewselctor(elementnameindb, type, newselector, timeout);
	}

	/**
	 * 
	 * @see
	 * @param elementname  自定义的元素明，只做为log中描述使用，无实际意义
	 * @param type
	 * @param newselctor  代码中编写新的selector
	 * @param timeout
	 * @return
	 */
	public WebElement getElementBynewselctor(String elementname, SelectorType type, String newselctor, int timeout) {
		WebElement webElement = null;
		// int timeout = 50;
		String selector = newselctor;
		this.pt("========" + elementname + "========开始定位===============================");
		this.pt("定位的元素new  "+type+"为：" + selector);
		webElement = this.waitThenGetElement(selector, timeout, type,true);
		this.pt("============" + elementname + "==========定位完毕===============================");
		return webElement;
	}
	
	/**
	 * 
	 * @see
	 * @param elementname  自定义的元素明，只做为log中描述使用，无实际意义
	 * @param type
	 * @param newselctor  代码中编写新的selector
	 * @param timeout
	 * @return
	 */
	public List<WebElement> getElementsBynewselctor(String elementname, SelectorType type, String newselctor, int timeout) {
		List<WebElement> list = null;
		// int timeout = 50;
		String selector = newselctor;
		this.pt("========" + elementname + "========开始定位===============================");
		this.pt("定位的元素集new  "+type+"为：" + selector);
		list = this.waitThenGetElement_S(selector, timeout, type,true);
		this.pt("============" + elementname + "==========定位完毕===============================");
		return list;
	}
	
	
	
	private List<WebElement> getEl_s_BySelectorInDB(String tablenameindb, String elementnameindb, SelectorType type, int timeout,boolean bool){
		
		String selector = this.getselectorFromdb(tablenameindb, elementnameindb, type).trim();
		List<WebElement> list = null;
			this.pt("========" + elementnameindb + "========开始定位===============================");
			this.pt("定位的元素集"+type+"为：" + selector);
			list = this.waitThenGetElement_S(selector, timeout, type,bool);
			this.pt("===========" + elementnameindb + "===========定位完毕===============================");
		return list;
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
	public List<WebElement> getElements(String tablenameindb, String elementnameindb, SelectorType type, int timeout) {
		return this.getEl_s_BySelectorInDB(tablenameindb, elementnameindb, type, timeout, true);
	}

	/**
	 * 
	 * 
	 * @param uiSelector
	 */
	public List<WebElement> getElementsBynewSelector(String elementname, SelectorType type, String newselector, int timeout) {

		// int timeout = 50;
		String selector = newselector;
		List<WebElement> list = null;
			this.pt("========" + elementname + "========开始定位===============================");
			this.pt("定位的元素new "+type+"为：" + selector);
			list = this.waitThenGetElement_S(selector, timeout, type,true);
			this.pt("===========" + elementname + "===========定位完毕===============================");
		

		return list;

	}

	// =====================================================================================

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 *            随机数字长度
	 * @param type
	 *            等于1为 纯数字 不等于1 数字+字母
	 * @return 返回字符串
	 */
	public String getRandomString(int length, int type) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		String base2 = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		if (type == 1) {
			for (int i = 0; i < length; i++) {
				int number = random.nextInt(base2.length());
				sb.append(base2.charAt(number));
			}
		} else {
			for (int i = 0; i < length; i++) {
				int number = random.nextInt(base.length());
				sb.append(base.charAt(number));
			}
		}
		this.pt("获取到的" + length + "位字符串为： " + sb.toString());
		return sb.toString();
	}

	/**
	 * 初始化测试目录table
	 * 
	 * @param filename
	 *            filename<br/>
	 *            如果存在则不创建，不存在则创建
	 * 
	 */
	public void setTable(String filename) {

		new ExcelByjxlTool("\\testData\\" + filename);
	}

	/**
	 * 等待固定时间，并将等待读秒打印到控制台
	 * 
	 * @param waitTime
	 */
	public void waitAndPrintTime(int waitTime) {
		System.out.print("共等待" + waitTime + "秒  开始等待：");
		for (int i = 1; i <= waitTime; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(i + "s  ");
		}
		this.pt("等待结束");
	}

	/**
	 * 元素选择器类型uiSelector, xPath;
	 * 
	 * @author Administrator
	 * 
	 */
	public enum SelectorType {
		UiSelector, xPath, CSS;
	}

	/**
	 * 控制台及log文件打印文字
	 * 
	 */
	public void pt(String str) {
		//System.out.println(str);
		log.info(str);
	}
	
	/**
	 * testngReporter打印文字
	 * 控制台及log文件打印文字
	 */
	public void rpt(String str) {
		//System.out.println(str);
		log.info2logAndReporter(str);
	}

	/**
	 * 生成随机中文字符
	 * 
	 * @param i
	 *            字符个数
	 * @return
	 * @throws Exception
	 */
	public String createname(int i) {
		String str = "";
		int hightPos, lowPos; // 定义高低位
		Random random = new Random();
		for (int j = 1; j <= i; j++) {
			hightPos = (176 + Math.abs(random.nextInt(39)));// 获取高位值
			lowPos = (161 + Math.abs(random.nextInt(93)));// 获取低位值
			byte[] b = new byte[2];
			b[0] = (new Integer(hightPos).byteValue());
			b[1] = (new Integer(lowPos).byteValue());
			try {
				str = str + new String(b, "GBK");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 转成中文
		}
		// System.err.println(str);
		return str;
	}

	/**
	 * 生成指定范围的随机整数
	 */
	public int getRandomInt(int min, int max) {
		Random random = new Random();
		if (min == max) {
			return min;
		} else {
			return random.nextInt(max) % (max - min + 1) + min;
			// this.pt(s);
		}
	}

	/**
	 * 获取当前时间
	 * 
	 * @param i
	 *            1 MM-dd HH:mm:ss <br/>
	 *            2 yy-MM-dd HH:mm <br/>
	 *            3 yy-MM-dd <br/>
	 *            other: yy-MM-dd HH:mm：ss
	 * @return 返回格式化好的字符串
	 */
	public String nowTime(int i) {
		String result;
		SimpleDateFormat df;
		switch (i) {
		case 1:
			df = new SimpleDateFormat("MM-dd HH:mm:ss");// 设置日期格式
			result = df.format(new Date());// new Date()为获取当前系统时间
			break;
		case 2:
			df = new SimpleDateFormat("yy-MM-dd HH:mm");// 设置日期格式
			result = df.format(new Date());// new Date()为获取当前系统时间
			break;
		case 3:
			df = new SimpleDateFormat("yy-MM-dd");// 设置日期格式
			result = df.format(new Date());// new Date()为获取当前系统时间
			break;
		default:
			df = new SimpleDateFormat("yy-MM-dd HH:mm：ss");// 设置日期格式
			result = df.format(new Date());// new Date()为获取当前系统时间

		}

		return result;

	}

	/**
	 * driver sleep secends s单位为秒
	 */
	public void threadSleep(int s) {
		int sec = s * 1000;
		try {
			Thread.sleep(sec);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			this.pt("Thread.sleep异常");
		}
	}

	/**
	 * 截图
	 * 
	 * @param driver
	 */
	public void screenShot(WebDriver driver) {
		String dir_name = "screenshot";
		if (!(new File(dir_name).isDirectory())) {
			new File(dir_name).mkdir();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String time = sdf.format(new Date());
		try {
			File source_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // 执行屏幕截图
			FileUtils.copyFile(source_file, new File(dir_name + File.separator + time + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * cmd命令
	 */
	public void cmd( String commands) {

	
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(commands);
			p.waitFor();
		} catch (Exception e) {

			e.printStackTrace();
			// super.pt(p.toString());
		} finally {

		}

	}

	/**
	 * 
	 * @see
	 * @param filename
	 * @return System.getProperty("user.dir") + "\\src\\test\\resources\\" + filename;
	 */
	public String getResourcesURL(String filename) {
		return System.getProperty("user.dir") + "\\src\\test\\resources\\" + filename;
	}

	/**
	 * 从数据库中读取element的选择器字符串
	 * 
	 * @param tablenameindb
	 * @param elementnameindb
	 * @param type
	 * @return
	 */
	public String getselectorFromdb(String tablenameindb, String elementnameindb, SelectorType type) {
		String valuename = type.toString();
		return this.dbtool.getValuebyKey(valuename, tablenameindb, "elementname", elementnameindb);
	
	}

}
