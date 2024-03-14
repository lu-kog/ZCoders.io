package utils;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

public class sqlFile {
	static Logger logger = new CommonLogger(sqlFile.class).getLogger();

	
	public static void append(String statement) {
		String filePath = "/home/workspace/Coders.io/Data.sql";
		String query = statement.substring(statement.indexOf(":") + 1).trim();
		try {
            FileWriter writer = new FileWriter(filePath, true); // true parameter for appending
            writer.write(query+"\n");
            writer.close();
        } catch (IOException e) {
            logger.error("Can't append to sql file: "+ query);
        }

	}
	
	
}
