package crazy.tools;
/**
 * 
 * @author xian_crazy QQ：330126160
 * @version 2014年10月30日  上午10:18:39
 * @see
 */
public class ExcelColExchangeNum {
	/**  * 将字母表示的列转为用数字表示(如A=0，AB=27)  */
	public static int convertLetterToNum(String letters){  
		letters = letters.toUpperCase();  
		int v = 0;  String regex = "[A-Z]+";  
		if(!letters.matches(regex)) 
			return v;  
		int radix = 1;  
		for(int i = letters.length()-1; i >= 0; i--){  
			char c = letters.charAt(i);   
			v += (c-'A'+1)*radix;   radix *= 26;  }  
		return v-1; } 
	/**  * 将数字表示的列转为字母表示(如0=A，27=AB,703=AAB)  */ 
	public static String convertNumToLetter(int colNum){  
		String colLetter="";
		colNum++;
		do {    colNum--;    colLetter = ((char) (colNum % 26 + (int) 'A')) + colLetter;  
		colNum = (int) ((colNum - colNum % 26) / 26);     }
		while (colNum > 0);  
		return colLetter; 
		}
}
