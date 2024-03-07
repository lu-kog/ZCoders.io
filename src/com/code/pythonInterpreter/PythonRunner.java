package com.code.pythonInterpreter;

import java.io.ByteArrayOutputStream;

import org.json.JSONException;
import org.json.JSONObject;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import com.solution.Solution;


public class PythonRunner{
	
	String definitionName;
	JSONObject jsonobj;
	ByteArrayOutputStream outputStream;
	PythonInterpreter interpreter;
	Solution solution;
	JSONObject resultjson = new JSONObject();

	public PythonRunner(String definitionName, JSONObject jsonobj, ByteArrayOutputStream outputStream, PythonInterpreter interpreter,Solution solution) {
		super();
		this.definitionName = definitionName;
		this.jsonobj = jsonobj;
		this.outputStream = outputStream;
		this.interpreter = interpreter;
		this.solution = solution;
	}

	public void run() throws JSONException {
		try {
//			interpreter.execfile(
//					"/home/vasanth-zstk361/Downloads/Coders.io/src/main/webapp/python/problems/evenOrOdd/evenOrOdd_test.py");
			PyObject pyFunction = interpreter.get(definitionName);
			PyObject function = pyFunction.__call__(new PyObject[] {});
			if(jsonobj.get("output") instanceof String){
				System.out.println(jsonobj);
				String result = function.asString();
				boolean iscrt = (jsonobj.get("output")).equals(result);
				resultjson.put("Result", iscrt);
				resultjson.put("output", jsonobj.get("output"));
			}
			else if(jsonobj.get("output") instanceof Integer){
				int result = function.asInt();
				boolean iscrt = jsonobj.getInt("output") == result;
				resultjson.put("Result", iscrt);
				resultjson.put("output", jsonobj.getInt("output"));
			}
			resultjson.put("Print", outputStream.toString());
		} catch (Exception e) {
			String error = e.getMessage();
			resultjson.put("Result", false);
			resultjson.put("Error", error);
			e.printStackTrace();
		}
	
	}

	public String getDefinitionName() {
		return definitionName;
	}

	public JSONObject getJsonobj() {
		return jsonobj;
	}
}