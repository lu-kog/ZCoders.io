package com.tournament.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import utils.CommonLogger;
import utils.JSON;
import com.tournament.TournamentDAO;


public class SubmitTournament extends HttpServlet {
	
	static Logger logger = new CommonLogger(SubmitTournament.class).getLogger();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String solution = request.getParameter("Sol_ID");
		String mailID = request.getParameter("mailID");
		String timeDifferenceStr = request.getParameter("timeDifference");
		long timeDifferenceMillis = Long.parseLong(timeDifferenceStr);
		long timeDifferenceMinutes = timeDifferenceMillis / (1000 * 60);
		String executionTimeStr = request.getParameter("executionTime");
		long executionTimeMillis = Long.parseLong(executionTimeStr);

		double score = calculateScore(timeDifferenceMinutes, executionTimeMillis);
		
		try {
		
			boolean isSubmit = TournamentDAO.getObj().isSubmit(mailID, solution, score); 			
			if(isSubmit) {
				logger.info(mailID + " submitted the solution successfully");
				JSONObject resObj = JSON.Create(200, mailID + " submitted the solution successfully");
				response.getWriter().write(resObj.toString());
			}
			else {
				throw new Exception("Sorry! Something went wrong");
			}
		}
		catch(Exception e) {
			logger.error(mailID + " failed to submit the solution " );
			JSONObject errObj = JSON.Create(400, mailID + "Failed to submit the solution ");
			response.getWriter().write(errObj.toString());
		}
	}
	
	private double calculateScore(long timeDifferenceMinutes, long executionTimeMillis) {
		
		return timeDifferenceMinutes * executionTimeMillis / 1000000;
	}

}
