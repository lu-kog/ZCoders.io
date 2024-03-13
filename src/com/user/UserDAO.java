package com.user;

import java.sql.Connection;
import java.sql.Date;
import java.time.Duration;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import com.clan.ClanDAO;
import com.question.Question;
import com.question.QuestionDao;
import com.solution.Solution;
import com.solution.SolutionDao;

import utils.CommonLogger;
import utils.DB;
import utils.Generator;
import utils.Query;
import utils.sqlFile;

public class UserDAO {

	static Logger logger = (new CommonLogger(UserDAO.class)).getLogger();
	Connection connection = DB.getConnection();

	private static UserDAO userDAO = new UserDAO();

	public static UserDAO getObj() {
		return userDAO;
	}

	
	public boolean createAccount(String userName, String mailID, String passwd) throws Exception {
		// insert user in user table & insert passwd in Login table
		try {

			logger.info("New Account going to create for "+mailID+" useName:"+userName);

			PreparedStatement statement = connection.prepareStatement(Query.CreateAccount);
			Date currentDate = Date.valueOf(LocalDate.now());
			statement.setString(1, mailID);
			statement.setString(2, userName);
			statement.setDate(3, currentDate);
			statement.setDate(4, currentDate);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 0) {
				logger.error("can't create account: " + statement.toString());
				throw new Exception("Something went wrong!");
			} else {
				sqlFile.append(statement.toString());
				
				// inserting login creds
				statement = connection.prepareStatement(Query.InsertCredentials);
				statement.setString(1, mailID);
				statement.setString(2, passwd);
				statement.execute();
				logger.info("new account created successfully.");
				sqlFile.append(statement.toString());
				return true;
			}
		} catch (SQLException e) {
			logger.error("SQL error on creating account"+" mail:"+mailID+" userName"+userName+ e);
			throw new Exception("Oops! Something went wrong. Please contact admin.");
		}
	}
	
	
	public boolean LoginUser(String mailID, String passwd) throws Exception {
		System.out.println(mailID + " "+passwd);
		String hshPW = getPasswd(mailID);
		if (BCrypt.checkpw(passwd, hshPW)) {
			return true;
		} else {
			return false;
		}
	}

	private String getPasswd(String mailID) throws Exception {
		try {
			PreparedStatement pstmt = connection.prepareStatement(Query.getPasswordByMailID);
			pstmt.setString(1, mailID);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				logger.info("Fetch password by Mail ID:" + mailID);
				String pw = rs.getString("passwd");
				System.out.println(pw);
				logger.info("check password"+pw);
				return pw;
			} else {
				logger.error("Can't get password for this user:" + mailID);
				throw new Exception("Something went wrong! Please contact admin.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in getting passwd by mail:" + mailID + " error:" + e);
			throw new Exception("Oops! Something went wrong.");
		}
	}

	public static String createNewSession(String mailID) throws Exception {

		Connection conn = DB.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(Query.CreateNewSession);
			String sessionID = Generator.createUUID("Session", "sessionID");
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			statement.setString(1, sessionID);
			statement.setString(2, mailID);
			statement.setTimestamp(3, currentTimestamp);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 0) {
				logger.error("can't create new session: " + statement.toString());
				throw new Exception("Invalid session!");
			} else {
				logger.info("new session created successfully.");
				// sqlFile.append(statement.toString());
				return sessionID;
			}
		} catch (SQLException e) {
			logger.error("SQL error on creating session" + e);
			throw new Exception("Can't create session! Please contact admin.");
		}

	}

	public static void deleteSession(String sessionID) throws Exception {

		Connection conn = DB.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(Query.DeleteSession);

			statement.setString(1, sessionID);

			if (statement.execute()) {
				// sqlFile.append(statement.toString());
				logger.info("session deleted successfully.");
			}
		} catch (SQLException e) {
			logger.error("SQL error on deleting session" + e);
			throw new Exception("session error! Please contact admin.");
		}

	}

	public static boolean validateSession(String sessionID, String mailID) throws Exception {

		Connection conn = DB.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(Query.validateSession);

			statement.setString(1, mailID);
			statement.setString(2, sessionID);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				logger.info("valid session!" + sessionID);
				return true;

			} else {
				logger.info("Session invalid!" + sessionID+" mailID:"+mailID);
				return false;
			}
		} catch (SQLException e) {
			logger.error("SQL error on deleting session" + e);
			throw new Exception("session error! Please contact admin.");
		}

	}


	// public JSONArray getAllAuthoredQuestions(String mailID) throws Exception {
		
	// 	// select * from questions where author like ?;
	// 	JSONArray authoredQuestions = new JSONArray();
	// 	try {
	// 		PreparedStatement pstmt = connection.prepareStatement(Query.getAuthoredQuestionsOfaUser);
	// 		pstmt.setString(1, mailID);
	// 		ResultSet res = pstmt.executeQuery();
	// 		while(res.next()) {
	// 			JSONObject question = new JSONObject();
	// 			ArrayList<String> lang = getLangForQues(res.getString("Q_ID"));
	// 			question.put("languages", lang);
	// 			question.put("ques", res.getString("Q_name"));
	// 			question.put("level", res.getString("levelID"));
	// 			authoredQuestions.put(question);
	// 		}
	// 		logger.info("Authored Questions obtained");
			
	// 		return authoredQuestions;
	// 	} catch (Exception e) {
	// 		throw new Exception("Oops! something went wrong.");
	// 	}
	// }

	public List<Question> getAllAuthoredQuestions(User user) throws Exception {
		
		// select * from questions where author like ?;
		String mailID = user.getMailID();
		List<Question> authoredQuestions = new ArrayList<Question>();
		try {
			PreparedStatement pstmt = connection.prepareStatement(Query.getAuthoredQuestionsOfaUser);
			pstmt.setString(1, mailID);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				JSONObject question = new JSONObject();
				//List<String> lang = QuestionDao.getObj().getLangForQues(res.getString("Q_ID"));
				
				Question questions = new Question();
				
				questions.setQuestionName(res.getString("Q_name"));
				questions.setQuestionName(res.getString("level_name"));
				
				
				//JSONObject questionJSON = Questions.createJSON(questions);
				
				authoredQuestions.add(questions);
			}
			logger.info("Authored Questions obtained");
			
			return authoredQuestions;
		} catch (Exception e) {
			throw new Exception("Oops! something went wrong.");
		}
	}

	

	


	public List<Solution> fetchCompletedSolutionsOfAUser(User user, String status) throws Exception {
		String mailID = user.getMailID();
		try {
			List<Solution> completedSolutions = new ArrayList<Solution>();
			PreparedStatement pstmt = connection.prepareStatement(Query.getAllCompletedSolutionsOfaUser);
			
			pstmt.setString(1, status);
			pstmt.setString(2, mailID);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				Solution soln = new Solution();
				soln.setCode(res.getString("Sol"));
				soln.setStatusFromString(res.getString("status"));
				soln.setLanguage(res.getString("lang_name"));
				soln.setDate(res.getDate("solDate"));
				soln.setLevelname(res.getString("level_name"));
				soln.setQuestion(QuestionDao.getObj().fetchQuestionDetails(res.getString("Q_ID")));

				completedSolutions.add(soln);
				
			}
			
			logger.info("completed solutions fetched succesfully"+mailID);
			logger.info(completedSolutions);
			return completedSolutions;
		} catch (Exception e) {
			logger.error("Error on fetching completed solutions of "+mailID+" Error:"+e);
			throw new Exception("Can't fetch completed solutions");
		}
		

	}
	
	
	


	public JSONObject getStats(String userMail) throws Exception {
		/*
		 * Clan position, Total completed katas, Total authored katas, Language trained - solution count of each, 
		 */
		
		JSONObject respJson = new JSONObject();
		boolean availInClan = ClanDAO.getObj().AvailInAnyClan(userMail);
		try {
			logger.info("clan " + availInClan);
			System.out.println(availInClan);
			if (availInClan) {
				String clanID = ClanDAO.getObj().getClanId(userMail);
				int clanPosition = ClanDAO.getObj().getClanPosition(userMail, clanID);
				String clanName = ClanDAO.getObj().getClanName(clanID);
				respJson.put("clanID", clanID);
				respJson.put("clanName", clanName);
				respJson.put("clanPosition", clanPosition);
			}
			System.out.println("checking");
			int totalCompletedKatas = getTotalCompletedKata(userMail);
			System.out.println("kata");
			int totalAuthoredKatas = getTotalAuthoredKata(userMail);
			System.out.println("authored");
			ArrayList<Integer> kataCountForEachLang = getCountOfEachLang(userMail);
			System.out.println("checking - 2");
			// responses
			respJson.put("availInClan", availInClan);
			respJson.put("totalCompletedKatas", totalCompletedKatas);
			respJson.put("totalAuthoredKatas", totalAuthoredKatas);
			respJson.put("kataCountForEachLang", kataCountForEachLang);
			logger.info("Stats fetched successfully");
			return respJson;
		} catch (Exception e) {
			logger.error("Error on getStats : " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}


	private ArrayList<Integer> getCountOfEachLang(String userMail) throws Exception {
		ArrayList<String> langList = new ArrayList<String>();
		langList.add("python");
		langList.add("java");
		langList.add("javascript");
		
		ArrayList<Integer> countForLang = new ArrayList<Integer>();
		try {
			PreparedStatement statement = connection.prepareStatement(Query.getCountOfSolutionsForEachLang);
			statement.setString(1, userMail);
			for (int i = 0; i < langList.size(); i++) {
				statement.setString(2, langList.get(i));
				ResultSet result = statement.executeQuery();
				int solutionCount = 0;
				if(result.next()){
					solutionCount = result.getInt("solCount");
				}
				countForLang.add(solutionCount);
			}
	
			logger.info("Getting total count of solutions for each languages :"+countForLang+" user:"+userMail);
			return countForLang;
		} catch (Exception e) {
			throw new Exception("Oops! something went wrong..");
		}
		
	}


	private int getTotalAuthoredKata(String userMail) throws Exception {
		try {
			PreparedStatement statement = connection.prepareStatement(Query.getCountOfAuthoredQuestions);
			statement.setString(1, userMail);
			ResultSet result = statement.executeQuery();
			int totalAuthored = 0;
			if(result.next()){
				totalAuthored = result.getInt("AuthoredQuestions");
			}
			
			
			logger.info("Getting total count of authored kata of:"+userMail);
			return totalAuthored;
		} catch (Exception e) {
			throw new Exception("Can't get Authored kata count!");
		}
	}


	private int getTotalCompletedKata(String userMail) throws Exception {

		try {
			logger.info(Query.getCountOfCompletedSolutions);
			PreparedStatement statement = connection.prepareStatement(Query.getCountOfCompletedSolutions);
			statement.setString(1, userMail);
			ResultSet result = statement.executeQuery();
			int totalKata = 0;
			if(result.next()){
				totalKata = result.getInt("CompletedSolutions");
			}
			
			
			logger.info("Getting total count of completed solutions of:"+userMail);
			return totalKata;
		} catch (Exception e) {
			logger.error("Error on getting completed kata" + e);
			throw new Exception("Can't get solutions count!" + e.getMessage());		
		}
	}


	public User getUserProfileData(User user) throws Exception {
		/*
		 * thevaiyana porutkal: user name, score, clan name, member since, badges count, streak.
		 */
		String userMail = user.getMailID();
		try {
			PreparedStatement statement = connection.prepareStatement(Query.getFullUserData);
			System.out.println(statement.toString());
			statement.setString(1, userMail);
			ResultSet result = statement.executeQuery();
			System.out.println(statement.toString());
			//System.out.println(result.next());
			if (result.next()) {
				System.out.println("before object");
				User userData = new User();
				
				userData.setMailID(result.getString("mailID"));
                userData.setUserName(result.getString("username"));
                userData.setScore(result.getInt("score"));
                userData.setStreak(result.getInt("streak"));
				System.out.println("date");
                userData.setDateJoined(result.getDate("Datejoined").toLocalDate());
				userData.setAce_badge(result.getInt("Ace_badge"));
				userData.setAce_badge(result.getInt("Conquer_badge"));
				userData.setAce_badge(result.getInt("Crown_badge"));
				System.out.println(userData);
                 boolean inAnyClan = ClanDAO.getObj().AvailInAnyClan(userMail);
                // userData.put("inAnyClan", inAnyClan);
                if (inAnyClan) {
					String clanID = ClanDAO.getObj().getClanId(userMail);
					String clanName = ClanDAO.getObj().getClanName(clanID);
				}
				System.out.println(userData);
                return userData;
			}else {
				throw new Exception("User not found!");
			}		
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	public String getUserNameFromMailID(String mailID) throws Exception {
		
		String userName = null;
		try {
			PreparedStatement statement = connection.prepareStatement(Query.getUserNameFromMailID);
			statement.setString(1, mailID);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
                userName = result.getString("userName");
                return userName;
			}else {
				throw new Exception("name - User not found!");
			}		
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	public String getScoreFromMailID(String mailID) throws Exception {
		
		String score = null;
		try {
			PreparedStatement statement = connection.prepareStatement(Query.getScoreFromMailID);
			statement.setString(1, mailID);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
                score = result.getString("score");
                return score;
			}else {
				throw new Exception("sc - User not found!");
			}		
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}



	public String getClanNameFromMailID(String mailID) throws Exception {
		
		String clanName = null;
		try {
			PreparedStatement statement = connection.prepareStatement(Query.getClanNameFromMailID);
			statement.setString(1, mailID);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
    			clanName = result.getString("clanName");
    			logger.info("clanName " + clanName);
    			return clanName;
			} else {
    			throw new Exception("clan - User not found!");
			}
	
			
		} catch (Exception e) {
			return "";
		}
	}



	public JSONObject createJSON(User user) throws Exception {
			JSONObject userdDetails = new JSONObject();
			userdDetails.put("mailID", user.getMailID());
			userdDetails.put("name", user.getUserName());
			userdDetails.put("score", user.getScore());
			userdDetails.put("streak", user.getStreak());
			userdDetails.put("dateJoined", user.getDateJoined());
			userdDetails.put("ace_badge", user.getAce_badge());
			userdDetails.put("conquer_badge", user.getConquer_badge());
			userdDetails.put("crown_badge", user.getCrown_badge());
			
		
		
		return userdDetails;
	}

	public void updateScore(int levelID, String mailID) throws SQLException {
	
    try {
        PreparedStatement pstmt = connection.prepareStatement(Query.increaseScore); 
        pstmt.setInt(1, levelID);
        pstmt.setString(2, mailID);
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected == 0) {
            logger.warn("No user found with mailID: " + mailID);
        } else {
            logger.info("Score updated successfully for user with mailID: " + mailID);
        }
    } catch (SQLException e) {
        logger.error("Error updating score for user with mailID " + mailID + ": " + e.getMessage());
        throw e;
    }
}


	public void checkStreakCount(String user) throws Exception {
			
			try{
				PreparedStatement pstmt = connection.prepareStatement(Query.getStreakDetails);
				pstmt.setString(1, user);
				ResultSet res = pstmt.executeQuery();
				LocalDate currdate = LocalDate.now();
				LocalDate date = null;
            
				while(res.next()) {
					date = LocalDate.parse(res.getString("Streakdate"));
				
				}
			
				Duration diff = Duration.between(currdate.atStartOfDay(), date.atStartOfDay());
				long diffDays = diff.toDays();
				if(diffDays != 1) {
					//set streak to 0
					PreparedStatement pstmt2 = connection.prepareStatement(Query.setStreak);
					pstmt2.setString(1, user);
					int res2 = pstmt2.executeUpdate();
					if(res2 == 1) {
						PreparedStatement pstmt3 = connection.prepareStatement(Query.setStreakDate);
						pstmt3.setString(1, currdate.toString());
						pstmt3.setString(2, user);
						int res3 = pstmt3.executeUpdate();
						if(res3 == 1) {
							// 	//streak not maintained
							logger.error("Streak not maintained");
							
						}
					}
				
				}
				else if(diffDays == 1) {
					// 	//streak maintained
					logger.info("Streak maintained");
						
				
				}
				
			}
			catch (SQLException e) {
        		logger.error("Error on get streak count : " + e);
        		throw new Exception(e.getMessage());
    		} 
	
	}


	public void increaseStreakCount(String user) throws Exception {
    int streak = 0;
    
    try {
        PreparedStatement pstmt = connection.prepareStatement(Query.getStreakDetails);
        pstmt.setString(1, user);
		System.out.print(pstmt.toString());
        ResultSet res = pstmt.executeQuery();
        
        LocalDate currdate = LocalDate.now();
        LocalDate date = null;
        
        while (res.next()) {
            streak = res.getInt("streak");
            date = LocalDate.parse(res.getString("Streakdate"));
        }

        if (date.plusDays(streak).equals(currdate)) {
            PreparedStatement pstmt2 = connection.prepareStatement(Query.increaseStreak);
            pstmt2.setInt(1, streak + 1);
            pstmt2.setString(2, user);
            int res2 = pstmt2.executeUpdate();
            
            if (res2 == 1) {
                logger.info("Streak increased for the user " + user);
                
            }
        } else {
            logger.error("Streak does not increased for the user " + user);
        }
    } catch (SQLException e) {
		e.printStackTrace();
        logger.error("SQL Exception occurred: " + e.getMessage());
        throw new Exception("something went wrong please try again");
    } catch (DateTimeParseException e) {
        logger.error("Error parsing date: " + e.getMessage());
        throw new Exception("Error no parsing date");
    } catch (Exception e) {
		e.printStackTrace();
        logger.error("Error occurred: " + e.getMessage());
        throw new Exception("something went wrong please try again");
    }
    
    
	
}


	public String getMailIDFromUserName(String userName) throws Exception{

		String mailID = null;

		try{
			PreparedStatement pstmt = connection.prepareStatement(Query.getMailIDFromUserName);
			pstmt.setString(1, userName);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()){
				logger.info("MailId got successfully.");
				mailID = rs.getString("mailID");
			}
		}
		catch(Exception e){
			logger.error("Error on get mailID : " + e);
			throw new Exception("Failed to get mailID");
		}

		return mailID;
	}


	// public boolean isAdmin(String mailID){

	// 	try{
	// 		String clanID = ClanDAO.getObj().getClanId(mailID);
	// 		PreparedStatement pstmt = connection.PreparedStatement(Query.getClanRole);
	// 		pstmt.setString(1, mailID);
	// 		pstmt.setString(2, clanID);

	// 		ResultSet rs = pstmt.executeQuery();

	// 		if(rs.next()){
	// 			if(rs.getString("role").equals("ADMIN")){
	// 				return true;
	// 			}
	// 		}
	// 	}
	// 	catch(Exception e){
			
	// 		logger.error("Error occured: " + e);
	// 		throw new Exception("Error in getClanRole "+e.getMessage());
	// 	}

	// 	return false;
	// }

}
