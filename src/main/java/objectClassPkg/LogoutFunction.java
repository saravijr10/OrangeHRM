package objectClassPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mainClassPkg.ReUseClass;

public class LogoutFunction {
	WebDriver driver;

	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	ReUseClass obj = new ReUseClass();
	public LogoutFunction(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	WebElement tmsg;
	@FindBy(xpath="//p[contains(@class,'userdropdown-name')]")
	WebElement acc_drpdwn;
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement logoutBtn;
//	@FindBy(id="btnLogin")
//	WebElement retryLogin;
//	@FindBy(xpath="//span[@class='restricted']")
//	WebElement restrictPage;
//	@FindBy(xpath="//div[@class='dashboardCard-title-for-card']")
//	WebElement retryPage;
	
	
	public void logout() {
		try {
			acc_drpdwn.click();
			logoutBtn.click();
		}
		catch(Exception e) {
			System.out.println(tmsg.getText());
		}
	}
}
