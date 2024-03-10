package com.code.javaCompiler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.CommonLogger;

public class TestCase {
    static Logger logger = new CommonLogger(TestCase.class).getLogger();
    private String methodName;
    private Object[] parameters;
    private Class<?>[] parameterTypes;
    private Object expectedOutput;
    private Object targetObject;
    private JSONObject testcase;
    private int testNum;
    private static ThreadLocal<ByteArrayOutputStream> baos = new ThreadLocal<>();
    private static ThreadLocal<PrintStream> printStream = new ThreadLocal<>();

    public TestCase(Object targetObject, String methodName, JSONObject testcase, Object expectedOutput, int testNum,
            Object... parameters) {
        this.targetObject = targetObject;
        this.methodName = methodName;
        this.parameters = parameters;
        this.parameterTypes = new Class<?>[parameters.length];
        this.expectedOutput = expectedOutput;
        this.testcase = testcase;
    }

    public Callable<JSONObject> toCallable() {
        return () -> {
            JSONObject resultObject = new JSONObject();

            try {
                // preparing seperate printstream for get all logs

                baos.set(new ByteArrayOutputStream());
                printStream.set(new PrintStream(baos.get()));
                PrintStream localPrinter = printStream.get();
                System.setOut(localPrinter);

                convertParametersType(parameters, parameterTypes);

                logger.info(parameterTypes[0]);
                Method method = targetObject.getClass().getMethod(methodName, parameterTypes);

                long startTime = System.nanoTime();

                Object result = method.invoke(targetObject, parameters);

                long endTime = System.nanoTime();

                String logs = baos.get().toString();

                boolean isPass = compareObjects(expectedOutput, result);
                long duration = (endTime - startTime);

                logger.info(isPass);

                if (isPass) {
                    resultObject.put("Result", isPass);
                    resultObject.put("logs", logs);
                    resultObject.put("time", "Completed in: " + duration + " ns");

                } else {
                    resultObject.put("Result", isPass);
                    resultObject.put("output", result);
                    resultObject.put("message",
                            String.format("Test Failed: Expected %s, got %s", expectedOutput, result));
                    resultObject.put("logs", logs);
                }

            } catch (Exception e) {
                e.printStackTrace();
                ;
                logger.error(e);
            } finally {
                baos.remove();
                printStream.remove();
            }
            logger.info("Test Case Number  =  " + testNum);
            resultObject.put("name", "test" + testNum);
            return resultObject;
        };
    }

    public boolean compareObjects(Object a, Object b) {
        if (a == b && !(a instanceof String) && !(b instanceof String)) {
            return true;
        } else if (a.getClass().isArray() && b.getClass().isArray()) {
            return comparePrimitiveArrays(a, b);
        } else if (a instanceof Map && b instanceof Map) {
            return compareMaps((Map<?, ?>) a, (Map<?, ?>) b);
        } else {
            return b.equals(a);
        }

    }

    private static boolean compareMaps(Map<?, ?> a, Map<?, ?> b) {
        for (Map.Entry<?, ?> entry : a.entrySet()) {
            Object key = entry.getKey();
            Object valueA = entry.getValue();
            Object valueB = b.get(key);

            if (!Objects.equals(valueA, valueB)) {
                return false;
            }
        }
        return true;

    }

    private boolean comparePrimitiveArrays(Object a, Object b) {
        return Arrays.deepEquals((Object[]) a, (Object[]) b);
    }

