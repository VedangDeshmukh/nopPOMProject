package com.qa.nop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.nop.utils.ElementUtil;

import io.qameta.allure.Step;

public class DashboardPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	
	private By dashboardheader = By.xpath("//div[@class='content-header']/h1");
	
	private By customersoption = By.xpath("(//ul[@class='nav nav-pills nav-sidebar flex-column nav-legacy']/li[@class='nav-item has-treeview'])[3]");
	private By customerpageoption = By.xpath("//ul[@class='nav nav-treeview']/li[@class='nav-item']//p[text()=' Customers']");
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	@Step("getting header of Dashboard page....")
	public String getDashboardHeader() {
		return elementUtil.doGetText(dashboardheader);
	}
	
	
	@Step("clicking on Customer sub menu and navigating to Customer page....")
	public CustomersPage navigateToCustomersPage() {
		elementUtil.actionsClick(customersoption);
		
		elementUtil.actionsClick(customerpageoption);
		return new CustomersPage(driver);
	}
	
	

}
