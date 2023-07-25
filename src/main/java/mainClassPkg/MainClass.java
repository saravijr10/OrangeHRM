package mainClassPkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import dataProviderClassPkg.DataProviderClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MainClass {
public WebDriver driver;
	
	public Properties ConfigProp() throws IOException {
		Properties config = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
		config.load(file);
		return config;
	}
	
	@BeforeTest
	public void LaunchBrowser() throws IOException {
		//System.setProperty("webdriver.chrome.driver", "H:\\selenium\\chromedriver.exe");
		//driver = new ChromeDriver();
		WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions= new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
		//driver.manage().window().maximize();
		driver.get(ConfigProp().getProperty("URL"));
	}
	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
	
	public Object[][] ReadExcelData(String SheetName, String[] header) throws Exception{
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx");
		DataProviderClass obj1 = new DataProviderClass();
		Object[][] data = obj1.ReadExcel(SheetName, file, header);
		return data;
	}
	
	public void WriteExcelData(String Result) throws Exception {
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx");
		DataProviderClass obj1 = new DataProviderClass();
		obj1.WriteData(Result, file);
	}
	
	public void CaptureScreen(WebDriver driver, String Name) throws Exception {
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String addDate = new SimpleDateFormat("yyMMddhhmmss").format(new Date());
		File scr = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\test"+Name+addDate+".png");
		FileUtils.copyFile(screen, scr);
	}

}
