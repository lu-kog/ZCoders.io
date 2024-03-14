package com.tournament.filters;

import java.io.IOException;
import java.sql.Timestamp;

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

import java.time.LocalTime;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import utils.CommonLogger;
import utils.JSON;

// @WebFilter("/v1/joinTournament")
public class JoinTournamentFilter extends HttpFilter implements Filter {
 
    public JoinTournamentFilter() {
        super();
    }

    public void destroy() {
    }
    
    static Logger logger = new CommonLogger(JoinTournamentFilter.class).getLogger();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final LocalTime startTime = LocalTime.of(18, 0); // 6:00 PM
        Timestamp joinTime = new Timestamp(System.currentTimeMillis());
        final LocalTime endTime = LocalTime.of(20, 0); // 8:00 PM


        try {
            String mailID = request.getParameter("mailID");
            boolean canJoinTournament = isTournamentJoinable(joinTime,startTime , endTime);

            boolean alreadyParticipated = checkIfHeAlreadyParticipated(mailID);

            if(alreadyParticipated){
                throw new Exception("Sorry, You already participated!");
            }
            if (canJoinTournament) {
                chain.doFilter(request, response);
            } else {
                JSONObject errRes = JSON.Create(400, "Sorry, can't join the tournament.");
                response.getWriter().write(errRes.toString());
            }
        } catch (Exception e) {
            logger.error("Error on join tournament filter - ", e);
            JSONObject errRes = JSON.Create(400,
                    "Error on joining tournament: " + e.getMessage());
            response.getWriter().write(errRes.toString());
        }
    }

    private boolean checkIfHeAlreadyParticipated(String mailID) {
        return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
    }

    private boolean isTournamentJoinable(Timestamp joinTime, LocalTime startTime, LocalTime endTime) {
        LocalTime joinLocalTime = joinTime.toLocalDateTime().toLocalTime();
        return (joinLocalTime.isBefore(endTime) && joinLocalTime.isAfter(startTime));
    }
}
