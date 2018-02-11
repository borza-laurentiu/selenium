package BJSS;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ReviewOrder {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void Review() {

        driver.get("http://automationpractice.com");

        WebElement SignInHomepage = driver.findElement(By.className("header_user_info"));
        SignInHomepage.click();

        WebElement userEmail = driver.findElement(By.id("email"));
        userEmail.clear();
        userEmail.sendKeys("borza.laurentiu@gmail.com");

        WebElement userPassword = driver.findElement(By.id("passwd"));
        userPassword.clear();
        userPassword.sendKeys("BJSSTest");

        WebElement SignInButton = driver.findElement(By.id("SubmitLogin"));
        SignInButton.click();

        WebElement OrderHistory = driver.findElement(By.xpath("//a[@href=\"http://automationpractice.com/index.php?controller=history\"]"));
        OrderHistory.click();

        WebElement clickOrder = driver.findElement(By.cssSelector("td.history_link"));
        WebElement date = driver.findElement(By.cssSelector("td.history_date"));

        Assert.assertTrue(date.getText().contains("02/09/2018"));
        clickOrder.click();

        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,1000)", "");

        WebDriverWait wait = new WebDriverWait(driver, 20);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.submit")));


    }


    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
