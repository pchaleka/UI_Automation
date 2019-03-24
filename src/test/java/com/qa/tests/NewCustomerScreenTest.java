package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

public class NewCustomerScreenTest extends TestBase {

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

	@Test
	public void customerOperations() {

		userslink = objectsprop.getProperty("userslink");
		newUserLink = objectsprop.getProperty("newUserLink");
		usernameRep = objectsprop.getProperty("usernameRep");
		passwordRep = objectsprop.getProperty("passwordRep");
		emailRep = objectsprop.getProperty("emailRep");
		loginbtn = objectsprop.getProperty("loginbtn");

		username = configprop.getProperty("username");
		password = configprop.getProperty("password");
		email = configprop.getProperty("email");

		// Click on Users link
		driver.findElement(By.linkText(userslink)).click();

		// Click on New User Link
		driver.findElement(By.xpath(newUserLink)).click();

		driver.findElement(By.xpath(usernameRep)).sendKeys(username);

		driver.findElement(By.xpath(passwordRep)).sendKeys(password);

		driver.findElement(By.xpath(emailRep)).sendKeys(email);

		try {
			driver.findElement(By.xpath(loginbtn)).click();
			Add_Log.info("New Customer with " + username + " is registered successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			Add_Log.info("New Customer is Unable to register or Already registered");
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		Add_Log.info(" Page Title is : " + driver.getTitle());

		String expectedTitle = username + " | Active Admin Depot";

		String actualTitle = driver.getTitle();

		// Assertion for Page Title after USer is registered.
		Assert.assertTrue(expectedTitle.equals(actualTitle));

		// Take screenshot and store as a file format
		NewCustomerScreenTest.captureScreenShot(driver);
	}

	public static void captureScreenShot(WebDriver ldriver) {
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile method

			FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\Screenshots\\Screenshot"
					+ System.currentTimeMillis() + ".png"));
		} catch (IOException e)

		{
			System.out.println(e.getMessage());
		}
	}

	@AfterMethod
	public void afterCleanup() {

		// Closing All the instances opened by the Driver
		driver.quit();

	}

}
