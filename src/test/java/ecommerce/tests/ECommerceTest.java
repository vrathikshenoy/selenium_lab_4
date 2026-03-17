package ecommerce.tests;

import ecommerce.pages.CartPage;
import ecommerce.pages.LoginPage;
import ecommerce.pages.ProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ECommerceTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeClass(groups = {"smoke", "regression"})
    public void setup() {
        WebDriverManager.chromedriver().browserVersion("145").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void testLogin() {
        driver.get("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed");
    }

    @Test(priority = 2, groups = {"regression"}, dependsOnMethods = "testLogin")
    public void testAddToCartFlow() {
        productsPage.addBackpackToCart();
        productsPage.goToCart();
        
        Assert.assertEquals(cartPage.getCartItemName(), "Sauce Labs Backpack", "Added item mismatch in cart");
    }

    @AfterClass(groups = {"smoke", "regression"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
