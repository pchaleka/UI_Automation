package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

public class TestBase {

	public static Logger Add_Log = null;
	public Properties configprop;
	public Properties objectsprop;
	
	// Variables for New Customer Screen
	public static WebDriver driver;
	public static String UIUrl;
	public static String userslink;
	public static String newUserLink;
	public static String usernameRep;
	public static String passwordRep;
	public static String emailRep;
	public static String loginbtn;
	public static String username;
	public static String password;
	public static String email;

	// Variables for Customer Filters
	public static String usernameDropdownStr;
	public static String userameDropdownText;
	public static String filterButton;
	public static String fromdate1;
	public static String todate1;
	public static String emailDropdown1;
	public static String emailDropdownText1;

	public TestBase() {
		try {

			Add_Log = Logger.getLogger("rootLogger");

			configprop = new Properties();
			FileInputStream fis1 = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/config/config.properties");
			configprop.load(fis1);
			Add_Log.info("Config.properties file loaded successfully.");

			objectsprop = new Properties();
			FileInputStream fis2 = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/objectsProperty/Objects.properties");
			objectsprop.load(fis2);
			Add_Log.info("Objects.properties file loaded successfully.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
