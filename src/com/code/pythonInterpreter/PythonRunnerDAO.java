package com.code.pythonInterpreter;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.python.util.PythonInterpreter;

import com.solution.Solution;


public class PythonRunnerDAO {
	JSONObject testCases;
	Solution solution;
	public PythonRunnerDAO(JSONObject testCases,Solution solution) {
		super();
		this.testCases = testCases;
		this.solution=solution;
	}
	
	public List<JSONObject> runner() throws ParseException, InterruptedException, ExecutionException, JSONException {
		List<JSONObject> result = new ArrayList<JSONObject>();
		System.setProperty("python.import.site", "false");
		for(int i=0;i<testCases.length();i++) {
	    	PythonInterpreter interpreter = new PythonInterpreter();
			ByteArrayOutputStream errStream = new ByteArrayOutputStream();
			interpreter.setErr(new PrintStream(errStream));
			JSONObject resultJson = new JSONObject();
			PythonRunner pr = null;
			JSONObject jsonobj = (JSONObject) testCases.get(i+"");
			try {
				String questionID = solution.getQuestion().getQuestionID();
				String basic ="\n"
						+ "import traceback\n"
						+ "try:\n"
						+ "	exec(open('/home/workspace/Coders.io/src/com/code/python/"+solution.getUser().getUserName()+"/"+solution.getUser().getUserName()+".py').read())\n"
						// + " "+solution.getCode()
						+ "\n"
						+ "except Exception as err:\n"
						+ "	raise Exception(traceback.format_exc())\n"
						+ "";
				System.out.println("HEllo");
				System.out.println(basic);
				interpreter.exec(basic);

				String func = "import traceback\n"
				    + "def test"+i+"():\n"
					+ "	try:\n"
					+ "";
				JSONObject jsonobj1 = jsonobj.getJSONObject("params");
				String param = "";
				func+="		return "+solution.getQuestion().getfunctionName()+"(\""+jsonobj1.get("0")+"\")\n"
					+ "	except Exception as err:\n"
					+ "		raise Exception(traceback.format_exc())\n"
					+ "";
				System.out.println(func);
				interpreter.exec(func);
		    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    	PrintStream printStream = new PrintStream(outputStream);
				interpreter.setOut(printStream);
				pr = new PythonRunner("test"+i,jsonobj,outputStream,interpreter,solution);
				pr.run();
				pr.resultjson.put("test"+i, jsonobj);
				resultJson = pr.resultjson;
				result.add(resultJson);
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject resultjson = new JSONObject();
				resultjson.put("test"+i, jsonobj);
				resultjson.put("Error", e.getMessage());
				result.add(resultjson);
			}
		}
        return result;
	}
	
}
