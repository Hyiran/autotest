package crazy.seleiumTools;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import crazy.tools.BasicTools;
import crazy.tools.BasicTools.SelectorType;
import crazy.tools.Dbtool;
import crazy.tools.FileAccess;

public class WebBasicPage  {
	
	public BasicTools btool;
	public WebTools wtool;
	public Sikuli s=new Sikuli();
	private WebDriver driver;
	private String tablenameindb = this.getClass().getSimpleName();

	public WebBasicPage(WebDriver wdriver, Dbtool dbtool) {
		this.btool=new BasicTools(wdriver, dbtool);
		this.wtool=new WebTools(wdriver,dbtool);
		this.driver=wdriver;
		//this.btool.pt("WebBasicPage:  "+tablenameindb);
	}
	
	/**
	 * 找到但不一定可见
	 * @see
	 * @param elementnameindb
	 * @return
	 */
	public WebElement findWebElement(String elementnameindb) {

		return this.btool.findElement(tablenameindb, elementnameindb,
				SelectorType.xPath,30);
	}
	
	public WebElement getWebElementInTime(String elementnameindb,int time) {

		return this.wtool.getWebElement(tablenameindb, elementnameindb,
				SelectorType.xPath,time);
	}

	public WebElement getWebElement(String elementnameindb) {

		return this.wtool.getWebElement(tablenameindb, elementnameindb,
				SelectorType.xPath,30);
	}
	
	public WebBasicPage setTablenameindb(String tablenameindb) {
		this.tablenameindb = tablenameindb;
		return this;
	}
	

	public String getTablenameindb() {
		return tablenameindb;
	}

	public WebElement getWebElementByupdateXpath(String elementnameindb,String valueOfvar) {
		return
this.wtool.getWebElementByReplaceSelectorOfindb(tablenameindb, elementnameindb, SelectorType.xPath, valueOfvar);
		 
	}
	
	
	public List<WebElement> getWebElementsByupdateXpath(String elementnameindb,String valueOfvar) {
		return
this.wtool.getWebElementsByReplaceSelectorOfindb(tablenameindb, elementnameindb, SelectorType.xPath, valueOfvar,10);
		 
	}
	
	public WebElement getWebElementByCSS(String elementnameindb) {

		return this.wtool.getWebElement(tablenameindb, elementnameindb,
				SelectorType.CSS,30);
	}
	/**
	 * 
	 * @see
	 * @param element
	 * @return 无论是否可见，均返回text(不包含子节点);
	 */
	public String getWebElementTextBy160SpaceSplit(String elmentNameInDB) {
		
		return this.getWebElementTextBy160SpaceSplit(this.findWebElement(elmentNameInDB)).get(0);
	}
	/**
	 * 元素及子元素以160空格结尾时适用
	 * @see
	 * @param element
	 * @return 无论是否可见，均返回textList 并去掉空格  下标从元素根节点开始递增;
	 */
	public List<String> getWebElementTextBy160SpaceSplit(WebElement elment) {
		String str=((String) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].textContent;", elment))+" ";
		
		List<String> list=Arrays.asList(str.split((char)160+""));
		for(String str1:list){
			list.set(list.indexOf(str1), str1.trim());
		}
		return list;
	}
	
	
	/**
	 *
	 * @see
	 * @param element
	 * @return 无论是否可见，均返回textList 并去掉空格  下标从元素根节点开始递增;
	 */
	public List<String> getWebElementText(WebElement elment) {
		String str=elment.getText();
		str.replace((char)160+"", "");
		List<String> list=Arrays.asList(str.split("\n"));
		for(String str1:list){
			list.set(list.indexOf(str1), str1.trim());
		}
		return list;
	}

	/**
	 * 
	 * @see
	 * @param elementname  自定义的元素名，只做为log中描述使用，无实际意义
	 * @param type
	 * @param newselctor  代码中编写新的selector
	 * @param timeout
	 * @return
	 */
	public WebElement getWebElementBynewselctor(String elementname,
			SelectorType type, String newselctor) {
		return this.wtool.getElementBynewselctor(elementname, type,
				newselctor,30);
	}
	
