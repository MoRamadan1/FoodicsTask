package APIs.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIUtils {

    // Base URL for the API
    public static final String BASE_URL = "https://reqres.in";

    // Method to create a user and return response
    public static Response createUser(String name, String job, int age) {
        String jsonPayload = "{"
                + "\"name\": \"" + name + "\","
                + "\"job\": \"" + job + "\","
                + "\"age\": " + age
                + "}";

        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post("/api/users");
    }


    public static Response getUserById(int userId) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + userId);
//                .get("/api/users/" + 2);
    }

    public static Response PutUser(String name, String job, int userId) {
        String jsonPayload = "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"job\": \"" + job + "\"\n" +
                "}";

        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .put("/api/users/" + userId);

    }

}
