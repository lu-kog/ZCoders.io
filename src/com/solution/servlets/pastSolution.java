package com.solution.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.solution.Solution;
import com.solution.SolutionDao;

import utils.CommonLogger;
import utils.JSON;

/**
 * Servlet implementation class pastSolution
 */
 @WebServlet("/pastSolution")
public class pastSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = new CommonLogger(showSolution.class).getLogger();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pastSolution() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    SolutionDao solutionDao=new SolutionDao();
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		//response.getWriter().append("Served at: ").append(request.getContextPath());
 		
 		// get the user solution for a particular question
 		 String questionId = request.getParameter("QID");
 		 String userId = request.getParameter("UID");
 		 String status="COMPLETED";
 		 System.out.println("pastSolution servlet");
 		 boolean isComplete=false;
 		 try {
 			 isComplete=solutionDao.isQuestionCompleted(questionId,userId);
 			 JSONObject json=null;
 			 if(isComplete) {
 				 json= solutionDetails(questionId,userId,status);
 			 }else {
         		 json = new JSONObject();
 				 json.put("statusCode", "200");
 				 json.put("questionID", questionId);
 				 json.put("hasCompleted", "FALSE");
 				 json.put("solutions", JSONObject.NULL);
 			    
 			 }
 			 
 			    
 			    logger.info("Solutions fecth successfully");
 			    response.getWriter().write(json.toString());
 			    
 		 }
 		 catch(Exception e) {
 			 logger.error("something went wrong for the problem");
 			 JSONObject errorResponse = JSON.Create(400, e.getMessage());

 			 response.getWriter().write(errorResponse.toString());
 		 }
 	}


 	private JSONObject solutionDetails(String questionId,String userId, String status) throws Exception {
 	    List<Solution> solution = solutionDao.fetchSolution(questionId,userId, status);
 	    System.out.println(solution);
 	    JSONObject json = new JSONObject();
 	    json.put("statusCode", "200");
 	    json.put("questionID", questionId);
 	    json.put("hasCompleted", "TRUE");

 	    JSONObject solutionsObject = new JSONObject();
 	    for (int i = 0; i < solution.size(); i++) {
 	        Solution sol = solution.get(i);
 	        JSONObject solutionObject = new JSONObject();
 	        solutionObject.put("solID", sol.getSolID());
 	        solutionObject.put("solution", sol.getCode());
 	        solutionObject.put("userID", userId);
 	        solutionsObject.put(String.valueOf(i), solutionObject);
 	    }
 	    json.put("solutions", solutionsObject);
 	    
 	    return json;
 	    //return json.toString();
 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
