package com.solution.servlets;

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

import com.question.QuestionDao;
import com.solution.Solution;
import com.solution.SolutionDao;
import utils.CommonLogger;
import utils.JSON;
import com.question.Question;
import javax.servlet.http.Cookie;

import com.user.UserDAO;
/**
 * Servlet implementation class sumbitServlet
 */
@WebServlet("/v1/sumbitServlet")
public class sumbitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = new CommonLogger(sumbitServlet.class).getLogger();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sumbitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    QuestionDao questionDao=new QuestionDao();
    SolutionDao solutionDao=new SolutionDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String solId = request.getParameter("solID");
		String questionId = request.getParameter("QID");
		String language = request.getParameter("language");
		String mailID = request.getParameter("userId");
		 System.out.println("questionID for submission: "+questionId);
		logger.info("questionID for submission: "+questionId);
		String status="COMPLETED";
		try {
			SolutionDao.getObj().updateStatus(solId, "COMPLETED");
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		try {

			// Cookie cookie[] = request.getCookies();
			// System.out.println(cookie);
			// String mailID = "";
			// // if(cookie != null){
			// 	for(Cookie c : cookie) {
			// 		if(c.getName().equals("username")) {
			// 		mailID = c.getValue();
			// 	}
			// }
			// }
			//get question details from questionDao
			Question questionDetails = questionDao.fetchQuestionDetails(questionId);
			List<Solution> solution = solutionDao.fetchAllSolution(questionId, status,language);
			//create JSON object for response
			System.out.println("checking....");			
			JSONObject json = new JSONObject();
			
			//put all details into the JSON object
			json.put("statusCode", 200);
			json.put("questionID", questionDetails.getQuestionID());
			json.put("questionName", questionDetails.getQuestionName());
			json.put("Language",language);
			
			JSONObject levelObject = new JSONObject();
			levelObject.put("levelID", questionDetails.getLevel().getLevelId());
			int levelID = questionDetails.getLevel().getLevelId();
			levelObject.put("levelName", questionDetails.getLevel().getLevelName());
			levelObject.put("score", questionDetails.getLevel().getScore());

			json.put("level", levelObject);
			
			//json.put("functionDetails", functionDetails);
			
			JSONArray languagesArray = new JSONArray();
			for (int i = 0; i < questionDetails.getLanguage().size(); i++) {
			    languagesArray.put(questionDetails.getLanguage().get(i).getLanguageName());
			}
			json.put("languages", languagesArray);

			JSONObject detailsObject = new JSONObject();
			detailsObject.put("description", questionDetails.getDescription());

			JSONArray tagsArray = new JSONArray();
			for (int i = 0; i < questionDetails.getTags().size(); i++) {
			    tagsArray.put(questionDetails.getTags().get(i).getTag());
			}
			detailsObject.put("tags", tagsArray);
			System.out.println("ksbyhusrzdujcszhnvikjiksjdkizk");
			json.put("details", detailsObject);

			JSONObject userObject = new JSONObject();
			userObject.put("mailID", mailID);
			userObject.put("userName", questionDetails.getUser().getUserName());

			json.put("user", userObject);

			json.put("attempt", questionDetails.getnoOfAttempt());
			json.put("submit", questionDetails.getnoOfTimesSubmitted());
			//List<Solutions> solution = solutionDao.fetchAllSolutions(questionId, status);
			JSONObject solutionJSON = new JSONObject();
		    System.out.println(solution);
		    solutionJSON.put("StatusCode", "200");
		    solutionJSON.put("QID", questionId);
		    solutionJSON.put("hasCompleted", "TRUE");

		    JSONArray solutionsObject = new JSONArray();
		    for (int i = 0; i < solution.size(); i++) {
		        Solution sol = solution.get(i);
		        JSONObject solutionObject = new JSONObject();
		        solutionObject.put("SolID", sol.getSolID());
		        solutionObject.put("Sol", sol.getCode());
		        solutionObject.put("Date", sol.getDate());
		        solutionObject.put("UID", sol.getUser().getMailID());
		        solutionObject.put("userName", sol.getUser().getUserName());

		        solutionsObject.put(solutionObject);
		    }
				solutionJSON.put("solutions", solutionsObject);
		    
		   		json.put("AllSolution", solutionJSON);
			    // Write JSON string to response
			    logger.info("Question details && solution fetched successfully");

				//increse streak
				UserDAO.getObj().increaseStreakCount(mailID);

				//increase score
				UserDAO.getObj().updateScore(levelID, mailID);
			    response.getWriter().write(json.toString());
		}
		 catch (Exception e) {
			e.printStackTrace();
			 logger.error("Error on submitting solution for question: "+questionId+" error:"+e);
			 JSONObject errorResponse = JSON.Create(400, e.getMessage());
			 response.getWriter().write(errorResponse.toString());		 
     }	
		
		
	}

	/**
	 * @throws Exception
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 	// TODO Auto-generated method stub
	// 	doGet(request, response);
	// }
	// public static void main(String[] args){
	// 	try{
	// 	//  QuestionDao questionDAO=new QuestionDao();
    //     //  SolutionDao solutionDao=new SolutionDao();
	// 	 boolean st=UserDAO.getObj().increaseStreakCount("charu07@gmail.com");

	// 	System.out.println(st);
	// 	}
	// 	catch (Exception e) {
	// 		System.out.println(e.getMessage());
	// 		// TODO: handle exception
	// 	}
	// }

}

