package com.code.javaCompiler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DynamicClassLoader extends ClassLoader {
    private String basePath;

    public DynamicClassLoader(String basePath, ClassLoader parent) {
        super(parent);
        this.basePath = basePath;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] b = loadClassFromFile(name);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }

    private byte[] loadClassFromFile(String fileName) throws IOException {
        // Construct the full path for the class file
        String filePath = basePath + File.separator + ".class";
        // Read the class file into a byte array
        InputStream inputStream = new FileInputStream(filePath);
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = inputStream.read();
        while (-1 != nextValue) {
            byteStream.write(nextValue);
            nextValue = inputStream.read();
        }
        buffer = byteStream.toByteArray();
        inputStream.close();
        return buffer;
    }
}
