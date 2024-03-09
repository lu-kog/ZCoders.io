package com.tournament;

import java.sql.Connection;
import utils.CommonLogger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DB;
import utils.Query;
import utils.sqlFile;
import org.apache.log4j.Logger;
import java.sql.Timestamp;

public class TournamentDAO {

	static Logger logger = new CommonLogger(TournamentDAO.class).getLogger();
	Connection connection = DB.getConnection();

     private static TournamentDAO tournamentDAObj = new TournamentDAO();  

     public static TournamentDAO getObj() {
        return tournamentDAObj;
    }  


    public boolean isJoin(String mailID, String Q_ID) throws Exception {
		 
		 try {
			 PreparedStatement pstmt = connection.prepareStatement("insert into Tournament (mailID, Join_time, Q_ID, Date) values (?,?,?,?)");
			 pstmt.setString(1, mailID);
			 Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			 pstmt.setTimestamp(2, currentTimestamp);
			 pstmt.setString(3,Q_ID);
			 
			 java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			 pstmt.setDate(4, currentDate);
			 
			 int rs = pstmt.executeUpdate();
			 
			 if(rs > 0)
			 {
				 logger.info(mailID + " joined in the tournament");
				 sqlFile.append(pstmt.toString());
				 return true;
			 }
			 
		 }
		 catch(Exception e) {
			 logger.error("Error on join tournament : " + e);
			 throw new Exception("Can't join tournament");
		 }
		 return false;
	 }

     

}