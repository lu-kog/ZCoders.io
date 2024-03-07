package com.clan.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.clan.ClanDAO;
import com.clan.CommonLogger;


public class GetClanMembersDetails extends HttpServlet {
	
    public GetClanMembersDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    static Logger logger = new CommonLogger(GetClanMembersDetails.class).getLogger();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String adminID = request.getParameter("adminID");
		JSONArray clanMembersDetails = null;
		
		try {
			clanMembersDetails = ClanDAO.getutiityObj().getClanMembersDetails(adminID);
			logger.info("Fetch clan members details successfully");
			
		}
		catch(Exception e) {
			
		}
		doGet(request, response);
	}

}
