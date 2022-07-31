package com.qa.nop.pages;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.nop.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By email = By.id("Email");
	private By password = By.id("Password");
	private By loginbtn = By.xpath("//button[text()='Log in']");
	private By loginheader = By.xpath("//strong[text()='Welcome, please sign in!']");
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	@Step("getting title of Login page....")
	public String getLoginPageTitle(){
		return elementUtil.getPageTitel();
	}
	
	
	@Step("getting login page Header....")
	public String getLoginPageHeader() {
		return elementUtil.doGetText(loginheader);
	}
	
	
	@Step("login to the application with correct username and password....")
	public DashboardPage doLogin(String username, String password) {
		elementUtil.doSendKeys(this.email, username);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doClick(loginbtn);
		
		return new DashboardPage(driver);
				
	}

}
