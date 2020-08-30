package ru.sunlab.meshgrouptest.exception;

public class ProfileExistsException extends RuntimeException{
    public ProfileExistsException(String message) {
        super(message);
    }
}
