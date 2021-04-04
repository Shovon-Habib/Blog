package com.dev.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ClasspathJsonReader {
    public static JSONObject read(String path) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        Resource resource = new ClassPathResource(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        String contents = reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));

        return (JSONObject) jsonParser.parse(contents);
    }
}
