package com.shopapotheke.test;
import java.util.concurrent.TimeUnit;

import com.shopapotheke.pages.HomePage;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;
public class TestProductAddition {
    WebDriver driver;
    HomePage homePage;

    @BeforeTest
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
    }

    @Test()
    public void testRandomProduct() throws InterruptedException {
        homePage.
                openHomePage().
                scrollDown().
                clickRandomProduct(1,6).
                validateProductTitle().
                validateProductPrice().
                addProductToCart();
    }

    @AfterTest
    public void tearDown(){
        // driver.quit();
    }

}
