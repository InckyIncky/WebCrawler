package execution;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pageFactory.AudioBooksPage;
import utility.csvWriter.CsvWriter;

public class CrawlerItself extends BaseTest {
    List<WebElement> bookWebElements;
    List<String> bookUrls = new ArrayList();

    @Test
    public void collectAllBooks() throws Exception {
        driver.get("https://www.mann-ivanov-ferber.ru/books/allbooks/?booktype=audiobook");
        logger.info("Page https://www.mann-ivanov-ferber.ru/books/allbooks/?booktype=audiobook opened");

        AudioBooksPage audioBooks = new AudioBooksPage();
        bookWebElements = audioBooks.collectBooks(driver);
        logger.info("WebElements collected");
        logger.info("Count of books : " + bookWebElements.size());

        for (WebElement webElement : bookWebElements) {
            bookUrls.add(webElement.getAttribute("href"));
        }

        logger.info("URL's got from WebElements");

        CsvWriter.createAndWrite(bookUrls);
    }
}
