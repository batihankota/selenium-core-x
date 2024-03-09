package definitions;

import settings.DriverSettings;
import settings.SelectorType;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static helpers.SelectorHelper.getSelector;

public class FileDefinitions {

    private static final Logger logger = Logger.getLogger(FileDefinitions.class);
    private static final String FILE_MAP_PATH = "src/test/java/FileMap/";

    private static WebDriver getDriver() {
        return DriverSettings.getWebDriver();
    }

    public static void writeToElementFromExcel(String fileName, int rowNum, int colNum, SelectorType selectorType, String locator) {
        try {
            String filePath = Paths.get(FILE_MAP_PATH, fileName).toString();
            String data = readExcelData(filePath, rowNum, colNum);
            By byLocator = getSelector(locator, selectorType);
            WebElement element = getDriver().findElement(byLocator);
            if (element != null) {
                element.clear(); // Önce mevcut içeriği temizle
                element.sendKeys(data);
                logger.info("Excel'den alınan veri '" + data + "', '" + byLocator + "' elementine yazıldı.");
            } else {
                logger.error("Belirtilen locator ile eşleşen element bulunamadı: " + locator + " (" + selectorType + ")");
            }
        } catch (IOException e) {
            logger.error("Dosya okunurken bir hata oluştu: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Excel'den veri okunurken veya elemente yazılırken bir hata oluştu: " + e.getMessage());
            throw new RuntimeException("Elementten veri okunurken veya Excelden veri yazılırken bir hata oluştu", e);
        }
    }

    public static void writeToExcelFromElement(String fileName, int rowNum, int colNum, SelectorType selectorType, String locator) {
        try {
            String filePath = Paths.get(FILE_MAP_PATH, fileName).toString();
            By byLocator = getSelector(locator, selectorType);
            WebElement element = getDriver().findElement(byLocator);
            if (element != null) {
                String data = element.getText();
                writeExcelData(filePath, rowNum, colNum, data);
                logger.info("'" + byLocator + "' elementinden alınan veri '" + data + "', Excelden '" + filePath + "' dosyasına yazıldı.");
            } else {
                logger.error("Belirtilen locator ile eşleşen element bulunamadı: " + locator + " (" + selectorType + ")");
            }
        } catch (Exception e) {
            logger.error("Elementten veri okunurken veya Excelden veri yazılırken bir hata oluştu: " + e.getMessage());
            throw new RuntimeException("Elementten veri okunurken veya Excelden veri yazılırken bir hata oluştu", e);
        }
    }

    public static void writeToElementFromCSV(String fileName, int rowNum, int colNum, String locator, SelectorType selectorType) {
        try {
            String filePath = Paths.get(FILE_MAP_PATH, fileName).toString();
            String data = readCSVData(filePath, rowNum, colNum);
            if (data != null) {
                By byLocator = getSelector(locator, selectorType);
                WebElement element = getDriver().findElement(byLocator);
                if (element != null) {
                    element.sendKeys(data);
                    logger.info("CSV'den alınan veri '" + data + "', '" + byLocator + "' elementine yazıldı.");
                } else {
                    logger.error("Belirtilen locator ile eşleşen element bulunamadı: " + locator + " (" + selectorType + ")");
                }
            } else {
                logger.error("CSV'den veri okunurken bir hata oluştu veya veri boş.");
            }
        } catch (Exception e) {
            logger.error("CSV'den veri okunurken veya elemente yazılırken bir hata oluştu: " + e.getMessage());
        }
    }

