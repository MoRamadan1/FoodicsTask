Overview
This repository contains end-to-end automated tests for both Web UI (using Selenium-Java + TestNG) and API (using Rest-Assured + TestNG). It demonstrates:

- Web Automation: Automating a user journey on Amazon.eg (https://www.amazon.eg/) including login, navigation, applying filters, adding items to the cart, and checkout steps (without final order submission).
- API Automation: Testing the reqres.in (https://reqres.in) public API for user creation, retrieval, and update.

The project follows best practices in configuration management, code organization, and version control.

--------------------------------------------------------------------------------
Project Structure

The code is split into two main components:

1. Web Automation

   - Web.OrederCreation.pages
     Contains page classes for different web pages, such as HomePage.java and LoginPage.java.

   - Web.OrederCreation.tests
     Contains TestNG test classes. For example, OrderPurchasing.java that drives the main end-to-end scenario.

   - Web.Utilities
     Houses utility classes (utilites.java) for browser setup, login, and other common functions.

2. API Automation

   - APIs.config
     Contains configuration management classes, such as ConfigManager.java for reading and managing properties (e.g., base URLs).

   - APIs.DataProviders
     Contains data provider classes (e.g., UserDataProvider.java) for generating test data for user creation and updates.

   - APIs.tests
     Contains all API test classes:
       • CreateUserTest.java
       • RetrieveUserTest.java
       • UpdateUserTest.java

   - APIs.utils
     Houses utility methods for making API requests (e.g., APIUtils.java).

--------------------------------------------------------------------------------
Web Automation: Key Classes and Flow

1. HomePage.java
   Purpose: Represents the Amazon.eg home page.
   Key Methods:
     - navigateToAllVideoGames(): Navigates to the "Video Games" section under "All".
     - applyFilteringCriteria(): Applies filters (e.g., Free Shipping, New condition).
     - applySortingCriteria(): Sorts items by price (High to Low).
     - addItemsUnder15KToCart(): Adds items that cost below 15,000 EGP to the cart (checks multiple pages if necessary).
     - navigateToCartAndCheckOverallPrice(): Navigates to the cart and verifies the total price.
     - proceedToOrder(): Proceeds to checkout and prompts for a new delivery address.

2. LoginPage.java
   Purpose: Represents the Amazon.eg login page.
   Key Elements:
     - phoneNumberField: Input field for the phone number.
     - continueButton: Button to proceed after entering the phone number.
     - password: Input field for the password.
     - signinButton: Button to submit the login form.

3. OrderPurchasing.java
   Purpose: Contains the end-to-end test case for purchasing items.
   Key Method:
     - grabbingAllItemsUnder(): Orchestrates the scenario of navigating, filtering, sorting, adding items to the cart, and proceeding to checkout.

4. utilites.java
   Purpose: Provides utility methods for browser setup, login, and common actions.
   Key Methods:
     - InitiateTestCase(): Initializes the WebDriver and navigates to the website.
     - Login(): Logs into Amazon.eg.
     - closeBrowser(): Closes the browser after test execution.

--------------------------------------------------------------------------------
Web Automation: End-to-End Test Scenario

1. Open Amazon.eg (https://www.amazon.eg/) and login
   - Enter phone number and password on the LoginPage.

2. Open “All” menu from the left side
   - Navigate to “Video Games” → “All Video Games”.

3. Apply filters
   - Condition: New
   - Free Shipping

4. Sort items by price: High to Low

5. Add items under 15,000 EGP to the cart
   - If no item is found on the current page, go to the next page and repeat.

6. Validate all items are in the cart

7. Add address and choose Cash as a payment method

8. Verify total amount (items cost + shipping fees if any)

9. Do not proceed with the order (stop before final submission).

--------------------------------------------------------------------------------
API Automation: Key Classes and Flow

1. ConfigManager.java (in APIs.config)
   - Manages configurations, such as the base URL for reqres.in.
   - getBaseUrl() returns the API base URL.

2. UserDataProvider.java (in APIs.DataProviders)
   - Generates test data for creating and updating users.
   - getUserData(): Provides data sets for user creation.
   - ModifiedUserData(): Provides data sets for user updates.
   - getRandomFullName(): Utility for generating a random full name.

3. CreateUserTest.java (in APIs.tests)
   - Purpose: Tests user creation.
   - Key Methods:
     - testCreateUser(String name, String job, int age): Validates successful user creation.
     - getCreatedUserId(): Returns the ID of the newly created user.

4. RetrieveUserTest.java (in APIs.tests)
   - Purpose: Tests retrieving user details.
   - Key Method:
     - testRetrieveUser(): Validates user details from the ID created in CreateUserTest.

5. UpdateUserTest.java (in APIs.tests)
   - Purpose: Tests updating user details.
   - Key Method:
     - testUpdateUser(String name, String job): Updates existing user details and validates the response.

6. APIUtils.java (in APIs.utils)
   - Purpose: Provides REST API utility methods.
   - Key Methods:
     - createUser(String name, String job, int age): Sends a POST request to /api/users.
     - getUserById(int userId): Sends a GET request to /api/users/{id}.
     - PutUser(String name, String job, int userId): Sends a PUT request to /api/users/{id}.

--------------------------------------------------------------------------------
Error Handling and Logging
- The framework includes checks to handle invalid requests, network failures, and unexpected responses.
- Detailed error messages are logged to help with debugging.

--------------------------------------------------------------------------------
Setup and Execution

Prerequisites
- Java JDK 8 or higher
- Maven (make sure you can run "mvn -v" on your terminal)
- TestNG (configured via Maven pom.xml)
- ChromeDriver (or other WebDriver compatible with your browser)
- Rest-Assured (configured via Maven pom.xml for API tests)

Installation Steps
1. Clone the repository
   git clone https://github.com/MoRamadan1/FoodicsTask.git

2. Navigate to the project directory
   cd YourRepo

3. Check Maven dependencies
   mvn clean install
   This will download and install all required dependencies.

How to Run Tests
Both the Web and API tests can be executed via TestNG.

1. Run all tests (Web + API together if configured in a single testng.xml):
   mvn test

2. Run specific TestNG suite if multiple suites are defined:
   mvn test -DsuiteXmlFile=TestNG_Web.xml
   mvn test -DsuiteXmlFile=TestNG_API.xml
   (Adjust the above commands based on your actual suite file names.)

Configuration Management
- Base URL for the API and other parameters are externalized in properties files (e.g., config.properties) or can be provided via environment variables.
- Browser options (e.g., ChromeDriver path) can also be set in properties or system environment variables.

--------------------------------------------------------------------------------
Code Quality and Version Control
- Code Quality: Follows Java best practices, modular test design, page object model for UI, and utility-based approach for API requests.
- Version Control: Managed via Git. Ensure frequent commits with descriptive messages and use branches for major features or bug fixes.

--------------------------------------------------------------------------------
Troubleshooting
- Browser Not Opening: Check that your ChromeDriver version matches your Chrome browser version.
- API Tests Failing: Verify that reqres.in is up and that you have an active internet connection.
- Configuration Issues: Make sure your property files or environment variables are loaded correctly.

--------------------------------------------------------------------------------
Contributing
1. Fork the repository.
2. Create a new feature branch (feature/your-feature).
3. Commit your changes with descriptive messages.
4. Open a pull request into the main branch.
