package tests;

import base.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class CartTests extends BaseTest {
	@Test
	public void TC_004_addSingleItemToCart() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login();
		// add to cart one element
		ProductPage productPage = new ProductPage(driver);

		productPage.addFirstProductToCart();

		productPage.clickCrossBtn();
		Assert.assertEquals(productPage.getCartCount(), 1, "Cart count should be 1 after adding single item");
	}

	@Test
	public void TC_005_addMultipleItemsToCart() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login();
		// add to cart one element
		ProductPage productPage = new ProductPage(driver);
		productPage.addFirstProductToCart();
		productPage.clickCrossBtn();
		productPage.addFirstProductToCart();
		productPage.clickCrossBtn();
		productPage.addFirstProductToCart();
		productPage.clickCrossBtn();
		int cartCount = productPage.getCartCount();
		Assert.assertEquals(cartCount, 3, "Cart count should be 3 after adding three items");
	}

	@Test
	public void TC_006_removeItemFromCart() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login();
		// add to cart one element
		ProductPage productPage = new ProductPage(driver);
		productPage.addFirstProductToCart();
		// productPage.clickCrossBtn();
		productPage.addFirstProductToCart();
		productPage.clickCrossBtn();
		// sleep();
		int cartCount = productPage.getCartCount();
		System.out.println("Cart Count before removing from cart" + cartCount);
		sleep();
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/span")).click();
		sleep();
		driver.findElement(By.className("shelf-item__del")).click();
		sleep();
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[1]")).click();
		sleep();
		int cartCount1 = productPage.getCartCount();
		System.out.println("Cart Count after removing from cart" + cartCount1);

		Assert.assertEquals(cartCount1 < cartCount, true, "Cart count should be less after removing item");
	}
}
