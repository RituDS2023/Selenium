package vtigercrm;

import java.io.IOException;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.ListenerImplementation;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;


@Listeners(ListenerImplementation.class)

public class Organization {
    
	
	//object creation
	PropertyFileUtil putil = new PropertyFileUtil();
	WebDriverUtil wutil = new WebDriverUtil();
	ExcelUtil eutil = new ExcelUtil();
	JavaUtil jutil = new JavaUtil();
	
	@Test
	public void organizationTest() throws IOException, InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		
		wutil.maximize(driver);         //to maximize the window
		wutil.implicitwait(driver);    // to apply wait for find element
		
		
		String URL = putil.getDataFromPropertyFile("Url");
		String USERNAME = putil.getDataFromPropertyFile("Username");
		String PASSWORD = putil.getDataFromPropertyFile("Password");
		
		//To read data from excel file
		String ORGNAME = eutil.getDataFromExcel("Organizations", 0, 1);
		String GROUP = eutil.getDataFromExcel("Organizations", 1, 1);     
		
		driver.get(URL); //to launch application 
		
		//TO Login application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click(); //click on organization 
		driver.findElement(By.cssSelector("img[alt='Create Organization...']")).click(); //click on create organization
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+ jutil.getRandomNumber()); //enter organization name
		
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click(); //group radio button select
		
		//Now in dropDown select support group
		
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
		
		wutil.handleDropDown(dropdown, GROUP);  
		
		//now click on save button module is over
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
		
		Thread.sleep(2000);
		//to click on logout mouseHover on that icon and click on SignOut
		//action class concept 
		//it is part of webDriverUtil so create method for MouseHovering and call it here
		
		WebElement icon = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		wutil.mouseHover(driver, icon);
		
		//now click on signOut
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
	}
}
