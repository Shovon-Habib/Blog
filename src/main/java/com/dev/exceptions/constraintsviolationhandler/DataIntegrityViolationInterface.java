package com.dev.exceptions.constraintsviolationhandler;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.Map;

public interface DataIntegrityViolationInterface {
    Map<String, Object> parseError(DataIntegrityViolationException ex);
}