	/**
	 * 
	 * @see
	 * @param elementname  自定义的元素名，只做为log中描述使用，无实际意义
	 * @param type
	 * @param newselctor  代码中编写新的selector
	 * @return
	 */
	public WebElement getWebElementBynewselctorInTime(String elementname,
			SelectorType type, String newselctor,int waitOutTime) {
		return this.wtool.getElementBynewselctor(elementname, type,
				newselctor,waitOutTime);
	}
	
	public List<WebElement> getWebElementsBynewselctorInTime(String elementname,
			SelectorType type, String newselctor,int waitOutTime) {
		return this.wtool.getElementsBynewselctor(elementname, type,
				newselctor,waitOutTime);
	}

	public List<WebElement> getWebElements(String elementnameindb) {
		return this.wtool
				.getWebElements(tablenameindb, elementnameindb, SelectorType.xPath);
	}
	
	

	/**
	 * 在指定时间内返回List 找不到或者超时则返回NULL
	 * @see
	 * @param elementnameindb
	 * @param waitTimeOut
	 * @return
	 */
	public List<WebElement> getWebElementsInTime(String elementnameindb,int waitTimeOut) {
		return this.wtool
				.getElements(tablenameindb, elementnameindb, SelectorType.xPath,waitTimeOut);
	}
	
	/**
	 * 获取List中的随机Webelement start表示list中随机范围起始下标值,end 表示随机范围结束下标
	 * ，如果end大于list最大下标，取最大下标
	 * @see
	 * @param list
	 * @param i
	 * @return
	 */
	public WebElement  getRandomWebElementFromList(List<WebElement> list, int start,int end){
		WebElement el=this.wtool.getRandomWebElementFromList(list, start,end);
		this.btool.pt("point:="+el.getLocation().getX()+ "  "+el.getLocation().getY());
		
		return el;
	}
	
	/**
	 * 
	 * @see
	 * @param list
	 * @return
	 */
	public WebElement  getLastWebElementFromList(List<WebElement> list){
		WebElement el=this.wtool.getLastWebElementFromList(list);
		this.btool.pt("point:="+el.getLocation().getX()+ "  "+el.getLocation().getY());
		
		return el;
	}

	public void pt(String str) {
		this.btool.pt(str);
	}

	public Select getSelectElement(String selectElementnameindb) {
		return this.wtool.getDrop_DownList(tablenameindb, selectElementnameindb,
				SelectorType.xPath);
	}
	
	/**
	 * select 下拉菜单选项设置
	 */
	public void setSelectVaule(String selectElementnameindb,String selectByVisibleText){
		this.getSelectElement(selectElementnameindb).selectByVisibleText(selectByVisibleText);
		
		WebElement el=this.getWebElement(selectElementnameindb);
		String jsstr="var el=arguments[0];"
				+ "var index = el.selectedIndex;"
				+ "var text = el.options[index].text;"
				+ "return text;";
		
		this.btool.rpt("下拉项：【"+selectElementnameindb+"】被设置为：【"+this.wtool.runjs(jsstr,el)+"】");
	}
	

	/**
	 * 通过js获取页面元素的title
	 * 元素必须存在ID 才可以使用本方法;
	 */
		public String getEltitle(WebElement el){
			String value = null;
			String jsstr="var el=arguments[0];"
					+ "var newel = document.getElementById(el.id);"
					+"var value=newel.title;"
					+ "return value;";	
			value=this.wtool.runjs(jsstr, el);	
			//FileAccess.saveAsFileWriter(this.driver.getPageSource(),new File("E:\\123.HTML"));
			return value;
		}
		
		

	/**
	 * 
	 * @param elementnameindb  下拉菜单的选择器元素名
	 * @param i   list下标随机选择范围起始值
	 * @return
	 */
	public WebElement getRandomWebElementFromDrop_DownList(String elementnameindb,int i){
		List<WebElement> list=this.getSelectElement(elementnameindb).getOptions();
			return this.wtool.getRandomWebElementFromList(list, i,list.size()-1);
		}
	
