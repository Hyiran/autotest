package crazy.seleiumTools;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;

import crazy.tools.Dbtool;

/**
 * @author xian_crazy QQ��330126160
 * @version 2014��12��5��  ����1:43:29
 * @see
 */
public class PageFactory {
	private WebDriver driver;
	private Dbtool dbtool;


	public PageFactory(WebDriver driver, Dbtool dbtool) {
		this.dbtool = dbtool;
		this.driver = driver;
		this.pageFactory();
	}

	public void pageFactory() {

		/**
		 * ͨ�������Զ�ѭ��ʵ��������public ҳ��
		 */
		Field[] f = this.getClass().getFields();
		for (int i = 0; i < f.length; i++) {
			try {
				f[i].set(
						this,
						Class.forName(f[i].getType().getName())
								.getConstructor(WebDriver.class, Dbtool.class)
								.newInstance(driver, dbtool));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
	}
}
