package company.deptname.opencartapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage  {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By resultsProduct = By.cssSelector("div.product-thumb"); //Indirect 

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getResultsProductCount()
	{
		int searchCount = eleUtil.waitForAllElementsVisible(resultsProduct ,  AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("Total Number of Search products :"+searchCount);
		return searchCount;
	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		System.out.println("Product Name :"+productName); 
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}

}
