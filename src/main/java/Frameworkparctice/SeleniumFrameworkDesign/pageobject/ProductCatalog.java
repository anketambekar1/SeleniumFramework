package Frameworkparctice.SeleniumFrameworkDesign.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Frameworkparctice.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products = driver.findElements(By.className("mb-3"));
	
	@FindBy(css="mb-3")
	List<WebElement> products;
	
	@FindBy(css=",ng-animating")
	WebElement spinner;
	
	//////////////////////////////////////////////////////////////////////
	By producst1 = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getproductList() 
	{
		
		waitForElementToAppear(producst1);
		return products;
		
	}
	/////////////////////////////////////////////////////////////////////////
	public WebElement getProductByName(String productName) 
	{
		 
		WebElement prod =
				getproductList().stream().filter(product->
				product.findElement(By.cssSelector("b")).
				getText().equals("ZARA COAT 3")).
				findFirst().orElse(null);
		
		return prod;
	}
	
	public  void addProductToCart(String productname) {
		WebElement prod = getProductByName(productname);
		
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastmessage);
		waitForElementToDisapper(spinner);
	}
	
}
