# Selenium & TestNG Lab Experiment Plan

This document outlines a structured, step-by-step plan to implement your Selenium & TestNG training syllabus as a comprehensive lab experiment, strictly adhering to **Java** as the programming language to fully utilize the **TestNG** framework.

## **Phase 1: Environment Setup & Basics (Week 1)**
**Objective:** Understand the ecosystem and write the first WebDriver script.
1. **Tool Setup:** Install Java (JDK), an IDE (Eclipse or IntelliJ IDEA), and Apache Maven.
2. **Project Initialization:** Create a Maven project and add dependencies for `selenium-java` and `webdrivermanager` to the `pom.xml`.
3. **Basic Scripting:** Write a simple Java `main` method to:
   - Initialize ChromeDriver.
   - Navigate to a demo website (e.g., [the-internet.herokuapp.com](http://the-internet.herokuapp.com/)).
   - Fetch and validate the page title.
   - Close the browser (`driver.quit();`).

## **Phase 2: Handling Web Elements & Interactions (Week 1-2)**
**Objective:** Master interactions with different types of HTML elements.
1. **Basic Elements:** Locate elements (ID, Name, XPath, CSS Selectors) to interact with text boxes, checkboxes, and radio buttons.
2. **Dynamic Elements:** Handle dropdowns using the `Select` class. Implement Explicit Waits (`WebDriverWait`) and Implicit Waits (`driver.manage().timeouts().implicitlyWait()`) to manage dynamically loaded content.
3. **Pop-ups & Frames:** Practice interacting with JavaScript Alerts (`driver.switchTo().alert()`), handling multiple browser tabs/windows, and switching into IFrames.

## **Phase 3: Introduction to TestNG Framework (Week 2)**
**Objective:** Migrate away from `main` methods and leverage a testing framework.
1. **Setup TestNG:** Add the `testng` dependency to the `pom.xml` and install the TestNG plugin in the IDE.
2. **First TestNG Test:** Convert the scripts from Phase 2 into `@Test` annotated methods.
3. **Annotations & Lifecycle:** Organize test teardown and setup using `@BeforeMethod`, `@AfterMethod`, `@BeforeClass`, and `@AfterClass` to prevent duplicate initialization code across your test suite.

## **Phase 4: Parameterization & Data-Driven Testing (Week 3)**
**Objective:** Run tests with different data sets and configurations.
1. **Cross-Browser Testing:** Use the `@Parameters` annotation and a `testng.xml` file to configure running your tests on Chrome, Firefox, and Edge.
2. **Data-Driven Approach:** Implement a `@DataProvider` method in Java to test a login form (e.g., standard user, locked-out user, incorrect password) automatically without duplicating the test method logic.
3. **Assertions:** Replace simple `if-else` validations with TestNG's Hard Assertions (`Assert.assertEquals`) and Soft Assertions for robust parallel validation.

## **Phase 5: Case Study Project - E-Commerce Testing (Week 4)**
**Objective:** Build a scalable, maintainable test automation framework for an e-commerce application.
1. **Scenario:** Test a complete business flow: Login -> Search Product -> Filter Results -> Add to Cart -> Proceed to Checkout.
2. **Design Pattern:** Implement the **Page Object Model (POM)**. Create separate Java classes for the Login Page, Home Page, and Cart Page to separate element locators (using `@FindBy`) from the test logic.
3. **Test Suite Creation:** Organize these tests logically using TestNG Groups (e.g., `groups = {"smoke", "regression"}`) and trigger them sequentially or in parallel via the `testng.xml` runner.

## **Phase 6: Continuous Integration & Reporting (Week 5)**
**Objective:** Automate test suite execution via CI/CD.
1. **Jenkins Setup:** Install Jenkins locally along with Java, Maven, and Git plugins.
2. **Freestyle Job:** Create a Jenkins job to pull the codebase (or use the local directory path) and execute the build using the Maven command (`mvn clean test`).
3. **Reporting:** Integrate the `maven-surefire-plugin` in `pom.xml` to generate TestNG's automated HTML reports (`emailable-report.html`) after the test execution runs in Jenkins, explicitly marking the pipeline as Passed or Failed based on the assertion results.
