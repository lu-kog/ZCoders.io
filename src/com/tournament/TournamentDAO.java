package com.tournament;

import java.sql.Connection;
import utils.CommonLogger;
import java.sql.PreparedStatement;
import utils.DB;
import utils.Query;
import utils.sqlFile;
import org.apache.log4j.Logger;
import java.sql.Timestamp;
import org.json.JSONObject;
import java.sql.ResultSet;

public class TournamentDAO {

	static Logger logger = new CommonLogger(TournamentDAO.class).getLogger();
	Connection connection = DB.getConnection();

     private static TournamentDAO tournamentDAObj = new TournamentDAO();  

     public static TournamentDAO getObj() {
        return tournamentDAObj;
    }  


    public boolean joinTournament(String mailID, String Q_ID) throws Exception {
		 
		 try {
			 PreparedStatement pstmt = connection.prepareStatement(Query.joinTournament);
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


	 public boolean isSubmit(String mailID, String solution, double score) throws Exception {
		 
		 try {
			 PreparedStatement pstmt = connection.prepareStatement(Query.submitTournament);
			 Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			 pstmt.setTimestamp(1, currentTimestamp);
			 pstmt.setString(2, solution);
			 pstmt.setDouble(3, score);
			 pstmt.setString(4,mailID);
			 
			 
			 
			 int rs = pstmt.executeUpdate();
			 
			 if(rs > 0) {
				 logger.info(mailID + " submitted the solution successfully");
				 sqlFile.append(pstmt.toString());
				 return true;
			 }
		 }
		 catch(Exception e) {
			 logger.error("Error on submit the solution : " + e);
			 throw new Exception(mailID + " can't submit the solution");
		 }
		 return false;
	 }
	 

     
	 public JSONObject leaderBoard() throws Exception {
		
		    JSONObject rankingObject = new JSONObject(); 
		    
		    try {
		        PreparedStatement pstmt = connection.prepareStatement(Query.leaderBoard);
		        ResultSet rs = pstmt.executeQuery();
		        
		        int count = 0;
		        
		        while (rs.next()) {
		           
		            rankingObject.put(String.valueOf(count), rs.getString("mailID"));
		            count++; 
		        }
		    } catch (Exception e) {
		        logger.error("Error on ranking: " + e);
		        throw new Exception("Unable to retrieve leaderboard data. Please try again later.");
		    }
		    
		    return rankingObject;
		}

}