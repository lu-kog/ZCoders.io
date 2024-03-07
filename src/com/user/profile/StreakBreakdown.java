package com.user.profile;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

import com.solution.SolutionDao;

import utils.Query;

/**
 * Servlet implementation class StreakBreakdown
 */
@WebServlet("/StreakBreakdown")
public class StreakBreakdown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StreakBreakdown() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
			int month = Integer.parseInt(request.getParameter("month"));
			String mailID = request.getParameter("mailId");

			ArrayList<LocalDate> solutionDates = SolutionDao.getObj().getDateOfSolutionSolved(month, mailID);
            JSONArray dateJsonArray = new JSONArray(solutionDates);
            JSONObject resulJsonObject = new JSONObject();
            resulJsonObject.put("dates", dateJsonArray);
            response.getWriter().write(resulJsonObject.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
