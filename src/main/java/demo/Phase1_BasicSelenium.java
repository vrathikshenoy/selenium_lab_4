package demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Phase 1: Environment Setup & Basics
 * A basic script using the main method (no TestNG yet).
 */
public class Phase1_BasicSelenium {
    public static void main(String[] args) {
        System.out.println("Setting up ChromeDriver...");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://the-internet.herokuapp.com/");
            String title = driver.getTitle();
            System.out.println("Page Title: " + title);
            
            if (title.equals("The Internet")) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing browser...");
            driver.quit();
        }
    }
}
