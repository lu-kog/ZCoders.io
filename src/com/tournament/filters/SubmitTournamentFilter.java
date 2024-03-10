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

import java.time.LocalTime;
import utils.CommonLogger;
import utils.JSON;

@WebFilter("/v1/submit")
public class SubmitTournamentFilter extends HttpFilter implements Filter {

    static Logger logger = new CommonLogger(SubmitTournamentFilter.class).getLogger();

    public SubmitTournamentFilter() {
        super();
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String mailID = request.getParameter("mailID");
        Timestamp submitTime = new Timestamp(System.currentTimeMillis());

    
        final LocalTime endTime = LocalTime.of(20, 0); // 8:00 PM

        try {
            boolean isSubmit = isSubmissionValid(submitTime, endTime);
            if (isSubmit) {
                logger.info(mailID + " can submit");
                chain.doFilter(request, response);
            } else {
                logger.error("Time out. " + mailID + " can't submit");
                
                
                JSONObject errRes = JSON.Create(400,
                        "Time out. " + mailID + " can't submit");
                res.getWriter().write(errRes.toString());
            }
        } catch (Exception e) {
            logger.error("Error on submit filter : " + e);
           
           
            JSONObject errRes = JSON.Create(400,
                    "Error on submit filter: " + e.getMessage());
            res.getWriter().write(errRes.toString());
        }

    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    private boolean isSubmissionValid(Timestamp submitTime, LocalTime endTime) {
        
        LocalTime submitLocalTime = submitTime.toLocalDateTime().toLocalTime();
        return submitLocalTime.isBefore(endTime);
    }
}
