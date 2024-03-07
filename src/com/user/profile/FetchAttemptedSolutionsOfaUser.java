package com.user.profile;

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

import com.solution.Solution;
import com.solution.SolutionDao;
import com.user.User;
import com.user.UserDAO;

import utils.CommonLogger;
import utils.JSON;

/**
 * Servlet implementation class FetchAttemptedSolutionsOfaUser
 */
// @WebServlet("/FetchAttemptedSolutionsOfaUser")
@WebServlet("/AttemptedSolutions")
public class FetchAttemptedSolutionsOfaUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchAttemptedSolutionsOfaUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * get mailID from param
		 * fetch all his unfinished solutions
		 * return in JSON
		 */
		Logger logger = new CommonLogger(FetchAttemptedSolutionsOfaUser.class).getLogger();
		String mailID = request.getParameter("mailID");
		User user = new User();
		user.setMailID(mailID);
		try {
			List<Solution> completedSolutions = UserDAO.getObj().fetchCompletedSolutionsOfAUser(user, "ATTEMPTED");
			logger.info("got : " + completedSolutions + " successful");
			System.out.println(" .......    " +completedSolutions);
			JSONArray solutions = SolutionDao.getObj().createJSON(completedSolutions);
			logger.info("got : " + solutions + " successful");
			// JSONArray array=new JSONArray();
			// // JSONObject solutionsObject = new JSONObject();
			// logger.info("created array for solutions");
		    // for (int i = 0; i < completedSolutions.size(); i++) {
			// 	logger.info("entered loop");
		    //     Solution sol = completedSolutions.get(i);
		    //     JSONObject solutionObject = new JSONObject();
		    //     solutionObject.put("SolID", sol.getSolID());
		    //     solutionObject.put("Sol", sol.getSol());
		    //     solutionObject.put("Date", sol.getSolutionDate());
		    //     solutionObject.put("UID", sol.getUser().getMailID());
		    //     solutionObject.put("userName", sol.getUser().getUserName());
		    //     array.put(solutionObject);
		    // }
			JSONObject success = new JSONObject();
			success.put("statuscode", 200);
			success.put("data", solutions);
			logger.info("fetched solutions of user:"+mailID+" successfully.");
			System.out.print(success);
			response.getWriter().write(success.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject errorResp = JSON.Create(400, e.getMessage());
			logger.error("error fetching completed solutions of user:"+mailID + e.getMessage());
			response.getWriter().write(errorResp.toString());
		}
	}

}
