package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;

import company.deptname.opencartapp.pages.AccountsPage;
import company.deptname.opencartapp.pages.LoginPage;
import company.deptname.opencartapp.pages.ProductInfoPage;
import company.deptname.opencartapp.pages.RegisterPage;
import company.deptname.opencartapp.pages.SearchResultsPage;
import company.qa.opencartapp.factory.DriverFactory;
import io.qameta.allure.Description;

//@Listeners(ChainTestListener.class) //It will be keep running in the background and listen test class and test methods capture status pass and fails
public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	
	@Description("init the driver and properties")
	@Parameters({"browser"}) //Pass parameter from TestNg to BeforeTest
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		//First init Prop
		prop = df.intiProp();
		
		
		//browserName is passed from .xml file
		if(browserName!=null) {
			prop.setProperty("browser", browserName);  //During Runtime config file
		}
		//Pass Prop and use as per the requirement
		driver = df.intiDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	
	
	@AfterMethod //will be running after each @test method
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {//only for failure test cases -- true
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		
		//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");


	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
