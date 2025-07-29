package base;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
	protected WebDriver driver;
	protected Logger logger = Logger.getLogger(getClass().getName());

	/* 
	 * This method runs once before any test methods in the class.
	 * It initializes the WebDriver and opens the target website.
	 */
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
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
