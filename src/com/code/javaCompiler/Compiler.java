package com.code.javaCompiler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import utils.CommonLogger;
import utils.JSON;

public class Compiler {
	static Logger logger = new CommonLogger(Compiler.class).getLogger();

	public JSONObject compile(String userName, String srcCode, String questionID) throws JSONException {
		logger.info("compilation progress");
		String srcPath = "/home/workspace/Coders.io/src/tmp";
		File folder = new File(srcPath + "/" + userName);
		String className = "kata";
		folder.mkdir();
		
		System.out.println("Folder created: " + folder.exists());
		String packageName = "package tmp." + userName + ";";
		String fullSrcCode = packageName + "\n" + srcCode;
		String javafilePath = srcPath + "/" + userName + "/" + "kata.java";

		try {

			File javaFile = new File(javafilePath);
			javaFile.createNewFile();
			System.out.println("Java File created: " + javaFile.exists());
			FileWriter fw = new FileWriter(javaFile);
			System.out.println(fullSrcCode);
			fw.append(fullSrcCode);
			fw.close();

			JavaCompiler javac = ToolProvider.getSystemJavaCompiler();

			DiagnosticCollector<JavaFileObject> dCollector = new DiagnosticCollector<>();
			StandardJavaFileManager fManager = javac.getStandardFileManager(dCollector, null, null);
			List<File> sourceFiles = new ArrayList<File>();

			sourceFiles.add(javaFile);

			Iterable<? extends JavaFileObject> compileUnits = fManager.getJavaFileObjectsFromFiles(sourceFiles);

			List<String> options = Arrays.asList("-d", ".cide_bin/");

			CompilationTask task = javac.getTask(null, fManager, dCollector, options, null, compileUnits);

			PrintStream originalOut = System.out;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream newOut = new PrintStream(baos);
			System.setOut(newOut);

			boolean taskStatus = task.call();

			System.out.println(dCollector.getDiagnostics());
			System.setOut(originalOut);
			if (taskStatus) {
				System.out.println("Success");
				JSONObject compilation = new JSONObject();
				compilation.put("status", true);

				return compilation;
			} else {
				System.out.println("Compile Error:");
				JSONObject compilationErrors = new JSONObject();
				compilationErrors.put("status", false);

				int i = 0;
				for (Diagnostic<?> diagnostic : dCollector.getDiagnostics()) {
					JSONObject error = new JSONObject();
					String errorLine = String.format("Error on line %d ", diagnostic.getLineNumber());
					String errorMsg = diagnostic.getMessage(null);

					error.put("lineNum", errorLine);
					error.put("msg", errorMsg);

					compilationErrors.put(i+"", error);
					i++;
				}

				return compilationErrors;
			}

		} catch (Exception e) {
			logger.error(e);
			JSONObject internalError = new JSONObject();
			internalError.put("status", false);

			JSONObject error = new JSONObject();
			error.put("msg", "Can't compile your code!");
			internalError.put("1", error);
			return internalError;
		}

	}

	public static void main(String[] args) throws IOException {
		String codeString = "public class kata{\n"
				+ "public static String reverse(String s) { \n"
				+ "	for (int i = 0; i<1000; i++){\n"
				+ "      for(int j = 0; j < 10000; j++){\n"
				+ "        System.out.println(i+\" \"+j);\n"
				+ "      }\n"
				+ "    }\n"
				+ "  return \"\";\n"
				+ "}\n"
				+ "}";

		String userName = "krish";

		Compiler obj = new Compiler();
		try {
			JSONObject compilation = obj.compile(userName, codeString, "12345678");
			System.out.println(compilation);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}