package CommonUtils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	public WebDriver driver;

	PropertyFileUtil putil = new PropertyFileUtil();
	WebDriverUtil wutil = new WebDriverUtil();
	
	@BeforeSuite
	public void BS()
	{
		System.out.println("Connect to DataBase");
	}
	@BeforeClass
	public void BC() throws IOException
	{
		//@BeforeClass is used to launch the application
		String URL = putil.getDataFromPropertyFile("Url");
		WebDriver driver = new ChromeDriver();
		wutil.maximize(driver);         //to maximize the window
		wutil.implicitwait(driver);    // to apply wait for find element
		driver.get(URL); //to launch the application
	}
	@BeforeMethod
	public void BM() throws IOException
	{
		//@BeforeMethod is used to login to the application
		String USERNAME = putil.getDataFromPropertyFile("Username");
		String PASSWORD = putil.getDataFromPropertyFile("Password");
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		
	}
	
	@AfterMethod
	public void AM()
	{
		//Used to logout from the application

		WebElement icon = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		wutil.mouseHover(driver, icon);
		
		//now click on signOut
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}
	@AfterClass
	public void AC()
	{
		//Used to close the browser
		driver.quit();
	}
	@AfterSuite
	public void AS()
	{
		System.out.println("Dissconnect from Database");
	}
	
}
