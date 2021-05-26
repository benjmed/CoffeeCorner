package com.coffeecorner.service;

/**
 * @author Mohamed.Benjelloun
 * The File resources Services for managing reading files
 */
public interface FileResourcesService {

    /**
     * open the file located in resources directory and return the content of the file as string
     * @param fileName: name of the file
     * @return content of the file
     */
    String readFile(String fileName);
}
