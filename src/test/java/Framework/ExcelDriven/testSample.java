package Framework.ExcelDriven;

import java.io.IOException;
import java.util.ArrayList;

public class testSample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//Create a instance of dataDriven class to fetch data from excel file
		dataDriven d = new dataDriven();
		ArrayList data = d.getData("Add Profile");
		//Print data on array's first index
		System.out.println(data.get(0));
		//Print data on array's second index
		System.out.println(data.get(1));
		//Print data on array's third index
		System.out.println(data.get(2));
		//Print data on array's fourth index
		System.out.println(data.get(3));
		
		//We can pass any cell value to our code like below:
		//driver.findElement(by.xpath("//a[value]")).sendKeys(data.get(0));
		
		
	}

}
