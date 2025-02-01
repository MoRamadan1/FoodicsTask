The project is organized into the following packages:
Web.OrederCreation.pages: Contains page classes for different web pages (e.g., HomePage, LoginPage).
Web.OrederCreation.tests: Contains test classes (e.g., OrderPurchasing).
Web.Utilities: Contains utility classes (e.g., utilites) for common functionalities like browser setup and login.

Key Classes
1. HomePage.java
Purpose: Represents the home page of the e-commerce platform.
Key Methods:
navigateToAllVideoGames(): Navigates to the "Video Games" section.
applyFilteringCriteria(): Applies filters like "New" and "Free Shipping".
applySortingCriteria(): Sorts items by price (High to Low).
addItemsUnder15KToCart(): Adds items under 15,000 to the cart.
NavigateToCartAndCheckoverALlPrice(): Navigates to the cart and verifies the total price.
proceedToOrder(): Proceeds to checkout and adds a new delivery address.

2. LoginPage.java
Purpose: Represents the login page of the e-commerce platform.
Key Elements:
phoneNumberField: Input field for the phone number.
continueButton: Button to proceed after entering the phone number.
password: Input field for the password.
signinButton: Button to submit the login form.

3. OrderPurchasing.java
Purpose: Contains the test case for purchasing items.
Key Method:
grabbingAllItemsUnder(): Executes the test case to navigate, filter, sort, add items to the cart, and proceed to checkout.

4. utilites.java
Purpose: Provides utility methods for browser setup, login, and common actions.
Key Methods:
InitiateTestCase(): Initializes the WebDriver and navigates to the website.
Login(): Logs into the e-commerce platform.
closeBrowser(): Closes the browser after test execution.

Setup and Execution
Prerequisites
Java JDK 8 or higher
Maven ( Maven Reload is needed )
TestNG
ChromeDriver (or any other WebDriver compatible with your browser)

Steps to Run the Tests
Clone the repository.
Navigate to the project directory.
Run the tests using TestNG.xml: