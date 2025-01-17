package Frameworkparctice.SeleniumFrameworkDesign.test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.callback.ConfirmationCallback;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Frameworkparctice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.CartPage;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.Checkout;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.ConfirmationPage;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.LandingPage;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.OrderPage;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.ProductCatalog;

public class StandAloneTest2 extends BaseTest {
	String productname ="ZARA COAT 3";
	
	@Test(dataProvider ="getData" ,groups = {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException 
	{
	
		// TODO Auto-generated method stub
		
		ProductCatalog productcatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productcatalog.getproductList();
		
		productcatalog.addProductToCart(input.get("product"));
		CartPage cartpage = productcatalog.goToCartPage();
		
		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		Checkout checkout = cartpage.goToCheckOut();
		checkout.selectCountry("india");
		ConfirmationPage ConfirmationPage = checkout.submitOrder();		
		String confirmessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
		
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		//ZARA coat 3
		ProductCatalog productcatalog = landingPage.loginApplication("anketambekar@gmail.com", "Anket@123");	
		OrderPage ordersPage = productcatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productname));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{ 
		/*HashMap<String, String> map = new HashMap<String,String>();
		map.put("email", "anketambekar@gmail.com");
		map.put("password", "Anket@123");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "IamKing000");
		map1.put("product", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String, String>> data = getJsonDataToMap();
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}
