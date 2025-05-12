package company.deptname.opencartapp.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By headers = By.xpath("//div[@id='content']/h2");
	private final By search = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button"); //Indirect 

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		// TODO Auto-generated method stub
		String title = eleUtil.waitForTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		// String pageTitle=driver.getTitle();
		System.out.println("Login Page Title ==>" + title);
		return title;

	}

	public String getAccPageUrl() {
		String pageUrl = eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		// String pageUrl=driver.getCurrentUrl();
		System.out.println("Home Page Url ==> " + pageUrl);
		return pageUrl;
	}

	public List<String> getAccPageHeaders() {
		List<WebElement> headerList = eleUtil.getElements(headers);// Encapsulation private variable is used in public
																	// methods
		List<String> headerValList = new ArrayList<String>();
		for (WebElement e : headerList) {
			String text = e.getText();
			headerValList.add(text);
		}
		System.out.println("Account Page HeadersList : " + headerValList);
		return headerValList;

	}

	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("Searching for Product : "+searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);

	}

}
