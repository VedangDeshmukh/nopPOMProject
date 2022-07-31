package com.qa.nop.tests;

import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.nop.utils.Constants;
import com.qa.nop.utils.Errors;
import com.qa.nop.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class CustomerPageTest extends BaseTest{
	
	@BeforeClass
	public void customerPageTestSetUP() {
		dashboardPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	
	@Description("verify customer page title ....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void customerPageHeaderTest() {
		customersPage =	dashboardPage.navigateToCustomersPage();
		String header = customersPage.getCustomerPageHeader();
		System.out.println("Customer Page Header is:"+header);
		
		Assert.assertEquals(header, Constants.CUSTOMERPAGE_HEADER, Errors.HEADER_MISMATCH_ERROR);
	}
	
	@Description("verify Add Customer button is available on customer page....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void addNewCustomerBtnExistTest() {
		customersPage =	dashboardPage.navigateToCustomersPage();
		Assert.assertTrue(customersPage.addNewBtnExist());
	}
	
	@Description("verify user is able to navigate to Create Customer page....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void navigateToAddNewCustomerPageTest() {
		
		customersPage =	dashboardPage.navigateToCustomersPage();
		addNewCustomerPage = customersPage.navigateToAddNewCustomerPage();
		assertTrue(addNewCustomerPage.getAddNewCustomerPageHeaderExist());

	}
	
	
	private String generateEmail() {
		Random random = new Random();
		String email = "usertest"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	
//	@DataProvider
//	public Object[][] getCreateCustomerData(){
//		return new Object[][] {
//			{"admin@123", "admit", "fust", "Male", "HPL"},
//			{"admin@123", "Rahul", "Desai", "Male", "Google"},
//			{"admin@123", "Shalani", "raut", "Female", "Amazon"},
//			
//		};
//	}
	
	@DataProvider
	public Object[][] getCreateCustomerData(){
		return ExcelUtil.getTestData("createcustomer");
	}
	
	
	@Description("verify user is able to Create Customers with email:{0} , password:{1}, name:{2}, lastname:{3}, geneder:{4}, company:{5}....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4, dataProvider = "getCreateCustomerData")
	public void createCustomerTest(String password,String name, String lastname,String gender,String company) {
		customersPage =	dashboardPage.navigateToCustomersPage();
		addNewCustomerPage = customersPage.navigateToAddNewCustomerPage();
		
		Assert.assertTrue(addNewCustomerPage.createCustomer(generateEmail(), password, name, lastname, gender, company));
	}

}
