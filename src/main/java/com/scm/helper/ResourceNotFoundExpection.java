package com.scm.helper;

public class ResourceNotFoundExpection extends RuntimeException {
    public ResourceNotFoundExpection(String message) {
        super(message);
    }
    public ResourceNotFoundExpection() {
        super("Resource Not Found");
    }


}
