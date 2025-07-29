package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoanCalculatorPage {
    WebDriver driver;

    public LoanCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* 
     * Validates a text input field and its associated slider by checking visibility, 
     * interactivity, and then entering a value into the input.
     */
    public void validateInputFieldAndSlider(String textBoxId, String sliderId, String valueToEnter) {
        WebElement textBox = driver.findElement(By.id(textBoxId));
        Assert.assertTrue(textBox.isDisplayed(), textBoxId + " textbox not visible");
        Assert.assertTrue(textBox.isEnabled(), textBoxId + " textbox not enabled");

        textBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        textBox.sendKeys(valueToEnter);

        WebElement slider = driver.findElement(By.id(sliderId));
        Assert.assertTrue(slider.isDisplayed(), sliderId + " slider not visible");

        WebElement sliderHandle = slider.findElement(By.className("ui-slider-handle"));
        Assert.assertTrue(sliderHandle.isDisplayed(), "Slider handle not visible for " + sliderId);
    }

    /* 
     * Validates the conversion between tenure in years and months by interacting with radio buttons 
     * and comparing expected values.
     */
    public void validateTenureYearMonthConversion(String tenureInputId, String yearRadioXpath, String monthRadioXpath) {
        WebElement tenureInput = driver.findElement(By.id(tenureInputId));
        tenureInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, "5");

        WebElement yearLabel = driver.findElement(By.xpath(yearRadioXpath));
        yearLabel.click();
        String yearValue = tenureInput.getAttribute("value");

        WebElement monthLabel = driver.findElement(By.xpath(monthRadioXpath));
        monthLabel.click();
        String monthValue = tenureInput.getAttribute("value");

        Assert.assertEquals(Integer.parseInt(monthValue), Integer.parseInt(yearValue) * 12,
                "Tenure conversion from years to months failed.");
        yearLabel.click(); // Switch back
    }

    /* 
     * Navigates to a specific calculator by its ID.
     */
    public void navigateToCalculator(String calculatorId) {
        driver.findElement(By.id(calculatorId)).click();
    }

    /* 
     * Navigates to the EMI Calculator option from the dropdown menu.
     */
    public void navigateToEMICalculatorMenu() {
        driver.findElement(By.xpath("//*[@id=\"menu-item-dropdown-2696\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"menu-item-2423\"]/a")).click();
    }
}
