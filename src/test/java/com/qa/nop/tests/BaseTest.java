package com.qa.nop.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.nop.factory.DriverFactory;
import com.qa.nop.pages.AddNewCustomerPage;
import com.qa.nop.pages.CustomersPage;
import com.qa.nop.pages.DashboardPage;
import com.qa.nop.pages.LoginPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	Properties prop;
	
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	CustomersPage customersPage;
	AddNewCustomerPage addNewCustomerPage;
	
	@BeforeTest
	public void setup() {
		df= new DriverFactory();
		prop = df.initProperties();
		driver = df.initDriver(prop);
		
		loginPage = new LoginPage(driver);
		
	}
	
	
	@AfterTest
	public void tearDown() {
		//driver.close();
	}

}
