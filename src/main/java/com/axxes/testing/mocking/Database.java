package com.axxes.testing.mocking;

import com.axxes.testing.mocking.exception.BeerNotFoundException;

import java.util.List;

public interface Database {

    Beer create(Beer beer);

    void updatePrice(long id, double price) throws BeerNotFoundException;

    void updateName(long id, String name) throws BeerNotFoundException;

    void updateRating(long id, double rating) throws BeerNotFoundException;

    Beer findById(long id) throws BeerNotFoundException;

    List<Beer> getAll();

    void delete(long id) throws BeerNotFoundException;

}
