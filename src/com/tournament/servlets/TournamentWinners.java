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

@WebServlet("/v1/TournamentWinners")
public class TournamentWinners extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	static Logger logger = new CommonLogger(TournamentWinners.class).getLogger();
	
    public TournamentWinners() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject winners = new JSONObject();
		
		
		try {
			winners = TournamentDAO.getObj().getWinners();
			response.getWriter().write(winners.toString());
		}
		catch(Exception e) {
			logger.error("Error on tournament winners : " + e);
			JSONObject errRes = JSON.Create(400, "Unable to retrieve winners : " + e.getMessage());
			response.getWriter().write(errRes.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
