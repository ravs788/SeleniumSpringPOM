# UI Automation Test Flow (Diagram)

The following diagram illustrates the flow of a typical end-to-end test in your Selenium/Spring Boot test suite:

```mermaid
flowchart TD
    %% Core classes
    subgraph Core
        BaseTest[BaseTest]
        BasePage[BasePage]
    end

    %% Page objects
    subgraph Pages
        LoginPage[LoginPage]
        MainPage[MainPage]
        CartPage[CartPage]
        CheckoutPage[CheckoutPage]
        CheckoutOverviewPage[CheckoutOverviewPage]
        CheckoutCompletePage[CheckoutCompletePage]
    end

    %% Utility classes
    subgraph Utilities
        AssertUtils[AssertUtils]
        ScreenshotUtils[ScreenshotUtils]
    end

    %% Relationships (no custom edge labels for max compatibility)
    LoginPage --> BasePage
    MainPage --> BasePage
    CartPage --> BasePage
    CheckoutPage --> BasePage
    CheckoutOverviewPage --> BasePage
    CheckoutCompletePage --> BasePage

    LoginTest[LoginTest] --> BaseTest
    LoginTest --> AssertUtils
    AssertUtils --> ScreenshotUtils

    %% Test Flow
    LoginTest --> LoginPage
    LoginPage --> MainPage
    MainPage --> MainPage
    MainPage --> CartPage
    CartPage --> CheckoutPage
    CheckoutPage --> CheckoutOverviewPage
    CheckoutOverviewPage --> CheckoutCompletePage
    CheckoutCompletePage --> MainPage
```

**Legend:**
- Boxes represent classes/roles.
- Arrows represent either navigation (test flow) or inheritance/usage relationships.
- Flow is top-down (test → pages → subsequent steps).

For a visual in your editor, paste the diagram block above into any Mermaid.js viewer or markdown editor with Mermaid support.

---

This diagram visualizes your available options for viewing Mermaid diagrams from the project.

---

## UI Test Flow – UML Sequence Diagram

```mermaid
sequenceDiagram
    participant Test as LoginTest
    participant LoginPage
    participant MainPage
    participant CartPage
    participant CheckoutPage
    participant CheckoutOverview as CheckoutOverviewPage
    participant CheckoutComplete as CheckoutCompletePage

    Test->>LoginPage: login(username, password)
    LoginPage-->>Test: (on success)
    Test->>MainPage: addProductToCartByName("Backpack")
    Test->>MainPage: addProductToCartByName("Bike Light")
    Test->>MainPage: openCart()
    MainPage-->>CartPage: (navigates)
    Test->>CartPage: clickCheckout()
    CartPage-->>CheckoutPage: (navigates)
    Test->>CheckoutPage: fillCheckoutForm(first, last, postal)
    CheckoutPage-->>CheckoutOverview: (navigates)
    Test->>CheckoutOverview: clickFinish()
    CheckoutOverview-->>CheckoutComplete: (navigates)
    Test->>CheckoutComplete: getCompleteHeaderText()
    CheckoutComplete-->>Test: "Thank you for your order!"
```
