package org.apache.maven.JavaCSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author - Chetan J Pradhan
 * @date -  09 March 2017
 * @project - JavaCSV
 * @package - org.apache.maven.JavaCSV
 * @filename - LogFileCreator.java
 */

public class LogFileCreator {

	private static final String FILENAME = "./Recordslog.log";

	/**
	   * Creates text log file using traditional approach as workaround under same directory.
	   * @param none
	   * @exception IOException
	   * @return No return value.
	   */ 
	public static void createLogFile() {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String info = "No. of records received: "+App.recordsReceivedCount+
					     "\nNo. of records successful: "+App.recordsSuccessCount+
					     "\nNo. of records failed: "+App.recordsFailedCount;

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(info);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}
	}
}
