package org.apache.maven.JavaCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 * @author - Chetan J Pradhan
 * @date -  05 March 2017
 * @project - JavaCSV
 * @package - org.apache.maven.JavaCSV
 * @filename - BadFileCreator.java
 */
public class BadFileCreator {
	
	/**
	   * Writes all records who does not match column count (bad records) to bad-data-<timestamp>.csv file under current directory
	   * Uses Apache Common CSV library for reading, formatting and writing CSV records
	   * @param List of bad records from CSVFileProcessor
	   * @exception any exception
	   * @return No return value.
	   */ 
	public static void writeBadRecords(List<CSVRecord> badRecords) {
		String NEW_LINE_SEPARATOR = "\n";
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD-HH-MM-SS");
		Date date = new Date();
		String badFileName = "bad-data"+dateFormat.format(date)+".csv";
		
		FileWriter wr = null;
		CSVPrinter csvWrite = null;
		CSVFormat csvFileFormatOut = CSVFormat.EXCEL.withRecordSeparator(NEW_LINE_SEPARATOR);
		
		try {
			wr = new FileWriter(badFileName);
			csvWrite = new CSVPrinter(wr, csvFileFormatOut);
			List badCSVRecord = new ArrayList(); //List to store every fields of a single bad record.
			
			//Loop through all bad records
			for(CSVRecord badRecord : badRecords) {
				
				//Loop through each field in a bad record and add to the list badCSVRecord
				for(int j=0;j<badRecord.size();j++) {
					badCSVRecord.add(badRecord.get(j));
				}
				
				//Writes the bad record to the CSV
				csvWrite.printRecord(badRecord);
			}
			System.out.println("CSV File with bad data created...");	
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				wr.flush();
				wr.close();
				csvWrite.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
