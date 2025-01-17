package Frameworkparctice.SeleniumFrameworkDesign.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Frameworkparctice.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{
	
	 WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, null);
	
	}
	
	@FindBy(css=".hero-primary")
	private WebElement confirmationMessage;
	
	public String getConfirmationMessage() {
		
		return confirmationMessage.getText();
		
	}

}
