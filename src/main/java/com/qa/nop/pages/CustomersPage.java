package com.qa.nop.pages;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.nop.utils.ElementUtil;

import io.qameta.allure.Step;

public class CustomersPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By header = By.xpath("(//h1)[2]");
	private By addnewbtn = By.xpath("//div[@class='float-right']/a[@class='btn btn-primary']");
	
	public CustomersPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	@Step("getting the header of Customer page...")
	public String getCustomerPageHeader() {
		return elementUtil.doGetText(header);
	}

	@Step("checking Add New button is availability....")
	public boolean addNewBtnExist() {
		return elementUtil.doCheckIsDisplayed(addnewbtn);
	}
	
	
	@Step("clicking on Add New button and navigateting to New Customer page....")
	public AddNewCustomerPage navigateToAddNewCustomerPage() {
		elementUtil.doClick(addnewbtn);
		
		return new AddNewCustomerPage(driver);
	}
	
	
	
}
