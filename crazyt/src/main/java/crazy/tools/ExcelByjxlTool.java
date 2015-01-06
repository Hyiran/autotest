package crazy.tools;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
/**
 * 
 * @author xian_crazy QQ：330126160
 * @version 2014年10月30日  上午10:18:36
 * @see
 */
public class ExcelByjxlTool {
	private File classpathRoot;
	public File exlFile;
	private WritableWorkbook writablebook;
	private String sheetname;
	private Workbook wb;
	private WritableSheet writablesheet;
	public Sheet sheet;

	/**
	 * 
	 * @param newfilename
	 *            相对路径<br/>
	 *            如果该文件不存在将创建该文件，并创建一个名为crazysheet的Sheet
	 */
	public ExcelByjxlTool(String newfilename) {
		classpathRoot = new File(System.getProperty("user.dir"));
		this.exlFile = new File(classpathRoot, newfilename);
		System.out.println("ExcelByjxlTool(String newfilename) 的目标文件为"+this.exlFile.toString());
		if (!this.exlFile.exists()) {
			System.out.println("文件不存在，正在创建……");
			this.creatExcel("crazysheet");
		}
	}

	/**
	 * 
	 * @param file
	 *            已经存在的excel
	 */
	public ExcelByjxlTool(File file) {
		// classpathRoot = new File(System.getProperty("user.dir"));
		// this.exlFile = new File(classpathRoot, "\\testData\\test.xls");
		this.exlFile = file;
	}

	public ExcelByjxlTool(File file, String sheetname) {
		// classpathRoot = new File(System.getProperty("user.dir"));
		// this.exlFile = new File(classpathRoot, "\\testData\\test.xls");
		this.exlFile = file;
		this.sheetname = sheetname;
	}

