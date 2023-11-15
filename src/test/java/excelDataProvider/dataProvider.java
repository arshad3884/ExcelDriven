package excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {

	DataFormatter formatter= new DataFormatter(); 
	@Test(dataProvider="driveTest")
	public void testCases(String greeting, String communication, String id)
	{
		System.out.println(greeting+communication+id);
	}
	// multiple set of data to our tests
	// Send each set of data as an array
	// if we send 5 set of data as an array from data provider to our test
	// then our test will run 5 times
	
	@DataProvider (name="driveTest")
	public Object[][] getData() throws IOException
	{
		//multidimensional array
		//Object[][] data = {{"hello","text","1"},{"bye","message","143"},{"solo","call","453"}};
		//every row of excel should be sent to 1 array
		
		FileInputStream fis = new FileInputStream("C:\\Users\\hp\\Downloads\\dataSet.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workBook.getSheetAt(0);
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();
		System.out.println(rowCount);
		System.out.println(columnCount);
		
		Object data[][]= new Object[rowCount-1][columnCount];
		for (int i=0;i<rowCount-1;i++)
		{
			row= sheet.getRow(i+1);
			for(int j=0;j<columnCount;j++)
			{
				XSSFCell cell= row.getCell(j);
				data[i][j]=formatter.formatCellValue(cell);
			}
		}
		System.out.println(data);
		return data;
	}
	
}
