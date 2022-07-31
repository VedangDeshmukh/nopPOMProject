package com.qa.nop.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.nop.utils.Constants;
import com.qa.nop.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {
	
	
	@Description("verify login page title...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is:"+loginPage);
		
		Assert.assertEquals(title, Constants.LOGINPAGE_TITLE, Errors.TITLE_MISMATCHERROR);
	}
	
	
	@Description("verify login page header...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageHeaderTest() {
		String header = loginPage.getLoginPageHeader();
		System.out.println("Login Page Header is:"+header);
		
		Assert.assertEquals(header, Constants.LOGIN_PAGE_HEADER, Errors.HEADER_MISMATCH_ERROR);
	}
	
	
	@Description("verify user is able to login with correct username:{0} and password:{1}...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void doLogin() {
		dashboardPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String header = dashboardPage.getDashboardHeader();
		System.out.println("Home Page header is:"+header);
		
		Assert.assertEquals(header, Constants.DASHBOARD_PAGE_HEADER, Errors.HEADER_MISMATCH_ERROR);
	}
	
	

}
