package pages;


import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 

public class CheckoutPage {

	private WebDriver driver;

	private By placeOrderButton = By.id("place-order-btn");
	private By successMessage = By.cssSelector(".order-success");
	private By emptyCartError = By.cssSelector(".toast-message");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void placeOrder() {
		driver.findElement(placeOrderButton).click();
	}

	public String getSuccessMessage() {
		return driver.findElement(successMessage).getText();
	}

	public String getErrorMessage() {

		return driver.findElement(emptyCartError).getText();
	}
}