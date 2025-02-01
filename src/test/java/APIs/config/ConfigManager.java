package APIs.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private Properties properties;
    private String BaseURL = "src/main/resources/config.properties" ;
    public ConfigManager() {
        properties = new Properties();

    }



    public String getBaseUrl() {
        return BaseURL;
    }
}
