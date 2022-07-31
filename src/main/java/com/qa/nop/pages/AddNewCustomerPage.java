package com.qa.nop.pages;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.nop.utils.ElementUtil;

import io.qameta.allure.Step;

public class AddNewCustomerPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By header = By.xpath("(//h1)[2]");
	
	private By email = By.id("Email");
	private By password = By.id("Password");
	private By fristname = By.id("FirstName");
	private By lastname = By.id("LastName");
	private By male = By.id("Gender_Male");
	private By female = By.id("Gender_Female");
	private By company = By.id("Company");
	
	private By savebtn = By.xpath("//button[@name='save']");
	private By successmsg = By.xpath("//div[@class='alert alert-success alert-dismissable']");
	
	public AddNewCustomerPage(WebDriver driver) {
		this.driver= driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	@Step("getting the header of Add New Customer page...")
	public boolean getAddNewCustomerPageHeaderExist() {
		return elementUtil.doCheckIsDisplayed(header);
	}
	
	@Step("creating the customer...")
	public boolean createCustomer(String email,String password,String fristname,String lastname,String gender,String company) {
		
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.fristname, fristname);
		elementUtil.doSendKeys(this.lastname, lastname);
		
		if(gender.equalsIgnoreCase("Male")) {
			elementUtil.doClick(male);
		}else if(gender.equalsIgnoreCase("Female")) {
			elementUtil.doClick(female);
		}
		
		elementUtil.doSendKeys(this.company, company);
		
		elementUtil.doClick(savebtn);
		
		return elementUtil.doCheckIsDisplayed(successmsg);
	}

}
