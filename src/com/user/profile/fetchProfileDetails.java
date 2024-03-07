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
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.question.Question;
import com.question.QuestionDao;
import com.solution.Solution;
import com.solution.SolutionDao;
import com.user.User;
import com.user.UserDAO;

import utils.CommonLogger;
import utils.JSON;

/**
 * Servlet implementation class FetchStatistics
 */

@WebServlet("/v1/profile")
public class fetchProfileDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fetchProfileDetails() {
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
		
		// Check if it is his own profile or someothers profile
				/** If own page,
				 * ownPage: true, show chart details, Clan controlls
				 * 
				 * If someOnes profile: 
				 * ownPage: false, just show all details. except answers.
				 */
				
			Logger logger = new CommonLogger(FetchStatistics.class).getLogger();
				// check given mailID and current user's mailID is equal.
                boolean flag = true;
				String ownMail;
				String userMail = request.getParameter("userMail");
				ownMail = request.getParameter("mailID");
			;
				flag = userMail.equals(ownMail);
				try {
					User user = new User();
					user.setMailID(userMail);
					//get stats
					JSONObject statistics = UserDAO.getObj().getStats(userMail);
					//get profile details
					User data = UserDAO.getObj().getUserProfileData(user);
					System.out.println("user object created");
					JSONObject userJSON = UserDAO.getObj().createJSON(data);
					System.out.println("all profile details fetched");
					//get attempted solutions
					List<Solution> completedSolutions = UserDAO.getObj().fetchCompletedSolutionsOfAUser(user, "ATTEMPTED");
					JSONArray solutions = SolutionDao.getObj().createJSON(completedSolutions);
					//get authored questions
					System.out.println("completedQuestions fetched");
					List<Question> authoredQuestions = UserDAO.getObj().getAllAuthoredQuestions(user);
					System.out.println(authoredQuestions);
					System.out.println("authored questions fetched");
					JSONArray questions = QuestionDao.getObj().createJSON(authoredQuestions);
					System.out.println("got details successfully");
					// get completed questions
					List<Solution> completedSolution = UserDAO.getObj().fetchCompletedSolutionsOfAUser(user, "COMPLETED");
					JSONArray completedsolutions = SolutionDao.getObj().createJSON(completedSolution);
					// this object has successcode and object of stats values
					JSONObject ProfileDataWithStats = new JSONObject();
					ProfileDataWithStats.put("statuscode", 200);
					ProfileDataWithStats.put("isOwnPage", flag);
					ProfileDataWithStats.put("profilePic", "");
					ProfileDataWithStats.put("userData", userJSON);
					ProfileDataWithStats.put("statistics", statistics);
					ProfileDataWithStats.put("attemptedSolutions", solutions);
					ProfileDataWithStats.put("authoredQuestions", questions);
					ProfileDataWithStats.put("completedSolutions", completedsolutions);
					
					logger.info("User profile statistics fetched successfully for "+userMail);
					System.out.println("User profile statistics fetched successfully for "+userMail);
					System.out.print(ProfileDataWithStats.toString());
					response.getWriter().write(ProfileDataWithStats.toString());
				} catch (Exception e) {
					logger.error("error fetching stats"+e);
					JSONObject errorResp = JSON.Create(400, e.getMessage());
					response.getWriter().write(errorResp.toString());
				}
	}

}
