package com.flightReservations.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.common.pages.BasePage;

public class FlightSearchPage extends BasePage {

	@FindBy(id="passengers")
	private WebElement passengersSelect;
	
	@FindBy(id="search-flights")
	private WebElement searchFlightsButton;
	
	public FlightSearchPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.passengersSelect));
		return this.passengersSelect.isDisplayed();
	}
	
	public void selectPassengers(String noOfPassengers) {
		Select select = new Select(this.passengersSelect);
		select.selectByValue(noOfPassengers);
	}
	
	public void searchFlights() {
		this.searchFlightsButton.click();
	}

}
