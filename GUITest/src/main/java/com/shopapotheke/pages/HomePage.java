package com.shopapotheke.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import com.shopapotheke.util.DataGenerator;

public class HomePage {
    WebDriver driver;
    int randomNumberForProduct;
    By randomProduct;
    By randomProductPrice;
    By randomProductTitle;
    String baseUrl;
    JavascriptExecutor js;
    String randomProductXpath;
    String randomProductTitleXpath;
    String randomProductPriceXpath;


    public HomePage(WebDriver driver, String baseUrl){
        this.driver = driver;
        this.baseUrl = baseUrl;
        js = (JavascriptExecutor) driver;

        randomNumberForProduct = DataGenerator.generateRandomNumber(1,6);
        randomProductXpath = "//ul[@class='o-SliderHorizontal__list a-list-reset u-position--relative o-SliderHorizontal__list--no-slider']/li[" + Integer.toString(randomNumberForProduct) + "]";
        randomProductTitleXpath = randomProductXpath + "//following::a[starts-with(@data-qa-id,'form-product-slider') and contains(@data-qa-id,'link-button')]";
        randomProductPriceXpath = randomProductXpath + "//following::div[@data-qa-id='entry-price']";
    }

    // Open home page
    public HomePage openHomePage(){
        driver.get(baseUrl);
        return this;
    }

    // Scroll down
    public HomePage scrollDown() throws InterruptedException {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight/4)");
        TimeUnit.SECONDS.sleep(10);
        return this;
    }

    // Click random product
    public ProductPage clickRandomProduct(){

        randomProduct = By.xpath(randomProductXpath);
        randomProductTitle = By.xpath(randomProductTitleXpath);
        randomProductPrice = By.xpath(randomProductPriceXpath);

        String title = driver.findElement(randomProductTitle).getText();
        String displayPrice = driver.findElement(randomProductPrice).getText();

        driver.findElement(randomProduct).click();
        ProductPage productPage = new ProductPage(driver, title, displayPrice);
        return productPage;
    }
}
