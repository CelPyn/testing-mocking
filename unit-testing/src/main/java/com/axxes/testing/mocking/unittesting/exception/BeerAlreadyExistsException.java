package com.axxes.testing.mocking.unittesting.exception;

import com.axxes.testing.mocking.unittesting.Beer;

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
