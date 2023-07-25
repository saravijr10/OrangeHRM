package mainClassPkg;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReUseClass extends MainClass{
	public void Select(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}
	public void WaitSelectable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
