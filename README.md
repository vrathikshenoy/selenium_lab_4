# Lab Experiment: Selenium WebDriver & TestNG Automation

## Aim
To implement advanced Selenium WebDriver techniques for automating test scenarios on web applications, covering web element interactions, TestNG lifecycle management, Data-Driven Testing (DDT), the Page Object Model (POM) design pattern, and integrating with Jenkins CI via Maven for seamless test execution and reporting.

---

## Objective
1. Handle dynamic web elements, dropdowns, checkboxes, and alerts using Selenium WebDriver.
2. Implement implicit waits for synchronized execution.
3. Master TestNG lifecycle annotations (`@BeforeMethod`, `@AfterClass`, `@Test`, etc.) for test organization.
4. Perform Data-Driven Testing (DDT) using TestNG's `@DataProvider`.
5. Establish automation best practices using the Page Object Model (POM) for a scalable test framework.
6. Design and execute a TestNG XML Suite (`testng.xml`) for running grouped test cases (e.g., `smoke`, `regression`).
7. Integrate Selenium tests with Maven and Jenkins for continuous integration and automated HTML reporting.

---

## Tools Required
| Tool               | Version | Purpose                                         |
| ------------------ | ------- | ----------------------------------------------- |
| Java (JDK)         | 17+     | Programming language                            |
| Selenium WebDriver | 4.18+   | Browser automation                              |
| TestNG             | 7.9+    | Test framework and assertions                   |
| Maven              | 3.8+    | Build tool and dependency management            |
| webdrivermanager   | 5.7+    | Automatic ChromeDriver binary management        |
| Google Chrome      | Latest  | Target browser                                  |
| Jenkins            | 2.x     | Continuous Integration server                   |

---

## Theory

### 1. Selenium WebDriver & Interactions
Selenium WebDriver provides a programming interface to create and execute test cases. It interacts directly with the browser using native browser drivers. Key interactions include identifying elements via `By.id`, `By.cssSelector`, and `By.xpath`, handling dropdowns via the `Select` class, and managing JavaScript alerts via `driver.switchTo().alert()`.

### 2. TestNG Framework & Annotations
TestNG (Test Next Generation) overcomes the limitations of JUnit by providing powerful annotations. 
- `@BeforeClass` / `@BeforeMethod`: Setup methods executed before test execution to initialize drivers and state.
- `@AfterClass` / `@AfterMethod`: Teardown methods executed after tests to quit the browser and release resources.
- `@Test`: Marks a method as an executable test case. Supports attributes like `priority`, `groups`, and `dependsOnMethods`.

### 3. Parameterization & Data-Driven Testing
Data-Driven Testing allows execution of the same test script against multiple sets of data. TestNG achieves this using the `@DataProvider` annotation, which returns a 2D array of objects representing the datasets. This is essential for testing scenarios like login forms where varying combinations of valid and invalid credentials must be validated without code duplication.

### 4. Page Object Model (POM)
A design pattern that creates an object repository for web UI elements. Each web page is represented as a distinct Java Class containing web elements (located using the `@FindBy` annotation via `PageFactory`) and interaction methods. POM drastically improves code maintainability and reusability by separating test logic from UI element locators.

### 5. Continuous Integration (Jenkins & Maven)
Maven is a build lifecycle mechanism that handles the resolution of external libraries (via `pom.xml`) and test execution (via `maven-surefire-plugin`). Jenkins automates this process by pulling source code from a repository and executing build commands (`mvn clean test`) every time a change is detected, immediately reporting successes or failures.

---

## Test Plan

### Scope
Automated testing of demonstration sites and an e-commerce application covering:

| Area                    | Target Page                 | Techniques Used                                   |
| ----------------------- | --------------------------- | ------------------------------------------------- |
| Basic Elements & Alerts | `the-internet.herokuapp.com`| Explicit/Implicit waits, Select class, Alerts     |
| Data-Driven Form        | `/login`                    | TestNG `@DataProvider`, Assertion validation      |
| E-Commerce E2E Flow     | `saucedemo.com`             | Page Object Model, `@FindBy`, TestNG Groups       |

### Test Environment
- **Browser**: Google Chrome
- **OS**: Linux
- **Framework**: Java + TestNG + Selenium WebDriver + Maven
- **Architecture**: Page Object Model (POM)

---

## Test Cases

| TC#  | Test Case                   | Steps                                                                                                                 | Expected Result                                           | Requirement                            |
| ---- | --------------------------- | --------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------- | -------------------------------------- |
| TC01 | Print Page Title            | 1. Open Herokuapp 2. Get Title 3. Validate title equals "The Internet"                                                | Console outputs Passed, browser quits                     | Basic WebDriver initialization         |
| TC02 | Dropdown Selection          | 1. Open `/dropdown` 2. Select "Option 1" via `Select` class 3. Validate selection                                     | "Option 1" is successfully selected                       | DOM interactions (`Select`)            |
| TC03 | Handle Checkboxes           | 1. Open `/checkboxes` 2. Identify Checkbox 1 3. Click if not selected                                                 | Checkbox state becomes `selected`                         | CSS Selectors, conditional logic       |
| TC04 | Accept JS Alert             | 1. Open `/javascript_alerts` 2. Click "JS Alert" button 3. `driver.switchTo().alert().accept()`                       | Alert accepted, "You successfully clicked an alert" shown | Pop-ups and Alerts                     |
| TC05 | Data-Driven Login Tests     | 1. Read `@DataProvider` 2. Open login page 3. Enter credentials 4. Submit 5. Validate success/error message           | Tests pass for valid, invalid, and empty credential sets  | TestNG Data-Driven Testing             |
| TC06 | E-Commerce E2E Flow         | 1. Open Saucedemo 2. Login (POM) 3. Add to Cart (POM) 4. Open Cart (POM) 5. Validate Item                             | Backpack is confirmed in the cart interface               | Page Object Model + E2E Workflow       |

