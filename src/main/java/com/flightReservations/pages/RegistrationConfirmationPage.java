package com.flightReservations.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.common.pages.BasePage;

public class RegistrationConfirmationPage extends BasePage {

	@FindBy(id="go-to-flights-search")
	private WebElement goToFlightSearchButton;
	
	@FindBy(css = "#registration-confirmation-section p  b")
	private WebElement name;
	
	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(goToFlightSearchButton));
		return this.goToFlightSearchButton.isDisplayed();
	}
	
	public String getNameFromConfirmationMessage() {
		return this.name.getText();
	}
	
	public void goToFlightSearch() {
		this.goToFlightSearchButton.click();
	}
}
