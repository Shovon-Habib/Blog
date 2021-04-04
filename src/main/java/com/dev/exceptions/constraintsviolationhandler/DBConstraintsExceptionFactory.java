package com.dev.exceptions.constraintsviolationhandler;

import com.dev.exceptions.constraintsviolationhandler.implementation.H2ConstraintsViolation;
import com.dev.exceptions.constraintsviolationhandler.implementation.NoDBConstraintsViolation;
import com.dev.exceptions.constraintsviolationhandler.utility.DataSourceInfo;
import org.springframework.stereotype.Component;

@Component
public class DBConstraintsExceptionFactory {

    private final DataSourceInfo dataSourceInfo;

    private final ConstraintsConfiguration constraintsConfiguration;

    public DBConstraintsExceptionFactory(DataSourceInfo dataSourceInfo, ConstraintsConfiguration constraintsConfiguration) {
        this.dataSourceInfo = dataSourceInfo;
        this.constraintsConfiguration = constraintsConfiguration;
    }

    public DataIntegrityViolationInterface create() {
        if (dataSourceInfo.getDbProductName().equals(DBProductNameEnum.H2.getValue())) {
            return new H2ConstraintsViolation(constraintsConfiguration);
        }
        return new NoDBConstraintsViolation();
    }
}
