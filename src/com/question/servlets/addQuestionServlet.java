package com.question.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.question.Language;
import com.question.Question;
import com.question.QuestionDao;
import com.question.Tag;
import com.solution.Solution;
import com.user.UserDAO;
import com.user.User;

import utils.CommonLogger;
import utils.Generator;
import utils.JSON;

/**
 * Servlet implementation class addQuestionServlet
 */
 @WebServlet("/v1/addQuestion")
public class addQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = new CommonLogger(AllQuestionDetails.class).getLogger();
//	  Q_ID varchar(8)primary key,
//	    Q_name varchar(100),
//	    description text,
//	    example text,
//	    levelID tinyint,
//	    Author varchar(30),
//	    status ENUM('APPROVED', 'NOTAPPROVED') default 'NOTAPPROVED',
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addQuestionServlet() {
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
	  QuestionDao questionDao=new QuestionDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// // TODO Auto-generated method stub
		// //doGet(request, response);
		
		// // String questionId = request.getParameter("questionID"); generate questionIID
		// String questionName = request.getParameter("questionName");
		// String description = request.getParameter("description");
		// String example = request.getParameter("example");
		// String level= request.getParameter("level");
		// String mailId = request.getParameter("UID");
		// String status = "NOTAPPROVED";
		// try {
		// 	// boolean success = questionDao.addQuestion(questionId, questionName, description, example, level, mailId, status);
		// 	boolean success = true;
		// 	if(success) {
		// 		logger.info("Question added successfully.");
		// 		JSONObject JsonResponse = JSON.Create(200, "Question added successfully");
		// 		 response.getWriter().write(JsonResponse.toString());
		// 		//response.getWriter().println("Question added successfully.");
		// 	}
		// 	else {
		// 		logger.error("Something wrong for add the question");
		// 		JSONObject errorResponse = JSON.Create(500, "Something wrong for add the question");
		// 		 response.getWriter().write(errorResponse.toString());
		// 	}
		// }
		// catch(Exception e) {
		// 	logger.error("Something went wrong to added the question: " + e.getMessage());
		//     JSONObject errorResponse = JSON.Create(500, e.getMessage());
		// 	 response.getWriter().write(errorResponse.toString());
		// }

		try {
			JSONObject questionDetails = new JSONObject(request.getParameter("questionDetails"));
			String QID = Generator.ValidNumerical(8,"Questions","Q_ID");
			String Qname  = questionDetails.getString("Qname");
			String userID  = questionDetails.getString("userID");
			int levelID = questionDetails.getInt("levelId");
			JSONArray languages = questionDetails.getJSONArray("languages");
			System.out.println(languages + "langs");
			JSONArray tags = questionDetails.getJSONArray("tags");
			String description  = questionDetails.getString("description");
			String example  = questionDetails.getString("example");
			String javaSyntax = questionDetails.getString("javaSyntax");
			String pythonSyntax = questionDetails.getString("pythonSyntax");
			String functionName = questionDetails.getString("functionName");
			JSONObject testCases = questionDetails.getJSONObject("testcases");
			List<Language> languagesList = new ArrayList<>();

        // Iterate through the JSONArray and add each element to the ArrayList
        for (int i = 0; i < languages.length(); i++) {
            languagesList.add(new Language((String) languages.get(i)));
        }
			List<Tag> tagsList = new ArrayList<>();

        // Iterate through the JSONArray and add each element to the ArrayList
        for (int i = 0; i < tags.length(); i++) {
            tagsList.add(new Tag((String) tags.get(i)));
        }
			QuestionDao questionDao = QuestionDao.getObj();
			User user = new User();
			user.setMailID(userID);
			user = UserDAO.getObj().getUserProfileData(user);
			Question question = new Question(QID,Qname,description,functionName,
			questionDao.getLevel(levelID),user,languagesList,example,0,0,new ArrayList<Solution>(),tagsList,testCases);
		response.getWriter().write(questionDao.addQuestion(question,javaSyntax,pythonSyntax)+"");
		} catch (Exception e) {
			e.printStackTrace();
		}



	}
}