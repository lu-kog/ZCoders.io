package com.tournament.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
	
		/*
		* fetch leaderboard from DB
		* If time ends, increase badge count to winners
		* 
		*/


		JSONObject ranking = null;
		
		try {
			LocalTime currentTime = LocalTime.now();
			LocalTime endTime = LocalTime.of(20, 0);

			if(currentTime.isAfter(endTime)){
				// announce winners;
				TournamentDAO.getObj().announceWinners();
			}
			ranking = TournamentDAO.getObj().leaderBoard();
			response.getWriter().write(ranking.toString());
		}
		catch(Exception e) {
			logger.error("Error on ranking : " + e);
			JSONObject errRes = JSON.Create(400, "Unable to retrieve leaderboard data. Please try again later.");
			response.getWriter().write(errRes.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
