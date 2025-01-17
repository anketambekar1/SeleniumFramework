package Frameworkparctice.SeleniumFrameworkDesign.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Frameworkparctice.AbstractComponents.AbstractComponents;


public class CartPage extends AbstractComponents{

WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
				
	}
	
	@FindBy(css = ".totalRow button")
	WebElement checoutEle;
	
	@FindBy(css = ".cartsection h3")
	List<WebElement> cartProducts;
	
	public boolean VerifyProductDisplay(String productname) {
		
		boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
		return match;
		
		
	}
	
	public Checkout goToCheckOut() {
		
		checoutEle.click();
		return new Checkout(driver);
		
	}
	
	
}
