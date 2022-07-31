package com.qa.nop.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	
	public WebDriver driver;
	
	public static String heighlight ;
	
	private OptionManager optionManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		
		System.out.println("Browser is launching:"+browserName);
		
		heighlight = prop.getProperty("heighlight");
		optionManager = new OptionManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver= new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
		}else if(browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.chromedriver().setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}else {
			System.out.println("Browser is not supported:"+browserName);
		}
		
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
		
	}
	
	
	/**
	 * Method to get the local copy of driver
	 */
	public WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	
	
	/**
	 * Method is use to read the properties from the properties file on the basic of given environment
	 * @return Properties prop
	 */
	public Properties initProperties() {
		
		Properties prop = null;
		FileInputStream ip = null;

		String env = System.getProperty("env"); // mvn clean install -Denv="qa"

		try {
			if (env == null) {
				System.out.println("Running on Environment: PRODUCTION");

				ip = new FileInputStream("./src/test/resources/config/master.qaconfig.properties");

			} else {

				System.out.println("Running on Environment:"+env);

				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.qaconfig.properties");
					break;

				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.qaconfig.properties");
					break;

				default:
					System.out.println("No ENV found.....");
					throw new Exception("NOENVFOUNDEXCEPTION");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			prop = new Properties();
			prop.load(ip);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
		
	}
	
	
	/**
	 * This method takes the screenshot's
	 * @return
	 */
	public String getScreenshot() {
				
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		
		String path = System.getProperty("user.dir")+"/screenshot/"+ System.currentTimeMillis()+".png";
		
		File destFile = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	
	

}
