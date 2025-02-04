package insider_test.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import insider_test.config.EnvironmentVariables;

public class MainPage extends BasePage {

    By imgFirmName = By.xpath("//*[@id='navigation']/div[2]/a[1]/img");
    By btnCompany = By.xpath("/html/body/nav/div[2]/div/ul[1]/li[6]/a");
    By btnCareers = By.xpath("//*[@id='navbarNavDropdown']/ul[1]/li[6]/div/div[2]/a[2]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvironmentVariables.WAIT_IMPLICIT));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(imgFirmName));
        return logo.isDisplayed();
    }

    public void openCompanyDropMenu() {
        driver.findElement(btnCompany).click();

    }

    public void openCareers() {
        driver.findElement(btnCareers).click();
    }

}
