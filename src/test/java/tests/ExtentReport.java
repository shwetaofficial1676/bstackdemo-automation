package tests;

import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pages.ProductPage;

public class ExtentReport {
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void setupReport() {
		// Timestamp for unique report name
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";

		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setDocumentTitle("BstackDemo Automation Report");
		spark.config().setReportName("Functional Tests");

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Tester", "Shweta T");
		extent.setSystemInfo("Environment", "QA");
	}

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://bstackdemo.com/");
	}

	@Test
	public void testLoginAddToCartCheckout() throws InterruptedException {
		test = extent.createTest("Login, Add to Cart, and Checkout Test");

		try {
			// Click Sign In
			driver.findElement(By.id("signin")).click();

			// --- Username Selection ---
			driver.findElement(By.id("username")).click(); // Open dropdown
			driver.findElement(By.id("react-select-2-option-0-0")).click(); // Choose 'demouser'
			test.info("Selected username: demouser");

			// --- Password Selection ---
			driver.findElement(By.id("password")).click(); // Open dropdown
			driver.findElement(By.id("react-select-3-option-0-0")).click(); // Choose 'testingisfun99'
			test.info("Selected password: testingisfun99");

			// --- Click Login Button ---
			driver.findElement(By.id("login-btn")).click();
			test.pass("Login successful");

			// --- Add products to cart ---
			driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();
			driver.findElement(By.xpath("//*[@id=\"2\"]/div[4]")).click();
			test.pass("Products added to cart");

			// --- Check cart quantity ---
			String cartQty = driver.findElement(By.className("bag__quantity")).getText();
			test.info("Cart size: " + cartQty);
			Assert.assertTrue(Integer.parseInt(cartQty) >= 1, "Cart is empty!");

			// --- Remove an item ---
			driver.findElement(By.className("shelf-item__del")).click();
			driver.navigate().refresh();
			test.pass("Removed one item from cart");

			// --- Proceed to checkout ---
			driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/span")).click();

			driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[3]/div[3]")).click();
			test.pass("Navigated to checkout page");

			// --- Fill checkout details ---
			Thread.sleep(2000);
			ProductPage productPage = new ProductPage(driver);
			productPage.customerData();
			test.pass("Entered checkout details");
			driver.findElement(By.id("checkout-shipping-continue")).click();
			test.pass("Your Order has been successfully placed.");

		} catch (Exception e) {
			test.fail("Test failed due to exception: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@AfterSuite
	public void generateReport() {
		extent.flush();
	}
}
