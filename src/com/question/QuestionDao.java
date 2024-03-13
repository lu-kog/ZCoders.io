package com.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.python.modules.itertools.repeat;

import com.user.User;

// import compiler.pyassem.py;
import utils.CommonLogger;
import utils.DB;
import utils.Query;
import utils.sqlFile;


public class QuestionDao {
	
	static Logger logger = new CommonLogger(QuestionDao.class).getLogger();
	Connection connection = DB.getConnection();

	private static QuestionDao quesDAO = new QuestionDao();

	public static QuestionDao getObj() {
		return quesDAO;
	}
    
    
    
    public boolean isValidQuestionId(String questionId) throws Exception {      
            // Prepare a query to check if the question ID exists in the Questions table

            try{
            	PreparedStatement statement = connection.prepareStatement(Query.isValidQuestionID);
                statement.setString(1, questionId);
                ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        // If the count is greater than 0, the question ID exists in the table
//                    	int result = resultSet.getInt("counts");
                        logger.info(questionId+" is a valid question.");
                    	return true;
                        
                    }
                    else {
                        logger.error(questionId+" is invalid question ID!");
                        throw new Exception("Question not found!");
                    }
            }
            catch (Exception e) {
                logger.error("Error on fetching question details : qid:"+questionId+" error:"+e);
                throw new Exception("Question not found!");
            }
    }
    
    // Method to fetch all question details from the database
    public List<Question> fetchAllQuestionDetails() throws Exception {
    	List<Question> QuestionDetails = new ArrayList<>();
        String query = Query.fetchAllQuestionDetails;
        try {
        	PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                	Question questionDetails = new Question();
                	questionDetails.setQuestionID(resultSet.getString("Q_ID"));
                    questionDetails.setQuestionName(resultSet.getString("Q_name"));
                    questionDetails.setDescription(resultSet.getString("description"));
                    Level level = new Level(resultSet.getInt("levelID"), resultSet.getString("level_name"), resultSet.getInt("score"));
                    questionDetails.setLevel(level);
                    User user=new User(
                            resultSet.getString("userName"),
                            resultSet.getString("Author"),
                            resultSet.getInt("score"),
                            resultSet.getInt("streak"));
                    questionDetails.setUser(user);
                    
                 // Fetch list of languages
                    List<Language> languages = fetchLanguages(questionDetails.getQuestionID());
                    questionDetails.setLanguage(languages);
                    
                 // Fetch list of languages
                    List<Tag> tags = fetchTags(questionDetails.getQuestionID());
                    questionDetails.setTags(tags);
                    
                 // Fetch count of attempts
                    int attemptCount = fetchAttemptCount(questionDetails.getQuestionID(),"ATTEMPTED");
                    questionDetails.setnoOfAttempt(attemptCount);
                    
                 // Fetch count of completed solutions
                    int completedCount = fetchCompletedCount(questionDetails.getQuestionID(), "COMPLETED");
                    questionDetails.setnoOfTimesSubmitted(completedCount);
                    
                    logger.info("Question details fecth successfully!!!!!");
                    QuestionDetails.add(questionDetails);
                    System.out.println(questionDetails.toJSON().toString());
                }
            }catch (Exception e) {
        	//Logger
        	 System.out.println(e.getMessage());
        	 logger.error("can't fetch Question details for this question");
        	throw new Exception("can't fecth Question details for this question");
		}
        return QuestionDetails;
    }

    // Method to fetch question details from the database
    public Question fetchQuestionDetails(String questionId) throws Exception {
    	Question questionDetails = new Question();
        String query = Query.fetchQuestionDetails;
        System.out.println("Enter into fetchQuestionDetails");
        try {
        	PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            
                if (resultSet.next()) {
                    logger.info("getting informations");
                	questionDetails.setQuestionID(resultSet.getString("Q_ID"));
                    questionDetails.setQuestionName(resultSet.getString("Q_name"));
                    questionDetails.setDescription(resultSet.getString("description"));
                    questionDetails.setExample(resultSet.getString("example"));
                    questionDetails.setfunctionName(resultSet.getString("functionString"));
                    questionDetails.setTestCases(new JSONObject(resultSet.getString("testcaseJSON")));
                    Level level = new Level(resultSet.getInt("levelID"), resultSet.getString("level_name"), resultSet.getInt("score"));
                    questionDetails.setLevel(level);
                    User user=new User(resultSet.getString("userName"),
                            resultSet.getString("Author"),
                            resultSet.getInt("score"),
                            resultSet.getInt("streak"));
                    questionDetails.setUser(user);
                    
                 // Fetch list of languages
                    List<Language> languages = fetchLanguages(questionId);
                    questionDetails.setLanguage(languages);
                    
                 // Fetch list of languages
                    List<Tag> tags = fetchTags(questionId);
                    questionDetails.setTags(tags);
                    
                 // Fetch count of attempts
                    int attemptCount = fetchAttemptCount(questionId,"ATTEMPTED");
                    questionDetails.setnoOfAttempt(attemptCount);
                    
                 // Fetch count of completed solutions
                    int completedCount = fetchCompletedCount(questionId, "COMPLETED");
                    questionDetails.setnoOfTimesSubmitted(completedCount);
                    
                    logger.info("Question details return successfully!!!!!");
                    return questionDetails;
                }
                else {
                	//Logger 
                	logger.error("No values");
                	throw new Exception("No values");
                }
         }catch (Exception e) {
            e.printStackTrace();
        	 System.out.println(e.getMessage());
        	 logger.error("can't fetch Question details for this question" + e);
        	throw new Exception("can't fecth Question details for this question");
		}
    }

