package demo_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	WebDriver webDriver;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver","path/to/chromedriver");
		webDriver = new ChromeDriver();
		
		webDriver.get("https://example.com/login");
	}
	
	
	@Test
	public void testLogin() {
		System.out.println("Executing... login test");
	}
	
	
	@AfterTest
	public void teardown() {
		webDriver.quit();
	}

}
