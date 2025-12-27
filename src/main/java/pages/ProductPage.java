package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils; 

public class ProductPage { 
 
    private WebDriver driver; 
 
    private By productAddButton = By.xpath("//*[@id='1']/div[4]"); 
    private By cartCount = By.xpath("//*[@id='__next']/div/div/div[2]/span/span"); 
    private By crossBtn = By.xpath("//*[@id='__next']/div/div/div[2]/div[1]"); 
 
    public ProductPage(WebDriver driver) { 
        this.driver = driver; 
    } 
 
    public void addFirstProductToCart() { 
    	WaitUtils.explicitWaitVisibility(driver, productAddButton);
        driver.findElement(productAddButton).click(); 
    } 
 
    public int getCartCount() { 
    	WaitUtils.explicitWaitVisibility(driver, cartCount);
        String countText = driver.findElement(cartCount).getText(); 
        return Integer.parseInt(countText); 
    } 
    
    public void clickCrossBtn() {
    	WaitUtils.explicitWaitVisibility(driver, crossBtn);
    	driver.findElement(crossBtn).click();
    }
    public void customerData() {
    	driver.findElement(By.id("firstNameInput")).sendKeys("Shweta");
		driver.findElement(By.id("lastNameInput")).sendKeys("M");
		driver.findElement(By.id("addressLine1Input")).sendKeys("Karnataka");
		driver.findElement(By.id("provinceInput")).sendKeys("Belgaum");
		driver.findElement(By.id("postCodeInput")).sendKeys("572113");
    }
} 
