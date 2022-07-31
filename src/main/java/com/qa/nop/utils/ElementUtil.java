package com.qa.nop.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.nop.factory.DriverFactory;


public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil javaScriptUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
	
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.heighlight)) {
			javaScriptUtil.flash(ele);
		}
		
		return ele;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public boolean doCheckIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean doCheckIsEnable(By locator) {
		return getElement(locator).isEnabled();
	}

	public String doGetAttribute(By locator, String attibuteValue) {
		return getElement(locator).getAttribute(attibuteValue);
	}
	
	public String getPageTitel() {
		return driver.getTitle();
	}

	public List<String> getLinksTextList(By locator) {

		List<String> linksText = new ArrayList<>();

		List<WebElement> eleList = getElements(locator);
		System.out.println("Size of elements is:" + eleList.size());

		for (WebElement e : eleList) {
			String text = e.getText();

			if (!text.isEmpty()) {
				linksText.add(text);
			}
		}

		return linksText;
	}

	public void doClickonLink(By locator, String value) {

		List<WebElement> eleList = getElements(locator);
		System.out.println("Size of elements is:" + eleList.size());

		for (WebElement e : eleList) {

			String text = e.getText();
			if (!text.isEmpty()) {
				System.out.println(text);
				if (text.equals(value)) {
					e.click();
					break;
				}
			}
		}

	}

	public boolean elementIsExist(By locator) {

		List<WebElement> eleList = getElements(locator);

		if (eleList.size() > 0) {
			System.out.println("element is present");
			return true;
		} else {
			System.out.println("element is not present");

			return false;
		}
	}

	// ******************************
	// DropDownUtil*********************************************************

	public void doSelectDropDownValueByIndex(By locator, int index) {
		Select s = new Select(getElement(locator));
		s.selectByIndex(index);
	}

	public void doSelectDropDownValueByValue(By locator, String value) {
		Select s = new Select(getElement(locator));
		s.selectByValue(value);
	}

	public void doSelectDropDownValueByVisibaleText(By locator, String text) {
		Select s = new Select(getElement(locator));
		s.selectByVisibleText(text);
	}

	public List<String> getDropDownOptions(By locator) {

		List<String> optionsList = new ArrayList<>();

		Select s = new Select(getElement(locator));
		List<WebElement> eleList = s.getOptions();
		System.out.println("Size of options:" + eleList.size());

		for (WebElement e : eleList) {
			String text = e.getText();
			optionsList.add(text);

		}

		return optionsList;

	}

	public void selectDropDownValueByOptions(By locator, String value) {

		Select s = new Select(getElement(locator));
		List<WebElement> dropdownelements = s.getOptions();

		for (WebElement e : dropdownelements) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}

	}

	public void selectDropDownValue(By locator, String value) {

		List<WebElement> dropdownelements = getElements(locator);

		for (WebElement e : dropdownelements) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}

		}

	}

	public void selectChoice(By locator, String... value) {

		List<WebElement> eleList = getElements(locator);
		System.out.println("size of elements is:" + eleList.size());

		if (!value[0].equalsIgnoreCase("all")) {
			for (WebElement e : eleList) {
				String text = e.getText();

				for (int j = 0; j < value.length; j++) {
					if (text.equals(value[j])) {
						e.click();
						break;
					}
				}

			}
		} else {
			// all selection logic
			try {
				for (WebElement e : eleList) {
					e.click();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	public void selectValue(By locator, String value) {

		List<WebElement> elelist = getElements(locator);
		for (WebElement e : elelist) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	// ********************** Actions Util
	// *************************************************************

	public void twoLevelMenuhandel(By parent, By child) {

		Actions act = new Actions(driver);
		act.moveToElement(getElement(parent)).perform();
		getElement(child).click();
	}

	public void threeLevelMenuhandel(By parent, By child, By subchild) {

		Actions act = new Actions(driver);
		act.moveToElement(getElement(parent)).perform();
		act.moveToElement(getElement(child)).perform();
		getElement(subchild).click();
	}

	public void rightClickAndSelect(By rightclickelementlocator, By optionslocator, String value) {

		Actions act = new Actions(driver);
		act.contextClick(getElement(rightclickelementlocator)).perform();

		List<WebElement> elelist = getElements(optionslocator);
		for (WebElement e : elelist) {

			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void actionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void actionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	// *********************** Wait Util
	// **********************************************************

	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementPresence(By locator, int timeout, int intervaltime) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, intervaltime);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public List<WebElement> waitForElementsPresence(By locator, int timeout, int intervaltime) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, intervaltime);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public Alert waitForAleart(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAleartText(int timeout) {
		return waitForAleart(timeout).getText();
	}

	public  void acceptAleart(int timeout) {
		waitForAleart(timeout).accept();
	}

	public void dismissAleart(int timeout) {
		waitForAleart(timeout).dismiss();
	}

	public void sendKeysOnAleart(int timeout, String value) {
		waitForAleart(timeout).sendKeys(value);
	}

	public String waitForTitleContains(int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		if (wait.until(ExpectedConditions.titleContains(value))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public String waitForTitleIs(int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		if (wait.until(ExpectedConditions.titleIs(value))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public String waitForUrlIs(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		if (wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
		} else {
			return null;
		}
	}

	public String waitForUrlConatain(String urlvalue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		if (wait.until(ExpectedConditions.urlContains(urlvalue))) {
			return driver.getCurrentUrl();
		} else {
			return null;
		}
	}

	public void waitForFrameAndSwitchToIt(String name, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
	}

	public void waitForFrameAndSwitchToIt(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFrameAndSwitchToIt(int frameindex, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameindex));
	}

	public void waitForFrameAndSwitchToIt(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public WebElement waitForElementToBeClickable(int timeout, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void clickWhenReady(By locator) {
		waitForElementToBeClickable(10, locator).click();
	}

}