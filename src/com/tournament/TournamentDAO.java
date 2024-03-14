package com.tournament;

import java.sql.Connection;
import utils.CommonLogger;
import java.sql.PreparedStatement;
import utils.DB;
import utils.Generator;
import utils.Query;
import utils.sqlFile;
import org.apache.log4j.Logger;
import java.sql.Timestamp;
import org.json.JSONObject;

import com.solution.SolutionDao;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.ResultSet;

public class TournamentDAO {

	static Logger logger = new CommonLogger(TournamentDAO.class).getLogger();
	Connection connection = DB.getConnection();

	private static TournamentDAO tournamentDAObj = new TournamentDAO();

	public static TournamentDAO getObj() {
		return tournamentDAObj;
	}

	public JSONObject joinTournament(String mailID) throws Exception {
		System.out.println("Mail : "+mailID);

		String Q_ID = null;

		JSONObject tournamentQuestionDetails = new JSONObject();
	String solutionID ="";
		try {

			PreparedStatement pstmt = connection.prepareStatement(Query.getQuestionsForTournament);
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			pstmt.setDate(1, currentDate);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				Q_ID = rs.getString("Q_ID");
			} else {
				Q_ID = generateTournamentQuestions();
			}

			PreparedStatement pstmt2 = connection.prepareStatement(Query.joinTournament);
			pstmt2.setString(1, mailID);
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			pstmt2.setTimestamp(2, currentTimestamp);
					ArrayList<String> languages = new ArrayList<>();
					languages.add("Java");
					languages.add("Python");

				solutionID = Generator.ValidNumerical(8, "Solutions", "Sol_ID");
			    	int langID = languages.indexOf("Python")+1;

					logger.info("inserting new Solution:"+solutionID+" mail:"+mailID+" question:"+Q_ID+" langID"+langID);
			    	SolutionDao solutionDao = new SolutionDao();
					solutionDao.insertSolution(solutionID, mailID, Q_ID, "ATTEMPTED", "TOURNAMENT", 2);
			// pstmt2.setString(3, Q_ID);

			java.sql.Date currentDate2 = new java.sql.Date(System.currentTimeMillis());
			pstmt2.setDate(3, currentDate2);

			int rs2 = pstmt2.executeUpdate();

			if (rs2 > 0) {
				PreparedStatement pstmt3 = connection.prepareStatement(Query.getQuestionDetailsForTournament);
				System.out.println(Q_ID + "jbhvgcftgvyubhsb");
				pstmt3.setString(1, Q_ID);

				ResultSet rs3 = pstmt3.executeQuery();

				if (rs3.next()) {
					sqlFile.append(pstmt.toString());
					JSONObject description = new JSONObject();
					tournamentQuestionDetails.put("questionName", rs3.getString("Q_name"));
					tournamentQuestionDetails.put("solID", solutionID);
					description.put("description", rs3.getString("description"));
					tournamentQuestionDetails.put("details", description);
					tournamentQuestionDetails.put("questionSyntax", rs3.getString("funcName"));
				}

		PreparedStatement pstmt4 = DB.getConnection().prepareStatement("UPDATE Tournament set Sol_ID=? where mailID=?");
		
		pstmt4.setString(1, solutionID);
		pstmt4.setString(2, mailID);
		System.out.println(pstmt4.toString());
		
		pstmt4.executeUpdate();
				logger.info(mailID + " joined in the tournament");

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error on join tournament : " + e);
			throw new Exception("Can't join tournament");
		}
		return tournamentQuestionDetails;
	}

	public boolean isSubmit(String mailID, String solution, double score) throws Exception {

		try {
			PreparedStatement pstmt = connection.prepareStatement(Query.submitTournament);
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(1, currentTimestamp);
			pstmt.setDouble(2, score);
			pstmt.setString(3, mailID);
			System.out.println(pstmt.toString());

			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				logger.info(mailID + " submitted the solution successfully");
				sqlFile.append(pstmt.toString());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error on submit the solution : " + e);
			throw new Exception(mailID + " can't submit the solution");
		}
		return false;
	}

	public JSONObject leaderBoard() throws Exception {

		JSONObject rankingObject = new JSONObject();

		try {
			PreparedStatement pstmt = connection.prepareStatement(Query.leaderBoard);
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			pstmt.setDate(1, currentDate);

			ResultSet rs = pstmt.executeQuery();

			int count = 0;

			while (rs.next()) {
				JSONObject participant = new JSONObject();
				participant.put("userName", rs.getString("userName"));
				participant.put("mailID", rs.getString("mailID"));
				participant.put("position", count+1);
				participant.put("timeTaken", rs.getString("timeTaken"));
				rankingObject.put(String.valueOf(count), participant);
				count++;
			}
		} catch (Exception e) {
			logger.error("Error on ranking: " + e);
			throw new Exception("Unable to retrieve leaderboard data. Please try again later.");
		}

		return rankingObject;
	}

