package com.axxes.testing.mocking;

import java.util.List;

public class BeerService {

    private final Database database;

    public BeerService(Database database) {
        this.database = database;
    }

    public List<Beer> getAllBeers() {
        return List.of();
    }

    public Beer createBeer(Beer beer) {
        return beer;
    }

    public Beer updateBeer(Beer beer) {
        return beer;
    }

    public boolean deleteBeer(int id) {
        return false;
    }

}
