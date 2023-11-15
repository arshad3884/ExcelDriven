package Framework.ExcelDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
		//Select and send handle of excel file to XSSFWorkbook using file stream
		//Select a particular sheet using searching it by name
		//Identify test cases column by scanning the entire first row
		//Once column is identified then scan the particular column
		//Grap the particular row to run that test case = pull all data our data and feed it in the test
	
	public ArrayList<String> getData(String testcase) throws IOException
	{
		//Select and send handle of excel file to XSSFWorkbook using file stream
				//FileinputStream argument
				ArrayList <String> a= new ArrayList<String>();
				
				FileInputStream fis = new FileInputStream("C://Users//hp//Downloads//demodata.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				int sheets = workbook.getNumberOfSheets();
				for (int i=0; i<sheets; i++)
				{
					//Select a particular sheet using searching it by name
					if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1"))
					{
					XSSFSheet sheet = workbook.getSheetAt(i);
					//Identify test cases column by scanning the entire first row
					Iterator <Row> rows = sheet.rowIterator();
					Row firstrow = rows.next();
					//Iterate through first row (row is the collections of cells)
					Iterator <Cell> ce = firstrow.cellIterator();
					int k=0;
					int column=0;
					while (ce.hasNext())
					{
					Cell value = ce.next();
						if (value.getStringCellValue().equalsIgnoreCase("TestCases"))
						{
						//desired column value found at which (Column index)
						column = k;
						}
					k++;
					}
					System.out.println("Testcase name Column: " + column);
					//Once column is identified then scan the particular column (tesetcase) to find the desired row
					int rowNo=0;
					while (rows.hasNext())
					{
						Row r = rows.next();
						if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcase))
						{
						//Grap the particular row to run that test case = pull all data our data and feed it in the test
						Iterator<Cell> cv = r.cellIterator();
						while(cv.hasNext())
							{
							Cell c = cv.next();
							//check cell value that what type of data inside the cell
							if (c.getCellType()==CellType.STRING)
							{
							//Store data in an arraylist "a" when iterating through each cell
							a.add(c.getStringCellValue());
							}
							else {a.add(NumberToTextConverter.toText(c.getNumericCellValue()));}
							}
						}
						rowNo++;
					}
					System.out.println("Testcase found on row No: "+rowNo);
					}
				}
				return a;
	}
	public static void main(String[] args) throws IOException {
		
		}

}
