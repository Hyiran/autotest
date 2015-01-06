package crazy.seleiumTools;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import crazy.tools.BasicTools;





/**
 * @author xian_crazy QQ：330126160
 * @version 2014年11月20日  下午3:09:13
 * @see
 */
public class Sikuli {
	Screen s=new Screen(); 
	private BasicTools btool=new BasicTools();
	
	public Sikuli click(String img){
		this.getElement(img).click();
		return this;
	}
	/**
	 * 点击图片,如果图片在页面找到且点击成功 返回true 否则 flase
	 * @see
	 * @param img  图片在本地的路径
	 * @param offsetx_by_topleft  图片左上角偏移x像素
	 * @param offset_by_topleft 图片左上角偏移y像素
	 * @return
	 */
	public boolean click(String img,int offsetx_by_topleft,int offset_by_topleft){
		
		Match m=this.getElement(img);
			// btool.rpt((offsetx_by_topleft-m.getW()/2)+"   "+ (offset_by_topleft-m.getH()/2));
			m.setTargetOffset(offsetx_by_topleft-m.getW()/2, offset_by_topleft-m.getH()/2);		
		
			boolean bool=(m!=null&&m.click()==1)?true:false;
			return bool;
	}
	
	//通过图片在页面找到元素并高亮1秒钟
	public Match getElement(String img){
		Match m =null;
		try {
			m=s.find(img);
			m.highlight(1);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			 btool.rpt("图片"+img+"在当前视图未找到");
		}	
		return m;
	}
}
