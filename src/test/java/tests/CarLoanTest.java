package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pages.CarLoanPage;

public class CarLoanTest extends BaseTest {

    @Test
    @Owner("Yashwanth")
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
}
