package com.tournament.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import utils.CommonLogger;
import utils.JSON;
import com.tournament.TournamentDAO;

@WebServlet("/v1/join")
public class JoinTournament extends HttpServlet {

	static Logger logger = new CommonLogger(JoinTournament.class).getLogger();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mailId = request.getParameter("mailId");
		String Q_ID = request.getParameter("Q_ID");
		
		try {
			boolean isJoined = TournamentDAO.getObj().isJoin(mailId, Q_ID);
			
			if(isJoined) {
				logger.info(mailId + " joined in the tournament");
				JSONObject resObj = JSON.Create(200, mailId + " joined in the tournament");
				response.getWriter().write(resObj.toString());
			}
			else {
				throw new Exception("Sorry! Something went wrong");
			}
		}
		catch(Exception e) {
			logger.error(mailId + " does not join in the tournament");
			JSONObject errObj = JSON.Create(400, mailId + " does not join in the tournament");
			response.getWriter().write(errObj.toString());
		}
		
	}

}
