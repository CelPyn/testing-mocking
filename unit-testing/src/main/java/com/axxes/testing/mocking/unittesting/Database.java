package com.axxes.testing.mocking.unittesting;

import java.util.List;
import java.util.Optional;

public interface Database {

    BeerEntity create(BeerEntity beerEntity);

    void update(BeerEntity beerEntity);

    Optional<BeerEntity> findById(long id);

    List<BeerEntity> getAll();

    void delete(long id);

}
