package company.deptname.opencartapp.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_TITLE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
//By Locator private + final 
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By forgetpwdlink = By.linkText("Forgotten Password");
	private final By loginBtn = By.xpath("//input[@value='Login']");

	private final By registerLink = By.linkText("Register");

	// Public page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	// public page actions/methods

	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		// String pageTitle=driver.getTitle();
		System.out.println("Login Page Title ==>" + title);
		return title;
	}
	
	
	@Step("getting login page url")
	public String getLoginPageUrl() {
		String pageUrl = eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		// String pageUrl=driver.getCurrentUrl();
		System.out.println("Login Page Url ==> " + pageUrl);
		return pageUrl;
	}

	@Step("checking forgot pwd link exist")
	public boolean isForgetPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgetpwdlink);
	}

	
	@Step("login with valid username: {0} and password: {1}")   //Based on Index
	// paris@gmail.com <=====> pwd: dowparis
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("UserName : " + un + " Password : " + pwd);
		eleUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(un);
		// driver.findElement(email).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		// driver.findElement(password).sendKeys(pwd);
		eleUtil.doClick(loginBtn);
		// driver.findElement(loginBtn).click();
		return new AccountsPage(driver);
	}

	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}

}
