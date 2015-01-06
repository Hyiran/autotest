package crazy.tools;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.testng.annotations.Test;

import crazy.seleiumTools.ProfilesIni;

/**
 * @author xian_crazy QQ：330126160
 * @version 2014年11月6日  上午11:15:04
 * @see
 */
/** 
 * 读取文件指定行。 
 */
public class ReadSelectedLine {
//	 读取文件指定行。 
	static void readAppointedLineNumber(File sourceFile, int lineNumber) throws IOException {
		FileReader in = new FileReader(sourceFile);
		LineNumberReader reader = new LineNumberReader(in);
		String s = reader.readLine();
		
		if (lineNumber < 0 || lineNumber > getTotalLines(sourceFile)) {
			System.out.println("不在文件的行数范围之内。");
		}

		{
		while (s != null) {
			System.out.println("当前行号为:" + reader.getLineNumber());
			
			System.out.println(s);
			System.exit(0);
			s = reader.readLine();
		}
		}
		reader.close();
		in.close();
	}

	// 文件内容的总行数。 
	static int getTotalLines(File file) throws IOException {
		FileReader in = new FileReader(file);
		LineNumberReader reader = new LineNumberReader(in);
		String s = reader.readLine();
		int lines = 0;
		while (s != null) {
			lines++;
			s = reader.readLine();
		}
		reader.close();
		in.close();
		return lines;
	}
	@Test
	public  void test() throws IOException {
		
		


		
		
		System.setProperty("webdriver.firefox.bin", "c:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	
	
		
		ProfilesIni allProfiles = new ProfilesIni();

		System.out.println(allProfiles.getNameOfpro());
		
        // 读取文件 
		File sourceFile = allProfiles.getProfiles_ini();
        // 获取文件的内容的总行数 
		int totalNo = getTotalLines(sourceFile);
		System.out.println("There are "+totalNo+ " lines in the text!");
		
		// 指定读取的行号 
		int lineNumber = 2;
		
		// 读取指定的行 
		readAppointedLineNumber(sourceFile, lineNumber);
		
		
		
	}

	
}