	/**
	 * 
	 * @param file
	 *            要创建的新文件全路径<br/>
	 *            创建crazysheet
	 */
	private void creatExcel(String sheetname) {

		try {
			// 打开文件
			System.out.println(this.exlFile);
			this.writablebook = Workbook.createWorkbook(this.exlFile);
			// 生成名为“第一页”的工作表，参数0表示这是第一页

			this.writablebook.createSheet(sheetname, 0);

			this.writablebook.write();
			this.writablebook.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 * @param sheetName
	 *            需要读取的sheet名
	 * @param c
	 *            字母表示的列号
	 * @param r
	 *            数字表示的行号从1开始
	 * @return 单元格内容
	 */
	public String readExcelcell(String sheetName, String c, int r) {
		String result = null;
		try {
			wb = Workbook.getWorkbook(this.exlFile);
			// 获得第一个工作表对象
			// Sheet sheet = book.getSheet( 0 );
			Sheet sheet = this.wb.getSheet(sheetName);
			// 得到第一列第一行的单元格
			Cell cell1 = sheet.getCell(
					ExcelColExchangeNum.convertLetterToNum(c), r - 1);
			result = cell1.getContents();
			System.out.println(result);
			wb.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	/**
	 * 
	 * @param sheetName
	 *            需要读取的sheet名
	 * @param c
	 *            字母表示的列号
	 * @param r
	 *            数字表示的行号从1开始
	 * @return 单元格内容
	 */
	public String readExcelcell(String c, int r) {
		String result = null;
		try {
			this.wb = Workbook.getWorkbook(this.exlFile);
			// 获得第一个工作表对象
			// Sheet sheet = book.getSheet( 0 );
			Sheet sheet = this.wb.getSheet(this.sheetname);
			// 得到第一列第一行的单元格
			Cell cell1 = sheet.getCell(
					ExcelColExchangeNum.convertLetterToNum(c), r - 1);
			result = cell1.getContents();
			System.out.println(result);
			this.wb.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	/**
	 * 初始化读取sheet方法，初始化可调用其他读取sheet的方法
	 * 
	 * @param sheetname
	 * @return
	 */
	public ExcelByjxlTool readSheet(String sheetname) {

		try {
			this.wb = Workbook.getWorkbook(this.exlFile);
			System.out.println("当前this.exlFile名为："+this.exlFile.getCanonicalPath());
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sheet = this.wb.getSheet(sheetname);
		System.out.println("当前Sheet名为："+this.sheet.getName());
		return this;
	}

	/**
	 * 通过单元格所属行属性和列属性读取单元格的值
	 * @param c
	 * @param r
	 * @return
	 * rowAttribute   取得行坐标的单元格内容
	 * colAttribute  取得列坐标的单元格内容
	 */
	public Cell getcellByRowAndColAttribute(String shname ,String rowAttribute, String colAttribute) {
		//获取属性所属行号
		//System.out.println("当前Sheet名为："+this.sheet.getName());
		this.readSheet(shname);
//		int rs=this.sheet.getRows();
//		int cs=this.sheet.getColumns();
	
		
		
		int r=this.sheet.findCell(rowAttribute).getRow();
		int c=this.sheet.findCell(colAttribute).getColumn();
		Cell cell= this.sheet.getCell(c, r);
		//System.out.println("从当前Sheet读取单元格"+ExcelColExchangeNum.convertNumToLetter(c)+(r+1)+"的内容为"+str);
		return cell;
	}
	
	/**
	 * 通过单元格所属行属性和列属性读取单元格的值
	 * @需要先调用readsheet方法初始化sheet;
	 * @param c
	 * @param r
	 * @return
	 * rowAttribute   取得行坐标的单元格内容
	 * colAttribute  取得列坐标的单元格内容
	 */
	public String readcellByRowAndColAttribute(String rowAttribute, String colAttribute) {
		//获取属性所属行号
		//System.out.println("当前Sheet名为："+this.sheet.getName());
		
//		int rs=this.sheet.getRows();
//		int cs=this.sheet.getColumns();
		int r=this.sheet.findCell(rowAttribute).getRow();
		int c=this.sheet.findCell(colAttribute).getColumn();
		String str= this.sheet.getCell(c, r).getContents();
		//System.out.println("从当前Sheet读取单元格"+ExcelColExchangeNum.convertNumToLetter(c)+(r+1)+"的内容为"+str);
		return str;
	}
	
	/**
	 * 通过单元格所属行属性和列属性更新单元格的值
	 * @param c
	 * @param r
	 * @return
	 * rowAttribute   取得行坐标的单元格内容
	 * colAttribute  取得列坐标的单元格内容
	 */
	public void updatecellByRowAndColAttribute(String shname,String rowAttribute, String colAttribute, String content) {		
		this.readSheet(shname);
		int r=this.sheet.findCell(rowAttribute).getRow()+1;
		int c=this.sheet.findCell(colAttribute).getColumn();	
		this.UpdateExcel(shname, ExcelColExchangeNum.convertNumToLetter(c), r, content);
	
	}

	/**
	 * 向已有的sheet中更新值
	 * 
	 * @param shname
	 *            sheetname 要更新的sheet名
	 * @param c
	 *            A B C 列 单元格列号
	 * @param r
	 *            1=第1行 单元格行号
	 * @param cellcontent
	 *            要添加的字符内容
	 */
	public ExcelByjxlTool UpdateExcel(String shname, String c, int r,
			String cellcontent) {
		try {
			// Excel获得文件
			this.wb = Workbook.getWorkbook(this.exlFile);
			// 打开一个文件的副本，并且指定数据写回到原文件
			// System.out.println(ExcelColExchangeNum.convertLetterToNum(c));
			this.writablebook = Workbook.createWorkbook(this.exlFile, this.wb);
			// 向sheet中的单元格写数据
			this.writablesheet = writablebook.getSheet(shname);
			//System.out.println("UpdateExcel的Sheet为："+this.writablesheet.getName());
			this.writablesheet.addCell(new Label(ExcelColExchangeNum
					.convertLetterToNum(c), r - 1, cellcontent));
			this.writablebook.write();
			this.wb.close();
			this.writablebook.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return this;
	}
	/**
	 * 
	 * @param shname 当前实例化的ExlFile的sheetname
	 * @return  int 实例化的sheetname的内容总行数；
	 */
	public int getThisExlFileTheSheetRows(String shname){
		int rs=this.readSheet(shname).sheet.getRows();
		return rs;
		
	}
	/**
	 * 在当前当前实例化的ExlFile的指定sheet最后一行，在指定列名写入内容
	 * @param shname  指定sheet
	 * @param cname  要写入内容的列名
	 * @param cellcontent  写入的内容
	 * @return
	 */
	public ExcelByjxlTool updateThisExcelSomeSheetInNewRow(String shname, String cname,String cellcontent){
			this.UpdateExcel(shname, cname, this.getThisExlFileTheSheetRows(shname)+1,cellcontent);	
		return this;	
	}
	
	/**
	 * 在当前sheet中最后一行指定列添加内容
	 * @param cname  A B C 列 单元格列号
	 * @param cellcontent
	 * @return
	 */
	public ExcelByjxlTool updateThisExcelThisSheetInNewRow( String cname,String cellcontent){
		this.UpdateExcel(this.sheetname, cname, this.getThisExlFileTheSheetRows(this.sheetname)+1,cellcontent);	
	return this;	
}
	


	/**
	 * 
	 * @param newshname
	 *            需要新创建的sheet名<br/>
	 *            如果newshname存在则不创建，不存在则在最后sheet后创建
	 */
	public void addSheet(String newshname)  {
		Boolean bool = false;
		try {
			System.out.println("当前需要addSheet的文件为："+this.exlFile.toString());
			this.wb = Workbook.getWorkbook(this.exlFile);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet[] sheets = this.wb.getSheets();
		int sheetsLen = sheets.length;
		for (int i = 0; i < sheetsLen; i++) {
			//System.out.println(sheets[i].getName());
			if (sheets[i].getName().equals(newshname)) {
				bool = true;
				break;
			}
		}

		if (bool) {
			System.out.println("名为" + newshname + "的Sheet已经存在");
		} else {
			try {
				this.writablebook = Workbook.createWorkbook(this.exlFile, this.wb);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.writablebook.createSheet(newshname, sheetsLen);
			try {
				this.writablebook.write();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.writablebook.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("名为" + newshname + "的Sheet创建成功");
			
		}
		this.wb.close();
	}

}
