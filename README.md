# Selenium Java Spring Boot Test Automation

A sample end-to-end UI automation project using Java, Selenium WebDriver, Spring Boot, and JUnit 5. Implements Page Object Model (POM) structure and demonstrates best practices for UI test automation, including screenshot capture on test failure.

## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running Tests](#running-tests)
- [Screenshot on Failure](#screenshot-on-failure)
- [Project Structure](#project-structure)
- [Custom Assertions](#custom-assertions)
- [Contributing](#contributing)
- [License](#license)

## Overview

This repository demonstrates browser automation using Selenium WebDriver with Spring Boot integration. Automated UI tests leverage JUnit 5 and Allure for reporting, with features such as:
- Page Object Model (POM) for maintainable test code
- Dependency injection for test objects
- Automatic screenshots on assertion/operation failure for debugging

## Prerequisites

- Java 17+
- Maven
- Chrome or Firefox browser (web drivers configured via Spring Boot configuration)
- Internet access for dependency resolution

## Installation & Setup

Clone this repository:
```sh
git clone https://github.com/YOUR_REPO_URL/selenium-java
cd selenium-java
```

Install project dependencies:
```sh
mvn clean install
```

Configure application properties (credentials, URLs) in `src/main/resources/application.properties`:
```
app.login.username=your-username
app.login.password=your-password
app.baseUrl=<WebSite URL>
```

## Running Tests

To execute all UI tests:

```sh
mvn test
```

Allure report can be generated after the tests:

```sh
mvn allure:serve
```

## Screenshot on Failure

Whenever an assertion or element operation fails, a screenshot will be captured automatically and stored in the `screenshots/` directory at the project root. Screenshots are named by test and operation.

## Project Structure

```
src/
  main/
    java/
      com.master.selenium/
        config/        # Spring Boot WebDriver config
        core/          # BasePage, core utilities
        pages/         # Page Object Model classes
        reporting/     # Reporting utilities (if any)
        utils/         # ScreenshotUtils and other helpers
    resources/
      application.properties
  test/
    java/
      com.master.selenium.tests/    # Test classes (JUnit 5)
      com.master.selenium.utils/    # Test-specific utilities (AssertUtils)
      com.master.selenium.core/     # BaseTest
screenshots/                        # Captured screenshots on failure
```

## Custom Assertions

Assertion helper methods in `AssertUtils` provide auto-screenshot functionality for test failures and should be used instead of direct JUnit assertions for maximum visibility.

## Contributing

Feel free to fork this repository and submit pull requests. Suggestions and improvements welcome.

## License

This project is licensed under the MIT License.
