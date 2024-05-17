package com.common.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.common.listeners.TestListener;
import com.common.utilities.Config;
import com.common.utilities.Constants;
import com.google.common.util.concurrent.Uninterruptibles;

@Listeners(TestListener.class)
public class BaseTest {
	protected WebDriver driver;
	private static Logger log = LoggerFactory.getLogger(BaseTest.class);
	
	@BeforeSuite
	public void setUpConfiguration () {
		Config.initialize();
	}
	
	
	@BeforeTest
	public void setUpDriver(ITestContext context) throws MalformedURLException {
		this.driver = Boolean.parseBoolean(Config.getPropertyOf(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
//		this.driver = new ChromeDriver();
		this.driver .manage().window().maximize();
		context.setAttribute(Constants.DRIVER, this.driver);
	}

	private WebDriver getRemoteDriver() throws MalformedURLException {
		Capabilities capabilities = null;
		if(Constants.CHROME.equalsIgnoreCase(Config.getPropertyOf(Constants.BROWSER))) {
			capabilities = new ChromeOptions();
		} else if(Constants.FIREFOX.equalsIgnoreCase(Config.getPropertyOf(Constants.BROWSER))) {
			capabilities = new FirefoxOptions();
		} else if(Constants.EDGE.equalsIgnoreCase(Config.getPropertyOf(Constants.BROWSER))) {
			capabilities  = new EdgeOptions();
		}
		String urlFormat = Config.getPropertyOf(Constants.GRID_URL_FORMAT);
		String hubHost = Config.getPropertyOf(Constants.GRID_HUB_HOST);
		String url = String.format(urlFormat, hubHost);
		log.info("grid url: {}",url);
		return new RemoteWebDriver(new URL(url),capabilities);
	}

	private WebDriver getLocalDriver() {
		if(Constants.CHROME.equalsIgnoreCase(Config.getPropertyOf(Constants.BROWSER))) {
		return new ChromeDriver();
		} else if(Constants.FIREFOX.equalsIgnoreCase(Config.getPropertyOf(Constants.BROWSER))) {
			return new FirefoxDriver();
		} else if(Constants.EDGE.equalsIgnoreCase(Config.getPropertyOf(Constants.BROWSER))) {
			return new EdgeDriver();
		} return null;
	}

	@AfterTest
	public void tearDown() {
		this.driver.quit();
	}

	@AfterMethod
	public void waitForSleep() {
		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
	}
}
