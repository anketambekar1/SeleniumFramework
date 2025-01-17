package Frameworkparctice.SeleniumFrameworkDesign.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Frameworkparctice.AbstractComponents.AbstractComponents;


public class OrderPage extends AbstractComponents{

WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
				
	}
	
	@FindBy(css = ".totalRow button")
	WebElement checoutEle;
	
	@FindBy(css = ".tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public boolean VerifyOrderDisplay(String productname) {
		
		boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
		return match;
		
		
	}
	
	public Checkout goToCheckOut() {
		
		checoutEle.click();
		return new Checkout(driver);
		
	}
	
	
}
