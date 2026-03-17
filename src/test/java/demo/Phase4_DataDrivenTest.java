package demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Phase 4: Parameterization and Data-Driven Testing
 */
public class Phase4_DataDrivenTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().browserVersion("145").setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * DataProvider method storing multiple test scenarios
     * Structure: Username, Password, Expected_Success_Boolean
     */
    @DataProvider(name = "loginCredentials")
    public Object[][] provideLoginData() {
        return new Object[][] {
            {"tomsmith", "SuperSecretPassword!", true},   // Valid credentials
            {"invalidUser", "wrongPassword", false},      // Invalid credentials
            {"", "", false}                               // Empty credentials
        };
    }

    @Test(dataProvider = "loginCredentials")
    public void testLoginScenarios(String username, String password, boolean expectSuccess) {
        driver.get("http://the-internet.herokuapp.com/login");
        
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement messageArea = driver.findElement(By.id("flash"));
        String messageText = messageArea.getText();

        if (expectSuccess) {
            Assert.assertTrue(messageText.contains("You logged into a secure area!"), "Valid login should succeed.");
        } else {
            Assert.assertTrue(messageText.contains("Your username is invalid!") || 
                              messageText.contains("Password"), 
                              "Invalid login should trigger an error message.");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
