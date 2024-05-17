package com.flightReservations.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.common.pages.BasePage;

public class RegistrationPage extends BasePage {
	@FindBy(id = "firstName")
	private WebElement firstNameInputField;

	@FindBy(id = "lastName")
	private WebElement lastNameInputField;

	@FindBy(id = "email")
	private WebElement emailInputField;

	@FindBy(id = "password")
	private WebElement passwordInputField;

	@FindBy(name = "street")
	private WebElement streetInputField;

	@FindBy(name = "city")
	private WebElement cityInputField;

	@FindBy(name = "zip")
	private WebElement zipInputField;

	@FindBy(id = "register-btn")
	private WebElement registerButton;

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	public void goTo(String url) {
		this.driver.get(url);
	}
	
	public void enterUserDetails(String firstName, String lastName ) {
		this.firstNameInputField.sendKeys(firstName);
		this.lastNameInputField.sendKeys(lastName);
	}
	
	public void enterUserCredentialsDetails(String email, String password) {
		this.emailInputField.sendKeys(email);
		this.passwordInputField.sendKeys(password);		
	}
	
	public void enterAddress(String street, String city, String zip) {
		this.streetInputField.sendKeys(street);
		this.cityInputField.sendKeys(city);
		this.zipInputField.sendKeys(zip);
	}
	
	public void register() {
		this.registerButton.click();
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(firstNameInputField));
		return this.firstNameInputField.isDisplayed();
	}
}
