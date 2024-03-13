package com.code.javaCompiler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userName = "krish";
		String questionID = "123";
		String srcCode = "public class kata{\n"
				+ "public static String sayHello() { \n"
				+ "  return \"hello\";\n"
				+ "}\n"
				+ "}";
		Compiler compiler = new Compiler();

		JSONObject respJson = new JSONObject();
		


		// getting class file:

		String clsPath = "/home/workspace/Coders.io/.cide_bin/";


            


		try {
            respJson.put("compilation result", compiler.compile(userName, srcCode, questionID));
			
			// Load the "Hello" class
			
			Thread.sleep(800);
			Class<?> clazz = Class.forName("tmp.krish.kata");
			respJson.put("class", clazz);
			respJson.put("classPath", clazz.getResource(clazz.getSimpleName() + ".class"));
			Object helloInstance = clazz.getDeclaredConstructor().newInstance();
            	Method sayHelloMethod = clazz.getMethod("sayHello");
            	sayHelloMethod.invoke(helloInstance);
				respJson.put("invoked output", sayHelloMethod.invoke(helloInstance));
        } catch (Exception e) {
			try {
                respJson.put("error2", e);
            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

		response.getWriter().write(respJson.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
