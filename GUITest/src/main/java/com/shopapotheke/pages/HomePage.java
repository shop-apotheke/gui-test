package com.shopapotheke.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import com.shopapotheke.pages.ProductPage;
import com.shopapotheke.util.DataGenerator;

public class HomePage {

    WebDriver driver;
    int randomNumberForProduct;
    By randomProduct;
    By randomProductPrice;
    By randomProductTitle;
    String baseUrl = "https://www.shop-apotheke.com/";
    JavascriptExecutor js;
    String selectedProductDescription;
    float selectedProductPrice;


    /*
    public static void main(String[] args) throws InterruptedException {

        By randomProduct = By.xpath("//ul[@class='o-SliderHorizontal__list a-list-reset u-position--relative o-SliderHorizontal__list--no-slider']/li[1]");
        By addToCartButton = By.xpath("//*[@id=\"AddToCartButton\"]");
        // By randomProduct = By.xpath("//*[@id=\"content\"]/article/div[1]/div[4]/div/div/div/div/div[2]/ul");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String baseUrl = "https://www.shop-apotheke.com/";
        driver.get(baseUrl);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight/4)");
        TimeUnit.SECONDS.sleep(10);
        driver.findElement(randomProduct).click();
        // js.executeScript("arguments[0].scrollIntoView();", product);
        driver.findElement(addToCartButton).click();
        driver.close();
    }
    */

    public HomePage(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    // Open home page
    public HomePage openHomePage(){
        driver.get(baseUrl);
        return this;
    }

    // Scroll down
    public HomePage scrollDown() throws InterruptedException {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight/4)");
        TimeUnit.SECONDS.sleep(10);
        return this;
    }

    // Click random product
    public ProductPage clickRandomProduct(int min, int max){

        randomNumberForProduct = DataGenerator.generateRandomNumber(min,max);

        String randomProductXpath = "//ul[@class='o-SliderHorizontal__list a-list-reset u-position--relative o-SliderHorizontal__list--no-slider']/li[" + Integer.toString(randomNumberForProduct) + "]";
        String randomProductTitleXpath = randomProductXpath + "//following::a[starts-with(@data-qa-id,'form-product-slider') and contains(@data-qa-id,'link-button')]";
        String randomProductPriceXpath = randomProductXpath + "//following::div[@data-qa-id='entry-price']";

        randomProduct = By.xpath(randomProductXpath);
        randomProductTitle = By.xpath(randomProductTitleXpath);
        randomProductPrice = By.xpath(randomProductPriceXpath);

        String title = driver.findElement(randomProductTitle).getText();
        String displayPrice = driver.findElement(randomProductPrice).getText();

        driver.findElement(randomProduct).click();
        ProductPage productPage = new ProductPage(driver, title, displayPrice);
        return productPage;
    }

}
