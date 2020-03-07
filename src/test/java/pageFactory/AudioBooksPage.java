package pageFactory;

import execution.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.time.Duration;

public class AudioBooksPage extends BaseTest {
    Actions action;
    WebDriverWait wait;
    List<WebElement> bookWebElements;

    public AudioBooksPage() {
        super();
        this.action = new Actions(driver);
        this.wait = new WebDriverWait(driver, 30);
    }

    public List<WebElement> collectBooks(WebDriver driver) {
        WebElement pageLoader = driver.findElement(By.cssSelector("div.js-page-loader.page-loader"));

        while (pageLoader.isDisplayed()) {
            action.moveToElement(pageLoader).perform();
            wait.withTimeout(Duration.ofSeconds(5L));
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 100)");
        }
        bookWebElements = driver.findElements(By.cssSelector("a[href^='https://www.mann-ivanov-ferber.ru/books/']"));
        return bookWebElements;
    }
}
