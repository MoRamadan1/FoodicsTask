Project Structure
The project is organized into the following packages:
APIs.config: Contains configuration management classes.
APIs.DataProviders: Contains data providers for generating test data.
APIs.tests: Contains test classes for different API operations.
APIs.utils: Contains utility classes for making API requests.

Key Classes
1. ConfigManager.java
Purpose: Manages configuration properties.
Key Methods:
getBaseUrl(): Returns the base URL for the API.

2. UserDataProvider.java
Purpose: Provides test data for user-related operations.
Key ethods:
getUserData(): Provides data for creating users.
ModifiedUserData(): Provides data for updating users.
getRandomFullName(): Generates a random full name for testing.

3. CreateUserTest.java 
Purpose: Tests the creation of users.
Key Methods:
testCreateUser(String name, String job, int age): Tests the creation of a user with the provided data.
getCreatedUserId(): Returns the ID of the created user.

4. RetrieveUserTest.java
Purpose: Tests the retrieval of user details.
Key Methods:
testRetrieveUser(): Tests retrieving the details of a user created in CreateUserTest.

5. UpdateUserTest.java
Purpose: Tests the updating of user details.
Key Methods:
testRetrieveUser(String name, String job): Tests updating the details of a user created in CreateUserTest.

6. APIUtils.java
Purpose: Provides utility methods for making API requests.
Key Methods:
createUser(String name, String job, int age): Creates a user and returns the response.
getUserById(int userId): Retrieves user details by ID.
PutUser(String name, String job, int userId): Updates user details.

Setup and Execution
Prerequisites
Java JDK 8 or higher
Maven ( Maven Reload is needed )
TestNG
Steps to Run the Tests
Clone the repository.
Navigate to the project directory.

Run the tests using TestNG.xml: