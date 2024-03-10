package com.code.pythonInterpreter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.python.antlr.PythonParser.return_stmt_return;
import org.python.icu.util.BytesTrie.Entry;
import org.python.icu.util.BytesTrie.Iterator;
import org.python.util.PythonInterpreter;

import com.solution.Solution;


public class PythonRunnerDAO {
	JSONObject testCases;
	Solution solution;
	long executionTime;
	public PythonRunnerDAO(JSONObject testCases,Solution solution) {
		super();
		this.testCases = testCases;
		this.solution=solution;
	}
	
	public List<JSONObject> runner() throws ParseException, InterruptedException, ExecutionException, JSONException {
		List<JSONObject> result = new ArrayList<JSONObject>();
		System.setProperty("python.import.site", "false");
		long stTime = System.currentTimeMillis();
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
				int j=0;
				String param = "";
				while(j<jsonobj1.length()){
					// System.out.println(itr.next());
					System.out.println(jsonobj1.get(j+""));
					param+=getAsParam(jsonobj1.get(j+""));
				j++;
				}
				System.out.println("Parameter : "+param);
				System.out.println("Parameter : "+param.substring(0, param.length()-1));
				func+="		return "+solution.getQuestion().getfunctionName()+"("+param+")\n"
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
		long endTime = System.currentTimeMillis();
		executionTime = endTime-stTime;
        return result;
	}

    private String getAsParam(Object object) {
		String a = "";
			if(object.toString().indexOf("[")>=0){
				a += object+"";
				System.out.println(a+"skdenfuisjhdbuvjh");
			}
			else if(object instanceof String){
				a+="\""+object+"\"";
			}
			else if(object instanceof Boolean){
			}
			else if(!(object instanceof String)){
				return object+"";
			}
			System.out.println(a+ "   sok");
			return a+",";
    }
	
}
