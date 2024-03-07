package com.question.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.question.QuestionDao;

import utils.CommonLogger;
import utils.JSON;

/**
 * Servlet Filter implementation class validQuestion
 */
 @WebFilter("/v1/questionDetails")
public class validQuestion extends HttpFilter implements Filter {
	Logger logger = new CommonLogger(validQuestion.class).getLogger();
    /**
     * @see HttpFilter#HttpFilter()
     */
    public validQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		// place your code here
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		 int questionId = httpRequest.getRequest("QID");
//		 
//		if (questionDao.isValidQuestionId(questionId) && questionId != -1) {
//	        // Validate the question ID against your database
//	        // If the question ID is valid, proceed with the request
//	        // If the question ID is invalid, you may choose to return an error response or redirect to an error page
//	        // For simplicity, let's assume a hypothetical method isValidQuestionId(int questionId) to validate the ID
//	            chain.doFilter(request, response); // Proceed with the request
//	        } 
//		else {
//	            // Handle invalid question ID (e.g., return an error response)
//	            HttpServletResponse httpResponse = (HttpServletResponse) response;
//	            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid question ID");
//	            return;
//	        }
//	     
//	}
	
	QuestionDao questionDao = new QuestionDao(); // Ensure questionDao is properly initialized

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    
	    // Extract the question ID from the request parameters
	    String questionId = request.getParameter("QID");
		System.out.println(questionId);
	    
		try {
	        if (questionDao.isValidQuestionId(questionId)) {
		        // If the question ID is valid, proceed with the request
	        	logger.info("the Question is valid!!!");
		        chain.doFilter(request, response);
		    } 
	    } catch (Exception e) {
		        logger.error("Invalid question ID");
		        JSONObject errorResponse = JSON.Create(400, e.getMessage());
		        response.getWriter().write(errorResponse.toString());
	    }
	}



	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
