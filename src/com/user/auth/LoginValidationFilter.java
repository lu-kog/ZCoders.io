package com.user.auth;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import utils.CommonLogger;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import utils.DB;
import utils.JSON;



import com.user.UserDAO;

/**
 * Servlet Filter implementation class LoginValidationFilter
 */
@WebFilter("/v1/login")
public class LoginValidationFilter extends HttpFilter implements Filter {
       
    private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public LoginValidationFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * Take mailID, Passwd as params
		 * null, "" check, Validate on DB.
		 * Chain with LoginUser
		 */
		
		

		Logger logger = new CommonLogger(LoginValidationFilter.class).getLogger();
	
		String mailID = request.getParameter("mailID");
	    String passwd = request.getParameter("passwd");
		System.out.println(mailID+"   " +passwd);
		try{
				if (mailID == null || mailID.trim().isEmpty() || passwd == null || passwd.trim().isEmpty() || (!validMailID(mailID))) {

				Logger.getLogger(LoginValidationFilter.class).error("Login credentials failed:"+mailID+" pass:"+passwd);
				JSONObject errJson = JSON.Create(401, "Invalid Credentials!");
				response.getWriter().write(errJson.toString());
	    	}else {
				logger.info("Login credentials passed:" + mailID + " pass:" + passwd);
				UserDAO.getObj().checkStreakCount(mailID);
				chain.doFilter(request, response);
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			JSONObject errJson = JSON.Create(400, "Invalid credentials!");
	        response.getWriter().write(errJson.toString());
		}
	   
		
	}

	private boolean validMailID(String mailID) {
		return DB.checkValueisExist(mailID, "Users", "mailID");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
