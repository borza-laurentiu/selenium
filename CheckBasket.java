package BJSS;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

public class CheckBasket {

    @Test
    public void checkBasketAndPay() {

        WebDriver driver = new ChromeDriver();

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

        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOf(quickViewFrame));
        driver.switchTo().frame(quickViewFrame);

        Select sizeDropdown = new Select(driver.findElement(By.id("group_1")));
        sizeDropdown.selectByVisibleText("M");

        driver.findElement(By.id("add_to_cart")).click();

        driver.switchTo().defaultContent();

        //******/

        WebElement checkoutWindow = driver.findElement(By.cssSelector("div.layer_cart_cart"));

        wait.until(ExpectedConditions.visibilityOf(checkoutWindow));

        WebElement continueButton = driver.findElement(By.cssSelector("span.continue.btn"));

        wait.until(ExpectedConditions.visibilityOf(continueButton));

        actions.moveToElement(continueButton);

        continueButton.click();

        driver.switchTo().defaultContent();

        //********/

        WebElement firstItem = driver.findElement(By.id("product_2_9_0_0"));
        WebElement secondItem = driver.findElement(By.id("product_3_13_0_0"));
        WebElement firstSize = driver.findElement(By.xpath(".//*[text()=\"Color : Black, Size : M\"]"));
        WebElement secondSize = driver.findElement(By.xpath(".//*[text()=\"Color : Orange, Size : S\"]"));


        // Assert.assertEquals(firstItem.getText(), "Color Black Size M", "check the size" );
        Assert.assertTrue(firstItem.getText().contains("Color : Black, Size : M"));

    }

}