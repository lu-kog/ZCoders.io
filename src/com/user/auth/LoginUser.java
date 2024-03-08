package com.user.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.user.UserDAO;

import utils.CommonLogger;
import utils.JSON;

/**
 * Servlet implementation class LoginUser
 */
@WebServlet("/v1/login")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUser() {
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
		
		/*
		 * check whether passwd is correct.
		 * create new session
		 * add to cookie
		 */

		
		Logger logger = new CommonLogger(LoginUser.class).getLogger();
		String mailID = request.getParameter("mailID");
		String Passwd = request.getParameter("passwd");


		System.out.println(mailID +"    "+Passwd);
		try {
			boolean isValid = UserDAO.getObj().LoginUser(mailID, Passwd);
			
			if (isValid) {
				// creating session
				String sessionID = UserDAO.createNewSession(mailID);
				List<Cookie> cookies = new ArrayList<Cookie>();
				cookies.add(new Cookie("userID", mailID));
				cookies.add(new Cookie("sessionID", sessionID));
				cookies.forEach(x-> response.addCookie(x));

				String score = UserDAO.getObj().getScoreFromMailID(mailID);
				String userName = UserDAO.getObj().getUserNameFromMailID(mailID);
				String clanName = UserDAO.getObj().getClanNameFromMailID(mailID);
				// boolean isAdmin = UserDAO.getObj().isAdmin(mailID);
				JSONObject respObject = new JSONObject();
				
		        respObject.put("statuscode", 200);
				respObject.put("sessionID", sessionID);
				respObject.put("mailID", mailID);
				respObject.put("userName", userName);
				respObject.put("clanName", clanName);
				respObject.put("score", score);
				// respObject.put("isAdmin", isAdmin);

		        logger.info("Login successfull! 200 :"+mailID);
		        response.getWriter().write(respObject.toString());
			}else {
				JSONObject errJson = JSON.Create(401, "Invalid Credentials!");
				response.getWriter().write(errJson.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("check this error :" + e);
			JSONObject errJson = JSON.Create(400, "Something went wrong! Please re-login.");
			response.getWriter().write(errJson.toString());
		}

	}

}
