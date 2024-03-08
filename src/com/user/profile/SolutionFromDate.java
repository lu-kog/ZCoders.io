package com.user.profile;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.question.Question;
import com.solution.SolutionDao;

/**
 * Servlet implementation class SolutionFromDate
 */
@WebServlet("/v1/SolutionFromDate")
public class SolutionFromDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolutionFromDate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
			ArrayList<Question> questionSolved = SolutionDao.getObj().getQuestionsSolvedOnDate(LocalDate.parse(request.getParameter("date")),request.getParameter("mailId"));
            System.out.println(questionSolved.size());
            
			JSONArray dateMatchedQuestions = new JSONArray();
            for(Question question:questionSolved){
				JSONObject resulJsonObject = new JSONObject();
                resulJsonObject.put("question", question.toJSON());
				dateMatchedQuestions.put(resulJsonObject);
            }
			JSONObject result = new JSONObject();
			result.put("data", dateMatchedQuestions);
            response.getWriter().write(result.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
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
