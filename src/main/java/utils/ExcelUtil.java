package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelUtil {


    // Method to extract EMI table data to Excel
    public static void extractDataToExcel(List<WebElement> rows, String filePath) throws Exception {
        // Create a new Excel file and sheet
        FileOutputStream file = new FileOutputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("EMI Data");

        int rowNum = 0;
        for (WebElement row : rows) {
            // Get all table data cells (td)
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Skip rows without any content
            boolean rowHasContent = cells.stream().anyMatch(cell -> !cell.getText().trim().isEmpty());
            if (!rowHasContent) continue;

            Row excelRow = sheet.createRow(rowNum++);
            int cellNum = 0;

            for (WebElement cell : cells) {
                String cellText = cell.getText().trim();

                if (cellText.isEmpty()) continue;

                // Write the cell data to Excel
                Cell excelCell = excelRow.createCell(cellNum++);
                excelCell.setCellValue(cellText);

            }
        }

        // Write data to the Excel file
        workbook.write(file);
        file.close();
        workbook.close();
    }
}
