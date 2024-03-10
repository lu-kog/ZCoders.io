package com.code.javaCompiler;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.json.JSONObject;


import utils.CommonLogger;
import utils.JSON;

public class TestRunner {
	static Logger logger = new CommonLogger(TestRunner.class).getLogger();

	public JSONObject runner(String userName, String questionID, String methodName, JSONObject testCasesJson)
			throws Exception {
		String className = "kata";
		Class<?> cls;
		try {
			System.out.println("tmp." + userName + "." + className);
			cls = Class.forName("tmp." + userName + "." + className);
		} catch (ClassNotFoundException e) {
			logger.error("Error on running java code: Class not found - " + e);
			throw new Exception("Compilation error!");
		}

		System.out.println(cls);

		Object obj;
		try {
			obj = cls.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			logger.error("Error on getting instance of Runtime object - " + e);
			throw new Exception("Method not found or changed!");
		}

		List<TestCase> testCases = new ArrayList<TestCase>();
		Iterator<?> keys = testCasesJson.keys();

		// creating testcase Objects
		int i=0;
		while (keys.hasNext()) {
			Object expectedOutput;
			List<Object> paramsValues = new ArrayList<Object>();

			String key = (String) keys.next();
			JSONObject testCase = testCasesJson.getJSONObject(key); // Get the test case object

			expectedOutput = testCase.get("output");

			JSONObject params = testCase.getJSONObject("params"); // Get the params object

			Iterator<?> paramKeys = params.keys();

			while (paramKeys.hasNext()) {
				paramsValues.add(params.get((String) paramKeys.next()));
			}
			i++;	
			TestCase newCase = new TestCase(obj, methodName,(JSONObject) testCasesJson.get((i-1)+""), expectedOutput, i, paramsValues.toArray());

			testCases.add(newCase);
		}

		List<Callable<JSONObject>> tasks = testCases.stream()
				.map(TestCase::toCallable)
				.collect(Collectors.toList());

		PrintStream originalOut = System.out;

		JSONObject results = new JSONObject();
		Iterator<Callable<JSONObject>> tasksCall = tasks.iterator();
		ExecutorService executor = Executors.newSingleThreadExecutor();

		i = 0;
		long startTime = System.currentTimeMillis();
		while (tasksCall.hasNext() && ((System.currentTimeMillis() - startTime) < 12000)) {
			FutureTask<JSONObject> futureTask = new FutureTask<>(() -> tasksCall.next().call());

			try {
				executor.execute(futureTask);
				JSONObject ExecResult = futureTask.get(3000, TimeUnit.MILLISECONDS); 
				logger.info("Test Case "+ ExecResult.get("name")+" Result:"+ExecResult);

                ExecResult.put("test"+i , testCasesJson.getJSONObject(i+""));
				
				results.put("test"+i + "", ExecResult);
				i++;
			} catch (TimeoutException e) {
				System.err.println("Execution timed out");
				futureTask.cancel(true); // cancel the task
				JSONObject resultObject = new JSONObject();
				resultObject.put("Result", false);
                resultObject.put("message", "Execution timed out: 3000ms");
                resultObject.put("logs", "");
				results.put("test"+i+"", resultObject);
				break;
			} catch (InterruptedException | ExecutionException e) {
				JSONObject resultObject = new JSONObject();
				resultObject.put("Result", false);
                resultObject.put("message", "Exception: "+e);
                resultObject.put("logs", "");
                resultObject.put("test"+i, testCasesJson.getJSONObject(i+""));
				results.put("test"+i+"", resultObject);
				
			}

			
		}


		System.setOut(originalOut);
		executor.shutdown();
	
		long endTime = System.currentTimeMillis();
		logger.info("All testcases invoked - user:" + userName + " Question - " + questionID);
		results.put("ExecTime", endTime - startTime);
		return results;

	}


	public String readFromFile(String filepath) throws Exception {
		File codeFile = new File(filepath);
		if (!codeFile.exists()) {
			logger.error("File not found - "+filepath);
			throw new Exception("File not found!");
		}
		
		StringBuilder content = new StringBuilder();
		try {
            Files.readAllLines(Paths.get(filepath)).forEach(line -> content.append(line).append("\n"));
            return content.toString();
        } catch (IOException e) {
        logger.error("Can't read from "+filepath+": "+e);
            throw new Exception("Error reading from file.");
        }

		
	}
	public static void main(String[] args) {
		try {
            Class<?> cls = Class.forName("tmp.vsr.kata");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
