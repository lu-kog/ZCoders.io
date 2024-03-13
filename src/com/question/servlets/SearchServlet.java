package com.question.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import utils.CommonLogger;
import utils.JSON;
import com.question.QuestionDao;

@WebServlet("/v1/Search")
public class SearchServlet extends HttpServlet {
	JSONObject FetchedQuestions = new JSONObject();
	
	static Logger logger = new CommonLogger(SearchServlet.class).getLogger();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String question_name = request.getParameter("question_name");
		
		try {
			FetchedQuestions = QuestionDao.getObj().getQuestions(question_name);
			logger.info("Questions fetched successfully");
			response.getWriter().write(FetchedQuestions.toString());
		}
		catch(Exception e) {
			logger.error("Error on searching questions: keyword:"+question_name+" error"+e);
			JSONObject errObj = JSON.Create(400, "Something went wrong!");
			response.getWriter().write(errObj.toString());
		}
	}

}
