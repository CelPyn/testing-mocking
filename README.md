# Testing and Mocking
## Context
In this project you can find a `BeerService` which has a dependency on a `Database`.

Sadly there is no implementation available for `Database`, but you really really want to test the `BeerService` 
(you're a good software consultant and want to take your responsibility).

Without spending all day providing a database implementation, you decide to use mocking to test the `BeerService`, 
as this class is an individual component and should be unit tested!

## Exercises
### 1) `getAllBeers`

`getAllBeers` returns a list of all the beers in the database.

Complete the implementation of `getAllBeers` so that it calls the appropriate method in `Database`.
Then, write a unit test that tests this functionality. Use a mock to define the behaviour of `Database::getAll`.

You should assert that:
- The `getAll` method was called and that the returned list is the same size as you expect.
- The result of the method is equal to what you mocked the database to return.

Extra:
Bonus points if you use a stream with a map operation to transform all the `BeerEntities` to `Beer` instances

<details>
<summary>Hints</summary>
<br>
    
    You can easily transform beers to entities and back with these methods:

    private Beer map(BeerEntity entity) {
        return new Beer(entity.getId(), entity.getName(), entity.getPrice(), entity.getRating());
    }

    private BeerEntity map(Beer beer) {
        return new BeerEntity(beer.getId(), beer.getName(), beer.getPrice(), beer.getRating());
    }

    You can verify that a beer is equal to a beer entity with this method:

    void verifyBeerIsEqualToEntity(Beer beer, BeerEntity beerEntity) {
        assertThat(beer.getId()).isEqualTo(beerEntity.getId());
        assertThat(beer.getName()).isEqualTo(beerEntity.getName());
        assertThat(beer.getPrice()).isEqualTo(beerEntity.getPrice());
        assertThat(beer.getRating()).isEqualTo(beerEntity.getRating());
    }

</details>

### 2) `createBeer`

`createBeer`, as the name implies, persists a new beer in the `Database`.
First, complete the implementation.

Then, test that the beer was persisted correctly. IE: Test that the values for each field in the persisted `Beer` are equal to that of the input.

Write a second test where the database throws a `BeerAlreadyExistsException` and assert accordingly.

<details>
<summary>Hints</summary>
<br>

    You can verify that a beer was created with the correct field by doing:

    verify(database, times(1)).create(eq(new BeerEntity(12L, "Stella", 3.50, 4.9)));

    You can check that an exception was thrown by using:

    assertThatExceptionOfType(BeerAlreadyExistsException.class).isThrownBy(() -> beerService.createBeer(stella));

</details>

### 3) `updateBeer`

`updateBeer` updates all the fields in `Beer`.

First, complete the implementation. You will want to look up any potential already existing beer for the id and check if there are differences.

To save resources, you wouldn't want to update a field if it's not needed.

Next up, test the implementation. Make sure to test these cases:
- Updating a non-existing beer should throw a Runtime(!) exception.
- Updating a beer which does not have changes for a certain field should have no interaction with the setter in the `BeerEntity`.

<details>
<summary>Hints</summary>
<br>

    You can verify that a beer was updated without touching any of the fields by doing:

    // Creating a mock entity returned by the DB:
        BeerEntity beerEntity = mock(BeerEntity.class);
        when(beerEntity.getId()).thenReturn(12L);
        when(beerEntity.getName()).thenReturn("Stella");
        when(beerEntity.getPrice()).thenReturn(3.50);
        when(beerEntity.getRating()).thenReturn(4.90);

        when(database.findById(12)).thenReturn(Optional.of(beerEntity));

    // asserting the mock
        verify(beerEntity, never()).setName(anyString());
        verify(beerEntity, never()).setRating(anyDouble());
        verify(beerEntity, never()).setPrice(anyDouble());

</details>

### 4) `deleteBeer`

`deleteBeer` removes beers from the `Database` and returns true if a beer was deleted.

First, complete the implementation. 

Then test the following:
- Deleting a beer that existed should return true.
- Deleting a beer that did not exist should return false.
