package com.coffeecorner.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Mohamed.Benjelloun
 * Implementation of the service FileResourcesService
 */
public class FileResourcesServiceImpl implements FileResourcesService {

    /**
     * open the file located in resources directory and return the content of the file as string
     * @param fileName: name of the file
     * @return content of the file
     */
    public String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }

        try (InputStreamReader streamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

}
