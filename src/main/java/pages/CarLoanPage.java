package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarLoanPage {
    WebDriver driver;

    public CarLoanPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='car-loan']/a")
    WebElement carLoanTab;

    @FindBy(id = "loanamount")
    WebElement loanAmount;

    @FindBy(id = "loaninterest")
    WebElement interestRate;

    @FindBy(id = "loanterm")
    WebElement loanTenure;

    @FindBy(xpath = "//*[@id='emiamount']/p/span")
    WebElement principalAmount;

    @FindBy(xpath = "//*[@id='emitotalinterest']/p/span")
    WebElement interestAmount;

    /* 
     * Clicks on the Car Loan tab to open the car loan calculator section.
     */
    public void openCarLoanCalculator() {
        carLoanTab.click();
    }

    /* 
     * Fills in the loan amount, interest rate, and loan tenure fields
     * with the provided input values.
     */
    public void fillLoanDetails(String amount, String rate, String tenure) {
        loanAmount.clear();
        loanAmount.sendKeys(amount);

        interestRate.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", interestRate);
        interestRate.sendKeys(rate);

        loanTenure.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", loanTenure);
        loanTenure.sendKeys(tenure);
    }

    /* 
     * Returns the calculated monthly principal amount as a string.
     */
    public String getMonthlyPrincipal() {
        return principalAmount.getText();
    }

    /* 
     * Returns the calculated monthly interest amount as a string.
     */
    public String getMonthlyInterest() {
        return interestAmount.getText();
    }
}
