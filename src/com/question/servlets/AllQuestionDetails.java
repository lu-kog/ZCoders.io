package com.question.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.question.Language;
import com.question.Question;
import com.question.QuestionDao;
import com.question.Tag;
import com.solution.SolutionDao;

import utils.CommonLogger;
import utils.JSON;

/**
 * Servlet implementation class AllQuestionDetails
 */

@WebServlet("/v1/AllQuestionDetails")
public class AllQuestionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = new CommonLogger(AllQuestionDetails.class).getLogger();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllQuestionDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    QuestionDao questionDao=new QuestionDao();
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String mailId = request.getParameter("mailID");
	  try {
		    List<Question> questionDetailsList = questionDao.fetchAllQuestionDetails();
			System.out.println(questionDetailsList.size());
		    JSONArray questionsArray = new JSONArray();

		    for (Question question : questionDetailsList) {
		        JSONObject questionObject = new JSONObject();

		        questionObject.put("QID", question.getQuestionID());
		        questionObject.put("QName", question.getQuestionName());

		        JSONObject levelObject = new JSONObject();
		        levelObject.put("levelID", question.getLevel().getLevelId());
		        levelObject.put("levelName", question.getLevel().getLevelName());
		        levelObject.put("score", question.getLevel().getScore());

		        questionObject.put("level", levelObject);

		        JSONArray languagesArray = new JSONArray();
		        for (Language language : question.getLanguage()) {
		            languagesArray.put(language.getLanguageName());
		        }
		        questionObject.put("languages", languagesArray);

		        JSONObject detailsObject = new JSONObject();
		        detailsObject.put("description", question.getDescription());

		        JSONArray tagsArray = new JSONArray();
		        for (Tag tag : question.getTags()) {
		            tagsArray.put(tag.getTag());
		        }
		        detailsObject.put("tags", tagsArray);

		        questionObject.put("Details", detailsObject);

		        JSONObject userObject = new JSONObject();
		        userObject.put("mailID", question.getUser().getMailID());
		        userObject.put("userName", question.getUser().getUserName());

		        questionObject.put("user", userObject);

		        questionObject.put("attempt", question.getnoOfAttempt());
		        questionObject.put("submit", question.getnoOfTimesSubmitted());

		        questionsArray.put(questionObject);
		    }

		    JSONObject responseJson = new JSONObject();
		    responseJson.put("StatusCode", "200");
		    responseJson.put("questions", questionsArray);
		    responseJson.put("streakDates", SolutionDao.getObj().getSolutionsDates(mailId));
			System.out.println(responseJson.toString());
		    System.out.println(responseJson.toString());

		    // Write JSON string to response
		    logger.info("All Question details fetched successfully");
		    response.getWriter().write(responseJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		    logger.error("Something went wrong while fetching question details: " + e.getMessage());
		    JSONObject errorResponse = JSON.Create(400, e.getMessage());
			 response.getWriter().write(errorResponse.toString());
		}

}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
