package org.apache.maven.JavaCSV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

/**
 * @author - Chetan J Pradhan
 * @date - 05 March 2017
 * @project - JavaCSV
 * @package - org.apache.maven.JavaCSV
 * @filename - DatabaseCreator.java
 */
public class DatabaseCreator {
	
	/**
	   * Connects to my MySQL database
	   * @param none
	   * @exception any exception
	   * @return connection
	   */ 
	public static Connection getConnection() throws Exception{
		try{
			String driver = "com.mysql.jdbc.Driver";
			String host = "jdbc:mysql://localhost:3306/ms3db?autoReconnect=true&useSSL=false"; //?autoReconnect=true&useSSL=false for avoiding SSL warning
			String username = "root";
			String password = "Chidambaram1!";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(host,username,password);
			System.out.println("Connected to MySQL Database");
			return conn;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	   * Creates Table X for storing good records
	   * @param none
	   * @exception any exception
	   * @return none
	   */ 
	public static void createTable() throws Exception {
		String tableQuery = "CREATE TABLE IF NOT EXISTS X("
							+"A VARCHAR(50), B VARCHAR(50), C VARCHAR(75), D VARCHAR(50),"
							+"E LONGTEXT, F VARCHAR(50), G FLOAT NULL, H BOOLEAN,"
							+"I BOOLEAN, J VARCHAR(75))";
		try {
			//Establish connection to database
			Connection conn = getConnection();
			
			//Using prepared statement to maximize efficiency
			PreparedStatement create = conn.prepareStatement(tableQuery);
			create.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	   * Writes good records to the table created above
	   * @param List of good records from CSVFileProcessor
	   * @exception any exception
	   * @return none
	   */ 
	public static void writeGoodRecords(List<CSVRecord> goodRecords) throws Exception{
		createTable();
		String insertQuery = "INSERT INTO X (A,B,C,D,E,F,G,H,I,J) VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement post = null;
		try{
			
			Connection conn = getConnection(); /*Establish connection to database*/
			
			//Loop in through good records
			for(CSVRecord record : goodRecords) {
				post = conn.prepareStatement(insertQuery);
				
				
				for(int i = 1;i<=6;i++) {
					//Loops in through columns A - F string columns
					
					if(i==5 && record.get(i-1).contains(",")) {
						//Only column E has elements containing ',' and below logic is to wrap the field with 
						//double quotes if the field has ','
						post.setString(i,"\""+record.get(i-1)+"\"");
					}
					else{
						post.setString(i, record.get(i-1));
					}
				}
				if(record.get("G").length()==0) {
					//Handles column G set to float type with empty values
					post.setFloat(7, (float) 0.00);
				}
				else{					
					try {
						//Catches exception for improper value type for G column at row 4002 which has headers as values
						post.setFloat(7, Float.parseFloat(record.get("G").substring(1)));
					}
					catch (NumberFormatException e) {
						continue;
					}
				}
				try {
					//Catches exception for improper value type for H and I columnS at row 4002 which has headers as values
					post.setBoolean(8, Boolean.parseBoolean(record.get("H")));
					post.setBoolean(9, Boolean.parseBoolean(record.get("I")));
				}
				catch (Exception e) {
					continue;
				}
				post.setString(10, record.get("J"));
				post.executeUpdate();
			}
			System.out.println("Insert completed...");
		}
		catch (StringIndexOutOfBoundsException e) {
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
