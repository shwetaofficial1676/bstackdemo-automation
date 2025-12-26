package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class LoginPage{
	WebDriver driver;
	private By usernameField = By.id("username");
	private By passwordField = By.id("password");
	private By loginButton = By.id("login-btn");
	private By signInButton = By.id("signin");
	private By errorMessage = By.cssSelector(".api-error");
    
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.findElement(signInButton).click();
	}

	public void enterUsername(String userAttr) {
		WebElement dropdown = driver.findElement(usernameField);

		Actions actions = new Actions(driver);
		actions.moveToElement(dropdown).click().perform();
		WebElement option = driver.findElement(By.id(userAttr));

		actions.moveToElement(option).click().perform();
	}

	public void enterPassword(String passwordAtt) {
		WebElement dropdown = driver.findElement(passwordField);

		Actions actions = new Actions(driver);
		actions.moveToElement(dropdown).click().perform();
		WebElement option = driver.findElement(By.id(passwordAtt));

		actions.moveToElement(option).click().perform();
	}

	public void clickLogin() {
		driver.findElement(loginButton).click();
	}

	public void enterInvalidUsername(String username) {
//		WaitUtils.fluentWait(driver, usernameField);
		WebElement dropdown = driver.findElement(usernameField);

		Actions actions = new Actions(driver);
		actions.moveToElement(dropdown).click().perform();
//    	driver.findElement(usernameField).sendKeys(username);
		WebElement option = driver.findElement(usernameField);

		actions.moveToElement(option).sendKeys(username).perform();
		WebElement option1 = driver.findElement(By.id("react-select-2-option-1"));
		actions.moveToElement(option1).click().perform();
	}

	public void enterInvalidPassword(String password) {
		//WaitUtils.explicitWaitVisibility(driver, passwordField);
		WebElement dropdown = driver.findElement(passwordField);

		Actions actions = new Actions(driver);
		actions.moveToElement(dropdown).click().perform();
		WebElement option = driver.findElement(passwordField);

		actions.moveToElement(option).sendKeys(password).perform();
		WebElement option1 = driver.findElement(By.id("react-select-3-option-1"));
		actions.moveToElement(option1).click().perform();

	}

	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}
	public void login() {
		WaitUtils.explicitWaitVisibility(driver, By.id("username"));

		this.enterUsername("react-select-2-option-0-0");
		WaitUtils.explicitWaitVisibility(driver, By.id("password"));
		this.enterPassword("react-select-3-option-0-0");
		this.clickLogin();
	}
}
