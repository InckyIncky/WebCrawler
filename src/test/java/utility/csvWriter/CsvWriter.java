package utility.csvWriter;

import au.com.bytecode.opencsv.CSVWriter;
import execution.Book;
import pageFactory.BookPage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public static final String csv = "audio_books.csv";
    public static Book book;


    public static void createAndWrite(List<String> bookUrls) throws Exception{
        try (
                FileWriter fw = new FileWriter(csv, true);
                CSVWriter writer = new CSVWriter(fw, ',')) {
            for (String bookUrl : bookUrls) {
                BookPage bookPage = new BookPage(bookUrl);
                try {
                    book = bookPage.collectBookData();
                    CsvWriter.addBookToCsv(book, writer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    public static void addBookToCsv(Book book, CSVWriter writer) {

        String[] record = new String[]{book.getErrorMsg(), book.getName(), book.getAuthor(), String.valueOf(book.getPrice()), book.getAcquaintanceFragment(), book.getBookUrl()};

        writer.writeNext(record);
    }
}
