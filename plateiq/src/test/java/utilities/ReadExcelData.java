package utilities;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	public static String[][] readData(String fileName) throws IOException {

		//To give the location of my workbook
		XSSFWorkbook ws= new XSSFWorkbook("./data/"+fileName+".xlsx");	

		//using sheet name we can get into sheet
		XSSFSheet sheet = ws.getSheet("Sheet1");

		//to identify num of rows
		int lastRowNum = sheet.getLastRowNum();//it will exclude the first row in sheet by default
		System.out.println("rowCouunt="+lastRowNum);
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		String[][] data=new String[lastRowNum][lastCellNum];
		for (int i = 1; i <= lastRowNum; i++) {
			for (int j = 0; j <lastCellNum; j++) {
				String cellValue = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j]=cellValue;
				System.out.println(cellValue);
			}
		}
		ws.close();
		return data;
	}
}