	/**
	 * 跳转到新的窗口
	 * @see
	 * @param newWindowsTitle
	 */
	public boolean toNewWindws(String newWindowsTitle){
		this.btool.rpt("跳转到新页面："+newWindowsTitle);
		return this.wtool.toNewWindows(newWindowsTitle);
	}
	
	/**
	 * 跳转iframe(适用于页面只有唯一的iframe情况)  
	 * By.xpath("//iframe")
	 * @see
	 * @return
	 */
	public void toiFrame(){
		this.wtool.toNewFrame(this.driver.findElement(By.xpath("//iframe")));
	}
	
	/**
	 * this.dr.switchTo().defaultContent();
	 * @see
	 */
	public void toDefaultFrame() {
		this.wtool.toDefaultFrame();
	}
	


	public WebBasicPage scrollertoWebElementAndClick(String elementnameiindb){
		this.wtool.scrollerToElement(this.getWebElement(elementnameiindb)).click();
		return this;
	}
	
	
	
	public WebBasicPage editInputElement(String elementnameindb,String inputstr){
		this.btool.rpt("先清空"+elementnameindb+"; 在输入: "+inputstr);
		WebElement el=this.getWebElement(elementnameindb);
		el.clear();
		el.sendKeys(inputstr);
		return this;
	}
	
	/**
	 * "清空"+elDescription+"; 在输入: "+inputstr
	 * @see
	 * @param el
	 * @param elDescription
	 * @param inputstr
	 * @return
	 */
	public WebBasicPage editInputElement(WebElement el,String elDescription,String inputstr){
		this.btool.rpt("清空"+elDescription+"; 在输入: "+inputstr);

		el.clear();
		el.sendKeys(inputstr);
		return this;
	}
	
	public WebBasicPage editInputSecret(String elementnameindb,String inputstr){
		this.btool.rpt("先清空"+elementnameindb+"; 在输入:********** ");
		WebElement el=this.getWebElement(elementnameindb);
		el.clear();
		el.sendKeys(inputstr);
		return this;
	}
	
	/**
	 * 
	 * @param elementnameindb  List选择器元素名
	 * @param i   list下标随机选择范围起始值
	 * @return
	 */
	public WebBasicPage clickRandomWebElementFromList(String listElementnameindb,int i){
		this.btool.rpt("点击元素集："+listElementnameindb+"  中从第"+(i+1)+"个开始的随机元素");
		List<WebElement> list=this.getWebElements(listElementnameindb);
		try{
		WebElement el=this.getRandomWebElementFromList(list, i,list.size()-1);
		while(!el.isDisplayed()){
			this.threadSleep(1);
		}
		el.click();
		}catch(TimeoutException e){
			 btool.rpt(" 页面加载超时"+BrowsersDriverPro.getPageLoadTimeout()+"秒，  不等待加载完毕，继续执行");
		}
		return  this;
	}
	
	/**
	 * 右键点击页面元素返回右键点击后需要找到的元素
	 */
	public WebElement rightClick2anotherEl(String elementNameInDB, String anotherElNameInDb){
		this.btool.rpt("右键点击["+elementNameInDB+"] 找到["+anotherElNameInDb+"]");
		WebElement nel=null;
		for(int i=0;i<5 && nel==null;i++ ){
		this.wtool.a.contextClick(this.getWebElement(elementNameInDB)).perform();
		nel=this.getWebElementInTime(anotherElNameInDb,2);
		}
		return nel;
	}
	
	/**
	 * 获取List中的随机Webelement start表示list中随机范围起始下标值,end 表示随机范围结束下标
	 * ，如果end大于list最大下标，取最大下标
	 * @see
	 * @param listElementnameindb
	 * @param start
	 * @param end
	 * @return
	 */
	public WebBasicPage clickRandomWebElementFromPartOfList(String listElementnameindb,int start,int end){
		this.btool.rpt("点击元素集:"+listElementnameindb+"中的第"+(start+1)+"个到第"+(end+1)+"个间的任意一个");
		try{
		this.getRandomWebElementFromList(this.getWebElements(listElementnameindb), start,end).click();
		}catch(TimeoutException e){
			 btool.rpt(" 页面加载超时"+BrowsersDriverPro.getPageLoadTimeout()+"秒，  不等待加载完毕，继续执行");
		}
		return  this;
	}
	
