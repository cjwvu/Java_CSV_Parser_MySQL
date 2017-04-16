package org.apache.maven.JavaCSV;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.StringIndexOutOfBoundsException;

import org.apache.log4j.Logger;

/**
 * @author - Chetan J Pradhan
 * @date - 04 March 2017
 * @project - JavaCSV
 * @package - org.apache.maven.JavaCSV
 * @filename - App.java
 */
public class App 
{
	public static int recordsReceivedCount = 0;
	public static int recordsSuccessCount = 0;
	public static int recordsFailedCount = 0;
	
	/**
	   * Main driver method
	   * @param A string array containing the command line arguments.
	   * @exception StringIndexOutOfBoundsException, FileNotFoundException, IOException
	   * @return No return value.
	   */ 
    public static void main( String[] args ) throws Exception
    {
    	//Sets path to read CSV file
    	String filePath = System.getProperty("user.dir")+"/CSVdata.csv";
    	
    	System.out.println("Reading CSV File...");
    	try {
			CSVFileProcessor.processFile(filePath);
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	finally {
    		LogFile.createLog();
    	}
    }
}
