package com.flightReservations.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.pages.BasePage;

public class FlightConfirmationPage extends BasePage {
	
	private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);
	
	@FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
	private WebElement flightConfirmationElement;
	
	@FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
	private WebElement flightTotalCostElement;

	public FlightConfirmationPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until((ExpectedConditions.visibilityOf(flightConfirmationElement)));
		return flightConfirmationElement.isDisplayed();
	}
	
	public String getPrice() {
		String confirmation = this.flightConfirmationElement.getText();
		String cost = this.flightTotalCostElement.getText();
		log.info("Flight Confirmation Number: {}",confirmation);
		log.info("Flight Cost: {}",cost);
		return cost;
	}

}
