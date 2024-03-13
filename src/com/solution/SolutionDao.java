package com.solution;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.question.Question;
import com.question.QuestionDao;
import com.user.User;

import utils.CommonLogger;
import utils.DB;
import utils.Query;

public class SolutionDao {

	private static SolutionDao solutionDAObj = new SolutionDao();

	public static SolutionDao getObj() {
		return solutionDAObj;
	}

	private Connection connection;
	Logger logger = new CommonLogger(SolutionDao.class).getLogger();

	public SolutionDao() {
		connection = DB.getConnection();
	}

	public boolean insertSolution(String solID, String mailId, String qId, String status, String solvedType, int langID)
			throws Exception {
		String query = Query.insertSolution;
		System.out.println(query);
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			System.out.println("Checking for error in SolutionDAO");
			System.out.println(statement.toString());
			statement.setString(1, solID);
			statement.setString(2, mailId);
			statement.setString(3, qId);
			statement.setInt(4, langID); // langID
			statement.setDate(5, Date.valueOf(LocalDate.now())); // sol date
			statement.setString(6, status);
			statement.setString(7, solvedType);
			int rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected);
			if (rowsAffected > 0) {
				System.out.println("return true");
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("can't insert the solution");
			throw new Exception("can't insert the solution");
		}
		logger.info("insert solution to database successfully!!!!");
		return false;
	}

	// Method to update the solution text in the Solution table
	public boolean updateSolution(String solId, String newSolution) throws Exception {
		boolean success = false;
		String query = Query.updateSolution;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, newSolution);
			preparedStatement.setString(2, solId);
			logger.info(preparedStatement.toString());
			int rowsUpdated = preparedStatement.executeUpdate();
			System.out.println(rowsUpdated);
			success = rowsUpdated > 0;
			logger.info("for this solution updation" + success);
		} catch (Exception e) {
			logger.error("can't update the solution");
			e.printStackTrace();
			throw new Exception("can't update the solution");
		}
		logger.info("update the solution to the database successfully!!");
		logger.info(success);
		System.out.println(success);
		return success;
	}

	// Method to check if the user has completed the question
	public boolean isQuestionCompleted(String questionId, String userId) throws Exception {
		String query = Query.QuestionCompleted;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, questionId);
			statement.setString(2, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				logger.info("the user has completed the question check");
				return count > 0;
			}

		} catch (Exception e) {
			// Logger
			logger.error("can't find his paticipation on this question");
			throw new Exception("can't find his paticipation on this question");
			// TODO Auto-generated catch block
		}
		return false;
	}

	// Method to check if the user has attempt the question
	public ArrayList<String> getAttemptedSolution(String questionId, String mailId, String languageName)
			throws Exception {
		String query = Query.QuestionAttempt;
		ArrayList<String> sol = new ArrayList<String>();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, questionId);
			statement.setString(2, mailId);
			statement.setString(3, languageName);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				sol.add(resultSet.getString("Sol"));
				sol.add(resultSet.getString("Sol_ID"));

				logger.info("fecth solution is successfully!!!" + sol);
			}

		} catch (Exception e) {
			// Logger
			logger.error("can't find his paticipation on this question");
			throw new Exception("can't find his paticipation on this question");
			// TODO Auto-generated catch block
		}
		return sol;
	}

	public boolean isAnyAttemptedSolution(String questionId, String mailId, String languageName) throws Exception {
		String query = Query.isAnyAttemptedSolution;
		int noOfAttemptedSolution = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, questionId);
			statement.setString(2, mailId);
			statement.setString(3, languageName);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				noOfAttemptedSolution = resultSet.getInt(1);

				logger.info("no of solution is " + noOfAttemptedSolution);
			}

		} catch (Exception e) {
			// Logger
			logger.error("can't find his paticipation on this question" + e);
			throw new Exception("can't find his paticipation on this question");
			// TODO Auto-generated catch block
		}
		return noOfAttemptedSolution > 0;
	}

	// Method to fetch function name from the question
	public String fecthFuncName(String questionId, String lang) throws Exception {
		String query = Query.fecthFuncName;

		// String query = "SELECT funcName FROM LanguageRelation lr JOIN Languages l ON
		// lr.l_ID = l.l_ID WHERE l.lang_name like ? AND lr.Q_ID like ?";
		try {
			System.out.println(query);
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, lang);
			statement.setString(2, questionId);

			System.out.println(lang + "   :   " + questionId);
			// Statement statement = connection.createStatement();
			// ResultSet resultSet = statement.executeQuery("SELECT funcName FROM
			// LanguageRelation lr JOIN Languages l ON lr.l_ID = l.l_ID WHERE l.lang_name =
			// " + lang + " AND lr.Q_ID = " + questionId);
			ResultSet resultSet = statement.executeQuery();
			System.out.println("result1");
			if (resultSet.next()) {
				System.out.println("result");
				String sol = resultSet.getString("funcName");
				logger.info("fecth funcName is successfully!!!");
				return sol;
			} else {
				System.out.println("no result set");
			}

		} catch (Exception e) {
			// Logger
			logger.error("can't find his paticipation on this question");
			throw new Exception("can't find his paticipation on this question");
			// TODO Auto-generated catch block
		}
		return "";
	}

	public Solution solutionDetails(String solId) throws Exception {
		Solution solution = new Solution();
		String query = Query.fetchSolutionDetails;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, solId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String mailID = resultSet.getString("mailID");
				String userName = resultSet.getString("userName");
				String Q_ID = resultSet.getString("Q_ID");
				String sol = resultSet.getString("Sol");
				String status = resultSet.getString("status");
				String solvedType = resultSet.getString("solvedType");
				String language = resultSet.getString("lang_name");
				User user = new User();

				user.setMailID(mailID);
				user.setUserName(userName);
				System.out.println(Q_ID);
				Question question = new QuestionDao().fetchQuestionDetails(Q_ID);
				System.out.println("sol" + sol);
				solution.setSolID(solId);
				solution.setQuestion(question);
				solution.setUser(user);
				System.out.println("Solution Code : " + sol);
				solution.setCode(sol);
				solution.setLanguage(language);
				solution.setStatusFromString(status);
				solution.setSolvedTypeFromString(solvedType);
			}
		} catch (Exception e) {
			logger.error("can't get the solutionDetails");
			e.printStackTrace();
			throw new Exception("can't get the solutionDetails");
		}
		logger.info("fecth the solution details successfully!!!!");
		return solution;
	}

	// Method to fetch Solution for a completed question
	public List<Solution> fetchSolution(String questionId, String userId, String attemptStatus) throws Exception {
		List<Solution> Solution = new ArrayList<>();
		String query = Query.fetchSolutions;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, questionId);
			statement.setString(2, userId);
			statement.setString(3, attemptStatus);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				// Create a new Solution object for each row and add it to the list
				Solution solution = new Solution();
				solution.setSolID("Sol_ID");
				solution.setCode(resultSet.getString("Sol"));
				solution.setStatusFromString(resultSet.getString("status"));
				solution.setQuestion(QuestionDao.getObj().fetchQuestionDetails(questionId));
				Solution.add(solution);
			}
		} catch (SQLException e) {
			// Log
			logger.error("Can't fetch Solution for this question");
			throw new Exception("Can't fetch Solution for this question");
		}
		logger.info("List of Solution fecth successfully!!!!");
		return Solution;
	}

	// Method to fetch all Solution for a completed question
	public List<Solution> fetchAllSolution(String questionId, String attemptStatus, String language) throws Exception {
		List<Solution> Solutions = new ArrayList<>();
		String query = Query.fetchAllSolutions;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, questionId);
			statement.setString(2, attemptStatus);
			statement.setString(3, language);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Solution solution = new Solution();
				solution.setSolID(resultSet.getString("Sol_ID"));
				solution.setCode(resultSet.getString("Sol"));
				System.out.println(solution.getCode());
				solution.setStatusFromString(resultSet.getString("status"));
				// Create a Users object and set it for the solution
				solution.setDate(resultSet.getDate("solDate"));
				System.out.println(solution.getDate());
				User user = getUser(resultSet.getString("mailID"));
				solution.setUser(user);
				Solutions.add(solution);
			}
		} catch (SQLException e) {
			// Log
			// System.out.println(e.getMessage());
			logger.error("SQL error on fetching solution for QID:" + questionId + " error:" + e);
			throw new Exception("Can't fetch Solution for this question");
		}
		logger.info("List of Solution fecth successfully!!!!");
		return Solutions;
	}

	private User getUser(String userId) throws Exception {
		String query = Query.getUser;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				User user = new User(resultSet.getString("mailID"), resultSet.getString("userName"),
						resultSet.getInt("score"), resultSet.getInt("streak"));
				return user;
			} else {
				throw new Exception("Can't fetch user details");
			}
		} catch (Exception e) {
			// Log
			// System.out.println(e.getMessage());
			logger.error("Can't fetch user details");
			throw new Exception("Can't fetch user details");
		}
	}

	public String getSolutionString(String SolID) throws Exception {
		String solution = "";
		PreparedStatement pstmt = connection.prepareStatement(Query.getSolution);
		pstmt.setString(1, SolID);
		ResultSet res = pstmt.executeQuery();
		if (res.next()) {
			solution = res.getString(1);
		}

		return solution;
	}

	public JSONArray createJSON(List<Solution> solutions) throws Exception {
		JSONArray completedSolution = new JSONArray();
		for (int i = 0; i < solutions.size(); i++) {
			JSONObject completedSolutionJSON = new JSONObject();
			// String question =
			// QuestionDao.getObj().getQuestion(solutions.get(i).getQuestion().getQuestionID());

			Question questionDetails = solutions.get(i).getQuestion();
			System.out.println(questionDetails);
			completedSolutionJSON.put("StatusCode", "200");
			completedSolutionJSON.put("QID", questionDetails.getQuestionID());
			completedSolutionJSON.put("QName", questionDetails.getQuestionName());

			JSONObject levelObject = new JSONObject();
			levelObject.put("levelID", questionDetails.getLevel().getLevelId());
			levelObject.put("levelName", questionDetails.getLevel().getLevelName());
			levelObject.put("score", questionDetails.getLevel().getScore());

			completedSolutionJSON.put("level", levelObject);

			JSONArray languagesArray = new JSONArray();
			for (int j = 0; j < questionDetails.getLanguage().size(); j++) {
				languagesArray.put(questionDetails.getLanguage().get(j).getLanguageName());
			}
			completedSolutionJSON.put("languages", languagesArray);

			JSONObject detailsObject = new JSONObject();
			detailsObject.put("description", questionDetails.getDescription());
			detailsObject.put("example", questionDetails.getExample());

			JSONArray tagsArray = new JSONArray();
			for (int j = 0; j < questionDetails.getTags().size(); j++) {
				tagsArray.put(questionDetails.getTags().get(j).getTag());
			}
			detailsObject.put("tags", tagsArray);

			completedSolutionJSON.put("Details", detailsObject);

			completedSolutionJSON.put("noOfCOmpleted", questionDetails.getnoOfTimesSubmitted());

			JSONObject userObject = new JSONObject();
			userObject.put("mailID", questionDetails.getUser().getMailID());
			userObject.put("userName", questionDetails.getUser().getUserName());

			completedSolutionJSON.put("user", userObject);
			completedSolutionJSON.put("status", 200);
			completedSolutionJSON.put("attempt", questionDetails.getnoOfAttempt());
			completedSolutionJSON.put("submit", questionDetails.getnoOfTimesSubmitted());
			// completedSolutionJSON.put("questionID",
			// solutions.get(i).getQuestion().getQuestionID());
			// completedSolutionJSON.put("question",
			// solutions.get(i).getQuestion().getQuestionName());
			completedSolutionJSON.put("solution", solutions.get(i).getCode());
			completedSolutionJSON.put("status", solutions.get(i).getStatus());
			completedSolutionJSON.put("language", solutions.get(i).getLanguage());
			completedSolutionJSON.put("date", solutions.get(i).getDate());
			completedSolutionJSON.put("level", solutions.get(i).getLevelname());

			completedSolution.put(completedSolutionJSON);
		}

		return completedSolution;
	}

	public ArrayList<LocalDate> getDateOfSolutionSolved(int month, String mailId) throws SQLException {
		ArrayList<LocalDate> solvedDatesList = new ArrayList<LocalDate>();
		String query = Query.getSolutionDateOfAMonth;
		Connection connection = null;
		connection = DB.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, month);
		pstmt.setString(2, mailId);
		System.out.println(pstmt.toString());
		ResultSet solvedDates = pstmt.executeQuery();
		while (solvedDates.next()) {
			solvedDatesList.add(solvedDates.getDate("solDate").toLocalDate());
		}

		return solvedDatesList;
	}

	public ArrayList<Question> getQuestionsSolvedOnDate(LocalDate date, String mailId) throws Exception {
		ArrayList<Question> questionsSolvedOnDateList = new ArrayList<Question>();
		String query = Query.getQIDforDate;
		Connection connection = null;
		connection = DB.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, mailId);
		pstmt.setDate(2, java.sql.Date.valueOf(date));
		System.out.println(pstmt.toString());
		ResultSet questionsSolvedOnDate = pstmt.executeQuery();
		QuestionDao questionDao = QuestionDao.getObj();
		while (questionsSolvedOnDate.next()) {
			System.out.println("question  :  " + questionsSolvedOnDate.getString("Q_ID"));
			Question question = questionDao.fetchQuestionDetails(questionsSolvedOnDate.getString("Q_ID"));
			questionsSolvedOnDateList.add(question);
		}
		return questionsSolvedOnDateList;
	}

	public boolean updateStatus(String solId, String status) throws SQLException {
		String query = "Update Solutions set status=? where Sol_ID=?";
		PreparedStatement pstmt = DB.getConnection().prepareStatement(query);
		pstmt.setString(1,status);
		pstmt.setString(2,solId);

		return pstmt.executeUpdate()==1;
	}

	public JSONArray getSolutionsDates(String mailId) throws SQLException {
		JSONArray solutionsDateJsonArray = new JSONArray();
		String query = Query.getSolutionDates;
		PreparedStatement pstmt = DB.getConnection().prepareStatement(query);
		pstmt.setString(1, mailId);
		System.out.println(pstmt.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println("lkjbhcgfxdzsxfgchvjbkml;,mkjhbvgf");
			solutionsDateJsonArray.put(rs.getString("solDate"));
		}
		System.out.println("Dates : ");
		System.out.println(solutionsDateJsonArray);
		return solutionsDateJsonArray;
	}

    public void addExecutionTime(long executionTime,String solID) throws Exception {
		String query= Query.setExecutionTime;
		PreparedStatement pstmt = DB.getConnection().prepareStatement(query);
		pstmt.setLong(1, executionTime);
		pstmt.setString(2, solID);
		int no_of_rows_updated = pstmt.executeUpdate();
		if(no_of_rows_updated ==0){
			throw new Exception("Execution Time not updated!!");
		}
    }

    public double getExecutionTimeMillis(String Sol_ID,String mailID) throws Exception {
		String query = Query.getMilliSecond;
		PreparedStatement pstmt = DB.getConnection().prepareStatement(query);
		pstmt.setString(1,Sol_ID);
		pstmt.setString(2,mailID);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return rs.getDouble("Execution_time");
		}
		throw new Exception("Invalid Details");
    }

    public long getTimeDiffernce(String Sol_ID,String mailID) throws Exception {
		String query = Query.getTimeDifference;
		PreparedStatement pstmt = DB.getConnection().prepareStatement(query);
		pstmt.setString(1,Sol_ID);
		pstmt.setString(2,mailID);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			Timestamp joinTime = rs.getTimestamp("Join_time");
			Timestamp submitTime = rs.getTimestamp("Submit_time");
			long differenceMillis = submitTime.getTime() - joinTime.getTime();
			return differenceMillis;
		}
		throw new Exception("Invalid Details");
    }

}
