package com.axxes.testing.mocking.unittesting.exception;

public class BeerNotFoundException extends RuntimeException {

    private final long id;

    public BeerNotFoundException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Beer with id " + id + " does not exist!";
    }
}
