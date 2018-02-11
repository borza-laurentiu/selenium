package BJSS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HappyPath {

    @Test
    public void QuickView() {

        WebDriver driver=new ChromeDriver();

        driver.get("http://automationpractice.com");

        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,600)", "");

        List<WebElement> products = driver.findElements(By.cssSelector("li.ajax_block_product"));
        WebElement selectedProduct = products.get(1);

        Actions actions = new Actions(driver);
        actions.moveToElement(products.get(1)).build().perform();

        //************************************************/

        WebElement quickViewLink = selectedProduct.findElement(By.cssSelector("a.quick-view"));
        quickViewLink.click();

        WebElement quickViewFrame = driver.findElement(By.cssSelector("iframe.fancybox-iframe"));

        WebDriverWait wait = new WebDriverWait(driver, 40);

        wait.until(ExpectedConditions.visibilityOf(quickViewFrame));
        driver.switchTo().frame(quickViewFrame);

        Select sizeDropdown = new Select(driver.findElement(By.id("group_1")));
        sizeDropdown.selectByVisibleText("M");

        driver.findElement(By.id("add_to_cart")).click();

        driver.switchTo().defaultContent();

        //*************************************************/

        WebElement checkoutWindow = driver.findElement(By.cssSelector("div.layer_cart_cart"));

        wait.until(ExpectedConditions.visibilityOf(checkoutWindow));

        WebElement continueButton = driver.findElement(By.cssSelector("span.continue.btn"));

        wait.until(ExpectedConditions.visibilityOf(continueButton));

        continueButton.click();

        driver.switchTo().defaultContent();

        //**************************************************/

        selectedProduct = products.get(2);

        actions.moveToElement(selectedProduct).build().perform();

        WebElement quickViewLinkX = selectedProduct.findElement(By.cssSelector("a.quick-view"));

        wait.until(ExpectedConditions.visibilityOf(quickViewLinkX));

        quickViewLinkX.click();

        WebElement quickViewFrameX = driver.findElement(By.cssSelector("iframe.fancybox-iframe"));

        wait.until(ExpectedConditions.visibilityOf(quickViewFrameX));

        driver.switchTo().frame(quickViewFrameX);

        driver.findElement(By.id("add_to_cart")).click();

        driver.switchTo().defaultContent();

        //*****************************************************/

        WebElement checkoutWindowX = driver.findElement(By.cssSelector("div.layer_cart_cart"));

        wait.until(ExpectedConditions.visibilityOf(checkoutWindowX));

        WebElement buttonContainer = driver.findElement(By.cssSelector("div.button-container"));

        wait.until(ExpectedConditions.visibilityOf(buttonContainer));

        WebElement checkOutButton = driver.findElement(By.cssSelector("span.btn.btn-default"));

        wait.until(ExpectedConditions.visibilityOf(checkOutButton));

        checkOutButton.click();

        driver.switchTo().defaultContent();

        //*****************************************************/


    }

 //       driver.quit();


}
