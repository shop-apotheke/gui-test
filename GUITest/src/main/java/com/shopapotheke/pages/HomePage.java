package com.shopapotheke.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class HomePage {


    public static void main(String[] args) throws InterruptedException {
        By randomProduct = By.xpath("//ul[@class='o-SliderHorizontal__list a-list-reset u-position--relative o-SliderHorizontal__list--no-slider']/li[1]");
        By addToCartButton = By.xpath("//*[@id=\"AddToCartButton\"]");
        // By randomProduct = By.xpath("//*[@id=\"content\"]/article/div[1]/div[4]/div/div/div/div/div[2]/ul");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String baseUrl = "https://www.shop-apotheke.com/";
        driver.get(baseUrl);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight/4)");
        TimeUnit.SECONDS.sleep(10);
        driver.findElement(randomProduct).click();
        // js.executeScript("arguments[0].scrollIntoView();", product);
        driver.findElement(addToCartButton).click();
        driver.close();
    }
}
