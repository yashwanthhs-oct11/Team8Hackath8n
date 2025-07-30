package tests;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.Base;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pages.CarLoanPage;
import pages.HomeLoanPage;
import pages.LoanCalculatorPage;
import utils.ExcelUtil;

@Epic("Find the Interest Amount for current year")
public class BaseTest extends Base{

	LoanCalculatorPage loanCalculatorPage;
	static Logger logger = Logger.getLogger(BaseTest.class);

	@Test(priority = 1)
	@Feature("EMI Calculation for Car Loan")
	@Story("Find the EMI for Car with price of 15 Lac, Interest rate of 9.5% & Tenure 1 year; Display the interest amount & principal amount for one month")
	@Severity(SeverityLevel.CRITICAL)
	@Description("This test validates EMI calculation for a car loan")
	public void validateCarLoanEMI() {
		CarLoanPage carLoanPage = new CarLoanPage(driver);

		// Open Car Loan Calculator and fill in the loan details
		carLoanPage.openCarLoanCalculator();
		carLoanPage.fillLoanDetails("1500000", "9.5", "1");

		// Get the calculated principal and interest
		String principal = carLoanPage.getMonthlyPrincipal();
		String interest = carLoanPage.getMonthlyInterest();

		// Log the EMI details as steps in the Allure report
		logEmiDetails(principal, interest);

		System.out.println("Principal Amount for 1 month: ₹" + principal);
		System.out.println("Interest Amount for 1 month: ₹" + interest);
	}

	@Step("Log EMI details: Principal Amount: {0}, Interest Amount: {1}")
	public void logEmiDetails(String principal, String interest) {
		System.out.println("Principal Amount: ₹" + principal + " | Interest Amount: ₹" + interest);
	}


	@Test(priority = 2)
	@Feature("Home Loan EMI Calculator:")
	@Story("From Menu, pick Home Loan EMI Calculator, fill relevant details & extract all the data from  year on year table & store in excel;")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Validates EMI data extraction for Home Loan and writes it to Excel")
	public void extractEMIDataToExcel() {
		try {
			HomeLoanPage homeLoanPage = new HomeLoanPage(driver);


			homeLoanPage.navigateToHomeLoanCalculator();
			logger.info("Navigated to Home Loan Calculator page.");

			homeLoanPage.fillHomeLoanDetails();
			logger.info("Filled Home Loan form with valid values.");

			List<WebElement> rows = homeLoanPage.extractTableRows();

			ExcelUtil.extractDataToExcel(rows, "testdata/EMIData.xlsx");

			logger.info("✅ EMI data extracted from Home Loan calculator and saved to Excel successfully.");

		} catch (Exception e) {
			logger.error("Error extracting EMI data to Excel", e);
			e.printStackTrace();
		}
	}


	@Test(priority = 3)
	@Feature("EMI Calculator Validation")
	@Story("From Menu, pick Loan Calculator and under EMI calculator, do all UI check for text box & scales; change the Loan tenure for year & month")
	@Severity(SeverityLevel.NORMAL)
	@Description("Validates all UI components of the EMI Calculator including input fields, sliders, and tenure conversions.")
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

	@Test(priority = 4)
	@Feature("Loan Amount Calculator Validation")
	@Story("From Menu, pick Loan Calculator and under Loan Amount calculator, do all UI check for text box & scales")
	@Severity(SeverityLevel.NORMAL)
	@Description("Validates all UI components of the EMI Calculator including input fields, sliders, and tenure conversions.")
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

	@Test(priority = 5)
	@Feature("Loan Tenure Calculator Validation")
	@Story("From Menu, pick Loan Calculator and under Loan Tenure calculator, do all UI check for text box & scales")
	@Severity(SeverityLevel.NORMAL)
	@Description("Validates all UI components of the EMI Calculator including input fields, sliders, and tenure conversions.")
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
