package ca.retrylife.mc.contributorcapes.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;

import com.google.gson.Gson;

/**
 * Loading tools for user-facing config
 */
public class ConfigLoader {

    /**
     * Configuration datastructure
     */
    public static class CapeConfiguration {
        public String url;
        public String[] usernames;
    }

    /**
     * Load configuration from file
     * 
     * @return Configuration
     */
    public static CapeConfiguration loadConfiguration() {

        // Get the config file
        File configFile = Paths.get("contributorcapes.json").toFile();

        // Return null if the file does not exist
        if (!configFile.exists()) {
            return null;
        }

        // Use Gson to parse the file
        Gson gson = new Gson();

        // Load the file
        FileReader jsonReader;
        try {
            jsonReader = new FileReader(configFile);
        } catch (FileNotFoundException e) {
            return null;
        }

        // Return the data
        return gson.fromJson(jsonReader, CapeConfiguration.class);
    }

}