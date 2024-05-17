package com.flightReservations.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.common.tests.BaseTest;
import com.common.utilities.Config;
import com.common.utilities.Constants;
import com.common.utilities.ReadJson;
import com.flightReservations.pages.FlightConfirmationPage;
import com.flightReservations.pages.FlightSearchPage;
import com.flightReservations.pages.FlightSelectionPage;
import com.flightReservations.pages.RegistrationConfirmationPage;
import com.flightReservations.pages.RegistrationPage;
import com.flightReservations.tests.model.FlightReservationTestData;


public class FlightReservationTest extends BaseTest{

	private FlightReservationTestData testData;
	@BeforeTest
	@Parameters("testDataPath")
	public void setParameters(String testDataPath) {
		this.testData = ReadJson.getTestData(testDataPath,FlightReservationTestData.class);
	}

	@Test
	public void userRegistrationTest() {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo(Config.getPropertyOf(Constants.FLIGHT_RESERVATION_URL));
		Assert.assertTrue(registrationPage.isAt());

		registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
		registrationPage.enterUserCredentialsDetails(testData.email(), testData.password());
		registrationPage .enterAddress(testData.street(), testData.city(), testData.zip());
		registrationPage.register();
	}

	@Test(dependsOnMethods = "userRegistrationTest")
	public void registrationConfirmationTest() {
		RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		Assert.assertTrue(registrationConfirmationPage.isAt());
		Assert.assertEquals(registrationConfirmationPage.getNameFromConfirmationMessage(), testData.firstName());
		registrationConfirmationPage.goToFlightSearch();
	}

	@Test(dependsOnMethods = "registrationConfirmationTest")
	public void flightSearchTest() {
		FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
		Assert.assertTrue(flightSearchPage.isAt());
		flightSearchPage.selectPassengers(testData.passengersCount() );
		flightSearchPage.searchFlights();
	}

	@Test(dependsOnMethods = "flightSearchTest")
	public void flightSelectTest() {
		FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
		Assert.assertTrue(flightSelectionPage.isAt());
		flightSelectionPage.selectFlights();
		flightSelectionPage.confirmFlight();
	}

	@Test(dependsOnMethods = "flightSelectTest")
	public void flightConfirmationTest() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		Assert.assertTrue(flightConfirmationPage.isAt());
		Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
	}
}
