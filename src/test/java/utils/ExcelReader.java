package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class ExcelReader {

    static Workbook book;
    static Sheet sheet;

    public static void openExcel(String filePath) {

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath); //navigate to path (link from path to java program)
            book = new XSSFWorkbook(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSheet(String sheetName) {

        sheet = book.getSheet(sheetName);
    }

    public static int getRowCount() {

        return sheet.getPhysicalNumberOfRows();
    }

    public static int getColsCount(int rowIndex) {

        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    public static String getCellData(int rowIndex, int columnIndex) {

        return sheet.getRow(rowIndex).getCell(columnIndex).toString();
    }

    public static List<Map<String, String>> excelListIntoMap(String filePath, String sheetName) {

        openExcel(filePath);
        getSheet(sheetName);

        List<Map<String, String>> listData = new ArrayList<>();

        for (int row = 1; row < getRowCount(); row++) {

            Map<String, String> employeeEntry = new LinkedHashMap<>();

            for (int col = 0; col < getColsCount(row); col++) {

                employeeEntry.put(getCellData(0, col), getCellData(row, col));
            }

            listData.add(employeeEntry);
        }

        return listData;

    }
}

