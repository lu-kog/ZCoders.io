package com.question.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.question.Question;
import com.question.QuestionDao;

import utils.CommonLogger;
import utils.JSON;

/**
 * Servlet implementation class QuestionDetailsServlet
 */


@WebServlet("/v1/questionDetails")
public class QuestionDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = new CommonLogger(QuestionDetailsServlet.class).getLogger();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    QuestionDao questionDao=new QuestionDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		//fetch all details about the question
		
		
		    String questionId = request.getParameter("QID");
			System.out.println(questionId);
	        //String userId = request.getParameter("UID");
	        //String solvedType = request.getParameter("solvedType");
	        
	        
			try {
				Question questionDetails = questionDao.fetchQuestionDetails(questionId);
				System.out.println("Question details " );
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

				json.put("author", userObject);
				json.put("status", 200);
				json.put("attempt", questionDetails.getnoOfAttempt());
				json.put("submit", questionDetails.getnoOfTimesSubmitted());

				System.out.println(json.toString());

				    // Write JSON string to response
				    logger.info("Question details fecth successfully");
				    response.getWriter().write(json.toString());
			}
			catch (Exception e) {
				 logger.error("something went wrong for getting details of question"+e);
				 JSONObject errorResponse = JSON.Create(400, e.getMessage());
				 response.getWriter().write(errorResponse.toString());
            }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
