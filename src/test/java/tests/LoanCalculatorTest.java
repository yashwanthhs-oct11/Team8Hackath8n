package tests;

import base.BaseTest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.LoanCalculatorPage;
import org.apache.log4j.Logger;

public class LoanCalculatorTest extends BaseTest {

    LoanCalculatorPage loanCalculatorPage;
    static Logger logger = Logger.getLogger(LoanCalculatorTest.class);

    @Test(priority = 1, description = "Validates all UI components of the EMI Calculator including input fields, sliders, and tenure conversions.")
    public void validateEMICalculatorUI() {
        loanCalculatorPage = new LoanCalculatorPage(driver);

        loanCalculatorPage.navigateToEMICalculatorMenu();

        loanCalculatorPage.validateInputFieldAndSlider("loanamount", "loanamountslider", "1500000");
        loanCalculatorPage.validateInputFieldAndSlider("loaninterest", "loaninterestslider", "10.75");
        loanCalculatorPage.validateInputFieldAndSlider("loanterm", "loantermslider", "5");
        loanCalculatorPage.validateTenureYearMonthConversion("loanterm", "//label[input[@id='loanyears']]", "//label[input[@id='loanmonths']]");
        loanCalculatorPage.validateInputFieldAndSlider("loanfees", "loanfeesslider", "10000");

        logger.info("✅ EMI Calculator UI validation completed successfully.");
    }

    @Test(priority = 2, description = "Validates UI behavior of the Loan Amount Calculator including sliders, input fields, and tenure switching.")
    public void validateLoanAmountCalculatorUI() {
        loanCalculatorPage = new LoanCalculatorPage(driver);

        loanCalculatorPage.navigateToCalculator("loan-amount-calc");

        loanCalculatorPage.validateInputFieldAndSlider("loanemi", "loanemislider", "20000");
        loanCalculatorPage.validateInputFieldAndSlider("loaninterest", "loaninterestslider", "10.75");

        driver.findElement(By.xpath("//label[input[@id='loanyears']]")).click();
        loanCalculatorPage.validateInputFieldAndSlider("loanterm", "loantermslider", "5");

        loanCalculatorPage.validateTenureYearMonthConversion("loanterm", "//label[input[@id='loanyears']]", "//label[input[@id='loanmonths']]");
        loanCalculatorPage.validateInputFieldAndSlider("loanfees", "loanfeesslider", "10000");

        logger.info("✅ Loan Amount Calculator UI validation completed successfully.");
    }

    @Test(priority = 3, description = "Validates the Loan Tenure Calculator UI elements such as amount, EMI, interest, and fees sliders.")
    public void validateLoanTenureCalculatorUI() {
        loanCalculatorPage = new LoanCalculatorPage(driver);

        loanCalculatorPage.navigateToCalculator("loan-tenure-calc");

        loanCalculatorPage.validateInputFieldAndSlider("loanamount", "loanamountslider", "1000000");
        loanCalculatorPage.validateInputFieldAndSlider("loanemi", "loanemislider", "20000");
        loanCalculatorPage.validateInputFieldAndSlider("loaninterest", "loaninterestslider", "10.75");
        loanCalculatorPage.validateInputFieldAndSlider("loanfees", "loanfeesslider", "10000");

        logger.info("✅ Loan Tenure Calculator UI validation completed successfully.");
    }
}
