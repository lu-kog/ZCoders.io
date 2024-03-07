package com.question;

import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.solution.Solution;
import com.user.User;

public class Question {
	String QuestionID;
	String QuestionName;
	String description;
	String functionName;
	JSONObject testCases;
	Level level;
	User user;
	List<Language> language;
	String example;
	int noOfAttempt;
	int noOfTimesSubmitted;
	List<Solution> solutions;
	List<Tag> tags;
	
	
	public Question() {
		
	}
	public Question(String questionID, String questionName, String description, String functionName, Level level,
			User user, List<Language> language, String example, int noOfAttempt, int noOfTimesSubmitted,
			List<Solution> solutions, List<Tag> tags, JSONObject testCases) {
		super();
		QuestionID = questionID;
		QuestionName = questionName;
		this.description = description;
		this.functionName = functionName;
		this.level = level;
		this.user = user;
		this.language = language;
		this.example = example;
		this.noOfAttempt = noOfAttempt;
		this.noOfTimesSubmitted = noOfTimesSubmitted;
		this.solutions = solutions;
		this.tags = tags;
		this.testCases = testCases;
	}
	public String getQuestionID() {
		return QuestionID;
	}
	public void setQuestionID(String questionID) {
		QuestionID = questionID;
	}
	public String getQuestionName() {
		return QuestionName;
	}
	public void setQuestionName(String questionName) {
		QuestionName = questionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getfunctionName() {
		return functionName;
	}
	public void setfunctionName(String functionName) {
		this.functionName = functionName;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Language> getLanguage() {
		return language;
	}
	public void setLanguage(List<Language> language) {
		this.language = language;
	}
	public int getnoOfAttempt() {
		return noOfAttempt;
	}
	public void setnoOfAttempt(int noOfAttempt) {
		this.noOfAttempt = noOfAttempt;
	}
	public int getnoOfTimesSubmitted() {
		return noOfTimesSubmitted;
	}
	public void setnoOfTimesSubmitted(int noOfTimesSubmitted) {
		this.noOfTimesSubmitted = noOfTimesSubmitted;
	}
	public List<Solution> getSolution() {
		return solutions;
	}
	public void setSolution(List<Solution> solutions) {
		this.solutions = solutions;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public JSONObject getTestCases() {
		return testCases;
	}
	public void setTestCases(JSONObject testCases) {
		this.testCases = testCases;
	}
	
	public JSONObject toJSON() throws JSONException{
		JSONObject jsonObject = new JSONObject();
        
        // Add simple properties
        jsonObject.put("QuestionID", QuestionID);
        jsonObject.put("QuestionName", QuestionName);
        jsonObject.put("description", description);
        jsonObject.put("functionName", functionName);
        jsonObject.put("example", example);
        jsonObject.put("noOfAttempt", noOfAttempt);
        jsonObject.put("noOfTimesSubmitted", noOfTimesSubmitted);

		return jsonObject;
	}
}


