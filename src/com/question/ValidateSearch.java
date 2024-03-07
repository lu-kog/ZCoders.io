package com.question;

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

import utils.CommonLogger;
import utils.JSON;

@WebFilter("/Search")
public class ValidateSearch extends HttpFilter implements Filter {
  
	static Logger logger = new CommonLogger(ValidateSearch.class).getLogger();
	
    public ValidateSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String question_name = req.getParameter("question_name");
		
        try {
        	 boolean checkPrompt = checkPrompt(question_name);
        	 
        	 if(checkPrompt) {
        		 logger.info(question_name + " is a valid search prompt!");
        		 chain.doFilter(request, response);
        	 }
        	 else {
        		throw new Exception("Invalid search");
        	 }
        }
        catch(Exception e) {
        	logger.error("Error on search filter - keyword:"+ question_name+ " error:" + e);
        	JSONObject errObj = JSON.Create(400, "Invalid search");
        	response.getWriter().write(errObj.toString());
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private boolean checkPrompt(String question_name) {
		
		if(question_name.equals(null) || question_name.isEmpty()) {
			return false;
		}
		return true;
	}

}
