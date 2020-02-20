package execution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Book {
    private String bookUrl;
    private String name;
    private String author;
    private double price;
    private String acquaintanceFragment;
    WebDriver driver;

//    public Book(WebDriver driver, String bookUrl) {
//        this.driver = driver;
//        this.bookUrl = bookUrl;
//        this.name = driver.findElement(By.cssSelector("span[sky-to='.js-wish-list-receiver']")).getText();
//        this.author = driver.findElement(By.cssSelector("span[class='author active']")).getText();
//        this.price = Double.parseDouble(driver.findElement(By.cssSelector(".l-book-buy-panel-top [data-type='audiobook']")).getAttribute("data-price"));
//        this.acquaintanceFragment = driver.findElement(By.cssSelector("a[ng-href*='.mp3']")).getAttribute("ng-href");
//    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = driver.findElement(By.cssSelector("span[sky-to='.js-wish-list-receiver']")).getText();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor() {
        this.author = driver.findElement(By.cssSelector("span[class='author active']")).getText();
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(WebDriver driver) {
        this.price = Double.parseDouble(driver.findElement(By.cssSelector(".l-book-buy-panel-top [data-type='audiobook']")).getAttribute("data-price"));
    }

    public String getAcquaintanceFragment() {
        return acquaintanceFragment;
    }

    public void setAcquaintanceFragment(String acquaintance_fragment) {
        this.acquaintanceFragment = driver.findElement(By.cssSelector("a[ng-href*='.mp3']")).getAttribute("ng-href");
    }

    public static class Builder extends BaseTest {
        private final Book newBook;
        private final String bookUrl;
        private String name;
        private String author;
        private double price;
        private String acquaintanceFragment;

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

//        public Builder addUrl() {
//            this.bookUrl = bookUrl;
//            return this;
//        }

        public Book build() {
            newBook.name = this.name;
            newBook.author = this.author;
            newBook.price = this.price;
            newBook.bookUrl = this.bookUrl;
            newBook.acquaintanceFragment = this.acquaintanceFragment;
            return newBook;
        }

    }
}
