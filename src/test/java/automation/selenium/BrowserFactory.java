package automation.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import automation.enums.Browsers;

public class BrowserFactory {

    public static WebDriver launch(Browsers browser) {
        if (browser.equals(Browsers.CHROME)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-cookie-encryption");
            return new ChromeDriver();
        } else if (browser.equals(Browsers.FIREFOX)) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("network.cookie.cookieBehavior", 2);
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(profile);
            return new FirefoxDriver();
        }

        // default
        return new ChromeDriver();
    }
}
