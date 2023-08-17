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

    /**
     * Not using Stream and Stream::map is also an acceptable solution.
     *
     * @see BeerService#getAllBeersImperative()
     */
    public List<Beer> getAllBeers() {
        return database.getAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    /**
     * Alternative method of getting all beers.
     */
    public List<Beer> getAllBeersImperative() {
        List<BeerEntity> allEntities = database.getAll();

        List<Beer> beers = new ArrayList<>(allEntities.size());
        for (BeerEntity entity : allEntities) {
            Beer beer = map(entity);
            beers.add(beer);
        }
        return beers;
    }

    public Beer createBeer(Beer beer) {
        Optional<BeerEntity> optionalBeer = database.findById(beer.getId());
        if (optionalBeer.isPresent()) {
            throw new BeerAlreadyExistsException(beer);
        }
        BeerEntity beerEntity = map(beer);
        database.create(beerEntity);
        return beer;
    }

    public Beer updateBeer(Beer beer) {
        Optional<BeerEntity> optionalBeer = database.findById(beer.getId());

        BeerEntity entity = optionalBeer.orElseThrow(() -> new BeerNotFoundException(beer.getId()));

        if (!beer.getName().equals(entity.getName())) {
            entity.setName(beer.getName());
        }
        if (beer.getPrice() != entity.getPrice()) {
            entity.setPrice(beer.getPrice());
        }
        if (beer.getRating() != entity.getRating()) {
            entity.setRating(beer.getRating());
        }

        database.update(entity);

        return beer;
    }

    /**
     * Not using Optional::Map is also an acceptable solution.
     *
     * @see BeerService#deleteBeerImperative(long)
     */
    public boolean deleteBeer(long id) {
        Optional<BeerEntity> entity = database.findById(id);
        entity.map(BeerEntity::getId)
                .ifPresent(database::delete);
        return entity.isPresent();
    }

    /**
     * Alternative method of deleting a beer.
     */
    public boolean deleteBeerImperative(long id) {
        Optional<BeerEntity> entity = database.findById(id);
        if (entity.isPresent()) {
            BeerEntity beerEntity = entity.get();
            database.delete(beerEntity.getId());
            return true;
        }
        return false;
    }

    private Beer map(BeerEntity entity) {
        return new Beer(entity.getId(), entity.getName(), entity.getPrice(), entity.getRating());
    }

    private BeerEntity map(Beer beer) {
        return new BeerEntity(beer.getId(), beer.getName(), beer.getPrice(), beer.getRating());
    }

}
