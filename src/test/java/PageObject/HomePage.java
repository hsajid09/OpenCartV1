package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//li[2]")
	WebElement login;
	
	
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		login.click();
	}
	
	
	
}
