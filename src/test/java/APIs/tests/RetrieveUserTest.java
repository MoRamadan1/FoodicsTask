package APIs.tests;
import APIs.utils.APIUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
public class RetrieveUserTest {

    @Test(dependsOnMethods = "APIs.tests.CreateUserTest.testCreateUser" )
    public void testRetrieveUser() {
        int userId = CreateUserTest.getCreatedUserId();
        System.out.println("User ID to be checked : " + userId);
        Assert.assertTrue(userId > 0, "User ID should be valid");

        // Retrieve user details
        Response response = APIUtils.getUserById(userId);

        // Validate response
        response.then()
                .statusCode(200)
                .body("data.id", equalTo(userId));

        System.out.println("User retrieved successfully: " + response.asString());
    }

    /**
     * Note : Portal https://reqres.in/ is faking adding new users
     * So you can't retirve data with same ID
     * But when you overwrite userId with defined value in server e.g. (2)
     * function is working normally
     */
}
