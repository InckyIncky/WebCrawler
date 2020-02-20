package execution;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pageFactory.AudioBooksPage;
import pageFactory.BookPage;

public class CrawlerItself extends BaseTest {
    List<WebElement> bookWebElements;
    List<Book> allBooks = new ArrayList();
    Book book;

    @Test
    public void collectAllBooks() {
        driver.get("https://www.mann-ivanov-ferber.ru/books/allbooks/?booktype=audiobook");

        AudioBooksPage audioBooks = new AudioBooksPage();
        bookWebElements = audioBooks.collectBooks(driver);

        List<String> bookUrls = new ArrayList();

        for (WebElement webElement : bookWebElements) {
            bookUrls.add(webElement.getAttribute("href"));

        }

        for (String bookUrl : bookUrls) {
            BookPage bookPage = new BookPage(bookUrl);
            book = bookPage.collectBookData();
            allBooks.add(book);
            System.out.println(book.getName());
            logger.info(allBooks.indexOf(book) + ", "+ book.getName() + " added");

        }
    }
}
