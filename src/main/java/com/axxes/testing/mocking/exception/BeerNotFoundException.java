package com.axxes.testing.mocking.exception;

public class BeerNotFoundException extends Exception {

    private final long id;

    public BeerNotFoundException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Beer with id " + id + " does not exist!";
    }
}
