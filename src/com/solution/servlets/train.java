package com.solution.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.solution.SolutionDao;

import utils.CommonLogger;
import utils.Generator;
import utils.JSON;

/**
 * Servlet implementation class train
 */
 @WebServlet("/v1/train")
public class train extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = new CommonLogger(train.class).getLogger();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public train() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		///function work
		//get the questionId,questionidName,levelId,levelName,function,
		//list of Language,description,list of Tags,
		//how many of us attendant the question
		//how many of us completed the question
		//UserName,UserMailId
		
		 String mailId = request.getParameter("mailID");
		 String questionId = request.getParameter("QID");
		 String languageName=request.getParameter("languageName");
		 String solvedType=request.getParameter("solvedType");
		 String functionDetails="";
		 String status="ATTEMPTED";
		 try {
			 //get attempted solution from solutionDao
			    boolean isAnyAttemptedSolution= solutionDao.isAnyAttemptedSolution(questionId, mailId, languageName);
			    
			    String solutionID ="";
				//check the solution is valid or none
			    if(isAnyAttemptedSolution) 
				{
					
					ArrayList<String> solution = solutionDao.getAttemptedSolution(questionId,mailId,languageName);
					logger.info(questionId+" is already attempted solution: "+solution.get(0)+" language:"+languageName+" user:"+mailId);
			    	functionDetails = solution.get(0);
					solutionID = solution.get(1);
			    }
			    else {
					ArrayList<String> languages = new ArrayList<>();
					languages.add("Java");
					languages.add("Python");

					solutionID = Generator.ValidNumerical(8, "Solutions", "Sol_ID");
			    	int langID = languages.indexOf(languageName)+1;

					logger.info("inserting new Solution:"+solutionID+" mail:"+mailId+" question:"+questionId+" langID"+langID);
			    	solutionDao.insertSolution(solutionID, mailId, questionId, status, solvedType, langID);
				functionDetails = solutionDao.fecthFuncName(questionId, languageName);
			    System.out.println("function"+functionDetails);
			    }
			    

			    //get question details from questionDao
				Question questionDetails = questionDao.fetchQuestionDetails(questionId);
				
				//create JSON object for response
				JSONObject json = new JSONObject();
				
				//put all details into the JSON object
				json.put("statusCode", "200");
				json.put("questionID", questionDetails.getQuestionID());
				json.put("solID", solutionID);
				json.put("questionName", questionDetails.getQuestionName());

				JSONObject levelObject = new JSONObject();
				levelObject.put("levelID", questionDetails.getLevel().getLevelId());
				levelObject.put("levelName", questionDetails.getLevel().getLevelName());
				levelObject.put("score", questionDetails.getLevel().getScore());

				json.put("level", levelObject);
				
				json.put("functionDetails", functionDetails);
				
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

				json.put("details", detailsObject);

				JSONObject userObject = new JSONObject();
				userObject.put("mailID", questionDetails.getUser().getMailID());
				userObject.put("userName", questionDetails.getUser().getUserName());

				json.put("author", userObject);

				json.put("attempt", questionDetails.getnoOfAttempt());
				json.put("submit", questionDetails.getnoOfTimesSubmitted());

				json.put("pastSolution",solutionDao.getObj().createJSON(solutionDao.getObj().fetchSolution(questionDetails.getQuestionID(),mailId,"COMPLETED")));
				
				    // Write JSON string to response
				    logger.info(questionId+" Question details fecth successfully: "+json);
					System.out.println("func "+isAnyAttemptedSolution +"      "+functionDetails);
				    response.getWriter().write(json.toString());
			}
			 catch (Exception e) {
				// TODO Auto-generated catch block
				 logger.error("Error of fetching function details for question:"+questionId+" error:"+e);
				 JSONObject errorResponse = JSON.Create(400, e.getMessage());
				e.printStackTrace();
				 response.getWriter().write(errorResponse.toString());
				 
         }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// protected void doPost(HttpServletRequest request, HfetchQuestionDetailsttpServletResponse response) throws ServletException, IOException {
	// 	// TODO Auto-generated method stub
	// 	doGet(request, response);
	// }

}
