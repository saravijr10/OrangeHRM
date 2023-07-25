package dataProviderClassPkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.DateFormatConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import mainClassPkg.MainClass;

public class DataProviderClass extends MainClass{
	static String shName;
	@DataProvider(name="Login")
	public Object[][] loginData() throws Exception{
		shName = ConfigProp().getProperty("Valid");
		String[] header = {"UserName", "Password"};
		Object[][] data = ReadExcelData(shName, header);
		return data;
	}
	
	@DataProvider(name="InvalidLogin")
	public Object[][] invalidLoginData() throws Exception{
		shName = ConfigProp().getProperty("Invalid");
		String[] header = {"UserName", "Password"};
		Object[][] data = ReadExcelData(shName, header);
		return data;
	}
	
	@DataProvider(name="PersonalDetails")
	public Object[][] fillPersonalDetails() throws Exception{
		shName = "PersonalDetails";
		String[] header = {"UserName", "Password", "FirstName", "LastName", "MartialStatus", "Gender", "Nationality", "PIT"};
		Object[][] data = ReadExcelData(shName, header);
		return data;
	}
	
	@DataProvider(name="ContactDetails")
	public Object[][] fillContactDetails() throws Exception{
		shName = "ContactDetails";
		String[] header = {"UserName", "Password", "AddressS1", "AddressS2", "City", "Country", "ZIP", "Mobile", "WorkEmail", "OtherEmail", "State"};
		Object[][] data = ReadExcelData(shName, header);
		return data;
	}
	
	@DataProvider(name="EmergencyContacts")
	public Object[][] fillEmergencyContacts() throws Exception{
		shName = "EmergencyContacts";
		String[] header = {"UserName", "Password", "Name", "Relationship", "Mobile"};
		Object[][] data = ReadExcelData(shName, header);
		return data;
	}
	
	@DataProvider(name="SocialMedia")
	public Object[][] fillSocailMediaDetails() throws Exception{
		shName = "SocialMedia";
		String[] header = {"UserName", "Password", "SocialType", "Handle", "Link", "Share"};
		Object[][] data = ReadExcelData(shName, header);
		return data;
	}
	
	@DataProvider(name="Dependents")
	public Object[][] fillDependentsDetails() throws Exception{
		shName = "Dependents";
		String[] header = {"UserName", "Password", "Name", "DoB", "Relation", "Others"};
		Object[][] data = ReadExcelData(shName, header);
		return data;
	}
	
	public Object[][] ReadExcel(String SheetName, File FilePath, String[] header) throws Exception {
		Object[][] data = null;
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		for(int s = 0; s<wb.getNumberOfSheets();s++) {
			XSSFSheet sheet = wb.getSheetAt(s);
			if(sheet.getSheetName().equalsIgnoreCase(SheetName)){
				int rowCount = wb.getSheet(SheetName).getPhysicalNumberOfRows();
				//System.out.println(SheetName + " "+rowCount);
				
				XSSFRow row0 = sheet.getRow(0);
				
				data = new Object[rowCount-1][header.length];
				
				for(int i=0; i<header.length;i++) {
					for(int j = 1; j<row0.getLastCellNum();j++) {
						String head = row0.getCell(j).getStringCellValue();
						if(header[i].equalsIgnoreCase(head)) {
							for(int k=1;k<rowCount;k++) {
								Row r1 = sheet.getRow(k);
								Cell c1 = r1.getCell(j);
								
								if (c1.getCellType()==CellType.STRING) {
									data[k-1][i] = c1.getStringCellValue();
								}
								else if(c1.getCellType()==CellType.BLANK) {
									data[k-1][i] = " ";
								}
								else if(DateUtil.isCellDateFormatted(c1)) {
									String date = new SimpleDateFormat("EEE, dd MMM yyyy").format(c1.getDateCellValue());
									//data[k-1][i] = new SimpleDateFormat("EEE, dd MMM yyyy").format(c1.getDateCellValue());
									data[k-1][i] = date;
								}
								else if(c1.getCellType() == CellType.NUMERIC || c1.getCellType() == CellType.FORMULA) {
									data[k-1][i] = c1.getNumericCellValue();
								}
								//System.out.println(cellData);
							}
						}
					}
				}
			}
		}
		
		/*for(Object[] row : data) {
			System.out.println(Arrays.toString(row));
		}*/
		wb.close();
		return data; 
	}
	public void WriteData(String Result, File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(shName);
		XSSFRow row0 = sheet.getRow(0);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		int colCount = row0.getLastCellNum();
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				if(row0.getCell(j).getStringCellValue().equalsIgnoreCase("Status")) {
					sheet.getRow(i).getCell(j).setCellValue(Result);
				}
			}
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();
		
	}
	
	
}
