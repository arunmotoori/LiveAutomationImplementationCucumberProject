package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CommonUtils {
	
	public static String generateEmailWithNanoTime() {
        long nanoTime = System.nanoTime();
        return "user_" + nanoTime + "@example.com";
    }
	
	public static Properties loadPropertiesFile() {
		
		Properties prop = new Properties();
		
		FileReader fr = null;
		
		try {
			fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\ProjectConfig.properties");
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		

	}

}
