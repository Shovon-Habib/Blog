package com.dev.exceptions.constraintsviolationhandler.implementation;

import com.dev.exceptions.GenericException;
import com.dev.exceptions.constraintsviolationhandler.ConstraintsConfiguration;
import com.dev.exceptions.constraintsviolationhandler.DataIntegrityViolationInterface;
import org.json.simple.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class H2ConstraintsViolation implements DataIntegrityViolationInterface {

    private final ConstraintsConfiguration constraintsConfiguration;

    public H2ConstraintsViolation(ConstraintsConfiguration constraintsConfiguration) {
        this.constraintsConfiguration = constraintsConfiguration;
    }

    @Override
    public Map<String, Object> parseError(DataIntegrityViolationException ex) {
        return this.parse(ex);
    }

    private Map<String, Object> parse(DataIntegrityViolationException ex) {
        String errMessage = ex.getCause().getCause().getMessage();
        return this.prepareErrorMessageFromConfiguration(errMessage);
    }

    private Map<String, Object> prepareErrorMessageFromConfiguration(String sentenceWithDBIndexDetails) {

        String uniqueIndexStartsWithRegex = ".";
        String uniqueIndexPrefixRegex = "_INDEX";

        if (sentenceWithDBIndexDetails.contains(uniqueIndexStartsWithRegex)) {
            int position = sentenceWithDBIndexDetails.indexOf(uniqueIndexStartsWithRegex);
            int endPosition = sentenceWithDBIndexDetails.indexOf(uniqueIndexPrefixRegex);
            String dbIndexName = sentenceWithDBIndexDetails.substring(position+1, endPosition);
            return parseMessage(dbIndexName);
        }
        Map<String, Object> messageMap = new HashMap();
        messageMap.put("message", (sentenceWithDBIndexDetails == null ?
                "Constraint Violated. Constrains Violation Error Message Parse Fails" :
                sentenceWithDBIndexDetails));
        return messageMap;
    }

    private Map<String, Object> prepareErrorMessageFromConfigurationForForeignKeyConsViolation(String sentenceWithDBIndexDetails) {

        StringBuilder foreignIndexStartsWithRegex = new StringBuilder("The INSERT statement conflicted with the FOREIGN KEY constraint \"");
        int position = sentenceWithDBIndexDetails.indexOf(foreignIndexStartsWithRegex.toString());
        if (position != -1) {
            String dbIndexName = sentenceWithDBIndexDetails.substring(position + foreignIndexStartsWithRegex.length())
                    .replace("\"", "");
            return parseMessage(dbIndexName);
        }
        throw new GenericException("Constraint Violated. Constrains Violation Error Message Parse Fails");
    }

    private String[] parseSentencesFromErrorMessage(String errorMessage) {
        String periodAndSpace = "\\. ";
        String endsWithPeriod = "\\.$";
        return errorMessage.split(periodAndSpace + "|" + endsWithPeriod);
    }

    private Map<String, Object> parseMessage(String dbIndexName) {

        JSONObject jsonObject = constraintsConfiguration.getJsonObject();
        Map<String, Object> map = new HashMap<>();
        if (jsonObject.containsKey(dbIndexName.toLowerCase())) {
            JSONObject details = (JSONObject) jsonObject.get(dbIndexName.toLowerCase());
            if (details.containsKey("message")) {
                map.put("fields", details.get("fields"));
                map.put("message", details.get("message"));
                return map;
            }
        }
        return map;
    }
}
