# Unit testing
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

### 2) `createBeer`

`createBeer`, as the name implies, persists a new beer in the `Database`.
First, complete the implementation.

Then, test that the beer was persisted correctly. IE: Test that the values for each field in the persisted `Beer` are equal to that of the input.

Write a second test where the database throws a `BeerAlreadyExistsException` and assert accordingly.

### 3) `updateBeer`

`updateBeer` updates all the fields in `Beer`.

First, complete the implementation. You will want to look up any potential already existing beer for the id and check if there are differences.

To save resources, you wouldn't want to update a field if it's not needed.

Next up, test the implementation. Make sure to test these cases:
- Updating a non-existing beer should throw a Runtime(!) exception.
- Updating a beer which does not have changes for a certain field should have no interaction with the setter in the `BeerEntity`.

### 4) `deleteBeer`

`deleteBeer` removes beers from the `Database` and returns true if a beer was deleted.

First, complete the implementation. 

Then test the following:
- Deleting a beer that existed should return true.
- Deleting a beer that did not exist should return false.
