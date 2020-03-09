package execution;

import java.util.*;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pageFactory.AudioBooksPage;
import utility.csvWriter.CsvWriter;
import utility.propertyManager.MyConfigs;

public class CrawlerItself extends BaseTest {
    List<WebElement> bookWebElements;
    List<String> bookUrls = new ArrayList();
    private static final MyConfigs cfg = ConfigFactory.create(MyConfigs.class, System.getProperties());
    private static final String csv = cfg.storageFile();


    @Test
    public void collectAllBooks() throws Exception {

        driver.get(cfg.targetURL());
        logger.info("Page https://www.mann-ivanov-ferber.ru/books/allbooks/?booktype=audiobook opened");

        AudioBooksPage audioBooks = new AudioBooksPage();
        bookWebElements = audioBooks.collectBooks(driver);
        logger.info("WebElements collected");
        logger.info("Count of books : " + bookWebElements.size());

        for (WebElement webElement : bookWebElements) {
            bookUrls.add(webElement.getAttribute("href"));
        }

        logger.info("URL's got from WebElements");

        CsvWriter.createAndWrite(bookUrls, csv);

        logger.info("Books collected and saved in audio_books.csv");
    }
}
