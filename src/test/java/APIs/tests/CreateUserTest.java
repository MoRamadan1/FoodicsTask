package APIs.tests;

import APIs.DataProviders.UserDataProvider;
import APIs.config.ConfigManager;
import APIs.utils.APIUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class CreateUserTest {

    private static int createdUserId; // Store user ID for retrieval test

    @Test(dataProvider = "userData", dataProviderClass = UserDataProvider.class)
    public void testCreateUser(String name, String job, int age) {
        // Create a user and get the response
        Response response = APIUtils.createUser(name, job, age);

        // Extract user ID from the response
        createdUserId = response.jsonPath().getInt("id");

        // Validate response
        response.then()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("job", equalTo(job));

        System.out.println("createdUserId : " + createdUserId);
        // Ensure user ID is generated
        Assert.assertTrue(createdUserId > 0, "User ID should be greater than 0");

        /**
         * Note : Portal https://reqres.in/ is faking adding new users
         * So you can't retirve data with same ID
         * But when you overwrite createdUserId with defined value in server e.g. (2)
         * function is working normally
         */
    }


    // Method to get the created user ID
    public static int getCreatedUserId() {
        return createdUserId;
    }
}
