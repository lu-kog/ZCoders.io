package com.solution;

import java.util.Collection;
import java.util.Date;

import com.question.Question;
import com.user.User;


public class Solution {
	private String SolID;
	private User user;
	private Date solDate;
	private Question question;
	private String code;
	private String language;
	private Status status;
	private SolvedType solvedType;
	private String levelName;
	
	


	public Solution(String solID, User user, Question question, String code, String language, Status status,SolvedType solvedType) {
		super();
		SolID = solID;
		this.user = user;
		this.question = question;
		this.code = code;
		this.language = language;
		this.status = status;
		this.solvedType = solvedType;
	}

	public Solution() {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLevelname() {
		return levelName;
	}

	public void setLevelname(String levelName) {
		this.levelName = levelName;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getDate() {
		return solDate;
	}

	public void setDate(Date solDate) {
		this.solDate = solDate;
	}

	public String getSolID() {
		return SolID;
	}
	public void setSolID(String solID) {
		SolID = solID;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Status getStatus() {
		return status;
	}
	
	public SolvedType getSolvedType() {
		return solvedType;
	}

	public void setSolvedType(SolvedType solvedType) {
		this.solvedType = solvedType;
	}
	

    public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setStatusFromString(String statusString) {
        if (statusString != null) {
            // Convert string to enum
            this.status = Status.valueOf(statusString.toUpperCase());
        }
    }

    public void setSolvedTypeFromString(String solvedTypeString) {
        if (solvedTypeString != null) {
            // Convert string to enum
            this.solvedType = SolvedType.valueOf(solvedTypeString.toUpperCase());
        }
    }
}
