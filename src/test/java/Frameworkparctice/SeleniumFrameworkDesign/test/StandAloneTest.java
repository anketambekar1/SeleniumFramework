package Frameworkparctice.SeleniumFrameworkDesign.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Frameworkparctice.SeleniumFrameworkDesign.pageobject.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productname ="ZARA COAT 3";
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	
		LandingPage landingPage = new LandingPage(driver);
		
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("anketambekar@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Anket@123");
		driver.findElement(By.id("login")).click();
		
		//wait to load all the items on webpage 
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.className("mb-3"));
		WebElement prod =
		products.stream().filter(product->
		product.findElement(By.cssSelector("b")).
		getText().equals("ZARA COAT 3")).
		findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animation 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		//now we are getting all the items added in cart in list 
		//and check if zara coat 3 is presnt in it and putting aseertion on it
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartsection h3"));
		
		boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		driver.findElement(By.cssSelector(".action-submit")).click();
		
		String confirmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(confirmessage, "THANKYOU FOR THE ORDER");
		
		//OR 
		
		Assert.assertTrue(confirmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
		
	}

}
