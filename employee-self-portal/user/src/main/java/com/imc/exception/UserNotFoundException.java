package com.imc.exception;


public class UserNotFoundException extends  BaseException {
    public UserNotFoundException(Long userId) {
        super(String.format("User not found %s",userId), "USER_NOT_FOUND");
    }
}
