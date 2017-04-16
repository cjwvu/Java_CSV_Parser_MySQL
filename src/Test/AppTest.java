package org.apache.maven.JavaCSV;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception
    {
    	//Simple test to match the no. of records inserted in database with the actual count of good records
    	Statement stmt = null;
        ResultSet rs = null;
        int rowCount = -1;
    	String countRows = "SELECT count(*) FROM ms3db.x";
    	
    	try {
			//Establish connection to database
			Connection conn = DatabaseCreator.getConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(countRows);
			rs.next();
		    rowCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	finally {
    	      rs.close();
    	      stmt.close();
    	}
        assertEquals( rowCount, 5995 ); //+1(5996) in log due to header
    }
}