    private void convertParametersType(Object[] params, Class<?>[] paramTypes) throws JSONException {

        for (int i = 0; i < params.length; i++) {
            Object parameter = params[i];
            Class<?> parameterType = parameter.getClass();

            if (parameterType == JSONArray.class) {
                this.parameters[i] = convertJsonArrayIntoObjectArray((JSONArray) parameter);
                parameter = this.parameters[i];
                paramTypes[i] = getPrimitiveArrayClassType(parameter, i);
            } else if (parameterType == Integer.class) {
                params[i] = (int) parameter;
                paramTypes[i] = int.class;
            } else if (parameter instanceof String) {
                params[i] = (String) parameter;
                paramTypes[i] = String.class;
            } else if (parameterType == Boolean.class) {
                params[i] = (boolean) parameter;
                paramTypes[i] = boolean.class;
            } else if (parameterType == Byte.class) {
                params[i] = (byte) parameter;
                paramTypes[i] = byte.class;
            } else if (parameterType == Character.class) {
                params[i] = (char) parameter;
                paramTypes[i] = char.class;
            } else if (parameterType == Double.class) {
                params[i] = (double) parameter;
                paramTypes[i] = double.class;
            } else if (parameterType == Float.class) {
                params[i] = (float) parameter;
                paramTypes[i] = float.class;
            } else if (parameterType == Long.class) {
                params[i] = (long) parameter;
                paramTypes[i] = long.class;
            } else if (parameterType == Short.class) {
                params[i] = (short) parameter;
                paramTypes[i] = short.class;
            } else {
                paramTypes[i] = parameterType;
            }

        }

    }

    public Class<?> getPrimitiveArrayClassType(Object param, int index) {

        Object[] objArray = ((Object[]) param);
        Object firstValue = objArray[0];

        if (firstValue instanceof Integer) {
            int[] intArray = new int[objArray.length];

            for (int i = 0; i < objArray.length; i++) {
                intArray[i] = (int) objArray[i];
            }
            this.parameters[index] = intArray;

            return int[].class;
        } else if (firstValue instanceof String) {
            String[] stringArray = new String[objArray.length];

            for (int i = 0; i < objArray.length; i++) {
                stringArray[i] = (String) objArray[i];
            }
            this.parameters[index] = stringArray;

            return String[].class;
        } else if (firstValue instanceof Boolean) {
            Boolean[] booleanArray = new Boolean[objArray.length];

            for (int i = 0; i < objArray.length; i++) {
                booleanArray[i] = (Boolean) objArray[i];
            }
            this.parameters[index] = booleanArray;

            return boolean[].class;
        } else if (firstValue instanceof Byte) {
            byte[] byteArray = new byte[objArray.length];

            for (int i = 0; i < objArray.length; i++) {
                byteArray[i] = (Byte) objArray[i];
            }
            this.parameters[index] = byteArray;

            return byte[].class;
        } else if (firstValue instanceof Character) {
            char[] charArray = new char[objArray.length];

            for (int i = 0; i < objArray.length; i++) {
                charArray[i] = (Character) objArray[i];
            }
            this.parameters[index] = charArray;

            return char[].class;
        } else if (firstValue instanceof Double) {
            double[] doubleArray = new double[objArray.length];

            for (int i = 0; i < objArray.length; i++) {
                doubleArray[i] = (Double) objArray[i];
            }
            this.parameters[index] = doubleArray;

            return double[].class;
        } else if (firstValue instanceof Float) {
            float[] floatArray = new float[objArray.length];

            for (int i = 0; i < objArray.length; i++) {
                floatArray[i] = (Float) objArray[i];
            }
            this.parameters[index] = floatArray;

            return float[].class;
        } else if (firstValue instanceof Long) {
            long[] longArray = new long[objArray.length];

            for (int i = 0; i < objArray.length; i++) {
                longArray[i] = (Long) objArray[i];
            }
            this.parameters[index] = longArray;

            return long[].class;
        } else if (firstValue instanceof Short) {
            short[] shortArray = new short[objArray.length];

            for (int i = 0; i < objArray.length; i++) {
                shortArray[i] = (Short) objArray[i];
            }
            this.parameters[index] = shortArray;

            return short[].class;
        } else {
            return param.getClass();
        }
    }

    public Object[] convertJsonArrayIntoObjectArray(JSONArray paramArray) throws JSONException {
        Object[] objectArray = new Object[paramArray.length()];

        for (int i = 0; i < paramArray.length(); i++) {
            objectArray[i] = paramArray.get(i);
        }

        return objectArray;

    }

}
