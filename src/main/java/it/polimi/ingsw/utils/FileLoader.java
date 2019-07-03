package it.polimi.ingsw.utils;

import java.io.InputStream;

public class FileLoader {

    public InputStream getResource(String path) {
        return getClass().getResourceAsStream(path);
    }

}
