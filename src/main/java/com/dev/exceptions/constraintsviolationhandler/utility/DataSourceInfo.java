package com.dev.exceptions.constraintsviolationhandler.utility;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DataSourceInfo {

    private String dbProductName;

    public DataSourceInfo(DataSource dataSource) throws SQLException {
        setDbProductName(dataSource.getConnection().getMetaData().getDatabaseProductName());
    }

    public String getDbProductName() {
        return dbProductName;
    }

    public void setDbProductName(String dbProductName) {
        this.dbProductName = dbProductName;
    }
}
