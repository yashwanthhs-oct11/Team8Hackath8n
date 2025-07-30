package base;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class Base {
	protected WebDriver driver;
	protected Logger logger = Logger.getLogger(getClass().getName());

	/* 
	 * This method runs once before any test methods in the class.
	 * It initializes the WebDriver and opens the target website.
	 */
	@BeforeClass
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-notifications");
        options.addArguments("start-maximized");
        options.addArguments("--ignore-certificate-errors");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://emicalculator.net/");
	}

	/* 
	 * This method runs once after all test methods in the class have executed.
	 * It closes the browser and quits the WebDriver session.
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
