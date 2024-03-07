package com.question;

import java.util.HashMap;

public class Level {
	
	private int levelId;
	private String levelName;
	private int score;
	
	public Level(int levelId, String levelName, int score) {
	    this.levelId = levelId;
	    this.levelName = levelName;
	    this.score = score;
	    
	    
	}
	
	public int getLevelId() {
	    return levelId;
	}
	
	public void setLevelId(int levelId) {
	    this.levelId = levelId;
	}
	
	public String getLevelName() {
	    return levelName;
	}
	
	public void setLevelName(String levelName) {
	    this.levelName = levelName;
	}
	
	public int getScore() {
	    return score;
	}
	
	public void setScore(int score) {
	    this.score = score;
	}
	
	public static Level getLevelsById(int levelId, String levelName, int score) {
		
		  return new Level(levelId, levelName, score);
	}

}