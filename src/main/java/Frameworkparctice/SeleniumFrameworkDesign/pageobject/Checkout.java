package Frameworkparctice.SeleniumFrameworkDesign.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Frameworkparctice.AbstractComponents.AbstractComponents;

public class Checkout extends AbstractComponents {

	static WebDriver driver;
	
	public Checkout(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, null);
	
	}
	
	@FindBy(css = ".action_submit")
	WebElement submit;
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy (xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(null);
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		
		submit.click();
		 return new ConfirmationPage(driver);
		
	}
	

}
