package pages;

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
 
public class CartPage { 
 
    private WebDriver driver; 
 
    private By cartItems = By.cssSelector(".cart-item"); 
    private By removeItemButton = By.cssSelector(".cart-item button[title='Remove item']"); 
    private By checkoutButton = By.className("buy-btn"); 
 
    public CartPage(WebDriver driver) { 
        this.driver = driver; 
    } 
 
    public int getCartItemsCount() { 
        return driver.findElements(cartItems).size(); 
    } 
 
    public void removeFirstItem() { 
        driver.findElement(removeItemButton).click(); 
    } 
 
    public void clickCheckout() { 
        driver.findElement(checkoutButton).click(); 
    } 
} 
