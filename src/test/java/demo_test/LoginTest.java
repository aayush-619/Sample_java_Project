package demo_test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        // 1. Automatically download and configure the ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Initialize ChromeDriver with options
        driver = new ChromeDriver(options);

        // 3. Open the static login page
        //    Make sure login.html is in src/test/resources/ and this path is correct
        String filePath = "file:///" + System.getProperty("user.dir") + "/src/test/resources/login.html";
        driver.get(filePath);
    }

    @Test
    public void testValidLogin() {
        // 1. Locate the username, password fields and login button
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton   = driver.findElement(By.xpath("//button[text()='Login']"));

        // 2. Enter valid credentials
        usernameField.clear();
        usernameField.sendKeys("testuser");

        passwordField.clear();
        passwordField.sendKeys("password123");

        // 3. Click the "Login" button
        loginButton.click();

        // 4. Check if success message appears
        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Login successful!");
    }

    @Test
    public void testInvalidLogin() {
        // 1. Locate fields again (Selenium re-finds elements each time)
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton   = driver.findElement(By.xpath("//button[text()='Login']"));

        // 2. Enter invalid credentials
        usernameField.clear();
        usernameField.sendKeys("wrong");

        passwordField.clear();
        passwordField.sendKeys("wrong");

        // 3. Click "Login"
        loginButton.click();

        // 4. Verify the error message
        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Invalid username or password.");
    }

    @AfterTest
    public void teardown() {
        // Close the browser after all tests
        if (driver != null) {
            driver.quit();
        }
    }
}
