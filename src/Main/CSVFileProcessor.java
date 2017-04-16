package org.apache.maven.JavaCSV;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
//import java.math.BigDecimal;
import java.lang.String;
import java.lang.StringIndexOutOfBoundsException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

/**
 * @author - Chetan J Pradhan
 * @date - 05 March 2017
 * @project - JavaCSV
 * @package - org.apache.maven.JavaCSV
 * @filename - CSVFileProcessor.java
 */
public class CSVFileProcessor {
	
	/**
	   * Processed record in CSV file and sends good rocrds to DatabaseCreator and bad records to BadFileCreator
	   * Uses Apache Common CSV library for reading, formatting and parsing CSV records
	   * @param File path from App
	   * @exception StringIndexOutOfBoundsException IllegalArgumentException
	   * @return No return value.
	   */ 
	public static void processFile(String filePath) throws Exception{
		Reader in = null;
		CSVParser csvParser = null;
		CSVFormat csvFileFormatIn = CSVFormat.EXCEL.withHeader();
		
		List<CSVRecord> goodRecords = new ArrayList<CSVRecord>();
		List<CSVRecord> badRecords = new ArrayList<CSVRecord>();
		
		try {
			in = new FileReader(filePath);
		    csvParser = new CSVParser(in, csvFileFormatIn);   
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		//int i = 2;
	    for (CSVRecord record : csvParser) {
	    	//Loops in through all records in CSV file
	    	//System.out.println(i++ + " "+record.isConsistent() + " "+record.size());
	    	try {
	    		if(record.isConsistent()) {
	    			//Handles records with no. of columns matching to header count.
	    			App.recordsSuccessCount++;
	    			goodRecords.add(record);
	    		}
	    		else {
	    			badRecords.add(record);
	    			App.recordsFailedCount++;
	    		}
	    	}	
	    	catch (StringIndexOutOfBoundsException e) {
	    	}
	    	catch(IllegalArgumentException e) {
	    	}
	    }
	    App.recordsReceivedCount = App.recordsSuccessCount + App.recordsFailedCount;
	    DatabaseCreator.writeGoodRecords(goodRecords);
	    BadFileCreator.writeBadRecords(badRecords);
	    
	}	
}





