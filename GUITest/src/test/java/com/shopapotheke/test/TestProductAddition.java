package com.shopapotheke.test;

import java.util.concurrent.TimeUnit;
import com.shopapotheke.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.lang.System;
public class TestProductAddition {
    WebDriver driver;
    HomePage homePage;

    @BeforeTest
    public void setup(){
        String baseUrl = System.getenv("SHOP_APOTHEKE_BASE_URL");
        String browser = System.getenv("BROWSER");
        switch (browser)
        {
            case "Chrome":
                driver = new ChromeDriver();
            case "Firefox":
                driver = new FirefoxDriver();
            default:
                driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver, baseUrl);
    }

    @Test()
    public void testRandomProduct() throws InterruptedException {
        homePage.
                openHomePage().
                scrollDownTillProductsAreLoaded().
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
                scrollDownTillProductsAreLoaded().
                clickRandomProduct().
                validateProductTitle("invalid title");
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
