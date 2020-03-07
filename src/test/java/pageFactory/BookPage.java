package pageFactory;

import execution.BaseTest;
import execution.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static utility.logger.CrawlerLogger.initiateLogger;

public class BookPage extends BaseTest {
    String bookUrl;

    private static final Logger logger;

    static {

        initiateLogger();
        logger = LogManager.getLogger("com");
    }

    public BookPage(String bookUrl) {
        super();
        this.bookUrl = bookUrl;
    }

    public Book collectBookData() {

        driver.get(this.bookUrl);

        try {
            return new Book.Builder(bookUrl)
                    .addBookName()
                    .addBookAuthor()
                    .addBookPrice()
                    .addBookAcquaintanceFragment()
                    .build();
        } catch (Exception ex) {
            logger.error("Error while collecting book data at: " + bookUrl);
            ex.printStackTrace();
            return new Book.Builder(bookUrl).addErrorMsg().build();
        }
    }
}
