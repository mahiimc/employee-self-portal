package com.imc.exception;


public class DuplicateUserException extends  BaseException {
    public DuplicateUserException(String username) {
        super(String.format("User already exists with username : %s ", username), "DUPLICATE_USER");
    }
}
