package demo_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update with correct path
        driver = new ChromeDriver();
        driver.get("D:\\Aayush\\mcasem2\\devops\\Prac6\\src\\test\\resources\\login.html"); // Change to the actual file path
    }

    @Test
    public void testValidLogin() {
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        username.sendKeys("testuser");
        password.sendKeys("password123");
        loginButton.click();

        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Login successful!");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
