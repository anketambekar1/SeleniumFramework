package Frameworkparctice.SeleniumFrameworkDesign.test;

import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Test;

import Frameworkparctice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.CartPage;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.Checkout;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.ConfirmationPage;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.LandingPage;
import Frameworkparctice.SeleniumFrameworkDesign.pageobject.ProductCatalog;

public class ErrorValidations extends BaseTest {
	
	
	
	@Test(groups = {"ErrorHandling"})
	public void submitOrder() throws IOException 
	{
	
		// TODO Auto-generated method stub
		
		landingPage.loginApplication("anketambekar@gmail.com", "Anket@1234");
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMessage());
		
	
	}

}
