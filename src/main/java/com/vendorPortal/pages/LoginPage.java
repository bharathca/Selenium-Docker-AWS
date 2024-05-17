package com.vendorPortal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends com.common.pages.BasePage{

	@FindBy(id="username")
	private WebElement enterUserNameInputField;
	
	@FindBy(id="password")
	private WebElement enterPasswordInputField;
	
	@FindBy(id="login")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOfAllElements(enterUserNameInputField));
		return enterUserNameInputField.isDisplayed();
	}
	
	public void goTo(String url) {
		this.driver.get(url);
	}
	
	public void login(String username, String password) {
		this.enterUserNameInputField.sendKeys(username);
		this.enterPasswordInputField.sendKeys(password);
		this.loginButton.click();
	}

}
