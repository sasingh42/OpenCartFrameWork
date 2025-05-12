package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void ProductInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider // 2D Array it Returns
	public  Object[][] getProductTestData() {
		return new Object[][]{ 
			{ "macbook", "MacBook Pro" }, //2 Coulmn 
			{ "macbook", "MacBook Air" }, 
			{ "imac", "iMac" } 
			};
	}

	// AAA Arrange Act Assert(1)
	@Test(dataProvider = "getProductTestData")  //This is data providername link this supply method name
	public void ProductHeaderTest(String searchKey,String producName) { //Two holding parameters due to 2 column 
		searchPage = accPage.doSearch(searchKey);
		productInfoPage = searchPage.selectProduct(producName);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, producName);

	}
	
	@DataProvider // 2D Array it Returns
	public  Object[][] getProductImageTestData() {
		return new Object[][]{ 
			{ "macbook", "MacBook Pro",4 },
			{ "macbook", "MacBook Air",4 }, 
			{ "imac", "iMac",3 } 
			};
	}
	
	@DataProvider
	public Object[][] getProductImageCSVTestData()
	{
		return CSVUtil.csvData("product");
	}

	@Test(dataProvider = "getProductImageCSVTestData")
	public void ProductImageCountTest(String searchKey,String productName, String expectedImageCount) {
		searchPage = accPage.doSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actImageCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(String.valueOf(actImageCount), expectedImageCount);
	}

	@Test
	public void productInfoTest() {
		searchPage = accPage.doSearch("macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductDeatilsMap = productInfoPage.getProductDetailsMap();
		// Hard Assertion if any Assertion fail then next line will not execute
//		Assert.assertEquals(actualProductDeatilsMap.get("Brand"), "Apple");
//		Assert.assertEquals(actualProductDeatilsMap.get("Product Code"), "Product 18");
//		Assert.assertEquals(actualProductDeatilsMap.get("Reward Points"), "800");
//		Assert.assertEquals(actualProductDeatilsMap.get("Availability"), "Out Of Stock");
//		Assert.assertEquals(actualProductDeatilsMap.get("productPrice"), "$2,000.00");
//		Assert.assertEquals(actualProductDeatilsMap.get("exTaxPrice"), "$2,000.00");

		// Soft Assertion if any Assertion fail then next line will be executed
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualProductDeatilsMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductDeatilsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductDeatilsMap.get("Reward Points"), "800");
		softAssert.assertEquals(actualProductDeatilsMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actualProductDeatilsMap.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(actualProductDeatilsMap.get("exTaxPrice"), "$2,000.00");

		softAssert.assertAll();// How many Assertion pass and fail Show Failure Information

	}

}
