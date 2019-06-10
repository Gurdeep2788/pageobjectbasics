package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.Utilities;

public class Page {

	public static WebDriver driver;
	public static TopMenu menu;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\w2a\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;


	public Page()  { 

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\w2a\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("Config file Loaded !!!");

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\w2a\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("OR file Loaded !!!");


			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");

			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox Launched !!!");

			} else if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\executables\\chromedriver.exe");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				driver = new ChromeDriver(options);

				log.debug("Chrome Browser Launched !!!");
			} else if (config.getProperty("browser").equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.debug("IE Launched");
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
			//log.debug("Browser Maximized !!!");
			driver.get(config.getProperty("testsiteurl"));
			//log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			
			menu =  new TopMenu(driver); 
		}

	}
	
	public boolean isElementPresent(By by)
	{
		try{

			driver.findElement(by);
			return true;

		}catch(NoSuchElementException e)
		{
			return false;
		}
	}


	public static void verifyEquals(String actual, String expected) throws IOException {
		try {
			Assert.assertEquals(actual, expected);	
		}
		catch(Throwable t){

			Utilities.captureScreenshot();
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Reporter.log("</br>"+"Verification failure : "+t.getMessage()+"<br>"); 	 			
			Reporter.log("<a target= \"_blank\" href="+Utilities.screenshotName+"><img src ="+Utilities.screenshotName+" height=100 width=100\"></img></a> ");		
		}

	}

	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		//log.debug("Clicking on an Element : "+locator);
		//test.log(LogStatus.INFO, "Clicking on : " + locator);
	}
	
	public static void IsElementClickable(String locator)
	{
			if (locator.endsWith("_CSS")) {
			WebDriverWait explicitWait1 = new WebDriverWait(driver, 10);
			explicitWait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(locator))));			
		} else if (locator.endsWith("_XPATH")) {
			WebDriverWait explicitWait1 = new WebDriverWait(driver, 10);
			explicitWait1.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator))));
		} else if (locator.endsWith("_ID")) {
			WebDriverWait explicitWait1 = new WebDriverWait(driver, 10);
			explicitWait1.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(locator))));
		}
			
	}
	
	
	public void type(String locator, String Value){
		if(locator.endsWith("_CSS"))
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(Value);
		else if(locator.endsWith("_XPATH"))
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(Value);
		else if(locator.endsWith("_ID"))
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(Value);	
		//test.log(LogStatus.INFO, "Typing in : "+locator+" entered value as "+Value);
	}

	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
	}
	
	
	public static void quit(){
		driver.quit();
	}

	
}
