package APIs.DataProviders;

import org.testng.annotations.DataProvider;

import java.util.Random;

public class UserDataProvider {
    private static final String[] FIRST_NAMES = {"Ahmed", "Mohamed", "Hassan", "Omar", "Youssef", "Mostafa"};
    private static final String[] LAST_NAMES = {"Ali", "Mahmoud", "Sayed", "Ibrahim", "Fahmy", "Gamal"};


    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return new Object[][] {
                {getRandomFullName(), "Software Engineer", 30},
                {getRandomFullName(), "Validation Manager", 40},
                {getRandomFullName(), "Quality Engineer", 23},
                {getRandomFullName(), "Quality Manager", 50},
        };
    }

    @DataProvider(name = "ModifiedUserData")
    public Object[][] ModifiedUserData() {
        return new Object[][] {
                {getRandomFullName()+" Modified", "Software Engineer"},
                {getRandomFullName()+" Modified", "Validation Manager"},
                {getRandomFullName()+" Modified", "Quality Engineer"},
                {getRandomFullName()+" Modified", "Quality Manager"},
        };
    }

    public static String getRandomFullName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

}
