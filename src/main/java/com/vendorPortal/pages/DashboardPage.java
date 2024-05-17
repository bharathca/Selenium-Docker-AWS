package com.vendorPortal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.pages.BasePage;

public class DashboardPage extends BasePage {
	private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

	@FindBy(id="monthly-earning")
	private WebElement monthlyEarningElement;
	
	@FindBy(id="annual-earning")
	private WebElement annualEarningElement;
	
	@FindBy(id="profit-margin")
	private WebElement profitMarginElement;
	
	
	@FindBy(id="available-inventory")
	private WebElement availableInventoryElement;
	
	@FindBy(css="#dataTable_filter input")
	private WebElement searchInputField;
	
	@FindBy(id="dataTable_info")
	private WebElement numberOfEntriesElement;
	
	@FindBy(css ="img.img-profile")
	private WebElement profilePictureElement;
	
	@FindBy(linkText = "Logout")
	private WebElement logOutLink;
	
	@FindBy(css = "#logoutModal a")
	private WebElement logOutFromPopup;
	

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOfAllElements(monthlyEarningElement));
		return monthlyEarningElement.isDisplayed();
	}
	
	public String getMonthlyEarning() {
		return this.monthlyEarningElement.getText();
	}
	
	public String getAnnualEarning() {
		return this.annualEarningElement.getText();
	}
	
	public String getProfitEarning() {
		return this.profitMarginElement.getText();
	}
	
	public String getAvailableInventory() {
		return this.availableInventoryElement.getText();
	}
	
	public void search(String searchName) {
		searchInputField.sendKeys(searchName);
	}
	
	public int getSearchResultsCount () {
		String searchResultsText = this.numberOfEntriesElement.getText();
		String[] arr = searchResultsText.split(" ");
		log.info("Results count "+Integer.parseInt(arr[5]));
		return Integer.parseInt(arr[5]);
	}
	
	public void logOut() {
		this.profilePictureElement.click();
		this.wait.until(ExpectedConditions.visibilityOf(this.logOutLink));
		this.logOutLink.click();
		this.wait.until(ExpectedConditions.visibilityOf(this.logOutFromPopup));
		this.logOutFromPopup.click();
	}
	

}
