package com.imc.exception;

public abstract class BaseException extends  RuntimeException {

    private final String errorCode;

    protected BaseException( String message, String errorCode ) {
        super(message);
        this.errorCode = errorCode;
    }

    public final String getErrorCode() {
        return this.errorCode;
    }
}
