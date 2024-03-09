package com.tournament.filters;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import utils.CommonLogger;
import utils.JSON;

@WebFilter("/v1/submit")
public class SubmitFilter extends HttpFilter implements Filter {
  
	static Logger logger = new CommonLogger(SubmitFilter.class).getLogger();
	
    public SubmitFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String mailID = request.getParameter("mailID");
		Timestamp submitTime = Timestamp.valueOf(req.getParameter("submitTime"));
		Timestamp endTime = Timestamp.valueOf(req.getParameter("endTime"));
		try {
			
			boolean isSubmit = isSubmissionValid(submitTime, endTime);
			if(isSubmit) {
				logger.info(mailID + " can submit");
				chain.doFilter(request, response);
			}
			else {
				logger.error("Time out. " + mailID + " can't submit");
				JSONObject errRes = JSON.Create(400, "Time out. " + mailID + " can't submit");
				response.getWriter().write(errRes.toString());
			}
		}
		catch(Exception e) {
			logger.error("Error on submit filter : " + e);
			JSONObject errRes = JSON.Create(400, "Time out. " + mailID + " can't submit");
			response.getWriter().write(errRes.toString());
		}
		
	}
	
	

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

    private boolean isSubmissionValid(Timestamp submitTime, Timestamp endTime) {
		    return submitTime.before(endTime);
	}

}
