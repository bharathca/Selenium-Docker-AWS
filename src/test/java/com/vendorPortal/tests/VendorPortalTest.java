package com.vendorPortal.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.common.tests.BaseTest;
import com.common.utilities.Config;
import com.common.utilities.Constants;
import com.common.utilities.ReadJson;
import com.vendorPortal.pages.DashboardPage;
import com.vendorPortal.pages.LoginPage;
import com.vendorPortal.tests.model.VendorPortalTestData;

public class VendorPortalTest extends BaseTest{
	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private VendorPortalTestData testData;
	@BeforeTest
	@Parameters("testDataPath")
	public void setPageObjects(String testDataPath) {
		this.loginPage = new LoginPage(driver);
		this.dashboardPage = new DashboardPage(driver);
		this.testData = ReadJson.getTestData(testDataPath, VendorPortalTestData.class);
	}

	@Test
	public void loginTest() {
		loginPage.goTo(Config.getPropertyOf(Constants.VENDOR_PORTAL_URL));
		Assert.assertTrue(loginPage.isAt());
		loginPage.login(testData.username(), testData.password());
	}

	@Test(dependsOnMethods = "loginTest")
	public void dashboardTest() {
		Assert.assertTrue(dashboardPage.isAt());
		Assert.assertEquals(dashboardPage.getMonthlyEarning(),testData.monthlyEarning());
		Assert.assertEquals(dashboardPage.getAnnualEarning(),testData.annualEarning());
		Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.availableInventory());
		Assert.assertEquals(dashboardPage.getProfitEarning(),testData.profitMargin());
		
		dashboardPage.search(testData.searchKeyword());	
		Assert.assertEquals(dashboardPage.getSearchResultsCount(),testData.searchResultsCount());
	}
	
	@Test(dependsOnMethods = "dashboardTest")
	public void logoutTest() {
		dashboardPage.logOut();
		Assert.assertTrue(loginPage.isAt());
		Assert.assertEquals(driver.getCurrentUrl(), "https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
	}
}
