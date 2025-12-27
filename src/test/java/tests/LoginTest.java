package tests;

import base.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.WaitUtils;

public class LoginTest extends BaseTest {

	@Test(priority = 1)
	public void TC_001_validLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login();
		WaitUtils.explicitWaitVisibility(driver, By.id("signin"));
		WebElement logout = driver.findElement(By.id("signin"));
		Assert.assertTrue(logout.isDisplayed(), "Login failed");

	}

	@Test(priority = 2)
	public void TC_002_invalidUserName() {
		LoginPage loginPage = new LoginPage(driver);
		sleep();
		loginPage.enterInvalidUsername("wronguser");
		sleep();
		loginPage.enterPassword("react-select-3-option-0-0");
		loginPage.clickLogin();
		sleep();
		Assert.assertEquals(loginPage.getErrorMessage(), "Invalid Username", "Error message mismatch");
	}

	@Test(priority = 3)
	public void TC_003_invalidPassword() {
		LoginPage loginPage = new LoginPage(driver);
		sleep();
		loginPage.enterUsername("react-select-2-option-0-0");
		sleep();
		loginPage.enterInvalidPassword("wrongpass");
		sleep();
		loginPage.clickLogin();
		sleep();
		Assert.assertEquals(loginPage.getErrorMessage(), "Invalid Password", "Error message mismatch");
	}
}