---

## Sample Code

### Project Structure
```text
selenium_lab_4/
├── pom.xml                                  # Maven dependencies and surefire config
├── testng.xml                               # TestNG suite runner configuration
└── src/
    ├── main/java/demo/
    │   └── Phase1_BasicSelenium.java        # Simple main method execution
    └── test/java/
        ├── demo/
        │   ├── Phase2_Phase3_WebElementsAndTestNGTest.java
        │   └── Phase4_DataDrivenTest.java   # DataProvider implementation
        └── ecommerce/
            ├── pages/                       # Page Object Model (POM) classes
            │   ├── LoginPage.java
            │   ├── ProductsPage.java
            │   └── CartPage.java
            └── tests/
                └── ECommerceTest.java       # TestNG E2E test script
```

### Key Code Snippets

#### TestNG `@DataProvider` (Phase4_DataDrivenTest.java)
```java
@DataProvider(name = "loginCredentials")
public Object[][] provideLoginData() {
    return new Object[][] {
        {"tomsmith", "SuperSecretPassword!", true},   // Valid
        {"invalidUser", "wrongPassword", false},      // Invalid
        {"", "", false}                               // Empty
    };
}

@Test(dataProvider = "loginCredentials")
public void testLoginScenarios(String username, String password, boolean expectSuccess) {
    // ... test logic ...
}
```

#### Page Object Model with `@FindBy` (LoginPage.java)
```java
public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        // ...
        loginButton.click();
    }
}
```

#### TestNG Suite Execution (`testng.xml`)
```xml
<suite name="SeleniumLabSuite" verbose="1">
    <test name="E-Commerce End to End Test">
        <groups>
            <run><include name="regression" /></run>
        </groups>
        <classes>
            <class name="ecommerce.tests.ECommerceTest"/>
        </classes>
    </test>
</suite>
```

---

## Screenshots & Result

**Running `mvn clean test`:**
```text
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running TestSuite
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 18.22 s -- in TestSuite
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

### Result
All TestNG tests covering Phase 1 through Phase 5 successfully executed and passed as designed. The Selenium WebDriver interacted successfully with dynamically rendered elements, form data was accurately supplied via DataProviders, and the E-Commerce testing scenario was successfully architected using the Page Object Model.

---

## Answers to Case Study & Hands-on Activity Questions

### 1. Case Study
**Q: A software development company adopts Selenium. How can the team set up Selenium WebDriver, write and execute test scripts using TestNG, and perform parameterization and data-driven testing?**
We set up the framework by leveraging Maven (`pom.xml`) to manage dependencies (`selenium-java`, `testng`, `webdrivermanager`). We wrote test scripts utilizing TestNG lifecycle annotations (`@BeforeMethod`, `@Test`) to organize setup and execution phases. To perform data-driven testing, we utilized TestNG's `@DataProvider` to pass multiple sets of credentials (valid, invalid, empty) to a single `@Test` method, testing the various edge-cases of the login bounds securely and concisely.

**Q: How can they integrate Selenium tests with continuous integration tools like Jenkins to automate test execution and reporting?**
By setting up a Jenkins Freestyle or Pipeline job on the local CI server. It automatically pulls the `selenium_lab_4` codebase, sets up the workspace, and runs the `mvn clean test` build phase command. This commands triggers the `maven-surefire-plugin` which reads the `testng.xml` configuration, executes the Selenium tests, and automatically halts the Jenkins build state (Passed vs Failed) while outputting HTML/XML test reports.

### 2. Hands-on Activity
**Q: How would they practice writing test scripts to automate common web testing tasks, such as handling dynamic web elements and pop-ups?**
In our `Phase2_Phase3_WebElementsAndTestNGTest.java` script, we automated tasks utilizing the `Select` class for dropdowns and boolean (`isSelected()`) conditional logic for checkboxes. To handle pop-ups, we utilized target switching to transition context from the main web document to the browser's native JavaScript modal: `driver.switchTo().alert().accept()`. Synchronizing these actions is achieved utilizing Implicit Waits attached to the WebDriver instance.

**Q: What are the best practices for designing maintainable and scalable automation frameworks using Selenium?**
We leveraged the **Page Object Model (POM)** design pattern in the Case Study. POM isolates the HTML element locators (`@FindBy`) from the sequential test logic (`@Test` assertions). If a website's UI changes, developers only need to update the central `LoginPage.java` or `CartPage.java` object files, rather than modifying dozens of broken test scripts. Additionally, utilizing TestNG `groups` allows you to logically separate test runs (e.g., executing only "smoke" tests on standard commits).
