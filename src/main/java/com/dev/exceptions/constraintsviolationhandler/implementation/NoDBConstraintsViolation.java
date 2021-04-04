package com.dev.exceptions.constraintsviolationhandler.implementation;

import com.dev.exceptions.constraintsviolationhandler.DataIntegrityViolationInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NoDBConstraintsViolation implements DataIntegrityViolationInterface {
    @Override
    public Map<String, Object> parseError(DataIntegrityViolationException ex) {
        return null;
    }
}
