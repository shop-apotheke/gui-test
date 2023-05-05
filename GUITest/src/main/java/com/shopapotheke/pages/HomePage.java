package com.shopapotheke.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.shopapotheke.util.DataGenerator;
import java.time.Duration;

public class HomePage {
    WebDriver driver;
    int randomNumberForProduct;
    By randomProduct;
    By randomProductPrice;
    By randomProductTitle;
    String baseUrl;
    JavascriptExecutor js;
    String randomProductLiXpath;
    String randomProductXpath;
    String randomProductTitleXpath;
    String randomProductPriceXpath;


    public HomePage(WebDriver driver, String baseUrl){
        this.driver = driver;
        this.baseUrl = baseUrl;
        js = (JavascriptExecutor) driver;

        randomNumberForProduct = DataGenerator.generateRandomNumber(1,6);
        randomProductLiXpath = "//ul[@class='o-SliderHorizontal__list a-list-reset u-position--relative o-SliderHorizontal__list--no-slider']/li[" + Integer.toString(randomNumberForProduct) + "]";
        randomProductXpath = randomProductLiXpath  + "/article";
        randomProductTitleXpath = randomProductLiXpath + "//following::a[starts-with(@data-qa-id,'form-product-slider') and contains(@data-qa-id,'link-button')]";
        randomProductPriceXpath = randomProductLiXpath + "//following::div[@data-qa-id='entry-price']";

        randomProduct = By.xpath(randomProductXpath);
        randomProductTitle = By.xpath(randomProductTitleXpath);
        randomProductPrice = By.xpath(randomProductPriceXpath);
    }

    // Open home page
    public HomePage openHomePage(){
        driver.get(baseUrl);
        return this;
    }

    // Scroll down
    public HomePage scrollDownTillProductsAreLoaded() throws InterruptedException {
        while(true){
            js.executeScript("window.scrollTo(0, document.body.scrollHeight/10)");
            if (!driver.findElements(randomProduct).isEmpty()){
                break;
            }
        }
        return this;
    }

    // Click random product
    public ProductPage clickRandomProduct(){

        String title = driver.findElement(randomProductTitle).getText();
        String displayPrice = driver.findElement(randomProductPrice).getText();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(randomProduct)).click();

        ProductPage productPage = new ProductPage(driver, title, displayPrice);
        return productPage;
    }
}