	/**
	 * 获取List中的随机Webelement start表示list中随机范围起始下标值,end 表示随机范围结束下标
	 * ，如果end大于list最大下标，取最大下标
	 * @see
	 * @param listElementnameindb
	 * @param start
	 * @param end
	 * @return
	 */
	public WebBasicPage clickRandomWebElementFromPartOfList(List<WebElement> list,int start,int end,String listDescription){
		this.btool.rpt("点击元素集:"+listDescription+"中的第"+(start+1)+"个到第"+(end+1)+"个间的任意一个");
		try{
		this.getRandomWebElementFromList(list, start,end).click();
		}catch(TimeoutException e){
			 btool.rpt(" 页面加载超时"+BrowsersDriverPro.getPageLoadTimeout()+"秒，  不等待加载完毕，继续执行");
		}
		return  this;
	}
	
	public WebBasicPage  clickWebElement(String elementnameindb){
		this.btool.rpt("点击："+elementnameindb);
		try{
		this.getWebElement(elementnameindb).click();
//		Point p =MouseInfo.getPointerInfo().getLocation();
//		this.btool.pt(p.getX()+"  "+p.getY());
		}catch(TimeoutException e){
			 btool.rpt(" 页面加载超时"+BrowsersDriverPro.getPageLoadTimeout()+"秒，  不等待加载完毕，继续执行");
		}
		return  this;
	}
	/**
	 * 查找el并点击el，2秒内等待newel出现，如果不出现循环查找5次，任然查找不到返回newl=null;
	 * @see
	 * @param elementnameindb
	 * @param newelNameindb
	 * @return
	 */
	public WebElement  clickEl4GetNewEL(String elementnameindb,String newelNameindb){
		this.btool.rpt("点击："+elementnameindb+"  等待出现："+newelNameindb);
		boolean t=true;
		WebElement el=null;
		for(int i=0;i<5&&t;i++){
		try{
		this.getWebElement(elementnameindb).click();
		}catch(TimeoutException e){
			 btool.rpt(" 页面加载超时"+BrowsersDriverPro.getPageLoadTimeout()+"秒，  不等待加载完毕，继续执行");
		}
		el=this.getWebElementInTime(newelNameindb, 2);
		if(el!=null){
			this.btool.rpt(newelNameindb+"-----已经出现");
			t=false;
			this.wtool.a.moveToElement(el).perform();
		};
		}
		return  el;
	}
	/**
	 * 点击el，并将点击动作记录到报告日志
	 * @see
	 * @param element
	 * @param elDescription
	 * @return
	 */
	public WebBasicPage  clickWebElement(WebElement element,String elDescription){
		this.btool.rpt("点击："+elDescription);
		try{
			element.click();
			//this.wtool.clickTheLeftMouseButton(element);
		}catch(TimeoutException e){			
			 btool.rpt(" 页面加载超时"+BrowsersDriverPro.getPageLoadTimeout()+"秒，  不等待加载完毕，继续执行");
		}
		return  this;
	}
	
	/**
	 * 
	 * @param elementnameindb  下拉菜单的选择器元素名
	 * @param i   list下标
	 * @return
	 */
	public WebBasicPage clickoneOfWebElementFromList(String listElementnameindb,int i){
		this.btool.rpt("点击元素集："+listElementnameindb+"  中的第"+(i+1)+"个");
		this.getWebElements(listElementnameindb).get(i).click();
		return  this;
	}
	
	

	public  WebBasicPage clickfileUploadElementThenUploadByAutoItforfireFox_64(String uploadElementNameIndb,String fileallURL) {
		 btool.rpt("点击文件上传按钮上传文件");
		
			this.wtool.clickfileUploadElementThenUploadByAutoItforfireFox_64(this.getWebElement(uploadElementNameIndb),fileallURL);
	
		return this;
	}
	