	public String generateTournamentQuestions() throws Exception {

		String Q_ID = null;

		try {
			PreparedStatement pstmt = connection.prepareStatement(Query.generateQuestionsForTournament);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				logger.info("Question collected successfully.");
				Q_ID = rs.getString("Q_ID");
			} else {
				logger.error("Failed to collect question.");
			}

			PreparedStatement pstmt2 = connection.prepareStatement(Query.addQuestionsForTournament);
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			pstmt2.setDate(1, currentDate);
			pstmt2.setString(2, Q_ID);

			int rs1 = pstmt2.executeUpdate();

			if (rs1 > 0) {
				logger.info("Question added successfully");
				sqlFile.append(pstmt.toString());
			} else {
				logger.error("Failed to add question.");
			}
		} catch (Exception e) {
			logger.error("Error on generate tournament question : " + e);
			throw new Exception("Can't generate questions. Please try again!");
		}

		return Q_ID;
	}

	public long timeDifference(String mailID) throws Exception {

		long timeDiff = -1;

		try {
			PreparedStatement pstmt = connection.prepareStatement("select Join_time from Tournament where mailID = ?");
			pstmt.setString(1, mailID);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				LocalDateTime joinTime = rs.getTimestamp("Join_time").toLocalDateTime();
				LocalDateTime currentTime = LocalDateTime.now();

				// calculate time difference
				Duration duration = Duration.between(joinTime, currentTime);
				timeDiff = duration.toMinutes();
			}
		} catch (Exception e) {
			logger.error("Error on calculating time : " + e);
			throw new Exception("Error on calculating time : " + e.getMessage());
		}

		return timeDiff;
	}

	public void announceWinners() throws Exception {
		// get leaderboard and give badges to top 3;
		try {
			PreparedStatement pstmt = connection.prepareStatement("select Join_time from Tournament where mailID = ?");

			ResultSet rs = pstmt.executeQuery();

			ArrayList<String> badges = new ArrayList<>();
			badges.add("Ace_badge"); badges.add("Conquer_badge"); badges.add("Crown_badge");
			for (int i = 0; i < 3 && rs.next(); i++) {
				increaseBadge(rs.getString("mailID"), badges.get(i));
				
			}
		} catch (Exception e) {
			logger.error("Error on  : " + e);
			throw new Exception("Error on  : " + e.getMessage());
		}
	}

    private void increaseBadge(String mailID, String badgeName) throws Exception {
		// increase badge count in user table
		// UPDATE Users SET Ace_badge = Ace_badge + 1 WHERE mailID = 'user@example.com';
		try{
			PreparedStatement pstmt = connection.prepareStatement(Query.increaseBadgeCount);
			pstmt.setString(1, badgeName);
			pstmt.setString(2, badgeName);
			pstmt.setString(3, mailID);

			int affectedRow = pstmt.executeUpdate();

			if (affectedRow < 1) {
				logger.error("Badge count not increased for "+mailID);
				throw new Exception("Something went wrong on Tournament!");				
			}

		}catch(Exception e){
			logger.error("Error on increasing badge count for "+mailID+" error:"+e);
			throw new Exception("Something went wrong on Tournament!");
		}
    }




	public JSONObject getWinners() throws Exception{
		 
		 JSONObject winners = new JSONObject();
		 
		 try {
			 PreparedStatement pstmt = connection.prepareStatement(Query.getWinners);
			 java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			 pstmt.setDate(1, currentDate);
			 
			 ResultSet rs = pstmt.executeQuery();
			 
			 
			 int count = 0;
			 
			 while(rs.next()) {
				 count++;
				 winners.put(String.valueOf(count), rs.getString("mailID"));
			 }
		 }
		 catch(Exception e) {
			 logger.error("Error on winners : " + e);
			 throw new Exception("Unable to retrieve winners : " + e.getMessage());
		 }
		 
		 return winners;
	 }



	 public boolean checkIfHeAlreadyParticipated(String mailID) throws Exception {

		try{
			PreparedStatement pstmt = connection.prepareStatement(Query.checkUserParticipation);
			pstmt.setString(1,mailID);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()){
				return true;
			}

		}
		catch(Exception e){
			throw new Exception("Error on checkIfHeAlreadyParticipated : " + e.getMessage());
		}

		return false;
	 }

}