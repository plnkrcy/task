package insider_test.tests.testng.testcases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import automation.enums.Browsers;
import automation.selenium.BrowserFactory;
import insider_test.config.EnvironmentVariables;
import insider_test.pages.QualityAssurancePage;

public class TC003_FilterJobs_Search extends BaseTest {

    protected WebDriver driver;
    private final Browsers browserC = Browsers.CHROME;
    private final String url = "https://useinsider.com/careers/quality-assurance/";

    @Test
    public void TC003_FilterJobsSearch() {
        driver = BrowserFactory.launch(browserC);

        driver.manage()
                .timeouts().implicitlyWait(Duration.ofSeconds(EnvironmentVariables.WAIT_MAX));
        driver.manage()
                .window().maximize();
        driver.get(url);
        QualityAssurancePage qa = new QualityAssurancePage(driver);

        try {
            qa.clickQualityAssuranceJobs();
            qa.selectFilterByDepartment();
            qa.selectFilterByLocation();
            boolean result = qa.isJobListEmpty();
            Assert.assertTrue(result);
        } catch (AssertionError e) {
            System.out.println("ERROR: " + e.getMessage());
            takeScreenshot();
            throw e;
        }

    }

    public void takeScreenshot() {

        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "screenshots/screenshot_" + timestamp + ".png";

        try {
            Files.createDirectories(Paths.get("screenshots"));
            File destinationFile = new File(screenshotPath);
            Files.copy(screenshot.toPath(), destinationFile.toPath());
            System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error occurred while the screenshot saving: " + e.getMessage());
        }
    }

}
