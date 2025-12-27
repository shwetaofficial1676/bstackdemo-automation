package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	public static void explicitWaitVisibility(WebDriver driver, By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void fluentWait(WebDriver driver, By element) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30)) // Maximum wait time
				.pollingEvery(Duration.ofSeconds(2)) // Polling frequency
				.ignoring(org.openqa.selenium.NoSuchElementException.class); // Ignore this exception
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(element));
	
	}
}
