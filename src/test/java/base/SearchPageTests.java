package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class SearchPageTests {
    private WebDriver driver;
    @BeforeMethod
    @Parameters({"width", "height"})
    public void setUp(int width, int height) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().setSize(new Dimension(width, height));
    }
    @Test
    public void searchPageTest() {
        try {
            driver.get("https://www.picsart.com/search");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement iframe = (WebElement) wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("[data-testid='com.picsart.social.search']")));
            System.out.println("Switched to iframe");

            WebElement filterBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='search-header-filter']")));
            WebElement filterSection = driver.findElement(By.cssSelector("[data-testid='search-filter-root']"));

            if (filterSection.getAttribute("class").contains("hide")) {
                filterBtn.click();
            }
            System.out.println("Filter section handled");

            WebElement licenseSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='list']")));
            List<WebElement> licenseCheckboxes = licenseSection.findElements(By.cssSelector("[data-testid='checkbox-item-root']"));

            WebElement licenseCheckbox = licenseCheckboxes.get(2);
            licenseCheckbox.click();
            System.out.println("License checkbox clicked");

            WebElement inputCheckbox = licenseCheckbox.findElement(By.cssSelector("[data-testid='checkbox-item-check']"));
            if ("true".equals(inputCheckbox.getAttribute("checked"))) {
                System.out.println("There are no PLUS assets");
            }

            WebElement contentRoot = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='content-grid-root']")));
            List<WebElement> searchCards = contentRoot.findElements(By.cssSelector("[data-testid='search-card-root']"));

            WebElement firstCard = searchCards.get(0);
            new Actions(driver).moveToElement(firstCard).perform();
            WebElement likeButton = firstCard.findElement(By.cssSelector("[data-testid='like-button-root']"));
            likeButton.click();
            System.out.println("Like button clicked");

            driver.switchTo().defaultContent();
            WebElement registerPopup = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='registration-overlay']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerPopup);
            System.out.println("Register popup closed");

            driver.switchTo().frame(iframe);
            wait.until(ExpectedConditions.elementToBeClickable(licenseCheckboxes.get(2))).click();

            new Actions(driver).moveToElement(firstCard).perform();
            WebElement tryBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("data-testid='try-now-button-root']")));
            tryBtn.click();
            System.out.println("Try button clicked");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
