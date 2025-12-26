package tests;

import base.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;


public class CheckoutTest extends BaseTest {
	@Test
	public void TC_007_placeOrderWithValidDetails() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login();
		// add to cart one element
		ProductPage productPage = new ProductPage(driver);

		productPage.addFirstProductToCart();
		productPage.clickCrossBtn();
		productPage.addFirstProductToCart();
		productPage.clickCrossBtn();
		driver.findElement(By.className("bag__quantity")).getText();
		System.out.println("size is" + driver.findElement(By.className("bag__quantity")).getText());
		sleep();
		// checkout
		driver.findElement(By.className("buy-btn")).click();
		sleep();
		productPage.customerData();
		sleep();
		driver.findElement(By.id("checkout-shipping-continue")).click();
		sleep();
		WebElement CheckoutElement = driver.findElement(By.id("confirmation-message"));
		Assert.assertTrue(CheckoutElement.getText().contains("Your Order has been successfully placed."),
				"Order success message missing");

	}
	
	@Test
	public void TC_008_CheckoutWithoutProd() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login();
		sleep();
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/span")).click(); 
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[3]/div[3]")).click();
        String expectedUrl = "https://bstackdemo.com/?signin=true";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "User should still be on the login page!");
	}

}
