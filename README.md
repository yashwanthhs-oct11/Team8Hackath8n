Here's a nicely formatted and easy-to-read markdown script based on the project description you provided.

-----

# 🏦 EMI Calculator Automation Project

### A Selenium TestNG Hackathon Project

## 📌 Project Overview

This project automates the process of calculating and extracting loan details from a public loan calculator website, such as [emicalculator.net](https://emicalculator.net). The solution uses a robust testing framework to perform various tasks, including calculating specific loan amounts, extracting a full amortization schedule, and validating UI components across different loan tools.

## 🚀 Technologies Used

  - **Java 21:** The core programming language.
  - **Selenium WebDriver:** For automating browser interactions.
  - **TestNG:** The testing framework for test management and execution.
  - **Apache POI:** To handle and save data to Excel files.
  - **Log4j:** For comprehensive logging of test execution.
  - **Allure Reports:** For generating detailed and interactive test reports.

-----

## 📝 Test Scenarios

The project is structured around three main test classes, each addressing a specific problem statement.

### ✅ 1. Car Loan EMI Calculation

  * **Test Class:** `CarLoanTest.java`
  * **Objective:** Calculate the monthly interest and principal amounts for a specific car loan.
  * **Inputs:**
      * **Loan Amount:** ₹15,00,000
      * **Interest Rate:** 9.5%
      * **Tenure:** 1 year
  * **Output:** The test logs the **first month's interest** and **principal** amounts to the console and a log file.

### ✅ 2. Home Loan Amortization Table Extraction

  * **Test Class:** `HomeLoanTest.java`
  * **Objective:** Extract the complete year-on-year amortization table for a home loan.
  * **Inputs:** Valid home loan data is provided to the calculator.
  * **Output:** The entire amortization table is extracted and saved to an Excel file named `EMIData.xlsx` inside the `testdata/` directory.

### ✅ 3. Loan Calculator UI Validation

  * **Test Class:** `LoanCalculatorUITest.java`
  * **Objective:** Validate the user interface and functionality of various loan calculators.
  * **Validations:**
      * Verifies that all input fields (text boxes) and sliders are present and functional.
      * Tests the correct functionality of the year/month tenure toggle button.
  * **Scope:** These validations are designed to be reusable across the **EMI Calculator**, **Loan Amount Calculator**, and **Loan Tenure Calculator**.

-----

## 🗂 Project Structure

```
hackathon/
├── src/
│ ├── base/
│ │ └── BaseTest.java
│ ├── pages/
│ │ ├── HomeLoanPage.java
│ │ ├── LoanCalculatorPage.java
│ │ └── CarLoanCalculatorPage.java
│ └── tests/
│ ├── CarLoanTest.java
│ ├── HomeLoanTest.java
│ └── LoanCalculatorUITest.java
├── logs/
│ └── automation.log
├── testdata/
│ └── EMIData.xlsx
├── pom.xml
├── log4j.properties
├── testng.xml
└── README.md
```

## ⚙️ How to Run the Tests

1.  **Clone the Repository**

    ```bash
    git clone https://github.com/yashwanthhs-oct11/Team8Hackath8n.git
    cd Team8Hackath8n
    ```

2.  **Import as a Maven Project**

      * Open your preferred IDE (Eclipse or IntelliJ).
      * Import the project as an existing Maven project.

3.  **Execute the Tests**

      * Right-click and run the `testng.xml` file to execute the entire test suite.
      * Alternatively, you can run individual test classes by right-clicking on the file and selecting "Run as TestNG Test."

4.  **Generate Allure Report**

      * After running the tests, navigate to the project directory in your terminal.
      * Run the following commands to generate and view the report:

    <!-- end list -->

    ```bash
    allure generate allure-results --clean -o allure-report
    allure serve allure-results
    ```

## 🙌 Credits

  * **Hackathon Team 8**
  * **Developed by:** Yashwanth HS, Ankita, Bhargavi, Sneha, Sruthi, Vaishanvi.
