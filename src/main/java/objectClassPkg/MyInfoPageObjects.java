package objectClassPkg;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import mainClassPkg.ReUseClass;

public class MyInfoPageObjects {
	WebDriver driver;
	static String saveCountry;

	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	ReUseClass obj = new ReUseClass();
	public MyInfoPageObjects(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@id='menu_pim_viewMyDetails']")
	public WebElement myInfoTab;
	@FindBy(xpath="//input[@id='otherId']")
	public WebElement pit;
	@FindBy(id="emp_marital_status")
	public WebElement martialStatus;
	@FindBy(xpath="//button[@type='submit']")
	public WebElement savePersonalDetails;
	@FindBy(xpath="//a[@id='top-menu-trigger']")
	public WebElement more;
	@FindBy(id="firstName")
	public WebElement fname;
	@FindBy(id="lastName")
	public WebElement lname;
	@FindBy(id="emp_gender")
	public WebElement gender;
	@FindBy(id="nation_code")
	public WebElement nationality;
	@FindBy(id="pim.navbar.employeeName")
	public WebElement employeeName;
	
	public void fillPersonalDetailsForm(String FirstName, String LastName, String MartialStatus, String Gender, String Nation, String PIT) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		myInfoTab.click();
		fname.clear();
		fname.sendKeys(FirstName);
		lname.clear();
		lname.sendKeys(LastName);
		pit.clear();
		pit.sendKeys(PIT);
		obj.Select(martialStatus, MartialStatus);
		obj.Select(gender, Gender);
		obj.Select(nationality, Nation);
		savePersonalDetails.click();
	}
	
	@FindBy(xpath="//input[@id='street1']")
	public WebElement address1;
	@FindBy(xpath="//input[@id='street2']")
	public WebElement address2;
	@FindBy(xpath="//input[@id='city']")
	public WebElement city;
	@FindBy(xpath="//div[@class='input-field col s12 m12 l4']//div")
	public WebElement countryField;
	@FindBy(css = "ul#select-options-c9fe75d2-60f9-01d9-47ed-c54b97f40338")
	public WebElement country;
	@FindBy(xpath="//div[@id='province_inputfileddiv']")
	public WebElement state;
	@FindBy(id="province")
	public WebElement province;
	@FindBy(id="emp_zipcode")
	public WebElement zip;
	@FindBy(xpath="//input[@id='emp_hm_telephone']")
	public WebElement hmTele;
	@FindBy(xpath="//input[@id='emp_mobile']")
	public WebElement mobile;
	@FindBy(xpath="//input[@id='emp_work_telephone']")
	public WebElement workTele;
	@FindBy(xpath="//input[@id='emp_work_email']")
	public WebElement workEmail;
	@FindBy(xpath="//input[@id='emp_oth_email']")
	public WebElement otherEmail;
	@FindBy(xpath="//button[@id='modal-save-button']")
	public WebElement save;
	@FindBy(xpath="//div[@class='toast-message']")
	public WebElement tmsg;
	@FindBy(xpath="//ul[@id='top-menu']//li//a[@id='top-menu-trigger']")
	public WebElement menuMod;
	@FindBy(xpath="//span[@translate='Contact Details']")
	public WebElement conDetails;
	
	public void fillContactDetails(String AddressS1, String AddressS2, String City, String Country, String ZIP, double Mobile, String WorkEmail, String OtherEmail, String State) throws Exception {
		myInfoTab.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(menuMod));
		//obj.WaitSelectable(menuMod);
		menuMod.click();
		conDetails.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		address1.clear();
		address1.sendKeys(AddressS1);
		address2.clear();
		address2.sendKeys(AddressS2);
		city.clear();
		city.sendKeys(City);
		zip.clear();
		zip.sendKeys(ZIP);
		countryField.click();
		
		
		List<WebElement> liCon = driver.findElements(By.xpath("//div[@id='country_inputfileddiv']//div//ul/li/span"));
		//System.out.println(liCon.size());
		try {
			for(WebElement s : liCon) {
				if(s.getText().equalsIgnoreCase(Country)) {
					this.saveCountry = s.getText();
					System.out.println(s.getText());
					//wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(s)));
					Actions act = new Actions(driver);
					act.moveToElement(s).click().build().perform();
					break;
				}
			}
		}catch(Exception e) {
			//e.printStackTrace();
			//System.out.println(e.getMessage());
		}
		int m = (int) Mobile;
		hmTele.clear();
		hmTele.sendKeys(String.valueOf(m));
		mobile.clear();
		mobile.sendKeys(String.valueOf(m));
		workTele.clear();
		workTele.sendKeys(String.valueOf(m));
		workEmail.clear();
		workEmail.sendKeys(WorkEmail);
		otherEmail.clear();
		otherEmail.sendKeys(OtherEmail);
		
		
		if(saveCountry.equalsIgnoreCase("United States")) {
			state.click();
			Thread.sleep(3000);
			try {				
				List<WebElement> liState = driver.findElements(By.xpath("//div[@id='province_inputfileddiv']//div//ul/li/span"));
				for(WebElement s : liState) {
					if(s.getText().equalsIgnoreCase(State)) {
						System.out.println(s.getText());
						Actions act = new Actions(driver);
						act.moveToElement(s);
						act.moveToElement(s).click().build().perform();
						break;
					}
					
				}
			}catch(Exception en) {
				en.printStackTrace();
			}
			
		}
		else {
			Thread.sleep(3000);
			System.out.println(State);
			province.clear();
			province.sendKeys(State);
		}
		Thread.sleep(3000);
		save.click();
	}
	
	@FindBy(xpath="//span[@translate='Emergency Contacts']")
	public WebElement emConatct;
	@FindBy(xpath="//a[@ng-click='emergencyContact.addEmergencyContact()']//i[@class='material-icons'][normalize-space()='add']")
	public WebElement add;
	@FindBy(xpath="//input[@ng-model=\"model['name']\"]")
	public WebElement contactName;
	@FindBy(xpath="//input[@ng-model=\"model['relationship']\"]")
	public WebElement relationName;
	@FindBy(xpath="//input[@ng-model=\"model['home_phone']\"]")
	public WebElement homePhone;
	@FindBy(xpath="//input[@ng-model=\"model['mobile_phone']\"]")
	public WebElement mobilePhone;
	@FindBy(xpath="//input[@ng-model=\"model['office_phone']\"]")
	public WebElement workPhone;
	@FindBy(xpath="//button[normalize-space()='Save']")
	public WebElement saveForm;
	
	public void fillEmergencyContacts(String Name, String Relationship, double Mobile) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		myInfoTab.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(menuMod));
		menuMod.click();
		emConatct.click();
		add.click();
		contactName.sendKeys(Name);
		relationName.sendKeys(Relationship);
		int m = (int) Mobile;
		homePhone.sendKeys(String.valueOf(m));
		mobilePhone.sendKeys(String.valueOf(m));
		workPhone.sendKeys(String.valueOf(m));
		saveForm.click();
	}
	
	@FindBy(xpath="//span[@translate='Social Media Details']")
	public WebElement socialMedia;
	@FindBy(xpath="//a[@ng-click='socialMedia.addSocialMedia()']//i[@class='material-icons'][normalize-space()='add']")
	public WebElement addSocial;
	@FindBy(xpath="//button[@role='combobox']")
	public WebElement socialType;
	@FindBy(xpath="//input[@id='profileName']")
	public WebElement handle;
	@FindBy(xpath="//input[@id='profileLink']")
	public WebElement socialLink;
	@FindBy(xpath="//label[@for='allowShare']")
	public WebElement allowShare;
	
	public void fillSocialMediadetails(String SocialType, String Handle, String Link, double Share) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		myInfoTab.click();
		menuMod.click();
		socialMedia.click();
		addSocial.click();
		socialType.click();
		List<WebElement> liType = driver.findElements(By.xpath("//div[@class='dropdown-menu show']//div//ul/li/a/span"));
		System.out.println(liType.size());
		for(WebElement typ : liType) {
			
			if(SocialType.equalsIgnoreCase(typ.getText())) {
				System.out.println(typ.getText());
				Actions act = new Actions(driver);
				act.moveToElement(typ).click().build().perform();
				break;
			}
		}
		handle.sendKeys(Handle);
		socialLink.sendKeys(Link);
		int sh = (int) Share;
		if(sh == 1) {
			allowShare.click();
		}
		saveForm.click();
	}
	
	@FindBy(xpath="//span[@translate='Dependents']")
	public WebElement dependent;
	@FindBy(xpath="//a[@ng-click='dependents.addDependent()']//i[@class='material-icons'][normalize-space()='add']")
	public WebElement addDependent;
	@FindBy(xpath="//input[@id='name']")
	public WebElement nameDependent;
	@FindBy(xpath="//input[@id='date_of_birth']")
	public WebElement dobDependent;
	@FindBy(xpath="//div[@class='filter-option-inner-inner']")
	public WebElement selectRelation;
	@FindBy(xpath="//input[@id='relationship']")
	public WebElement specifyRelation;
	
	public void fillDependents(String Name, String DoB, String Relation, String Others) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		myInfoTab.click();
		menuMod.click();
		dependent.click();
		addDependent.click();
		nameDependent.sendKeys(Name);
		dobDependent.sendKeys(String.valueOf(DoB));
		selectRelation.click();
		List<WebElement> liRel = driver.findElements(By.xpath("//div[@class='dropdown-menu show']//div//ul/li/a/span"));
		System.out.println(liRel.size());
		for(WebElement typ : liRel) {
			
			if(Relation.equalsIgnoreCase(typ.getText())) {
				System.out.println(typ.getText());
				Actions act = new Actions(driver);
				act.moveToElement(typ).click().build().perform();
				break;
			}
		}
		try {
			specifyRelation.sendKeys(Others);
		}catch(Exception relation) {
			
		}
		saveForm.click();
	}
}

