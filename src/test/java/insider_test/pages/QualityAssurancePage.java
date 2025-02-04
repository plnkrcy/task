package insider_test.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import insider_test.config.EnvironmentVariables;

public class QualityAssurancePage extends BasePage {

    By btnSeeAllQaJobs = By.xpath("//*[@id='page-head']/div/div/div[1]/div/div/a");
    By cmbboxFilterByLocation = By.xpath("//*[@id='top-filter-form']/div[1]/span");
    By drpdwnOptionsFilterByLocation = By.xpath("//*[@id=\"select2-filter-by-location-results\"]");
    By cmbboxFilterByDepartment = By.xpath("//*[@id='select2-filter-by-department-container']");
    By drpdwnOptionsFilterByDepartment = By.xpath("//ul[@id='select2-filter-by-department-results']/li");
    By lstjobList = By.id("jobs-list");

    public QualityAssurancePage(WebDriver driver) {
        super(driver);
    }

    public void clickQualityAssuranceJobs() {
        driver.findElement(btnSeeAllQaJobs).click();
    }

    public void selectFilterByLocation() {
        WebElement dropdown = driver.findElement(cmbboxFilterByLocation);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvironmentVariables.WAIT_MAX));
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(drpdwnOptionsFilterByLocation));

        dropdown.click();

        for (WebElement option : options) {
            if (option.getText().trim().equals("Istanbul, Turkey")) {
                option.click();
                break;
            }
        }
    }

    public void selectFilterByDepartment() {
        WebElement dropdown = driver.findElement(cmbboxFilterByDepartment);
        dropdown.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvironmentVariables.WAIT_MAX));
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(drpdwnOptionsFilterByDepartment));

        for (WebElement option : options) {
            if (option.getText().equals("Quality Assurance")) {
                option.click();
                break;
            }
        }
    }

    public boolean isJobListEmpty() {
        WebElement jobList = driver.findElement(lstjobList);
        String innerHTML = jobList.getAttribute("innerHTML");

        if (innerHTML == null || innerHTML.trim().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

}
