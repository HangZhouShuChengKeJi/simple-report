package com.orange.heart.entity;

import java.io.Serializable;

public class ColumnsWithBLOBs extends Columns implements Serializable {
    private String columnDefault;

    private String columnType;

    private static final long serialVersionUID = 1L;

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}