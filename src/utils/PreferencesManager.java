package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PreferencesManager {
    private Properties properties = new Properties();
    private String propertiesFilePath = "resources\\properties.properties";

    public PreferencesManager(){
        loadProperties();
    }
    
    private void loadProperties(){
        try {
            // open prprt file
            FileInputStream input = new FileInputStream(propertiesFilePath);
            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getMovieCategory() {
        return Boolean.parseBoolean(properties.getProperty("movieCategory"));
    }

    public boolean getMusicCategory() {
        return Boolean.parseBoolean(properties.getProperty("musicCategory"));
    }

    public boolean getGeneralCategory() {
        return Boolean.parseBoolean(properties.getProperty("generalCategory"));
    }

    public boolean getMathCategory() {
        return Boolean.parseBoolean(properties.getProperty("mathCategory"));
    }

    public int getQuestionSize() {
        return Integer.parseInt(properties.getProperty("questionSize"));
    }

    public void setMovieCategory(boolean state) {
        properties.setProperty("movieCategory", Boolean.toString(state));
        saveProperties();
    }

    public void setMusicCategory(boolean state) {
        properties.setProperty("musicCategory", Boolean.toString(state));
        saveProperties();
    }

    public void setGeneralCategory(boolean state) {
        properties.setProperty("generalCategory", Boolean.toString(state));
        saveProperties();
    }

    public void setMathCategory(boolean state) {
        properties.setProperty("mathCategory", Boolean.toString(state));
        saveProperties();
    }

    public void setQuestionSize(int size) {
        properties.setProperty("questionSize", Integer.toString(size));
        saveProperties();
    }

    private void saveProperties() {
        try (FileOutputStream output = new FileOutputStream(propertiesFilePath)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
