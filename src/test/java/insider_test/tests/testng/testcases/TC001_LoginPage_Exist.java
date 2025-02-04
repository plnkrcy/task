package insider_test.tests.testng.testcases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import insider_test.pages.MainPage;

public class TC001_LoginPage_Exist extends BaseTest {

    @Test
    public void TC001_LoginPageExist() {

        String currentURL = driver.getCurrentUrl();

        MainPage mp = new MainPage(driver);

        try {
            boolean result = mp.isAt();
            Assert.assertTrue(result);
            Assert.assertEquals("https://useinsider.com/", currentURL);
            System.out.println("OK: Home page is displayed.");
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
