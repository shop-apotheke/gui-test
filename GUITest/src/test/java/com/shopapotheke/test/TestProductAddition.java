package com.shopapotheke.test;
import java.util.List;
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
        String baseUrl = "https://www.shop-apotheke.com/";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver, baseUrl);
    }

    @Test()
    public void testRandomProduct() throws InterruptedException {
        homePage.
                openHomePage().
                scrollDown().
                clickRandomProduct().
                validateProductTitle().
                validateProductPrice().
                addProductToCart().
                goToCart().
                validateTitle();
    }

    @Test
    public void testInvalidTitle() throws InterruptedException {
        homePage.
                openHomePage().
                scrollDown().
                clickRandomProduct().
                validateProductTitle("invalid title");
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