    public static void writeToCSVFromElement(String fileName, int rowNum, int colNum, SelectorType selectorType, String locator) {
        try {
            String filePath = Paths.get(FILE_MAP_PATH, fileName).toString();
            By byLocator = getSelector(locator, selectorType);
            WebElement element = getDriver().findElement(byLocator);
            if (element != null) {
                String data = element.getText();
                writeCSVData(filePath, rowNum, colNum, data);
                logger.info("'" + byLocator + "' elementinden alınan veri '" + data + "', CSV'ye '" + filePath + "' dosyasına yazıldı.");
            } else {
                logger.error("Belirtilen locator ile eşleşen element bulunamadı: " + locator + " (" + selectorType + ")");
            }
        } catch (Exception e) {
            logger.error("Elementten veri okunurken veya CSV'ye veri yazılırken bir hata oluştu: " + e.getMessage());
            throw new RuntimeException("Elementten veri okunurken veya CSV'ye veri yazılırken bir hata oluştu", e);
        }
    }

    private static String readExcelData(String filePath, int rowNum, int colNum) throws IOException, EncryptedDocumentException {
        String cellValue = null;
        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0); // İlk sayfayı alıyoruz
            Row row = sheet.getRow(rowNum - 1);
            if (row != null) {
                Cell cell = row.getCell(colNum - 1);
                if (cell != null) {
                    cellValue = cell.getStringCellValue();
                    logger.info("Excel'den alınan değer: " + cellValue);
                } else {
                    throw new IllegalArgumentException("Belirtilen hücre boş");
                }
            } else {
                throw new IllegalArgumentException("Belirtilen satır bulunamadı");
            }
        } catch (IOException | EncryptedDocumentException e) {
            logger.error("Excel dosyasından veri alınırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
        return cellValue;
    }

    private static void writeExcelData(String filePath, int rowNum, int colNum, String data) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0); // İlk sayfayı alıyoruz
            Row row = sheet.getRow(rowNum - 1);
            if (row == null) {
                row = sheet.createRow(rowNum - 1);
            }
            Cell cell = row.getCell(colNum - 1);
            if (cell == null) {
                cell = row.createCell(colNum - 1);
            }
            cell.setCellValue(data);
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }
            logger.info("Excel'e yazılan değer: " + data);
        }
    }

    private static String readCSVData(String filePath, int rowNum, int colNum) {
        String cellValue = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentRow = 1;
            while ((line = br.readLine()) != null) {
                if (currentRow == rowNum) {
                    String[] fields = line.split(",");
                    if (colNum <= fields.length) {
                        cellValue = fields[colNum - 1].trim(); // Trim metoduyla başındaki ve sonundaki boşlukları kaldırırız
                    } else {
                        logger.error("Belirtilen sütun numarası CSV dosyasındaki sütun sayısından büyük.");
                    }
                    break; // Veriyi bulduktan sonra döngüden çıkabiliriz
                }
                currentRow++;
            }
            if (cellValue == null) {
                logger.warn("CSV'de belirtilen satır numarası bulunamadı veya veri boş.");
            }
        } catch (IOException e) {
            logger.error("CSV dosyası okunurken bir hata oluştu: " + e.getMessage());
        }
        return cellValue != null ? cellValue : ""; // Boş değerler yerine boş bir karakter dizisi döndür
    }

    private static void writeCSVData(String filePath, int rowNum, int colNum, String data) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = new ArrayList<>();
            String line;
            int currentRow = 1;
            while ((line = br.readLine()) != null) {
                if (currentRow == rowNum) {
                    String[] fields = line.split(",");
                    if (colNum <= fields.length) {
                        fields[colNum - 1] = data;
                        lines.add(String.join(",", fields));
                    } else {
                        logger.error("Belirtilen sütun numarası CSV dosyasındaki sütun sayısından büyük.");
                        return;
                    }
                } else {
                    lines.add(line);
                }
                currentRow++;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (String updatedLine : lines) {
                    bw.write(updatedLine);
                    bw.newLine();
                }
                logger.info("CSV'ye yazılan değer: " + data);
            } catch (IOException e) {
                logger.error("CSV dosyasına veri yazılırken bir hata oluştu: " + e.getMessage());
                throw e;
            }
        } catch (IOException e) {
            logger.error("CSV dosyası okunurken bir hata oluştu: " + e.getMessage());
        }
    }
}
