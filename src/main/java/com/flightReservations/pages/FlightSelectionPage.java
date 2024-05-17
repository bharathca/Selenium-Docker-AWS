package com.flightReservations.pages;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.common.pages.BasePage;

public class FlightSelectionPage extends BasePage {

	@FindBy(name="departure-flight")
	private List<WebElement> departureFlightsOptions;
	
	@FindBy(name="arrival-flight")
	private List<WebElement> arrivalFlightsOptions;
	
	@FindBy(id="confirm-flights")
	private WebElement confirmFlightsButton;
	
	public FlightSelectionPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(confirmFlightsButton));
		return this.confirmFlightsButton.isDisplayed();
	}
	
	public void selectFlights() {
		int randomNumber = ThreadLocalRandom.current().nextInt(0,9);
		this.departureFlightsOptions.get(randomNumber).click();
		this.arrivalFlightsOptions.get(randomNumber).click();
	}
	
	public void confirmFlight() {
		this.confirmFlightsButton.click();
	}

}
