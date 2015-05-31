package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/*this is a class for reading from properties files; 
it has 2 methods: one for reading from OR.properties and another for config.properties
*/
public class ReadingProperties {

	private static final Logger LOGGER = Logger.getLogger(ReadingProperties.class);
	public String value;
	public String key;
	static Properties propertyOR = new Properties();
	static Properties propertyCONFIG = new Properties();

	// **************************************************************

	public ReadingProperties()
	{
		loadConfigProperties();
	}
	
	private static final boolean loadConfigProperties()
	{
		boolean success=false;
		FileInputStream fis = null ;
		try
		{			
			fis= new FileInputStream(System.getProperty("user.dir") + "/src/test/java/config/config.properties");
			propertyCONFIG.load(fis);
			success=true;			
		}
		
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e);
	}
		finally
		{
			if (fis!=null)
			{
				try {
					fis.close();
				} catch (IOException e) {
					LOGGER.debug(e);
					e.printStackTrace();
				}
			}
		}
	return success;
}

// *****************************************************************************
public String readConfigProperties(String key) {
	
	// String val=property.getProperty(key);
	if (propertyCONFIG.getProperty(key) != null)// check if key exists in config.properties file
	{
		LOGGER.info("Successfully read from config.properties file <"+ key + ">");
		value = propertyCONFIG.getProperty(key);
	}
	else {
		LOGGER.debug("Could not read specified key from the config.properties file <"+ key + ">");
	}
	return value;
}

// *****************************************************************************

}
//  TEST FOR  readingProperties method
class TestReadingProperies {
	public static void main(String args[]) {
		Logger log = Logger.getLogger("LOGGER");
		ReadingProperties rp = new ReadingProperties();
		String configVal = rp.readConfigProperties("fail.screenshot.path");
		System.out.println("<fail.screenshot.path> :" + configVal);

		log.info("reading config.properties file value is <"
				+ configVal + ">");

	}
}