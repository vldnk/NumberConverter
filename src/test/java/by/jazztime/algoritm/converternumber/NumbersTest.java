package by.jazztime.algoritm.converternumber;

import by.jazztime.algoritm.controller.ConverterNumberController;
import by.jazztime.algoritm.model.Words;
import by.jazztime.algoritm.model.WordsGet;
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
    private final WordsGet words;
    private final ConverterNumberController converter;
    private String number;
    private String text;

    public NumbersTest() {
        Words wordsController = new Words();
        this.words = wordsController;
        this.converter = new ConverterNumberController(wordsController);
    }

    @Test
    public void testFirst() throws IOException {
        testXMLSheet(0);
    }

    @Test
    public void testSecond() throws IOException {
        testXMLSheet(1);
    }

    private void testXMLSheet(int index) throws IOException {
        InputStream in = new FileInputStream("src/test/Data.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        Sheet sheet = wb.getSheetAt(index);
        Iterator<Row> itRow = sheet.rowIterator();
        Cell title = itRow.next().getCell(0);
        if (title.getCellType() == Cell.CELL_TYPE_STRING)
            System.out.println(title.getStringCellValue());
        while (itRow.hasNext()) {
            itRow.next().forEach(cell -> {
                int cellType = cell.getCellType();
                switch (cellType) {
                    case Cell.CELL_TYPE_NUMERIC:
                        number = String.valueOf(Math.round(cell.getNumericCellValue()));
                        break;
                    case Cell.CELL_TYPE_STRING:
                        text = cell.getStringCellValue();
                        break;
                    default:
                        break;
                }
            });
            System.out.println(number + ": " + text);
            converter.toWords(number);
            assertEquals("Ошибка в числе: " + number, text, words.getNumberSecond());
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
            number = s.substring(0, a);
            converter.toWords(number);
            assertEquals(
                    "Ошибка в числе: " + number,
                    s.substring(a + 1, s.length()),
                    words.getNumberSecond());
        });
        csvParser.close();
    }
}