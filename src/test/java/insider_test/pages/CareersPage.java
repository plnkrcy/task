package insider_test.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import insider_test.config.EnvironmentVariables;

public class CareersPage extends BasePage {

    By pnlTeams = By.id("career-find-our-calling");
    By pnlLocations = By.id("career-our-location");
    By pnlLifeAtInsider = By.xpath("/html/body/div[1]/section[4]");
    By pnlJobs = By.xpath("//*[@id=\"jobs-list\"]/div[3]/div");

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTeamsPanelOpen() {
        WebElement panelElement = driver.findElement(pnlTeams);
        return panelElement.isDisplayed();
    }

    public boolean isLocationsPanelOpen() {
        WebElement panelElement = driver.findElement(pnlLocations);
        return panelElement.isDisplayed();
    }

    public boolean isLifeAtInsiderPanelOpen() {
        WebElement panelElement = driver.findElement(pnlLifeAtInsider);
        return panelElement.isDisplayed();
    }

    public boolean isJobsIncludesQaAndIstanbul() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvironmentVariables.WAIT_MAX));

        List<WebElement> jobPanels = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pnlJobs));
    
        for (WebElement jobPanel : jobPanels) {

            String departmentText = jobPanel.findElement(By.cssSelector("[class*='position-department text-large font-weight-600 text-primary']")).getText();
            String locationText = jobPanel.findElement(By.cssSelector("[class*='position-location text-large']")).getText();
    
            boolean isDepartmentQa = departmentText.contains("Quality Assurance");
            boolean isLocationIstanbul = locationText.contains("Istanbul, Turkey");
    
            if (isDepartmentQa && isLocationIstanbul) {
                return true;
            }
        }
    
        return false;
    }

}
