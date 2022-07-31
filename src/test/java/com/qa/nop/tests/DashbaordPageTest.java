package com.qa.nop.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.nop.utils.Constants;
import com.qa.nop.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class DashbaordPageTest extends BaseTest {
	
	@BeforeClass
	public void dashboardPageTestSetup() {
		dashboardPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Description("verify dashbaord page header...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void dashbaordPageHeaderTest() {
		String header = dashboardPage.getDashboardHeader();
		System.out.println("Dashboard Page Header is:"+header);
		
		Assert.assertEquals(header, Constants.DASHBOARD_PAGE_HEADER);
	}
	
	@Description("verify user is able to navigate to Customer page....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void navigateToCustomerPageTest() {
		customersPage =	dashboardPage.navigateToCustomersPage();
		String header =  customersPage.getCustomerPageHeader();
		System.out.println("CustomerPage Header is:"+ header);
		
		Assert.assertEquals(header, Constants.CUSTOMERPAGE_HEADER, Errors.HEADER_MISMATCH_ERROR);
		
		
	}

}
