package execution;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import utility.webDriverFactory.Browsers;
import utility.webDriverFactory.WebDriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static utility.logger.CrawlerLogger.test;


public abstract class BaseTest {
    public static WebDriver driver;
    static final Logger logger /*= LogManager.getLogger("ChromeTestLog")*/;

    static {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger = LogManager.getLogger("com");
    }

    @BeforeAll
    public static void setUp() {
        driver = WebDriverFactory.create(Browsers.CHROME);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        logger.info("webdriver configured");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
