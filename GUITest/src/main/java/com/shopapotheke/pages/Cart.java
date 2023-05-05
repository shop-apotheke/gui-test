package com.shopapotheke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    WebDriver driver;
    List<String> expectedTitles = new ArrayList<>();
    By productTitles = By.xpath("//*[@data-qa-id='cart-entry-productInfo']/a");
    public Cart(WebDriver driver, List<String> expectedTitles) {
        this.driver = driver;
        this.expectedTitles = expectedTitles;
    }

    public Cart(WebDriver driver){
        this.driver = driver;
    }

    public List<String> getTitlesOfProducts(){
        List<String> titleList = new ArrayList<>();
        List<WebElement> productTitlesList =  driver.findElements(productTitles);
        for (WebElement productTitle : productTitlesList){
            titleList.add(productTitle.getText());
            System.out.println(productTitle.getText());
        }
        return titleList;
    }

    public void validateTitle(){
        Assert.assertEquals(getTitlesOfProducts(), this.expectedTitles);
    }
}
