package company.deptname.opencartapp.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.MEDIUM_DEFAULT_TIMEOUT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By Locator
	private final By productHeader = By.tagName("h1");
	private final By productImages = By.cssSelector("ul.thumbnails img");
	private final By productMetaData = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[2]/li");
	
	private Map<String,String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	

	public String getProductHeader()
	{
		String header = eleUtil.waitForElementVisible(productHeader, DEFAULT_TIMEOUT).getText();
		System.out.println("This is Product Header :"+header);
		return header;
	}
	public int getProductImagesCount()
	{
		int productImageCount = 
				eleUtil.waitForAllElementsVisible(productImages, MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("Product Image Count :"+productImageCount);
		return productImageCount; 
	}
	
	public Map<String, String> getProductDetailsMap()
	{
		productMap = new HashMap<String,String>();
		productMap.put("product header", getProductHeader());
		productMap.put("product images", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("Full Product Details :"+productMap);
		return productMap;
	}
	
//  HashMap Example - It is not an order based 
	//LinkedHashMap - Maintian order based on Insertion 
	//Sorted order for Keys TreeMap 
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	
	private void getProductMetaData()
	{
		productMap = new HashMap<String,String>();
		List <WebElement> MetaList = eleUtil.waitForAllElementsVisible(productMetaData, DEFAULT_TIMEOUT);
		for (WebElement e : MetaList)
		{
			String text = e.getText();
			String[] meta =  text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
			
		}
	}
//	$2,000.00
//	Ex Tax: $2,000.00
	
	private void getProductPriceData()
	{
		List <WebElement> priceList = eleUtil.waitForAllElementsVisible(productPriceData, DEFAULT_TIMEOUT);
		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);
	}
	
	

}
