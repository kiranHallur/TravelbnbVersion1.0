package com.travelbnb.travelbnb.exception;

public class ResourceNotFound extends RuntimeException{

    private String errorMsg;

    public ResourceNotFound(String errorMsg){
        super(errorMsg);
    }
}
