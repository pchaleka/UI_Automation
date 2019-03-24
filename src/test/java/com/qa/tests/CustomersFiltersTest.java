package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;


public class CustomersFiltersTest extends TestBase {

	@BeforeTest
	public void setUp() {
		
		UIUrl = configprop.getProperty("UIUrl");

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/BrowserDrivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(UIUrl);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Add_Log.info("UI Url launched is --->" + UIUrl);
	}

	@Test(priority = 1)
	public void customerOperations() {

		userslink = objectsprop.getProperty("userslink");
		usernameDropdownStr = objectsprop.getProperty("usernameDropdown");
		userameDropdownText = objectsprop.getProperty("userameDropdownText");
		filterButton = objectsprop.getProperty("filterButton");

		// Click on Users link
		driver.findElement(By.linkText(userslink)).click();

		// Selecting Username from Dropdown options
		WebElement usernameDropdown = driver.findElement(By.xpath(usernameDropdownStr));
		Select dropdown = new Select(usernameDropdown);
		dropdown.selectByVisibleText("Starts with");

		// Enter Username Search values in textbox
		driver.findElement(By.xpath(userameDropdownText)).sendKeys("tuser");

		// Click on Filter button
		driver.findElement(By.xpath(filterButton)).click();

		Add_Log.info("User is searched successfully using Username");

		// Take screenshot of Filter Screen and store as a file format
		CustomersFiltersTest.captureScreenShot(driver);

	}

	// Method used for searching Users Using Date Filter
	@Test(priority = 2)
	public void searchUsersUsingDateFilter() {

		fromdate1 = objectsprop.getProperty("fromdate1");
		todate1 = objectsprop.getProperty("todate1");

		// Find the date time picker control
		WebElement fromdate = driver.findElement(By.xpath(fromdate1));

		// Fill From date as 2019-03-16
		fromdate.sendKeys("2019-03-16");

		// Find the date time picker control
		WebElement todate = driver.findElement(By.xpath(todate1));

		// Fill To date as 2019-03-16
		todate.sendKeys("2019-03-18");

		// Click on Filter button
		driver.findElement(By.xpath("//input[@value='Filter']")).click();

		Add_Log.info("User is searched successfully using Date Filter");

		// Take screenshot of Filter Screen and store as a file format
		CustomersFiltersTest.captureScreenShot(driver);

	}

	// Method is used to reset all the Customers Filters selected
	@Test(priority = 3)
	public void resetFiltersSelected() {

		emailDropdown1 = objectsprop.getProperty("emailDropdown1");
		emailDropdownText1 = objectsprop.getProperty("emailDropdownText1");

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		// Selecting Username from Dropdown options
		WebElement usernameDropdown = driver.findElement(By.xpath(usernameDropdownStr));
		Select dropdown = new Select(usernameDropdown);
		dropdown.selectByVisibleText("Starts with");

		// Enter Username Search values in textbox
		driver.findElement(By.xpath(userameDropdownText)).sendKeys("tuser");

		// Selecting Username from Dropdown options
		WebElement emailDropdown = driver.findElement(By.xpath(emailDropdown1));
		Select dropdown1 = new Select(emailDropdown);
		dropdown1.selectByVisibleText("Equals");

		// Enter Username Search values in textbox
		driver.findElement(By.xpath(emailDropdownText1)).sendKeys("tuser@mail.com");

		// Click on Clear filters Button :
		driver.findElement(By.linkText("Clear Filters")).click();

		Add_Log.info("All the Filters are resetted successfully");

		// Take screenshot of Filter Screen and store as a file format
		CustomersFiltersTest.captureScreenShot(driver);

	}

	public static void captureScreenShot(WebDriver driver) {
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile method

			FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\Screenshots\\CustomerFiltersScreenshot"
					+ System.currentTimeMillis() + ".png"));
		} catch (IOException e)

		{
			System.out.println(e.getMessage());
		}
	}

	@AfterTest
	public void afterCleanup() {

		// Closing All the instances opened by the Driver
		driver.quit();

	}

}
