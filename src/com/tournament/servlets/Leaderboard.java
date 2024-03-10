package com.tournament.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import utils.CommonLogger;
import utils.JSON;
import com.tournament.TournamentDAO;

@WebServlet("/v1/leaderboard")
public class Leaderboard extends HttpServlet {

	
	static Logger logger = new CommonLogger(Leaderboard.class).getLogger();
	
    public Leaderboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject ranking = null;
		
		try {
			ranking = TournamentDAO.getObj().leaderBoard();
			response.getWriter().write(ranking.toString());
		}
		catch(Exception e) {
			logger.error("Error on ranking : " + e);
			JSONObject errRes = JSON.Create(400, "Unable to retrieve leaderboard data. Please try again later.");
			response.getWriter().write(errRes.toString());
		}
		
	}

}
