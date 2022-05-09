package com.axxes.testing.mocking;

import com.axxes.testing.mocking.exception.BeerNotFoundException;

import java.util.List;

public interface Database {

    BeerEntity create(BeerEntity beerEntity);

    void update(BeerEntity beerEntity);

    BeerEntity findById(long id);

    List<BeerEntity> getAll();

    void delete(long id);

}
