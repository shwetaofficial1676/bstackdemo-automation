package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

	public static WebDriver driver;

	@BeforeTest(alwaysRun = true)
	public void initDriver() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://bstackdemo.com/");
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {

		driver.quit();

	}

	public void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
