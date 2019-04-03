package com.myRetail.model;


import java.util.List;
import java.util.ArrayList;

public class ExceptionResponse {
    private String errorCode;
    private List<String> errorMessage = new ArrayList<>();

    public ExceptionResponse() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getErrorMessage() {
        return new ArrayList<>(this.errorMessage);
    }

    public void setErrorMessage(ArrayList<String> errorMessage) {
        this.errorMessage = new ArrayList<>(errorMessage);
    }
}
