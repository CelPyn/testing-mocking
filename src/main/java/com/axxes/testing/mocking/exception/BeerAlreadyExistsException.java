package com.axxes.testing.mocking.exception;

import com.axxes.testing.mocking.Beer;

public class BeerAlreadyExistsException extends RuntimeException {

    private final Beer beer;

    public BeerAlreadyExistsException(Beer beer) {
        this.beer = beer;
    }

    @Override
    public String getMessage() {
        return "Beer with id " + beer.getId() + " already exists!";
    }
}
