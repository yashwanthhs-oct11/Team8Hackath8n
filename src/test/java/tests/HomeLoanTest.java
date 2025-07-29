package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomeLoanPage;

import java.io.FileOutputStream;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomeLoanTest extends BaseTest {

    static Logger logger = Logger.getLogger(HomeLoanTest.class);

    @Test
    @Description("This test validates EMI data extraction for Home Loan and writes it to Excel")
    public void extractEMIDataToExcel() {
        try {
            HomeLoanPage homeLoanPage = new HomeLoanPage(driver);

            // Navigate to Home Loan Calculator
            homeLoanPage.navigateToHomeLoanCalculator();
            logger.info("Navigated to Home Loan Calculator page.");

            // Fill Home Loan Form with realistic values
            homeLoanPage.fillHomeLoanDetails();
            logger.info("Filled Home Loan form with valid values.");
            // Extract EMI table data and write to Excel
            extractDataToExcel(homeLoanPage);

            logger.info("✅ EMI data extracted from Home Loan calculator and saved to Excel successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    @Step("Extract EMI table data to Excel")
    public void extractDataToExcel(HomeLoanPage homeLoanPage) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the table is fully loaded and visible
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("noextras")));

        WebElement table = driver.findElement(By.className("noextras"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        FileOutputStream file = new FileOutputStream("testdata/EMIData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("EMI Data");

        int rowNum = 0;

        for (WebElement row : rows) {
            // Get fresh row and cells to avoid stale refs
            List<WebElement> updatedRows = driver.findElement(By.className("noextras")).findElements(By.tagName("tr"));
            WebElement currentRow = updatedRows.get(rowNum);

            Row excelRow = sheet.createRow(rowNum++);
            List<WebElement> cells = currentRow.findElements(By.tagName("td"));
            int cellNum = 0;
            for (WebElement cell : cells) {
                String cellText = cell.getText();
                Cell excelCell = excelRow.createCell(cellNum++);
                excelCell.setCellValue(cellText);
                logger.debug("Extracted Cell: " + cellText);
            }
        }

        workbook.write(file);
        file.close();
        workbook.close();
    }

}
