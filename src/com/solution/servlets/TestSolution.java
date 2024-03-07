package com.solution.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.code.javaCompiler.TestRunner;
import com.code.javaCompiler.Compiler;
import com.code.pythonInterpreter.Python;
import com.question.Question;
import com.solution.Solution;
import com.solution.SolutionDao;

import utils.CommonLogger;
import utils.JSON;

/**
 * Servlet implementation class TestSolution
 */
@WebServlet("/v1/TestSolution")
public class TestSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = new CommonLogger(TestSolution.class).getLogger();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	SolutionDao solutionDao = new SolutionDao();

	public TestSolution() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		String solId = "";
		String sol = "";
		try {
			System.out.println(request.getParameter("solutionJson"));
			System.out.println();
			JSONObject json = new JSONObject(request.getParameter("solutionJson"));
			solId = json.getString("solId");
			sol = json.getString("solution");
			System.out.println(sol);
			
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		// String sol = request.getParameter("solution");
		logger.info("solution : " + sol);
	
		try {
			boolean result = solutionDao.updateSolution(solId, sol);
			System.out.println("status : "+result+"    "+sol);
			logger.info(result);
			if (result) {
				Solution solution = solutionDao.solutionDetails(solId);

				JSONObject solutionJson = new JSONObject();
				solutionJson.put("solID", solution.getSolID());
				solutionJson.put("mailID", solution.getUser().getMailID());
				solutionJson.put("QID", solution.getQuestion().getQuestionID());
				solutionJson.put("sol", solution.getCode());
				solutionJson.put("status", solution.getStatus().toString());
				solutionJson.put("language", solution.getLanguage());
				solutionJson.put("solvedType", solution.getSolvedType().toString());

				logger.info("Test solution JSON response Successfully!!!");
				JSONObject resultJson = new JSONObject();
				System.out.println(solution.getLanguage());
				if(solution.getLanguage().equals("Java")) {
					System.out.print("JAva        sdihgioa");
					Compiler compiler = new Compiler();
					JSONObject compilation = compiler.compile(solution.getUser().getUserName(), solution.getCode(), "Calculator");
					boolean isCompiled = compilation.getBoolean("status");
					logger.info("java code compiled: " + isCompiled);
					if(isCompiled) {
						TestRunner run = new TestRunner();
						resultJson.put("StatusCode",200);

						JSONObject testCases = solution.getQuestion().getTestCases();
						// username, solutionID, funcName, testCases - to call test runner
						JSONObject resJsonObject = (JSONObject) run.runner(solution.getUser().getUserName(), solution.getQuestion().getQuestionID(), solution.getQuestion().getfunctionName(), testCases);
						resultJson.put("result",resJsonObject);
					}
					else {
						throw new Exception("Compilation error!");
					}
				}
				else if(solution.getLanguage().equals("Python")) {
					System.out.println("Python skjdbv");
					Python pyRunner = new Python();
					resultJson.put("StatusCode",200);
					JSONObject resJsonObject = pyRunner.runner(solution);
					resultJson.put("result",resJsonObject);
				}
				System.out.print("Output");
				System.out.print(resultJson);
				response.getWriter().write(resultJson.toString());
			} else {
				logger.error("something went wrong for the problem");
				throw new Exception("something went wrong for the problem");
			}
		} catch (Exception e) {
			 e.printStackTrace();
			logger.error("something went wrong for the problem" + e);
			JSONObject errorResponse = JSON.Create(400, e.getMessage());
			response.getWriter().write(errorResponse.toString());
		}
	}

}
