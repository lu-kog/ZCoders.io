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

@WebServlet("/v1/joinTournament")
public class JoinTournament extends HttpServlet {

	static Logger logger = new CommonLogger(JoinTournament.class).getLogger();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mailId = request.getParameter("mailId");
		
		JSONObject questionDetails = new JSONObject();

		try {
			questionDetails = TournamentDAO.getObj().joinTournament(mailId);
			response.getWriter().write(questionDetails.toString());
		}
		catch(Exception e) {
			logger.error(mailId + " does not join in the tournament"+" error:"+e);
			JSONObject errObj = JSON.Create(400, mailId + " can't join in the tournament");
			response.getWriter().write(errObj.toString());
		}
		
	}

}
