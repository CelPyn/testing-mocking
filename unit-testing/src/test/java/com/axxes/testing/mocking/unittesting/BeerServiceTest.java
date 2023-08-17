package com.axxes.testing.mocking.unittesting;


import com.axxes.testing.mocking.unittesting.exception.BeerAlreadyExistsException;
import com.axxes.testing.mocking.unittesting.exception.BeerNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class BeerServiceTest {

    private final Database database = mock(Database.class);
    private final BeerService beerService = new BeerService(database);

    @Test
    void when_getAllBeers_allBeersAreReturned() {
        List<BeerEntity> entities = List.of(
                new BeerEntity(12L, "Stella", 3.50, 4.9),
                new BeerEntity(45L, "Westmalle", 4.50, 3.6)
        );
        when(database.getAll()).thenReturn(entities);

        List<Beer> allBeers = beerService.getAllBeers();
        Assertions.assertThat(allBeers).isNotNull()
                .isNotEmpty()
                .hasSize(2);
        verifyBeerIsEqualToEntity(allBeers.get(0), entities.get(0));
        verifyBeerIsEqualToEntity(allBeers.get(1), entities.get(1));

        verify(database, times(1)).getAll();
        verifyNoMoreInteractions(database);
    }

    @Test
    void when_createBeer_beerDoesNotExist_beerIsCreated() {
        when(database.findById(12)).thenReturn(Optional.empty());

        Beer stella = new Beer(12L, "Stella", 3.50, 4.9);
        Beer result = beerService.createBeer(stella);

        assertThat(result).isEqualTo(stella);
        verify(database, times(1)).findById(12);
        verify(database, times(1)).create(eq(new BeerEntity(12L, "Stella", 3.50, 4.9)));
        verifyNoMoreInteractions(database);
    }

    @Test
    void when_createBeer_beerExists_beerIsNotCreated() {
        when(database.findById(12)).thenReturn(Optional.of(new BeerEntity(12L, "Stella", 3.50, 4.9)));

        Beer stella = new Beer(12L, "Stella", 3.50, 4.9);
        assertThatExceptionOfType(BeerAlreadyExistsException.class).isThrownBy(() -> beerService.createBeer(stella));
        verify(database, times(1)).findById(12);
        verifyNoMoreInteractions(database);
    }

    @Test
    void when_updateBeer_beerExists_beerIsUpdated() {
        when(database.findById(12)).thenReturn(Optional.of(new BeerEntity(12L, "Stella", 3.50, 4.9)));

        beerService.updateBeer(new Beer(12L, "Stella", 3.90, 4.9));

        verify(database, times(1)).update(new BeerEntity(12L, "Stella", 3.90, 4.9));
        verify(database, times(1)).findById(12);
        verifyNoMoreInteractions(database);
    }

    @Test
    void when_updateBeer_beerExists_beerIsSameAsEntity_beerIsNotUpdated() {
        BeerEntity beerEntity = mock(BeerEntity.class);
        when(beerEntity.getId()).thenReturn(12L);
        when(beerEntity.getName()).thenReturn("Stella");
        when(beerEntity.getPrice()).thenReturn(3.50);
        when(beerEntity.getRating()).thenReturn(4.90);

        when(database.findById(12)).thenReturn(Optional.of(beerEntity));

        beerService.updateBeer(new Beer(12L, "Stella", 3.50, 4.9));

        verify(database, times(1)).update(eq(beerEntity));
        verify(database, times(1)).findById(12);
        verify(beerEntity, never()).setName(anyString());
        verify(beerEntity, never()).setRating(anyDouble());
        verify(beerEntity, never()).setPrice(anyDouble());
        verifyNoMoreInteractions(database);
    }

    @Test
    void when_updateBeer_beerDoesNotExist_beerIsNotUpdated() {
        when(database.findById(12)).thenReturn(Optional.empty());

        Beer stella = new Beer(12L, "Stella", 3.50, 4.9);
        assertThatExceptionOfType(BeerNotFoundException.class).isThrownBy(() -> beerService.updateBeer(stella));

        verify(database, times(1)).findById(12);
        verifyNoMoreInteractions(database);
    }

    @Test
    void when_deleteBeer_beerExists_trueIsReturned() {
        when(database.findById(12)).thenReturn(Optional.of(new BeerEntity(12L, "Stella", 3.50, 4.9)));

        boolean result = beerService.deleteBeer(12);

        assertThat(result).isTrue();
        verify(database, times(1)).findById(12);
        verify(database, times(1)).delete(12);
        verifyNoMoreInteractions(database);
    }

    @Test
    void when_deleteBeer_beerDoesNotExist_falseIsReturned() {
        when(database.findById(12)).thenReturn(Optional.empty());

        boolean result = beerService.deleteBeer(12);

        assertThat(result).isFalse();
        verify(database, times(1)).findById(12);
        verifyNoMoreInteractions(database);
    }

    void verifyBeerIsEqualToEntity(Beer beer, BeerEntity beerEntity) {
        assertThat(beer.getId()).isEqualTo(beerEntity.getId());
        assertThat(beer.getName()).isEqualTo(beerEntity.getName());
        assertThat(beer.getPrice()).isEqualTo(beerEntity.getPrice());
        assertThat(beer.getRating()).isEqualTo(beerEntity.getRating());
    }


}
