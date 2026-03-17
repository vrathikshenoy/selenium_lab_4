package demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Phase 2 & 3: Web Elements, Waits, and TestNG Lifecycle Annotations
 */
public class Phase2_Phase3_WebElementsAndTestNGTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().browserVersion("145").setup();
        driver = new ChromeDriver();
        // Implicit wait strategy
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testDropdownInteraction() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);
        
        // Select an option by its visible text
        dropdown.selectByVisibleText("Option 1");
        
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        Assert.assertEquals(selectedOption.getText(), "Option 1", "Dropdown selection failed");
    }

    @Test
    public void testCheckboxInteraction() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        
        WebElement checkbox1 = driver.findElement(By.cssSelector("form#checkboxes input:nth-child(1)"));
        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }
        Assert.assertTrue(checkbox1.isSelected(), "Checkbox 1 should be selected");
    }

    @Test
    public void testJavascriptAlerts() {
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
        
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        
        // Switch to the alert and accept it
        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals(alertText, "I am a JS Alert");
        driver.switchTo().alert().accept();
        
        WebElement resultMsg = driver.findElement(By.id("result"));
        Assert.assertEquals(resultMsg.getText(), "You successfully clicked an alert");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