	public WebBasicPage clickWebElementOfReplaceXpath(String elementnameindb, String valueOfvar){
		this.btool.rpt("点击："+elementnameindb);
		this.wtool.getWebElementByReplaceSelectorOfindb(tablenameindb, elementnameindb, SelectorType.xPath, valueOfvar).click();
		return this;
	}
	/**
	 * 
	 * @see
	 * @param elementnameOfMy97DatePickerindb
	 * @param i 0 默认时间 
	 * @return
	 */
	public WebBasicPage setDate_use_My97DatePicker(String toBeSetELNameInDB, int i){
		this.clickWebElement(toBeSetELNameInDB);
		this.wtool.toNewFrame(this.driver.findElement(By.xpath("//iframe[contains(@src,'My97DatePicker')]")));
		switch(i){
		case 0:
			this.driver.findElement(By.xpath("//input[@id='dpOkInput']")).click();
			break;
		default:
			 btool.rpt("传参错误");
		}
		this.wtool.toDefaultFrame();				
		return this;		
	}
	
	/**
	 * 
	 * @see
	 * @param elementnameOfMy97DatePickerindb
	 * @param i 0 默认时间 
	 * @return
	 */
	public WebBasicPage setDate_use_onlyiframe(String toBeSetELNameInDB, int month,int year,int day){
		WebElement frame=null;
		for(int i=0;i<3&&frame==null;i++){
		this.clickWebElement(toBeSetELNameInDB);
		frame=this.getWebElementBynewselctorInTime("时间控件frame", SelectorType.xPath, "//iframe[not(@id)]",2);
		}
		WebElement month1=null;
		for(int i=0;i<3 && month1==null;i++){
		this.wtool.toNewFrame(frame);
		month1=this.btool.waitThenGetElement("//div[@id='dpTitle']/div[3]/input[@class='yminput']", 2, SelectorType.xPath, true);
		}//设置月
		
		this.clickWebElement(month1, "月编辑框").editInputElement(month1, "", month+"");

		
		//设置年
		WebElement year1=this.driver.findElement(By.xpath("//div[@id='dpTitle']/div[4]/input[@class='yminput']"));
		this.clickWebElement(year1, "年编辑框").editInputElement(year1, "", year+"");

		
		//点击星期栏消除下拉菜单
		this.wtool.a.moveToElement(this.driver.findElement(By.xpath("//table/tbody/tr[@class='MTitle']"))
				, 5, 5).click().perform();
		
		
		WebElement day1=null;
		//设置日期
		this.btool.rpt("日期设置为："+day);
		if(day<=15){
		 day1=this.driver.findElements(By.xpath("//td[text()="+day+"]")).get(0);
		}else{
			day1=this.driver.findElements(By.xpath("//tbody/descendant::td[text()="+day+"][last()]")).get(0);
		}
		
		//如果当前日期未被选择，则点击选择
		if(0>day1.getAttribute("onmouseout").indexOf("Wselday"))
			day1.click();
		
		//判断点击日期后是否关闭控件，如果点击日点击确定
		WebElement ok=this.btool.waitThenGetElement("//input[contains(@id,'OkInput')]", 2, SelectorType.xPath, true);
		if(ok!=null){
			ok.click();
		}
		
			
		this.wtool.toDefaultFrame();				
		return this;		
	}
	
	public String getWebElementValue(String elementnameindb){
		
		return this.getWebElement(elementnameindb).getAttribute("value");
	}
	
	/**
	 * 通过点击页面元素，获取新的页面元素 超时时间3秒
	 * @see
	 * @param oldWebElnameindb
	 * @param newWebElNameInDB
	 * @param i 如果新元素没有定位到，重复点击oldel次数
	 * @return
	 */
	public WebElement getNewWebElByClickOldWebEl(String oldWebElnameindb,String newWebElNameInDB,int i){
		WebElement newEL=null;
		for(int j=0;j<i&&newEL==null;j++){
			this.clickWebElement(oldWebElnameindb);
			newEL=this.getWebElementInTime(newWebElNameInDB, 3);
		}
		return newEL;
	}

