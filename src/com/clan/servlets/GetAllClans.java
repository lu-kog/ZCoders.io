package com.clan.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.clan.ClanDAO;
import utils.CommonLogger;
import utils.JSON;

@WebServlet("/v1/GetAllClans")
public class GetAllClans extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllClans() {
        super();
        // TODO Auto-generated constructor stub
    }


	static Logger logger = new CommonLogger(GetAllClans.class).getLogger();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONArray detailsForClan = null;
		
		try {
			detailsForClan = ClanDAO.getObj().getClanDetails();
			logger.info("Fetch user details for clan successfully");
			JSONObject clanDetails=new JSONObject();
			clanDetails.put("clanDetails",detailsForClan);
			response.getWriter().write(clanDetails.toString());
		}
		catch(Exception e) {
			logger.error("Failed to fetch user details for clan");
			JSONObject errObj = JSON.Create(400, "Failed to fetch user details for clan");
			response.getWriter().write(errObj.toString());
		}
	}

}
