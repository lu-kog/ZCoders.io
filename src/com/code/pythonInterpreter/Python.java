package com.code.pythonInterpreter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

import com.solution.Solution;

public class Python {
    public long executionTime;
	public JSONObject runner(Solution solution) throws IOException, JSONException, ParseException {

		String questionName = "evenOrOdd";
		String userName = solution.getUser().getUserName();
		String code = solution.getCode();
		JSONObject testCases = solution.getQuestion().getTestCases();
        // "{\"56873465\": [{\"param\": {0:[0,1,2,3,4,5,6,7,8,9],1:\"hello\"}, \"expectedOutput\": 45},{\"param\": {0:[0,2,4,6,8],1:1}, \"expectedOutput\": 20},{\"param\": {0:[1,3,5,7,9],1:1}, \"expectedOutput\": 25},{\"param\": {0:[0,5,10,15,20,25,30,35,40,45],1:1}, \"expectedOutput\": 225},{\"param\": {0:[0,10,20,30,40,50,60,70,80,90],1:1}, \"expectedOutput\": 450}]}";

        
        // String testCases = "{\"Q1testCases\": [{\"inputs\": [1], \"expectedOutput\": \"Odd\"},{\"inputs\": [2], \"expectedOutput\": \"Even\"},{\"inputs\": [0], \"expectedOutput\": \"Even\"},{\"inputs\": [87234], \"expectedOutput\": \"Even\"},{\"inputs\": [863245], \"expectedOutput\": \"Odd\"}]}";
        
        // System.out.println("/home/workspace/Coders.io/src/com/code/python/"+solution.getQuestion().getQuestionID()+"/solution/"+solution.getUser().getUserName()+solution.getQuestion().getQuestionID()+".py");
        
        File file = new File("/home/workspace/Coders.io/src/com/code/python/"+solution.getUser().getUserName());
        file.mkdir();

        file = new File("/home/workspace/Coders.io/src/com/code/python/"+solution.getUser().getUserName()+"/"+solution.getUser().getUserName()+".py");
        
        
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists.");
        }

        FileWriter writer = new FileWriter(file);
        
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        
        bufferedWriter.write(code);
        
        bufferedWriter.close();

        PythonRunnerDAO run = new PythonRunnerDAO(testCases,solution);
        List<JSONObject> prs = null;
        try {
			prs = run.runner();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
        JSONObject json = new JSONObject();
        String name="test";
        int i=0;
        for(JSONObject pr : prs) {
        	json.put(name+i, pr);
        	i++;
        }
        executionTime = run.executionTime;
        return (JSONObject)json;
	}
}
