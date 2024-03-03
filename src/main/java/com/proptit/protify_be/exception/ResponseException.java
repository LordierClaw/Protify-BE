package com.proptit.protify_be.exception;

import com.proptit.protify_be.constants.ResponseValue;

public class ResponseException extends Exception {
    private ResponseValue responseValue;

    public ResponseException(ResponseValue responseValue) {
        super(responseValue.getMessage());
        this.responseValue = responseValue;
    }

    public ResponseValue getResponseValue() {
        return responseValue;
    }
}
