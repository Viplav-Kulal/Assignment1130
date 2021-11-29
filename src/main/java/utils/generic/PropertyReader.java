package utils.generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	public static Properties getProperty(String path) {

		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(path)) {
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;

	}

	public static String readPropertyFor(Properties fromProp, String forKey) {
		return fromProp.getProperty(forKey);
	}

}
