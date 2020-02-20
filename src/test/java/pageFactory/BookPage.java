package pageFactory;

import execution.BaseTest;
import execution.Book;

public class BookPage extends BaseTest {
    String bookUrl;

    public BookPage(String bookUrl) {
        super();
        this.bookUrl = bookUrl;
    }

    public Book collectBookData() {
        driver.get(this.bookUrl);
        return new Book.Builder(bookUrl)
                .addBookName()
                .addBookAuthor()
                .addBookPrice()
                .addBookAcquaintanceFragment()
                .build();
    }
}
