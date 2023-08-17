package com.axxes.testing.mocking.unittesting;

import com.axxes.testing.mocking.unittesting.exception.BeerAlreadyExistsException;
import com.axxes.testing.mocking.unittesting.exception.BeerNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BeerService {

    private final Database database;

    public BeerService(Database database) {
        this.database = database;
    }


    public List<Beer> getAllBeers() {
        return List.of();
    }

    public Beer createBeer(Beer beer) {
        return null;
    }

    public Beer updateBeer(Beer beer) {
        return null;
    }

    public boolean deleteBeer(long id) {
        return true;
    }

    private Beer map(BeerEntity entity) {
        return new Beer(entity.getId(), entity.getName(), entity.getPrice(), entity.getRating());
    }

    private BeerEntity map(Beer beer) {
        return new BeerEntity(beer.getId(), beer.getName(), beer.getPrice(), beer.getRating());
    }

}