	/**
	 * @see
	 * @param i
	 */
	public WebBasicPage threadSleep(int i) {
		this.btool.threadSleep(i);
		return this;
		
	}
	
	/**
	 * 如果element的id在页面中唯一，可以使用该方法进行滚动其偏移容器使element出现在视图中;<br/>
	 * "鼠标移动到"+whereElNameIndb+"并滚动直到"+elDescription+"出现在当前视图"
	 * 
	 */
	public WebElement rollElToCurrentPage(WebElement el,String whereElNameIndb,String elDescription){
		this.btool.rpt("鼠标移动到"+whereElNameIndb+"并滚动直到"+elDescription+"出现在当前视图");
		WebElement whereEl=this.getWebElement(whereElNameIndb);
		this.wtool.a.moveToElement(whereEl).sendKeys(Keys.ENTER).build().perform();
		this.wtool.webscroll.scrollToElement(el);
		return el;
	}
	
	/**
	 * 如果element的id在页面中唯一，可以使用该方法进行滚动其偏移容器使element出现在视图中;<br/>
	 * "鼠标移动到"+whereEl+"并滚动直到"+el+"出现在当前视图"
	 * 
	 */
	public WebElement rollElToCurrentPage(WebElement el,WebElement whereEl){
		this.wtool.a.moveToElement(whereEl).sendKeys(Keys.ENTER).build().perform();
		
		this.wtool.webscroll.scrollToElement(el);
		return el;
	}
	
	/**
	 * 如果element的id在页面中唯一，可以使用该方法进行滚动其偏移容器使element出现在视图中;<br/>
	 * "鼠标移动到"+whereEl+"并滚动直到"+el+"出现在当前视图"
	 * 
	 */
	public WebElement rollElToCurrentPage(String elID,WebElement whereEl){
		this.wtool.a.moveToElement(whereEl).sendKeys(Keys.ENTER).build().perform();
		WebElement el=this.driver.findElement(By.id(elID));
		this.wtool.webscroll.scrollToElement(el);
		return el;
	}
	
	/**
	 * 移动鼠标到父元素中心后点击，鼠标悬停，随后找到子元素点击
	 * @see
	 */
	public WebBasicPage hovermouseFornewElementThenClick(String elNameIndb, String subElementNameIndb){
		this.btool.rpt("移动鼠标到["+elNameIndb+"]中心后点击，鼠标悬停，随后找到["+subElementNameIndb+"]点击");
	 WebElement el=this.getWebElement(elNameIndb);
	 boolean bool=true;
	 for(int i=0;i<3 && bool;i++){
	 try{
		this.wtool.a.moveToElement(el)
		.click().perform();
		this.wtool.a.moveToElement(this.getWebElementInTime(subElementNameIndb,3))
		.click().perform();	
		bool=false;
	 }catch(Exception e){
		 this.btool.rpt(subElementNameIndb+"未找到，再次点击"+elNameIndb);
	 }
	 }
		return this;
	}
	
	/**
	 * 移动鼠标到元素左上角x偏移百分比（y偏移为元素高度的一半）位置后点击
	 * @see
	 * @param elNameIndb
	 * @param percentoffsetx  eg:  50%   percentoffsetx=50;
	 * @return
	 */
	public WebBasicPage moveMouse2ElOffsetXY_ThenClick(String elNameIndb, int percentoffsetx){
		WebElement el=this.getWebElement(elNameIndb);
		Dimension size=el.getSize();
		this.wtool.a.moveToElement(el,size.getWidth()*(percentoffsetx/100),size.getHeight()/2)
		.click().perform();
		
		return this;
	}
	
	public void sendKey(CharSequence key){
		this.wtool.a.sendKeys(key).perform();
	}
	
	
	public WebTools getWtool() {
		return wtool;
	}
	

	public WebDriver getDriver() {
		return driver;
	}
	
}
