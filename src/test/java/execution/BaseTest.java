package execution;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import utility.webDriverFactory.Browsers;
import utility.webDriverFactory.WebDriverFactory;

import java.util.concurrent.TimeUnit;

import static utility.logger.CrawlerLogger.initiateLogger;


public abstract class BaseTest {
    public static WebDriver driver;
    static final Logger logger;

    static {

            initiateLogger();

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
