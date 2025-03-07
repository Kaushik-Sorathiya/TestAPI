
package framework.logger;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class LoggerHelper {

	private static boolean root = false;

	public static Logger getLogger(@SuppressWarnings("rawtypes") Class clas) {
		if (root)
			return Logger.getLogger(clas);

		 String log4jprop = new File(System.getProperty("user.dir")).getAbsolutePath()
		 + "//log4j.properties";
		 PropertyConfigurator.configure(log4jprop);
		root = true;
		return Logger.getLogger(clas);
	}

}
