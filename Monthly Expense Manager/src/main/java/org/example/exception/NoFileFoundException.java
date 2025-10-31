package org.example.exception;

public class NoFileFoundException extends RuntimeException{
    public NoFileFoundException(String message){
        super(message);
    }
}
