package by.jazztime.algoritm;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by vova on 8.10.16.
 */
public class NumbersTest {
    private long number;
    private String text;

    @Test
    public void testFirst() throws IOException {
        System.out.println("Тест первый !!!");
        testXMLSheet(0);
    }

    @Test
    public void testSecond() throws IOException {
        System.out.println("Тест второй !!!");
        testXMLSheet(1);
    }

    private void testXMLSheet(int index) throws IOException {
        InputStream in = new FileInputStream("src/test/Data.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        Sheet sheet = wb.getSheetAt(index);
        Iterator<Row> itRow = sheet.rowIterator();
        itRow.next();
        while (itRow.hasNext()) {
            itRow.next().forEach(cell -> {
                int cellType = cell.getCellType();
                switch (cellType) {
                    case Cell.CELL_TYPE_NUMERIC:
                        number = (long) cell.getNumericCellValue();
                        break;
                    case Cell.CELL_TYPE_STRING:
                        text = cell.getStringCellValue();
                        break;
                    default:
                        break;
                }
            });
            System.out.println(number + ": " + text);
            assertEquals("Ошибка в числе: " + number, text,
                    ConvertNumberToString.convert(number));
        }
        wb.close();
        in.close();
    }

    @Test
    public void testThird() throws IOException {
        CSVParser csvParser = new CSVParser(
                new FileReader("src/test/DataCSV.csv"), CSVFormat.DEFAULT);
        csvParser.forEach(record -> {
            String s = record.get(0);
            int a = s.indexOf(';');
            System.out.println(s);
            number = Long.valueOf(s.substring(0, a)).longValue();
            assertEquals(
                    "Ошибка в числе: " + number,
                    s.substring(a + 1, s.length()),
                    ConvertNumberToString.convert(number));
        });
        csvParser.close();
    }
}