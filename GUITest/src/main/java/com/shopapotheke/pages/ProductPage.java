package com.shopapotheke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;
    By addToCartButton = By.xpath("//*[@id=\"AddToCartButton\"]");

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public void addProductToCart(){
        driver.findElement(addToCartButton).click();
    }

}
