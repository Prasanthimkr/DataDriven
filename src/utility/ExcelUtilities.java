package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	private static XSSFWorkbook book;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	
	public void setExcelFile(String excelpath,String sheetname) throws IOException
	{
		File file=new File(excelpath);
		FileInputStream fis=new FileInputStream(file);
		book=new XSSFWorkbook(fis);
		sheet=book.getSheet(sheetname);
	}
	
	public static String getcelldata(int row,int cellnum)
	{
		cell=sheet.getRow(row).getCell(cellnum);
		return cell.getStringCellValue();
	}
	
	public static int getrowcount()
	{
		int rowcount=sheet.getLastRowNum();
		return rowcount;
	}
	
	public void setcellvalue(int row,int cellnum,String cellvalue,String excelpath) throws IOException
	{
		sheet.getRow(row).createCell(cellnum).setCellValue(cellvalue);
		FileOutputStream fos=new FileOutputStream(excelpath);
	    book.write(fos);
	}

}
