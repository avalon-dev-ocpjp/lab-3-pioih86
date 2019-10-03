package ru.avalon.java.ocpjp.labs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceManager {
    public InputStream getInstance(String path) {
        return getClass().getResourceAsStream(path);
    }

    public String readSQL(String path) {
        path = "/resources/sql/" + path;
        try (InputStream stream = getInstance(path); InputStreamReader reader = new InputStreamReader(stream);
             BufferedReader in = new BufferedReader(reader)) {
            return in.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            System.err.println(e);
            return "";
        }
    }
}
