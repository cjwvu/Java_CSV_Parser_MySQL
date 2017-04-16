package org.apache.maven.JavaCSV;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * @author - Chetan J Pradhan
 * @date - 08 March 2017
 * @project - JavaCSV
 * @package - org.apache.maven.JavaCSV
 * @filename - LogFile.java
 */
public class LogFile 
{
	//For creating log file with time stamps
	static{  
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }
	static Logger logger = Logger.getLogger(App.class);
	
	/**
	   * Creates log file using log4j framework storing count of total, good and bad records
	   * @param none
	   * @exception none
	   * @return No return value.
	   */ 
	public static void createLog() {
		BasicConfigurator.configure();
		
        logger.info("No. of records received: "+App.recordsReceivedCount);
		logger.info("No. of records successful: "+App.recordsSuccessCount);
        logger.info("No. of records failed: "+App.recordsFailedCount);
        
        //Unable to create log file using log4j after the first time, hence creating a text .log file as a workaround
        LogFileCreator.createLogFile();
	}
}
