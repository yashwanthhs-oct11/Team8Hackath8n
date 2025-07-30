package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeLoanPage {
    WebDriver driver;

    public HomeLoanPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
  //bhargavi changes
    @FindBy(xpath = "//*[@id='menu-item-dropdown-2696']")
    WebElement emiCalculatorDropdown;

    @FindBy(xpath = "//*[@id='menu-item-3294']/a")
    WebElement homeLoanCalculator;

    @FindBy(id = "homeprice")
    WebElement homeValue;

    @FindBy(id = "downpayment")
    WebElement margin;

    @FindBy(id = "homeloaninsuranceamount")
    WebElement loanInsurance;

    @FindBy(id = "homeloanamount")
    WebElement loanAmount;

    @FindBy(id = "homeloaninterest")
    WebElement interestRate;

    @FindBy(id = "homeloanterm")
    WebElement loanTenure;

    @FindBy(id = "loanfees")
    WebElement loanFees;

    @FindBy(id = "onetimeexpenses")
    WebElement oneTimeExpenses;

    @FindBy(id = "propertytaxes")
    WebElement propertyTax;

    @FindBy(className = "noextras")
    WebElement dataTable;

    /* 
     * Navigates to the Home Loan Calculator page using the dropdown menu.
     */
    public void navigateToHomeLoanCalculator() {
        emiCalculatorDropdown.click();
        homeLoanCalculator.click();
    }

    /* 
     * Fills in the Home Loan Calculator form with predefined values.
     */
    public void fillHomeLoanDetails() {
        clearAndType(homeValue, "500000");
        clearAndType(margin, "50000");
        clearAndType(loanInsurance, "10000");
        clearAndType(loanAmount, "450000");
        clearAndType(interestRate, "7.5");
        clearAndType(loanTenure, "15");
        clearAndType(loanFees, "12000");
        clearAndType(oneTimeExpenses, "8000");
        clearAndType(propertyTax, "6000");
        propertyTax.sendKeys(Keys.ENTER);
    }

    /* 
     * Utility method to clear existing text and type a new value into a form field.
     */
    private void clearAndType(WebElement element, String value) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        element.sendKeys(value);
    }

    /* 
     * Returns the WebElement representing the data table shown on the UI.
     */
    public WebElement getDataTable() {
        return dataTable;
    }
}