// Method to fetch Languages for a question
    private List<Language> fetchLanguages(String questionId) throws Exception {
        List<Language> languages = new ArrayList<>();
        String query = Query.fetchLanguages;
        try{
        	PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, questionId);
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String languageName = resultSet.getString("lang_name");
                    Language language = new Language(languageName);
                    languages.add(language);
                }
              logger.info("List of languages fecth successfully!!!!");
             return languages;
        } catch (Exception e) {
        	logger.error("Failed to fetch languages for this question");
            throw new SQLException("Failed to fetch languages for this question");
        }
    }
    

    // Method to fetch Languages for a question
    private List<Tag> fetchTags(String questionId) throws Exception {
        List<Tag> tags = new ArrayList<>();
        String query = Query.fetchTags;
        try{
        	PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, questionId);
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String tagName = resultSet.getString("Tag_name");
                    Tag tag = new Tag(tagName);
                    tags.add(tag);
                }
              logger.info("List of Tags fecth successfully!!!!");
             return tags;
        } catch (Exception e) {
        	logger.error("Failed to fetch tags for this question");
            throw new SQLException("Failed to fetch tags for this question");
        }
    }

 // Method to fetch AttemptCount for a question
    private int fetchAttemptCount(String questionId, String attemptStatus) throws Exception {
        int attemptCount = 0;
        // Execute SQL query to fetch count of attempts for the given questionId and status
        String query = Query.fetchAttemptCount;
        try{
        	PreparedStatement statement = connection.prepareStatement(query);
        	statement.setString(1, questionId);
            statement.setString(2, attemptStatus);
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                	attemptCount = resultSet.getInt(1);
                }
                logger.info("fecth count the Attempted for a question successfully!!!!");
                return attemptCount;
        }
        catch (Exception e) {
        	//Logger
        	logger.error("can't find AttemptCount on this question");
        	throw new Exception("can't find AttemptCount on this question");
			// TODO Auto-generated catch block
		}
    }
    
    
 // Method to fetch CompletedCount for a question
    private int fetchCompletedCount(String questionId, String attemptStatus) throws Exception {
        int completedCount = 0;
        // Execute SQL query to fetch count of completed solutions for the given questionId and status
        String query = Query.fetchCompletedCount;
        try{
        	PreparedStatement statement = connection.prepareStatement(query);
        	statement.setString(1, questionId);
            statement.setString(2, attemptStatus);
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                	completedCount = resultSet.getInt(1);
                }
                logger.info("fecth count the completed for a question successfully!!!!");
            return completedCount;
        }
        catch (Exception e) {
        	//Logger
        	logger.error("can't find completedCount on this question");
        	throw new Exception("can't find completedCount on this question");
			// TODO Auto-generated catch block
		}
    }

    // public Level getLevelByName(String Lname) throws Exception{
    //     PreparedStatement preparedStatement = connection.prepareStatement(Query.getLevelID);
    //     preparedStatement.setInt(1, Lname);
    //     Level level = new Level();
    //     level.set
    //     ResultSet res = preparedStatement.executeQuery();
    //     if(res.next()){
    //         return res.getInt("levelID");
    //     }
    //     else{
    //         throw new exception("invalid level name");
    //     }
    // }
    
    // public boolean addQuestion(String questionId, String questionName, String description, String example, String level, String mailId, String status) throws Exception {
    //     PreparedStatement preparedStatement = connection.prepareStatement(Query.insertQuestion);
    //     Question question = new Question();
    //     question.setQuestionName(questionName);
    //     question.setDescription(description);
    //     question.setExample(example);
    //     question.setLevel(getLevelByName(level));
    //     question.setAuthor();
    //     question.setStatus(status);
    //     //question.se
    //   }
    
    //method to update the status of a question
    public boolean approveQuestion(String questionId) throws Exception {
        String query = Query.approveQuestion;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, questionId);

            // Execute the update query
            int rowsUpdated = preparedStatement.executeUpdate();
            
            logger.info("Question status is updated successfuly");
            // Return true if the update was successful
            return rowsUpdated > 0;
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("can't update the question status");
        	throw new Exception("can't update the question status");
        }
    }

    public String getQuestion(String QID) throws Exception{
		String ques = "";
            PreparedStatement pstmt = connection.prepareStatement(Query.getQuestion);
            pstmt.setString(1, QID);
            ResultSet res = pstmt.executeQuery();
            if(res.next()) {
            	ques = res.getString(1);
            }
	
		return ques;
	}

    public ArrayList<String> getLangForQues(String questionID) throws Exception {
		// select lang from lang relation where questionid like ?;
		ArrayList<String> languages = new ArrayList<String>();
		try {
			PreparedStatement pstmt = connection.prepareStatement(Query.getLangListFromQuestionID);
			pstmt.setString(1, questionID);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				languages.add(res.getString("lang_name"));
			}
			return languages;
		} catch (Exception e) {
			throw new Exception("Oops! Something went wrong!");
		}
		
	}
	
	public JSONArray createJSON(List<Question> questions) throws Exception {
		JSONArray authoredQuestionList = new JSONArray();
		for(int i=0; i<questions.size(); i++) {
			JSONObject authoredQuestionJSON = new JSONObject();
			authoredQuestionJSON.put("question", questions.get(i).getQuestionName());
			authoredQuestionJSON.put("language", getLangForQues(questions.get(i).getQuestionID()));
			authoredQuestionJSON.put("level", questions.get(i).getLevel());
			authoredQuestionJSON.put("totalusers", fetchCompletedCount(questions.get(i).getQuestionID(),"COMPLETED"));
			
			authoredQuestionList.put(authoredQuestionJSON);
		}
		
		return authoredQuestionList;
	}
    public JSONObject questionJSON(Question questionDetails) throws JSONException{
        JSONObject json = new JSONObject();

				json.put("StatusCode", "200");
				json.put("QID", questionDetails.getQuestionID());
				json.put("QName", questionDetails.getQuestionName());

				JSONObject levelObject = new JSONObject();
				levelObject.put("levelID", questionDetails.getLevel().getLevelId());
				levelObject.put("levelName", questionDetails.getLevel().getLevelName());
				levelObject.put("score", questionDetails.getLevel().getScore());

				json.put("level", levelObject);

				JSONArray languagesArray = new JSONArray();
				for (int i = 0; i < questionDetails.getLanguage().size(); i++) {
				    languagesArray.put(questionDetails.getLanguage().get(i).getLanguageName());
				}
				json.put("languages", languagesArray);

				JSONObject detailsObject = new JSONObject();
				detailsObject.put("description", questionDetails.getDescription());
				detailsObject.put("example", questionDetails.getExample());
				
				JSONArray tagsArray = new JSONArray();
				for (int i = 0; i < questionDetails.getTags().size(); i++) {
				    tagsArray.put(questionDetails.getTags().get(i).getTag());
				}
				detailsObject.put("tags", tagsArray);

				json.put("Details", detailsObject);

				JSONObject userObject = new JSONObject();
				userObject.put("mailID", questionDetails.getUser().getMailID());
				userObject.put("userName", questionDetails.getUser().getUserName());

				json.put("user", userObject);
				json.put("status", 200);
				json.put("attempt", questionDetails.getnoOfAttempt());
				json.put("submit", questionDetails.getnoOfTimesSubmitted());
                return json;
    }

     public JSONObject getQuestions(String question_name) throws Exception {
		 
         
		 JSONArray quesArray = new JSONArray();
		 
		 String question_nameArr[] = question_name.split(" ");
		 
		 for(int i = 0; i < question_nameArr.length; i++) {
				 try {
                        String wordToSearch = question_nameArr[i].toLowerCase();

                        if(wordToSearch.length() < 2){
                            continue;
                        }
                        
                            PreparedStatement pstmt = connection.prepareStatement("select Q_ID from Questions where Q_name like ?"); 
                            pstmt.setString(1, "%" + wordToSearch + "%");
                            logger.info(question_name);
						    ResultSet rs = pstmt.executeQuery();
						
						    while(rs.next()) {
                                //JSONArray matchedQuestions = new JSONArray();
							    JSONObject quesObject = new JSONObject();
							    // quesObject.put("Q_ID", rs.getString("Q_ID"));
                                // quesObject.put("levelID", rs.getString("levelID"));
							    // quesObject.put("Author", rs.getString("Author"));
                                quesArray.put(questionJSON(fetchQuestionDetails(rs.getString("Q_ID"))));
                                logger.info(quesArray);
						    }    			
					 }

                        

					 catch(Exception e) {
						 logger.error("Error on searching questions: keywords:"+question_name+" error:"+e);
						 throw new Exception("Sorry! Can't fetch..");
					 }
			 
			 
		 }
		 
         logger.info("Kata found:"+quesArray.length());
        JSONObject ques = new JSONObject();
        ques.put("questions", quesArray);
         return ques;
		 
		
	 }

     String getQuestionFunctionName(String QID) throws SQLException{
        PreparedStatement pstmt = connection.prepareStatement(Query.getFuncname); 
        pstmt.setString(1, QID);
        ResultSet res = pstmt.executeQuery();
        String functionName = "";
        if(res.next()){
            functionName = res.getString("functionString");
        }
        return functionName;
     }

     JSONObject getTestCase(String QID) throws SQLException, JSONException{
        PreparedStatement pstmt = connection.prepareStatement(Query.getTestcase); 
        pstmt.setString(1, QID);
        ResultSet res = pstmt.executeQuery();
        String testcaseString = "";
        JSONObject testcase = null;
        if(res.next()){
            testcaseString = res.getString("testcaseJSON");
            testcase = new JSONObject(testcaseString);
        }
        return testcase;
     }

    public Level getLevel(int levelId) throws Exception{
        String query = Query.getLevel;
        PreparedStatement pstmt = DB.getConnection().prepareStatement(query);
        pstmt.setInt(1,levelId);                        

        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return new Level(rs.getInt("levelID"),rs.getString("level_name"),rs.getInt("score"));
        }
        else{
            throw new Exception("Invalid Level ID");
        }
    }




	public boolean addQuestion(Question question, String javaSyntax, String pythonSyntax) throws SQLException {

        String query = Query.insertQuestion;
        PreparedStatement pstmt = DB.getConnection().prepareStatement(query);
        pstmt.setString(1,question.getQuestionID());
        pstmt.setString(2,question.getQuestionName());
        pstmt.setString(3,question.getDescription());
        pstmt.setString(4,question.getExample());
        pstmt.setString(5,question.getfunctionName());
        pstmt.setString(6,question.getTestCases().toString());
        pstmt.setInt(7,question.getLevel().getLevelId());
        pstmt.setString(8,question.getUser().getMailID());
        pstmt.setString(9,"NOTAPPROVED");
        System.out.println(pstmt.toString());
        if(pstmt.executeUpdate()==1){
            sqlFile.append(pstmt.toString());
            List<Tag> tags = question.getTags();
            for(Tag tag:tags){
                query = Query.getTagId;
                pstmt = DB.getConnection().prepareStatement(query);
                pstmt.setString(1,tag.getTag());
                System.out.println(pstmt.toString());
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    sqlFile.append(pstmt.toString());
                    int tagID = rs.getInt("Tag_ID");
                    query = Query.insertTagRelation;
                    pstmt = DB.getConnection().prepareStatement(query);
                    pstmt.setString(1,question.getQuestionID());
                    pstmt.setInt(2,tagID);
                    System.out.println(pstmt.toString());
                    pstmt.executeUpdate();
                    sqlFile.append(pstmt.toString());
                }
            }
            List<Language> languages = question.getLanguage();
            for(Language language:languages){
                query = Query.getLanguageID;
                System.out.println(language.getLanguageName());
                pstmt = DB.getConnection().prepareStatement(query);
                pstmt.setString(1,language.getLanguageName());
                System.out.println(pstmt.toString());
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    sqlFile.append(pstmt.toString());
                    int langID = rs.getInt("l_ID");
                    query = Query.insertLanguageRelation;
                    pstmt = DB.getConnection().prepareStatement(query);
                    pstmt.setString(2,question.getQuestionID());
                    pstmt.setInt(1,langID);
                    System.out.println(language.getLanguageName() + "     "+langID);
                    if(langID == 1){
                    pstmt.setString(3,javaSyntax);
                    }
                    else if(langID == 2){
                    pstmt.setString(3,pythonSyntax);
                    }
                    pstmt.setString(4,"");
                    System.out.println(pstmt.toString()+"kdfmboik");
                    pstmt.executeUpdate();
                    sqlFile.append(pstmt.toString());
                }
            }
        }
        else{
            return false;
        }
        return true;
	}
}
