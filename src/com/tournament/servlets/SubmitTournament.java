package com.tournament.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import utils.CommonLogger;
import utils.DB;
import utils.JSON;
import utils.Query;

import javax.servlet.annotation.WebServlet;
import com.solution.SolutionDao;
import com.tournament.TournamentDAO;

@WebServlet("/v1/SubmitTournament")
public class SubmitTournament extends HttpServlet {
	
	static Logger logger = new CommonLogger(SubmitTournament.class).getLogger();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String solution = request.getParameter("Sol_ID");
		String mailID = request.getParameter("mailID");
		
		try {
			PreparedStatement pstmt = DB.getConnection().prepareStatement("UPDATE Tournament SET Submit_time = ? WHERE mailID = ?");
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(1, currentTimestamp);
			pstmt.setString(2, mailID);
			int rs = pstmt.executeUpdate();
			System.out.println(pstmt.toString());
			double executionTimeMillis = SolutionDao.getObj().getExecutionTimeMillis(solution,mailID);
			long timeDifferenceMinutes = SolutionDao.getObj().getTimeDiffernce(solution,mailID);
			double score = calculateScore(timeDifferenceMinutes, executionTimeMillis);
		
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
			e.printStackTrace();
			logger.error(mailID + " failed to submit the solution " );
			JSONObject errObj = JSON.Create(400, mailID + "Failed to submit the solution ");
			response.getWriter().write(errObj.toString());
		}
	}
	

	private double calculateScore(long timeDifferenceMinutes, double executionTimeMillis) {
		return timeDifferenceMinutes * executionTimeMillis / 1000000;
	}

	

}

