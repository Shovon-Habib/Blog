package com.dev.exceptions.constraintsviolationhandler;

public enum DBProductNameEnum {
    H2("H2");

    private String value;

    DBProductNameEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
