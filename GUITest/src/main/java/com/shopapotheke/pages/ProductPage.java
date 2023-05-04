package com.shopapotheke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class ProductPage {
    WebDriver driver;
    Actions actions;
    By addToCartButton;
    By productTitle;
    By retailPrice;
    WebElement addToCartElement;
    String expectedTitle;
    String expectedDisplayPrice;

    public ProductPage(WebDriver driver, String title, String displayPrice){
        this.driver = driver;
        this.expectedTitle = title;
        this.expectedDisplayPrice = displayPrice;

        actions = new Actions(driver);

        addToCartButton = By.xpath("//*[@id=\"AddToCartButton\"]");
        retailPrice = By.xpath("//*[@data-qa-id=\"entry-price\"]");
        productTitle = By.xpath("//*[@data-qa-id=\"product-title\"]");
    }

    public void addProductToCart(){
        driver.findElement(addToCartButton).click();
    }

    private String getProductTitle(){
        return driver.findElement(productTitle).getText();
    }

    private String getProductPrice(){
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

}
