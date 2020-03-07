package execution;

import org.openqa.selenium.By;

public class Book {
    private String bookUrl;
    private String name;
    private String author;
    private double price;
    private String acquaintanceFragment;
    private String errorMsg;

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public double getPrice() {
        return price;
    }

    public String getAcquaintanceFragment() {
        return acquaintanceFragment;
    }

    public String getErrorMsg() { return  errorMsg; }


    public static class Builder extends BaseTest {
        private final Book newBook;
        private final String bookUrl;
        private String name;
        private String author;
        private double price;
        private String acquaintanceFragment;
        private String errorMsg;

        public Builder(String bookUrl) {
            newBook = new Book();
            this.bookUrl = bookUrl;
        }

        public Builder addBookName() {
            this.name = driver.findElement(By.cssSelector("span[sky-to='.js-wish-list-receiver']")).getText();
            return this;
        }

        public Builder addBookAuthor() {
            this.author = driver.findElement(By.cssSelector("span[class='author active']")).getText();
            return this;
        }

        public Builder addBookPrice() {
            this.price = Double.parseDouble(driver.findElement(By.cssSelector(".l-book-buy-panel-top [data-type='audiobook']")).getAttribute("data-price"));
            return this;
        }

        public Builder addBookAcquaintanceFragment() {
            boolean isElementPresent = driver.findElements(By.cssSelector("a[ng-href*='.mp3']")).size() > 0;
            if (isElementPresent) {
                this.acquaintanceFragment = driver.findElement(By.cssSelector("a[ng-href*='.mp3']")).getAttribute("ng-href");
            } else {
                this.acquaintanceFragment = "Acquaintance fragment is not furnished";
            }
            return this;
        }

        public Builder addErrorMsg() {
            this.errorMsg = "Book data is not collected";
            return this;
        }

        public Book build() {
            newBook.name = this.name;
            newBook.author = this.author;
            newBook.price = this.price;
            newBook.bookUrl = this.bookUrl;
            newBook.acquaintanceFragment = this.acquaintanceFragment;
            newBook.errorMsg = this.errorMsg;
            return newBook;
        }

    }
}
