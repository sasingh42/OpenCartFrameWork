package company.qa.opencartapp.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

import io.qameta.allure.Step;

public class DriverFactory {
	WebDriver driver;
	// From Java help to load . properties files
	Properties prop;
	OptionsManager optionsManager;
	public static final Logger log = LogManager.getLogger(DriverFactory.class); //Logger  for every class use this line
	//========================== warn,info,error,fatal (memory issue ) ==================================
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialized driver based on browserName
	 * 
	 * @param browserName
	 */
	@Step("init driver with properties: {0}")
	public WebDriver intiDriver(Properties prop) {
		log.info("Properties :"+prop);
		//browser is key give me value  	
		String browserName = prop.getProperty("browser");
	    
		//System.out.println("Browser Name ==> " + browserName);
		log.info("Browser Name :"+browserName);   //Console & File
		optionsManager = new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));			
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));			
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));			
			break;
		case "safari":
			tlDriver.set(new SafariDriver());			
			break;

		default:
			//System.out.println("plz pass the valid browser name..." + browserName);
			log.error("plz pass the valid browser name..." + browserName); 
			throw new BrowserException("===INVALID BROWSER===");
		}

		getDriver().get(prop.getProperty("url"));// login page url
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	
	/**
	 * getDriver: get the local thready copy of the driver
	 */
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	

	/**
	 * This is used to initialized config properties
	 */
	//mvn clean install -Denv="qa"
	public Properties intiProp() {
		String envName = System.getProperty("env"); //To read env from cmd line
		FileInputStream ip = null;
		prop = new Properties();

		try {
			if (envName == null) {
				log.warn("env is null, hence running the tests on QA env by default...");
				//System.out.println("env is null, hence running the tests on QA env by default...");
				ip = new FileInputStream("./src/test/resourceses/config/qa.config.properties");
				
				//./src/test/resources/config/qa.config.properties
			} else {
				System.out.println("Running tests on env: " + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resourceses/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resourceses/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resourceses/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resourceses/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resourceses/config/prod.config.properties");
					break;

				default:
					log.error("--------Passing wrong Env Name---------- :"+envName);
					throw new FrameworkException("===INVALID ENV NAME==== : "+ envName);
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;

	}
	
	
	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}

}
