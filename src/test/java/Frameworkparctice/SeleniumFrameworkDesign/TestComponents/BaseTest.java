package Frameworkparctice.SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Frameworkparctice.SeleniumFrameworkDesign.pageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException 
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Frameworkpractice\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName =
				System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		
		if(browserName.contains("Edge"))
		{
			EdgeOptions options = new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new EdgeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); //full screen
		} 
		
		else if (browserName.equalsIgnoreCase("chrome")) 
		{
			driver = new ChromeDriver();	
		}
		else if (browserName.equalsIgnoreCase("firefox")) 
		{
			driver = new FirefoxDriver();	
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
public List<HashMap<String, String>> getJsonDataToMap () throws IOException {
		
		//read json to string 
	String jsonContent = FileUtils.readFileToString(new File("C:\\Users\\91997\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\Frameworkpractice\\SeleniumFrameworkDesign\\DataPackage\\PurchaseOrder.json"),
			StandardCharsets.UTF_8);
		
	
	//convert string to hashmap jackson databind
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent , new TypeReference<List<HashMap<String, String>>>() {
	});
	return data;
	
	}

	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException 
	{
		
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() 
	{
		driver.close();
	}
	
}
