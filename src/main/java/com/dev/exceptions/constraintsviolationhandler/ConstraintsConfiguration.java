package com.dev.exceptions.constraintsviolationhandler;

import com.dev.utils.ClasspathJsonReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConstraintsConfiguration {

    private JSONObject jsonObject;

    private String configFilePath = "dataintegrity-violation-config.json";

    public ConstraintsConfiguration() throws IOException, ParseException {
        this.jsonObject = ClasspathJsonReader.read(configFilePath);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
