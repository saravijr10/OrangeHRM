package objectClassPkg;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPageObjects {
	WebDriver driver;
	public LoginPageObjects(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@name='username']")
	WebElement username;
	@FindBy(xpath="//input[@type='password']")
	WebElement password;
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginBtn;
	
	//@FindBy(xpath="//div[@class='toast-message']")
	//WebElement tmsg;
	
	@FindBy(xpath="//h6[normalize-space()='Dashboard']")
	WebElement dashboard;
	
	@FindBy(xpath="//span[@id='account-job']//following::i[@class='material-icons']")
	WebElement acc_drpdwn;
	@FindBy(xpath="//ul[@id='user_menu']//li//a[@id='logoutLink']")
	WebElement logoutBtn;
	@FindBy(id="btnLogin")
	WebElement retryLogin;
	@FindBy(xpath="//span[@class='restricted']")
	WebElement restrictPage;
	@FindBy(xpath="//div[@class='dashboardCard-title-for-card']")
	WebElement retryPage;
	
	public void login(String UName, String Pwd) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		username.sendKeys(UName);
		password.sendKeys(Pwd);
		try {
			loginBtn.click();
		}catch(Exception e) {
			//String retry = retryPage.getText();
			retryLogin.click();	
		}
		
	}
	
	public String dash() {
		String s = dashboard.getText();
		return s;
	}
	
}
