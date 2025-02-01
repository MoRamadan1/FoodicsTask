package APIs.tests;

import APIs.DataProviders.UserDataProvider;
import APIs.utils.APIUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest {

    @Test(dependsOnMethods = "APIs.tests.CreateUserTest.testCreateUser" , dataProvider = "ModifiedUserData", dataProviderClass = UserDataProvider.class )
    public void testRetrieveUser(String name , String job) {
        int userId = CreateUserTest.getCreatedUserId();
        System.out.println("User ID to be updated : " + userId);
        Assert.assertTrue(userId > 0, "User ID should be valid");

        // Retrieve user details
        Response response = APIUtils.PutUser(name , job , userId);

        // Validate response
        response.then()
                .statusCode(200)
                    .body("data.id", equalTo(userId));

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains(name), "Name was not updated correctly.");
        Assert.assertTrue(responseBody.contains(job), "Email was not updated correctly.");
        System.out.println("User retrieved successfully: " + response.asString());
    }

    /**
     * Note : Portal https://reqres.in/ is faking adding new users
     * So you can't retirve data with same ID
     * But when you overwrite userId with defined value in server e.g. (2)
     * function is working normally
     */
}
