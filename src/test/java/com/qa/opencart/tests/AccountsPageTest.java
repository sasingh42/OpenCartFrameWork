package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	// BT -- BC
	// Test Driven Approach Start form this page
	@BeforeClass
	public void accPageSetup() {
		// Not eligible for Garbage Collector
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(), HOME_PAGE_TITLE);
	}

	@Test
	public void accPageUrlTest() {
		// Assert.assertEquals();
		Assert.assertTrue(accPage.getAccPageUrl().contains(HOME_PAGE_FRACTION_URL));

	}

	@Test
	public void accPageHeadersTest() {
		List<String> actualHearderList = accPage.getAccPageHeaders();
		Assert.assertEquals(actualHearderList, AppConstants.expAppPageHeaderList);

	}
}
