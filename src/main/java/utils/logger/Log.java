package utils.logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.Reporter;

public class Log {

	public static final String PROJECT_PATH = new File("").getAbsolutePath();
	public static final String TIME_STAMP = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
			.format(Calendar.getInstance().getTime());
	private static final Logger LOGGER = Logger.getLogger("logger");
	private static PatternLayout layout = new PatternLayout("%d{dd MMM yyyy HH:mm:ss} %5p %c{1} - %m%n");
	private static FileAppender appender;
	private static ConsoleAppender consoleAppender;

	static {
		try {

			consoleAppender = new ConsoleAppender(layout, "System.out");
			appender = new FileAppender(layout,
					(PROJECT_PATH + "/Logs/LogFile_" + TIME_STAMP + ".txt").replace("/", File.separator), true);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * To display errors in log called from catch block.
	 * 
	 * @param className
	 *            name of class in which error occurred.
	 * @param methodName
	 *            name of method in which error occurred.
	 * @param exception
	 *            stack trace of exception
	 */
	public static void error(String className, String methodName, String exception) {
		LOGGER.addAppender(appender);
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("ClassName :" + className);
		LOGGER.info("MethodName :" + methodName);
		LOGGER.info("Exception :" + exception);
		LOGGER.info("-----------------------------------------------------------------------------------");
	}

	/**
	 * To display error in logs
	 * 
	 * @param message
	 *            message to be displayed
	 */
	public static void error(String message) {
		consoleAppender.setName("Console");
		LOGGER.addAppender(consoleAppender);
		LOGGER.addAppender(appender);
		LOGGER.setLevel(Level.INFO);
		LOGGER.info(message);
		Reporter.log(message);
		LOGGER.info("-----------------------------------------------------------------------------------");
	}

	/**
	 * To display information in logs
	 * 
	 * @param message
	 *            message to be displayed
	 */
	public static void info(String message) {
		consoleAppender.setName("Console");
		LOGGER.addAppender(consoleAppender);
		LOGGER.addAppender(appender);
		LOGGER.setLevel(Level.INFO);
		LOGGER.info(message);
		Reporter.log(message);
	}

}