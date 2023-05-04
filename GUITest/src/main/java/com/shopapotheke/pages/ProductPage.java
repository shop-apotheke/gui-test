package com.shopapotheke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ProductPage {
    WebDriver driver;
    Actions actions;
    By addToCartButton;
    By productTitle;
    By retailPrice;
    By cartButton;
    By productQuantity;
    Select productQuantityDropdown;
    String expectedTitle;
    String expectedDisplayPrice;
    JavascriptExecutor js;

    public ProductPage(WebDriver driver, String title, String displayPrice){
        this.driver = driver;
        this.expectedTitle = title;
        this.expectedDisplayPrice = displayPrice;

        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        addToCartButton = By.xpath("//*[@id=\"AddToCartButton\"]");
        retailPrice = By.xpath("//*[@data-qa-id=\"entry-price\"]");
        productTitle = By.xpath("//*[@data-qa-id=\"product-title\"]");
        productQuantity = By.id("product_quantity_BuyBox");
        cartButton = By.xpath("//*[@data-qa-id=\"m-cart-button\"]");
    }

    public CartIntermediatePage addProductToCart(){
        WebElement element = driver.findElement(addToCartButton);
        JavascriptExecutor ex=(JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click()", element);
        CartIntermediatePage cartIntermediatePage = new CartIntermediatePage(driver, this.expectedTitle);
        return cartIntermediatePage;
    }

    private String getProductTitle(){

        return driver.findElement(productTitle).getText();
    }

    private String getProductPrice(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(retailPrice));
        return driver.findElement(retailPrice).getText();
    }

    public ProductPage validateProductTitle(){
        Assert.assertEquals(getProductTitle(), this.expectedTitle);
        return this;
    }

    public ProductPage validateProductTitle(String productTitle){
        Assert.assertEquals(getProductTitle(), productTitle);
        return this;
    }

    public ProductPage validateProductPrice(){
        Assert.assertEquals(getProductPrice(), this.expectedDisplayPrice);
        return this;
    }

    public void goToCart(){
        driver.findElement(cartButton).click();
    }

    public ProductPage selectQuantity(int quantity) throws InterruptedException {
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(productQuantity));
        driver.findElement(By.xpath("//*[@class=\"u-margin-small--right o-BuyBox__grow--one-quarter@small-tiny o-AddToCart__select-wrapper\"]")).click();
        productQuantityDropdown = new Select(driver.findElement(productQuantity));
        TimeUnit.SECONDS.sleep(10);
        // driver.findElement(By.xpath("//*[@data-clientside-hook='select-item' and @data-value='2']")).click();
        // new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-clientside-hook='select-item' and @data-value='2']"))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@data-clientside-hook='select-item' and @data-value='2']"))));

        productQuantityDropdown.selectByValue(Integer.toString(quantity));
        return this;
    }

}
