package com.qa.opencart.tests;

//import com.qa.opencart.constants.AppConstants;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_TITLE;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Feature("F 50: Open Cart - Login Feature")  //AllureReport
@Epic("Epic 100: design pages for open cart application")
@Story("US 101: implement login page for open cart application")
public class LoginPageTest extends BaseTest{
    //Not able to Run it as TestNg plugin is missing 
	//Note Test will run based on Alphabetical Order
	
	@Description("checking open cart login page title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Shekhar")
	@Test(description = "checking login title") 
	public void loginPageTitleTest()
	{
		String acttitle=loginPage.getLoginPageTitle();
		ChainTestListener.log("Checking Login Page Title :"+acttitle);   //For Logs
		Assert.assertEquals(acttitle, LOGIN_PAGE_TITLE);
	}
	
	@Description("checking open cart login page url...")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Naveen")
	@Test
	public void loginPageUrlTest()
	{
		String actUrl=loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(LOGIN_PAGE_FRACTION_URL)); //If True it will be passed
	}
	
	
	
	@Description("checking open cart login page has forgot pwd link...")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Naveen")
	@Test
	public void forgetPwdLinkExistTest()
	{
		Assert.assertTrue(loginPage.isForgetPwdLinkExist());
	}
	
	
	@Description("check user is able to login with valid user credentials...")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Naveen")
	@Test(priority=Short.MAX_VALUE)
	public void loginTest()
	{
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(),HOME_PAGE_TITLE);
	}
	
	@Test(enabled = false, description = "WIP -- forgot pwd check")
	public void forgotPwd() {
		System.out.println("forgot pwd ");
	}
	
	
}
