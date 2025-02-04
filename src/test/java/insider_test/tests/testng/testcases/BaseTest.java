package insider_test.tests.testng.testcases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import automation.enums.Browsers;
import automation.selenium.BrowserFactory;
import insider_test.config.EnvironmentVariables;

public abstract class BaseTest {

    protected WebDriver driver;
    private final Browsers browserC = Browsers.CHROME;
    private final Browsers browserF = Browsers.FIREFOX;
    private final String url = "https://useinsider.com/";

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.launch(browserC);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvironmentVariables.WAIT_IMPLICIT));
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
