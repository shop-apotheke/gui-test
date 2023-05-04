package com.shopapotheke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartIntermediatePage {

    By cartButton;
    String title;
    WebDriver driver;
    public CartIntermediatePage(WebDriver driver, String title) {
        this.driver = driver;
        this.title = title;
        cartButton = By.xpath("//*[@data-qa-id=\"m-cart-button\"]");
    }

    public Cart goToCart(){
        List<String> titles = new Cart(driver).getTitlesOfProducts();
        // driver.findElement(cartButton).click();
        WebElement element = driver.findElement(cartButton);
        JavascriptExecutor ex=(JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click()", element);
        titles.add(title);
        Cart cart = new Cart(driver, titles);
        return cart;

        //new WebDriverWait(driver, Duration.ofSeconds(10)).
          //      until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }


}
