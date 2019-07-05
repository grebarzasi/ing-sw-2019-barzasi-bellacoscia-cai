package it.polimi.ingsw.utils;

import java.io.InputStream;

/**
 * File reader for static Object building classes
 */
public class FileLoader {

    /**
     * Reads the file from the given path
     * @param path the path of the file
     * @return the content of the file as Stream
     */
    public InputStream getResource(String path) {
        return getClass().getResourceAsStream(path);
    }

}
