package utils;

import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelUtil {

    /* 
     * Writes the contents of a table (represented as a list of WebElement rows) 
     * into an Excel file with the given file name.
     */
    public static void writeTableToExcel(List<WebElement> rows, String fileName) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("EMI Data");

        int rowNum = 0;
        for (WebElement row : rows) {
            Row excelRow = sheet.createRow(rowNum++);
            List<WebElement> cells = row.findElements(org.openqa.selenium.By.tagName("td"));
            int cellNum = 0;
            for (WebElement cell : cells) {
                Cell excelCell = excelRow.createCell(cellNum++);
                excelCell.setCellValue(cell.getText());
            }
        }

        FileOutputStream out = new FileOutputStream(fileName);
        workbook.write(out);
        out.close();
        workbook.close();
    }
}
