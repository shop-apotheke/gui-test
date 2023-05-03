package com.shopapotheke.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage {
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://www.shop-apotheke.com/";
        driver.get(baseUrl);
        driver.close();
    }
}
