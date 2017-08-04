package com.example.androidu.musicmaker.model;

/**
 * Created by christopherfong on 7/12/17.
 */

public class BadInputException extends RuntimeException{

    public BadInputException(String message) {
        super(message);
    }

    public BadInputException() {

    }
}
